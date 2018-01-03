/*    */ package com.murabi10.portalgunreloaded.testingelement.logicgate;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.testingelement.ElementType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ 
/*    */ public class NOT extends TestingElement
/*    */ {
/*    */   public NOT(Location OriginLoc, int x, int y, int z)
/*    */   {
/* 14 */     super(OriginLoc, ElementType.NOT, com.murabi10.portalgunreloaded.testingelement.LinkType.OUT_IN, BlockFace.SOUTH, x, y, z);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean check()
/*    */   {
/* 20 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void destroy() {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void Run()
/*    */   {
/* 34 */     getRelative1(this.OriginLocation).getBlock().setType(isEditMode() ? Material.GOLD_BLOCK : Material.AIR);
/*    */     
/* 36 */     setInput(true);
/* 37 */     if (Switches().size() != 0) {
/* 38 */       for (TestingElement e : Switches()) {
/* 39 */         if (!e.isOutput()) {
/* 40 */           setInput(false);
/* 41 */           break;
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 46 */     setOutput(!isInput());
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\logicgate\NOT.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */