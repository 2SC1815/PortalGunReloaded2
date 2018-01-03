/*    */ package com.murabi10.portalgunreloaded2.testingelement.objects;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ public enum CubeType
/*    */ {
/*  7 */   NORMAL(Material.REDSTONE_BLOCK), 
/*  8 */   COMPANION(Material.EMERALD_BLOCK), 
/*  9 */   REFLECTION(Material.DISPENSER);
/*    */   
/*    */ 
/*    */   private Material cube;
/*    */   
/*    */   private CubeType(Material material)
/*    */   {
/* 16 */     this.cube = material;
/*    */   }
/*    */   
/*    */   public Material getmaterial()
/*    */   {
/* 21 */     return this.cube;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\objects\CubeType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */