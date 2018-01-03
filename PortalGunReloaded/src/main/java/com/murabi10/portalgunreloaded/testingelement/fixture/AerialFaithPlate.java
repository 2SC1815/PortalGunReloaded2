package com.murabi10.portalgunreloaded.testingelement.fixture;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.murabi10.portalgunreloaded.testingelement.TestingElement;

public class AerialFaithPlate extends TestingElement {
	private float power;
	private float Yaw;
	private float Pitch;

	public float getPower() {
		return this.power;
	}

	public void setPower(float power) {
		this.power = power;
	}

	public float getYaw() {
		return this.Yaw;
	}

	public void setYaw(float yaw) {
		this.Yaw = yaw;
	}

	public float getPitch() {
		return this.Pitch;
	}

	public void setPitch(float pitch) {
		this.Pitch = pitch;
	}

	public AerialFaithPlate(Location OriginLoc, int x, int y, int z, float yaw, float pitch, int power) {
		super(OriginLoc, com.murabi10.portalgunreloaded.testingelement.ElementType.AERIAL_FAITH_PLATE,
				com.murabi10.portalgunreloaded.testingelement.LinkType.DNC, org.bukkit.block.BlockFace.SOUTH, x, y, z);
		this.Yaw = yaw;
		this.Pitch = pitch;
		this.power = power;
	}

	public boolean check() {
		return getRelative1(this.OriginLocation).getBlock().getType().equals(Material.PISTON_BASE);
	}

	protected void destroy() {
		getRelative1(this.OriginLocation).getBlock().setType(Material.AIR);
	}

	protected void Run() {
		for (Entity ent : this.OriginLocation.getWorld()
				.getNearbyEntities(getRelative1(this.OriginLocation).clone().add(0.0D, 1.0D, 0.0D), 2.0D, 2.0D, 2.0D)) {
			if ((com.murabi10.portalgunreloaded.Methods.LocationEquals(ent.getLocation(),
					getRelative1(this.OriginLocation).clone().add(0.0D, 1.0D, 0.0D)))
					&& ((!isEditMode())
							|| ((isEditMode()) && ((ent instanceof Player)) && (((Player) ent).isSneaking())))) {
				ent.setVelocity(com.murabi10.portalgunreloaded.Methods.DirectionToVector(this.Pitch, this.Yaw).clone()
						.multiply(this.power));
				this.OriginLocation.getWorld().playSound(getRelative1(this.OriginLocation),
						org.bukkit.Sound.ENTITY_GENERIC_EXPLODE, 1.2F, 2.0F);
			}
		}
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\fixture\AerialFaithPlate.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */