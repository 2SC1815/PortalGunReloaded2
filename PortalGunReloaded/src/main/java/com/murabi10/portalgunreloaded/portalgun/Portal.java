/*    */ package com.murabi10.portalgunreloaded.portalgun;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.PortalGun;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Portal
/*    */ {
/*    */   private Player creator;
/*    */   private PortalColor color;
/* 16 */   private ArrayList<Location> judgmentLocs = new ArrayList();
/*    */   private BlockFace launchDirection;
/*    */   private BlockFace substitutionDirection;
/*    */   private Location representativeLoc;
/* 20 */   private Portal dest = null;
/*    */   private PortalFunctionRunnable particleRunnable;
/*    */   
/*    */   public Portal(Player p, PortalColor c, BlockFace launchD, BlockFace substitutionD, Location exit, Location... judgmentLocs) {
/* 24 */     this.creator = p;
/* 25 */     this.color = c;
/* 26 */     for (int i = 0; i < judgmentLocs.length; i++) {
/* 27 */       this.judgmentLocs.add(judgmentLocs[i]);
/*    */     }
/* 29 */     this.representativeLoc = exit;
/* 30 */     this.launchDirection = launchD;
/* 31 */     this.substitutionDirection = substitutionD;
/*    */     
/*    */ 
/*    */ 
/* 35 */     this.particleRunnable = new PortalFunctionRunnable(this);
/* 36 */     this.particleRunnable.runTaskTimer(PortalGun.getPlugin(), 3L, 1L);
/*    */   }
/*    */   
/*    */ 
/*    */   public void Replace(Location exit, ArrayList<Location> judgmentLocs)
/*    */   {
/* 42 */     stopParticle();
/*    */     
/* 44 */     this.judgmentLocs = judgmentLocs;
/* 45 */     this.representativeLoc = exit;
/*    */     
/* 47 */     this.particleRunnable = new PortalFunctionRunnable(this);
/* 48 */     this.particleRunnable.runTaskTimer(PortalGun.getPlugin(), 3L, 1L);
/*    */   }
/*    */   
/*    */   public void stopParticle() {
/* 52 */     this.particleRunnable.cancel();
/* 53 */     this.particleRunnable = null;
/*    */   }
/*    */   
/*    */   public Player getPlayer() {
/* 57 */     return this.creator;
/*    */   }
/*    */   
/*    */   public PortalColor getColor() {
/* 61 */     return this.color;
/*    */   }
/*    */   
/*    */   public ArrayList<Location> getLocations() {
/* 65 */     return this.judgmentLocs;
/*    */   }
/*    */   
/*    */   public BlockFace getLaunchDirection() {
/* 69 */     return this.launchDirection;
/*    */   }
/*    */   
/*    */   public BlockFace getSubstitutionDirection() {
/* 73 */     return this.substitutionDirection;
/*    */   }
/*    */   
/*    */   public Location getRepresentativeLocation() {
/* 77 */     return this.representativeLoc;
/*    */   }
/*    */   
/*    */   public Portal getDest() {
/* 81 */     return this.dest;
/*    */   }
/*    */   
/*    */   public void setDest(Portal dest) {
/* 85 */     this.dest = dest;
/*    */   }
/*    */   
/*    */   public boolean isClosed() {
/* 89 */     return this.dest == null;
/*    */   }
/*    */   
/*    */   public BukkitRunnable getParticleRunnable() {
/* 93 */     return this.particleRunnable;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalgun\Portal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */