package com.murabi10.portalgunreloaded2.testingelement.beam;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;

import com.murabi10.portalgunreloaded2.Methods;
import com.murabi10.portalgunreloaded2.portalgun.Portal;
import com.murabi10.portalgunreloaded2.testingelement.ElementType;
import com.murabi10.portalgunreloaded2.testingelement.TestingElement;

public abstract class Beam extends TestingElement {
	private Location Origin;
	private BlockFace OriginFace;

	protected Beam(Location ChamberOrigin, ElementType type, BlockFace face, int X, int Y, int Z) {
		super(ChamberOrigin, type, com.murabi10.portalgunreloaded2.testingelement.LinkType.INPUT, face, X, Y, Z);
	}

	protected void Run() {
		recursiveLaser(this.Origin, this.OriginFace);
	}

	private void recursiveLaser(Location loc, BlockFace bf) {
		for (double i = 0.0D; i < 70.0D; i += 0.2D) {
			Location currentLoc = loc.getBlock().getRelative(bf, (int) i).getLocation();

			Portal portal = Methods.getPortal(currentLoc, true);

			if ((portal != null) && (portal.getDest() != null)) {
				recursiveLaser(portal.getDest().getRepresentativeLocation(), portal.getDest().getLaunchDirection());
				break;
			}

			BlockFace rtn = laser(currentLoc, bf);
			if (rtn == null) {
				return;
			}
			if (!rtn.equals(bf)) {
				recursiveLaser(currentLoc, rtn);
				break;
			}
		}
	}

	public abstract BlockFace laser(Location paramLocation, BlockFace paramBlockFace);
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\beam\Beam.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */