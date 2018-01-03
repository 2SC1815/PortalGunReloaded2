/*    */ package com.murabi10.portalgunreloaded.devices;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ public class Device
/*    */ {
/*    */   private Function func;
/*    */   private ItemStack item;
/*    */   private String name;
/*    */   
/*    */   public Device(Function function, Material material, short dataValue, String RegistName, String Name, String... Lore)
/*    */   {
/* 18 */     this.name = RegistName;
/* 19 */     this.func = function;
/* 20 */     ItemStack item = new ItemStack(material, 1, dataValue);
/* 21 */     ItemMeta meta = item.getItemMeta();
/* 22 */     meta.setDisplayName(Name);
/* 23 */     ArrayList<String> lore = new ArrayList();
/* 24 */     for (int i = 0; i < Lore.length; i++) {
/* 25 */       lore.add(Lore[i]);
/*    */     }
/* 27 */     meta.setLore(lore);
/* 28 */     item.setItemMeta(meta);
/* 29 */     this.item = item;
/*    */   }
/*    */   
/*    */   public ItemStack getItem() {
/* 33 */     return this.item;
/*    */   }
/*    */   
/*    */   public void runFunction(Player p, Action a) {
/* 37 */     this.func.func(p, a);
/*    */   }
/*    */   
/*    */   public String getName() {
/* 41 */     return this.name;
/*    */   }
/*    */   
/*    */   public void Register() {
/* 45 */     DeviceManager.add(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\devices\Device.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */