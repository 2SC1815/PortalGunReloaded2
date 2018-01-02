package com.murabi10.portalgunreloaded.portalevents;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.murabi10.portalgunreloaded.portalgun.PortalColor;

public class PortalPreLaunchEvent extends Event implements Cancellable{

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;

    private Player player;
    private PortalColor c;

    public PortalPreLaunchEvent(Player launchPlayer, PortalColor color) {
    	this.player = launchPlayer;
    	this.c = color;
    }

    public Player getLaunchPlayer() {
    	return this.player;
    }

    public PortalColor getColor() {
    	return this.c;
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
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;

	}

}
