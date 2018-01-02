package com.murabi10.portalgunreloaded.portalevents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.murabi10.portalgunreloaded.portalgun.PortalColor;

public class PortalLaunchEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private PortalColor c;
    private boolean suc;

    public PortalLaunchEvent(Player launchPlayer, PortalColor color, boolean success) {
    	this.player = launchPlayer;
    	this.c = color;
    	this.suc = success;
    }

    public Player getLaunchPlayer() {
    	return this.player;
    }

    public PortalColor getColor() {
    	return this.c;
    }

    public boolean isSuccess() {
    	return this.suc;
    }

	@Override
	public HandlerList getHandlers() {
        return handlers;
    }

	public static HandlerList getHandlerList() {
        return handlers;
    }

}
