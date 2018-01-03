package com.murabi10.portalgunreloaded2.testingelement.fixture;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

import com.murabi10.portalgunreloaded2.portalgun.PortalColor;
import com.murabi10.portalgunreloaded2.portalgun.PortalDevice;
import com.murabi10.portalgunreloaded2.testingelement.ElementType;
import com.murabi10.portalgunreloaded2.testingelement.TestingElement;

public class PortalSpawner extends TestingElement {
	private transient boolean oldActivated = true;
	transient int i = 0;
	private PortalColor color;
	private BlockFace dir2;

	public PortalSpawner(Location OriginLoc, BlockFace dir, BlockFace dir2, int x, int y, int z, PortalColor c) {
		super(OriginLoc, ElementType.PORTAL_SPAWNER, com.murabi10.portalgunreloaded2.testingelement.LinkType.INPUT, dir,
				x, y, z);
		this.dir2 = dir2;
		this.color = c;
	}

	public boolean check() {
		return true;
	}

	protected void destroy() {
		getRelative1(this.OriginLocation).getBlock().setType(Material.AIR);
	}

	@SuppressWarnings("deprecation")
	protected void Run() {
		if (this.i >= 3) {
			getRelative1(this.OriginLocation).getBlock().setType(Material.STONE);
			getRelative1(this.OriginLocation).getBlock().setData((byte) 4);

			setInput(true);
			if (Switches().size() != 0) {
				for (TestingElement e : Switches()) {
					if (!e.isOutput()) {
						setInput(false);
						break;
					}
				}
			}

			if ((isInput() != this.oldActivated) && (isInput())) {
				PortalDevice d = PortalDevice.getDeviceInstance(getTargetPlayer());
				d.LaunchPortal(this.color,
						getRelative1(this.OriginLocation).getBlock().getRelative(getDirection()).getLocation(),
						getDirection(), this.dir2);
			}

			this.oldActivated = isInput();
			this.i = 0;
		}
		this.i += 1;
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\fixture\PortalSpawner.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */