package com.murabi10.portalgunreloaded.testingelement.objects;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;

public class CubeManager implements Listener {

	private static ArrayList<Cube> Cubes = new ArrayList<Cube>();

	public static void add(Cube cube) {
		Cubes.add(cube);
	}

	protected static void remove(Cube cube) {
		Cubes.remove(cube);
	}

	public static void Disable() {
		ArrayList<Cube> queue = new ArrayList<Cube>();
		for (Cube cube : Cubes) {
			queue.add(cube);
		}
		for (Cube cube : queue) {
			cube.Destroy(false);
		}
	}

	public static Cube getCube(UUID markerUUID) {
		for (Cube cube : Cubes) {
			if (cube.getMarker().getUniqueId().equals(markerUUID)) {
				return cube;
			}
		}
		return null;
	}

	public static ArrayList<Cube> getCubes() {
		return Cubes;
	}

	@EventHandler
	public void onUnloadChunk(ChunkUnloadEvent e) {
		for (Cube cube : Cubes) {
			if (e.getChunk().equals(cube.getMarker().getLocation().getChunk()) || e.getChunk().equals(cube.getCube().getLocation().getChunk())) {
				e.setCancelled(true);
			}
		}
	}

}
