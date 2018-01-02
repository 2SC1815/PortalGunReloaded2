package com.murabi10.portalgunreloaded;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.murabi10.portalgunreloaded.portalgun.PortalDevice;

public class Retire implements CommandExecutor {

	public Retire(PortalGun plugin) {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			PortalDevice d = PortalDevice.getDeviceInstance(((Player) sender).getUniqueId());
			if (d != null) {
				if (d.getQueue() != null) {
					d.getQueue().StopTest();
				}
			}
		}
		return true;
	}

}
