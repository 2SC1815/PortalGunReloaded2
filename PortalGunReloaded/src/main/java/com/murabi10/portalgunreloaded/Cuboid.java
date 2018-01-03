/*     */ package com.murabi10.portalgunreloaded;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class Cuboid implements Cloneable, ConfigurationSerializable, Iterable<Block>
/*     */ {
/*     */   protected String worldName;
/*     */   protected final Vector minimumPoint;
/*     */   protected final Vector maximumPoint;
/*     */   
/*     */   public Cuboid(Cuboid cuboid)
/*     */   {
/*  23 */     this(cuboid.worldName, cuboid.minimumPoint.getX(), cuboid.minimumPoint.getY(), cuboid.minimumPoint.getZ(), cuboid.maximumPoint.getX(), cuboid.maximumPoint.getY(), cuboid.maximumPoint.getZ());
/*     */   }
/*     */   
/*     */   public Cuboid(Location loc) {
/*  27 */     this(loc, loc);
/*     */   }
/*     */   
/*     */   public Cuboid(Location loc1, Location loc2) {
/*  31 */     if ((loc1 != null) && (loc2 != null)) {
/*  32 */       if ((loc1.getWorld() != null) && (loc2.getWorld() != null)) {
/*  33 */         if (!loc1.getWorld().getUID().equals(loc2.getWorld().getUID()))
/*  34 */           throw new IllegalStateException("The 2 locations of the cuboid must be in the same world!");
/*     */       } else {
/*  36 */         throw new NullPointerException("One/both of the worlds is/are null!");
/*     */       }
/*  38 */       this.worldName = loc1.getWorld().getName();
/*     */       
/*  40 */       double xPos1 = Math.min(loc1.getX(), loc2.getX());
/*  41 */       double yPos1 = Math.min(loc1.getY(), loc2.getY());
/*  42 */       double zPos1 = Math.min(loc1.getZ(), loc2.getZ());
/*  43 */       double xPos2 = Math.max(loc1.getX(), loc2.getX());
/*  44 */       double yPos2 = Math.max(loc1.getY(), loc2.getY());
/*  45 */       double zPos2 = Math.max(loc1.getZ(), loc2.getZ());
/*  46 */       this.minimumPoint = new Vector(xPos1, yPos1, zPos1);
/*  47 */       this.maximumPoint = new Vector(xPos2, yPos2, zPos2);
/*     */     } else {
/*  49 */       throw new NullPointerException("One/both of the locations is/are null!");
/*     */     }
/*     */   }
/*     */   
/*     */   public Cuboid(String worldName, double x1, double y1, double z1, double x2, double y2, double z2) {
/*  54 */     if ((worldName == null) || (Bukkit.getServer().getWorld(worldName) == null))
/*  55 */       throw new NullPointerException("One/both of the worlds is/are null!");
/*  56 */     this.worldName = worldName;
/*     */     
/*  58 */     double xPos1 = Math.min(x1, x2);
/*  59 */     double xPos2 = Math.max(x1, x2);
/*  60 */     double yPos1 = Math.min(y1, y2);
/*  61 */     double yPos2 = Math.max(y1, y2);
/*  62 */     double zPos1 = Math.min(z1, z2);
/*  63 */     double zPos2 = Math.max(z1, z2);
/*  64 */     this.minimumPoint = new Vector(xPos1, yPos1, zPos1);
/*  65 */     this.maximumPoint = new Vector(xPos2, yPos2, zPos2);
/*     */   }
/*     */   
/*     */   public boolean containsLocation(Location location) {
/*  69 */     return (location != null) && (location.getWorld().getName().equals(this.worldName)) && (location.toVector().isInAABB(this.minimumPoint, this.maximumPoint));
/*     */   }
/*     */   
/*     */   public boolean containsVector(Vector vector) {
/*  73 */     return (vector != null) && (vector.isInAABB(this.minimumPoint, this.maximumPoint));
/*     */   }
/*     */   
/*     */   public List<Block> getBlocks() {
/*  77 */     List<Block> blockList = new ArrayList();
/*  78 */     World world = getWorld();
/*  79 */     if (world != null) {
/*  80 */       for (int x = this.minimumPoint.getBlockX(); x <= this.maximumPoint.getBlockX(); x++) {
/*  81 */         for (int y = this.minimumPoint.getBlockY(); (y <= this.maximumPoint.getBlockY()) && (y <= world.getMaxHeight()); y++) {
/*  82 */           for (int z = this.minimumPoint.getBlockZ(); z <= this.maximumPoint.getBlockZ(); z++) {
/*  83 */             blockList.add(world.getBlockAt(x, y, z));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*  88 */     return blockList;
/*     */   }
/*     */   
/*     */   public Location getLowerLocation() {
/*  92 */     return this.minimumPoint.toLocation(getWorld());
/*     */   }
/*     */   
/*     */   public double getLowerX() {
/*  96 */     return this.minimumPoint.getX();
/*     */   }
/*     */   
/*     */   public double getLowerY() {
/* 100 */     return this.minimumPoint.getY();
/*     */   }
/*     */   
/*     */   public double getLowerZ() {
/* 104 */     return this.minimumPoint.getZ();
/*     */   }
/*     */   
/*     */   public Location getUpperLocation() {
/* 108 */     return this.maximumPoint.toLocation(getWorld());
/*     */   }
/*     */   
/*     */   public double getUpperX() {
/* 112 */     return this.maximumPoint.getX();
/*     */   }
/*     */   
/*     */   public double getUpperY() {
/* 116 */     return this.maximumPoint.getY();
/*     */   }
/*     */   
/*     */   public double getUpperZ() {
/* 120 */     return this.maximumPoint.getZ();
/*     */   }
/*     */   
/*     */   public double getVolume() {
/* 124 */     return (getUpperX() - getLowerX() + 1.0D) * (getUpperY() - getLowerY() + 1.0D) * (getUpperZ() - getLowerZ() + 1.0D);
/*     */   }
/*     */   
/*     */   public World getWorld() {
/* 128 */     World world = Bukkit.getServer().getWorld(this.worldName);
/* 129 */     if (world == null) throw new NullPointerException("World '" + this.worldName + "' is not loaded.");
/* 130 */     return world;
/*     */   }
/*     */   
/*     */   public void setWorld(World world) {
/* 134 */     if (world != null) this.worldName = world.getName(); else {
/* 135 */       throw new NullPointerException("The world cannot be null.");
/*     */     }
/*     */   }
/*     */   
/*     */   public Cuboid clone() {
/* 140 */     return new Cuboid(this);
/*     */   }
/*     */   
/*     */   public java.util.ListIterator<Block> iterator()
/*     */   {
/* 145 */     return getBlocks().listIterator();
/*     */   }
/*     */   
/*     */   public Map<String, Object> serialize()
/*     */   {
/* 150 */     Map<String, Object> serializedCuboid = new HashMap();
/* 151 */     serializedCuboid.put("worldName", this.worldName);
/* 152 */     serializedCuboid.put("x1", Double.valueOf(this.minimumPoint.getX()));
/* 153 */     serializedCuboid.put("x2", Double.valueOf(this.maximumPoint.getX()));
/* 154 */     serializedCuboid.put("y1", Double.valueOf(this.minimumPoint.getY()));
/* 155 */     serializedCuboid.put("y2", Double.valueOf(this.maximumPoint.getY()));
/* 156 */     serializedCuboid.put("z1", Double.valueOf(this.minimumPoint.getZ()));
/* 157 */     serializedCuboid.put("z2", Double.valueOf(this.maximumPoint.getZ()));
/* 158 */     return serializedCuboid;
/*     */   }
/*     */   
/*     */   public static Cuboid deserialize(Map<String, Object> serializedCuboid) {
/*     */     try {
/* 163 */       String worldName = (String)serializedCuboid.get("worldName");
/*     */       
/* 165 */       double xPos1 = ((Double)serializedCuboid.get("x1")).doubleValue();
/* 166 */       double xPos2 = ((Double)serializedCuboid.get("x2")).doubleValue();
/* 167 */       double yPos1 = ((Double)serializedCuboid.get("y1")).doubleValue();
/* 168 */       double yPos2 = ((Double)serializedCuboid.get("y2")).doubleValue();
/* 169 */       double zPos1 = ((Double)serializedCuboid.get("z1")).doubleValue();
/* 170 */       double zPos2 = ((Double)serializedCuboid.get("z2")).doubleValue();
/*     */       
/* 172 */       return new Cuboid(worldName, xPos1, yPos1, zPos1, xPos2, yPos2, zPos2);
/*     */     } catch (Exception ex) {
/* 174 */       ex.printStackTrace(); }
/* 175 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\Cuboid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */