/*    */ package com.murabi10.portalgunreloaded.testingelement.logicgate;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.BlockFace;

/*    */
/*    */ import com.murabi10.portalgunreloaded.testingelement.ElementType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.LinkType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*    */
/*    */ public class RSInputInterface extends TestingElement
/*    */ {
/*    */   public RSInputInterface(Location OriginLoc, int x, int y, int z)
/*    */   {
/* 15 */     super(OriginLoc, ElementType.RSINPUT, LinkType.OUTPUT, BlockFace.SOUTH, x, y, z);
/*    */   }
/*    */
/*    */   public boolean check()
/*    */   {
/* 20 */     return true;
/*    */   }
/*    */
/*    */
/*    */   protected void destroy() {}
/*    */
/*    */
/*    */   protected void Run()
/*    */   {
/* 29 */     getRelative1(this.OriginLocation).getBlock().setType(Material.IRON_BLOCK);
/* 30 */     setOutput(getRelative1(this.OriginLocation).getBlock().getBlockPower() >= 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\logicgate\RSInputInterface.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */