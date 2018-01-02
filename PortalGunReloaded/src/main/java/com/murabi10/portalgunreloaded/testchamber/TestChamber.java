package com.murabi10.portalgunreloaded.testchamber;

import java.io.Serializable;
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import com.murabi10.portalgunreloaded.BlockData;
import com.murabi10.portalgunreloaded.testingelement.TestingElement;

public class TestChamber implements Serializable {

	private ArrayList<TestingElement> elements = new ArrayList<TestingElement>();

	private Dimension Chamber = null;

	public TestChamber() {
		this.Chamber = Dimension.getStartingChamber();
	}

	public Dimension Chamber() {
		return this.Chamber;
	}

	public ArrayList<TestingElement> TestingElements() {
		return this.elements;
	}

	@SuppressWarnings("deprecation")
	public void placeToWorld(Location loc) {
		Location tmp;
		for (int x = 0; x < Chamber.getXSize(); x++) {
			for (int y = 0; y < Chamber.getYSize(); y++) {
				for (int z = 0; z < Chamber.getZSize(); z++) {
					tmp = loc.clone().add(x, y, z);
					BlockData b = Chamber.getBlock(x, y, z);
					tmp.getBlock().setType(b.getMaterial());
					tmp.getBlock().setData(b.GetData());
				}
			}
		}
	}

	public void deleteFromWorld(Location loc) {
		Location tmp;
		for (int x = 0; x < Chamber.getXSize(); x++) {
			for (int y = 0; y < Chamber.getYSize(); y++) {
				for (int z = 0; z < Chamber.getZSize(); z++) {
					tmp = loc.clone().add(x, y, z);
					tmp.getBlock().setType(Material.AIR);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void importFromWorld(Location loc) {
		Block tmp;
		for (int x = 0; x < Chamber.getXSize(); x++) {
			for (int y = 0; y < Chamber.getYSize(); y++) {
				for (int z = 0; z < Chamber.getZSize(); z++) {
					tmp = loc.clone().add(x, y, z).getBlock();
					Chamber.setBlock(x, y, z, new BlockData(tmp.getType(), tmp.getData()));
				}
			}
		}
	}

}
