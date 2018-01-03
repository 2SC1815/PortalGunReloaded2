package com.murabi10.portalgunreloaded2.testingelement.dropper;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;

import com.murabi10.portalgunreloaded2.testingelement.ElementType;
import com.murabi10.portalgunreloaded2.testingelement.TestingElement;
import com.murabi10.portalgunreloaded2.testingelement.objects.Gel;
import com.murabi10.portalgunreloaded2.testingelement.objects.GelType;

public class GelDropper extends TestingElement {
	private transient Gel Gel = null;
	private GelType type;

	public GelDropper(Location OriginLoc, BlockFace Dir, GelType type, int x, int y, int z) {
		super(OriginLoc, ElementType.GEL_DROPPER, com.murabi10.portalgunreloaded2.testingelement.LinkType.INPUT, Dir, x,
				y, z);
		this.type = type;
	}

	public boolean check() {
		return true;
	}

	protected void destroy() {
		if (this.Gel != null)
			this.Gel.Destroy();
		this.Gel = null;
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

		if ((isInput()) && ((this.Gel == null) || (this.Gel.getMarker().isDead()))) {
			this.Gel = new Gel(getRelative1(this.OriginLocation).clone().add(0.5D, -1.5D, 0.5D), this.type);
		}
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\dropper\GelDropper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */