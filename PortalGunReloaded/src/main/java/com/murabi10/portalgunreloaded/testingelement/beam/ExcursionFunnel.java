package com.murabi10.portalgunreloaded.testingelement.beam;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;

import com.murabi10.portalgunreloaded.testingelement.ElementType;

public class ExcursionFunnel extends Beam {

	protected ExcursionFunnel(Location ChamberOrigin, ElementType type, BlockFace face, int X, int Y, int Z) {
		super(ChamberOrigin, type, face, X, Y, Z);
		// TODO ��ư�������줿���󥹥ȥ饯������������
	}

	@Override
	public BlockFace laser(Location loc, BlockFace bf) {
		// TODO ��ư�������줿�᥽�åɡ�������
		return null;
	}

	@Override
	public boolean check() {
		// TODO ��ư�������줿�᥽�åɡ�������
		return false;
	}

	@Override
	public void destroy() {
		// TODO ��ư�������줿�᥽�åɡ�������

	}

}
