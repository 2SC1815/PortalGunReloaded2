package com.murabi10.portalgunreloaded.portalevents;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.murabi10.portalgunreloaded.testingelement.objects.Cube;

public class CubeDestroyEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	private Cube cube;

	public CubeDestroyEvent(Cube cube) {
		this.cube = cube;
	}

	public Cube getCube() {
		return this.cube;
	}

	@Override
	public HandlerList getHandlers() {
        return handlers;
    }

	public static HandlerList getHandlerList() {
        return handlers;
    }

}
