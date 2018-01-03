/*    */ package com.murabi10.portalgunreloaded.testingelement.logicgate;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.testingelement.ElementType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ 
/*    */ public class RSOutputInterface extends TestingElement
/*    */ {
/*    */   public RSOutputInterface(Location OriginLoc, int x, int y, int z)
/*    */   {
/* 14 */     super(OriginLoc, ElementType.RSOUTPUT, com.murabi10.portalgunreloaded.testingelement.LinkType.INPUT, BlockFace.SOUTH, x, y, z);
/*    */   }
/*    */   
/*    */   public boolean check()
/*    */   {
/* 19 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */   protected void destroy() {}
/*    */   
/*    */ 
/*    */   protected void Run()
/*    */   {
/* 28 */     setInput(true);
/* 29 */     if (Switches().size() != 0) {
/* 30 */       for (TestingElement e : Switches()) {
/* 31 */         if (!e.isOutput()) {
/* 32 */           setInput(false);
/* 33 */           break;
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 38 */     getRelative1(this.OriginLocation).getBlock().setType(isInput() ? Material.REDSTONE_BLOCK : Material.IRON_BLOCK);
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\logicgate\RSOutputInterface.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */