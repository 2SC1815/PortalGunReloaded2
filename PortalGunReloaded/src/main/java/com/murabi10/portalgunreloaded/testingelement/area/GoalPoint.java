package com.murabi10.portalgunreloaded.testingelement.area;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.murabi10.portalgunreloaded.Cuboid;
import com.murabi10.portalgunreloaded.Methods;
import com.murabi10.portalgunreloaded.portalevents.PlayerChamberGoalEvent;
import com.murabi10.portalgunreloaded.testingelement.ElementType;
import com.murabi10.portalgunreloaded.testingelement.TestingElement;

public class GoalPoint extends TestingElement {
	public GoalPoint(Location OriginLoc, int x1, int y1, int z1, int x2, int y2, int z2) {
		super(OriginLoc, ElementType.GOAL_POINT, com.murabi10.portalgunreloaded.testingelement.LinkType.DNC,
				BlockFace.SOUTH, x1, y1, z1, x2, y2, z2);
	}

	public boolean check() {
		return true;
	}

	private transient int i = 0;

	protected void destroy() {
	}

	protected void Run() {
		if (this.i >= 4) {
			for (Block b : new Cuboid(getRelative1(this.OriginLocation), getRelative2(this.OriginLocation))) {
				Location loc = b.getLocation();

				if (!isEditMode()) {
					for (Entity ent : loc.getWorld().getNearbyEntities(loc, 2.0D, 2.0D, 2.0D)) {
						if (((ent instanceof Player)) &&
								(Methods.LocationEquals(ent.getLocation(), loc))) {
							PlayerChamberGoalEvent event = new PlayerChamberGoalEvent(getTargetPlayer());
							org.bukkit.Bukkit.getServer().getPluginManager().callEvent(event);
						}
					}
				}

				Methods.spawnParticle(Methods.LocationNormalize(loc), (byte) -56, (byte) -56, (byte) 10);
			}

			this.i = 0;
		}
		this.i += 1;
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\area\GoalPoint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */