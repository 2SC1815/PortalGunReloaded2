/*    */ package com.murabi10.portalgunreloaded.testchamber;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.BlockData;
/*    */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ 
/*    */ 
/*    */ public class TestChamber
/*    */   implements Serializable
/*    */ {
/* 15 */   private ArrayList<TestingElement> elements = new ArrayList();
/*    */   
/* 17 */   private Dimension Chamber = null;
/*    */   
/*    */   public TestChamber() {
/* 20 */     this.Chamber = Dimension.getStartingChamber();
/*    */   }
/*    */   
/*    */   public Dimension Chamber() {
/* 24 */     return this.Chamber;
/*    */   }
/*    */   
/*    */   public ArrayList<TestingElement> TestingElements() {
/* 28 */     return this.elements;
/*    */   }
/*    */   
/*    */ 
/*    */   public void placeToWorld(Location loc)
/*    */   {
/* 34 */     for (int x = 0; x < this.Chamber.getXSize(); x++) {
/* 35 */       for (int y = 0; y < this.Chamber.getYSize(); y++) {
/* 36 */         for (int z = 0; z < this.Chamber.getZSize(); z++) {
/* 37 */           Location tmp = loc.clone().add(x, y, z);
/* 38 */           BlockData b = this.Chamber.getBlock(x, y, z);
/* 39 */           tmp.getBlock().setType(b.getMaterial());
/* 40 */           tmp.getBlock().setData(b.GetData());
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void deleteFromWorld(Location loc)
/*    */   {
/* 48 */     for (int x = 0; x < this.Chamber.getXSize(); x++) {
/* 49 */       for (int y = 0; y < this.Chamber.getYSize(); y++) {
/* 50 */         for (int z = 0; z < this.Chamber.getZSize(); z++) {
/* 51 */           Location tmp = loc.clone().add(x, y, z);
/* 52 */           tmp.getBlock().setType(Material.AIR);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void importFromWorld(Location loc)
/*    */   {
/* 61 */     for (int x = 0; x < this.Chamber.getXSize(); x++) {
/* 62 */       for (int y = 0; y < this.Chamber.getYSize(); y++) {
/* 63 */         for (int z = 0; z < this.Chamber.getZSize(); z++) {
/* 64 */           Block tmp = loc.clone().add(x, y, z).getBlock();
/* 65 */           this.Chamber.setBlock(x, y, z, new BlockData(tmp.getType(), tmp.getData()));
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testchamber\TestChamber.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */