package com.murabi10.portalgunreloaded.portalgun;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import com.murabi10.portalgunreloaded.Methods;
import com.murabi10.portalgunreloaded.portalevents.PortalThroughEvent;
import com.murabi10.portalgunreloaded.testingelement.objects.Cube;
import com.murabi10.portalgunreloaded.testingelement.objects.CubeManager;

public class CubeTransportRunnable extends BukkitRunnable {

	public CubeTransportRunnable() {
	}

	@Override
	public void run() {

		for (Cube c : CubeManager.getCubes()) {
			// d(p.toString());
			if (c.getHanging() == null) {
				Portal portal = Methods.getPortal(c.getMarker().getLocation(), true);

				if (portal != null) {
					// d(portal.getRepresentativeLocation().toString());
					Portal dest = portal.getDest();
					if (dest != null && !c.getFlag()) {

						Location loc = dest.getRepresentativeLocation();
						loc = Methods.BlockFaceFormat(dest.getLaunchDirection(), dest.getSubstitutionDirection(), loc);
						loc = Methods.LocationNormalize(loc);
						PortalThroughEvent event = new PortalThroughEvent(c.getMarker(), portal, dest, loc);
						Bukkit.getServer().getPluginManager().callEvent(event);

						c.tp(event.getDest());

						c.getMarker().setVelocity(Methods.BlockFaceToVector(dest.getLaunchDirection(), event.getV()));

						c.setFlag(true);
					}
				} else {
					c.setFlag(false);
				}
			}
		}

	}

}
