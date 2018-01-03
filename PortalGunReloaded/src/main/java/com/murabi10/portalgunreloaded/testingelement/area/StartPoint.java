/*    */ package com.murabi10.portalgunreloaded.testingelement.area;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.Methods;
/*    */ import com.murabi10.portalgunreloaded.testingelement.ElementType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ 
/*    */ public class StartPoint extends TestingElement
/*    */ {
/*    */   public StartPoint(Location OriginLoc, int x, int y, int z)
/*    */   {
/* 14 */     super(OriginLoc, ElementType.START_POINT, com.murabi10.portalgunreloaded.testingelement.LinkType.DNC, BlockFace.SOUTH, x, y, z);
/*    */   }
/*    */   
/*    */   public boolean check()
/*    */   {
/* 19 */     Location loc = getRelative1(this.OriginLocation);
/* 20 */     return (loc.getBlock().isEmpty()) && (loc.getBlock().getRelative(BlockFace.UP).isEmpty());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected void destroy() {}
/*    */   
/*    */ 
/*    */   protected void Run()
/*    */   {
/* 30 */     if (isEditMode()) {
/* 31 */       Location loc = Methods.LocationNormalize(getRelative1(this.OriginLocation));
/* 32 */       Methods.spawnParticle(loc, (byte)10, (byte)-56, (byte)-56);
/* 33 */       Methods.spawnParticle(loc.clone().add(0.0D, 1.0D, 0.0D), (byte)10, (byte)-56, (byte)-56);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\area\StartPoint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */