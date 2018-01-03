/*     */ package com.murabi10.portalgunreloaded.testingelement.objects;
/*     */ 
/*     */ import com.murabi10.portalgunreloaded.Methods;
/*     */ import com.murabi10.portalgunreloaded.PortalGun;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.ArmorStand;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ 
/*     */ public class Gel
/*     */ {
/*     */   private GelType type;
/*     */   private ArmorStand gel;
/*     */   private ArmorStand marker;
/*     */   private BukkitRunnable follow;
/*     */   private boolean portal;
/*  22 */   private Vector[] vector = new Vector[12];
/*     */   
/*     */ 
/*     */   public Gel(Location loc, GelType type)
/*     */   {
/*  27 */     this.gel = ((ArmorStand)loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND));
/*  28 */     this.marker = ((ArmorStand)loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND));
/*     */     
/*  30 */     this.gel.setVisible(false);
/*  31 */     this.marker.setVisible(false);
/*     */     
/*  33 */     this.gel.setGravity(false);
/*  34 */     this.marker.setGravity(true);
/*     */     
/*  36 */     this.type = type;
/*     */     
/*  38 */     this.gel.setHelmet(new ItemStack(this.type.getmaterial(), 1));
/*     */     
/*  40 */     this.follow = new BukkitRunnable()
/*     */     {
/*     */       public void run()
/*     */       {
/*  44 */         if ((Gel.this.gel.isDead()) || (Gel.this.marker.isDead())) {
/*  45 */           Gel.this.Destroy();
/*     */         } else {
/*  47 */           Location loc = Gel.this.marker.getLocation().clone().add(0.0D, -1.3D, 0.0D);
/*  48 */           Gel.this.gel.teleport(loc);
/*     */         }
/*  50 */         if (Gel.this.marker.getLocation().getBlock().isLiquid()) {
/*  51 */           Gel.this.Destroy();
/*     */ 
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */ 
/*  58 */     };
/*  59 */     this.follow.runTaskTimer(PortalGun.getPlugin(), 1L, 2L);
/*     */     
/*  61 */     flush();
/*     */     
/*  63 */     GelManager.add(this);
/*     */   }
/*     */   
/*     */   public void tp(Location loc)
/*     */   {
/*  68 */     this.marker.teleport(loc);
/*  69 */     this.gel.teleport(loc);
/*     */   }
/*     */   
/*     */   public ArmorStand getMarker() {
/*  73 */     return this.marker;
/*     */   }
/*     */   
/*     */   public ArmorStand getGel() {
/*  77 */     return this.gel;
/*     */   }
/*     */   
/*     */   public GelType getGelType() {
/*  81 */     return this.type;
/*     */   }
/*     */   
/*     */   public void Destroy() {
/*  85 */     GelManager.remove(this);
/*  86 */     this.follow.cancel();
/*  87 */     this.follow = null;
/*  88 */     this.gel.remove();
/*  89 */     this.gel = null;
/*  90 */     this.marker.remove();
/*  91 */     this.marker = null;
/*     */   }
/*     */   
/*     */   public double getVelocity(boolean ignoreVertical)
/*     */   {
/*  96 */     double max = 0.0D;
/*  97 */     for (int i = 0; i < 12; i++) {
/*  98 */       double tmp = Methods.VectorToAcc(this.vector[i], ignoreVertical);
/*  99 */       if (tmp > max)
/* 100 */         max = tmp;
/*     */     }
/* 102 */     return max;
/*     */   }
/*     */   
/*     */   public void setVelocity(Vector Velocity)
/*     */   {
/* 107 */     for (int i = 1; i < 12; i++) {
/* 108 */       this.vector[i] = this.vector[(i - 1)];
/*     */     }
/* 110 */     this.vector[0] = Velocity;
/*     */   }
/*     */   
/*     */   private void flush() {
/* 114 */     for (int i = 0; i < 12; i++) {
/* 115 */       this.vector[i] = new Vector(0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean getFlag() {
/* 120 */     return this.portal;
/*     */   }
/*     */   
/*     */   public void setFlag(boolean portal) {
/* 124 */     this.portal = portal;
/*     */   }
/*     */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\objects\Gel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */