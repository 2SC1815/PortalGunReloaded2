package com.murabi10.portalgunreloaded.portalgun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.murabi10.portalgunreloaded.Methods;
import com.murabi10.portalgunreloaded.PortalGun;
import com.murabi10.portalgunreloaded.portalevents.PortalDestroyEvent;
import com.murabi10.portalgunreloaded.portalevents.PortalLandingEvent;
import com.murabi10.portalgunreloaded.portalevents.PortalLaunchEvent;
import com.murabi10.portalgunreloaded.portalevents.PortalPreLaunchEvent;
import com.murabi10.portalgunreloaded.selector.ItemClickWait;
import com.murabi10.portalgunreloaded.selector.StringInputWait;
import com.murabi10.portalgunreloaded.testchamber.TestQueue;
import com.murabi10.portalgunreloaded.testingelement.objects.Cube;

public class PortalDevice {

	private static HashMap<UUID, PortalDevice> devices = new HashMap<UUID, PortalDevice>();

	public static PortalDevice AuthPlayer(Player p) {

		UUID uuid = p.getUniqueId();

		PortalDevice portaldevice = null;

		if (devices.containsKey(uuid)) {

			portaldevice = devices.get(uuid);

		} else {

			portaldevice = new PortalDevice(p);

			devices.put(uuid, portaldevice);

		}

		portaldevice.flush();

		return portaldevice;
	}

	public static PortalDevice getDeviceInstance(UUID uuid) {
		return devices.get(uuid);
	}

	public static PortalDevice getDeviceInstance(Player p) {
		return getDeviceInstance(p.getUniqueId());
	}

	public static boolean isAuthed(UUID uuid) {
		return devices.containsKey(uuid);
	}

	public static boolean isAuthed(Player p) {
		return isAuthed(p.getUniqueId());
	}

	public static void Disable(UUID uuid) {
		if (isAuthed(uuid)) {
			devices.get(uuid).clearPortal();
			devices.remove(uuid);
		}
	}

	public static ArrayList<PortalDevice> getDevices() {
		ArrayList<PortalDevice> devices_ = new ArrayList<PortalDevice>();
		for (UUID key : devices.keySet()) {
			devices_.add(devices.get(key));
		}
		return devices_;
	}

	public static void Disable(Player p) {
		Disable(p.getUniqueId());
	}


	private Vector[] vector = new Vector[12];

	private boolean portalInsideFlag = false;

	private Player owner;

	private Portal BluePortal = null;
	private Portal OrangePortal = null;

	private Cube hangingCube;

	private ItemStack itemInput = null;
	private boolean canItemInput = false;

	private String StringInput = "";
	private boolean canStringInput = false;

	private ItemClickWait itemwait = null;
	private StringInputWait strwait = null;

	private TestQueue queue = null;


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

	private PortalDevice(Player p) {
		this.owner = p;
	}

	public void LaunchPortal(PortalColor color) {

		if (hangingCube != null) {
			return;
		}

		PortalPreLaunchEvent prelaunch = new PortalPreLaunchEvent(this.owner, color);
		Bukkit.getServer().getPluginManager().callEvent(prelaunch);

		if (!prelaunch.isCancelled()) {

			Location eyeloc = owner.getEyeLocation();
			Vector dir = eyeloc.getDirection();

			boolean stop = false;

			// d("1");
			for (double count = 1; count <= PortalGun.portalLen + 1; count += 0.4) {
				// d("2");
				Location loc = eyeloc.clone().add(dir.clone().multiply(count));
				for (Material mat : PortalGun.PortalTransparentMaterials) {
					// d("3");
					if (loc.getBlock().getType().equals(mat)) {
						// d("4 color");
						if (color.equals(PortalColor.BLUE)) {
							loc.getWorld().spawnParticle(Particle.REDSTONE, loc.getX(), loc.getY(), loc.getZ(), 0,
									((double) 1 / 255), ((double) 64 / 255), ((double) 254 / 255), 1d);
						} else {
							loc.getWorld().spawnParticle(Particle.REDSTONE, loc.getX(), loc.getY(), loc.getZ(), 0,
									((double) 254 / 255), ((double) 180 / 255), ((double) 30 / 255), 1d);
						}
						break;
					} else {
						// stop = true;
						// break;
					}
				}
				if (stop) {
					break;
				}
			}

			boolean success = true; // false

			/*
			 * for (Block blk :
			 * owner.getLineOfSight(PortalGun.transparentMaterials,
			 * PortalGun.portalLen)) { if ( TODO
			 * flooredLocationArrayCompare(PortalGun.Grid(), blk.getLocation()))
			 * { success = false; break; } }
			 */

			List<Block> blocks = owner.getLastTwoTargetBlocks(PortalGun.PortalTransparentMaterials, PortalGun.portalLen);
			BlockFace launchDirection = null;

			if (blocks.size() != 1) {
				launchDirection = blocks.get(1).getFace(blocks.get(0));
			}
			if (launchDirection == null) {
				success = false;
			}

			PortalLaunchEvent launch = new PortalLaunchEvent(this.owner, color, success);
			Bukkit.getServer().getPluginManager().callEvent(launch);

			if (success) {

				Portal portal;

				switch (launchDirection) {
				case UP:
				case DOWN:
					portal = new Portal(this.owner, color, launchDirection,
							Methods.YawToBlockFace(this.owner.getEyeLocation().getYaw()), blocks.get(0).getLocation(),
							blocks.get(0).getLocation(),
							blocks.get(0).getLocation().getBlock()
									.getRelative(Methods.YawToBlockFace(this.owner.getEyeLocation().getYaw()))
									.getLocation());
					break;
				default:
					portal = new Portal(this.owner, color, launchDirection,
							Methods.YawToBlockFace(this.owner.getEyeLocation().getYaw()), blocks.get(0).getLocation(),
							blocks.get(0).getLocation());
					break;
				}

				PortalLandingEvent landing = new PortalLandingEvent(portal, blocks.get(1).getLocation());
				Bukkit.getServer().getPluginManager().callEvent(landing);

				if (!landing.isCancelled()) {

					if (color.equals(PortalColor.BLUE)) {

						this.DestroyPortal(color);

						this.BluePortal = portal;

						if (this.OrangePortal != null) {
							this.OrangePortal.setDest(portal);
							this.BluePortal.setDest(this.OrangePortal);
						}

					} else {

						this.DestroyPortal(color);

						this.OrangePortal = portal;

						if (this.BluePortal != null) {
							this.BluePortal.setDest(portal);
							this.OrangePortal.setDest(this.BluePortal);
						}

					}

				} else {
					portal.stopParticle();
					portal = null;
				}

				// public Portal(Player p, PortalColor c, BlockFace launchD,
				// BlockFace ApperanceD, Location exit, Location...
				// judgmentLocs) {

			}

		}

	}

	public void DestroyPortal(PortalColor color) {
		if (color.equals(PortalColor.BLUE)) {
			if (this.BluePortal != null) {
				if (this.OrangePortal != null) {
					this.OrangePortal.setDest(null);
				}

				this.BluePortal.stopParticle();
				this.BluePortal = null;
			}
		} else {
			if (this.OrangePortal != null) {
				if (this.BluePortal != null) {
					this.BluePortal.setDest(null);
				}

				this.OrangePortal.stopParticle();
				this.OrangePortal = null;
			}
		}

		PortalDestroyEvent destroy = new PortalDestroyEvent();
		Bukkit.getServer().getPluginManager().callEvent(destroy);
	}

	public void clearPortal() {
		DestroyPortal(PortalColor.BLUE);
		DestroyPortal(PortalColor.ORANGE);
	}

	public Portal getBluePortal() {
		return this.BluePortal;
	}

	public Portal getOrangePortal() {
		return this.OrangePortal;
	}

	public Player getOwner() {
		return this.owner;
	}

	public void setFlag(boolean flag) {
		this.portalInsideFlag = flag;
	}

	public boolean getFlag() {
		return this.portalInsideFlag;
	}

	public Cube getHangingCube() {
		return hangingCube;
	}

	public void setHangingCube(Cube hangingCube) {
		this.hangingCube = hangingCube;
	}


	public boolean canStringInput() {
		return canStringInput;
	}

	public void setStringInput(boolean canInput) {
		this.canStringInput = canInput;
	}

	public String getInputString() {
		return StringInput;
	}

	public void resetInputString() {
		this.StringInput = "";
	}

	public boolean isEmptyString() {
		return this.StringInput.equals("");
	}

	public void setInputString(String input) {
		this.StringInput = input;
	}


	public boolean canItemInput() {
		return canItemInput;
	}

	public void setItemInput(boolean canInput) {
		this.canItemInput = canInput;
	}

	public ItemStack getInputItem() {
		return itemInput;
	}

	public void resetInputItem() {
		this.itemInput = null;
	}

	public boolean isEmptyItem() {
		return this.itemInput == null;
	}

	public void setInputItem(ItemStack input) {
		this.itemInput = input;
	}

	public ItemClickWait getItemwait() {
		return itemwait;
	}

	public boolean hasItemwait() {
		return itemwait != null;
	}

	public void setItemwait(ItemClickWait itemwait) {
		this.itemwait = itemwait;
	}

	public StringInputWait getStrWait() {
		return strwait;
	}

	public boolean hasStrWait() {
		return strwait != null;
	}

	public void setStrWait(StringInputWait strwait) {
		this.strwait = strwait;
	}

	public TestQueue getQueue() {
		return queue;
	}

	public void setQueue(TestQueue queue) {
		this.queue = queue;
	}

}
