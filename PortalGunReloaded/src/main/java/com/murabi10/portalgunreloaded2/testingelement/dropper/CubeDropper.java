package com.murabi10.portalgunreloaded2.testingelement.dropper;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;

import com.murabi10.portalgunreloaded2.testingelement.ElementType;
import com.murabi10.portalgunreloaded2.testingelement.TestingElement;
import com.murabi10.portalgunreloaded2.testingelement.objects.Cube;
import com.murabi10.portalgunreloaded2.testingelement.objects.CubeType;

public class CubeDropper extends TestingElement {
	private boolean oldActivated = false;
	private transient Cube cube = null;
	private CubeType type;

	public CubeDropper(Location OriginLoc, BlockFace Dir, CubeType type, int x, int y, int z) {
		super(OriginLoc, ElementType.CUBE_DROPPER, com.murabi10.portalgunreloaded2.testingelement.LinkType.INPUT, Dir, x,
				y, z);
		this.type = type;
	}

	public boolean check() {
		return true;
	}

	protected void destroy() {
		if (this.cube != null)
			this.cube.Destroy(false);
		this.cube = null;
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

		if ((this.cube == null) || (this.cube.getMarker() == null)
				|| ((this.cube.getMarker() != null) && (this.cube.getMarker().isDead()))) {
			this.cube = new Cube(getRelative1(this.OriginLocation).clone().add(0.5D, -1.5D, 0.5D), this.type);
		}

		if ((isInput() != this.oldActivated) && (isInput())) {
			if (this.cube != null) {
				this.cube.Destroy(false);
			}
			this.cube = new Cube(getRelative1(this.OriginLocation).clone().add(0.5D, -1.5D, 0.5D), this.type);
		}

		this.oldActivated = isInput();
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\dropper\CubeDropper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */