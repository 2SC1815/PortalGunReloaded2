package com.murabi10.portalgunreloaded.gui;


import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Button {

	private ItemStack icon;
	private int  Location;
	private GUIFunction func;


	public Button(Material material, int stack, short dataValue, String Name, int iconLocation, GUIFunction function, String... Lore) {
		this.func = function;
		this.Location = iconLocation;
		ItemStack item = new ItemStack(material, stack, (short) dataValue);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Name);
		ArrayList<String> lore = new ArrayList<String>();
		for(int i=0; i<Lore.length; i++) {
			lore.add(ChatColor.RESET + Lore[i]);
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		this.icon = item;
	}

	public boolean runFunction(Player p, ClickType type) {
		return func.click(p, type);
	}

	public int getLoc() {
		return this.Location;
	}

	public ItemStack getIcon() {
		return this.icon;
	}

}