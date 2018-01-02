package com.murabi10.portalgunreloaded.testingelement.beam;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;

import com.murabi10.portalgunreloaded.testingelement.ElementType;

public class ExcursionFunnel extends Beam {

	protected ExcursionFunnel(Location ChamberOrigin, ElementType type, BlockFace face, int X, int Y, int Z) {
		super(ChamberOrigin, type, face, X, Y, Z);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public BlockFace laser(Location loc, BlockFace bf) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean check() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void destroy() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
