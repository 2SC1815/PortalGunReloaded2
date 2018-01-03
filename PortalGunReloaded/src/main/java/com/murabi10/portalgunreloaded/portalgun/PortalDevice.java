/*     */ package com.murabi10.portalgunreloaded.portalgun;
/*     */ 
/*     */ import com.murabi10.portalgunreloaded.Methods;
/*     */ import com.murabi10.portalgunreloaded.PortalGun;
/*     */ import com.murabi10.portalgunreloaded.portalevents.PortalDestroyEvent;
/*     */ import com.murabi10.portalgunreloaded.portalevents.PortalLandingEvent;
/*     */ import com.murabi10.portalgunreloaded.portalevents.PortalLaunchEvent;
/*     */ import com.murabi10.portalgunreloaded.portalevents.PortalPreLaunchEvent;
/*     */ import com.murabi10.portalgunreloaded.selector.ItemClickWait;
/*     */ import com.murabi10.portalgunreloaded.selector.StringInputWait;
/*     */ import com.murabi10.portalgunreloaded.testchamber.TestQueue;
/*     */ import com.murabi10.portalgunreloaded.testingelement.objects.Cube;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class PortalDevice
/*     */ {
/*  31 */   private static HashMap<UUID, PortalDevice> devices = new HashMap();
/*     */   
/*     */   public static PortalDevice AuthPlayer(Player p)
/*     */   {
/*  35 */     UUID uuid = p.getUniqueId();
/*     */     
/*  37 */     PortalDevice portaldevice = null;
/*     */     
/*  39 */     if (devices.containsKey(uuid))
/*     */     {
/*  41 */       portaldevice = (PortalDevice)devices.get(uuid);
/*     */     }
/*     */     else
/*     */     {
/*  45 */       portaldevice = new PortalDevice(p);
/*     */       
/*  47 */       devices.put(uuid, portaldevice);
/*     */     }
/*     */     
/*     */ 
/*  51 */     portaldevice.flush();
/*     */     
/*  53 */     return portaldevice;
/*     */   }
/*     */   
/*     */   public static PortalDevice getDeviceInstance(UUID uuid) {
/*  57 */     return (PortalDevice)devices.get(uuid);
/*     */   }
/*     */   
/*     */   public static PortalDevice getDeviceInstance(Player p) {
/*  61 */     return getDeviceInstance(p.getUniqueId());
/*     */   }
/*     */   
/*     */   public static boolean isAuthed(UUID uuid) {
/*  65 */     return devices.containsKey(uuid);
/*     */   }
/*     */   
/*     */   public static boolean isAuthed(Player p) {
/*  69 */     return isAuthed(p.getUniqueId());
/*     */   }
/*     */   
/*     */   public static void Disable(UUID uuid) {
/*  73 */     if (isAuthed(uuid)) {
/*  74 */       ((PortalDevice)devices.get(uuid)).clearPortal();
/*  75 */       devices.remove(uuid);
/*     */     }
/*     */   }
/*     */   
/*     */   public static ArrayList<PortalDevice> getDevices() {
/*  80 */     ArrayList<PortalDevice> devices_ = new ArrayList();
/*  81 */     for (UUID key : devices.keySet()) {
/*  82 */       devices_.add((PortalDevice)devices.get(key));
/*     */     }
/*  84 */     return devices_;
/*     */   }
/*     */   
/*     */   public static void Disable(Player p) {
/*  88 */     Disable(p.getUniqueId());
/*     */   }
/*     */   
/*  91 */   private Vector[] vector = new Vector[12];
/*     */   
/*  93 */   private boolean portalInsideFlag = false;
/*     */   
/*     */   private Player owner;
/*     */   
/*  97 */   private Portal BluePortal = null;
/*  98 */   private Portal OrangePortal = null;
/*     */   
/*     */   private Cube hangingCube;
/*     */   
/* 102 */   private ItemStack itemInput = null;
/* 103 */   private boolean canItemInput = false;
/*     */   
/* 105 */   private String StringInput = "";
/* 106 */   private boolean canStringInput = false;
/*     */   
/* 108 */   private ItemClickWait itemwait = null;
/* 109 */   private StringInputWait strwait = null;
/*     */   
/* 111 */   private TestQueue queue = null;
/*     */   
/*     */   public double getVelocity(boolean ignoreVertical) {
/* 114 */     double max = 0.0D;
/* 115 */     for (int i = 0; i < 12; i++) {
/* 116 */       double tmp = Methods.VectorToAcc(this.vector[i], ignoreVertical);
/* 117 */       if (tmp > max)
/* 118 */         max = tmp;
/*     */     }
/* 120 */     return max;
/*     */   }
/*     */   
/*     */   public void setVelocity(Vector Velocity)
/*     */   {
/* 125 */     for (int i = 1; i < 12; i++) {
/* 126 */       this.vector[i] = this.vector[(i - 1)];
/*     */     }
/* 128 */     this.vector[0] = Velocity;
/*     */   }
/*     */   
/*     */   private void flush() {
/* 132 */     for (int i = 0; i < 12; i++) {
/* 133 */       this.vector[i] = new Vector(0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   private PortalDevice(Player p) {
/* 138 */     this.owner = p;
/*     */   }
/*     */   
/*     */   public void LaunchPortal(PortalColor color)
/*     */   {
/* 143 */     if (this.hangingCube != null) {
/* 144 */       return;
/*     */     }
/*     */     
/* 147 */     PortalPreLaunchEvent prelaunch = new PortalPreLaunchEvent(this.owner, color);
/* 148 */     Bukkit.getServer().getPluginManager().callEvent(prelaunch);
/*     */     
/* 150 */     if (!prelaunch.isCancelled())
/*     */     {
/* 152 */       Location eyeloc = this.owner.getEyeLocation();
/* 153 */       Vector dir = eyeloc.getDirection();
/*     */       
/* 155 */       boolean stop = false;
/*     */       
/*     */ 
/* 158 */       for (double count = 1.0D; count <= 201.0D; count += 0.4D)
/*     */       {
/* 160 */         Location loc = eyeloc.clone().add(dir.clone().multiply(count));
/* 161 */         for (Material mat : PortalGun.PortalTransparentMaterials)
/*     */         {
/* 163 */           if (loc.getBlock().getType().equals(mat))
/*     */           {
/* 165 */             if (color.equals(PortalColor.BLUE)) {
/* 166 */               loc.getWorld().spawnParticle(org.bukkit.Particle.REDSTONE, loc.getX(), loc.getY(), loc.getZ(), 0, 
/* 167 */                 0.00392156862745098D, 0.25098039215686274D, 0.996078431372549D, 1.0D);
/* 168 */               break; }
/* 169 */             loc.getWorld().spawnParticle(org.bukkit.Particle.REDSTONE, loc.getX(), loc.getY(), loc.getZ(), 0, 
/* 170 */               0.996078431372549D, 0.7058823529411765D, 0.11764705882352941D, 1.0D);
/*     */             
/* 172 */             break;
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 178 */         if (stop) {
/*     */           break;
/*     */         }
/*     */       }
/*     */       
/* 183 */       boolean success = true;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 193 */       List<Block> blocks = this.owner.getLastTwoTargetBlocks(PortalGun.PortalTransparentMaterials, 
/* 194 */         200);
/* 195 */       BlockFace launchDirection = null;
/*     */       
/* 197 */       if (blocks.size() != 1) {
/* 198 */         launchDirection = ((Block)blocks.get(1)).getFace((Block)blocks.get(0));
/*     */       }
/* 200 */       if (launchDirection == null) {
/* 201 */         success = false;
/*     */       }
/*     */       
/* 204 */       PortalLaunchEvent launch = new PortalLaunchEvent(this.owner, color, success);
/* 205 */       Bukkit.getServer().getPluginManager().callEvent(launch);
/*     */       
/* 207 */       if (success)
/*     */       {
/*     */         Portal portal;
/*     */         Portal portal;
/* 211 */         switch (launchDirection) {
/*     */         case NORTH: 
/*     */         case NORTH_EAST: 
/* 214 */           portal = new Portal(this.owner, color, launchDirection, 
/* 215 */             Methods.YawToBlockFace(this.owner.getEyeLocation().getYaw()), ((Block)blocks.get(0)).getLocation(), new Location[] {
/* 216 */             ((Block)blocks.get(0)).getLocation(), 
/* 217 */             ((Block)blocks.get(0)).getLocation().getBlock()
/* 218 */             .getRelative(Methods.YawToBlockFace(this.owner.getEyeLocation().getYaw()))
/* 219 */             .getLocation() });
/* 220 */           break;
/*     */         default: 
/* 222 */           portal = new Portal(this.owner, color, launchDirection, 
/* 223 */             Methods.YawToBlockFace(this.owner.getEyeLocation().getYaw()), ((Block)blocks.get(0)).getLocation(), new Location[] {
/* 224 */             ((Block)blocks.get(0)).getLocation() });
/*     */         }
/*     */         
/*     */         
/* 228 */         PortalLandingEvent landing = new PortalLandingEvent(portal, ((Block)blocks.get(1)).getLocation());
/* 229 */         Bukkit.getServer().getPluginManager().callEvent(landing);
/*     */         
/* 231 */         if (!landing.isCancelled())
/*     */         {
/* 233 */           if (color.equals(PortalColor.BLUE))
/*     */           {
/* 235 */             DestroyPortal(color);
/*     */             
/* 237 */             this.BluePortal = portal;
/*     */             
/* 239 */             if (this.OrangePortal != null) {
/* 240 */               this.OrangePortal.setDest(portal);
/* 241 */               this.BluePortal.setDest(this.OrangePortal);
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 246 */             DestroyPortal(color);
/*     */             
/* 248 */             this.OrangePortal = portal;
/*     */             
/* 250 */             if (this.BluePortal != null) {
/* 251 */               this.BluePortal.setDest(portal);
/* 252 */               this.OrangePortal.setDest(this.BluePortal);
/*     */             }
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 258 */           portal.stopParticle();
/* 259 */           portal = null;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void LaunchPortal(PortalColor color, Location loc, BlockFace dir, BlockFace dir2)
/*     */   {
/* 274 */     BlockFace launchDirection = dir;
/*     */     
/*     */     Portal portal;
/*     */     Portal portal;
/* 278 */     switch (launchDirection) {
/*     */     case NORTH: 
/*     */     case NORTH_EAST: 
/* 281 */       portal = new Portal(this.owner, color, launchDirection, dir2, loc, new Location[] { loc, 
/* 282 */         loc.getBlock().getRelative(dir2).getLocation() });
/* 283 */       break;
/*     */     default: 
/* 285 */       portal = new Portal(this.owner, color, launchDirection, dir2, loc, new Location[] { loc });
/*     */     }
/*     */     
/*     */     
/* 289 */     PortalLandingEvent landing = new PortalLandingEvent(portal, loc.getBlock().getRelative(dir).getLocation());
/* 290 */     Bukkit.getServer().getPluginManager().callEvent(landing);
/*     */     
/* 292 */     if (!landing.isCancelled())
/*     */     {
/* 294 */       if (color.equals(PortalColor.BLUE))
/*     */       {
/* 296 */         DestroyPortal(color);
/*     */         
/* 298 */         this.BluePortal = portal;
/*     */         
/* 300 */         if (this.OrangePortal != null) {
/* 301 */           this.OrangePortal.setDest(portal);
/* 302 */           this.BluePortal.setDest(this.OrangePortal);
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 307 */         DestroyPortal(color);
/*     */         
/* 309 */         this.OrangePortal = portal;
/*     */         
/* 311 */         if (this.BluePortal != null) {
/* 312 */           this.BluePortal.setDest(portal);
/* 313 */           this.OrangePortal.setDest(this.BluePortal);
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 318 */       portal.stopParticle();
/* 319 */       portal = null;
/*     */     }
/*     */   }
/*     */   
/*     */   public void DestroyPortal(PortalColor color)
/*     */   {
/* 325 */     if (color.equals(PortalColor.BLUE)) {
/* 326 */       if (this.BluePortal != null) {
/* 327 */         if (this.OrangePortal != null) {
/* 328 */           this.OrangePortal.setDest(null);
/*     */         }
/*     */         
/* 331 */         this.BluePortal.stopParticle();
/* 332 */         this.BluePortal = null;
/*     */       }
/*     */     }
/* 335 */     else if (this.OrangePortal != null) {
/* 336 */       if (this.BluePortal != null) {
/* 337 */         this.BluePortal.setDest(null);
/*     */       }
/*     */       
/* 340 */       this.OrangePortal.stopParticle();
/* 341 */       this.OrangePortal = null;
/*     */     }
/*     */     
/*     */ 
/* 345 */     PortalDestroyEvent destroy = new PortalDestroyEvent();
/* 346 */     Bukkit.getServer().getPluginManager().callEvent(destroy);
/*     */   }
/*     */   
/*     */   public void clearPortal() {
/* 350 */     DestroyPortal(PortalColor.BLUE);
/* 351 */     DestroyPortal(PortalColor.ORANGE);
/*     */   }
/*     */   
/*     */   public Portal getBluePortal() {
/* 355 */     return this.BluePortal;
/*     */   }
/*     */   
/*     */   public Portal getOrangePortal() {
/* 359 */     return this.OrangePortal;
/*     */   }
/*     */   
/*     */   public Player getOwner() {
/* 363 */     return this.owner;
/*     */   }
/*     */   
/*     */   public void setFlag(boolean flag) {
/* 367 */     this.portalInsideFlag = flag;
/*     */   }
/*     */   
/*     */   public boolean getFlag() {
/* 371 */     return this.portalInsideFlag;
/*     */   }
/*     */   
/*     */   public Cube getHangingCube() {
/* 375 */     return this.hangingCube;
/*     */   }
/*     */   
/*     */   public void setHangingCube(Cube hangingCube) {
/* 379 */     this.hangingCube = hangingCube;
/*     */   }
/*     */   
/*     */   public boolean canStringInput() {
/* 383 */     return this.canStringInput;
/*     */   }
/*     */   
/*     */   public void setStringInput(boolean canInput) {
/* 387 */     this.canStringInput = canInput;
/*     */   }
/*     */   
/*     */   public String getInputString() {
/* 391 */     return this.StringInput;
/*     */   }
/*     */   
/*     */   public void resetInputString() {
/* 395 */     this.StringInput = "";
/*     */   }
/*     */   
/*     */   public boolean isEmptyString() {
/* 399 */     return this.StringInput.equals("");
/*     */   }
/*     */   
/*     */   public void setInputString(String input) {
/* 403 */     this.StringInput = input;
/*     */   }
/*     */   
/*     */   public boolean canItemInput() {
/* 407 */     return this.canItemInput;
/*     */   }
/*     */   
/*     */   public void setItemInput(boolean canInput) {
/* 411 */     this.canItemInput = canInput;
/*     */   }
/*     */   
/*     */   public ItemStack getInputItem() {
/* 415 */     return this.itemInput;
/*     */   }
/*     */   
/*     */   public void resetInputItem() {
/* 419 */     this.itemInput = null;
/*     */   }
/*     */   
/*     */   public boolean isEmptyItem() {
/* 423 */     return this.itemInput == null;
/*     */   }
/*     */   
/*     */   public void setInputItem(ItemStack input) {
/* 427 */     this.itemInput = input;
/*     */   }
/*     */   
/*     */   public ItemClickWait getItemwait() {
/* 431 */     return this.itemwait;
/*     */   }
/*     */   
/*     */   public boolean hasItemwait() {
/* 435 */     return this.itemwait != null;
/*     */   }
/*     */   
/*     */   public void setItemwait(ItemClickWait itemwait) {
/* 439 */     this.itemwait = itemwait;
/*     */   }
/*     */   
/*     */   public StringInputWait getStrWait() {
/* 443 */     return this.strwait;
/*     */   }
/*     */   
/*     */   public boolean hasStrWait() {
/* 447 */     return this.strwait != null;
/*     */   }
/*     */   
/*     */   public void setStrWait(StringInputWait strwait) {
/* 451 */     this.strwait = strwait;
/*     */   }
/*     */   
/*     */   public TestQueue getQueue() {
/* 455 */     return this.queue;
/*     */   }
/*     */   
/*     */   public void setQueue(TestQueue queue) {
/* 459 */     this.queue = queue;
/*     */   }
/*     */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalgun\PortalDevice.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */