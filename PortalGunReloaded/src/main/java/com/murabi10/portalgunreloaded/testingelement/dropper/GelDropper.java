/*    */ package com.murabi10.portalgunreloaded.testingelement.dropper;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.testingelement.ElementType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*    */ import com.murabi10.portalgunreloaded.testingelement.objects.Gel;
/*    */ import com.murabi10.portalgunreloaded.testingelement.objects.GelType;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.entity.ArmorStand;
/*    */ 
/*    */ public class GelDropper extends TestingElement
/*    */ {
/* 14 */   private transient Gel Gel = null;
/*    */   private GelType type;
/*    */   
/*    */   public GelDropper(Location OriginLoc, BlockFace Dir, GelType type, int x, int y, int z) {
/* 18 */     super(OriginLoc, ElementType.GEL_DROPPER, com.murabi10.portalgunreloaded.testingelement.LinkType.INPUT, Dir, x, y, z);
/* 19 */     this.type = type;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean check()
/*    */   {
/* 27 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected void destroy()
/*    */   {
/* 34 */     if (this.Gel != null) this.Gel.Destroy();
/* 35 */     this.Gel = null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected void Run()
/*    */   {
/* 42 */     setInput(true);
/*    */     
/* 44 */     if (Switches().size() != 0) {
/* 45 */       for (TestingElement e : Switches()) {
/* 46 */         if (!e.isOutput()) {
/* 47 */           setInput(false);
/* 48 */           break;
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 53 */     if ((isInput()) && ((this.Gel == null) || (this.Gel.getMarker().isDead()))) {
/* 54 */       this.Gel = new Gel(getRelative1(this.OriginLocation).clone().add(0.5D, -1.5D, 0.5D), this.type);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\dropper\GelDropper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */