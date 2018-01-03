package com.murabi10.portalgunreloaded2.testingelement.objects;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.murabi10.portalgunreloaded2.Methods;
import com.murabi10.portalgunreloaded2.PortalGun;

public class Gel {
	private GelType type;
	private ArmorStand gel;
	private ArmorStand marker;
	private BukkitRunnable follow;
	private boolean portal;
	private Vector[] vector = new Vector[12];

	public Gel(Location loc, GelType type) {
		this.gel = ((ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND));
		this.marker = ((ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND));

		this.gel.setVisible(false);
		this.marker.setVisible(false);

		this.gel.setGravity(false);
		this.marker.setGravity(true);

		this.type = type;

		this.gel.setHelmet(new ItemStack(this.type.getmaterial(), 1));

		this.follow = new BukkitRunnable() {
			public void run() {
				if ((Gel.this.gel.isDead()) || (Gel.this.marker.isDead())) {
					Gel.this.Destroy();
				} else {
					Location loc = Gel.this.marker.getLocation().clone().add(0.0D, -1.3D, 0.0D);
					Gel.this.gel.teleport(loc);
				}
				if (Gel.this.marker.getLocation().getBlock().isLiquid()) {
					Gel.this.Destroy();

				}

			}

		};
		this.follow.runTaskTimer(PortalGun.getPlugin(), 1L, 2L);

		flush();

		GelManager.add(this);
	}

	public void tp(Location loc) {
		this.marker.teleport(loc);
		this.gel.teleport(loc);
	}

	public ArmorStand getMarker() {
		return this.marker;
	}

	public ArmorStand getGel() {
		return this.gel;
	}

	public GelType getGelType() {
		return this.type;
	}

	public void Destroy() {
		GelManager.remove(this);
		this.follow.cancel();
		this.follow = null;
		this.gel.remove();
		this.gel = null;
		this.marker.remove();
		this.marker = null;
	}

	public double getVelocity(boolean ignoreVertical) {
		double max = 0.0D;
		for (int i = 0; i < 12; i++) {
			double tmp = Methods.VectorToAcc(this.vector[i], ignoreVertical);
			if (tmp > max)
				max = tmp;
		}
		return max;
	}

	public void setVelocity(Vector Velocity) {
		for (int i = 1; i < 12; i++) {
			this.vector[i] = this.vector[(i - 1)];
		}
		this.vector[0] = Velocity;
	}

	private void flush() {
		for (int i = 0; i < 12; i++) {
			this.vector[i] = new Vector(0.0D, 0.0D, 0.0D);
		}
	}

	public boolean getFlag() {
		return this.portal;
	}

	public void setFlag(boolean portal) {
		this.portal = portal;
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\objects\Gel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */