package com.murabi10.portalgunreloaded.portalgun;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.murabi10.portalgunreloaded.Methods;
import com.murabi10.portalgunreloaded.portalevents.PortalThroughEvent;

public class PortalTransportRunnable extends BukkitRunnable {

	public PortalTransportRunnable() {
	}

	@Override
	public void run() {

		for (Player p : Bukkit.getOnlinePlayers()) {
			// d(p.toString());
			PortalDevice instance = PortalDevice.getDeviceInstance(p);
			Portal portal = Methods.getPortal(p.getLocation(), true);

			if (portal != null) {
				// d(portal.getRepresentativeLocation().toString());
				Portal dest = portal.getDest();
				if (dest != null && instance.getFlag() == false) {
					// d(dest.getRepresentativeLocation().toString());
					boolean isUPDOWN = (portal.getLaunchDirection().equals(BlockFace.UP)
							|| portal.getLaunchDirection().equals(BlockFace.DOWN));
					if ((isUPDOWN || (!isUPDOWN
							&& portal.getLaunchDirection().equals(Methods.YawToBlockFace(p.getEyeLocation().getYaw()).getOppositeFace())))) {
						// ポータルが上か下向きなら問答無用で入れるが、横向きならプレイヤーがその向きに向いている必要がある。
						// しかし、またやってしまったねぇ アライさん。見にくい条件文になってるねぇ

						Location loc = dest.getRepresentativeLocation();
						loc = Methods.BlockFaceFormat(dest.getLaunchDirection(), dest.getSubstitutionDirection(), loc);
						loc = Methods.LocationNormalize(loc);
						PortalThroughEvent event = new PortalThroughEvent(p, portal, dest, loc);
						Bukkit.getServer().getPluginManager().callEvent(event);

						p.teleport(event.getDest());

						p.setVelocity(Methods.BlockFaceToVector(dest.getLaunchDirection(), event.getV()));

						instance.setFlag(true);
					}
				}
			} else {
				instance.setFlag(false);
			}
		}

	}


}
