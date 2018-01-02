package com.murabi10.portalgunreloaded.selector;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.murabi10.portalgunreloaded.PortalGun;
import com.murabi10.portalgunreloaded.portalgun.PortalDevice;

public class ItemClickWait extends BukkitRunnable {

	private PortalDevice d;
	private ClickFunction f;

	public ItemClickWait(Player p, ClickFunction func) throws Exception {

		f = func;

		d = PortalDevice.getDeviceInstance(p);

		if (d.hasItemwait()) {
			d.getItemwait().cancel();
		}

		if (d.hasStrWait()) {
			d.getStrWait().cancel();
		}

		if (d != null) {
			d.setItemInput(true);
		} else {
			throw new Exception("player " + p.getName() + " has not have portaldevice instance!");
		}

		d.setItemwait(this);

		this.runTaskTimer(PortalGun.getPlugin(), 1, 1);

	}

	@Override
	public void run() {
		//System.out.println("hello");
		if (!d.isEmptyItem()) {
			ItemStack item = d.getInputItem();
			d.resetInputItem();

			if (!f.click(d.getOwner(), item)) { // return false -> close return true -> continue
				this.cancel();
			} else {
				d.setItemInput(true);
			}
		}

	}


}
