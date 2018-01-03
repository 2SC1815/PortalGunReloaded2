/*    */ package com.murabi10.portalgunreloaded2.testingelement.beam;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.BlockFace;

import com.murabi10.portalgunreloaded2.testingelement.ElementType;
/*    */ 
/*    */ public class ExcursionFunnel extends Beam
/*    */ {
/*    */   protected ExcursionFunnel(Location ChamberOrigin, ElementType type, BlockFace face, int X, int Y, int Z)
/*    */   {
/* 11 */     super(ChamberOrigin, type, face, X, Y, Z);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public BlockFace laser(Location loc, BlockFace bf)
/*    */   {
/* 18 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean check()
/*    */   {
/* 24 */     return false;
/*    */   }
/*    */   
/*    */   public void destroy() {}
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\beam\ExcursionFunnel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */