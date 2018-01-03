/*     */ package com.murabi10.portalgunreloaded.portalgun;
/*     */ 
/*     */ import com.murabi10.portalgunreloaded.devices.Device;
/*     */ import com.murabi10.portalgunreloaded.devices.DeviceManager;
/*     */ import com.murabi10.portalgunreloaded.testingelement.objects.Cube;
/*     */ import com.murabi10.portalgunreloaded.testingelement.objects.CubeManager;
/*     */ import java.util.List;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.ArmorStand;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerMoveEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class HangingManager implements Listener
/*     */ {
/*     */   @EventHandler
/*     */   public void onDrop(PlayerDropItemEvent e)
/*     */   {
/*  29 */     ItemStack item = e.getItemDrop().getItemStack();
/*  30 */     Player p = e.getPlayer();
/*  31 */     if ((item != null) && (item.hasItemMeta())) {
/*  32 */       ItemMeta meta = item.getItemMeta();
/*  33 */       if (meta.hasLore()) {
/*  34 */         List<String> lore = meta.getLore();
/*  35 */         Material material = item.getType();
/*  36 */         short data = item.getDurability();
/*  37 */         for (Device device : DeviceManager.devices) {
/*  38 */           ItemStack d = device.getItem();
/*  39 */           if ((d.getType().equals(material)) && (d.getDurability() == data) && 
/*  40 */             (d.getItemMeta().getLore().equals(lore)))
/*     */           {
/*  42 */             e.setCancelled(true);
/*     */             
/*  44 */             PortalDevice gun = PortalDevice.getDeviceInstance(p);
/*     */             
/*  46 */             if (gun.getHangingCube() != null) {
/*  47 */               gun.getHangingCube().setHanging(null);
/*  48 */               gun.setHangingCube(null);
/*     */             }
/*     */             else {
/*  51 */               Location eyeloc = e.getPlayer().getEyeLocation();
/*  52 */               Vector dir = eyeloc.getDirection();
/*     */               
/*  54 */               for (double count = 1.0D; count <= 4.0D; count += 0.4D)
/*     */               {
/*  56 */                 Location loc = eyeloc.clone().add(dir.clone().multiply(count));
/*     */                 
/*  58 */                 if (!loc.getBlock().getType().isSolid())
/*     */                 {
/*  60 */                   for (Entity ent : loc.getWorld().getNearbyEntities(loc, 2.0D, 2.0D, 2.0D)) {
/*  61 */                     if (ent.getType().equals(EntityType.ARMOR_STAND))
/*     */                     {
/*  63 */                       Cube cube = CubeManager.getCube(ent.getUniqueId());
/*  64 */                       if (cube != null) {
/*  65 */                         cube.setHanging(p);
/*  66 */                         gun.setHangingCube(cube);
/*     */                       }
/*     */                     }
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onMove(PlayerMoveEvent e)
/*     */   {
/*  82 */     Player p = e.getPlayer();
/*  83 */     Cube cube = PortalDevice.getDeviceInstance(p).getHangingCube();
/*  84 */     if (cube != null)
/*     */     {
/*  86 */       Location eyeloc = p.getEyeLocation();
/*  87 */       Vector v = eyeloc.getDirection();
/*  88 */       Location rtn = eyeloc;
/*  89 */       for (double count = 1.0D; count <= 3.0D; count += 0.4D) {
/*  90 */         Location loc = eyeloc.clone().add(v.clone().multiply(count));
/*  91 */         if (loc.getBlock().getType().isSolid()) {
/*     */           break;
/*     */         }
/*  94 */         rtn = loc;
/*     */       }
/*     */       
/*  97 */       rtn.setPitch(0.0F);
/*  98 */       rtn.setYaw(eyeloc.getYaw());
/*     */       
/* 100 */       cube.getMarker().teleport(rtn);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalgun\HangingManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */