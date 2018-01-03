/*     */ package com.murabi10.portalgunreloaded.portalgun;
/*     */ 
/*     */ import com.murabi10.portalgunreloaded.Methods;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ 
/*     */ public class PortalFunctionRunnable
/*     */   extends BukkitRunnable
/*     */ {
/*  14 */   private int phase = 0;
/*     */   private PortalColor color;
/*     */   private Location loc;
/*     */   private BlockFace AD;
/*     */   private BlockFace PlayerD;
/*     */   private Portal p;
/*     */   
/*     */   public PortalFunctionRunnable(Portal portal)
/*     */   {
/*  23 */     this.p = portal;
/*  24 */     this.color = portal.getColor();
/*  25 */     this.loc = portal.getRepresentativeLocation();
/*  26 */     this.AD = portal.getLaunchDirection();
/*  27 */     this.PlayerD = portal.getSubstitutionDirection();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void run()
/*     */   {
/*  34 */     this.phase += 1;
/*  35 */     if (this.phase >= 360) {
/*  36 */       this.phase = 0;
/*     */     }
/*  38 */     CreatePortalParticle(this.loc, this.AD, this.color, this.PlayerD, this.phase);
/*     */     
/*     */ 
/*     */ 
/*  42 */     if (!Methods.isSuitable(this.p, false)) {
/*  43 */       PortalDevice.getDeviceInstance(this.p.getPlayer()).DestroyPortal(this.p.getColor());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private static void CreatePortalParticle(Location locate, BlockFace mode, PortalColor type, BlockFace bf, int phase)
/*     */   {
/*  50 */     Block block = locate.getBlock();
/*  51 */     Location loc = block.getRelative(mode.getOppositeFace()).getLocation();
/*  52 */     int i = phase;
/*  53 */     double x = 0.0D;double y = 0.0D;double z = 0.0D;
/*  54 */     double x2 = 0.0D;double y2 = 0.0D;double z2 = 0.0D;
/*  55 */     switch (mode) {
/*     */     case EAST_NORTH_EAST: 
/*  57 */       x = Trigonometrics.cos(i) / 2.0D + 0.5D;
/*  58 */       y = Trigonometrics.sin(i) + 1.0D;
/*  59 */       z = 1.1D;
/*     */       
/*  61 */       x2 = Trigonometrics.cos2(i) / 2.0D + 0.5D;
/*  62 */       y2 = Trigonometrics.sin2(i) + 1.0D;
/*  63 */       z2 = 1.1D;
/*  64 */       break;
/*     */     case EAST_SOUTH_EAST: 
/*  66 */       x = -0.1D;
/*  67 */       y = Trigonometrics.sin(i) + 1.0D;
/*  68 */       z = Trigonometrics.cos(i) / 2.0D + 0.5D;
/*     */       
/*  70 */       x2 = -0.1D;
/*  71 */       y2 = Trigonometrics.sin2(i) + 1.0D;
/*  72 */       z2 = Trigonometrics.cos2(i) / 2.0D + 0.5D;
/*  73 */       break;
/*     */     case DOWN: 
/*  75 */       x = Trigonometrics.cos(i) / 2.0D + 0.5D;
/*  76 */       y = Trigonometrics.sin(i) + 1.0D;
/*  77 */       z = -0.1D;
/*     */       
/*  79 */       x2 = Trigonometrics.cos2(i) / 2.0D + 0.5D;
/*  80 */       y2 = Trigonometrics.sin2(i) + 1.0D;
/*  81 */       z2 = -0.1D;
/*  82 */       break;
/*     */     case EAST: 
/*  84 */       x = 1.1D;
/*  85 */       y = Trigonometrics.sin(i) + 1.0D;
/*  86 */       z = Trigonometrics.cos(i) / 2.0D + 0.5D;
/*     */       
/*  88 */       x2 = 1.1D;
/*  89 */       y2 = Trigonometrics.sin2(i) + 1.0D;
/*  90 */       z2 = Trigonometrics.cos2(i) / 2.0D + 0.5D;
/*  91 */       break;
/*     */     case NORTH: 
/*  93 */       if ((bf.equals(BlockFace.SOUTH)) || (bf.equals(BlockFace.NORTH))) {
/*  94 */         x = Trigonometrics.cos(i) / 2.0D + 0.5D;
/*  95 */         y = 1.1D;
/*  96 */         if (bf.equals(BlockFace.SOUTH)) {
/*  97 */           z = Trigonometrics.sin(i) + 1.0D;
/*     */         } else
/*  99 */           z = Trigonometrics.sin(i);
/*     */       } else {
/* 101 */         if (bf.equals(BlockFace.EAST)) {
/* 102 */           x = Trigonometrics.sin(i) + 1.0D;
/*     */         } else
/* 104 */           x = Trigonometrics.sin(i);
/* 105 */         y = 1.1D;
/* 106 */         z = Trigonometrics.cos(i) / 2.0D + 0.5D;
/*     */       }
/*     */       
/* 109 */       if ((bf.equals(BlockFace.SOUTH)) || (bf.equals(BlockFace.NORTH))) {
/* 110 */         x2 = Trigonometrics.cos2(i) / 2.0D + 0.5D;
/* 111 */         y2 = 1.1D;
/* 112 */         if (bf.equals(BlockFace.SOUTH)) {
/* 113 */           z2 = Trigonometrics.sin2(i) + 1.0D;
/*     */         } else
/* 115 */           z2 = Trigonometrics.sin2(i);
/*     */       } else {
/* 117 */         if (bf.equals(BlockFace.EAST)) {
/* 118 */           x2 = Trigonometrics.sin2(i) + 1.0D;
/*     */         } else
/* 120 */           x2 = Trigonometrics.sin2(i);
/* 121 */         y2 = 1.1D;
/* 122 */         z2 = Trigonometrics.cos2(i) / 2.0D + 0.5D;
/*     */       }
/* 124 */       break;
/*     */     case NORTH_EAST: 
/* 126 */       if ((bf.equals(BlockFace.SOUTH)) || (bf.equals(BlockFace.NORTH))) {
/* 127 */         x = Trigonometrics.cos(i) / 2.0D + 0.5D;
/* 128 */         y = -0.1D;
/* 129 */         if (bf.equals(BlockFace.SOUTH)) {
/* 130 */           z = Trigonometrics.sin(i) + 1.0D;
/*     */         } else
/* 132 */           z = Trigonometrics.sin(i);
/*     */       } else {
/* 134 */         if (bf.equals(BlockFace.EAST)) {
/* 135 */           x = Trigonometrics.sin(i) + 1.0D;
/*     */         } else
/* 137 */           x = Trigonometrics.sin(i);
/* 138 */         y = -0.1D;
/* 139 */         z = Trigonometrics.cos(i) / 2.0D + 0.5D;
/*     */       }
/*     */       
/* 142 */       if ((bf.equals(BlockFace.SOUTH)) || (bf.equals(BlockFace.NORTH))) {
/* 143 */         x2 = Trigonometrics.cos2(i) / 2.0D + 0.5D;
/* 144 */         y2 = -0.1D;
/* 145 */         if (bf.equals(BlockFace.SOUTH)) {
/* 146 */           z2 = Trigonometrics.sin2(i) + 1.0D;
/*     */         } else
/* 148 */           z2 = Trigonometrics.sin2(i);
/*     */       } else {
/* 150 */         if (bf.equals(BlockFace.EAST)) {
/* 151 */           x2 = Trigonometrics.sin2(i) + 1.0D;
/*     */         } else
/* 153 */           x2 = Trigonometrics.sin2(i);
/* 154 */         y2 = -0.1D;
/* 155 */         z2 = Trigonometrics.cos2(i) / 2.0D + 0.5D;
/*     */       }
/* 157 */       break;
/*     */     }
/*     */     
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 164 */     if (type.equals(PortalColor.BLUE)) {
/* 165 */       block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x, loc.getY() + y, loc.getZ() + z, 0, 
/* 166 */         0.00392156862745098D, 0.25098039215686274D, 0.996078431372549D, 1.0D);
/*     */       
/* 168 */       block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x2, loc.getY() + y2, loc.getZ() + z2, 0, 
/* 169 */         0.00392156862745098D, 0.25098039215686274D, 0.996078431372549D, 1.0D);
/*     */       
/*     */ 
/* 172 */       block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x, loc.getY() + y, loc.getZ() + z, 0, 
/* 173 */         0.00392156862745098D, 0.25098039215686274D, 0.996078431372549D, 1.0D);
/*     */       
/* 175 */       block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x2, loc.getY() + y2, loc.getZ() + z2, 0, 
/* 176 */         0.00392156862745098D, 0.25098039215686274D, 0.996078431372549D, 1.0D);
/*     */     }
/*     */     else {
/* 179 */       block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x, loc.getY() + y, loc.getZ() + z, 0, 
/* 180 */         0.996078431372549D, 0.7058823529411765D, 0.11764705882352941D, 1.0D);
/*     */       
/* 182 */       block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x2, loc.getY() + y2, loc.getZ() + z2, 0, 
/* 183 */         0.996078431372549D, 0.7058823529411765D, 0.11764705882352941D, 1.0D);
/*     */       
/* 185 */       block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x, loc.getY() + y, loc.getZ() + z, 0, 
/* 186 */         0.996078431372549D, 0.7058823529411765D, 0.11764705882352941D, 1.0D);
/*     */       
/* 188 */       block.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + x2, loc.getY() + y2, loc.getZ() + z2, 0, 
/* 189 */         0.996078431372549D, 0.7058823529411765D, 0.11764705882352941D, 1.0D);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalgun\PortalFunctionRunnable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */