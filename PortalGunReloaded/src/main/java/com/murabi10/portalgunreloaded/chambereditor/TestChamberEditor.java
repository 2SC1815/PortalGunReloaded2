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

import com.murabi10.portalgunreloaded.Cuboid;
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
	private Location CuboidPos1 = null;

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

		this.selection = new BukkitRunnable() {
			public void run() {
				TestChamberEditor.this.visual();
			}
		};
	}

	public void setVisible(boolean visible) {
		this.selectVisible = visible;
		if (this.selectVisible) {
			this.selection.runTaskTimer(PortalGun.getPlugin(), 1L, 3L);
		} else {
			this.selection.cancel();
		}
	}

	public boolean getVisible() {
		return this.selectVisible;
	}

	private void visual() {
		if (getPointLoc() != null) {
			Location point = Methods.LocationNormalize(
					getPointLoc().getBlock().getRelative(this.selectionFace).getLocation().clone().add(0.0D, 1.0D,
							0.0D));
			Methods.spawnParticle(point, (byte) -2, (byte) -2, (byte) 45);
			Methods.spawnParticle(point.clone().add(Methods.BlockFaceToVector(this.selectionFace, 0.4D)), (byte) -2,
					(byte) 45, (byte) 45);
		}
	}

	public Location getOrigin() {
		return this.ChamberOriginLoc;
	}

	public void StartEdit() {
		this.target.placeToWorld(this.ChamberOriginLoc);

		for (TestingElement e : this.target.TestingElements()) {
			e.setOriginLocation(this.ChamberOriginLoc);
			e.setEditMode(true);
			e.setTargetPlayer(this.EditPlayer);
			e.initRunnable();
		}
		for (TestingElement e : this.target.TestingElements()) {
			if (e.getType().equals(ElementType.START_POINT)) {
				Location loc = e.getRelative1(this.ChamberOriginLoc);
				if ((loc.getBlock().isEmpty()) && (loc.getBlock().getRelative(BlockFace.UP).isEmpty())) {
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

		for (Block b : new Cuboid(this.ChamberOriginLoc.clone().add(1.0D, 1.0D, 1.0D),
				this.ChamberOriginLoc.clone().add(63.0D, 63.0D, 63.0D))) {
			Location loc = b.getLocation();
			if ((loc.getBlock().isEmpty()) && (loc.getBlock().getRelative(BlockFace.UP).isEmpty())) {
				StartPoint point = new StartPoint(this.ChamberOriginLoc, Methods.getX(this.ChamberOriginLoc, loc),
						Methods.getY(this.ChamberOriginLoc, loc), Methods.getZ(this.ChamberOriginLoc, loc));
				AddTestElement(point);
				point.setOriginLocation(this.ChamberOriginLoc);
				point.setEditMode(true);
				point.setTargetPlayer(this.EditPlayer);
				point.initRunnable();

				this.EditPlayer.teleport(loc);
				return;
			}
		}

		Location loc = this.ChamberOriginLoc.clone().add(2.0D, 2.0D, 2.0D);
		loc.getBlock().setType(Material.AIR);
		loc.getBlock().getRelative(BlockFace.UP).setType(Material.AIR);
		StartPoint point = new StartPoint(this.ChamberOriginLoc, Methods.getX(this.ChamberOriginLoc, loc),
				Methods.getY(this.ChamberOriginLoc, loc), Methods.getZ(this.ChamberOriginLoc, loc));
		AddTestElement(point);
		this.EditPlayer.teleport(loc);
	}

	public void Save() {
		this.target.importFromWorld(this.ChamberOriginLoc);
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
		DataSystem.Save(this.target, this.targetdata, this.targetdata.getFileName());
	}

	public void Exit() {
		setVisible(false);
		disableAllElements();
		this.selection = null;
		Save();
		SaveToFile();
		editors.remove(this);
		this.target.deleteFromWorld(this.ChamberOriginLoc);
		EditChamberSelector.remove(this.ChamberOriginLoc);
		this.EditPlayer.teleport(this.EditPlayer.getBedSpawnLocation());
		EditChamberSelector.updateData();
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

	public boolean AddTestElement(TestingElement e) {
		if ((e.getType().equals(ElementType.START_POINT)) || (e.getType().equals(ElementType.GOAL_POINT))) {
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

		if ((getElement(e.getRelative1(this.ChamberOriginLoc)) != null) ||
				(getElement(e.getRelative2(this.ChamberOriginLoc)) != null)) {
			return false;
		}
		this.target.TestingElements().add(e);

		e.setOriginLocation(this.ChamberOriginLoc);
		e.setEditMode(true);
		e.setTargetPlayer(this.EditPlayer);

		return true;
	}

	public void RemoveElement(Location loc) {
		ArrayList<TestingElement> queue = new ArrayList<TestingElement>();
		for (TestingElement element : this.target.TestingElements()) {
			if ((Methods.LocationEquals(element.getRelative1(this.ChamberOriginLoc), loc)) ||
					(Methods.LocationEquals(element.getRelative2(this.ChamberOriginLoc), loc))) {
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
			if ((Methods.LocationEquals(element.getRelative1(this.ChamberOriginLoc), loc)) ||
					(Methods.LocationEquals(element.getRelative2(this.ChamberOriginLoc), loc))) {
				return element;
			}
		}
		return null;
	}

	public ArrayList<TestingElement> elements() {
		return this.target.TestingElements();
	}

	public boolean Pos1Empt() {
		return this.CuboidPos1 == null;
	}

	public boolean Pos2Empt() {
		return this.CuboidPos2 == null;
	}

	public Location getPos1() {
		return this.CuboidPos1;
	}

	public Location getPos2() {
		return this.CuboidPos2;
	}

	public Location getPointLoc() {
		if ((Pos1Empt()) && (Pos2Empt())) {
			return null;
		}
		if (Pos2Empt())
			return this.CuboidPos1;
		if (Pos1Empt())
			return this.CuboidPos2;
		if (Methods.LocationEquals(this.CuboidPos1, this.CuboidPos2)) {
			return this.CuboidPos1;
		}
		return null;
	}

	public BlockFace getsel() {
		return this.selectionFace;
	}

	public void clearPos() {
		this.CuboidPos1 = null;
		this.CuboidPos2 = null;
		this.selectionFace = null;
	}

	public void RClick() {
		List<Block> blocks = this.EditPlayer.getLastTwoTargetBlocks(null, 5);
		this.CuboidPos1 = ((Block) blocks.get(1)).getLocation();
		this.selectionFace = ((Block) blocks.get(1)).getFace((Block) blocks.get(0));
	}

	public void LClick() {
		List<Block> blocks = this.EditPlayer.getLastTwoTargetBlocks(null, 5);
		this.CuboidPos2 = ((Block) blocks.get(1)).getLocation();
		this.selectionFace = ((Block) blocks.get(1)).getFace((Block) blocks.get(0));
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
		if ((!f.exists()) || (!f.isDirectory())) {
			f.delete();
			f.mkdirs();
		}
	}

	public static ArrayList<File> getChambers() {
		ArrayList<File> file = new ArrayList<File>();
		File[] files = new File(PortalGun.TEST_CHAMBER_FILE_PATH).listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if ((f.isFile()) && (f.toString().endsWith(".chamber"))) {
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
		return (!Pos1Empt()) && (!Pos2Empt());
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\chambereditor\TestChamberEditor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */