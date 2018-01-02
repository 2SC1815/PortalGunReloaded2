package com.murabi10.portalgunreloaded.selector;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.murabi10.portalgunreloaded.portalgun.PortalDevice;

public class StringInputManager implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {

			PortalDevice d = PortalDevice.getDeviceInstance(e.getPlayer());

			if (d != null && d.canStringInput()) {
				d.setInputString(e.getMessage());
				d.setStringInput(false);
				e.setCancelled(true);
			}


	}

}
