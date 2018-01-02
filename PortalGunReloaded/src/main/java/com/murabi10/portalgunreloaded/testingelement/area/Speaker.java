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
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public boolean check() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	protected void destroy() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	protected void Run() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
