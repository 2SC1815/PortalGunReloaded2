package com.murabi10.portalgunreloaded.testingelement.field;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import com.murabi10.portalgunreloaded.Cuboid;
import com.murabi10.portalgunreloaded.Methods;
import com.murabi10.portalgunreloaded.testingelement.ElementType;
import com.murabi10.portalgunreloaded.testingelement.LinkType;
import com.murabi10.portalgunreloaded.testingelement.TestingElement;

public class LaserField extends TestingElement {
	public LaserField(Location OriginLoc, int x1, int y1, int z1, int x2, int y2, int z2) {
		super(OriginLoc, ElementType.LASER_FIELD, LinkType.INPUT, BlockFace.SOUTH, x1, y1, z1, x2, y2, z2);
	}

	public boolean check() {
		return true;
	}

	private transient int i = 0;
	transient Random rnd = null;

	protected void destroy() {
	}

	protected void Run() {
		if (this.i >= 2) {

			getRelative1(this.OriginLocation).getBlock().setType(isEditMode() ? Material.IRON_BLOCK : Material.AIR);

			setInput(true);
			if (Switches().size() != 0) {
				for (TestingElement e : Switches()) {
					if (!e.isOutput()) {
						setInput(false);
						break;
					}
				}
			}

			if (isInput()) {
				if (this.rnd == null) {
					this.rnd = new Random();
				}

				for (Block b : new Cuboid(getRelative1(this.OriginLocation), getRelative2(this.OriginLocation))) {
					Location loc = b.getLocation();

					for (Entity ent : loc.getWorld().getNearbyEntities(loc, 2.0D, 2.0D, 2.0D)) {
						if (Methods.LocationEquals(ent.getLocation(), loc)) {
							if ((!ent.getType().equals(EntityType.ARMOR_STAND)) && ((ent instanceof LivingEntity))
									&& ((!ent.getType().equals(EntityType.PLAYER)) || (!isEditMode()))) {

								((LivingEntity) ent).damage(1000.0D);
							}
						}
					}

					Methods.spawnParticle(
							loc.clone().add(this.rnd.nextDouble(), this.rnd.nextDouble(), this.rnd.nextDouble()),
							(byte) -2, (byte) 45, (byte) 45);
				}
			}

			this.i = 0;
		}
		this.i += 1;
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\field\LaserField.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */