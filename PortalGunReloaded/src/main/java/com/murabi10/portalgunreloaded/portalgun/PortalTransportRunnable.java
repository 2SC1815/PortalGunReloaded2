/*    */ package com.murabi10.portalgunreloaded.portalgun;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.Methods;
/*    */ import com.murabi10.portalgunreloaded.portalevents.PortalThroughEvent;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PortalTransportRunnable
/*    */   extends BukkitRunnable
/*    */ {
/*    */   public void run()
/*    */   {
/* 20 */     for (Player p : )
/*    */     {
/* 22 */       PortalDevice instance = PortalDevice.getDeviceInstance(p);
/* 23 */       Portal portal = Methods.getPortal(p.getLocation(), true);
/*    */       
/* 25 */       if (portal != null)
/*    */       {
/* 27 */         Portal dest = portal.getDest();
/* 28 */         if ((dest != null) && (!instance.getFlag()))
/*    */         {
/* 30 */           boolean isUPDOWN = (portal.getLaunchDirection().equals(BlockFace.UP)) || 
/* 31 */             (portal.getLaunchDirection().equals(BlockFace.DOWN));
/* 32 */           if ((isUPDOWN) || ((!isUPDOWN) && 
/* 33 */             (portal.getLaunchDirection().equals(Methods.YawToBlockFace(p.getEyeLocation().getYaw()).getOppositeFace()))))
/*    */           {
/*    */ 
/*    */ 
/* 37 */             Location loc = dest.getRepresentativeLocation();
/* 38 */             loc = Methods.BlockFaceFormat(dest.getLaunchDirection(), dest.getSubstitutionDirection(), loc);
/* 39 */             loc = Methods.LocationNormalize(loc);
/* 40 */             PortalThroughEvent event = new PortalThroughEvent(p, portal, dest, loc);
/* 41 */             Bukkit.getServer().getPluginManager().callEvent(event);
/*    */             
/* 43 */             p.teleport(event.getDest());
/*    */             
/* 45 */             p.setVelocity(Methods.BlockFaceToVector(dest.getLaunchDirection(), event.getV()));
/*    */             
/* 47 */             instance.setFlag(true);
/*    */           }
/*    */         }
/*    */       } else {
/* 51 */         instance.setFlag(false);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalgun\PortalTransportRunnable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */