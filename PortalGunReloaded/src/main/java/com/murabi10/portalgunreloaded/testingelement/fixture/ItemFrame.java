package com.murabi10.portalgunreloaded.testingelement.fixture;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;

import com.murabi10.portalgunreloaded.testingelement.ElementType;
import com.murabi10.portalgunreloaded.testingelement.LinkType;
import com.murabi10.portalgunreloaded.testingelement.TestingElement;

public class ItemFrame extends TestingElement {

	protected ItemFrame(Location OriginLoc, ElementType type, BlockFace Dir, int x, int y, int z) {
		super(OriginLoc, type, LinkType.DNC, Dir, x, y, z);
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
