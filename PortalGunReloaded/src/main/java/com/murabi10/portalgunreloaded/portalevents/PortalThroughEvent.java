package com.murabi10.portalgunreloaded.portalevents;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.murabi10.portalgunreloaded.portalgun.Portal;

public class PortalThroughEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	private Portal from;
	private Portal to;
	private Location dest;
	private Entity ent;
	private double v = 0.0;

	public PortalThroughEvent(Entity ent, Portal from, Portal to, Location dest) {
		this.ent = ent;
		this.from = from;
		this.to = to;
		this.dest = dest;
	}

	@Override
	public HandlerList getHandlers() {
        return handlers;
    }

	public static HandlerList getHandlerList() {
        return handlers;
    }

	public Entity getEntity() {
		return this.ent;
	}
	public Portal getFrom() {
		return this.from;
	}
	public Portal getTo() {
		return this.to;
	}

	public Location getDest() {
		return dest;
	}

	public void setDest(Location dest) {
		this.dest = dest;
	}

	public double getV() {
		return v;
	}

	public void setV(double v) {
		this.v = v;
	}
}
