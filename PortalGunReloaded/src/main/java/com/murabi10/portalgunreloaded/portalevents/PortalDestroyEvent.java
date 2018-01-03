/*    */ package com.murabi10.portalgunreloaded.portalevents;
/*    */ 
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ public class PortalDestroyEvent extends Event
/*    */ {
/*  8 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   public HandlerList getHandlers()
/*    */   {
/* 12 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 16 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalevents\PortalDestroyEvent.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */