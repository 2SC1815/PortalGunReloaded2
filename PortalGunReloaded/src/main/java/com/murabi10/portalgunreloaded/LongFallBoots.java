package com.murabi10.portalgunreloaded;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LongFallBoots implements Listener {
	public static ItemStack getBoots() {
		ItemStack item = new ItemStack(Material.IRON_BOOTS, 1, (short) 0);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("長距離落下ブーツ");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Aperture Science");
		lore.add("Long Fall Boots :");
		lore.add("落下ダメージを無効化する");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	@EventHandler
	public void onFall(EntityDamageEvent e) {
		if ((e.getCause().equals(EntityDamageEvent.DamageCause.FALL))
				&& (e.getEntity().getType().equals(EntityType.PLAYER))) {
			ItemStack boots = ((Player) e.getEntity()).getInventory().getBoots();
			if ((boots != null) && (boots.getType().equals(getBoots().getType())) && (boots.hasItemMeta())
					&& (boots.getItemMeta().hasLore())
					&& (boots.getItemMeta().getLore().equals(getBoots().getItemMeta().getLore()))) {
				e.setCancelled(true);
				boots.setDurability((short) 0);
			}
		}
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\LongFallBoots.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */