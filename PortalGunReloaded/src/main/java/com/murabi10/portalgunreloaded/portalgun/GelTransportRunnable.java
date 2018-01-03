/*    */ package com.murabi10.portalgunreloaded.portalgun;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.Methods;
/*    */ import com.murabi10.portalgunreloaded.portalevents.PortalThroughEvent;
/*    */ import com.murabi10.portalgunreloaded.testingelement.objects.Gel;
/*    */ import com.murabi10.portalgunreloaded.testingelement.objects.GelManager;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.entity.ArmorStand;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ 
/*    */ public class GelTransportRunnable
/*    */   extends BukkitRunnable
/*    */ {
/*    */   public void run()
/*    */   {
/* 20 */     for (Gel c : )
/*    */     {
/* 22 */       Portal portal = Methods.getPortal(c.getMarker().getLocation(), true);
/*    */       
/* 24 */       if (portal != null)
/*    */       {
/* 26 */         Portal dest = portal.getDest();
/* 27 */         if ((dest != null) && (!c.getFlag()))
/*    */         {
/* 29 */           Location loc = dest.getRepresentativeLocation();
/* 30 */           loc = Methods.BlockFaceFormat(dest.getLaunchDirection(), dest.getSubstitutionDirection(), loc);
/* 31 */           loc = Methods.LocationNormalize(loc);
/* 32 */           PortalThroughEvent event = new PortalThroughEvent(c.getMarker(), portal, dest, loc);
/* 33 */           Bukkit.getServer().getPluginManager().callEvent(event);
/*    */           
/* 35 */           c.tp(event.getDest());
/*    */           
/* 37 */           c.getMarker().setVelocity(Methods.BlockFaceToVector(dest.getLaunchDirection(), event.getV()));
/*    */           
/* 39 */           c.setFlag(true);
/*    */         }
/*    */       } else {
/* 42 */         c.setFlag(false);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalgun\GelTransportRunnable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */