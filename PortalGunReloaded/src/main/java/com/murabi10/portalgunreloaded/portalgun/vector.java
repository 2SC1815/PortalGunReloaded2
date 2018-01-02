package com.murabi10.portalgunreloaded.portalgun;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.murabi10.portalgunreloaded.testingelement.objects.Cube;
import com.murabi10.portalgunreloaded.testingelement.objects.CubeManager;

public class vector extends BukkitRunnable {

	@Override
	public void run() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			// if
			// (PortalGun.Propulsion().contains(p.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation()))
			// {
			// Portal.setVelocity(p.getName(),
			// p.getVelocity().clone().multiply(14));
			// } else {
			PortalDevice.getDeviceInstance(p).setVelocity(p.getVelocity());
			// }
		}
		for (Cube c : CubeManager.getCubes()) {
			c.setVelocity(c.getMarker().getVelocity());
		}
	}

}
