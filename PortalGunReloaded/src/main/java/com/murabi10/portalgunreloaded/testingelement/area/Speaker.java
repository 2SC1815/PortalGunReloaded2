package com.murabi10.portalgunreloaded.testingelement.area;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;

import com.murabi10.portalgunreloaded.testingelement.ElementType;
import com.murabi10.portalgunreloaded.testingelement.LinkType;
import com.murabi10.portalgunreloaded.testingelement.TestingElement;

public class Speaker extends TestingElement {

	protected Speaker(Location OriginLoc, ElementType type, BlockFace Dir, int x1, int y1, int z1, int x2, int y2,
			int z2) {
		super(OriginLoc, type, LinkType.INPUT, Dir, x1, y1, z1, x2, y2, z2);
		// TODO ��ư�������줿���󥹥ȥ饯������������
	}

	@Override
	public boolean check() {
		// TODO ��ư�������줿�᥽�åɡ�������
		return false;
	}

	@Override
	protected void destroy() {
		// TODO ��ư�������줿�᥽�åɡ�������

	}

	@Override
	protected void Run() {
		// TODO ��ư�������줿�᥽�åɡ�������

	}

}
