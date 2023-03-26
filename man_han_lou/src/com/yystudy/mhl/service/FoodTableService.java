package com.yystudy.mhl.service;

import com.yystudy.mhl.dao.FoodTableDAO;
import com.yystudy.mhl.domain.FoodTable;
import java.util.List;

public class FoodTableService {
    private FoodTableDAO foodTableDAO = new FoodTableDAO();

    //显示餐桌状态的方法
    public void getTableinfo(){
        List<FoodTable> foodTables = foodTableDAO.queryMany("select * from foodTable;", FoodTable.class);
        System.out.println("餐桌编号\t\t状态\t\t预定人\t\t联系电话");
        for (FoodTable foodTable : foodTables) {
            if ("空".equals(foodTable.getState())){
                foodTable.setOrderName("");
                foodTable.setOrderTel("");
            }
            System.out.println(foodTable.getId() + "\t\t\t" + foodTable.getState()
            + "\t\t" + foodTable.getOrderName() + "\t\t" + foodTable.getOrderTel());
        }
        System.out.println();
    }



    //预定餐桌方法，传入要预定的餐桌id，预定人，预定人的电话，并返回一个布尔值
    public boolean bookingTable(int id,String name,String phone){
        //检查该餐桌是否存在
        FoodTable foodTable = foodTableDAO.querySingle("select * from foodTable where id=?", FoodTable.class, id);
        if (foodTable == null){
            return false;
        }

        //检查该餐桌的状态是否为空
        if (!("空".equals(foodTable.getState()))){
            return false;
        }

        //如果该餐桌存在且为空就可以进行预定
        int update = foodTableDAO.update("update foodTable set state='已预定', orderName=?, orderTel=? where id=?;",
                name, phone, id);

        return update > 0;
    }

    //显示当前状态为空的餐桌
    public void emptyTable(){
        List<FoodTable> foodTables = foodTableDAO.queryMany("select * from foodTable where state='空'", FoodTable.class);
        System.out.println("当前可预定的餐桌：");
        for (FoodTable foodTable : foodTables) {
            System.out.println(foodTable.getId() + "\t\t\t" + foodTable.getState());
        }
        System.out.println();
    }

    //设置餐桌状态
    public boolean setState(String state, int tableid){
        int update = foodTableDAO.update("update foodTable set state = ? where id = ?", state, tableid);
        return update > 0;
    }

    //根据传入桌号id，判断是否存在
    public boolean checkTableis(int tableid){
        FoodTable foodTable = foodTableDAO.querySingle("select * from foodTable where id = ?", FoodTable.class, tableid);
        if (foodTable == null){
            return false;
        }
        return true;
    }

    //根据传入id，设置相应桌子的状态为空，且清空联系人，联系电话
    public boolean updateState(int tableid){
        int update = foodTableDAO.update("update foodTable set state = '空', orderName='', orderTel='' where id = ?", tableid);
        return update > 0;
    }
}
