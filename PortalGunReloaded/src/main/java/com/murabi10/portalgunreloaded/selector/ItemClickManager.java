package com.murabi10.portalgunreloaded.selector;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import com.murabi10.portalgunreloaded.portalgun.PortalDevice;

public class ItemClickManager implements Listener {

	@EventHandler
	public void onClickInv(InventoryClickEvent e) {
		ItemStack item = e.getCurrentItem();
		if (item!=null) {

			PortalDevice d = PortalDevice.getDeviceInstance(e.getWhoClicked().getUniqueId());

			if (d != null && d.canItemInput()) {
				if (item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().hasLore()) {
					d.setInputItem(item);
					d.setItemInput(false);
				}
				e.setCancelled(true);
			}

		}

	}

	@EventHandler
	public void onCloseInv(InventoryCloseEvent e) {
		PortalDevice d = PortalDevice.getDeviceInstance(e.getPlayer().getUniqueId());

		if (d != null && d.canItemInput()) {
			d.setItemInput(false);
		}
	}

}
