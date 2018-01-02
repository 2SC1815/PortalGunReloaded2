package com.murabi10.portalgunreloaded.testingelement.fixture;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import com.murabi10.portalgunreloaded.Cuboid;
import com.murabi10.portalgunreloaded.testingelement.ElementType;
import com.murabi10.portalgunreloaded.testingelement.LinkType;
import com.murabi10.portalgunreloaded.testingelement.TestingElement;

public class Door extends TestingElement {

	private boolean oldActivated = false;

	public Door(Location OriginLoc, int x1, int y1, int z1, int x2, int y2, int z2) {
		super(OriginLoc, ElementType.DOOR, LinkType.INPUT, BlockFace.SOUTH, x1, y1, z1, x2, y2, z2);
	}

	@Override
	public boolean check() {
		return true;
	}

	@Override
	protected void destroy() {


		for (Block b : new Cuboid(this.getRelative1(OriginLocation), this.getRelative2(OriginLocation))) {
			b.setType(Material.AIR);
		}

	}

	@Override
	protected void Run() {

		this.setActivated(true);
		for (TestingElement e : this.Switches()) {
			if (!e.isActivated()) {
				this.setActivated(false);
				break;
			}
		}

		if (this.isActivated() != oldActivated) {
			this.OriginLocation.getWorld().playSound(this.getTargetPlayer().getLocation(),
					this.isActivated() ? Sound.BLOCK_PISTON_CONTRACT : Sound.BLOCK_PISTON_EXTEND, 2.0f,
					1.0f);


			for (Block b : new Cuboid(this.getRelative1(OriginLocation), this.getRelative2(OriginLocation))) {

						if (this.isActivated()) {
							b.setType(Material.AIR);
						} else {
							b.setType(Material.IRON_BLOCK);
						}


						this.OriginLocation.getWorld().playSound(b.getLocation(),
								this.isActivated() ? Sound.BLOCK_PISTON_CONTRACT : Sound.BLOCK_PISTON_EXTEND, 2.0f,
								1.0f);

			}


		}
		oldActivated = this.isActivated();
	}

}
