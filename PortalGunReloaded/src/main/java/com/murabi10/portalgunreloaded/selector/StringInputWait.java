package com.murabi10.portalgunreloaded.selector;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.murabi10.portalgunreloaded.PortalGun;
import com.murabi10.portalgunreloaded.chambereditor.EditorFunction;
import com.murabi10.portalgunreloaded.portalgun.PortalDevice;

public class StringInputWait extends BukkitRunnable {

	private PortalDevice d;
	private EditorFunction f;

	public StringInputWait(Player p, EditorFunction func) throws Exception {

		f = func;

		d = PortalDevice.getDeviceInstance(p);

		if (d.hasStrWait()) {
			d.getStrWait().cancel();
		}

		if (d != null) {
			d.setStringInput(true);
		} else {
			throw new Exception("player " + p.getName() + " has not have portaldevice instance!");
		}

		d.setStrWait(this);

		this.runTaskTimer(PortalGun.getPlugin(), 1, 1);

	}

	@Override
	public void run() {
		//System.out.println("hello");
		if (!d.isEmptyString()) {
			String str = d.getInputString();
			d.resetInputString();

			if (!f.reveive(str)) {
				this.cancel();
			} else {
				d.setStringInput(true);
			}
		}

	}


}
