package com.murabi10.portalgunreloaded2.chambereditor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.murabi10.portalgunreloaded2.PortalGun;

public class EditorEventListener implements org.bukkit.event.Listener {
	@EventHandler
	public void onPlayerClicks(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();

		player.getWorld().getName().equals(PortalGun.EditorWorldName);

		TestChamberEditor editor = TestChamberEditor.getEditor(player);

		if (editor != null) {
			if (action.equals(Action.LEFT_CLICK_BLOCK)) {
				System.out.println(action.toString());
				editor.LClick();
			}

			if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
				System.out.println(action.toString());
				editor.RClick();
			}
		}
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\chambereditor\EditorEventListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */