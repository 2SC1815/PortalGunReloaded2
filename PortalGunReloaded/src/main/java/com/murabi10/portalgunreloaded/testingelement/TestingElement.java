package com.murabi10.portalgunreloaded.testingelement;

import java.io.Serializable;
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.murabi10.portalgunreloaded.Methods;
import com.murabi10.portalgunreloaded.PortalGun;

public abstract class TestingElement implements Serializable {

	transient protected Location OriginLocation;
	private ElementType type;
	private LinkType link;
	private BlockFace dir;
	private int X1, Y1, Z1;
	private int X2, Y2, Z2;
	transient private BukkitRunnable runnable = null;
	transient private Player targetPlayer = null;
	transient private boolean isEditMode = false;
	transient private boolean activated = false;
	private ArrayList<TestingElement> switches = new ArrayList<TestingElement>();

	protected TestingElement(Location OriginLoc, ElementType type, LinkType linkType, BlockFace Dir, int x1, int y1, int z1, int x2,
			int y2, int z2) {
		this.setOriginLocation(OriginLoc);
		this.dir = Dir;
		this.type = type;
		this.link = linkType;
		this.X1 = x1;
		this.Y1 = y1;
		this.Z1 = z1;
		this.X2 = x2;
		this.Y2 = y2;
		this.Z2 = z2;

		//System.out.println(this.OriginLocation.toString() + " " + this.X1 + " " +  this.Y1 + " " + this.Z1+ " " + this.X2 + " " +  this.Y2 + " " + this.Z2 + "" + this.dir);

	}

	protected TestingElement(Location OriginLoc, ElementType type, LinkType linkType, BlockFace Dir, int x, int y, int z) {
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

			@Override
			public void run() {
				if (isSuitable()) {
					Run();
				}
			}

		};

		this.runnable.runTaskTimer(PortalGun.getPlugin(), 1, 1);
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


	/**
	 * 装置があるLocationは適しているかどうか：適していない＝falseを返したら相応の処置をする
	 * チェンバーエディターで編集されている間は1tickごとに呼ばれる
	 *
	 * @return 適していたらtrue
	 */
	public abstract boolean check();

	/**
	 * 装置をディセーブルする
	 */
	protected abstract void destroy();

	/**
	 * BukkitRunnableで回すメソッド
	 */
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

	// 1 : L , 2 : R

	public int getX1() {
		return X1;
	}

	public int getY1() {
		return Y1;
	}

	public int getZ1() {
		return Z1;
	}

	public int getX2() {
		return X2;
	}

	public int getY2() {
		return Y2;
	}

	public int getZ2() {
		return Z2;
	}

	public Location getRelative1(Location loc) {
		return loc.clone().add(X1, Y1, Z1);
	}

	public Location getRelative2(Location loc) {
		return loc.clone().add(X2, Y2, Z2);
	}

	public Player getTargetPlayer() {
		return targetPlayer;
	}

	public void setTargetPlayer(Player targetPlayer) {
		this.targetPlayer = targetPlayer;
	}

	public boolean isEditMode() {
		return isEditMode;
	}

	public void setEditMode(boolean isEditMode) {
		this.isEditMode = isEditMode;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

}
