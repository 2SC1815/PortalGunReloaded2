package com.murabi10.portalgunreloaded.portalgun;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import com.murabi10.portalgunreloaded.devices.Device;
import com.murabi10.portalgunreloaded.devices.DeviceManager;
import com.murabi10.portalgunreloaded.testingelement.objects.Cube;
import com.murabi10.portalgunreloaded.testingelement.objects.CubeManager;

public class HangingManager implements Listener {
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		ItemStack item = e.getItemDrop().getItemStack();
		Player p = e.getPlayer();
		if ((item != null) && (item.hasItemMeta())) {
			ItemMeta meta = item.getItemMeta();
			if (meta.hasLore()) {
				List<String> lore = meta.getLore();
				Material material = item.getType();
				short data = item.getDurability();
				for (Device device : DeviceManager.devices) {
					ItemStack d = device.getItem();
					if ((d.getType().equals(material)) && (d.getDurability() == data) &&
							(d.getItemMeta().getLore().equals(lore))) {
						e.setCancelled(true);

						PortalDevice gun = PortalDevice.getDeviceInstance(p);

						if (gun.getHangingCube() != null) {
							gun.getHangingCube().setHanging(null);
							gun.setHangingCube(null);
						} else {
							Location eyeloc = e.getPlayer().getEyeLocation();
							Vector dir = eyeloc.getDirection();

							for (double count = 1.0D; count <= 4.0D; count += 0.4D) {
								Location loc = eyeloc.clone().add(dir.clone().multiply(count));

								if (!loc.getBlock().getType().isSolid()) {
									for (Entity ent : loc.getWorld().getNearbyEntities(loc, 2.0D, 2.0D, 2.0D)) {
										if (ent.getType().equals(EntityType.ARMOR_STAND)) {
											Cube cube = CubeManager.getCube(ent.getUniqueId());
											if (cube != null) {
												cube.setHanging(p);
												gun.setHangingCube(cube);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Cube cube = PortalDevice.getDeviceInstance(p).getHangingCube();
		if (cube != null) {
			Location eyeloc = p.getEyeLocation();
			Vector v = eyeloc.getDirection();
			Location rtn = eyeloc;
			for (double count = 1.0D; count <= 3.0D; count += 0.4D) {
				Location loc = eyeloc.clone().add(v.clone().multiply(count));
				if (loc.getBlock().getType().isSolid()) {
					break;
				}
				rtn = loc;
			}

			rtn.setPitch(0.0F);
			rtn.setYaw(eyeloc.getYaw());

			cube.getMarker().teleport(rtn);
		}
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalgun\HangingManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */