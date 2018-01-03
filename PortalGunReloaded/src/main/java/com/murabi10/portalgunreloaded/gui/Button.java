/*    */ package com.murabi10.portalgunreloaded.gui;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.ClickType;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Button
/*    */ {
/*    */   private ItemStack icon;
/*    */   private int Location;
/*    */   private GUIFunction func;
/*    */   
/*    */   public Button(Material material, int stack, short dataValue, String Name, int iconLocation, GUIFunction function, String... Lore)
/*    */   {
/* 21 */     this.func = function;
/* 22 */     this.Location = iconLocation;
/* 23 */     ItemStack item = new ItemStack(material, stack, dataValue);
/* 24 */     ItemMeta meta = item.getItemMeta();
/* 25 */     meta.setDisplayName(Name);
/* 26 */     ArrayList<String> lore = new ArrayList();
/* 27 */     for (int i = 0; i < Lore.length; i++) {
/* 28 */       lore.add(ChatColor.RESET + Lore[i]);
/*    */     }
/* 30 */     meta.setLore(lore);
/* 31 */     item.setItemMeta(meta);
/* 32 */     this.icon = item;
/*    */   }
/*    */   
/*    */   public boolean runFunction(Player p, ClickType type) {
/* 36 */     return this.func.click(p, type);
/*    */   }
/*    */   
/*    */   public int getLoc() {
/* 40 */     return this.Location;
/*    */   }
/*    */   
/*    */   public ItemStack getIcon() {
/* 44 */     return this.icon;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\gui\Button.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */