/*     */ package com.murabi10.portalgunreloaded.chambereditor;
/*     */ 
/*     */ import com.murabi10.portalgunreloaded.Cuboid;
/*     */ import com.murabi10.portalgunreloaded.Methods;
/*     */ import com.murabi10.portalgunreloaded.PortalGun;
/*     */ import com.murabi10.portalgunreloaded.selector.EditChamberSelector;
/*     */ import com.murabi10.portalgunreloaded.testchamber.DataSystem;
/*     */ import com.murabi10.portalgunreloaded.testchamber.TestChamber;
/*     */ import com.murabi10.portalgunreloaded.testchamber.TestChamberData;
/*     */ import com.murabi10.portalgunreloaded.testingelement.ElementType;
/*     */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*     */ import com.murabi10.portalgunreloaded.testingelement.area.StartPoint;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ 
/*     */ 
/*     */ public class TestChamberEditor
/*     */ {
/*  27 */   private static ArrayList<TestChamberEditor> editors = new ArrayList();
/*     */   
/*     */   private Location ChamberOriginLoc;
/*     */   private TestChamber target;
/*     */   private TestChamberData targetdata;
/*  32 */   private Location CuboidPos1 = null;
/*     */   
/*  34 */   private Location CuboidPos2 = null;
/*     */   
/*     */   private BlockFace selectionFace;
/*     */   
/*     */   private Player EditPlayer;
/*     */   private BukkitRunnable selection;
/*  40 */   private boolean selectVisible = false;
/*     */   
/*     */   public TestChamberEditor(Location canvasLoc, TestChamber targetChamber, TestChamberData data, Player editPlayer) throws Exception
/*     */   {
/*  44 */     for (TestChamberEditor editor : editors) {
/*  45 */       if (editor.getEditPlayer().getUniqueId().equals(editPlayer.getUniqueId())) {
/*  46 */         throw new Exception("Player " + editPlayer.getName() + " has already started edit mode!");
/*     */       }
/*     */     }
/*  49 */     this.ChamberOriginLoc = canvasLoc;
/*  50 */     this.targetdata = data;
/*  51 */     this.target = targetChamber;
/*  52 */     this.EditPlayer = editPlayer;
/*  53 */     editors.add(this);
/*     */     
/*  55 */     this.selection = new BukkitRunnable()
/*     */     {
/*     */       public void run()
/*     */       {
/*  59 */         TestChamberEditor.this.visual();
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   public void setVisible(boolean visible)
/*     */   {
/*  67 */     this.selectVisible = visible;
/*  68 */     if (this.selectVisible) {
/*  69 */       this.selection.runTaskTimer(PortalGun.getPlugin(), 1L, 3L);
/*     */     } else {
/*  71 */       this.selection.cancel();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean getVisible() {
/*  76 */     return this.selectVisible;
/*     */   }
/*     */   
/*     */   private void visual() {
/*  80 */     if (getPointLoc() != null) {
/*  81 */       Location point = Methods.LocationNormalize(
/*  82 */         getPointLoc().getBlock().getRelative(this.selectionFace).getLocation().clone().add(0.0D, 1.0D, 0.0D));
/*  83 */       Methods.spawnParticle(point, (byte)-2, (byte)-2, (byte)45);
/*  84 */       Methods.spawnParticle(point.clone().add(Methods.BlockFaceToVector(this.selectionFace, 0.4D)), (byte)-2, 
/*  85 */         (byte)45, (byte)45);
/*     */     }
/*     */   }
/*     */   
/*     */   public Location getOrigin() {
/*  90 */     return this.ChamberOriginLoc;
/*     */   }
/*     */   
/*     */   public void StartEdit() {
/*  94 */     this.target.placeToWorld(this.ChamberOriginLoc);
/*     */     
/*  96 */     for (TestingElement e : this.target.TestingElements())
/*     */     {
/*  98 */       e.setOriginLocation(this.ChamberOriginLoc);
/*  99 */       e.setEditMode(true);
/* 100 */       e.setTargetPlayer(this.EditPlayer);
/* 101 */       e.initRunnable();
/*     */     }
/*     */     Location loc;
/* 104 */     for (TestingElement e : this.target.TestingElements()) {
/* 105 */       if (e.getType().equals(ElementType.START_POINT)) {
/* 106 */         loc = e.getRelative1(this.ChamberOriginLoc);
/* 107 */         if ((loc.getBlock().isEmpty()) && (loc.getBlock().getRelative(BlockFace.UP).isEmpty())) {
/* 108 */           e.getTargetPlayer().teleport(Methods.LocationNormalize(loc));
/* 109 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 114 */     ArrayList<TestingElement> queue = new ArrayList();
/* 115 */     for (TestingElement element : this.target.TestingElements()) {
/* 116 */       if (element.getType().equals(ElementType.START_POINT)) {
/* 117 */         element.disable();
/* 118 */         queue.add(element);
/*     */       }
/*     */     }
/*     */     
/* 122 */     for (TestingElement element : queue) {
/* 123 */       this.target.TestingElements().remove(element);
/*     */     }
/*     */     
/* 126 */     queue.clear();
/* 127 */     queue = null;
/*     */     
/* 129 */     for (Block b : new Cuboid(this.ChamberOriginLoc.clone().add(1.0D, 1.0D, 1.0D), this.ChamberOriginLoc.clone().add(63.0D, 63.0D, 63.0D))) {
/* 130 */       Location loc = b.getLocation();
/* 131 */       if ((loc.getBlock().isEmpty()) && (loc.getBlock().getRelative(BlockFace.UP).isEmpty())) {
/* 132 */         StartPoint point = new StartPoint(this.ChamberOriginLoc, Methods.getX(this.ChamberOriginLoc, loc), 
/* 133 */           Methods.getY(this.ChamberOriginLoc, loc), Methods.getZ(this.ChamberOriginLoc, loc));
/* 134 */         AddTestElement(point);
/* 135 */         point.setOriginLocation(this.ChamberOriginLoc);
/* 136 */         point.setEditMode(true);
/* 137 */         point.setTargetPlayer(this.EditPlayer);
/* 138 */         point.initRunnable();
/*     */         
/* 140 */         this.EditPlayer.teleport(loc);
/* 141 */         return;
/*     */       }
/*     */     }
/*     */     
/* 145 */     Location loc = this.ChamberOriginLoc.clone().add(2.0D, 2.0D, 2.0D);
/* 146 */     loc.getBlock().setType(Material.AIR);
/* 147 */     loc.getBlock().getRelative(BlockFace.UP).setType(Material.AIR);
/* 148 */     StartPoint point = new StartPoint(this.ChamberOriginLoc, Methods.getX(this.ChamberOriginLoc, loc), 
/* 149 */       Methods.getY(this.ChamberOriginLoc, loc), Methods.getZ(this.ChamberOriginLoc, loc));
/* 150 */     AddTestElement(point);
/* 151 */     this.EditPlayer.teleport(loc);
/*     */   }
/*     */   
/*     */ 
/*     */   public void Save()
/*     */   {
/* 157 */     this.target.importFromWorld(this.ChamberOriginLoc);
/*     */   }
/*     */   
/*     */   public Player getEditPlayer() {
/* 161 */     return this.EditPlayer;
/*     */   }
/*     */   
/*     */   public void Link(TestingElement SignalFrom, TestingElement activateTarget) {
/* 165 */     if (!activateTarget.Switches().contains(SignalFrom)) {
/* 166 */       activateTarget.Switches().add(SignalFrom);
/*     */     }
/*     */   }
/*     */   
/*     */   public void SaveToFile() {
/* 171 */     DataSystem.Save(this.target, this.targetdata, this.targetdata.getFileName());
/*     */   }
/*     */   
/*     */   public void Exit() {
/* 175 */     setVisible(false);
/* 176 */     disableAllElements();
/* 177 */     this.selection = null;
/* 178 */     Save();
/* 179 */     SaveToFile();
/* 180 */     editors.remove(this);
/* 181 */     this.target.deleteFromWorld(this.ChamberOriginLoc);
/* 182 */     EditChamberSelector.remove(this.ChamberOriginLoc);
/* 183 */     this.EditPlayer.teleport(this.EditPlayer.getBedSpawnLocation());
/* 184 */     EditChamberSelector.updateData();
/*     */   }
/*     */   
/*     */ 
/*     */   public void printData() {}
/*     */   
/*     */ 
/*     */   public void checkElements()
/*     */   {
/* 193 */     for (TestingElement element : this.target.TestingElements()) {
/* 194 */       element.check();
/*     */     }
/*     */   }
/*     */   
/*     */   public void disableAllElements() {
/* 199 */     for (TestingElement element : this.target.TestingElements()) {
/* 200 */       element.disable();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean AddTestElement(TestingElement e)
/*     */   {
/* 208 */     if ((e.getType().equals(ElementType.START_POINT)) || (e.getType().equals(ElementType.GOAL_POINT)))
/*     */     {
/* 210 */       ArrayList<TestingElement> queue = new ArrayList();
/* 211 */       for (TestingElement element : this.target.TestingElements()) {
/* 212 */         if (element.getType().equals(e.getType())) {
/* 213 */           element.disable();
/* 214 */           queue.add(element);
/*     */         }
/*     */       }
/*     */       
/* 218 */       for (TestingElement element : queue) {
/* 219 */         this.target.TestingElements().remove(element);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 225 */     if ((getElement(e.getRelative1(this.ChamberOriginLoc)) != null) || 
/* 226 */       (getElement(e.getRelative2(this.ChamberOriginLoc)) != null)) {
/* 227 */       return false;
/*     */     }
/* 229 */     this.target.TestingElements().add(e);
/*     */     
/* 231 */     e.setOriginLocation(this.ChamberOriginLoc);
/* 232 */     e.setEditMode(true);
/* 233 */     e.setTargetPlayer(this.EditPlayer);
/*     */     
/* 235 */     return true;
/*     */   }
/*     */   
/*     */   public void RemoveElement(Location loc) {
/* 239 */     ArrayList<TestingElement> queue = new ArrayList();
/* 240 */     for (TestingElement element : this.target.TestingElements()) {
/* 241 */       if ((Methods.LocationEquals(element.getRelative1(this.ChamberOriginLoc), loc)) || 
/* 242 */         (Methods.LocationEquals(element.getRelative2(this.ChamberOriginLoc), loc))) {
/* 243 */         element.disable();
/* 244 */         queue.add(element);
/*     */       }
/*     */     }
/*     */     
/* 248 */     for (TestingElement element : queue) {
/* 249 */       RemoveElement(element);
/*     */     }
/*     */     
/* 252 */     queue.clear();
/* 253 */     queue = null;
/*     */   }
/*     */   
/*     */   public void RemoveElement(TestingElement e) {
/* 257 */     if (this.target.TestingElements().contains(e)) {
/* 258 */       for (TestingElement el : this.target.TestingElements()) {
/* 259 */         if (el.Switches().contains(e)) {
/* 260 */           el.Switches().remove(e);
/*     */         }
/*     */       }
/* 263 */       this.target.TestingElements().remove(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public TestingElement getElement(Location loc) {
/* 268 */     for (TestingElement element : this.target.TestingElements()) {
/* 269 */       if ((Methods.LocationEquals(element.getRelative1(this.ChamberOriginLoc), loc)) || 
/* 270 */         (Methods.LocationEquals(element.getRelative2(this.ChamberOriginLoc), loc))) {
/* 271 */         return element;
/*     */       }
/*     */     }
/* 274 */     return null;
/*     */   }
/*     */   
/*     */   public ArrayList<TestingElement> elements() {
/* 278 */     return this.target.TestingElements();
/*     */   }
/*     */   
/*     */   public boolean Pos1Empt() {
/* 282 */     return this.CuboidPos1 == null;
/*     */   }
/*     */   
/*     */   public boolean Pos2Empt() {
/* 286 */     return this.CuboidPos2 == null;
/*     */   }
/*     */   
/*     */   public Location getPos1() {
/* 290 */     return this.CuboidPos1;
/*     */   }
/*     */   
/*     */   public Location getPos2() {
/* 294 */     return this.CuboidPos2;
/*     */   }
/*     */   
/*     */   public Location getPointLoc() {
/* 298 */     if ((Pos1Empt()) && (Pos2Empt())) {
/* 299 */       return null;
/*     */     }
/* 301 */     if (Pos2Empt())
/* 302 */       return this.CuboidPos1;
/* 303 */     if (Pos1Empt())
/* 304 */       return this.CuboidPos2;
/* 305 */     if (Methods.LocationEquals(this.CuboidPos1, this.CuboidPos2)) {
/* 306 */       return this.CuboidPos1;
/*     */     }
/* 308 */     return null;
/*     */   }
/*     */   
/*     */   public BlockFace getsel() {
/* 312 */     return this.selectionFace;
/*     */   }
/*     */   
/*     */   public void clearPos() {
/* 316 */     this.CuboidPos1 = null;
/* 317 */     this.CuboidPos2 = null;
/* 318 */     this.selectionFace = null;
/*     */   }
/*     */   
/*     */   public void RClick() {
/* 322 */     List<Block> blocks = this.EditPlayer.getLastTwoTargetBlocks(null, 5);
/* 323 */     this.CuboidPos1 = ((Block)blocks.get(1)).getLocation();
/* 324 */     this.selectionFace = ((Block)blocks.get(1)).getFace((Block)blocks.get(0));
/*     */   }
/*     */   
/*     */   public void LClick() {
/* 328 */     List<Block> blocks = this.EditPlayer.getLastTwoTargetBlocks(null, 5);
/* 329 */     this.CuboidPos2 = ((Block)blocks.get(1)).getLocation();
/* 330 */     this.selectionFace = ((Block)blocks.get(1)).getFace((Block)blocks.get(0));
/*     */   }
/*     */   
/*     */   public int getX(Location target) {
/* 334 */     return target.getBlockX() - this.ChamberOriginLoc.getBlockX();
/*     */   }
/*     */   
/*     */   public int getY(Location target) {
/* 338 */     return target.getBlockY() - this.ChamberOriginLoc.getBlockY();
/*     */   }
/*     */   
/*     */   public int getZ(Location target) {
/* 342 */     return target.getBlockZ() - this.ChamberOriginLoc.getBlockZ();
/*     */   }
/*     */   
/*     */   public static void Check() {
/* 346 */     File f = new File(PortalGun.TEST_CHAMBER_FILE_PATH);
/* 347 */     if ((!f.exists()) || (!f.isDirectory())) {
/* 348 */       f.delete();
/* 349 */       f.mkdirs();
/*     */     }
/*     */   }
/*     */   
/*     */   public static ArrayList<File> getChambers() {
/* 354 */     ArrayList<File> file = new ArrayList();
/* 355 */     File[] files = new File(PortalGun.TEST_CHAMBER_FILE_PATH).listFiles();
/* 356 */     for (int i = 0; i < files.length; i++) {
/* 357 */       File f = files[i];
/* 358 */       if ((f.isFile()) && (f.toString().endsWith(".chamber"))) {
/* 359 */         file.add(f);
/*     */       }
/*     */     }
/* 362 */     return file;
/*     */   }
/*     */   
/*     */   public static TestChamberEditor getEditor(Player p) {
/* 366 */     for (TestChamberEditor editor : editors) {
/* 367 */       if (editor.getEditPlayer().getUniqueId().equals(p.getUniqueId())) {
/* 368 */         return editor;
/*     */       }
/*     */     }
/* 371 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isCuboid() {
/* 375 */     return (!Pos1Empt()) && (!Pos2Empt());
/*     */   }
/*     */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\chambereditor\TestChamberEditor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */