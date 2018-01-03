package com.murabi10.portalgunreloaded.testingelement.fixture;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

import com.murabi10.portalgunreloaded.testingelement.ElementType;
import com.murabi10.portalgunreloaded.testingelement.TestingElement;

public class Speaker extends TestingElement {
	private transient boolean oldActivated = true;
	transient int i = 0;
	transient boolean activated = false;
	private String message = "";

	public Speaker(Location OriginLoc, int x, int y, int z) {
		super(OriginLoc, ElementType.SPEAKER, com.murabi10.portalgunreloaded.testingelement.LinkType.INPUT,
				BlockFace.SOUTH, x, y, z);
	}

	public boolean check() {
		return true;
	}

	protected void destroy() {
		getRelative1(this.OriginLocation).getBlock().setType(Material.AIR);
	}

	protected void Run() {
		if (isEditMode()) {
			this.activated = false;
		}

		if (this.i >= 3) {
			getRelative1(this.OriginLocation).getBlock().setType(isEditMode() ? Material.JUKEBOX : Material.AIR);

			setInput(true);
			if (Switches().size() != 0) {
				for (TestingElement e : Switches()) {
					if (!e.isOutput()) {
						setInput(false);
						break;
					}
				}
			}

			if ((isInput() != this.oldActivated) && (isInput())) {
				getTargetPlayer().sendMessage("録音メッセージ： " + this.message);

				if (!isEditMode()) {
					this.activated = true;
				}
			}

			this.oldActivated = isInput();
			this.i = 0;
		}
		this.i += 1;
	}

	public void setString(String message) {
		this.message = message;
	}

	public String getString() {
		return this.message;
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\fixture\Speaker.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */