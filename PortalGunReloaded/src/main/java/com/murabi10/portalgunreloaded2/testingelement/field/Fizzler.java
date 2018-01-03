package com.murabi10.portalgunreloaded2.testingelement.field;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.murabi10.portalgunreloaded2.Cuboid;
import com.murabi10.portalgunreloaded2.Methods;
import com.murabi10.portalgunreloaded2.portalgun.PortalDevice;
import com.murabi10.portalgunreloaded2.testingelement.ElementType;
import com.murabi10.portalgunreloaded2.testingelement.LinkType;
import com.murabi10.portalgunreloaded2.testingelement.TestingElement;
import com.murabi10.portalgunreloaded2.testingelement.objects.Cube;
import com.murabi10.portalgunreloaded2.testingelement.objects.CubeManager;

public class Fizzler extends TestingElement {
	public Fizzler(Location OriginLoc, int x1, int y1, int z1, int x2, int y2, int z2) {
		super(OriginLoc, ElementType.MATERIAL_EMANCIPATION_GRILL, LinkType.INPUT, BlockFace.SOUTH, x1, y1, z1, x2, y2,
				z2);
	}

	public boolean check() {
		return true;
	}

	private transient int i = 0;
	transient Random rnd = null;

	protected void destroy() {
	}

	protected void Run() {
		if (this.i >= 2) {

			getRelative1(this.OriginLocation).getBlock().setType(isEditMode() ? Material.IRON_BLOCK : Material.AIR);

			setInput(true);
			if (Switches().size() != 0) {
				for (TestingElement e : Switches()) {
					if (!e.isOutput()) {
						setInput(false);
						break;
					}
				}
			}

			if (isInput()) {
				if (this.rnd == null) {
					this.rnd = new Random();
				}

				for (Block b : new Cuboid(getRelative1(this.OriginLocation), getRelative2(this.OriginLocation))) {
					Location loc = b.getLocation();

					for (Entity ent : loc.getWorld().getNearbyEntities(loc, 2.0D, 2.0D, 2.0D)) {
						if (Methods.LocationEquals(ent.getLocation(), loc)) {
							Cube c = CubeManager.getCube(ent.getUniqueId());

							if (c != null) {
								c.Destroy(true);

							} else if ((ent instanceof Player)) {
								PortalDevice.getDeviceInstance(ent.getUniqueId()).clearPortal();
							}
						}
					}

					Methods.spawnParticle(
							loc.clone().add(this.rnd.nextDouble(), this.rnd.nextDouble(), this.rnd.nextDouble()),
							(byte) -71, (byte) -3, (byte) -3);
				}
			}

			this.i = 0;
		}
		this.i += 1;
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\field\Fizzler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */