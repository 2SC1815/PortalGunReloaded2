package com.murabi10.portalgunreloaded;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.murabi10.portalgunreloaded.portalgun.PortalDevice;

public class Retire
		implements CommandExecutor {
	public Retire(PortalGun plugin) {
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if ((sender instanceof Player)) {
			PortalDevice d = PortalDevice.getDeviceInstance(((Player) sender).getUniqueId());
			if ((d != null) &&
					(d.getQueue() != null)) {
				d.getQueue().StopTest();
			}
		}

		return true;
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\Retire.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */