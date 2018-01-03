/*    */ package com.murabi10.portalgunreloaded.portalevents;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.portalgun.Portal;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ public class PortalThroughEvent
/*    */   extends Event
/*    */ {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private Portal from;
/*    */   private Portal to;
/*    */   private Location dest;
/*    */   private Entity ent;
/* 18 */   private double v = 0.0D;
/*    */   
/*    */   public PortalThroughEvent(Entity ent, Portal from, Portal to, Location dest) {
/* 21 */     this.ent = ent;
/* 22 */     this.from = from;
/* 23 */     this.to = to;
/* 24 */     this.dest = dest;
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers()
/*    */   {
/* 29 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 33 */     return handlers;
/*    */   }
/*    */   
/*    */   public Entity getEntity() {
/* 37 */     return this.ent;
/*    */   }
/*    */   
/* 40 */   public Portal getFrom() { return this.from; }
/*    */   
/*    */   public Portal getTo() {
/* 43 */     return this.to;
/*    */   }
/*    */   
/*    */   public Location getDest() {
/* 47 */     return this.dest;
/*    */   }
/*    */   
/*    */   public void setDest(Location dest) {
/* 51 */     this.dest = dest;
/*    */   }
/*    */   
/*    */   public double getV() {
/* 55 */     return this.v;
/*    */   }
/*    */   
/*    */   public void setV(double v) {
/* 59 */     this.v = v;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalevents\PortalThroughEvent.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */