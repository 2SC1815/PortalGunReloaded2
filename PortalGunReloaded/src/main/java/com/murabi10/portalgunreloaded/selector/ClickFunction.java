package com.murabi10.portalgunreloaded.selector;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public interface ClickFunction {
	public abstract boolean click(Player p, ItemStack item);
}
