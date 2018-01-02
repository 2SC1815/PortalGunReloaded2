package com.murabi10.portalgunreloaded.devices;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.murabi10.portalgunreloaded.PortalGun;
import com.murabi10.portalgunreloaded.chambereditor.TestChamberEditor;
import com.murabi10.portalgunreloaded.portalgun.PortalColor;
import com.murabi10.portalgunreloaded.portalgun.PortalDevice;

public class DeviceManager implements Listener {

	public static ArrayList<Device> devices = new ArrayList<Device>();

	public static void Enable() {

		new Device(new Function() {



			@Override
			public void func(Player player, Action action) {

				if (action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
					PortalDevice.getDeviceInstance(player).LaunchPortal(PortalColor.BLUE);
				}

				if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
					PortalDevice.getDeviceInstance(player).LaunchPortal(PortalColor.ORANGE);
				}

			}
		}, Material.WOOD_SWORD, (short) 0, "PORTALGUN", "デュアルポータルガン", "Aperture Handheld Portal Device", "右クリックでオレンジ", "左クリックでブルーの",
				"ポータルが打てる").Register();

		new Device(new Function() {

			@Override
			public void func(Player player, Action a) {
				PortalDevice.getDeviceInstance(player).LaunchPortal(PortalColor.BLUE);
			}
		}, Material.WOOD_SWORD, (short) 0, "BROKEN_PORTALGUN", "シングルポータルガン", "Aperture Handheld Portal Device", "クリックでブルーの", "ポータルが打てる")
				.Register();

		new Device(new Function() {

			@Override
			public void func(Player player, Action action) {
				TestChamberEditor editor = TestChamberEditor.getEditor(player);

				if (editor != null) {

					if (action.equals(Action.LEFT_CLICK_BLOCK)) {
						editor.LClick();
					}

					if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
						editor.RClick();
					}

				}
			}
		}, Material.WOOD_AXE, (short) 0, "SELECTOR", "セレクタ", "右/左クリックで", "範囲選択ができる")
				.Register();

		new Device(new Function() {

			@Override
			public void func(Player player, Action action) {
				TestChamberEditor editor = TestChamberEditor.getEditor(player);

				if (editor != null) {

					if (action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR)) {
						PortalGun.fixture.openGUI(player);
					}

				}
			}
		}, Material.WOOD_HOE, (short) 0, "PLACER", "エディターメニュー", "右クリックでメニューを開く。", "セーブしたり", "選択範囲内の編集を行ったり", "テスト装置を設置したりできる")
				.Register();


	}

	public static void add(Device d) {
		if (!devices.contains(d)) {
			devices.add(d);
		}
	}

	public static Device getDevice(String str) {
		for (Device d : devices) {
			if (d.getName().equals(str)) {
				return d;
			}
		}
		return null;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		ItemStack item = e.getItem();
		if (item != null && item.hasItemMeta()) {
			ItemMeta meta = item.getItemMeta();
			if (meta.hasLore()) {
				List<String> lore = meta.getLore();
				Material material = item.getType();
				short data = item.getDurability();
				for (Device device : devices) {
					ItemStack d = device.getItem();
					if (d.getType().equals(material) && d.getDurability() == data
							&& d.getItemMeta().getLore().equals(lore)) {
						device.runFunction(e.getPlayer(), e.getAction());
					}
				}
			}
		}
	}

}
