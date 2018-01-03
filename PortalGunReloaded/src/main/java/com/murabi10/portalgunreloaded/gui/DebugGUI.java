/*     */ package com.murabi10.portalgunreloaded.gui;
/*     */ 
/*     */ import com.murabi10.portalgunreloaded.devices.Device;
/*     */ import com.murabi10.portalgunreloaded.devices.DeviceManager;
/*     */ import com.murabi10.portalgunreloaded.portalgun.Portal;
/*     */ import com.murabi10.portalgunreloaded.portalgun.PortalColor;
/*     */ import com.murabi10.portalgunreloaded.portalgun.PortalDevice;
/*     */ import com.murabi10.portalgunreloaded.selector.EditChamberSelector;
/*     */ import com.murabi10.portalgunreloaded.selector.SortType;
/*     */ import com.murabi10.portalgunreloaded.testingelement.fixture.ThermalDiscouragementBeam;
/*     */ import com.murabi10.portalgunreloaded.testingelement.objects.Cube;
/*     */ import com.murabi10.portalgunreloaded.testingelement.objects.CubeType;
/*     */ import java.util.ArrayList;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.ClickType;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ 
/*     */ public class DebugGUI extends GUI
/*     */ {
/*     */   public void init()
/*     */   {
/*  26 */     setMenuName("DEBUG MENU");
/*  27 */     setSize(3);
/*     */     
/*  29 */     addbutton(new Button(Material.STONE, 1, (short)0, "DEBUG : PORTAL SPECIFICATIONS", 3, new GUIFunction()
/*     */     {
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/*  33 */         ArrayList<Portal> portals = new ArrayList();
/*  34 */         for (PortalDevice portalgun : PortalDevice.getDevices()) {
/*  35 */           Portal portal = portalgun.getBluePortal();
/*  36 */           if (portal != null) {
/*  37 */             portals.add(portal);
/*     */           }
/*  39 */           portal = portalgun.getOrangePortal();
/*  40 */           if (portal != null) {
/*  41 */             portals.add(portal);
/*     */           }
/*     */         }
/*  44 */         p.sendMessage("===Portal specifications===");
/*  45 */         for (Portal portal : portals) {
/*  46 */           p.sendMessage("Color:" + portal.getColor().toString() + " Luncher:" + portal.getPlayer().getName() + 
/*  47 */             " LaunchDirection:" + portal.getLaunchDirection().toString() + " Sub:" + 
/*  48 */             portal.getSubstitutionDirection().toString());
/*  49 */           Portal dest = portal.getDest();
/*  50 */           if (dest != null) {
/*  51 */             p.sendMessage("Representative:" + portal.getRepresentativeLocation() + " Dest:" + 
/*  52 */               dest.getRepresentativeLocation());
/*     */           } else {
/*  54 */             p.sendMessage("Representative:" + portal.getRepresentativeLocation() + " Dest: NOT FOUND");
/*     */           }
/*  56 */           String locs = "locs: ";
/*  57 */           for (Location loc : portal.getLocations()) {
/*  58 */             locs = locs + loc.toString() + ", ";
/*     */           }
/*  60 */           p.sendMessage(locs);
/*  61 */           p.sendMessage("=== === === === ===");
/*     */         }
/*  63 */         p.sendMessage("=== === END === ===");
/*  64 */         return true; } }, new String[] {
/*     */     
/*     */ 
/*  67 */       "現在設置されているポータルの", "情報を表示します" }));
/*     */     
/*  69 */     addbutton(new Button(Material.STONE, 2, (short)0, "DEBUG : TEST CHAMBERS", 4, new GUIFunction()
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         try
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 106 */           p.closeInventory();
/* 107 */           EditChamberSelector.OpenGUI(p, 0, SortType.NAME, "");
/*     */         }
/*     */         catch (Exception e) {
/* 110 */           e.printStackTrace();
/* 111 */           p.sendMessage("なんかえらーでた");
/*     */         }
/* 113 */         return false; } }, new String[] {
/*     */     
/*     */ 
/* 116 */       "テストチェンバーの", "テスト" }));
/*     */     
/* 118 */     addbutton(new Button(Material.STONE, 2, (short)0, "DEBUG : GIVE PORTALGUN", 1, new GUIFunction()
/*     */     {
/*     */ 
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 123 */         ItemStack item = null;
/* 124 */         switch (type) {
/*     */         case CONTROL_DROP: 
/* 126 */           item = DeviceManager.getDevice("PORTALGUN").getItem();
/* 127 */           break;
/*     */         case DOUBLE_CLICK: 
/* 129 */           item = DeviceManager.getDevice("BROKEN_PORTALGUN").getItem();
/* 130 */           break;
/*     */         }
/*     */         
/*     */         
/*     */ 
/* 135 */         if (item != null) {
/* 136 */           int index = p.getInventory().firstEmpty();
/* 137 */           if (index != -1) {
/* 138 */             p.getInventory().setItem(index, item);
/* 139 */             return true;
/*     */           }
/*     */         }
/* 142 */         return false; } }, new String[] {
/*     */     
/*     */ 
/* 145 */       "左クリックでデュアルポータルガン、", "右クリックでシングルポータルガンを出す" }));
/*     */     
/* 147 */     addbutton(new Button(Material.REDSTONE_BLOCK, 2, (short)0, "DEBUG : CUBE TEST", 5, new GUIFunction()
/*     */     {
/*     */ 
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/*     */ 
/* 153 */         switch (type) {
/*     */         case DOUBLE_CLICK: 
/* 155 */           new Cube(p.getLocation(), CubeType.REFLECTION);
/* 156 */           break;
/*     */         case CONTROL_DROP: 
/* 158 */           new Cube(p.getLocation(), CubeType.NORMAL);
/* 159 */           break;
/*     */         case NUMBER_KEY: 
/* 161 */           new Cube(p.getLocation(), CubeType.COMPANION);
/* 162 */           break;
/*     */         case CREATIVE: case DROP: case LEFT: case MIDDLE: default: 
/* 164 */           return false;
/*     */         }
/*     */         
/* 167 */         return true; } }, new String[] {
/*     */     
/*     */ 
/* 170 */       "WEIGHTED STORAGE CUBE TEST!", "左クリックで通常、右で反射、", "ミドルでコンパニオン" }));
/*     */     
/* 172 */     addbutton(new Button(Material.STONE, 2, (short)0, "DEBUG : THERMAL DISCOURAGEMENT BEAM TEST", 6, new GUIFunction()
/*     */     {
/*     */ 
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 177 */         new ThermalDiscouragementBeam(p.getLocation(), BlockFace.SOUTH, 0, 0, 0);
/*     */         
/* 179 */         return true; } }, new String[] {
/*     */     
/*     */ 
/* 182 */       "LASER TEST!" }));
/*     */     
/*     */ 
/* 185 */     addbutton(new Button(Material.GOLD_BLOCK, 2, (short)0, "DEBUG : GET TOOLS", 7, new GUIFunction()
/*     */     {
/*     */ 
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 190 */         int index = p.getInventory().firstEmpty();
/* 191 */         if (index != -1) {
/* 192 */           p.getInventory().setItem(index, DeviceManager.getDevice("PLACER").getItem());
/*     */         }
/* 194 */         index = p.getInventory().firstEmpty();
/* 195 */         if (index != -1) {
/* 196 */           p.getInventory().setItem(index, DeviceManager.getDevice("SELECTOR").getItem());
/*     */         }
/* 198 */         index = p.getInventory().firstEmpty();
/* 199 */         if (index != -1) {
/* 200 */           p.getInventory().setItem(index, DeviceManager.getDevice("INFO").getItem());
/*     */         }
/* 202 */         return true; } }, new String[] {
/*     */     
/*     */ 
/* 205 */       "  " }));
/*     */   }
/*     */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\gui\DebugGUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */