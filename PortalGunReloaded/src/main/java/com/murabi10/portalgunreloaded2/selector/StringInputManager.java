/*    */ package com.murabi10.portalgunreloaded2.selector;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.murabi10.portalgunreloaded2.portalgun.PortalDevice;
/*    */ 
/*    */ public class StringInputManager
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onChat(AsyncPlayerChatEvent e)
/*    */   {
/* 14 */     PortalDevice d = PortalDevice.getDeviceInstance(e.getPlayer());
/*    */     
/* 16 */     if ((d != null) && (d.canStringInput())) {
/* 17 */       d.setInputString(e.getMessage());
/* 18 */       d.setStringInput(false);
/* 19 */       e.setCancelled(true);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\selector\StringInputManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */