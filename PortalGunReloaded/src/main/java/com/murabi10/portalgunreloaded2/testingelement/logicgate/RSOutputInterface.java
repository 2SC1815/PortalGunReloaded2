package com.murabi10.portalgunreloaded2.testingelement.logicgate;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

import com.murabi10.portalgunreloaded2.testingelement.ElementType;
import com.murabi10.portalgunreloaded2.testingelement.TestingElement;

public class RSOutputInterface extends TestingElement {
	public RSOutputInterface(Location OriginLoc, int x, int y, int z) {
		super(OriginLoc, ElementType.RSOUTPUT, com.murabi10.portalgunreloaded2.testingelement.LinkType.INPUT,
				BlockFace.SOUTH, x, y, z);
	}

	public boolean check() {
		return true;
	}

	protected void destroy() {
	}

	protected void Run() {
		setInput(true);
		if (Switches().size() != 0) {
			for (TestingElement e : Switches()) {
				if (!e.isOutput()) {
					setInput(false);
					break;
				}
			}
		}

		getRelative1(this.OriginLocation).getBlock().setType(isInput() ? Material.REDSTONE_BLOCK : Material.IRON_BLOCK);
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\logicgate\RSOutputInterface.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */