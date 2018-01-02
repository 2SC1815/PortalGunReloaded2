package com.murabi10.portalgunreloaded.testingelement.logicgate;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;

import com.murabi10.portalgunreloaded.testingelement.ElementType;
import com.murabi10.portalgunreloaded.testingelement.LinkType;
import com.murabi10.portalgunreloaded.testingelement.TestingElement;

public class OR extends TestingElement {

	protected OR(Location OriginLoc, int x, int y, int z) {
		super(OriginLoc, ElementType.OR, LinkType.OUT_IN, BlockFace.SOUTH, x, y, z);
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
