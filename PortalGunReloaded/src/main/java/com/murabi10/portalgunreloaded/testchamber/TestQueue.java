/*     */ package com.murabi10.portalgunreloaded.testchamber;
/*     */ 
/*     */ import com.murabi10.portalgunreloaded.PortalGun;
/*     */ import com.murabi10.portalgunreloaded.devices.Device;
/*     */ import com.murabi10.portalgunreloaded.devices.DeviceManager;
/*     */ import com.murabi10.portalgunreloaded.portalevents.PlayerChamberGoalEvent;
/*     */ import com.murabi10.portalgunreloaded.portalgun.PortalDevice;
/*     */ import com.murabi10.portalgunreloaded.testingelement.ElementType;
/*     */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*     */ import com.murabi10.portalgunreloaded.testingelement.objects.Cube;
/*     */ import java.util.ArrayList;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ import org.bukkit.event.player.PlayerRespawnEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public class TestQueue implements org.bukkit.event.Listener
/*     */ {
/*  27 */   private static ArrayList<Location> locs = new ArrayList();
/*     */   
/*     */   private Player testSubject;
/*     */   private TestChamberData testChamberData;
/*     */   private TestChamber testChamber;
/*     */   private Location exitLocation;
/*     */   private Location lobbyLocation;
/*  34 */   private Location originLoc = null;
/*  35 */   private Location StartLocation = null;
/*     */   private ItemStack[] inv;
/*     */   
/*     */   public TestQueue(Player testSubject, TestChamberData testChamber, Location exit, Location lobby) {
/*  39 */     this.testSubject = testSubject;
/*  40 */     this.testChamberData = testChamber;
/*  41 */     this.exitLocation = exit;
/*  42 */     this.lobbyLocation = lobby;
/*     */   }
/*     */   
/*     */   public boolean BeginTest() {
/*  46 */     return BeginTest(true);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean BeginTest(boolean reserve)
/*     */   {
/*  52 */     if (reserve) {
/*  53 */       this.originLoc = reserve();
/*     */     }
/*  55 */     if (this.originLoc != null)
/*     */     {
/*  57 */       this.testChamber = DataSystem.getChamberObject(this.testChamberData.getFileName());
/*  58 */       this.testChamber.placeToWorld(this.originLoc);
/*     */       
/*  60 */       Bukkit.getServer().getPluginManager().registerEvents(this, PortalGun.getPlugin());
/*     */       
/*  62 */       for (TestingElement e : this.testChamber.TestingElements())
/*     */       {
/*  64 */         e.setOriginLocation(this.originLoc);
/*  65 */         e.setEditMode(false);
/*  66 */         e.setTargetPlayer(this.testSubject);
/*  67 */         e.initRunnable();
/*     */       }
/*     */       
/*  70 */       for (TestingElement e : this.testChamber.TestingElements()) {
/*  71 */         if (e.getType().equals(ElementType.START_POINT)) {
/*  72 */           Location loc = com.murabi10.portalgunreloaded.Methods.LocationNormalize(e.getRelative1(this.originLoc));
/*  73 */           if ((loc.getBlock().isEmpty()) && (loc.getBlock().getRelative(org.bukkit.block.BlockFace.UP).isEmpty())) {
/*  74 */             this.testSubject.teleport(loc);
/*     */             
/*  76 */             this.StartLocation = loc;
/*     */             
/*  78 */             PortalDevice dev = PortalDevice.getDeviceInstance(this.testSubject);
/*  79 */             if ((dev.getQueue() != null) && (!dev.getQueue().equals(this))) {
/*  80 */               dev.getQueue().stop();
/*     */             }
/*  82 */             dev.setQueue(this);
/*     */             
/*  84 */             dev.clearPortal();
/*     */             
/*     */ 
/*     */ 
/*  88 */             if (reserve) {
/*  89 */               this.inv = this.testSubject.getInventory().getContents();
/*     */             }
/*     */             
/*  92 */             this.testSubject.getInventory().clear();
/*     */             
/*  94 */             this.testSubject.getInventory().setBoots(com.murabi10.portalgunreloaded.LongFallBoots.getBoots());
/*     */             
/*  96 */             ItemStack portalGun = null;
/*     */             
/*  98 */             switch (this.testChamberData.getPortalGunGive()) {
/*     */             case DUAL_PORTAL_DEVICE: 
/* 100 */               portalGun = DeviceManager.getDevice("PORTALGUN").getItem();
/* 101 */               break;
/*     */             case NONE: 
/* 103 */               portalGun = DeviceManager.getDevice("BROKEN_PORTALGUN").getItem();
/* 104 */               break;
/*     */             }
/*     */             
/*     */             
/*     */ 
/*     */ 
/* 110 */             if (portalGun != null) {
/* 111 */               this.testSubject.getInventory().addItem(new ItemStack[] { portalGun });
/*     */             }
/*     */             
/* 114 */             this.testSubject.sendTitle(this.testChamberData.getChamberName(), 
/* 115 */               this.testChamberData.getDesignerName());
/*     */             
/* 117 */             locs.add(this.originLoc);
/* 118 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 123 */       for (TestingElement element : this.testChamber.TestingElements()) {
/* 124 */         element.disable();
/*     */       }
/*     */       
/* 127 */       StopTest();
/*     */       
/* 129 */       this.testSubject.sendMessage("エラー：このチェンバーは開始位置が存在しないためテストを開始できませんてした");
/* 130 */       this.testSubject.sendMessage("チェンバー設計者(" + this.testChamberData.getDesignerName() + ")に連絡してください。");
/*     */       
/* 132 */       return false;
/*     */     }
/* 134 */     this.testSubject.sendMessage("同時テスト可能数を超えているためテストを開始できませんでした。");
/* 135 */     this.testSubject.sendMessage("しばらくたってからやり直してください。");
/* 136 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void StopTest()
/*     */   {
/* 142 */     this.testSubject.teleport(this.lobbyLocation);
/* 143 */     stop();
/* 144 */     PortalDevice d = PortalDevice.getDeviceInstance(this.testSubject);
/* 145 */     if (d != null) {
/* 146 */       d.clearPortal();
/* 147 */       if (d.getQueue() != null) {
/* 148 */         d.setQueue(null);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onGoal(PlayerChamberGoalEvent e)
/*     */   {
/* 156 */     if (this.testSubject.getUniqueId().equals(e.getPlayer().getUniqueId())) {
/* 157 */       e.getPlayer().teleport(this.exitLocation);
/* 158 */       e.getPlayer().sendTitle("テストチェンバークリア！", "That's awesome!");
/* 159 */       stop();
/* 160 */       PortalDevice d = PortalDevice.getDeviceInstance(e.getPlayer());
/* 161 */       if (d != null) {
/* 162 */         d.clearPortal();
/* 163 */         Cube c = d.getHangingCube();
/* 164 */         if (c != null) {
/* 165 */           c.Destroy(false);
/* 166 */           if (d.getQueue() != null) {
/* 167 */             d.setQueue(null);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onRespawn(PlayerRespawnEvent e) {
/* 176 */     if (this.testSubject.getUniqueId().equals(e.getPlayer().getUniqueId())) {
/* 177 */       e.getPlayer().sendMessage("テスト再開中... しばらくおまちください");
/* 178 */       PortalDevice d = PortalDevice.getDeviceInstance(e.getPlayer());
/* 179 */       if (d != null) {
/* 180 */         d.clearPortal();
/* 181 */         Cube c = d.getHangingCube();
/* 182 */         if (c != null) {
/* 183 */           c.Destroy(false);
/* 184 */           if (d.getQueue() != null) {
/* 185 */             d.setQueue(null);
/*     */           }
/*     */         }
/*     */       }
/* 189 */       restartTest();
/* 190 */       e.setRespawnLocation(this.StartLocation);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onQuit(PlayerQuitEvent e) {
/* 196 */     if (this.testSubject.getUniqueId().equals(e.getPlayer().getUniqueId())) {
/* 197 */       e.getPlayer().teleport(this.lobbyLocation);
/* 198 */       PortalDevice d = PortalDevice.getDeviceInstance(e.getPlayer());
/* 199 */       if (d != null) {
/* 200 */         d.clearPortal();
/* 201 */         Cube c = d.getHangingCube();
/* 202 */         if (c != null) {
/* 203 */           c.Destroy(false);
/* 204 */           if (d.getQueue() != null) {
/* 205 */             d.setQueue(null);
/*     */           }
/*     */         }
/*     */       }
/* 209 */       stop();
/*     */     }
/*     */   }
/*     */   
/*     */   private void stop() {
/* 214 */     this.testSubject.getInventory().clear();
/* 215 */     if (this.inv != null) {
/* 216 */       this.testSubject.getInventory().setContents(this.inv);
/*     */     }
/* 218 */     locs.remove(this.originLoc);
/* 219 */     PlayerChamberGoalEvent.getHandlerList().unregister(this);
/* 220 */     PlayerRespawnEvent.getHandlerList().unregister(this);
/* 221 */     PlayerQuitEvent.getHandlerList().unregister(this);
/* 222 */     this.testChamber.deleteFromWorld(this.originLoc);
/* 223 */     for (TestingElement element : this.testChamber.TestingElements()) {
/* 224 */       element.disable();
/*     */     }
/*     */   }
/*     */   
/*     */   public void restartTest() {
/* 229 */     PlayerChamberGoalEvent.getHandlerList().unregister(this);
/* 230 */     PlayerRespawnEvent.getHandlerList().unregister(this);
/* 231 */     PlayerQuitEvent.getHandlerList().unregister(this);
/* 232 */     for (TestingElement element : this.testChamber.TestingElements()) {
/* 233 */       element.disable();
/*     */     }
/* 235 */     BeginTest(false);
/*     */   }
/*     */   
/*     */   private static Location reserve() {
/* 239 */     Location loc = new Location(Bukkit.getWorld(PortalGun.EditorWorldName), 0.0D, 0.0D, 128.0D);
/* 240 */     int i = 0;
/*     */     for (;;)
/*     */     {
/* 243 */       if (i >= 6) {
/* 244 */         return null;
/*     */       }
/*     */       
/* 247 */       if (!locs.contains(loc)) break;
/* 248 */       loc = loc.add(65.0D, 0.0D, 0.0D);
/* 249 */       i++;
/*     */     }
/* 251 */     return loc;
/*     */   }
/*     */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testchamber\TestQueue.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */