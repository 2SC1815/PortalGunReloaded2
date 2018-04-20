package com.murabi10.portalgunreloaded2.testingelement.fixture;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.inventory.ItemStack;

import com.murabi10.portalgunreloaded2.PortalGun;
import com.murabi10.portalgunreloaded2.testingelement.ElementType;
import com.murabi10.portalgunreloaded2.testingelement.TestingElement;

public class Indicator extends TestingElement {
	private transient ItemFrame frame = null;

	public Indicator(Location OriginLoc, BlockFace Dir, int x, int y, int z) {
		super(OriginLoc, ElementType.ITEM_FRAME, com.murabi10.portalgunreloaded2.testingelement.LinkType.INPUT, Dir, x,
				y, z);
	}

	public boolean check() {
		return (getRelative1(this.OriginLocation).getBlock().getType().isSolid()) &&
				(getRelative1(this.OriginLocation).getBlock().getRelative(getDirection()).isEmpty());
	}

	protected void destroy() {
		if (this.frame != null) {
			this.frame.setItem(new ItemStack(Material.AIR));
			this.frame.remove();
			this.frame = null;
		}
	}

	private transient int i = 0;

	protected void Run() {
		if (this.i >= 5) {
			if (this.frame == null) {
				this.frame = ((ItemFrame) this.OriginLocation.getWorld().spawnEntity(
						getRelative1(this.OriginLocation).getBlock().getRelative(getDirection()).getLocation(),
						EntityType.ITEM_FRAME));
				this.frame.setFacingDirection(getDirection());
				ItemStack i = new ItemStack(Material.MAP);
				i.setDurability(isInput() ? PortalGun.ON_INDICATOR_MAP_ID : PortalGun.OFF_INDICATOR_MAP_ID);

				if (i != null) {
					if (this.frame.getItem().getDurability() != i.getDurability())
					this.frame.setItem(i);
				}
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

			ItemStack item = new ItemStack(Material.MAP);
			item.setDurability(isInput() ? PortalGun.ON_INDICATOR_MAP_ID : PortalGun.OFF_INDICATOR_MAP_ID);

			if (this.frame.getItem().getDurability() != item.getDurability())
			this.frame.setItem(item);

			this.i = 0;
		}
		this.i += 1;
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\fixture\Indicator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */