package com.murabi10.portalgunreloaded2.testingelement.objects;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.murabi10.portalgunreloaded2.Methods;
import com.murabi10.portalgunreloaded2.PortalGun;
import com.murabi10.portalgunreloaded2.portalevents.CubeDestroyEvent;
import com.murabi10.portalgunreloaded2.portalgun.PortalDevice;

public class Cube {
	private CubeType type;
	private ArmorStand cube;
	private ArmorStand marker;
	private BukkitRunnable follow;
	private Player hangedBy;
	private boolean portal;
	private Vector[] vector = new Vector[12];

	public Cube(Location loc, CubeType type) {
		this.cube = ((ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND));
		this.marker = ((ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND));

		this.cube.setVisible(false);
		this.marker.setVisible(false);

		this.cube.setGravity(false);
		this.marker.setGravity(true);

		this.type = type;

		this.cube.setHelmet(new ItemStack(this.type.getmaterial(), 1));

		this.follow = new BukkitRunnable() {
			public void run() {
				if ((Cube.this.cube.isDead()) || (Cube.this.marker.isDead())) {
					Cube.this.Destroy(true);
				} else {
					Location loc = Cube.this.marker.getLocation().clone().add(0.0D, -1.3D, 0.0D);
					Cube.this.cube.teleport(loc);
				}
				if (Cube.this.marker.getLocation().getBlock().isLiquid()) {
					Cube.this.Destroy(true);
				}

			}

		};
		this.follow.runTaskTimer(PortalGun.getPlugin(), 1L, 2L);

		flush();

		CubeManager.add(this);
	}

	public void tp(Location loc) {
		this.marker.teleport(loc);
		this.cube.teleport(loc);
	}

	public ArmorStand getMarker() {
		return this.marker;
	}

	public ArmorStand getCube() {
		return this.cube;
	}

	public CubeType getCubeType() {
		return this.type;
	}

	public void Destroy(boolean notify) {
		if (notify) {
			CubeDestroyEvent event = new CubeDestroyEvent(this);
			org.bukkit.Bukkit.getServer().getPluginManager().callEvent(event);
		}
		if (getMarker() != null) {
			Location explode = getMarker().getLocation();
			explode.getWorld().spawnParticle(org.bukkit.Particle.SMOKE_NORMAL, explode, 20);
			explode = null;
		}
		for (PortalDevice d : PortalDevice.getDevices()) {
			Cube c = d.getHangingCube();
			if ((c != null) && (c.equals(this))) {
				d.setHangingCube(null);
			}
		}
		CubeManager.remove(this);
		this.follow.cancel();
		this.follow = null;
		this.cube.remove();
		this.cube = null;
		this.marker.remove();
		this.marker = null;
		this.hangedBy = null;
	}

	public Player getHanging() {
		return this.hangedBy;
	}

	public void setHanging(Player p) {
		this.hangedBy = p;
		if (p != null) {
			this.marker.setGravity(false);
		} else {
			this.marker.setGravity(true);
		}
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

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\objects\Cube.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */