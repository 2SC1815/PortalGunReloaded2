/*    */ package com.murabi10.portalgunreloaded.testingelement.dropper;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.testingelement.ElementType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*    */ import com.murabi10.portalgunreloaded.testingelement.objects.Cube;
/*    */ import com.murabi10.portalgunreloaded.testingelement.objects.CubeType;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.entity.ArmorStand;
/*    */ 
/*    */ public class CubeDropper extends TestingElement
/*    */ {
/* 14 */   private boolean oldActivated = false;
/* 15 */   private transient Cube cube = null;
/*    */   private CubeType type;
/*    */   
/*    */   public CubeDropper(Location OriginLoc, BlockFace Dir, CubeType type, int x, int y, int z) {
/* 19 */     super(OriginLoc, ElementType.CUBE_DROPPER, com.murabi10.portalgunreloaded.testingelement.LinkType.INPUT, Dir, x, y, z);
/* 20 */     this.type = type;
/*    */   }
/*    */   
/*    */   public boolean check()
/*    */   {
/* 25 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected void destroy()
/*    */   {
/* 32 */     if (this.cube != null) this.cube.Destroy(false);
/* 33 */     this.cube = null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected void Run()
/*    */   {
/* 40 */     setInput(true);
/*    */     
/* 42 */     if (Switches().size() != 0) {
/* 43 */       for (TestingElement e : Switches()) {
/* 44 */         if (!e.isOutput()) {
/* 45 */           setInput(false);
/* 46 */           break;
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 51 */     if ((this.cube == null) || (this.cube.getMarker() == null) || ((this.cube.getMarker() != null) && (this.cube.getMarker().isDead()))) {
/* 52 */       this.cube = new Cube(getRelative1(this.OriginLocation).clone().add(0.5D, -1.5D, 0.5D), this.type);
/*    */     }
/*    */     
/* 55 */     if ((isInput() != this.oldActivated) && (isInput())) {
/* 56 */       if (this.cube != null) {
/* 57 */         this.cube.Destroy(false);
/*    */       }
/* 59 */       this.cube = new Cube(getRelative1(this.OriginLocation).clone().add(0.5D, -1.5D, 0.5D), this.type);
/*    */     }
/*    */     
/*    */ 
/* 63 */     this.oldActivated = isInput();
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\dropper\CubeDropper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */