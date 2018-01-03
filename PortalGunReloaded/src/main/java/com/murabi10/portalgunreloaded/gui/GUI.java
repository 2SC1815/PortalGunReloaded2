/*    */ package com.murabi10.portalgunreloaded.gui;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public abstract class GUI
/*    */ {
/* 12 */   private ArrayList<Button> buttons = new ArrayList();
/* 13 */   private String GUIName = "menu";
/* 14 */   private int size = 1;
/*    */   
/*    */   public GUI() {
/* 17 */     nativeInit();
/*    */   }
/*    */   
/*    */   protected void nativeInit() {
/* 21 */     init();
/* 22 */     GUIManager.GUIs.put(this.GUIName, this);
/*    */   }
/*    */   
/*    */   protected void addbutton(Button button) {
/* 26 */     this.buttons.add(button);
/*    */   }
/*    */   
/*    */   public String getMenuName()
/*    */   {
/* 31 */     return this.GUIName;
/*    */   }
/*    */   
/*    */   protected void setMenuName(String name) {
/* 35 */     this.GUIName = name;
/*    */   }
/*    */   
/*    */   protected void setSize(int size) {
/* 39 */     this.size = size;
/*    */   }
/*    */   
/*    */   public abstract void init();
/*    */   
/*    */   public ArrayList<Button> getButtons() {
/* 45 */     return this.buttons;
/*    */   }
/*    */   
/*    */   public void openGUI(Player p) {
/* 49 */     Inventory UI = Bukkit.getServer().createInventory(null, 9 * this.size, this.GUIName);
/* 50 */     for (Button button : this.buttons) {
/* 51 */       UI.setItem(button.getLoc(), button.getIcon());
/*    */     }
/* 53 */     p.openInventory(UI);
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\gui\GUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */