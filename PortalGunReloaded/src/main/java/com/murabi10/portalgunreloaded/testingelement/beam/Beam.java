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
				recursiveLaser(portal.getDest().getRepresentativeLocation(), portal.getDest().getLaunchDirection()); // �ݡ����뤬���ä��餽�����̤äƤ���
				break;
			}

			BlockFace rtn = this.laser(currentLoc, bf);
			if (rtn == null) {
				return;
				// null���֤����餽���ǻߤޤ�
			} else if (!rtn.equals(bf)) {
				// null�ʳ����ʤ�Ǥ��������ʳ����֤����餽����������ž��
				recursiveLaser(currentLoc, rtn);
				break;
			}
			// �ʤ�Ǥ����������֤����鲿�⤷�ʤ�

			/*
			 *  171201: Thermal Discouragement Beam
			 * �λ����ѹ��ˤ�ꤳ�ν�����ȤäƳ��٤�ʤ���ɬ�פ��ʤ��ʤä����ʤ��ʤ餽�줬�������˶ʤ���褦�ˤʤä����顣��������������
			 *
			 */
		}
	}

	public abstract BlockFace laser(Location loc, BlockFace bf);

}
