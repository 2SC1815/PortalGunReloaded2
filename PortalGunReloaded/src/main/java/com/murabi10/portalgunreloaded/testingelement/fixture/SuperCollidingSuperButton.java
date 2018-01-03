package com.murabi10.portalgunreloaded.testingelement.fixture;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import com.murabi10.portalgunreloaded.Methods;
import com.murabi10.portalgunreloaded.testingelement.ElementType;
import com.murabi10.portalgunreloaded.testingelement.LinkType;
import com.murabi10.portalgunreloaded.testingelement.TestingElement;
import com.murabi10.portalgunreloaded.testingelement.objects.CubeManager;

public class SuperCollidingSuperButton
		extends TestingElement {
	public SuperCollidingSuperButton(Location OriginLoc, BlockFace Dir, int x, int y, int z) {
		super(OriginLoc, ElementType.BUTTON, LinkType.OUTPUT, Dir, x, y, z);
	}

	private boolean oldActivated = false;

	public boolean check() {
		return true;
	}

	protected void destroy() {
	}

	@SuppressWarnings("deprecation")
	protected void Run() {
		if (isEditMode()) {
			Block b = getRelative1(this.OriginLocation).getBlock();
			if (!b.getType().equals(Material.BONE_BLOCK)) {
				b.setType(Material.BONE_BLOCK);
				switch (getDirection()) {
				case NORTH:
				case NORTH_EAST:
				default:
					b.setData((byte) 0);
					break;
				case DOWN:
				case EAST_NORTH_EAST:
					b.setData((byte) 8);
					break;
				case EAST:
				case EAST_SOUTH_EAST:
					b.setData((byte) 4);
				}

			}
		}

		setOutput(false);
		Location judge = getRelative1(this.OriginLocation).getBlock().getRelative(getDirection()).getLocation();
		for (Entity ent : judge.getWorld().getNearbyEntities(judge, 2.0D, 2.0D, 2.0D)) {
			if ((((ent instanceof LivingEntity)) && (Methods.LocationEquals(judge, ent.getLocation())))
					|| ((CubeManager.getCube(ent.getUniqueId()) != null)
							&& (Methods.LocationEquals(judge, ent.getLocation())))) {
				setOutput(true);
				break;
			}
		}

		if (this.oldActivated != isOutput()) {

			this.OriginLocation.getWorld().playSound(getRelative1(this.OriginLocation),
					isOutput() ? Sound.BLOCK_STONE_PRESSUREPLATE_CLICK_ON : Sound.BLOCK_STONE_PRESSUREPLATE_CLICK_OFF,
					1.0F, 0.3F);
		}

		this.oldActivated = isOutput();
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\fixture\SuperCollidingSuperButton.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */