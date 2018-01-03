/*    */ package com.murabi10.portalgunreloaded.portalevents;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.portalgun.PortalColor;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ public class PortalPreLaunchEvent
/*    */   extends Event implements Cancellable
/*    */ {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/* 13 */   private boolean cancelled = false;
/*    */   private Player player;
/*    */   private PortalColor c;
/*    */   
/*    */   public PortalPreLaunchEvent(Player launchPlayer, PortalColor color)
/*    */   {
/* 19 */     this.player = launchPlayer;
/* 20 */     this.c = color;
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
/*    */   public HandlerList getHandlers()
/*    */   {
/* 33 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 37 */     return handlers;
/*    */   }
/*    */   
/*    */   public boolean isCancelled()
/*    */   {
/* 42 */     return this.cancelled;
/*    */   }
/*    */   
/*    */   public void setCancelled(boolean cancelled)
/*    */   {
/* 47 */     this.cancelled = cancelled;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalevents\PortalPreLaunchEvent.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */