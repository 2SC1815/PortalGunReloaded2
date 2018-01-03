/*    */ package com.murabi10.portalgunreloaded;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ public class LongFallBoots implements Listener
/*    */ {
/*    */   public static ItemStack getBoots()
/*    */   {
/* 19 */     ItemStack item = new ItemStack(Material.IRON_BOOTS, 1, (short)0);
/* 20 */     ItemMeta meta = item.getItemMeta();
/* 21 */     meta.setDisplayName("長距離落下ブーツ");
/* 22 */     ArrayList<String> lore = new ArrayList();
/* 23 */     lore.add("Aperture Science");
/* 24 */     lore.add("Long Fall Boots :");
/* 25 */     lore.add("落下ダメージを無効化する");
/* 26 */     meta.setLore(lore);
/* 27 */     item.setItemMeta(meta);
/* 28 */     return item;
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onFall(EntityDamageEvent e) {
/* 33 */     if ((e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) && (e.getEntity().getType().equals(EntityType.PLAYER))) {
/* 34 */       ItemStack boots = ((Player)e.getEntity()).getInventory().getBoots();
/* 35 */       if ((boots != null) && (boots.getType().equals(getBoots().getType())) && (boots.hasItemMeta()) && (boots.getItemMeta().hasLore()) && (boots.getItemMeta().getLore().equals(getBoots().getItemMeta().getLore()))) {
/* 36 */         e.setCancelled(true);
/* 37 */         boots.setDurability((short)0);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\LongFallBoots.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */