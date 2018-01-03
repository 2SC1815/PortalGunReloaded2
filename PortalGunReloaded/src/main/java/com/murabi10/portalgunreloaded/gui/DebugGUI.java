package com.murabi10.portalgunreloaded.gui;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import com.murabi10.portalgunreloaded.devices.DeviceManager;
import com.murabi10.portalgunreloaded.portalgun.Portal;
import com.murabi10.portalgunreloaded.portalgun.PortalDevice;
import com.murabi10.portalgunreloaded.selector.EditChamberSelector;
import com.murabi10.portalgunreloaded.selector.SortType;
import com.murabi10.portalgunreloaded.testingelement.fixture.ThermalDiscouragementBeam;
import com.murabi10.portalgunreloaded.testingelement.objects.Cube;
import com.murabi10.portalgunreloaded.testingelement.objects.CubeType;

public class DebugGUI extends GUI {
	public void init() {
		setMenuName("DEBUG MENU");
		setSize(3);

		addbutton(new Button(Material.STONE, 1, (short) 0, "DEBUG : PORTAL SPECIFICATIONS", 3, new GUIFunction() {
			public boolean click(Player p, ClickType type) {
				ArrayList<Portal> portals = new ArrayList<Portal>();
				for (PortalDevice portalgun : PortalDevice.getDevices()) {
					Portal portal = portalgun.getBluePortal();
					if (portal != null) {
						portals.add(portal);
					}
					portal = portalgun.getOrangePortal();
					if (portal != null) {
						portals.add(portal);
					}
				}
				p.sendMessage("===Portal specifications===");
				for (Portal portal : portals) {
					p.sendMessage("Color:" + portal.getColor().toString() + " Luncher:" + portal.getPlayer().getName() +
							" LaunchDirection:" + portal.getLaunchDirection().toString() + " Sub:" +
							portal.getSubstitutionDirection().toString());
					Portal dest = portal.getDest();
					if (dest != null) {
						p.sendMessage("Representative:" + portal.getRepresentativeLocation() + " Dest:" +
								dest.getRepresentativeLocation());
					} else {
						p.sendMessage("Representative:" + portal.getRepresentativeLocation() + " Dest: NOT FOUND");
					}
					String locs = "locs: ";
					for (Location loc : portal.getLocations()) {
						locs = locs + loc.toString() + ", ";
					}
					p.sendMessage(locs);
					p.sendMessage("=== === === === ===");
				}
				p.sendMessage("=== === END === ===");
				return true;
			}
		}, new String[] {

				"現在設置されているポータルの", "情報を表示します" }));

		addbutton(new Button(Material.STONE, 2, (short) 0, "DEBUG : TEST CHAMBERS", 4, new GUIFunction() {

			public boolean click(Player p, ClickType type) {

				try {

					p.closeInventory();
					EditChamberSelector.OpenGUI(p, 0, SortType.NAME, "");
				} catch (Exception e) {
					e.printStackTrace();
					p.sendMessage("なんかえらーでた");
				}
				return false;
			}
		}, new String[] {

				"テストチェンバーの", "テスト" }));

		addbutton(new Button(Material.STONE, 2, (short) 0, "DEBUG : GIVE PORTALGUN", 1, new GUIFunction() {

			public boolean click(Player p, ClickType type) {
				ItemStack item = null;
				switch (type) {
				case CONTROL_DROP:
					item = DeviceManager.getDevice("PORTALGUN").getItem();
					break;
				case DOUBLE_CLICK:
					item = DeviceManager.getDevice("BROKEN_PORTALGUN").getItem();
					break;
				default:
					break;
				}

				if (item != null) {
					int index = p.getInventory().firstEmpty();
					if (index != -1) {
						p.getInventory().setItem(index, item);
						return true;
					}
				}
				return false;
			}
		}, new String[] {

				"左クリックでデュアルポータルガン、", "右クリックでシングルポータルガンを出す" }));

		addbutton(new Button(Material.REDSTONE_BLOCK, 2, (short) 0, "DEBUG : CUBE TEST", 5, new GUIFunction() {

			public boolean click(Player p, ClickType type) {

				switch (type) {
				case DOUBLE_CLICK:
					new Cube(p.getLocation(), CubeType.REFLECTION);
					break;
				case CONTROL_DROP:
					new Cube(p.getLocation(), CubeType.NORMAL);
					break;
				case NUMBER_KEY:
					new Cube(p.getLocation(), CubeType.COMPANION);
					break;
				case CREATIVE:
				case DROP:
				case LEFT:
				case MIDDLE:
				default:
					return false;
				}

				return true;
			}
		}, new String[] {

				"WEIGHTED STORAGE CUBE TEST!", "左クリックで通常、右で反射、", "ミドルでコンパニオン" }));

		addbutton(new Button(Material.STONE, 2, (short) 0, "DEBUG : THERMAL DISCOURAGEMENT BEAM TEST", 6,
				new GUIFunction() {

					public boolean click(Player p, ClickType type) {
						new ThermalDiscouragementBeam(p.getLocation(), BlockFace.SOUTH, 0, 0, 0);

						return true;
					}
				}, new String[] {

						"LASER TEST!" }));

		addbutton(new Button(Material.GOLD_BLOCK, 2, (short) 0, "DEBUG : GET TOOLS", 7, new GUIFunction() {

			public boolean click(Player p, ClickType type) {
				int index = p.getInventory().firstEmpty();
				if (index != -1) {
					p.getInventory().setItem(index, DeviceManager.getDevice("PLACER").getItem());
				}
				index = p.getInventory().firstEmpty();
				if (index != -1) {
					p.getInventory().setItem(index, DeviceManager.getDevice("SELECTOR").getItem());
				}
				index = p.getInventory().firstEmpty();
				if (index != -1) {
					p.getInventory().setItem(index, DeviceManager.getDevice("INFO").getItem());
				}
				return true;
			}
		}, new String[] {

				"  " }));
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\gui\DebugGUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */