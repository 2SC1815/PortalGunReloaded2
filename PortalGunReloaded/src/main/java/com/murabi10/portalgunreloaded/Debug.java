package com.murabi10.portalgunreloaded;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Debug implements CommandExecutor {

	public Debug(PortalGun plugin) {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		PortalGun.debug.openGUI((Player) sender);

		return true;
	}

}
