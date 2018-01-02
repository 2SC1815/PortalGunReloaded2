package com.murabi10.portalgunreloaded.gui;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIManager implements Listener {


	public static HashMap<String, GUI> GUIs = new HashMap<String, GUI>();

	@EventHandler
	public void onClickInv(InventoryClickEvent e) {
		String name = e.getInventory().getName();
		if (GUIs.containsKey(name)) {
			for (String key : GUIs.keySet()) {
				GUI gui = GUIs.get(key);
				for (Button button : gui.getButtons()) {
					if (e.getCurrentItem() != null && button != null && button.getIcon() != null && button.getIcon().equals(e.getCurrentItem())) {
						boolean c = button.runFunction((Player) e.getWhoClicked(), e.getClick());
						e.setCancelled(true);
						if (c) {
							e.getWhoClicked().closeInventory();
						}
					}
				}
			}
		}
	}
}
