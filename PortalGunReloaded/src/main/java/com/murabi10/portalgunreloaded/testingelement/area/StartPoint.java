package com.murabi10.portalgunreloaded.testingelement.area;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;

import com.murabi10.portalgunreloaded.Methods;
import com.murabi10.portalgunreloaded.testingelement.ElementType;
import com.murabi10.portalgunreloaded.testingelement.LinkType;
import com.murabi10.portalgunreloaded.testingelement.TestingElement;

public class StartPoint extends TestingElement {

	public StartPoint(Location OriginLoc, int x, int y, int z) {
		super(OriginLoc, ElementType.START_POINT, LinkType.DNC, BlockFace.SOUTH, x, y, z);
	}

	@Override
	public boolean check() {
		Location loc = this.getRelative1(this.OriginLocation);
		return loc.getBlock().isEmpty() && loc.getBlock().getRelative(BlockFace.UP).isEmpty();
	}

	@Override
	protected void destroy() {

	}

	@Override
	protected void Run() {
		if (this.isEditMode()) {
			Location loc = Methods.LocationNormalize(this.getRelative1(this.OriginLocation));
			Methods.spawnParticle(loc, (byte)10, (byte)200, (byte)200);
			Methods.spawnParticle(loc.clone().add(0, 1, 0), (byte)10, (byte)200, (byte)200);
		}

	}

}
