/*    */ package com.murabi10.portalgunreloaded.testingelement.fixture;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.Cuboid;
/*    */ import com.murabi10.portalgunreloaded.testingelement.ElementType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.LinkType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ 
/*    */ public class Door extends TestingElement
/*    */ {
/* 16 */   private transient boolean oldActivated = true;
/*    */   
/*    */   public Door(Location OriginLoc, int x1, int y1, int z1, int x2, int y2, int z2) {
/* 19 */     super(OriginLoc, ElementType.DOOR, LinkType.INPUT, BlockFace.SOUTH, x1, y1, z1, x2, y2, z2);
/*    */   }
/*    */   
/*    */   public boolean check()
/*    */   {
/* 24 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected void destroy()
/*    */   {
/* 31 */     for (Block b : new Cuboid(getRelative1(this.OriginLocation), getRelative2(this.OriginLocation))) {
/* 32 */       b.setType(Material.AIR);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/* 37 */   private transient int i = 0;
/*    */   
/*    */   protected void Run()
/*    */   {
/* 41 */     if (this.i >= 5)
/*    */     {
/* 43 */       setInput(true);
/* 44 */       if (Switches().size() != 0) {
/* 45 */         for (TestingElement e : Switches()) {
/* 46 */           if (!e.isOutput()) {
/* 47 */             setInput(false);
/* 48 */             break;
/*    */           }
/*    */         }
/*    */       }
/*    */       
/* 53 */       if (isInput() != this.oldActivated)
/*    */       {
/*    */ 
/* 56 */         for (Block b : new Cuboid(getRelative1(this.OriginLocation), getRelative2(this.OriginLocation)))
/*    */         {
/* 58 */           this.OriginLocation.getWorld().playSound(b.getLocation(), 
/* 59 */             isInput() ? Sound.BLOCK_PISTON_CONTRACT : Sound.BLOCK_PISTON_EXTEND, 0.9F, 
/* 60 */             1.0F);
/*    */         }
/*    */         
/*    */ 
/* 64 */         if (isEditMode()) {
/* 65 */           for (Block b : new Cuboid(getRelative1(this.OriginLocation), getRelative2(this.OriginLocation)))
/*    */           {
/* 67 */             if (isInput()) {
/* 68 */               b.setType(Material.AIR);
/*    */             } else {
/* 70 */               b.setType(Material.IRON_BLOCK);
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/*    */       
/*    */ 
/*    */ 
/* 78 */       if (!isEditMode()) {
/* 79 */         for (Block b : new Cuboid(getRelative1(this.OriginLocation), getRelative2(this.OriginLocation)))
/*    */         {
/* 81 */           if (isInput()) {
/* 82 */             b.setType(Material.AIR);
/*    */           } else {
/* 84 */             b.setType(Material.IRON_BLOCK);
/*    */           }
/*    */         }
/*    */       }
/*    */       
/*    */ 
/* 90 */       this.oldActivated = isInput();
/* 91 */       this.i = 0;
/*    */     }
/* 93 */     this.i += 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\fixture\Door.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */