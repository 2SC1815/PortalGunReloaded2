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

// 1500_Megawatt_Aperture_Science_Heavy_Duty_Super-Colliding_Super_Button

public class SuperCollidingSuperButton extends TestingElement {

	public SuperCollidingSuperButton(Location OriginLoc, BlockFace Dir, int x, int y, int z) {
		super(OriginLoc, ElementType.BUTTON, LinkType.OUTPUT, Dir, x, y, z);
	}

	private boolean oldActivated = false;

	@Override
	public boolean check() {

		return true;
	}

	@Override
	protected void destroy() {
		this.getRelative1(this.OriginLocation).getBlock().setType(Material.AIR);

	}

	@SuppressWarnings("deprecation")
	@Override
	protected void Run() {
		if (this.isEditMode()) {
			Block b = this.getRelative1(this.OriginLocation).getBlock();
			if (!b.getType().equals(Material.BONE_BLOCK)) {
				b.setType(Material.BONE_BLOCK);
				switch (this.getDirection()) {
				default:
				case DOWN:
				case UP:
					b.setData((byte) 0);
					break;
				case SOUTH:
				case NORTH:
					b.setData((byte) 8);
					break;
				case WEST:
				case EAST:
					b.setData((byte) 4);
					break;
				}

			}
		}

		this.setActivated(false);
		Location judge = this.getRelative1(this.OriginLocation).getBlock().getRelative(getDirection()).getLocation();
		for (Entity ent : judge.getWorld().getNearbyEntities(judge, 2, 2, 2)) {
			if ((ent instanceof LivingEntity && Methods.LocationEquals(judge, ent.getLocation()))
					|| (CubeManager.getCube(ent.getUniqueId()) != null && Methods.LocationEquals(judge, ent.getLocation()))) {
				this.setActivated(true);
				break;
			}
		}

		if (oldActivated != this.isActivated()) {
			//System.out.println(this.isActivated());
			this.OriginLocation.getWorld()
					.playSound(this.getRelative1(this.OriginLocation), this.isActivated()
							? Sound.BLOCK_STONE_PRESSUREPLATE_CLICK_ON : Sound.BLOCK_STONE_PRESSUREPLATE_CLICK_OFF,
							1.5f, 0.6f);
		}

		oldActivated = this.isActivated();

	}

}
