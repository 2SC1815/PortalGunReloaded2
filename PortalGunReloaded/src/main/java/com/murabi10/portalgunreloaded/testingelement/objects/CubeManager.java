/*    */ package com.murabi10.portalgunreloaded.testingelement.objects;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.UUID;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.ArmorStand;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.world.ChunkUnloadEvent;
/*    */ 
/*    */ public class CubeManager implements Listener
/*    */ {
/* 12 */   private static ArrayList<Cube> Cubes = new ArrayList();
/*    */   
/*    */   public static void add(Cube cube) {
/* 15 */     Cubes.add(cube);
/*    */   }
/*    */   
/*    */   protected static void remove(Cube cube) {
/* 19 */     Cubes.remove(cube);
/*    */   }
/*    */   
/*    */   public static void Disable() {
/* 23 */     ArrayList<Cube> queue = new ArrayList();
/* 24 */     for (Cube cube : Cubes) {
/* 25 */       queue.add(cube);
/*    */     }
/* 27 */     for (Cube cube : queue) {
/* 28 */       cube.Destroy(false);
/*    */     }
/*    */   }
/*    */   
/*    */   public static Cube getCube(UUID markerUUID) {
/* 33 */     for (Cube cube : Cubes) {
/* 34 */       if (cube.getMarker().getUniqueId().equals(markerUUID)) {
/* 35 */         return cube;
/*    */       }
/*    */     }
/* 38 */     return null;
/*    */   }
/*    */   
/*    */   public static ArrayList<Cube> getCubes() {
/* 42 */     return Cubes;
/*    */   }
/*    */   
/*    */   @org.bukkit.event.EventHandler
/*    */   public void onUnloadChunk(ChunkUnloadEvent e) {
/* 47 */     for (Cube cube : Cubes) {
/* 48 */       if ((e.getChunk().equals(cube.getMarker().getLocation().getChunk())) || (e.getChunk().equals(cube.getCube().getLocation().getChunk()))) {
/* 49 */         e.setCancelled(true);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\objects\CubeManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */