/*    */ package com.murabi10.portalgunreloaded.selector;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*    */ import org.bukkit.inventory.ItemStack;

/*    */
/*    */ import com.murabi10.portalgunreloaded.portalgun.PortalDevice;
/*    */
/*    */ public class ItemClickManager implements org.bukkit.event.Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onClickInv(InventoryClickEvent e)
/*    */   {
/* 15 */     ItemStack item = e.getCurrentItem();
/* 16 */     if (item != null)
/*    */     {
/* 18 */       PortalDevice d = PortalDevice.getDeviceInstance(e.getWhoClicked().getUniqueId());
/*    */
/* 20 */       if ((d != null) && (d.canItemInput())) {
/* 21 */         if ((item.hasItemMeta()) && (item.getItemMeta().hasDisplayName()) && (item.getItemMeta().hasLore())) {
/* 22 */           d.setInputItem(item);
/* 23 */           d.setItemInput(false);
/*    */         }
/* 25 */         e.setCancelled(true);
/*    */       }
/*    */     }
/*    */   }
/*    */
/*    */
/*    */   @EventHandler
/*    */   public void onCloseInv(InventoryCloseEvent e)
/*    */   {
/* 34 */     PortalDevice d = PortalDevice.getDeviceInstance(e.getPlayer().getUniqueId());
/*    */
/* 36 */     if ((d != null) && (d.canItemInput())) {
/* 37 */       d.setItemInput(false);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\selector\ItemClickManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */