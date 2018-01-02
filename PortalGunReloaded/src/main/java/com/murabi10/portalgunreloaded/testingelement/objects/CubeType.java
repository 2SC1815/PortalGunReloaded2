package com.murabi10.portalgunreloaded.testingelement.objects;

import org.bukkit.Material;

public enum CubeType {

	NORMAL(Material.REDSTONE_BLOCK),
	COMPANION(Material.EMERALD_BLOCK),
	REFLECTION(Material.DISPENSER),

	;
	private Material cube;

	private CubeType(Material material) {

		cube = material;

	}

	public Material getmaterial() {
		return this.cube;
	}

}
