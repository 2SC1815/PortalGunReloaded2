package com.murabi10.portalgunreloaded;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.murabi10.portalgunreloaded.portalevents.PlayerChamberGoalEvent;
import com.murabi10.portalgunreloaded.portalevents.PortalLandingEvent;
import com.murabi10.portalgunreloaded.portalevents.PortalThroughEvent;
import com.murabi10.portalgunreloaded.portalgun.PortalDevice;
import com.murabi10.portalgunreloaded.testingelement.objects.Cube;

public class EventListener implements org.bukkit.event.Listener {
	@EventHandler
	public void logout(PlayerQuitEvent e) {
		PortalDevice.Disable(e.getPlayer());
	}

	@EventHandler
	public void login(PlayerJoinEvent e) {
		PortalDevice.AuthPlayer(e.getPlayer());
	}

	@EventHandler
	public void onGoal(PlayerChamberGoalEvent e) {
	}

	@EventHandler
	public void onManipulate(PlayerArmorStandManipulateEvent e) {
		PortalDevice d = PortalDevice.getDeviceInstance(e.getPlayer());
		if ((d != null) && (d.getQueue() != null)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		if ((e.getRightClicked().getType().equals(EntityType.ARMOR_STAND)) ||
				(e.getRightClicked().getType().equals(EntityType.ITEM_FRAME))) {
			PortalDevice d = PortalDevice.getDeviceInstance(e.getPlayer());
			if ((d != null) && (d.getQueue() != null)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPortalTransport(PortalThroughEvent e) {
		if (e.getTo().getLaunchDirection().equals(BlockFace.DOWN)) {
			e.setDest(e.getDest().clone().add(0.0D, -1.0D, 0.0D));
		}

		boolean ignore = false;

		switch (e.getFrom().getLaunchDirection()) {
		case NORTH:
		case NORTH_EAST:
			break;
		default:
			ignore = true;
		}

		Double vel = null;

		if (e.getEntity().getType().equals(EntityType.PLAYER)) {
			vel = Double.valueOf(PortalDevice.getDeviceInstance(e.getEntity().getUniqueId()).getVelocity(ignore));
			e.setV(vel.doubleValue());
		} else if (e.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
			Cube cube = com.murabi10.portalgunreloaded.testingelement.objects.CubeManager
					.getCube(e.getEntity().getUniqueId());
			if (cube != null) {
				vel = Double.valueOf(cube.getVelocity(ignore));
				e.setV(vel.doubleValue());
			}
		}

		if (vel != null) {
			Location tmp = e.getDest().clone()
					.add(Methods.BlockFaceToVector(e.getTo().getLaunchDirection(), vel.doubleValue()));
			if (tmp.getBlock().isEmpty()) {
				e.setDest(tmp);
			}
		}
	}

	@EventHandler
	public void onPortalLanding(PortalLandingEvent e) {
		if (!Methods.isSuitable(e.getPortal(), true)) {
			ArrayList<Location> newjudgment = new ArrayList<Location>();

			switch (e.getPortal().getLaunchDirection()) {
			case DOWN:
			case EAST:
			case EAST_NORTH_EAST:
			case EAST_SOUTH_EAST:
			default:
				for (Location locs : e.getPortal().getLocations()) {
					newjudgment.add(locs.clone().add(0.0D, -1.0D, 0.0D));
				}
				e.getPortal().Replace(e.getPortal().getRepresentativeLocation().clone().add(0.0D, -1.0D, 0.0D),
						newjudgment);
				break;
			case NORTH:
			case NORTH_EAST:
				for (Location locs : e.getPortal().getLocations()) {
					newjudgment.add(locs.getBlock()
							.getRelative(e.getPortal().getSubstitutionDirection().getOppositeFace()).getLocation());
				}

				e.getPortal().Replace(e.getPortal().getRepresentativeLocation().getBlock()
						.getRelative(e.getPortal().getSubstitutionDirection().getOppositeFace()).getLocation(),
						newjudgment);
			}

			if (!Methods.isSuitable(e.getPortal(), true)) {
				e.setCancelled(true);
			}
		}
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\EventListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */