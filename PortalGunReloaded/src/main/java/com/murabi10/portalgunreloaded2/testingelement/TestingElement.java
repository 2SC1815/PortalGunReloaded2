package com.murabi10.portalgunreloaded2.testingelement;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.murabi10.portalgunreloaded2.Methods;

public abstract class TestingElement implements java.io.Serializable {
	protected transient Location OriginLocation;
	private ElementType type;
	private LinkType link;
	private BlockFace dir;
	private int X1;
	private int Y1;
	private int Z1;
	private int X2;
	private int Y2;
	private int Z2;
	private transient BukkitRunnable runnable = null;
	private transient Player targetPlayer = null;
	private transient boolean isEditMode = false;
	private transient boolean input = false;
	private transient boolean output = false;
	private ArrayList<TestingElement> switches = new ArrayList<TestingElement>();

	protected TestingElement(Location OriginLoc, ElementType type, LinkType linkType, BlockFace Dir, int x1, int y1,
			int z1, int x2, int y2, int z2) {
		setOriginLocation(OriginLoc);
		this.dir = Dir;
		this.type = type;
		this.link = linkType;
		this.X1 = x1;
		this.Y1 = y1;
		this.Z1 = z1;
		this.X2 = x2;
		this.Y2 = y2;
		this.Z2 = z2;
	}

	protected TestingElement(Location OriginLoc, ElementType type, LinkType linkType, BlockFace Dir, int x, int y,
			int z) {
		this(OriginLoc, type, linkType, Dir, x, y, z, x, y, z);
	}

	public void setOriginLocation(Location loc) {
		this.OriginLocation = loc;
		if (this.OriginLocation != null) {
			this.OriginLocation.setYaw(Methods.BlockFaceToYaw(this.dir));
			this.OriginLocation.setPitch(Methods.BlockFaceToPitch(this.dir));
		}
	}

	public void initRunnable() {
		if (this.runnable != null) {
			this.runnable.cancel();
			this.runnable = null;
		}

		this.runnable = new BukkitRunnable() {
			public void run() {
				if (TestingElement.this.isSuitable()) {
					TestingElement.this.Run();
				}

			}

		};
		this.runnable.runTaskTimer(com.murabi10.portalgunreloaded2.PortalGun.getPlugin(), 1L, 1L);
	}

	public ArrayList<TestingElement> Switches() {
		return this.switches;
	}

	private boolean isSuitable() {
		return this.OriginLocation != null;
	}

	public void disable() {
		if (this.runnable != null) {
			this.runnable.cancel();
			this.runnable = null;
		}
		destroy();
	}

	public abstract boolean check();

	protected abstract void destroy();

	protected abstract void Run();

	public ElementType getType() {
		return this.type;
	}

	public LinkType getLinkType() {
		return this.link;
	}

	protected BlockFace getDirection() {
		return this.dir;
	}

	public int getX1() {
		return this.X1;
	}

	public int getY1() {
		return this.Y1;
	}

	public int getZ1() {
		return this.Z1;
	}

	public int getX2() {
		return this.X2;
	}

	public int getY2() {
		return this.Y2;
	}

	public int getZ2() {
		return this.Z2;
	}

	public Location getRelative1(Location loc) {
		return loc.clone().add(this.X1, this.Y1, this.Z1);
	}

	public Location getRelative2(Location loc) {
		return loc.clone().add(this.X2, this.Y2, this.Z2);
	}

	public Player getTargetPlayer() {
		return this.targetPlayer;
	}

	public void setTargetPlayer(Player targetPlayer) {
		this.targetPlayer = targetPlayer;
	}

	public boolean isEditMode() {
		return this.isEditMode;
	}

	public void setEditMode(boolean isEditMode) {
		this.isEditMode = isEditMode;
	}

	public boolean isInput() {
		return this.input;
	}

	public void setInput(boolean input) {
		this.input = input;
	}

	public boolean isOutput() {
		return this.output;
	}

	public void setOutput(boolean output) {
		this.output = output;
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\TestingElement.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */