package com.murabi10.portalgunreloaded2.portalgun;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import com.murabi10.portalgunreloaded2.Methods;
import com.murabi10.portalgunreloaded2.portalevents.PortalThroughEvent;
import com.murabi10.portalgunreloaded2.testingelement.objects.Gel;
import com.murabi10.portalgunreloaded2.testingelement.objects.GelManager;

public class GelTransportRunnable
		extends BukkitRunnable {
	public void run() {
		for (Gel c : GelManager.getGels()) {
			Portal portal = Methods.getPortal(c.getMarker().getLocation(), true);

			if (portal != null) {
				Portal dest = portal.getDest();
				if ((dest != null) && (!c.getFlag())) {
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

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalgun\GelTransportRunnable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */