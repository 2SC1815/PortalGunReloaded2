package com.murabi10.portalgunreloaded.testingelement.beam;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;

import com.murabi10.portalgunreloaded.Methods;
import com.murabi10.portalgunreloaded.portalgun.Portal;
import com.murabi10.portalgunreloaded.testingelement.ElementType;
import com.murabi10.portalgunreloaded.testingelement.LinkType;
import com.murabi10.portalgunreloaded.testingelement.TestingElement;

public abstract class Beam extends TestingElement {

	private Location Origin;
	private BlockFace OriginFace;

	protected Beam(Location ChamberOrigin, ElementType type, BlockFace face, int X, int Y, int Z) {
		super(ChamberOrigin, type, LinkType.INPUT, face, X, Y, Z);
	}

	@Override
	protected void Run() {
		recursiveLaser(Origin, OriginFace);
	}

	private void recursiveLaser(Location loc, BlockFace bf) {
		for (double i = 0; i < 70; i += 0.2) {
			Location currentLoc = loc.getBlock().getRelative(bf, (int) i).getLocation();

			Portal portal = Methods.getPortal(currentLoc, true);

			if (portal != null && portal.getDest() != null) {
				recursiveLaser(portal.getDest().getRepresentativeLocation(), portal.getDest().getLaunchDirection()); // ポータルがあったらそこを通っていく
				break;
			}

			BlockFace rtn = this.laser(currentLoc, bf);
			if (rtn == null) {
				return;
				// nullを返したらそこで止まる
			} else if (!rtn.equals(bf)) {
				// null以外、進んでいる方向以外を返したらそこから方向転換
				recursiveLaser(currentLoc, rtn);
				break;
			}
			// 進んでいる方向を返したら何もしない

			/*
			 *  171201: Thermal Discouragement Beam
			 * の仕様変更によりこの処理を使って角度を曲げる必要がなくなった。なぜならそれが全方向に曲がるようになったから。うああああああ
			 *
			 */
		}
	}

	public abstract BlockFace laser(Location loc, BlockFace bf);

}
