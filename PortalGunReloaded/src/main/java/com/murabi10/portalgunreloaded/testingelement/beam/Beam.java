/*    */ package com.murabi10.portalgunreloaded.testingelement.beam;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.Methods;
/*    */ import com.murabi10.portalgunreloaded.portalgun.Portal;
/*    */ import com.murabi10.portalgunreloaded.testingelement.ElementType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ 
/*    */ public abstract class Beam extends TestingElement
/*    */ {
/*    */   private Location Origin;
/*    */   private BlockFace OriginFace;
/*    */   
/*    */   protected Beam(Location ChamberOrigin, ElementType type, BlockFace face, int X, int Y, int Z)
/*    */   {
/* 18 */     super(ChamberOrigin, type, com.murabi10.portalgunreloaded.testingelement.LinkType.INPUT, face, X, Y, Z);
/*    */   }
/*    */   
/*    */   protected void Run()
/*    */   {
/* 23 */     recursiveLaser(this.Origin, this.OriginFace);
/*    */   }
/*    */   
/*    */   private void recursiveLaser(Location loc, BlockFace bf) {
/* 27 */     for (double i = 0.0D; i < 70.0D; i += 0.2D) {
/* 28 */       Location currentLoc = loc.getBlock().getRelative(bf, (int)i).getLocation();
/*    */       
/* 30 */       Portal portal = Methods.getPortal(currentLoc, true);
/*    */       
/* 32 */       if ((portal != null) && (portal.getDest() != null)) {
/* 33 */         recursiveLaser(portal.getDest().getRepresentativeLocation(), portal.getDest().getLaunchDirection());
/* 34 */         break;
/*    */       }
/*    */       
/* 37 */       BlockFace rtn = laser(currentLoc, bf);
/* 38 */       if (rtn == null) {
/* 39 */         return;
/*    */       }
/* 41 */       if (!rtn.equals(bf))
/*    */       {
/* 43 */         recursiveLaser(currentLoc, rtn);
/* 44 */         break;
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public abstract BlockFace laser(Location paramLocation, BlockFace paramBlockFace);
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\beam\Beam.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */