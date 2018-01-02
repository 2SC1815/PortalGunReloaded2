package com.murabi10.portalgunreloaded.chambereditor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.murabi10.portalgunreloaded.PortalGun;

public class EditorEventListener implements Listener {

	@EventHandler
	public void onPlayerClicks(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
		// ItemStack item = event.getItem();

		//ItemStack item = player.getInventory().getItemInMainHand();
		if (player.getWorld().getName().equals(PortalGun.EditorWorldName) | true) { // TODO: Player World Judgement

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


}
