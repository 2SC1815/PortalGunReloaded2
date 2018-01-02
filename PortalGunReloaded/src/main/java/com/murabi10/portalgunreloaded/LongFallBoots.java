package com.murabi10.portalgunreloaded;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LongFallBoots implements Listener {


	public static ItemStack getBoots() {
		ItemStack item = new ItemStack(Material.IRON_BOOTS, 1, (short) 0);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Ĺ��Υ��֡���");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Aperture Science");
		lore.add("Long Fall Boots :");
		lore.add("����᡼����̵��������");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	@EventHandler
	public void onFall(EntityDamageEvent e) {
		if (e.getCause().equals(DamageCause.FALL) && e.getEntity().getType().equals(EntityType.PLAYER)) {
			ItemStack boots = ((Player)e.getEntity()).getInventory().getBoots();
			if (boots != null && boots.getType().equals(getBoots().getType()) && boots.hasItemMeta() && boots.getItemMeta().hasLore() && boots.getItemMeta().getLore().equals(getBoots().getItemMeta().getLore())) {
				e.setCancelled(true);
				boots.setDurability((short)0);
			}
		}
	}

}
