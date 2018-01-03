/*    */ package com.murabi10.portalgunreloaded.testingelement.fixture;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.PortalGun;
/*    */ import com.murabi10.portalgunreloaded.testingelement.ElementType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.ItemFrame;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class Indicator extends TestingElement
/*    */ {
/* 17 */   private transient ItemFrame frame = null;
/*    */   
/*    */   public Indicator(Location OriginLoc, BlockFace Dir, int x, int y, int z) {
/* 20 */     super(OriginLoc, ElementType.ITEM_FRAME, com.murabi10.portalgunreloaded.testingelement.LinkType.INPUT, Dir, x, y, z);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean check()
/*    */   {
/* 26 */     return (getRelative1(this.OriginLocation).getBlock().getType().isSolid()) && 
/* 27 */       (getRelative1(this.OriginLocation).getBlock().getRelative(getDirection()).isEmpty());
/*    */   }
/*    */   
/*    */   protected void destroy()
/*    */   {
/* 32 */     if (this.frame != null) {
/* 33 */       this.frame.setItem(new ItemStack(Material.AIR));
/* 34 */       this.frame.remove();
/* 35 */       this.frame = null;
/*    */     }
/*    */   }
/*    */   
/* 39 */   private transient int i = 0;
/*    */   
/*    */   protected void Run()
/*    */   {
/* 43 */     if (this.i >= 5)
/*    */     {
/* 45 */       if (this.frame == null) {
/* 46 */         this.frame = ((ItemFrame)this.OriginLocation.getWorld().spawnEntity(
/* 47 */           getRelative1(this.OriginLocation).getBlock().getRelative(getDirection()).getLocation(), 
/* 48 */           EntityType.ITEM_FRAME));
/* 49 */         this.frame.setFacingDirection(getDirection());
/* 50 */         ItemStack i = new ItemStack(Material.MAP);
/* 51 */         i.setDurability(isInput() ? PortalGun.ON_INDICATOR_MAP_ID : PortalGun.OFF_INDICATOR_MAP_ID);
/*    */         
/* 53 */         if (i != null) {
/* 54 */           this.frame.setItem(i);
/*    */         }
/*    */       }
/*    */       
/* 58 */       setInput(true);
/* 59 */       if (Switches().size() != 0) {
/* 60 */         for (TestingElement e : Switches()) {
/* 61 */           if (!e.isOutput()) {
/* 62 */             setInput(false);
/* 63 */             break;
/*    */           }
/*    */         }
/*    */       }
/*    */       
/* 68 */       ItemStack item = new ItemStack(Material.MAP);
/* 69 */       item.setDurability(isInput() ? PortalGun.ON_INDICATOR_MAP_ID : PortalGun.OFF_INDICATOR_MAP_ID);
/*    */       
/* 71 */       this.frame.setItem(item);
/*    */       
/*    */ 
/* 74 */       this.i = 0;
/*    */     }
/* 76 */     this.i += 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\fixture\Indicator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */