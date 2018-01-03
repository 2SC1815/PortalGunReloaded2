package com.murabi10.portalgunreloaded.gui;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class GUI {
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private String GUIName = "menu";
	private int size = 1;

	public GUI() {
		nativeInit();
	}

	protected void nativeInit() {
		init();
		GUIManager.GUIs.put(this.GUIName, this);
	}

	protected void addbutton(Button button) {
		this.buttons.add(button);
	}

	public String getMenuName() {
		return this.GUIName;
	}

	protected void setMenuName(String name) {
		this.GUIName = name;
	}

	protected void setSize(int size) {
		this.size = size;
	}

	public abstract void init();

	public ArrayList<Button> getButtons() {
		return this.buttons;
	}

	public void openGUI(Player p) {
		Inventory UI = Bukkit.getServer().createInventory(null, 9 * this.size, this.GUIName);
		for (Button button : this.buttons) {
			UI.setItem(button.getLoc(), button.getIcon());
		}
		p.openInventory(UI);
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\gui\GUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */