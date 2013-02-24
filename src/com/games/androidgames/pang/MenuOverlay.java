package com.games.androidgames.pang;

import java.util.List;
import java.util.ArrayList;

import com.games.androidgames.pang.buttons.MenuButton;

public class MenuOverlay {
	private List<List<MenuButton>> menuList;
	private List<String> labels;
	public List<MenuButton> current;
	
	public MenuOverlay(String label, List<MenuButton> menu){
		this.current = menu;
		menuList = new ArrayList<List<MenuButton>>();
		labels = new ArrayList<String>();
		
		menuList.add(menu);
		labels.add(label);
	}
	
	public int numberOfMenus(){
		return menuList.size();
	}
	
	public void addMenu(String label, List<MenuButton> menu){
		menuList.add(menu);
		labels.add(label);
	}
	
	/**
	 * set Current Menu using label, if found return true else return false;
	 * @param label of menu wanted
	 * @return true if found and set
	 */
	public boolean setMenuByLabel(String label){
		for(int i = 0; i < menuList.size() && i < labels.size(); i++){
			if(this.labels.get(i).equals(label)){
				current = menuList.get(i);
				return true;
			}
		}
		return false;
	}
}
