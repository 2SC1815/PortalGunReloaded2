package com.murabi10.portalgunreloaded.testingelement.fixture;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Rotation;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.inventory.ItemStack;

import com.murabi10.portalgunreloaded.testingelement.ElementType;
import com.murabi10.portalgunreloaded.testingelement.LinkType;
import com.murabi10.portalgunreloaded.testingelement.TestingElement;

public class ItemFrameElement extends TestingElement {
	private ArrayList<String> itemLore = new ArrayList<String>();
	private String itemName = "";
	private short data = 0;
	private Material m = null;
	private Rotation r = Rotation.NONE;

	private transient ItemFrame frame = null;

	public ItemFrameElement(Location OriginLoc, BlockFace Dir, int x, int y, int z) {
		super(OriginLoc, ElementType.ITEM_FRAME, LinkType.DNC, Dir, x, y, z);
	}

	public boolean check() {
		return (getRelative1(this.OriginLocation).getBlock().getType().isSolid()) &&
				(getRelative1(this.OriginLocation).getBlock().getRelative(getDirection()).isEmpty());
	}

	protected void destroy() {
		if (this.frame != null) {
			this.frame.setItem(new ItemStack(Material.AIR));
			this.frame.remove();
			this.frame = null;
		}
	}

	private transient int i = 0;

	protected void Run() {
		if (this.i >= 7) {
			if (this.frame == null) {
				this.frame = ((ItemFrame) this.OriginLocation.getWorld().spawnEntity(
						getRelative1(this.OriginLocation).getBlock().getRelative(getDirection()).getLocation(),
						EntityType.ITEM_FRAME));
				this.frame.setFacingDirection(getDirection());
				ItemStack i = new ItemStack(this.m != null ? this.m : Material.AIR);
				i.setDurability(this.data);
				if (!this.itemName.equals("")) {
					i.getItemMeta().setDisplayName(this.itemName);
				}
				if (!this.itemLore.isEmpty()) {
					i.getItemMeta().setLore(this.itemLore);
				}
				this.frame.setItem(i);
				this.frame.setRotation(this.r);
			}
			if (isEditMode()) {
				ItemStack item = this.frame.getItem();
				if (item != null) {
					this.m = item.getType();
					this.data = item.getDurability();
					if (item.hasItemMeta()) {
						if (item.getItemMeta().hasDisplayName()) {
							this.itemName = item.getItemMeta().getDisplayName();
						}
						if (item.getItemMeta().hasLore()) {
							this.itemLore = (ArrayList<String>) item.getItemMeta().getLore();
						}
					}
					this.r = this.frame.getRotation();
				}
			}
			this.i = 0;
		}
		this.i += 1;
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\fixture\ItemFrameElement.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */