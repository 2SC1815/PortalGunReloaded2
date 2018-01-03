package com.murabi10.portalgunreloaded.portalgun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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
			portaldevice = (PortalDevice) devices.get(uuid);
		} else {
			portaldevice = new PortalDevice(p);

			devices.put(uuid, portaldevice);
		}

		portaldevice.flush();

		return portaldevice;
	}

	public static PortalDevice getDeviceInstance(UUID uuid) {
		return (PortalDevice) devices.get(uuid);
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
			((PortalDevice) devices.get(uuid)).clearPortal();
			devices.remove(uuid);
		}
	}

	public static ArrayList<PortalDevice> getDevices() {
		ArrayList<PortalDevice> devices_ = new ArrayList<PortalDevice>();
		for (UUID key : devices.keySet()) {
			devices_.add((PortalDevice) devices.get(key));
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

	private PortalDevice(Player p) {
		this.owner = p;
	}

	public void LaunchPortal(PortalColor color) {
		if (this.hangingCube != null) {
			return;
		}

		PortalPreLaunchEvent prelaunch = new PortalPreLaunchEvent(this.owner, color);
		Bukkit.getServer().getPluginManager().callEvent(prelaunch);

		if (!prelaunch.isCancelled()) {
			Location eyeloc = this.owner.getEyeLocation();
			Vector dir = eyeloc.getDirection();

			boolean stop = false;

			for (double count = 1.0D; count <= 201.0D; count += 0.4D) {
				Location loc = eyeloc.clone().add(dir.clone().multiply(count));
				for (Material mat : PortalGun.PortalTransparentMaterials) {
					if (loc.getBlock().getType().equals(mat)) {
						if (color.equals(PortalColor.BLUE)) {
							loc.getWorld().spawnParticle(org.bukkit.Particle.REDSTONE, loc.getX(), loc.getY(),
									loc.getZ(), 0,
									0.00392156862745098D, 0.25098039215686274D, 0.996078431372549D, 1.0D);
							break;
						}
						loc.getWorld().spawnParticle(org.bukkit.Particle.REDSTONE, loc.getX(), loc.getY(), loc.getZ(),
								0,
								0.996078431372549D, 0.7058823529411765D, 0.11764705882352941D, 1.0D);

						break;
					}
				}

				if (stop) {
					break;
				}
			}

			boolean success = true;

			List<Block> blocks = this.owner.getLastTwoTargetBlocks(PortalGun.PortalTransparentMaterials,
					200);
			BlockFace launchDirection = null;

			if (blocks.size() != 1) {
				launchDirection = ((Block) blocks.get(1)).getFace((Block) blocks.get(0));
			}
			if (launchDirection == null) {
				success = false;
			}

			PortalLaunchEvent launch = new PortalLaunchEvent(this.owner, color, success);
			Bukkit.getServer().getPluginManager().callEvent(launch);

			if (success) {
				Portal portal;
				switch (launchDirection) {
				case NORTH:
				case NORTH_EAST:
					portal = new Portal(this.owner, color, launchDirection,
							Methods.YawToBlockFace(this.owner.getEyeLocation().getYaw()),
							((Block) blocks.get(0)).getLocation(), new Location[] {
									((Block) blocks.get(0)).getLocation(),
									((Block) blocks.get(0)).getLocation().getBlock()
											.getRelative(Methods.YawToBlockFace(this.owner.getEyeLocation().getYaw()))
											.getLocation() });
					break;
				default:
					portal = new Portal(this.owner, color, launchDirection,
							Methods.YawToBlockFace(this.owner.getEyeLocation().getYaw()),
							((Block) blocks.get(0)).getLocation(), new Location[] {
									((Block) blocks.get(0)).getLocation() });
				}

				PortalLandingEvent landing = new PortalLandingEvent(portal, ((Block) blocks.get(1)).getLocation());
				Bukkit.getServer().getPluginManager().callEvent(landing);

				if (!landing.isCancelled()) {
					if (color.equals(PortalColor.BLUE)) {
						DestroyPortal(color);

						this.BluePortal = portal;

						if (this.OrangePortal != null) {
							this.OrangePortal.setDest(portal);
							this.BluePortal.setDest(this.OrangePortal);
						}
					} else {
						DestroyPortal(color);

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
			}
		}
	}

	public void LaunchPortal(PortalColor color, Location loc, BlockFace dir, BlockFace dir2) {
		BlockFace launchDirection = dir;

		Portal portal;
		switch (launchDirection) {
		case NORTH:
		case NORTH_EAST:
			portal = new Portal(this.owner, color, launchDirection, dir2, loc, new Location[] { loc,
					loc.getBlock().getRelative(dir2).getLocation() });
			break;
		default:
			portal = new Portal(this.owner, color, launchDirection, dir2, loc, new Location[] { loc });
		}

		PortalLandingEvent landing = new PortalLandingEvent(portal, loc.getBlock().getRelative(dir).getLocation());
		Bukkit.getServer().getPluginManager().callEvent(landing);

		if (!landing.isCancelled()) {
			if (color.equals(PortalColor.BLUE)) {
				DestroyPortal(color);

				this.BluePortal = portal;

				if (this.OrangePortal != null) {
					this.OrangePortal.setDest(portal);
					this.BluePortal.setDest(this.OrangePortal);
				}
			} else {
				DestroyPortal(color);

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
		} else if (this.OrangePortal != null) {
			if (this.BluePortal != null) {
				this.BluePortal.setDest(null);
			}

			this.OrangePortal.stopParticle();
			this.OrangePortal = null;
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
		return this.hangingCube;
	}

	public void setHangingCube(Cube hangingCube) {
		this.hangingCube = hangingCube;
	}

	public boolean canStringInput() {
		return this.canStringInput;
	}

	public void setStringInput(boolean canInput) {
		this.canStringInput = canInput;
	}

	public String getInputString() {
		return this.StringInput;
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
		return this.canItemInput;
	}

	public void setItemInput(boolean canInput) {
		this.canItemInput = canInput;
	}

	public ItemStack getInputItem() {
		return this.itemInput;
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
		return this.itemwait;
	}

	public boolean hasItemwait() {
		return this.itemwait != null;
	}

	public void setItemwait(ItemClickWait itemwait) {
		this.itemwait = itemwait;
	}

	public StringInputWait getStrWait() {
		return this.strwait;
	}

	public boolean hasStrWait() {
		return this.strwait != null;
	}

	public void setStrWait(StringInputWait strwait) {
		this.strwait = strwait;
	}

	public TestQueue getQueue() {
		return this.queue;
	}

	public void setQueue(TestQueue queue) {
		this.queue = queue;
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalgun\PortalDevice.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */