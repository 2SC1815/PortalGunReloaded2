package com.murabi10.portalgunreloaded2.testchamber;

import java.io.Serializable;
import java.util.ArrayList;

import org.bukkit.Location;

public class TestProcess implements Serializable {

	private ArrayList<String> sequence = new ArrayList<String>();
	private transient String filename = "";
	private String processName = "[Process Name Here]";
	private int doller = 0;
	private Location exit = null;

	public int getDoller() {
		return doller;
	}

	public void setDoller(int doller) {
		this.doller = doller;
	}

	public TestChamberData getTestChamber(int index) {
		return DataSystem.getChamberData(sequence.get(index));
	}

	public String getRegisteredName(int index) {
		return sequence.get(index);
	}

	public int getProcessSize() {
		return sequence.size();
	}

	public void addChamber(String data) {
		this.sequence.add(data);
	}

	public boolean addChamberIn(String data, int index) {
		try {
			this.sequence.add(index, data);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean removeChamber(int index) {
		return this.sequence.remove(index) != null;
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public Location getExit() {
		return exit;
	}

	public void setExit(Location exit) {
		this.exit = exit;
	}

}
