package com.eumji.zblog.controller.user;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

public class Menu {
	
	private Integer id;
    private String menuName;
    private Integer parentId;
    private Integer position;
    private List<Menu> childMenu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public List<Menu> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<Menu> childMenu) {
        this.childMenu = childMenu;
    }

    public Menu(Integer id, String menuName, Integer parentId, Integer position) {
        this.id = id;
        this.menuName = menuName;
        this.parentId = parentId;
        this.position = position;
    }

    public Menu() {
        super();
    }




    public static void main(String[] args) {
        List<Menu> treeMenu=new ArrayList<Menu>();

        List<Menu> list=new ArrayList<Menu>();
        list.add(new Menu(1, "习近平", -1, 1));
        list.add(new Menu(8, "湖南市长", 1, 2));
        list.add(new Menu(6, "金贞恩", -1, 2));
        list.add(new Menu(3, "湖北市长", 1, 1));
        list.add(new Menu(7, "石景山", 4, 2));
        list.add(new Menu(4, "北京市长", 1, 2));
        list.add(new Menu(5, "成寿寺", 4, 2));
        list.add(new Menu(10, "十堰", 3, 2));
        list.add(new Menu(9, "武汉", 3, 2));
        list.add(new Menu(2, "奥巴马", -1, 2));
        List<Menu> listTree=buildTree(list,-1);
        String trssJson= JSONArray.fromObject(listTree).toString();
        System.out.println("result--"+trssJson);

    }

    
    public static List<Menu> buildTree(List<Menu> list,int parentId){
        List<Menu> result=new ArrayList<Menu>();
        for (Menu menu : list) {
            int id = menu.getId();
            int pid = menu.getParentId();
            if (parentId == pid) {
            	List<Menu> buildTree = buildTree(list, id);
                menu.setChildMenu(buildTree);
                result.add(menu);
            }
        }
       
        return result;
    }

}
