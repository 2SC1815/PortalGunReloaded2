package com.murabi10.portalgunreloaded.testingelement.fixture;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;

import com.murabi10.portalgunreloaded.testingelement.ElementType;
import com.murabi10.portalgunreloaded.testingelement.LinkType;
import com.murabi10.portalgunreloaded.testingelement.TestingElement;

public class ItemFrame extends TestingElement {

	protected ItemFrame(Location OriginLoc, ElementType type, BlockFace Dir, int x, int y, int z) {
		super(OriginLoc, type, LinkType.DNC, Dir, x, y, z);
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
