package com.murabi10.portalgunreloaded2.testingelement.area;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;

import com.murabi10.portalgunreloaded2.Methods;
import com.murabi10.portalgunreloaded2.testingelement.ElementType;
import com.murabi10.portalgunreloaded2.testingelement.TestingElement;

public class StartPoint extends TestingElement {
	public StartPoint(Location OriginLoc, int x, int y, int z) {
		super(OriginLoc, ElementType.START_POINT, com.murabi10.portalgunreloaded2.testingelement.LinkType.DNC,
				BlockFace.SOUTH, x, y, z);
	}

	public boolean check() {
		Location loc = getRelative1(this.OriginLocation);
		return (loc.getBlock().isEmpty()) && (loc.getBlock().getRelative(BlockFace.UP).isEmpty());
	}

	protected void destroy() {
	}

	protected void Run() {
		if (isEditMode()) {
			Location loc = Methods.LocationNormalize(getRelative1(this.OriginLocation));
			Methods.spawnParticle(loc, (byte) 10, (byte) -56, (byte) -56);
			Methods.spawnParticle(loc.clone().add(0.0D, 1.0D, 0.0D), (byte) 10, (byte) -56, (byte) -56);
		}
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\area\StartPoint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */