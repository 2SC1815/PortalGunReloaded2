/*    */ package com.murabi10.portalgunreloaded2.testingelement.objects;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ public enum GelType {
/*  6 */   REPULSION(Material.WOOL, (byte)11), 
/*  7 */   PROPULSION(Material.WOOL, (byte)1), 
/*  8 */   WATER(Material.ICE, (byte)0);
/*    */   
/*    */   private Material gel;
/*    */   private byte data;
/*    */   
/*    */   private GelType(Material material, byte data) {
/* 14 */     this.gel = material;
/* 15 */     this.data = data;
/*    */   }
/*    */   
/*    */   public Material getmaterial()
/*    */   {
/* 20 */     return this.gel;
/*    */   }
/*    */   
/*    */ 
/*    */   public byte getData()
/*    */   {
/* 26 */     return this.data;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\objects\GelType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */