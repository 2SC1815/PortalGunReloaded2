package com.murabi10.portalgunreloaded.testingelement.dropper;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

import com.murabi10.portalgunreloaded.testingelement.ElementType;
import com.murabi10.portalgunreloaded.testingelement.LinkType;
import com.murabi10.portalgunreloaded.testingelement.TestingElement;
import com.murabi10.portalgunreloaded.testingelement.objects.Cube;
import com.murabi10.portalgunreloaded.testingelement.objects.CubeType;

public class CubeDropper extends TestingElement {

	private boolean oldActivated = false;
	private Cube cube = null;
	private CubeType type;

	public CubeDropper(Location OriginLoc, BlockFace Dir, CubeType type, int x, int y, int z) {
		super(OriginLoc, ElementType.CUBE_DROPPER, LinkType.INPUT, Dir, x, y, z);
		this.type = type;
	}

	@Override
	public boolean check() {
		return this.getRelative1(OriginLocation).getBlock().getType().equals(Material.DROPPER);
	}

	@Override
	protected void destroy() {

		this.getRelative1(OriginLocation).getBlock().setType(Material.AIR);

	}

	@Override
	protected void Run() {

		this.setActivated(true);

		if (this.Switches().size() != 0) {
			for (TestingElement e : this.Switches()) {
				if (!e.isActivated()) {
					this.setActivated(false);
					break;
				}
			}
		}

		if (cube == null || cube.getMarker().isDead()) {
			cube = new Cube(this.getRelative1(OriginLocation).clone().add(0, -1.1, 0), this.type);
		}

		if ((this.isActivated() != oldActivated) && this.isActivated()) {
			if (cube != null) {
				cube.Destroy(false);
			}
				cube = new Cube(this.getRelative1(OriginLocation).clone().add(0, -1.1, 0), this.type);

		}

		oldActivated = this.isActivated();
	}


}
