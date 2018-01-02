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

	@Override
	public void init() {

		setMenuName("DEBUG MENU");
		setSize(3);

		addbutton(new Button(Material.STONE, 1, (short) 0, "DEBUG : PORTAL SPECIFICATIONS", 3, new GUIFunction() {

			@Override
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
					p.sendMessage("Color:" + portal.getColor().toString() + " Luncher:" + portal.getPlayer().getName()
							+ " LaunchDirection:" + portal.getLaunchDirection().toString() + " Sub:"
							+ portal.getSubstitutionDirection().toString());
					Portal dest = portal.getDest();
					if (dest != null) {
						p.sendMessage("Representative:" + portal.getRepresentativeLocation() + " Dest:"
								+ dest.getRepresentativeLocation());
					} else {
						p.sendMessage("Representative:" + portal.getRepresentativeLocation() + " Dest: NOT FOUND");
					}
					String locs = "locs: ";
					for (Location loc : portal.getLocations()) {
						locs += loc.toString() + ", ";
					}
					p.sendMessage(locs);
					p.sendMessage("=== === === === ===");
				}
				p.sendMessage("=== === END === ===");
				return true;
			}

		}, "現在設置されているポータルの", "情報を表示します"));

		addbutton(new Button(Material.STONE, 2, (short) 0, "DEBUG : TEST CHAMBERS", 4, new GUIFunction() {

			@Override
			public boolean click(Player p, ClickType type) {

				/*switch (type) {
				case LEFT:
					if (TestChamberEditor.getEditor(p) != null) {
						return false;
					}
				TestChamber tc = TestChamberEditor.ImportFromFile("test");

				if (tc == null) {
					tc = new TestChamber(p.getUniqueId(), "test");
				}

				try {
					TestChamberEditor edit = new TestChamberEditor(p.getLocation(), tc, p);

					edit.setVisible(true);

					edit.StartEdit();

				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				case RIGHT:
					if (TestChamberEditor.getEditor(p) != null) {
						TestChamberEditor.getEditor(p).Exit();
					}
					break;
				default:
					return false;
				}*/

				try {
					p.closeInventory();
					EditChamberSelector.OpenGUI(p, 0, SortType.NAME, "");
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
					p.sendMessage("なんかえらーでた");
				}
				return false;
			}

		}, "テストチェンバーの", "テスト"));

		addbutton(new Button(Material.STONE, 2, (short) 0, "DEBUG : GIVE PORTALGUN", 1, new GUIFunction() {

			@Override
			public boolean click(Player p, ClickType type) {

				ItemStack item = null;
				switch (type) {
				case LEFT:
					item = DeviceManager.getDevice("PORTALGUN").getItem();
					break;
				case RIGHT:
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

		}, "左クリックでデュアルポータルガン、", "右クリックでシングルポータルガンを出す"));

		addbutton(new Button(Material.REDSTONE_BLOCK, 2, (short) 0, "DEBUG : CUBE TEST", 5, new GUIFunction() {

			@Override
			public boolean click(Player p, ClickType type) {


				switch(type) {
				case RIGHT:
					new Cube(p.getLocation(), CubeType.REFLECTION);
					break;
				case LEFT:
					new Cube(p.getLocation(), CubeType.NORMAL);
					break;
				case MIDDLE:
					new Cube(p.getLocation(), CubeType.COMPANION);
					break;
				default:
					return false;
				}

				return true;
			}

		}, "WEIGHTED STORAGE CUBE TEST!", "左クリックで通常、右で反射、", "ミドルでコンパニオン"));

		addbutton(new Button(Material.STONE, 2, (short) 0, "DEBUG : THERMAL DISCOURAGEMENT BEAM TEST", 6, new GUIFunction() {

			@Override
			public boolean click(Player p, ClickType type) {

				new ThermalDiscouragementBeam(p.getLocation(), BlockFace.SOUTH, 0, 0, 0);

				return true;
			}

		}, "LASER TEST!"));


		addbutton(new Button(Material.STONE, 2, (short) 0, "DEBUG : GIVE PLASER AND SELECTOR", 7, new GUIFunction() {

			@Override
			public boolean click(Player p, ClickType type) {

					int index = p.getInventory().firstEmpty();
					if (index != -1) {
						p.getInventory().setItem(index, DeviceManager.getDevice("PLACER").getItem());
					}
					index = p.getInventory().firstEmpty();
					if (index != -1) {
						p.getInventory().setItem(index, DeviceManager.getDevice("SELECTOR").getItem());
					}
				return true;
			}

		}, "  "));

	}

}
