package com.murabi10.portalgunreloaded.testingelement.fixture;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import com.murabi10.portalgunreloaded.Methods;
import com.murabi10.portalgunreloaded.portalgun.Portal;
import com.murabi10.portalgunreloaded.testingelement.ElementType;
import com.murabi10.portalgunreloaded.testingelement.LinkType;
import com.murabi10.portalgunreloaded.testingelement.TestingElement;
import com.murabi10.portalgunreloaded.testingelement.objects.Cube;
import com.murabi10.portalgunreloaded.testingelement.objects.CubeManager;

public class ThermalDiscouragementBeam extends TestingElement {

	public ThermalDiscouragementBeam(Location ChamberOrigin, BlockFace face, int X, int Y, int Z) {
		//update(e, loc);
		super(ChamberOrigin, ElementType.THERMAL_DISCOURAGEMENT_BEAM, LinkType.INPUT, face, X, Y, Z);
	}

	@Override
	protected void Run() {
		Location loc = this.OriginLocation.clone();
		loc.add(getX1(), getY1(), getZ1());
		//System.out.println(loc.toString());
		recursiveLaser(loc, 200);
	}

	private void recursiveLaser(Location locd, int length) {
		for (double i = 0; i < 60; i += 0.3) {

			Location currentLoc = locd.clone().add(locd.getDirection().multiply(i));

			Portal portal = Methods.getPortal(currentLoc, true);

			if (portal != null && portal.getDest() != null) {
				// recursiveLaser(portal.getDest().getRepresentativeLocation(),
				// Methods.BlockFaceToYaw(portal.getDest().getLaunchDirection()));
				// // ポータルがあったらそこを通っていく
				break;// TODO
			}

			Float rtn;
			Location redirect = null;

			Location loc = currentLoc;

			if (!Methods.isTransparent(loc)) {
				rtn = null;
			} else {
				rtn = loc.getYaw();
			}

			if (rtn != null) {
				Methods.spawnParticle(loc.clone().add(0, 0.36, 0), (byte) 254, (byte) 45, (byte) 45);// TODO

				for (Entity ent : loc.getWorld().getNearbyEntities(loc, 0.5, 1, 0.5)) {
					if (ent.getType().equals(EntityType.ARMOR_STAND)
							&& Methods.LocationEquals(ent.getLocation(), loc)) {
						Cube cube = CubeManager.getCube(ent.getUniqueId());
						if (cube != null) {
							switch (cube.getCubeType()) {
							case REFLECTION:
								rtn = cube.getMarker().getEyeLocation().getYaw();
								redirect = cube.getMarker().getLocation();
								break;
							case COMPANION:
							case NORMAL:
								rtn = null;
								break;
							}
							break;
						}

					} else if (ent instanceof LivingEntity && Methods.LocationEquals(ent.getLocation(), loc)) {
						((LivingEntity) ent).damage(2);
					}
				}
			}

			// System.out.println(rtn);
			if (rtn == null) {
				return;
				// nullを返したらそこで止まる
			} else if (rtn != currentLoc.getYaw()) {
				// null以外、進んでいる方向以外を返したらそこから方向転換
				redirect.setYaw(rtn);
				recursiveLaser(redirect, length);
				break;
			}
			// 進んでいる方向を返したら何もしない

			length--;
			if (length <= 0) {
				return;
			}
		}
	}

	@Override
	public boolean check() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public void destroy() {

	}

}
