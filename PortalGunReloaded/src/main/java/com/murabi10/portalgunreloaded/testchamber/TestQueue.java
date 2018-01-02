package com.murabi10.portalgunreloaded.testchamber;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import com.murabi10.portalgunreloaded.LongFallBoots;
import com.murabi10.portalgunreloaded.Methods;
import com.murabi10.portalgunreloaded.PortalGun;
import com.murabi10.portalgunreloaded.devices.DeviceManager;
import com.murabi10.portalgunreloaded.portalevents.PlayerChamberGoalEvent;
import com.murabi10.portalgunreloaded.portalgun.PortalDevice;
import com.murabi10.portalgunreloaded.testingelement.ElementType;
import com.murabi10.portalgunreloaded.testingelement.TestingElement;
import com.murabi10.portalgunreloaded.testingelement.objects.Cube;

public class TestQueue implements Listener {

	private static ArrayList<Location> locs = new ArrayList<Location>();

	private Player testSubject;
	private TestChamberData testChamberData ;
	private TestChamber testChamber;
	private Location exitLocation;
	private Location lobbyLocation;
	private Location originLoc = null;
	private Location StartLocation = null;
	private ItemStack[] inv;

	public TestQueue(Player testSubject, TestChamberData testChamber, Location exit, Location lobby) {
		this.testSubject = testSubject;
		this.testChamberData = testChamber;
		this.exitLocation = exit;
		this.lobbyLocation = lobby;
	}

	public boolean BeginTest() {
		return BeginTest(true);
	}
	@SuppressWarnings("deprecation")
	public boolean BeginTest(boolean reserve) {

		if (reserve)
			this.originLoc = reserve();

		if (this.originLoc != null) {


			this.testChamber = DataSystem.getChamberObject(this.testChamberData.getFileName());
			this.testChamber.placeToWorld(originLoc);

			Bukkit.getServer().getPluginManager().registerEvents(this, PortalGun.getPlugin());

			for (TestingElement e : this.testChamber.TestingElements()) {
				//System.out.println(e.toString());
				e.setOriginLocation(originLoc);
				e.setEditMode(false);
				e.setTargetPlayer(testSubject);
				e.initRunnable();
			}

			for (TestingElement e : this.testChamber.TestingElements()) {
				if (e.getType().equals(ElementType.START_POINT)) {
					Location loc = Methods.LocationNormalize(e.getRelative1(originLoc));
					if (loc.getBlock().isEmpty() && loc.getBlock().getRelative(BlockFace.UP).isEmpty()) {
						this.testSubject.teleport(loc);

						this.StartLocation = loc;

						PortalDevice dev = PortalDevice.getDeviceInstance(this.testSubject);
						if (dev.getQueue() != null && !dev.getQueue().equals(this)) {
							dev.getQueue().stop();
						}
						dev.setQueue(this);

						//TODO

						if (reserve) {
							this.inv = this.testSubject.getInventory().getContents();
						}

						this.testSubject.getInventory().clear();

						this.testSubject.getInventory().setBoots(LongFallBoots.getBoots());

						ItemStack portalGun = null;

						switch(this.testChamberData.getPortalGunGive()) {
						case DUAL_PORTAL_DEVICE:
							portalGun = DeviceManager.getDevice("PORTALGUN").getItem();
							break;
						case SINGLE_PORTAL_DEVICE:
							portalGun = DeviceManager.getDevice("BROKEN_PORTALGUN").getItem();
							break;
						default:
							break;

						}

						if (portalGun != null) {
							this.testSubject.getInventory().addItem(portalGun);
						}

						this.testSubject.sendTitle(this.testChamberData.getChamberName(), this.testChamberData.getDesignerName());

						locs.add(this.originLoc);
						return true;
					}
				}
			}

			for (TestingElement element : this.testChamber.TestingElements()) {
				element.disable();
			}

			StopTest();

			testSubject.sendMessage("エラー：このチェンバーは開始位置が存在しないためテストを開始できませんてした");
			testSubject.sendMessage("チェンバー設計者(" + this.testChamberData.getDesignerName() + ")に連絡してください。");

			return false;
		} else {
			testSubject.sendMessage("同時テスト可能数を超えているためテストを開始できませんでした。");
			testSubject.sendMessage("しばらくたってからやり直してください。");
			return false;
		}

	}

	public void StopTest() {
		this.testSubject.teleport(this.lobbyLocation);
		stop();
		PortalDevice d = PortalDevice.getDeviceInstance(this.testSubject);
		if (d != null) {
			if (d.getQueue() != null) {
				d.setQueue(null);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onGoal(PlayerChamberGoalEvent e) {
		if (this.testSubject.getUniqueId().equals(e.getPlayer().getUniqueId())) {
			e.getPlayer().teleport(this.exitLocation);
			e.getPlayer().sendTitle("テストチェンバークリア！", "That's awesome!");
			stop();
			PortalDevice d = PortalDevice.getDeviceInstance(e.getPlayer());
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

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		if (this.testSubject.getUniqueId().equals(e.getPlayer().getUniqueId())) {
			e.getPlayer().sendMessage("テスト再開中... しばらくおまちください");
			restartTest();
			e.setRespawnLocation(StartLocation);
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if (this.testSubject.getUniqueId().equals(e.getPlayer().getUniqueId())) {
			e.getPlayer().teleport(this.lobbyLocation);
			stop();
		}
	}

	private void stop() {
		this.testSubject.getInventory().clear();
		if (this.inv != null) {
			this.testSubject.getInventory().setContents(this.inv);
		}
		locs.remove(originLoc);
		PlayerChamberGoalEvent.getHandlerList().unregister(this);
		PlayerRespawnEvent.getHandlerList().unregister(this);
		PlayerQuitEvent.getHandlerList().unregister(this);
		this.testChamber.deleteFromWorld(originLoc);
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
		this.BeginTest(false);
	}

	private static Location reserve() {
		Location loc = new Location(Bukkit.getWorld(PortalGun.EditorWorldName), 0, 0, 128);
		int i = 0;
		while (true) {

			if (i >= 6) {
				return null;
			}

			if (locs.contains(loc)) {
				loc = loc.add(65, 0, 0);
				i++;
			} else {
				return loc;
			}

		}
	}

}
