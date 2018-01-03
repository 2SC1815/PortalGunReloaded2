package com.murabi10.portalgunreloaded.selector;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import com.murabi10.portalgunreloaded.portalgun.PortalDevice;

public class ItemClickManager implements org.bukkit.event.Listener {
	@EventHandler
	public void onClickInv(InventoryClickEvent e) {
		ItemStack item = e.getCurrentItem();
		if (item != null) {
			PortalDevice d = PortalDevice.getDeviceInstance(e.getWhoClicked().getUniqueId());

			if ((d != null) && (d.canItemInput())) {
				if ((item.hasItemMeta()) && (item.getItemMeta().hasDisplayName()) && (item.getItemMeta().hasLore())) {
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

		if ((d != null) && (d.canItemInput())) {
			d.setItemInput(false);
		}
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\selector\ItemClickManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */