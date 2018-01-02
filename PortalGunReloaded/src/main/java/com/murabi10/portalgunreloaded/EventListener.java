package com.murabi10.portalgunreloaded;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.murabi10.portalgunreloaded.portalevents.PlayerChamberGoalEvent;
import com.murabi10.portalgunreloaded.portalevents.PortalLandingEvent;
import com.murabi10.portalgunreloaded.portalevents.PortalThroughEvent;
import com.murabi10.portalgunreloaded.portalgun.PortalDevice;
import com.murabi10.portalgunreloaded.testingelement.objects.Cube;
import com.murabi10.portalgunreloaded.testingelement.objects.CubeManager;

public class EventListener implements Listener {

	@EventHandler
	public void logout(PlayerQuitEvent e) {
		PortalDevice.Disable(e.getPlayer());
	}

	@EventHandler
	public void login(PlayerJoinEvent e) {
		PortalDevice.AuthPlayer(e.getPlayer());
		//e.getPlayer().sendMessage("OK");
	}

	/*public void t(PlayerCommandPreprocessEvent e) {
		String message = "プレイヤー " + e.getPlayer().getName() + " は、 コマンド: " + e.getMessage() + " を実行しました。";

		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.hasPermission("testplugin.receive") || p.isOp()) {
				p.sendMessage(message);
			}
		}
	}*/

	@EventHandler
	public void onGoal(PlayerChamberGoalEvent e) {
		//e.getPlayer().teleport(e.getPlayer().getBedSpawnLocation());
	}


	@EventHandler
	public void onPortalTransport(PortalThroughEvent e) {

		if (e.getTo().getLaunchDirection().equals(BlockFace.DOWN)) {
			e.setDest(e.getDest().clone().add(0, -1, 0));
		}


		boolean ignore = false;

		switch (e.getFrom().getLaunchDirection()) {
		case UP:
		case DOWN:
			break;
		default:
			ignore = true;
		}

		Double vel = null;

		if (e.getEntity().getType().equals(EntityType.PLAYER)) {
			vel = PortalDevice.getDeviceInstance(e.getEntity().getUniqueId()).getVelocity(ignore);
			e.setV(vel);
		} else if (e.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
			Cube cube = CubeManager.getCube(e.getEntity().getUniqueId());
			if (cube != null) {
				vel = cube.getVelocity(ignore);
				e.setV(vel);
			}
		}

		if (vel != null) {
			Location tmp = e.getDest().clone().add(Methods.BlockFaceToVector(e.getTo().getLaunchDirection(), vel));
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
			default:
			case EAST:
			case SOUTH:
			case WEST:
			case NORTH:
				for (Location locs : e.getPortal().getLocations()) {
					newjudgment.add(locs.clone().add(0, -1, 0));
				}
				e.getPortal().Replace(e.getPortal().getRepresentativeLocation().clone().add(0, -1, 0), newjudgment);
				break;
			case UP:
			case DOWN:
				for (Location locs : e.getPortal().getLocations()) {
					newjudgment.add(locs.getBlock()
							.getRelative(e.getPortal().getSubstitutionDirection().getOppositeFace()).getLocation());
				}
				e.getPortal()
						.Replace(e.getPortal().getRepresentativeLocation().getBlock()
								.getRelative(e.getPortal().getSubstitutionDirection().getOppositeFace()).getLocation(),
								newjudgment);
				break;
			}

			if (!Methods.isSuitable(e.getPortal(), true)) {
				e.setCancelled(true);
			}
		}
	}

	/*private void d(String m) {
		System.out.println(m);
	}*/

}
