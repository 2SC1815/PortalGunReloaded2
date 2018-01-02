package com.murabi10.portalgunreloaded.testingelement.area;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.murabi10.portalgunreloaded.Methods;
import com.murabi10.portalgunreloaded.portalevents.PlayerChamberGoalEvent;
import com.murabi10.portalgunreloaded.testingelement.ElementType;
import com.murabi10.portalgunreloaded.testingelement.LinkType;
import com.murabi10.portalgunreloaded.testingelement.TestingElement;

public class GoalPoint extends TestingElement {

	public GoalPoint(Location OriginLoc, int x1, int y1, int z1, int x2, int y2,
			int z2) {
		super(OriginLoc, ElementType.GOAL_POINT, LinkType.DNC, BlockFace.SOUTH, x1, y1, z1, x2, y2, z2);
	}

	@Override
	public boolean check() {
		return true;
	}

	@Override
	protected void destroy() {

	}

	int i = 0;

	@Override
	protected void Run() {
		if (i >= 4) {

			int minx = Math.min(getRelative1(this.OriginLocation).getBlockX(),
					getRelative2(this.OriginLocation).getBlockX()),
					miny = Math.min(getRelative1(this.OriginLocation).getBlockY(),
							getRelative2(this.OriginLocation).getBlockY()),
					minz = Math.min(getRelative1(this.OriginLocation).getBlockZ(),
							getRelative2(this.OriginLocation).getBlockZ()),
					maxx = Math.max(getRelative1(this.OriginLocation).getBlockX(),
							getRelative2(this.OriginLocation).getBlockX()),
					maxy = Math.max(getRelative1(this.OriginLocation).getBlockY(),
							getRelative2(this.OriginLocation).getBlockY()),
					maxz = Math.max(getRelative1(this.OriginLocation).getBlockZ(),
							getRelative2(this.OriginLocation).getBlockZ());

			for (int x = minx; x < maxx; x++) {
				for (int y = miny; y < maxy; y++) {
					for (int z = minz; z < maxz; z++) {
						Location loc = new Location(this.OriginLocation.getWorld(), x, y, z);

						if (!this.isEditMode()) {

							for (Entity ent : loc.getWorld().getNearbyEntities(loc, 2, 2, 2)) {
								if (ent instanceof Player) {
									if (Methods.LocationEquals(ent.getLocation(), loc)) {
										PlayerChamberGoalEvent event = new PlayerChamberGoalEvent(
												this.getTargetPlayer());
										Bukkit.getServer().getPluginManager().callEvent(event);
									}
								}
							}

						}

						Methods.spawnParticle(Methods.LocationNormalize(loc), (byte)200, (byte)200, (byte)10);

					}
				}
			}
			i = 0;
		}
		i++;

	}

}
