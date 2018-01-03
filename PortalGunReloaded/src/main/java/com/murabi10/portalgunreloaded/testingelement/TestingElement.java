/*     */ package com.murabi10.portalgunreloaded.testingelement;
/*     */ 
/*     */ import com.murabi10.portalgunreloaded.Methods;
/*     */ import java.util.ArrayList;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ 
/*     */ public abstract class TestingElement implements java.io.Serializable
/*     */ {
/*     */   protected transient Location OriginLocation;
/*     */   private ElementType type;
/*     */   private LinkType link;
/*     */   private BlockFace dir;
/*     */   private int X1;
/*     */   private int Y1;
/*     */   private int Z1;
/*     */   private int X2;
/*     */   private int Y2;
/*     */   private int Z2;
/*  22 */   private transient BukkitRunnable runnable = null;
/*  23 */   private transient Player targetPlayer = null;
/*  24 */   private transient boolean isEditMode = false;
/*  25 */   private transient boolean input = false;
/*  26 */   private transient boolean output = false;
/*  27 */   private ArrayList<TestingElement> switches = new ArrayList();
/*     */   
/*     */   protected TestingElement(Location OriginLoc, ElementType type, LinkType linkType, BlockFace Dir, int x1, int y1, int z1, int x2, int y2, int z2)
/*     */   {
/*  31 */     setOriginLocation(OriginLoc);
/*  32 */     this.dir = Dir;
/*  33 */     this.type = type;
/*  34 */     this.link = linkType;
/*  35 */     this.X1 = x1;
/*  36 */     this.Y1 = y1;
/*  37 */     this.Z1 = z1;
/*  38 */     this.X2 = x2;
/*  39 */     this.Y2 = y2;
/*  40 */     this.Z2 = z2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected TestingElement(Location OriginLoc, ElementType type, LinkType linkType, BlockFace Dir, int x, int y, int z)
/*     */   {
/*  47 */     this(OriginLoc, type, linkType, Dir, x, y, z, x, y, z);
/*     */   }
/*     */   
/*     */   public void setOriginLocation(Location loc) {
/*  51 */     this.OriginLocation = loc;
/*  52 */     if (this.OriginLocation != null) {
/*  53 */       this.OriginLocation.setYaw(Methods.BlockFaceToYaw(this.dir));
/*  54 */       this.OriginLocation.setPitch(Methods.BlockFaceToPitch(this.dir));
/*     */     }
/*     */   }
/*     */   
/*     */   public void initRunnable()
/*     */   {
/*  60 */     if (this.runnable != null) {
/*  61 */       this.runnable.cancel();
/*  62 */       this.runnable = null;
/*     */     }
/*     */     
/*  65 */     this.runnable = new BukkitRunnable()
/*     */     {
/*     */       public void run()
/*     */       {
/*  69 */         if (TestingElement.this.isSuitable()) {
/*  70 */           TestingElement.this.Run();
/*     */         }
/*     */         
/*     */       }
/*     */       
/*  75 */     };
/*  76 */     this.runnable.runTaskTimer(com.murabi10.portalgunreloaded.PortalGun.getPlugin(), 1L, 1L);
/*     */   }
/*     */   
/*     */   public ArrayList<TestingElement> Switches() {
/*  80 */     return this.switches;
/*     */   }
/*     */   
/*     */   private boolean isSuitable() {
/*  84 */     return this.OriginLocation != null;
/*     */   }
/*     */   
/*     */   public void disable()
/*     */   {
/*  89 */     if (this.runnable != null) {
/*  90 */       this.runnable.cancel();
/*  91 */       this.runnable = null;
/*     */     }
/*  93 */     destroy();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract boolean check();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract void destroy();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract void Run();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ElementType getType()
/*     */   {
/* 116 */     return this.type;
/*     */   }
/*     */   
/*     */   public LinkType getLinkType() {
/* 120 */     return this.link;
/*     */   }
/*     */   
/*     */   protected BlockFace getDirection() {
/* 124 */     return this.dir;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getX1()
/*     */   {
/* 130 */     return this.X1;
/*     */   }
/*     */   
/*     */   public int getY1() {
/* 134 */     return this.Y1;
/*     */   }
/*     */   
/*     */   public int getZ1() {
/* 138 */     return this.Z1;
/*     */   }
/*     */   
/*     */   public int getX2() {
/* 142 */     return this.X2;
/*     */   }
/*     */   
/*     */   public int getY2() {
/* 146 */     return this.Y2;
/*     */   }
/*     */   
/*     */   public int getZ2() {
/* 150 */     return this.Z2;
/*     */   }
/*     */   
/*     */   public Location getRelative1(Location loc) {
/* 154 */     return loc.clone().add(this.X1, this.Y1, this.Z1);
/*     */   }
/*     */   
/*     */   public Location getRelative2(Location loc) {
/* 158 */     return loc.clone().add(this.X2, this.Y2, this.Z2);
/*     */   }
/*     */   
/*     */   public Player getTargetPlayer() {
/* 162 */     return this.targetPlayer;
/*     */   }
/*     */   
/*     */   public void setTargetPlayer(Player targetPlayer) {
/* 166 */     this.targetPlayer = targetPlayer;
/*     */   }
/*     */   
/*     */   public boolean isEditMode() {
/* 170 */     return this.isEditMode;
/*     */   }
/*     */   
/*     */   public void setEditMode(boolean isEditMode) {
/* 174 */     this.isEditMode = isEditMode;
/*     */   }
/*     */   
/*     */   public boolean isInput() {
/* 178 */     return this.input;
/*     */   }
/*     */   
/*     */   public void setInput(boolean input) {
/* 182 */     this.input = input;
/*     */   }
/*     */   
/*     */   public boolean isOutput() {
/* 186 */     return this.output;
/*     */   }
/*     */   
/*     */   public void setOutput(boolean output) {
/* 190 */     this.output = output;
/*     */   }
/*     */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\TestingElement.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */