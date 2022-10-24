package com.yysduty.houserent.view;


import com.yysduty.houserent.service.HouseService;
import com.yysduty.houserent.utils.Utility;

public class HouseView {
    /**
     * 主界面菜单
     * 接受用户输入，以打开想要操作的功能
     */

    private boolean loop = true;
    private char key;      //接受用户输出的值选取功能
    private HouseService housemethod = new HouseService(10);   //房屋服务对象，以此来调用当中的服务方法，初始最多存10个房屋信息

    public void mainMenu(){


        do{
            System.out.println("-------------------房屋出租系统-------------------");
            System.out.println("\t\t\t1 新增房源");
            System.out.println("\t\t\t2 查找房屋");
            System.out.println("\t\t\t3 删除房屋");
            System.out.println("\t\t\t4 修改房屋信息");
            System.out.println("\t\t\t5 房屋列表");
            System.out.println("\t\t\t6 退    出");
            System.out.print("请输入你想要的操作值：");
            key = Utility.readMenuSelection();

            switch (key){
                case '1':
                    housemethod.addHouse();
                    break;
                case '2':
                    housemethod.selectHouse();
                    break;
                case '3':
                    housemethod.deleteHouse();
                    break;
                case '4':
                    housemethod.update();
                    break;
                case '5':
                    housemethod.houseList();
                    break;
                case '6':
                    System.out.println("你真的要退出吗？");
                    char choice = Utility.readConfirmSelection();
                    if (choice == 'Y'){
                        loop = false;
                    }
                    break;
            }
        }while(loop);
    }



    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
