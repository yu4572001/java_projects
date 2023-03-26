package com.yystudy.mhl.service;

import com.yystudy.mhl.dao.BillDAO;
import com.yystudy.mhl.dao.MutiTablebeanDAO;
import com.yystudy.mhl.domain.Bill;
import com.yystudy.mhl.domain.MutitableBean;

import java.util.List;
import java.util.UUID;

//编写与bill表相关的操作
public class BillService {
    private BillDAO billDAO = new BillDAO();
    private MenuService menuSer = new MenuService();
    private FoodTableService foodTableSer = new FoodTableService();
    private MutiTablebeanDAO mutiTablebeanDAO = new MutiTablebeanDAO();


    //编写点菜方法，生成对应的账单到bill表中，该项目中一个菜对应一个账单
    //点才成功时，还要更新该餐桌的状态
    public boolean orderMenu(int tableid, int menuid,int num){
        //生成一个随机的账单id
        String uuid = UUID.randomUUID().toString();

        int update = billDAO.update("insert into bill values (null,?,?,?,?,?,now(),'未付款');", uuid, tableid, menuid, num,
                menuSer.getMenyById(menuid).getPrice() * num);
        if (update <= 0){
            return false;
        }

        //更新餐桌的状态为就餐中
        if (!(foodTableSer.setState("就餐中", tableid))){
            return false;
        }

        return true;

    }


    //返回所有账单记录信息
    //演示多表查询
    public List<MutitableBean> getBillList(){
        List<MutitableBean> bills = mutiTablebeanDAO.queryMany("SELECT bill.id,billid,foodTableid,menuid,`name`,nums,money,billDate,state " +
                "FROM bill,menu " +
                "WHERE bill.menuid = menu.id", MutitableBean.class);
        return bills;
    }

    //查看餐桌是否有未结账的账单
    public boolean notPay(int id){
        Bill bill = billDAO.querySingle("select * from bill where foodTableid = ? and state = '未付款' limit 0,1;", Bill.class, id);
        if (bill == null){
            return false;
        }
        return true;
    }



    //完成餐桌的结账，将该桌的金额进行求和
    public boolean payBill(int tableid,String payMethod){
        //得到该桌的全部账单金额
        Object money = billDAO.queryScalar("SELECT SUM(money) FROM bill WHERE foodTableid = ? and state = '未付款';", tableid);
        System.out.println("您总共消费：" + money + "元");



        //结账以后，修改账单表中的该桌号的状态(已付款：微信、现金、支付宝)
        int update = billDAO.update("update bill set state = ? where foodTableid = ?", payMethod, tableid);
        if (update <= 0){
            return false;
        }

        //修改foodTable表中的餐桌的状态为空(清空该桌子)
        return foodTableSer.updateState(tableid);
    }
}
