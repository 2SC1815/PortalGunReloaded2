package com.murabi10.portalgunreloaded2.testingelement.fixture;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import com.murabi10.portalgunreloaded2.Cuboid;
import com.murabi10.portalgunreloaded2.testingelement.ElementType;
import com.murabi10.portalgunreloaded2.testingelement.LinkType;
import com.murabi10.portalgunreloaded2.testingelement.TestingElement;

public class Door extends TestingElement {
	private transient boolean oldActivated = true;

	public Door(Location OriginLoc, int x1, int y1, int z1, int x2, int y2, int z2) {
		super(OriginLoc, ElementType.DOOR, LinkType.INPUT, BlockFace.SOUTH, x1, y1, z1, x2, y2, z2);
	}

	public boolean check() {
		return true;
	}

	protected void destroy() {
		for (Block b : new Cuboid(getRelative1(this.OriginLocation), getRelative2(this.OriginLocation))) {
			b.setType(Material.AIR);
		}
		this.getRelative1(this.OriginLocation).getBlock().setType(Material.AIR);
	}

	private transient int i = 0;

	protected void Run() {
		if (this.i >= 5) {
			if (isEditMode()) {
				this.getRelative1(this.OriginLocation).getBlock().setType(Material.IRON_BLOCK);
			}
			setInput(true);
			if (Switches().size() != 0) {
				for (TestingElement e : Switches()) {
					if (!e.isOutput()) {
						setInput(false);
						break;
					}
				}
			}

			if (isInput() != this.oldActivated) {

				for (Block b : new Cuboid(getRelative1(this.OriginLocation), getRelative2(this.OriginLocation))) {
					this.OriginLocation.getWorld().playSound(b.getLocation(),
							isInput() ? Sound.BLOCK_PISTON_CONTRACT : Sound.BLOCK_PISTON_EXTEND, 0.9F,
							1.0F);
				}

				if (isEditMode()) {
					for (Block b : new Cuboid(getRelative1(this.OriginLocation), getRelative2(this.OriginLocation))) {
						if (isInput()) {
							b.setType(Material.AIR);
						} else {
							b.setType(Material.IRON_BLOCK);
						}
					}
				}
			}

			if (!isEditMode()) {
				for (Block b : new Cuboid(getRelative1(this.OriginLocation), getRelative2(this.OriginLocation))) {
					if (isInput()) {
						b.setType(Material.AIR);
					} else {
						b.setType(Material.IRON_BLOCK);
					}
				}
			}

			this.oldActivated = isInput();
			this.i = 0;
		}
		this.i += 1;
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\fixture\Door.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */