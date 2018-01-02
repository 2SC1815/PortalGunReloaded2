package com.murabi10.portalgunreloaded;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import com.murabi10.portalgunreloaded.portalgun.Portal;
import com.murabi10.portalgunreloaded.portalgun.PortalDevice;

public class Methods {

	public static ArrayList<Entity> getEntity(Location loc) {
		ArrayList<Entity> entities = new ArrayList<Entity>();
		for (Entity ent : loc.getWorld().getNearbyEntities(loc, 2, 2, 2)) {
			if (LocationEquals(ent.getLocation(), loc)) {
				entities.add(ent);
			}
		}
		return entities;
	}

	public static boolean LocationEquals(Location loc1, Location loc2) {
		return (floor(loc1.getBlockX()) == floor(loc2.getBlockX())) &&
		(floor(loc1.getBlockY()) == floor(loc2.getBlockY())) &&
		(floor(loc1.getBlockZ()) == floor(loc2.getBlockZ()));
	}

	public static Location LocationFloor(Location loc) {
		Location loc_ = loc.clone();
		loc_.setYaw(0F);
		loc_.setPitch(0F);
		loc_.setX(floor(loc_.getBlockX()));
		loc_.setY(floor(loc_.getBlockY()));
		loc_.setZ(floor(loc_.getBlockZ()));
		return loc;
	}

	public static int floor(double d) {
		return (int) d;
	}

	public static Location BlockFaceFormat(BlockFace launchD, BlockFace bf, Location loc) {
		Location rtn = loc.clone();
		float yaw = 0;
		if (launchD != null) {
			if (!launchD.equals(BlockFace.DOWN) || !launchD.equals(BlockFace.UP)) {
				yaw = BlockFaceToYaw(launchD);
			} else {
				yaw = BlockFaceToYaw(bf);
			}
		}
		rtn.setYaw(yaw);
		return rtn;
	}

	private static final double PI = 3.141592653;
	private static final double sin0 = 0.02741213;
	private static final double cos0 = 0.99962421;

	public static Vector DirectionToVector(float dpitch, float dyaw) {
		double pitch = ((dpitch + 90) * PI) / 180;
		double yaw = ((dyaw + 90) * PI) / 180;
		return new Vector(Math.sin(pitch) * Math.cos(yaw),
				Math.sin(pitch) * Math.sin(yaw), Math.cos(pitch)).normalize();
	}

	public static Vector YawToVector(double yaw) {
		double dyaw = ((yaw + 90) * PI) / 180;
		return new Vector(sin0 * Math.cos(dyaw),
				sin0 * Math.sin(dyaw), cos0).normalize();

	}

	@SuppressWarnings("deprecation")
	public static boolean isSuitable(Portal portal, boolean overlapSearch) {

		ArrayList<Location> air = new ArrayList<Location>();
		ArrayList<Location> block = new ArrayList<Location>();

		switch (portal.getLaunchDirection()) {
		default:
		case EAST:
		case SOUTH:
		case WEST:
		case NORTH:
			if (overlapSearch && (getPortal(portal.getRepresentativeLocation()) != null || getPortal(portal.getRepresentativeLocation().clone().add(0, 1, 0)) != null)) {
				return false;
			}
			air.add(portal.getRepresentativeLocation());
			air.add(portal.getRepresentativeLocation().clone().add(0,1,0));
			Location Long = portal.getRepresentativeLocation().getBlock().getRelative(portal.getLaunchDirection().getOppositeFace()).getLocation();
			block.add(Long);
			block.add(Long.clone().add(0,1,0));
			break;
		case DOWN:
			for (Location loc : portal.getLocations()) {
				air.add(loc);
			}
			for (Location loc : portal.getLocations()) {
				block.add(loc.clone().add(0, 1, 0));
			}
			break;
		case UP:
			for (Location loc : portal.getLocations()) {
				air.add(loc);
			}
			for (Location loc : portal.getLocations()) {
				block.add(loc.clone().add(0, -1, 0));
			}
			break;
		}

		for (Location loc : air) {
			if (!loc.getBlock().getType().equals(Material.AIR) || (overlapSearch && getPortal(loc) != null)) {
				return false;
			}
		}

		boolean ok;

		for (Location loc : block) {
			ok = false;

			for (BlockData blk : PortalGun.PortalConductors) {
				if (loc.getBlock().getType().equals(blk.getMaterial()) && (blk.GetData() == -1 || blk.GetData() == loc.getBlock().getData()) ) {
					ok = true;
					break;
				}
			}

			if (!ok) {
				return false;
			}

		}

		return true;

	}

	public static BlockFace YawToBlockFace_(float Yaw) {
		BlockFace dir = null;
		float y = Yaw;
		if (y < 0) {
			y += 360;
		}
		y %= 360;
		int i = (int) ((y + 8) / 22.5);

		if (i <= 3 && i >= 6)
			dir = BlockFace.WEST;
		else if (i <= 7 && i >= 10)
			dir = BlockFace.NORTH;
		else if (i <= 11 && i >= 14)
			dir = BlockFace.EAST;
		else if ((i <= 0 && i >= 2)||i<=15)
			dir = BlockFace.SOUTH;
		else {
			dir = BlockFace.SOUTH;
			System.out.println("WOOOOOOOOOOOOOOOO!!!!");
		}

		return dir;
	}

	public static BlockFace YawToBlockFace(float Yaw) {
		BlockFace dir = null;
		float y = Yaw;
		if (y < 0) {
			y += 360;
		}
		y %= 360;
		int i = (int) ((y + 8) / 22.5);
		if (i == 0)
			dir = BlockFace.SOUTH;
		else if (i == 1)
			dir = BlockFace.SOUTH;
		else if (i == 2)
			dir = BlockFace.SOUTH;
		else if (i == 3)
			dir = BlockFace.WEST;
		else if (i == 4)
			dir = BlockFace.WEST;
		else if (i == 5)
			dir = BlockFace.WEST;
		else if (i == 6)
			dir = BlockFace.WEST;// west
		else if (i == 7)
			dir = BlockFace.NORTH;
		else if (i == 8)
			dir = BlockFace.NORTH;
		else if (i == 9)
			dir = BlockFace.NORTH;
		else if (i == 10)
			dir = BlockFace.NORTH;// north
		else if (i == 11)
			dir = BlockFace.EAST;
		else if (i == 12)
			dir = BlockFace.EAST;
		else if (i == 13)
			dir = BlockFace.EAST;
		else if (i == 14)
			dir = BlockFace.EAST;// east
		else if (i == 15)
			dir = BlockFace.SOUTH;// south
		else {
			dir = BlockFace.SOUTH;
		}
		return dir;
	}

	public static Location LocationNormalize(Location loc) {
		if (loc != null)
			return loc.add(0.5, 0, 0.5);
		return null;
	}

	public static Vector BlockFaceToVector(BlockFace DestFace, double acc) {
		Vector rtn = new Vector(0D, 0D, 0D);
			switch (DestFace) {
			case SOUTH:
				rtn.setZ(acc);
				break;
			case WEST:
				rtn.setX(-acc);
				break;
			case NORTH:
				rtn.setZ(-acc);
				break;
			case EAST:
				rtn.setX(acc);
				break;
			case UP:
				rtn.setY(acc);
				break;
			case DOWN:
				rtn.setY(-acc);
				break;
			default:
				break;
			}
		return rtn;
	}

	public static Portal getPortal(Location loc) {
		Portal rtn = null;
		for (PortalDevice gun : PortalDevice.getDevices()) {

			Portal BP = gun.getBluePortal();
			Portal OP = gun.getOrangePortal();

			if (BP != null) {
				for (Location locs : BP.getLocations()) {
					if (LocationEquals(locs, loc)) {
						rtn = BP;
					}
				}
			}

			if (OP != null) {
				for (Location locs : OP.getLocations()) {
					if (LocationEquals(locs, loc))
						rtn = OP;
				}

			}
		}

		return rtn;

	}

	public static Portal getPortal(Location loc, boolean SpecialSearch) {
		if (!SpecialSearch) {
			return getPortal(loc);
		} else {
			Portal p = getPortal(loc);
			if (p != null) {
				return p;
			} else {
				p = getPortal(loc.clone().add(0, 1, 0));
				if (p != null && p.getLaunchDirection().equals(BlockFace.DOWN)) {
					return p;
				} else {
					return null;
				}
			}
		}
	}

	public static double VectorToAcc(Vector dir, boolean ignoreVertical) {
			if (ignoreVertical) {

				return Math.sqrt(powTwo(dir.getX()) + powTwo(dir.getZ()));
			} else {
				return Math.sqrt(powTwo(dir.getX()) + powTwo(dir.getY())
				+ powTwo(dir.getZ()));
			}
	}

	private static double powTwo(double x) {
		return x*x;
	}

	public static void spawnParticle(Location loc, byte R, byte G, byte B) {

		loc.getWorld().spawnParticle(Particle.REDSTONE, loc.getX(), loc.getY(), loc.getZ(), 0,
				((double) R / 255), ((double) G / 255), ((double) B / 255), 1d);
	}

	public static boolean isTransparent(Location loc) {
		for (Material mat : PortalGun.transparentMaterials) {
			if (loc.getBlock().getType().equals(mat)) {
				return true;
			}
		}
		return false;
	}

	public static float BlockFaceToPitch(BlockFace bf) {
		if (bf != null) {
			switch (bf) {
			default:
				return 0f;
			case UP:
				return -90f;
			case DOWN:
				return 90f;
			}
		}
		return 0;
	}


	public static float BlockFaceToYaw(BlockFace bf) {
		if (bf != null) {
			switch (bf) {
			default:
			case SOUTH:
				return 0f;
			case WEST:
				return 90f;
			case NORTH:
				return 180f;
			case EAST:
				return -90f;
			}
		}
		return 0;
	}

	public static int getX(Location origin, Location target) {
		return target.getBlockX() - origin.getBlockX();
	}

	public static int getY(Location origin, Location target) {
		return target.getBlockY() - origin.getBlockY();
	}

	public static int getZ(Location origin, Location target) {
		return target.getBlockZ() - origin.getBlockZ();
	}


}
