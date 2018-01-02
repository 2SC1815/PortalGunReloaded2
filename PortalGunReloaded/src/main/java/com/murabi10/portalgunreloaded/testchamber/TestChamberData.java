package com.murabi10.portalgunreloaded.testchamber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class TestChamberData implements Serializable {

	private transient String FileName;
	private String ChamberName = "[Test Chamber Name Here]";
	private String DesignerName = "[Designer Name Here]";
	private UUID PlayerUUID;
	private Calendar time;
	private ArrayList<String> Discreption = new ArrayList<String>();
	private int Popurality = 0;
	private int played = 0;
	private int cleared = 0;
	private boolean published = false;
	private GunType portalGunGive = GunType.DUAL_PORTAL_DEVICE;


	public TestChamberData(UUID uuid, String FileName, Calendar cal) {
		this.FileName = FileName;
		this.PlayerUUID = uuid;
		this.time = cal;
	}

	public void setFileName(String name) {
		this.FileName = name;
	}
	public String getFileName() {
		return this.FileName;
	}

	public UUID getUUID() {
		return this.PlayerUUID;
	}

	public String getChamberName() {
		return ChamberName;
	}

	public void setChamberName(String chamberName) {
		ChamberName = chamberName;
	}

	public String getDesignerName() {
		return DesignerName;
	}

	public void setDesignerName(String designerName) {
		DesignerName = designerName;
	}

	public ArrayList<String> Discreption() {
		return this.Discreption;
	}

	public Calendar getTime() {
		return time;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}

	public int getPopurality() {
		return Popurality;
	}

	public void setPopurality(int popurality) {
		Popurality = popurality;
	}

	public int getPlayed() {
		return played;
	}

	public void incrementPlayed() {
		this.played++;
	}

	public int getCleared() {
		return cleared;
	}

	public void incrementCleared() {
		this.cleared++;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public GunType getPortalGunGive() {
		return portalGunGive;
	}

	public void setPortalGunGive(GunType portalGunGive) {
		this.portalGunGive = portalGunGive;
	}

}
