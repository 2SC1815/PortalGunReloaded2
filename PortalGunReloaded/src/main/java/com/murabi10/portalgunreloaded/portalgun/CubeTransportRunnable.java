/*    */ package com.murabi10.portalgunreloaded.portalgun;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.Methods;
/*    */ import com.murabi10.portalgunreloaded.portalevents.PortalThroughEvent;
/*    */ import com.murabi10.portalgunreloaded.testingelement.objects.Cube;
/*    */ import com.murabi10.portalgunreloaded.testingelement.objects.CubeManager;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.entity.ArmorStand;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ 
/*    */ public class CubeTransportRunnable
/*    */   extends BukkitRunnable
/*    */ {
/*    */   public void run()
/*    */   {
/* 20 */     for (Cube c : )
/*    */     {
/* 22 */       if (c.getHanging() == null) {
/* 23 */         Portal portal = Methods.getPortal(c.getMarker().getLocation(), true);
/*    */         
/* 25 */         if (portal != null)
/*    */         {
/* 27 */           Portal dest = portal.getDest();
/* 28 */           if ((dest != null) && (!c.getFlag()))
/*    */           {
/* 30 */             Location loc = dest.getRepresentativeLocation();
/* 31 */             loc = Methods.BlockFaceFormat(dest.getLaunchDirection(), dest.getSubstitutionDirection(), loc);
/* 32 */             loc = Methods.LocationNormalize(loc);
/* 33 */             PortalThroughEvent event = new PortalThroughEvent(c.getMarker(), portal, dest, loc);
/* 34 */             Bukkit.getServer().getPluginManager().callEvent(event);
/*    */             
/* 36 */             c.tp(event.getDest());
/*    */             
/* 38 */             c.getMarker().setVelocity(Methods.BlockFaceToVector(dest.getLaunchDirection(), event.getV()));
/*    */             
/* 40 */             c.setFlag(true);
/*    */           }
/*    */         } else {
/* 43 */           c.setFlag(false);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalgun\CubeTransportRunnable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */