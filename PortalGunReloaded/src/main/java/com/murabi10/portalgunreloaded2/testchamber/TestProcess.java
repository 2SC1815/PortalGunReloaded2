package com.murabi10.portalgunreloaded2.testchamber;

import java.io.Serializable;
import java.util.ArrayList;

public class TestProcess implements Serializable {

	private ArrayList<String> sequence = new ArrayList<String>();
	private transient String filename = "";
	private String processName = "[Process Name Here]";

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

}
