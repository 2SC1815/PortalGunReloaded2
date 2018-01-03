/*    */ package com.murabi10.portalgunreloaded2.portalevents;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ public class PlayerChamberGoalEvent extends Event
/*    */ {
/*  9 */   private static final HandlerList handlers = new HandlerList();
/*    */   private Player p;
/*    */   
/*    */   public PlayerChamberGoalEvent(Player p)
/*    */   {
/* 14 */     this.p = p;
/*    */   }
/*    */   
/*    */   public Player getPlayer() {
/* 18 */     return this.p;
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers()
/*    */   {
/* 23 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 27 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalevents\PlayerChamberGoalEvent.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */