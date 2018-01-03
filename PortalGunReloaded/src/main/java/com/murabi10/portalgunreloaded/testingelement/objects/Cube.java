/*     */ package com.murabi10.portalgunreloaded.testingelement.objects;
/*     */ 
/*     */ import com.murabi10.portalgunreloaded.Methods;
/*     */ import com.murabi10.portalgunreloaded.PortalGun;
/*     */ import com.murabi10.portalgunreloaded.portalevents.CubeDestroyEvent;
/*     */ import com.murabi10.portalgunreloaded.portalgun.PortalDevice;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.ArmorStand;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class Cube
/*     */ {
/*     */   private CubeType type;
/*     */   private ArmorStand cube;
/*     */   private ArmorStand marker;
/*     */   private BukkitRunnable follow;
/*     */   private Player hangedBy;
/*     */   private boolean portal;
/*  27 */   private Vector[] vector = new Vector[12];
/*     */   
/*     */ 
/*     */   public Cube(Location loc, CubeType type)
/*     */   {
/*  32 */     this.cube = ((ArmorStand)loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND));
/*  33 */     this.marker = ((ArmorStand)loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND));
/*     */     
/*  35 */     this.cube.setVisible(false);
/*  36 */     this.marker.setVisible(false);
/*     */     
/*  38 */     this.cube.setGravity(false);
/*  39 */     this.marker.setGravity(true);
/*     */     
/*  41 */     this.type = type;
/*     */     
/*  43 */     this.cube.setHelmet(new ItemStack(this.type.getmaterial(), 1));
/*     */     
/*  45 */     this.follow = new BukkitRunnable()
/*     */     {
/*     */       public void run()
/*     */       {
/*  49 */         if ((Cube.this.cube.isDead()) || (Cube.this.marker.isDead())) {
/*  50 */           Cube.this.Destroy(true);
/*     */         } else {
/*  52 */           Location loc = Cube.this.marker.getLocation().clone().add(0.0D, -1.3D, 0.0D);
/*  53 */           Cube.this.cube.teleport(loc);
/*     */         }
/*  55 */         if (Cube.this.marker.getLocation().getBlock().isLiquid()) {
/*  56 */           Cube.this.Destroy(true);
/*     */         }
/*     */         
/*     */       }
/*     */       
/*  61 */     };
/*  62 */     this.follow.runTaskTimer(PortalGun.getPlugin(), 1L, 2L);
/*     */     
/*  64 */     flush();
/*     */     
/*  66 */     CubeManager.add(this);
/*     */   }
/*     */   
/*     */   public void tp(Location loc)
/*     */   {
/*  71 */     this.marker.teleport(loc);
/*  72 */     this.cube.teleport(loc);
/*     */   }
/*     */   
/*     */   public ArmorStand getMarker() {
/*  76 */     return this.marker;
/*     */   }
/*     */   
/*     */   public ArmorStand getCube() {
/*  80 */     return this.cube;
/*     */   }
/*     */   
/*     */   public CubeType getCubeType() {
/*  84 */     return this.type;
/*     */   }
/*     */   
/*     */   public void Destroy(boolean notify) {
/*  88 */     if (notify) {
/*  89 */       CubeDestroyEvent event = new CubeDestroyEvent(this);
/*  90 */       org.bukkit.Bukkit.getServer().getPluginManager().callEvent(event);
/*     */     }
/*  92 */     if (getMarker() != null) {
/*  93 */       Location explode = getMarker().getLocation();
/*  94 */       explode.getWorld().spawnParticle(org.bukkit.Particle.SMOKE_NORMAL, explode, 20);
/*  95 */       explode = null;
/*     */     }
/*  97 */     for (PortalDevice d : PortalDevice.getDevices()) {
/*  98 */       Cube c = d.getHangingCube();
/*  99 */       if ((c != null) && (c.equals(this))) {
/* 100 */         d.setHangingCube(null);
/*     */       }
/*     */     }
/* 103 */     CubeManager.remove(this);
/* 104 */     this.follow.cancel();
/* 105 */     this.follow = null;
/* 106 */     this.cube.remove();
/* 107 */     this.cube = null;
/* 108 */     this.marker.remove();
/* 109 */     this.marker = null;
/* 110 */     this.hangedBy = null;
/*     */   }
/*     */   
/*     */   public Player getHanging() {
/* 114 */     return this.hangedBy;
/*     */   }
/*     */   
/*     */   public void setHanging(Player p) {
/* 118 */     this.hangedBy = p;
/* 119 */     if (p != null) {
/* 120 */       this.marker.setGravity(false);
/*     */     } else {
/* 122 */       this.marker.setGravity(true);
/*     */     }
/*     */   }
/*     */   
/*     */   public double getVelocity(boolean ignoreVertical)
/*     */   {
/* 128 */     double max = 0.0D;
/* 129 */     for (int i = 0; i < 12; i++) {
/* 130 */       double tmp = Methods.VectorToAcc(this.vector[i], ignoreVertical);
/* 131 */       if (tmp > max)
/* 132 */         max = tmp;
/*     */     }
/* 134 */     return max;
/*     */   }
/*     */   
/*     */   public void setVelocity(Vector Velocity)
/*     */   {
/* 139 */     for (int i = 1; i < 12; i++) {
/* 140 */       this.vector[i] = this.vector[(i - 1)];
/*     */     }
/* 142 */     this.vector[0] = Velocity;
/*     */   }
/*     */   
/*     */   private void flush() {
/* 146 */     for (int i = 0; i < 12; i++) {
/* 147 */       this.vector[i] = new Vector(0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean getFlag() {
/* 152 */     return this.portal;
/*     */   }
/*     */   
/*     */   public void setFlag(boolean portal) {
/* 156 */     this.portal = portal;
/*     */   }
/*     */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\objects\Cube.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */