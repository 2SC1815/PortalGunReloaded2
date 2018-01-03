/*    */ package com.murabi10.portalgunreloaded.portalevents;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.portalgun.Portal;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ public class PortalLandingEvent
/*    */   extends Event implements Cancellable
/*    */ {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/* 13 */   private boolean cancelled = false;
/*    */   private Location location;
/*    */   private Portal portal;
/*    */   
/*    */   public PortalLandingEvent(Portal p, Location loc)
/*    */   {
/* 19 */     this.location = loc;
/* 20 */     this.portal = p;
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers()
/*    */   {
/* 25 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 29 */     return handlers;
/*    */   }
/*    */   
/*    */   public boolean isCancelled()
/*    */   {
/* 34 */     return this.cancelled;
/*    */   }
/*    */   
/*    */   public void setCancelled(boolean cancel)
/*    */   {
/* 39 */     this.cancelled = cancel;
/*    */   }
/*    */   
/*    */   public Portal getPortal() {
/* 43 */     return this.portal;
/*    */   }
/*    */   
/*    */   public Location getLocation() {
/* 47 */     return this.location;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalevents\PortalLandingEvent.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */