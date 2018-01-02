package com.murabi10.portalgunreloaded.portalgun;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.murabi10.portalgunreloaded.PortalGun;

public class Portal {

	private Player creator;
	private PortalColor color;
	private ArrayList<Location> judgmentLocs = new ArrayList<Location>();
	private BlockFace launchDirection;
	private BlockFace substitutionDirection;
	private Location representativeLoc;
	private Portal dest = null;
	private PortalFunctionRunnable particleRunnable;

	public Portal(Player p, PortalColor c,  BlockFace launchD, BlockFace substitutionD, Location exit, Location... judgmentLocs) {
		this.creator = p;
		this.color = c;
		for (int i=0; i<judgmentLocs.length; i++) {
			this.judgmentLocs.add(judgmentLocs[i]);
		}
		this.representativeLoc = exit;
		this.launchDirection = launchD;
		this.substitutionDirection = substitutionD;

		//TODO particle runnable

		this.particleRunnable = new PortalFunctionRunnable(this);
		this.particleRunnable.runTaskTimer(PortalGun.getPlugin(), 3, 1);

	}


	public void Replace(Location exit, ArrayList<Location> judgmentLocs) {
		this.stopParticle();

		this.judgmentLocs = judgmentLocs;
		this.representativeLoc = exit;

		this.particleRunnable = new PortalFunctionRunnable(this);
		this.particleRunnable.runTaskTimer(PortalGun.getPlugin(), 3, 1);
	}

	public void stopParticle() {
		this.particleRunnable.cancel();
		this.particleRunnable = null;
	}

	public Player getPlayer() {
		return this.creator;
	}

	public PortalColor getColor() {
		return this.color;
	}

	public ArrayList<Location> getLocations() {
		return this.judgmentLocs;
	}

	public BlockFace getLaunchDirection() {
		return this.launchDirection;
	}

	public BlockFace getSubstitutionDirection() {
		return this.substitutionDirection;
	}

	public Location getRepresentativeLocation() {
		return this.representativeLoc;
	}

	public Portal getDest() {
		return this.dest;
	}

	public void setDest(Portal dest) {
		this.dest = dest;
	}

	public boolean isClosed() {
		return this.dest == null;
	}

	public BukkitRunnable getParticleRunnable() {
		return this.particleRunnable;
	}
}
