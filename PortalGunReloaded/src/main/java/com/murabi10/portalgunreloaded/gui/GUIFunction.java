package com.murabi10.portalgunreloaded.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public interface GUIFunction {
	public abstract boolean click(Player p, ClickType type);
}
