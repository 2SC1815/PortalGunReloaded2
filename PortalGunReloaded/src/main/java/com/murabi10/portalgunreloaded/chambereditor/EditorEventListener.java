/*    */ package com.murabi10.portalgunreloaded.chambereditor;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.PortalGun;
/*    */ import java.io.PrintStream;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ public class EditorEventListener implements org.bukkit.event.Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onPlayerClicks(PlayerInteractEvent event)
/*    */   {
/* 15 */     Player player = event.getPlayer();
/* 16 */     Action action = event.getAction();
/*    */     
/*    */ 
/*    */ 
/* 20 */     player.getWorld().getName().equals(PortalGun.EditorWorldName);
/*    */     
/* 22 */     TestChamberEditor editor = TestChamberEditor.getEditor(player);
/*    */     
/* 24 */     if (editor != null)
/*    */     {
/* 26 */       if (action.equals(Action.LEFT_CLICK_BLOCK)) {
/* 27 */         System.out.println(action.toString());
/* 28 */         editor.LClick();
/*    */       }
/*    */       
/* 31 */       if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
/* 32 */         System.out.println(action.toString());
/* 33 */         editor.RClick();
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\chambereditor\EditorEventListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */