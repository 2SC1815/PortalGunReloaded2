package com.murabi10.portalgunreloaded.chambereditor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.murabi10.portalgunreloaded.Methods;
import com.murabi10.portalgunreloaded.PortalGun;
import com.murabi10.portalgunreloaded.selector.EditChamberSelector;
import com.murabi10.portalgunreloaded.testchamber.DataSystem;
import com.murabi10.portalgunreloaded.testchamber.TestChamber;
import com.murabi10.portalgunreloaded.testchamber.TestChamberData;
import com.murabi10.portalgunreloaded.testingelement.ElementType;
import com.murabi10.portalgunreloaded.testingelement.TestingElement;
import com.murabi10.portalgunreloaded.testingelement.area.StartPoint;

public class TestChamberEditor {

	private static ArrayList<TestChamberEditor> editors = new ArrayList<TestChamberEditor>();

	private Location ChamberOriginLoc;
	private TestChamber target;
	private TestChamberData targetdata;
	private Location CuboidPos1 = null; // CuboidPos is click to place block
										// location
	private Location CuboidPos2 = null;
	private BlockFace selectionFace;

	private Player EditPlayer;

	private BukkitRunnable selection;
	private boolean selectVisible = false;

	public TestChamberEditor(Location canvasLoc, TestChamber targetChamber, TestChamberData data, Player editPlayer)
			throws Exception {
		for (TestChamberEditor editor : editors) {
			if (editor.getEditPlayer().getUniqueId().equals(editPlayer.getUniqueId())) {
				throw new Exception("Player " + editPlayer.getName() + " has already started edit mode!");
			}
		}
		this.ChamberOriginLoc = canvasLoc;
		this.targetdata = data;
		this.target = targetChamber;
		this.EditPlayer = editPlayer;
		editors.add(this);

		selection = new BukkitRunnable() {

			@Override
			public void run() {
				visual();
			}

		};

	}

	public void setVisible(boolean visible) {
		this.selectVisible = visible;
		if (this.selectVisible) {
			selection.runTaskTimer(PortalGun.getPlugin(), 1, 3);
		} else {
			selection.cancel();
		}
	}

	public boolean getVisible() {
		return this.selectVisible;
	}

	private void visual() {
		if (getPointLoc() != null) {
			Location point = Methods.LocationNormalize(
					getPointLoc().getBlock().getRelative(selectionFace).getLocation().clone().add(0, 1, 0));
			Methods.spawnParticle(point, (byte) 254, (byte) 254, (byte) 45);
			Methods.spawnParticle(point.clone().add(Methods.BlockFaceToVector(selectionFace, 0.4)), (byte) 254,
					(byte) 45, (byte) 45);
		}
	}

	public Location getOrigin() {
		return this.ChamberOriginLoc;
	}

	public void StartEdit() {
		target.placeToWorld(ChamberOriginLoc);

		for (TestingElement e : target.TestingElements()) {
			//System.out.println(e.toString());
			e.setOriginLocation(ChamberOriginLoc);
			e.setEditMode(true);
			e.setTargetPlayer(EditPlayer);
			e.initRunnable();
		}

		for (TestingElement e : target.TestingElements()) {
			if (e.getType().equals(ElementType.START_POINT)) {
				Location loc = e.getRelative1(ChamberOriginLoc);
				if (loc.getBlock().isEmpty() && loc.getBlock().getRelative(BlockFace.UP).isEmpty()) {
					e.getTargetPlayer().teleport(Methods.LocationNormalize(loc));
					return;
				}
			}
		}

		ArrayList<TestingElement> queue = new ArrayList<TestingElement>();
		for (TestingElement element : this.target.TestingElements()) {
			if (element.getType().equals(ElementType.START_POINT)) {
				element.disable();
				queue.add(element);
			}
		}

		for (TestingElement element : queue) {
			this.target.TestingElements().remove(element);
		}

		queue.clear();
		queue = null;

		int minx = Math.min(ChamberOriginLoc.getBlockX(), ChamberOriginLoc.getBlockX() + 64),
				miny = Math.min(ChamberOriginLoc.getBlockY(), ChamberOriginLoc.getBlockY() + 64),
				minz = Math.min(ChamberOriginLoc.getBlockZ(), ChamberOriginLoc.getBlockZ() + 64),
				maxx = Math.max(ChamberOriginLoc.getBlockX(), ChamberOriginLoc.getBlockX() + 64),
				maxy = Math.max(ChamberOriginLoc.getBlockY(), ChamberOriginLoc.getBlockY() + 64),
				maxz = Math.max(ChamberOriginLoc.getBlockZ(), ChamberOriginLoc.getBlockZ() + 64);

		for (int x = minx+1; x < maxx; x++) {
			for (int y = miny+1; y < maxy; y++) {
				for (int z = minz+1; z < maxz; z++) {
					Location loc = new Location(ChamberOriginLoc.getWorld(), x, y, z);
					if (loc.getBlock().isEmpty() && loc.getBlock().getRelative(BlockFace.UP).isEmpty()) {
						StartPoint point = new StartPoint(ChamberOriginLoc, Methods.getX(ChamberOriginLoc, loc),
								Methods.getY(ChamberOriginLoc, loc), Methods.getZ(ChamberOriginLoc, loc));
						AddTestElement(point);
						point.setOriginLocation(ChamberOriginLoc);
						point.setEditMode(true);
						point.setTargetPlayer(EditPlayer);
						point.initRunnable();

						EditPlayer.teleport(loc);
						return;
					}
				}
			}
		}

		Location loc = ChamberOriginLoc.clone().add(2, 2, 2);
		loc.getBlock().setType(Material.AIR);
		loc.getBlock().getRelative(BlockFace.UP).setType(Material.AIR);
		StartPoint point = new StartPoint(ChamberOriginLoc, Methods.getX(ChamberOriginLoc, loc),
				Methods.getY(ChamberOriginLoc, loc), Methods.getZ(ChamberOriginLoc, loc));
		AddTestElement(point);
		EditPlayer.teleport(loc);

		// TODO
	}

	public void Save() {
		target.importFromWorld(ChamberOriginLoc);
	}

	public Player getEditPlayer() {
		return this.EditPlayer;
	}

	public void Link(TestingElement SignalFrom, TestingElement activateTarget) {
		if (!activateTarget.Switches().contains(SignalFrom)) {
			activateTarget.Switches().add(SignalFrom);
		}
	}

	public void SaveToFile() {
		DataSystem.Save(target, targetdata, targetdata.getFileName());
	}

	public void Exit() {
		setVisible(false);
		disableAllElements();
		selection = null;
		Save();
		SaveToFile();
		editors.remove(this);
		this.target.deleteFromWorld(ChamberOriginLoc);
		EditChamberSelector.remove(this.ChamberOriginLoc);
		this.EditPlayer.teleport(this.EditPlayer.getBedSpawnLocation());
		EditChamberSelector.updateData();
		// TODO
	}

	public void printData() {

	}

	public void checkElements() {
		for (TestingElement element : this.target.TestingElements()) {
			element.check();
		}
	}

	public void disableAllElements() {
		for (TestingElement element : this.target.TestingElements()) {
			element.disable();
		}
	}

	// TODO panel & testing element Manipulator

	public boolean AddTestElement(TestingElement e) {

		if (e.getType().equals(ElementType.START_POINT) || e.getType().equals(ElementType.GOAL_POINT)) {

			ArrayList<TestingElement> queue = new ArrayList<TestingElement>();
			for (TestingElement element : this.target.TestingElements()) {
				if (element.getType().equals(e.getType())) {
					element.disable();
					queue.add(element);
				}
			}

			for (TestingElement element : queue) {
				this.target.TestingElements().remove(element);
			}

		}


		if (this.getElement(e.getRelative1(this.ChamberOriginLoc)) != null
				|| this.getElement(e.getRelative2(this.ChamberOriginLoc)) != null) {
			return false;
		}
		this.target.TestingElements().add(e);

		e.setOriginLocation(ChamberOriginLoc);
		e.setEditMode(true);
		e.setTargetPlayer(EditPlayer);

		return true;
	}

	public void RemoveElement(Location loc) {
		ArrayList<TestingElement> queue = new ArrayList<TestingElement>();
		for (TestingElement element : this.target.TestingElements()) {
			if (Methods.LocationEquals(element.getRelative1(this.ChamberOriginLoc), loc)
					|| Methods.LocationEquals(element.getRelative2(this.ChamberOriginLoc), loc)) {
				element.disable();
				queue.add(element);
			}
		}

		for (TestingElement element : queue) {
			RemoveElement(element);
		}

		queue.clear();
		queue = null;
	}

	public void RemoveElement(TestingElement e) {
		if (this.target.TestingElements().contains(e)) {
			for (TestingElement el : this.target.TestingElements()) {
				if (el.Switches().contains(e)) {
					el.Switches().remove(e);
				}
			}
			this.target.TestingElements().remove(e);
		}
	}

	public TestingElement getElement(Location loc) {
		for (TestingElement element : this.target.TestingElements()) {
			if (Methods.LocationEquals(element.getRelative1(this.ChamberOriginLoc), loc)
					|| Methods.LocationEquals(element.getRelative2(this.ChamberOriginLoc), loc)) {
				return element;
			}
		}
		return null;
	}

	public boolean Pos1Empt() {
		return CuboidPos1 == null;
	}

	public boolean Pos2Empt() {
		return CuboidPos2 == null;
	}

	public Location getPos1() {
		return CuboidPos1;
	}

	public Location getPos2() {
		return CuboidPos2;
	}

	public Location getPointLoc() {
		if ((Pos1Empt() && Pos2Empt())) {
			return null;
		}
		if (Pos2Empt()) {
			return CuboidPos1;
		} else if (Pos1Empt()) {
			return CuboidPos2;
		} else if (Methods.LocationEquals(CuboidPos1, CuboidPos2)) {
			return CuboidPos1;
		}
		return null;
	}

	public BlockFace getsel() {
		return this.selectionFace;
	}

	public void clearPos() {
		CuboidPos1 = null;
		CuboidPos2 = null;
		selectionFace = null;
	}

	public void RClick() {
		List<Block> blocks = this.EditPlayer.getLastTwoTargetBlocks(null, 5);
		this.CuboidPos1 = blocks.get(1).getLocation();
		selectionFace = blocks.get(1).getFace(blocks.get(0));
	}

	public void LClick() {
		List<Block> blocks = this.EditPlayer.getLastTwoTargetBlocks(null, 5);
		this.CuboidPos2 = blocks.get(1).getLocation();
		selectionFace = blocks.get(1).getFace(blocks.get(0));
	}

	public int getX(Location target) {
		return target.getBlockX() - this.ChamberOriginLoc.getBlockX();
	}

	public int getY(Location target) {
		return target.getBlockY() - this.ChamberOriginLoc.getBlockY();
	}

	public int getZ(Location target) {
		return target.getBlockZ() - this.ChamberOriginLoc.getBlockZ();
	}

	public static void Check() {
		File f = new File(PortalGun.TEST_CHAMBER_FILE_PATH);
		if (!(f.exists() && f.isDirectory())) {
			f.delete();
			f.mkdirs();
		}
	}

	public static ArrayList<File> getChambers() {
		ArrayList<File> file = new ArrayList<File>();
		File[] files = new File(PortalGun.TEST_CHAMBER_FILE_PATH).listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isFile() && f.toString().endsWith(PortalGun.TEST_CHAMBER_EXTENSION)) {
				file.add(f);
			}
		}
		return file;
	}

	public static TestChamberEditor getEditor(Player p) {
		for (TestChamberEditor editor : editors) {
			if (editor.getEditPlayer().getUniqueId().equals(p.getUniqueId())) {
				return editor;
			}
		}
		return null;
	}

	public boolean isCuboid() {
		return !Pos1Empt() && !Pos2Empt();
	}

}
