/*    */ package com.murabi10.portalgunreloaded.portalevents;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.testingelement.objects.Cube;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ public class CubeDestroyEvent
/*    */   extends Event
/*    */ {
/* 10 */   private static final HandlerList handlers = new HandlerList();
/*    */   private Cube cube;
/*    */   
/*    */   public CubeDestroyEvent(Cube cube)
/*    */   {
/* 15 */     this.cube = cube;
/*    */   }
/*    */   
/*    */   public Cube getCube() {
/* 19 */     return this.cube;
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers()
/*    */   {
/* 24 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 28 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalevents\CubeDestroyEvent.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */