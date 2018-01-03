package com.murabi10.portalgunreloaded2.testchamber;

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
		return this.ChamberName;
	}

	public void setChamberName(String chamberName) {
		this.ChamberName = chamberName;
	}

	public String getDesignerName() {
		return this.DesignerName;
	}

	public void setDesignerName(String designerName) {
		this.DesignerName = designerName;
	}

	public ArrayList<String> Discreption() {
		return this.Discreption;
	}

	public Calendar getTime() {
		return this.time;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}

	public int getPopurality() {
		return this.Popurality;
	}

	public void setPopurality(int popurality) {
		this.Popurality = popurality;
	}

	public int getPlayed() {
		return this.played;
	}

	public void incrementPlayed() {
		this.played += 1;
	}

	public int getCleared() {
		return this.cleared;
	}

	public void incrementCleared() {
		this.cleared += 1;
	}

	public boolean isPublished() {
		return this.published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public GunType getPortalGunGive() {
		return this.portalGunGive;
	}

	public void setPortalGunGive(GunType portalGunGive) {
		this.portalGunGive = portalGunGive;
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testchamber\TestChamberData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */