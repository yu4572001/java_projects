package com.yystudy.mhl.service;

import com.yystudy.mhl.dao.MenuDAO;
import com.yystudy.mhl.domain.Menu;

import java.util.List;

public class MenuService {
    private MenuDAO menuDAO = new MenuDAO();

    //显示所有菜品
    public void getAllFood(){
        List<Menu> menus = menuDAO.queryMany("select * from menu", Menu.class);
        System.out.println("菜品编号\t\t菜品名\t\t类别\t\t价格");
        for (Menu menu : menus) {
            System.out.println(menu.getId() + "\t\t\t" + menu.getName() +
                    "\t\t" + menu.getType() + "\t\t" + menu.getPrice());
        }
        System.out.println();

    }

    //根据传入菜品id，返回该菜品对象
    public Menu getMenyById(int id){
        Menu menu = menuDAO.querySingle("select * from menu where id = ?", Menu.class, id);
        return menu;
    }
}
