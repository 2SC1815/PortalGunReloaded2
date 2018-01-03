package com.murabi10.portalgunreloaded2.testingelement.objects;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.event.world.ChunkUnloadEvent;

public class GelManager implements org.bukkit.event.Listener {
	private static ArrayList<Gel> gels = new ArrayList<Gel>();

	public static void add(Gel gel) {
		gels.add(gel);
	}

	protected static void remove(Gel gel) {
		gels.remove(gel);
	}

	public static void Disable() {
		ArrayList<Gel> queue = new ArrayList<Gel>();
		for (Gel gel : gels) {
			queue.add(gel);
		}
		for (Gel gel : queue) {
			gel.Destroy();
		}
	}

	public static Gel getGel(UUID markerUUID) {
		for (Gel gel : gels) {
			if (gel.getMarker().getUniqueId().equals(markerUUID)) {
				return gel;
			}
		}
		return null;
	}

	public static ArrayList<Gel> getGels() {
		return gels;
	}

	@org.bukkit.event.EventHandler
	public void onUnloadChunk(ChunkUnloadEvent e) {
		for (Gel gel : gels) {
			if ((e.getChunk().equals(gel.getMarker().getLocation().getChunk()))
					|| (e.getChunk().equals(gel.getGel().getLocation().getChunk()))) {
				e.setCancelled(true);
			}
		}
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\objects\GelManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */