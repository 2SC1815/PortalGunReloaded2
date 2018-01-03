package com.murabi10.portalgunreloaded.testingelement.logicgate;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

import com.murabi10.portalgunreloaded.testingelement.ElementType;
import com.murabi10.portalgunreloaded.testingelement.TestingElement;

public class Timer extends TestingElement {
	private transient boolean oldActivated = true;
	private int timer = 3;
	transient BukkitRunnable timerRunnable = null;

	public Timer(Location OriginLoc, int x, int y, int z) {
		super(OriginLoc, ElementType.TIMER, com.murabi10.portalgunreloaded.testingelement.LinkType.OUT_IN,
				org.bukkit.block.BlockFace.SOUTH, x, y, z);
	}

	public boolean check() {
		return true;
	}

	protected void destroy() {
		getRelative1(this.OriginLocation).getBlock().setType(Material.AIR);
		this.timerRunnable.cancel();
	}

	protected void Run() {
		getRelative1(this.OriginLocation).getBlock().setType(isEditMode() ? Material.GOLD_BLOCK : Material.AIR);

		setInput(true);
		if (Switches().size() != 0) {
			for (TestingElement e : Switches()) {
				if (!e.isOutput()) {
					setInput(false);
					break;
				}
			}
		}

		if ((isInput() != this.oldActivated) && (isInput())) {
			if ((this.timerRunnable != null) && (!this.timerRunnable.isCancelled())) {
				this.timerRunnable.cancel();
			}

			final int time = this.timer;
			final Timer t = this;

			t.setOutput(true);

			this.timerRunnable = new BukkitRunnable() {
				int i = 0;

				public void run() {
					if (this.i > time) {
						t.setOutput(false);
						cancel();
						Timer.this.OriginLocation.getWorld().playSound(Timer.this.getTargetPlayer().getLocation(),
								Sound.BLOCK_NOTE_CHIME, 1.0F, 1.8F);
					} else {
						Timer.this.OriginLocation.getWorld().playSound(Timer.this.getTargetPlayer().getLocation(),
								Sound.BLOCK_NOTE_SNARE, 1.0F, 1.2F);
					}

					this.i += 1;

				}

			};
			this.timerRunnable.runTaskTimer(com.murabi10.portalgunreloaded.PortalGun.getPlugin(), 0L, 20L);
		}

		this.oldActivated = isInput();
	}

	public void setDelaySec(int delay) {
		this.timer = delay;
	}

	public int getDelaySec() {
		return this.timer;
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\logicgate\Timer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */