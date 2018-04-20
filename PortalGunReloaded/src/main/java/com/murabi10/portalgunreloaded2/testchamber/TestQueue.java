package com.murabi10.portalgunreloaded2.testchamber;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import com.murabi10.portalgunreloaded2.PortalGun;
import com.murabi10.portalgunreloaded2.devices.DeviceManager;
import com.murabi10.portalgunreloaded2.portalevents.PlayerChamberGoalEvent;
import com.murabi10.portalgunreloaded2.portalgun.PortalDevice;
import com.murabi10.portalgunreloaded2.testingelement.ElementType;
import com.murabi10.portalgunreloaded2.testingelement.TestingElement;
import com.murabi10.portalgunreloaded2.testingelement.objects.Cube;

public class TestQueue implements org.bukkit.event.Listener {
	private static ArrayList<Location> locs = new ArrayList<Location>();

	private Player testSubject;
	private TestChamberData testChamberData;
	private TestChamber testChamber;
	private Location exitLocation;
	private Location lobbyLocation;
	private Location originLoc = null;
	private Location StartLocation = null;
	private ItemStack[] inv;
	private int index = 0;

	private ArrayList<TestChamberData> testChambers = new ArrayList<TestChamberData>();

	public TestQueue(Player testSubject, ArrayList<TestChamberData> testChamber, Location exit, Location lobby) {
		this.testSubject = testSubject;
		this.testChambers = testChamber;
		this.exitLocation = exit;
		this.lobbyLocation = lobby;
	}

	public boolean BeginTest() {
		return BeginTest(true);
	}

	@SuppressWarnings("deprecation")
	public boolean BeginTest(boolean reserve) {
		if (reserve) {
			this.originLoc = reserve();
		}
		if (this.originLoc != null) {
			this.testChamberData = testChambers.get(index);
			this.testChamber = DataSystem.getChamberObject(this.testChamberData.getFileName());
			this.testChamber.placeToWorld(this.originLoc);

			Bukkit.getServer().getPluginManager().registerEvents(this, PortalGun.getPlugin());

			for (TestingElement e : this.testChamber.TestingElements()) {
				e.setOriginLocation(this.originLoc);
				e.setEditMode(false);
				e.setTargetPlayer(this.testSubject);
				e.initRunnable();
			}

			for (TestingElement e : this.testChamber.TestingElements()) {
				if (e.getType().equals(ElementType.START_POINT)) {
					Location loc = com.murabi10.portalgunreloaded2.Methods
							.LocationNormalize(e.getRelative1(this.originLoc));
					if ((loc.getBlock().isEmpty())
							&& (loc.getBlock().getRelative(org.bukkit.block.BlockFace.UP).isEmpty())) {
						this.testSubject.teleport(loc);

						this.StartLocation = loc;

						PortalDevice dev = PortalDevice.getDeviceInstance(this.testSubject);
						if ((dev.getQueue() != null) && (!dev.getQueue().equals(this))) {
							dev.getQueue().stop();
						}
						dev.setQueue(this);

						dev.clearPortal();

						if (reserve) {
							this.inv = this.testSubject.getInventory().getContents();
						}

						this.testSubject.getInventory().clear();

						this.testSubject.getInventory()
								.setBoots(com.murabi10.portalgunreloaded2.LongFallBoots.getBoots());

						ItemStack portalGun = null;

						switch (this.testChamberData.getPortalGunGive()) {
						case DUAL_PORTAL_DEVICE:
							portalGun = DeviceManager.getDevice("PORTALGUN").getItem();
							break;
						case NONE:
							portalGun = DeviceManager.getDevice("BROKEN_PORTALGUN").getItem();
							break;
						default:
							break;
						}

						if (portalGun != null) {
							this.testSubject.getInventory().addItem(new ItemStack[] { portalGun });
						}

						this.testSubject.sendTitle(this.testChamberData.getChamberName(),
								this.testChamberData.getDesignerName());

						locs.add(this.originLoc);
						return true;
					}
				}
			}

			for (TestingElement element : this.testChamber.TestingElements()) {
				element.disable();
			}

			StopTest();

			this.testSubject.sendMessage("エラー：このチェンバーは開始位置が存在しないためテストを開始できませんてした");
			this.testSubject.sendMessage("チェンバー設計者(" + this.testChamberData.getDesignerName() + ")に連絡してください。");

			return false;
		}
		this.testSubject.sendMessage("同時テスト可能数を超えているためテストを開始できませんでした。");
		this.testSubject.sendMessage("しばらくたってからやり直してください。");
		return false;
	}

	public void StopTest() {
		this.testSubject.teleport(this.lobbyLocation);
		stop();
		PortalDevice d = PortalDevice.getDeviceInstance(this.testSubject);
		if (d != null) {
			d.clearPortal();
			if (d.getQueue() != null) {
				d.setQueue(null);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onGoal(PlayerChamberGoalEvent e) {
		if (this.testSubject.getUniqueId().equals(e.getPlayer().getUniqueId())) {

			stopTestingElements();
			index++;
			if (index >= testChambers.size()) {
				e.getPlayer().teleport(this.exitLocation);
				e.getPlayer().sendTitle("クリア", "That's awesome!");
				stop();
			} else {
				e.getPlayer().sendMessage("テストチェンバー" + index+1 + " クリア");
				BeginTest(false);
			}
			PortalDevice d = PortalDevice.getDeviceInstance(e.getPlayer());
			if (d != null) {
				d.clearPortal();
				Cube c = d.getHangingCube();
				if (c != null) {
					c.Destroy(false);
					if (d.getQueue() != null) {
						d.setQueue(null);
					}
				}
			}
		}
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		if (this.testSubject.getUniqueId().equals(e.getPlayer().getUniqueId())) {
			e.getPlayer().sendMessage("テスト再開中... しばらくおまちください");
			PortalDevice d = PortalDevice.getDeviceInstance(e.getPlayer());
			if (d != null) {
				d.clearPortal();
				Cube c = d.getHangingCube();
				if (c != null) {
					c.Destroy(false);
					if (d.getQueue() != null) {
						d.setQueue(null);
					}
				}
			}
			restartTest();
			e.setRespawnLocation(this.StartLocation);
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if (this.testSubject.getUniqueId().equals(e.getPlayer().getUniqueId())) {
			e.getPlayer().teleport(this.lobbyLocation);
			PortalDevice d = PortalDevice.getDeviceInstance(e.getPlayer());
			if (d != null) {
				d.clearPortal();
				Cube c = d.getHangingCube();
				if (c != null) {
					c.Destroy(false);
					if (d.getQueue() != null) {
						d.setQueue(null);
					}
				}
			}
			stop();
		}
	}

	private void stop() {
		this.testSubject.getInventory().clear();
		if (this.inv != null) {
			this.testSubject.getInventory().setContents(this.inv);
		}
		locs.remove(this.originLoc);
		PlayerChamberGoalEvent.getHandlerList().unregister(this);
		PlayerRespawnEvent.getHandlerList().unregister(this);
		PlayerQuitEvent.getHandlerList().unregister(this);
		stopTestingElements();
	}

	private void stopTestingElements() {
		this.testChamber.deleteFromWorld(this.originLoc);
		for (TestingElement element : this.testChamber.TestingElements()) {
			element.disable();
		}
	}

	public void restartTest() {
		PlayerChamberGoalEvent.getHandlerList().unregister(this);
		PlayerRespawnEvent.getHandlerList().unregister(this);
		PlayerQuitEvent.getHandlerList().unregister(this);
		for (TestingElement element : this.testChamber.TestingElements()) {
			element.disable();
		}
		BeginTest(false);
	}

	private static Location reserve() {
		Location loc = new Location(Bukkit.getWorld(PortalGun.EditorWorldName), 0.0D, 0.0D, 128.0D);
		int i = 0;
		for (;;) {
			if (i >= 6) {
				return null;
			}

			if (!locs.contains(loc))
				break;
			loc = loc.add(65.0D, 0.0D, 0.0D);
			i++;
		}
		return loc;
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testchamber\TestQueue.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */