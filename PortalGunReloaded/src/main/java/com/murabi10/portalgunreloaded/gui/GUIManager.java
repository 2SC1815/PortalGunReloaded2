/*    */ package com.murabi10.portalgunreloaded.gui;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public class GUIManager implements org.bukkit.event.Listener
/*    */ {
/* 13 */   public static HashMap<String, GUI> GUIs = new HashMap();
/*    */   
/*    */   @EventHandler
/*    */   public void onClickInv(InventoryClickEvent e) {
/* 17 */     String name = e.getInventory().getName();
/* 18 */     if (GUIs.containsKey(name)) { Iterator localIterator2;
/* 19 */       for (Iterator localIterator1 = GUIs.keySet().iterator(); localIterator1.hasNext(); 
/*    */           
/* 21 */           localIterator2.hasNext())
/*    */       {
/* 19 */         String key = (String)localIterator1.next();
/* 20 */         GUI gui = (GUI)GUIs.get(key);
/* 21 */         localIterator2 = gui.getButtons().iterator(); continue;Button button = (Button)localIterator2.next();
/* 22 */         if ((e.getCurrentItem() != null) && (button != null) && (button.getIcon() != null) && (button.getIcon().equals(e.getCurrentItem()))) {
/* 23 */           boolean c = button.runFunction((org.bukkit.entity.Player)e.getWhoClicked(), e.getClick());
/* 24 */           e.setCancelled(true);
/* 25 */           if (c) {
/* 26 */             e.getWhoClicked().closeInventory();
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\gui\GUIManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */