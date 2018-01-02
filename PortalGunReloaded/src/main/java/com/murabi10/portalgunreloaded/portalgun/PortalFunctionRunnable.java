package com.murabi10.portalgunreloaded.portalgun;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.scheduler.BukkitRunnable;

import com.murabi10.portalgunreloaded.Methods;

public class PortalFunctionRunnable extends BukkitRunnable {


	private int phase = 0;
	private PortalColor color;
	private Location loc;
	private BlockFace AD;
	private BlockFace PlayerD;
	private Portal p;


	public PortalFunctionRunnable(Portal portal) {
		this.p = portal;
		this.color = portal.getColor();
		this.loc = portal.getRepresentativeLocation();
		this.AD = portal.getLaunchDirection();
		this.PlayerD = portal.getSubstitutionDirection();
	}

	@Override
	public void run() {

		if (true) {
			this.phase++;
			if (this.phase>=360)
				this.phase = 0;

			CreatePortalParticle(this.loc, this.AD, this.color, this.PlayerD, phase);

		}

		if (!Methods.isSuitable(this.p, false)) {
			PortalDevice.getDeviceInstance(this.p.getPlayer()).DestroyPortal(this.p.getColor());
		}
	}



	private static void CreatePortalParticle(Location locate, BlockFace mode, PortalColor type, BlockFace bf, int phase) {
		Block block = locate.getBlock();
		Location loc = block.getRelative(mode.getOppositeFace()).getLocation();
		int i = phase;
		double x = 0, y = 0, z = 0;
		double x2 = 0, y2 = 0, z2 = 0;
			switch (mode) {
			case SOUTH:
				x = (Trigonometrics.cos(i) / 2) + 0.5;
				y = Trigonometrics.sin(i) + 1;
				z = 1.1;

				x2 = (Trigonometrics.cos2(i) / 2) + 0.5;
				y2 = Trigonometrics.sin2(i) + 1;
				z2 = 1.1;
				break;
			case WEST:
				x = -0.1;
				y = Trigonometrics.sin(i) + 1;
				z = (Trigonometrics.cos(i) / 2) + 0.5;

				x2 = -0.1;
				y2 = Trigonometrics.sin2(i) + 1;
				z2 = (Trigonometrics.cos2(i) / 2) + 0.5;
				break;
			case NORTH:
				x = (Trigonometrics.cos(i) / 2) + 0.5;
				y = Trigonometrics.sin(i) + 1;
				z = -0.1;

				x2 = (Trigonometrics.cos2(i) / 2) + 0.5;
				y2 = Trigonometrics.sin2(i) + 1;
				z2 = -0.1;
				break;
			case EAST:
				x = 1.1;
				y = Trigonometrics.sin(i) + 1;
				z = (Trigonometrics.cos(i) / 2) + 0.5;

				x2 = 1.1;
				y2 = Trigonometrics.sin2(i) + 1;
				z2 = (Trigonometrics.cos2(i) / 2) + 0.5;
				break;
			case UP:
				if (bf.equals(BlockFace.SOUTH) || bf.equals(BlockFace.NORTH)) {
					x = (Trigonometrics.cos(i) / 2) + 0.5;
					y = 1.1;
					if (bf.equals(BlockFace.SOUTH))
						z = Trigonometrics.sin(i) + 1;
					else
						z = Trigonometrics.sin(i);
				} else {
					if (bf.equals(BlockFace.EAST))
						x = Trigonometrics.sin(i) + 1;
					else
						x = Trigonometrics.sin(i);
					y = 1.1;
					z = (Trigonometrics.cos(i) / 2) + 0.5;
				}

				if (bf.equals(BlockFace.SOUTH) || bf.equals(BlockFace.NORTH)) {
					x2 = (Trigonometrics.cos2(i) / 2) + 0.5;
					y2 = 1.1;
					if (bf.equals(BlockFace.SOUTH))
						z2 = Trigonometrics.sin2(i) + 1;
					else
						z2 = Trigonometrics.sin2(i);
				} else {
					if (bf.equals(BlockFace.EAST))
						x2 = Trigonometrics.sin2(i) + 1;
					else
						x2 = Trigonometrics.sin2(i);
					y2 = 1.1;
					z2 = (Trigonometrics.cos2(i) / 2) + 0.5;
				}
				break;
			case DOWN:
				if (bf.equals(BlockFace.SOUTH) || bf.equals(BlockFace.NORTH)) {
					x = (Trigonometrics.cos(i) / 2) + 0.5;
					y = -0.1;
					if (bf.equals(BlockFace.SOUTH))
						z = Trigonometrics.sin(i) + 1;
					else
						z = Trigonometrics.sin(i);
				} else {
					if (bf.equals(BlockFace.EAST))
						x = Trigonometrics.sin(i) + 1;
					else
						x = Trigonometrics.sin(i);
					y = -0.1;
					z = (Trigonometrics.cos(i) / 2) + 0.5;
				}

				if (bf.equals(BlockFace.SOUTH) || bf.equals(BlockFace.NORTH)) {
					x2 = (Trigonometrics.cos2(i) / 2) + 0.5;
					y2 = -0.1;
					if (bf.equals(BlockFace.SOUTH))
						z2 = Trigonometrics.sin2(i) + 1;
					else
						z2 = Trigonometrics.sin2(i);
				} else {
					if (bf.equals(BlockFace.EAST))
						x2 = Trigonometrics.sin2(i) + 1;
					else
						x2 = Trigonometrics.sin2(i);
					y2 = -0.1;
					z2 = (Trigonometrics.cos2(i) / 2) + 0.5;
				}
				break;
			default:

				break;

			}

			if (type.equals(PortalColor.BLUE)) {
				block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x, loc.getY() + y, loc.getZ() + z, 0,
						((double) 1 / 255), ((double) 64 / 255), ((double) 254 / 255), 1d);

				block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x2, loc.getY() + y2, loc.getZ() + z2, 0,
						((double) 1 / 255), ((double) 64 / 255), ((double) 254 / 255), 1d);


				block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x, loc.getY() + y, loc.getZ() + z, 0,
						((double) 1 / 255), ((double) 64 / 255), ((double) 254 / 255), 1d);

				block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x2, loc.getY() + y2, loc.getZ() + z2, 0,
						((double) 1 / 255), ((double) 64 / 255), ((double) 254 / 255), 1d);
			} else {

				block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x, loc.getY() + y, loc.getZ() + z, 0,
						((double) 254 / 255), ((double) 180 / 255), ((double) 30 / 255), 1d);

				block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x2, loc.getY() + y2, loc.getZ() + z2, 0,
						((double) 254 / 255), ((double) 180 / 255), ((double) 30 / 255), 1d);

				block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x, loc.getY() + y, loc.getZ() + z, 0,
						((double) 254 / 255), ((double) 180 / 255), ((double) 30 / 255), 1d);

				block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x2, loc.getY() + y2, loc.getZ() + z2, 0,
						((double) 254 / 255), ((double) 180 / 255), ((double) 30 / 255), 1d);
			}

	}

}
