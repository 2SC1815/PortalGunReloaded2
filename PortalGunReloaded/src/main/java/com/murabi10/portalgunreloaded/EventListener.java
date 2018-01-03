/*     */ package com.murabi10.portalgunreloaded;
/*     */ 
/*     */ import com.murabi10.portalgunreloaded.portalevents.PlayerChamberGoalEvent;
/*     */ import com.murabi10.portalgunreloaded.portalevents.PortalLandingEvent;
/*     */ import com.murabi10.portalgunreloaded.portalevents.PortalThroughEvent;
/*     */ import com.murabi10.portalgunreloaded.portalgun.Portal;
/*     */ import com.murabi10.portalgunreloaded.portalgun.PortalDevice;
/*     */ import com.murabi10.portalgunreloaded.testingelement.objects.Cube;
/*     */ import java.util.ArrayList;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*     */ import org.bukkit.event.player.PlayerJoinEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ 
/*     */ public class EventListener implements org.bukkit.event.Listener
/*     */ {
/*     */   @EventHandler
/*     */   public void logout(PlayerQuitEvent e)
/*     */   {
/*  26 */     PortalDevice.Disable(e.getPlayer());
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void login(PlayerJoinEvent e) {
/*  31 */     PortalDevice.AuthPlayer(e.getPlayer());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @EventHandler
/*     */   public void onGoal(PlayerChamberGoalEvent e) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @EventHandler
/*     */   public void onManipulate(PlayerArmorStandManipulateEvent e)
/*     */   {
/*  51 */     PortalDevice d = PortalDevice.getDeviceInstance(e.getPlayer());
/*  52 */     if ((d != null) && (d.getQueue() != null)) {
/*  53 */       e.setCancelled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onInteract(PlayerInteractEntityEvent e) {
/*  59 */     if ((e.getRightClicked().getType().equals(EntityType.ARMOR_STAND)) || 
/*  60 */       (e.getRightClicked().getType().equals(EntityType.ITEM_FRAME))) {
/*  61 */       PortalDevice d = PortalDevice.getDeviceInstance(e.getPlayer());
/*  62 */       if ((d != null) && (d.getQueue() != null)) {
/*  63 */         e.setCancelled(true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPortalTransport(PortalThroughEvent e)
/*     */   {
/*  71 */     if (e.getTo().getLaunchDirection().equals(BlockFace.DOWN)) {
/*  72 */       e.setDest(e.getDest().clone().add(0.0D, -1.0D, 0.0D));
/*     */     }
/*     */     
/*  75 */     boolean ignore = false;
/*     */     
/*  77 */     switch (e.getFrom().getLaunchDirection()) {
/*     */     case NORTH: 
/*     */     case NORTH_EAST: 
/*     */       break;
/*     */     default: 
/*  82 */       ignore = true;
/*     */     }
/*     */     
/*  85 */     Double vel = null;
/*     */     
/*  87 */     if (e.getEntity().getType().equals(EntityType.PLAYER)) {
/*  88 */       vel = Double.valueOf(PortalDevice.getDeviceInstance(e.getEntity().getUniqueId()).getVelocity(ignore));
/*  89 */       e.setV(vel.doubleValue());
/*  90 */     } else if (e.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
/*  91 */       Cube cube = com.murabi10.portalgunreloaded.testingelement.objects.CubeManager.getCube(e.getEntity().getUniqueId());
/*  92 */       if (cube != null) {
/*  93 */         vel = Double.valueOf(cube.getVelocity(ignore));
/*  94 */         e.setV(vel.doubleValue());
/*     */       }
/*     */     }
/*     */     
/*  98 */     if (vel != null) {
/*  99 */       Location tmp = e.getDest().clone().add(Methods.BlockFaceToVector(e.getTo().getLaunchDirection(), vel.doubleValue()));
/* 100 */       if (tmp.getBlock().isEmpty()) {
/* 101 */         e.setDest(tmp);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPortalLanding(PortalLandingEvent e) {
/* 108 */     if (!Methods.isSuitable(e.getPortal(), true)) {
/* 109 */       ArrayList<Location> newjudgment = new ArrayList();
/*     */       
/* 111 */       switch (e.getPortal().getLaunchDirection()) {
/*     */       case DOWN: 
/*     */       case EAST: 
/*     */       case EAST_NORTH_EAST: 
/*     */       case EAST_SOUTH_EAST: 
/*     */       default: 
/* 117 */         for (Location locs : e.getPortal().getLocations()) {
/* 118 */           newjudgment.add(locs.clone().add(0.0D, -1.0D, 0.0D));
/*     */         }
/* 120 */         e.getPortal().Replace(e.getPortal().getRepresentativeLocation().clone().add(0.0D, -1.0D, 0.0D), newjudgment);
/* 121 */         break;
/*     */       case NORTH: 
/*     */       case NORTH_EAST: 
/* 124 */         for (Location locs : e.getPortal().getLocations()) {
/* 125 */           newjudgment.add(locs.getBlock()
/* 126 */             .getRelative(e.getPortal().getSubstitutionDirection().getOppositeFace()).getLocation());
/*     */         }
/*     */         
/* 129 */         e.getPortal().Replace(e.getPortal().getRepresentativeLocation().getBlock()
/* 130 */           .getRelative(e.getPortal().getSubstitutionDirection().getOppositeFace()).getLocation(), 
/* 131 */           newjudgment);
/*     */       }
/*     */       
/*     */       
/* 135 */       if (!Methods.isSuitable(e.getPortal(), true)) {
/* 136 */         e.setCancelled(true);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\EventListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */