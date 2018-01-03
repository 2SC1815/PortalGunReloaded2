/*    */ package com.murabi10.portalgunreloaded.testingelement.objects;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.UUID;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.ArmorStand;
/*    */ import org.bukkit.event.world.ChunkUnloadEvent;
/*    */ 
/*    */ public class GelManager implements org.bukkit.event.Listener
/*    */ {
/* 11 */   private static ArrayList<Gel> gels = new ArrayList();
/*    */   
/*    */   public static void add(Gel gel) {
/* 14 */     gels.add(gel);
/*    */   }
/*    */   
/*    */   protected static void remove(Gel gel) {
/* 18 */     gels.remove(gel);
/*    */   }
/*    */   
/*    */   public static void Disable() {
/* 22 */     ArrayList<Gel> queue = new ArrayList();
/* 23 */     for (Gel gel : gels) {
/* 24 */       queue.add(gel);
/*    */     }
/* 26 */     for (Gel gel : queue) {
/* 27 */       gel.Destroy();
/*    */     }
/*    */   }
/*    */   
/*    */   public static Gel getGel(UUID markerUUID) {
/* 32 */     for (Gel gel : gels) {
/* 33 */       if (gel.getMarker().getUniqueId().equals(markerUUID)) {
/* 34 */         return gel;
/*    */       }
/*    */     }
/* 37 */     return null;
/*    */   }
/*    */   
/*    */   public static ArrayList<Gel> getGels() {
/* 41 */     return gels;
/*    */   }
/*    */   
/*    */   @org.bukkit.event.EventHandler
/*    */   public void onUnloadChunk(ChunkUnloadEvent e) {
/* 46 */     for (Gel gel : gels) {
/* 47 */       if ((e.getChunk().equals(gel.getMarker().getLocation().getChunk())) || (e.getChunk().equals(gel.getGel().getLocation().getChunk()))) {
/* 48 */         e.setCancelled(true);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\objects\GelManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */