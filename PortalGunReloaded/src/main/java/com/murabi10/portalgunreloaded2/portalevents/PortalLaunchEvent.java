/*    */ package com.murabi10.portalgunreloaded2.portalevents;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;

import com.murabi10.portalgunreloaded2.portalgun.PortalColor;
/*    */ 
/*    */ public class PortalLaunchEvent
/*    */   extends Event
/*    */ {
/* 11 */   private static final HandlerList handlers = new HandlerList();
/*    */   private Player player;
/*    */   private PortalColor c;
/*    */   private boolean suc;
/*    */   
/*    */   public PortalLaunchEvent(Player launchPlayer, PortalColor color, boolean success)
/*    */   {
/* 18 */     this.player = launchPlayer;
/* 19 */     this.c = color;
/* 20 */     this.suc = success;
/*    */   }
/*    */   
/*    */   public Player getLaunchPlayer() {
/* 24 */     return this.player;
/*    */   }
/*    */   
/*    */   public PortalColor getColor() {
/* 28 */     return this.c;
/*    */   }
/*    */   
/*    */   public boolean isSuccess() {
/* 32 */     return this.suc;
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers()
/*    */   {
/* 37 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 41 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalevents\PortalLaunchEvent.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */