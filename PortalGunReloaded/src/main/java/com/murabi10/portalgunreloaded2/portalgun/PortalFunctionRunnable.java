package com.murabi10.portalgunreloaded2.portalgun;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.scheduler.BukkitRunnable;

import com.murabi10.portalgunreloaded2.Methods;

public class PortalFunctionRunnable
		extends BukkitRunnable {
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

	public void run() {
		this.phase += 1;
		if (this.phase >= 360) {
			this.phase = 0;
		}
		CreatePortalParticle(this.loc, this.AD, this.color, this.PlayerD, this.phase);

		if (!Methods.isSuitable(this.p, false)) {
			PortalDevice.getDeviceInstance(this.p.getPlayer()).DestroyPortal(this.p.getColor());
		}
	}

	private static void CreatePortalParticle(Location locate, BlockFace mode, PortalColor type, BlockFace bf,
			int phase) {
		Block block = locate.getBlock();
		Location loc = block.getRelative(mode.getOppositeFace()).getLocation();
		int i = phase;
		double x = 0.0D;
		double y = 0.0D;
		double z = 0.0D;
		double x2 = 0.0D;
		double y2 = 0.0D;
		double z2 = 0.0D;
		switch (mode) {
		case EAST_NORTH_EAST:
			x = Trigonometrics.cos(i) / 2.0D + 0.5D;
			y = Trigonometrics.sin(i) + 1.0D;
			z = 1.1D;

			x2 = Trigonometrics.cos2(i) / 2.0D + 0.5D;
			y2 = Trigonometrics.sin2(i) + 1.0D;
			z2 = 1.1D;
			break;
		case EAST_SOUTH_EAST:
			x = -0.1D;
			y = Trigonometrics.sin(i) + 1.0D;
			z = Trigonometrics.cos(i) / 2.0D + 0.5D;

			x2 = -0.1D;
			y2 = Trigonometrics.sin2(i) + 1.0D;
			z2 = Trigonometrics.cos2(i) / 2.0D + 0.5D;
			break;
		case DOWN:
			x = Trigonometrics.cos(i) / 2.0D + 0.5D;
			y = Trigonometrics.sin(i) + 1.0D;
			z = -0.1D;

			x2 = Trigonometrics.cos2(i) / 2.0D + 0.5D;
			y2 = Trigonometrics.sin2(i) + 1.0D;
			z2 = -0.1D;
			break;
		case EAST:
			x = 1.1D;
			y = Trigonometrics.sin(i) + 1.0D;
			z = Trigonometrics.cos(i) / 2.0D + 0.5D;

			x2 = 1.1D;
			y2 = Trigonometrics.sin2(i) + 1.0D;
			z2 = Trigonometrics.cos2(i) / 2.0D + 0.5D;
			break;
		case NORTH:
			if ((bf.equals(BlockFace.SOUTH)) || (bf.equals(BlockFace.NORTH))) {
				x = Trigonometrics.cos(i) / 2.0D + 0.5D;
				y = 1.1D;
				if (bf.equals(BlockFace.SOUTH)) {
					z = Trigonometrics.sin(i) + 1.0D;
				} else
					z = Trigonometrics.sin(i);
			} else {
				if (bf.equals(BlockFace.EAST)) {
					x = Trigonometrics.sin(i) + 1.0D;
				} else
					x = Trigonometrics.sin(i);
				y = 1.1D;
				z = Trigonometrics.cos(i) / 2.0D + 0.5D;
			}

			if ((bf.equals(BlockFace.SOUTH)) || (bf.equals(BlockFace.NORTH))) {
				x2 = Trigonometrics.cos2(i) / 2.0D + 0.5D;
				y2 = 1.1D;
				if (bf.equals(BlockFace.SOUTH)) {
					z2 = Trigonometrics.sin2(i) + 1.0D;
				} else
					z2 = Trigonometrics.sin2(i);
			} else {
				if (bf.equals(BlockFace.EAST)) {
					x2 = Trigonometrics.sin2(i) + 1.0D;
				} else
					x2 = Trigonometrics.sin2(i);
				y2 = 1.1D;
				z2 = Trigonometrics.cos2(i) / 2.0D + 0.5D;
			}
			break;
		case NORTH_EAST:
			if ((bf.equals(BlockFace.SOUTH)) || (bf.equals(BlockFace.NORTH))) {
				x = Trigonometrics.cos(i) / 2.0D + 0.5D;
				y = -0.1D;
				if (bf.equals(BlockFace.SOUTH)) {
					z = Trigonometrics.sin(i) + 1.0D;
				} else
					z = Trigonometrics.sin(i);
			} else {
				if (bf.equals(BlockFace.EAST)) {
					x = Trigonometrics.sin(i) + 1.0D;
				} else
					x = Trigonometrics.sin(i);
				y = -0.1D;
				z = Trigonometrics.cos(i) / 2.0D + 0.5D;
			}

			if ((bf.equals(BlockFace.SOUTH)) || (bf.equals(BlockFace.NORTH))) {
				x2 = Trigonometrics.cos2(i) / 2.0D + 0.5D;
				y2 = -0.1D;
				if (bf.equals(BlockFace.SOUTH)) {
					z2 = Trigonometrics.sin2(i) + 1.0D;
				} else
					z2 = Trigonometrics.sin2(i);
			} else {
				if (bf.equals(BlockFace.EAST)) {
					x2 = Trigonometrics.sin2(i) + 1.0D;
				} else
					x2 = Trigonometrics.sin2(i);
				y2 = -0.1D;
				z2 = Trigonometrics.cos2(i) / 2.0D + 0.5D;
			}
			break;

		default:
			break;
		}

		if (type.equals(PortalColor.BLUE)) {
			block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x, loc.getY() + y, loc.getZ() + z, 0,
					0.00392156862745098D, 0.25098039215686274D, 0.996078431372549D, 1.0D);

			block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x2, loc.getY() + y2, loc.getZ() + z2, 0,
					0.00392156862745098D, 0.25098039215686274D, 0.996078431372549D, 1.0D);

			block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x, loc.getY() + y, loc.getZ() + z, 0,
					0.00392156862745098D, 0.25098039215686274D, 0.996078431372549D, 1.0D);

			block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x2, loc.getY() + y2, loc.getZ() + z2, 0,
					0.00392156862745098D, 0.25098039215686274D, 0.996078431372549D, 1.0D);
		} else {
			block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x, loc.getY() + y, loc.getZ() + z, 0,
					0.996078431372549D, 0.7058823529411765D, 0.11764705882352941D, 1.0D);

			block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x2, loc.getY() + y2, loc.getZ() + z2, 0,
					0.996078431372549D, 0.7058823529411765D, 0.11764705882352941D, 1.0D);

			block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x, loc.getY() + y, loc.getZ() + z, 0,
					0.996078431372549D, 0.7058823529411765D, 0.11764705882352941D, 1.0D);

			block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x2, loc.getY() + y2, loc.getZ() + z2, 0,
					0.996078431372549D, 0.7058823529411765D, 0.11764705882352941D, 1.0D);
		}
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalgun\PortalFunctionRunnable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */