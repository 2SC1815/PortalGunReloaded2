package com.murabi10.portalgunreloaded;

import java.io.Serializable;

import org.bukkit.Material;

public class BlockData implements Serializable {

	private Material m;
	private byte data;

	public BlockData(Material mat, byte data) {
		this.m = mat;
		this.data = data;
	}

	public Material getMaterial() {
		return this.m;
	}

	public byte GetData() {
		return this.data;
	}
}
