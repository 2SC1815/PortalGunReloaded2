package com.murabi10.portalgunreloaded2;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Debug
		implements CommandExecutor {
	public Debug(PortalGun plugin) {
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		PortalGun.debug.openGUI((Player) sender);

		return true;
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\Debug.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */