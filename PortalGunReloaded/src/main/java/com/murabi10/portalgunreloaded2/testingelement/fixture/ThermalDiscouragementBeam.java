package com.murabi10.portalgunreloaded2.testingelement.fixture;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import com.murabi10.portalgunreloaded2.Methods;
import com.murabi10.portalgunreloaded2.portalgun.Portal;
import com.murabi10.portalgunreloaded2.testingelement.ElementType;
import com.murabi10.portalgunreloaded2.testingelement.TestingElement;
import com.murabi10.portalgunreloaded2.testingelement.objects.Cube;
import com.murabi10.portalgunreloaded2.testingelement.objects.CubeManager;

public class ThermalDiscouragementBeam extends TestingElement {
	public ThermalDiscouragementBeam(Location ChamberOrigin, BlockFace face, int X, int Y, int Z) {
		super(ChamberOrigin, ElementType.THERMAL_DISCOURAGEMENT_BEAM,
				com.murabi10.portalgunreloaded2.testingelement.LinkType.INPUT, face, X, Y, Z);
	}

	protected void Run() {
		Location loc = this.OriginLocation.clone();
		loc.add(getX1(), getY1(), getZ1());

		recursiveLaser(loc, 200);
	}

	private void recursiveLaser(Location locd, int length) {
		for (double i = 0.0D; i < 60.0D; i += 0.3D) {
			Location currentLoc = locd.clone().add(locd.getDirection().multiply(i));

			Portal portal = Methods.getPortal(currentLoc, true);

			if ((portal != null) && (portal.getDest() != null)) {
				break;
			}

			Location redirect = null;

			Location loc = currentLoc;
			Float rtn;
			if (!Methods.isTransparent(loc)) {
				rtn = null;
			} else {
				rtn = Float.valueOf(loc.getYaw());
			}

			if (rtn != null) {
				Methods.spawnParticle(loc.clone().add(0.0D, 0.36D, 0.0D), (byte) -2, (byte) 45, (byte) 45);

				for (Entity ent : loc.getWorld().getNearbyEntities(loc, 0.5D, 1.0D, 0.5D)) {
					Cube cube;
					if ((ent.getType().equals(EntityType.ARMOR_STAND)) &&
							(Methods.LocationEquals(ent.getLocation(), loc))) {
						cube = CubeManager.getCube(ent.getUniqueId());
						if (cube != null) {
							switch (cube.getCubeType()) {
							case REFLECTION:
								rtn = Float.valueOf(cube.getMarker().getEyeLocation().getYaw());
								redirect = cube.getMarker().getLocation();
								break;
							case COMPANION:
							case NORMAL:
								rtn = null;
							default:
								break;

							}

						}
						if (((ent instanceof LivingEntity))
								&& (Methods.LocationEquals(ent.getLocation(), loc))) {
							((LivingEntity) ent).damage(2.0D);
							break;
						}
					}

				}
			}
			if (rtn == null) {
				return;
			}
			if (rtn.floatValue() != currentLoc.getYaw()) {
				redirect.setYaw(rtn.floatValue());
				recursiveLaser(redirect, length);
				break;
			}

			length--;
			if (length <= 0) {
				return;
			}
		}
	}

	public boolean check() {
		return true;
	}

	public void destroy() {
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\fixture\ThermalDiscouragementBeam.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */