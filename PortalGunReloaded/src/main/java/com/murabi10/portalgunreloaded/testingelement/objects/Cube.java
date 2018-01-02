package com.murabi10.portalgunreloaded.testingelement.objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.murabi10.portalgunreloaded.Methods;
import com.murabi10.portalgunreloaded.PortalGun;
import com.murabi10.portalgunreloaded.portalevents.CubeDestroyEvent;
import com.murabi10.portalgunreloaded.portalgun.PortalDevice;

public class Cube {

	private CubeType type;
	private ArmorStand cube;
	private ArmorStand marker;
	private BukkitRunnable follow;
	private Player hangedBy;
	private boolean portal;

	private Vector[] vector = new Vector[12];


	public Cube(Location loc, CubeType type) {

		this.cube = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		this.marker = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);

		this.cube.setVisible(false);
		this.marker.setVisible(false);

		this.cube.setGravity(false);
		this.marker.setGravity(true);

		this.type = type;

		this.cube.setHelmet(new ItemStack(this.type.getmaterial(), 1));

		this.follow = new BukkitRunnable() {

			@Override
			public void run() {
				if (cube.isDead() || marker.isDead()) {
					Destroy(true);
				} else {
					Location loc = marker.getLocation().clone().add(0, -1.3, 0);
					cube.teleport(loc);
				}
			}

		};

		this.follow.runTaskTimer(PortalGun.getPlugin(), 1, 2);

		this.flush();

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
			Bukkit.getServer().getPluginManager().callEvent(event);
		}
		Location explode = this.getMarker().getLocation();
		explode.getWorld().spawnParticle(Particle.SMOKE_NORMAL, explode, 20);
		explode = null;
		for (PortalDevice d : PortalDevice.getDevices()) {
			Cube c = d.getHangingCube();
			if (c != null && c.equals(this)) {
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
		return hangedBy;
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
		double max = 0.0;
		for (int i = 0; i < 12; i++) {
				double tmp = Methods.VectorToAcc(vector[i], ignoreVertical);
				if (tmp > max)
					max = tmp;
		}
		return max;
	}

	public void setVelocity(Vector Velocity) {

		for (int i = 1; i < 12; i++) {
			vector[i] = vector[i - 1];
		}
		vector[0] = Velocity;
	}

	private void flush() {
			for (int i = 0; i < 12; i++) {
				vector[i] = new Vector(0.0, 0.0, 0.0);
			}
	}

	public boolean getFlag() {
		return portal;
	}

	public void setFlag(boolean portal) {
		this.portal = portal;
	}

}
