package com.murabi10.portalgunreloaded2.testingelement.logicgate;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

import com.murabi10.portalgunreloaded2.testingelement.ElementType;
import com.murabi10.portalgunreloaded2.testingelement.TestingElement;

public class OR extends TestingElement {
	public OR(Location OriginLoc, int x, int y, int z) {
		super(OriginLoc, ElementType.OR, com.murabi10.portalgunreloaded2.testingelement.LinkType.OUT_IN,
				BlockFace.SOUTH, x, y, z);
	}

	public boolean check() {
		return false;
	}

	protected void destroy() {
	}

	protected void Run() {
		getRelative1(this.OriginLocation).getBlock()
				.setType(isEditMode() ? Material.GOLD_BLOCK : Material.AIR);

		setInput(false);
		if (Switches().size() != 0) {
			for (TestingElement e : Switches()) {
				if (e.isOutput()) {
					setInput(true);
					break;
				}
			}
		}

		setOutput(isInput());
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\logicgate\OR.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */