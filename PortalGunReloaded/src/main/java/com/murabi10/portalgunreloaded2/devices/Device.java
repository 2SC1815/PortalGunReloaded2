 package com.murabi10.portalgunreloaded2.devices;

 import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

 public class Device
 {
   private Function func;
   private ItemStack item;
   private String name;

   public Device(Function function, Material material, short dataValue, String RegistName, String Name, String... Lore)
   {
     this.name = RegistName;
     this.func = function;
     ItemStack item = new ItemStack(material, 1, dataValue);
     ItemMeta meta = item.getItemMeta();
     meta.setDisplayName(Name);
     ArrayList<String> lore = new ArrayList<String>();
     for (int i = 0; i < Lore.length; i++) {
       lore.add(Lore[i]);
     }
     meta.setLore(lore);
     item.setItemMeta(meta);
     this.item = item;
   }

   public ItemStack getItem() {
     return this.item;
   }

   public void runFunction(Player p, Action a) {
     this.func.func(p, a);
   }

   public String getName() {
     return this.name;
   }

   public void Register() {
     DeviceManager.add(this);
   }
 }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\devices\Device.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */