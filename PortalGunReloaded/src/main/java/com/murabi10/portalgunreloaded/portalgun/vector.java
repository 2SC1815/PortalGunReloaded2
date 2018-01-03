/*    */ package com.murabi10.portalgunreloaded.portalgun;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.testingelement.objects.Cube;
/*    */ import com.murabi10.portalgunreloaded.testingelement.objects.CubeManager;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.ArmorStand;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ public class vector extends BukkitRunnable
/*    */ {
/*    */   public void run()
/*    */   {
/* 14 */     for (Player p : )
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 21 */       PortalDevice.getDeviceInstance(p).setVelocity(p.getVelocity());
/*    */     }
/*    */     
/* 24 */     for (Cube c : CubeManager.getCubes()) {
/* 25 */       c.setVelocity(c.getMarker().getVelocity());
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalgun\vector.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */