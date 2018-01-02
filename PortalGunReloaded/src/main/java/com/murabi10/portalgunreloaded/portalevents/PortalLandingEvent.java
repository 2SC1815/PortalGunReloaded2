package com.murabi10.portalgunreloaded.portalevents;

import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.murabi10.portalgunreloaded.portalgun.Portal;

public class PortalLandingEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;

    private Location location;
    private Portal portal;

    public PortalLandingEvent(Portal p, Location loc) {
    	this.location = loc;
    	this.portal = p;
    }

	@Override
	public HandlerList getHandlers() {
        return handlers;
    }

	public static HandlerList getHandlerList() {
        return handlers;
    }

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}

	public Portal getPortal() {
		return portal;
	}

	public Location getLocation() {
		return location;
	}

}
