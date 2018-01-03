/*    */ package com.murabi10.portalgunreloaded.testingelement.fixture;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.portalgun.PortalColor;
/*    */ import com.murabi10.portalgunreloaded.portalgun.PortalDevice;
/*    */ import com.murabi10.portalgunreloaded.testingelement.ElementType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ 
/*    */ public class PortalSpawner extends TestingElement
/*    */ {
/* 15 */   private transient boolean oldActivated = true;
/* 16 */   transient int i = 0;
/*    */   private PortalColor color;
/*    */   private BlockFace dir2;
/*    */   
/*    */   public PortalSpawner(Location OriginLoc, BlockFace dir, BlockFace dir2, int x, int y, int z, PortalColor c) {
/* 21 */     super(OriginLoc, ElementType.PORTAL_SPAWNER, com.murabi10.portalgunreloaded.testingelement.LinkType.INPUT, dir, x, y, z);
/* 22 */     this.dir2 = dir2;
/* 23 */     this.color = c;
/*    */   }
/*    */   
/*    */   public boolean check()
/*    */   {
/* 28 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */   protected void destroy()
/*    */   {
/* 34 */     getRelative1(this.OriginLocation).getBlock().setType(Material.AIR);
/*    */   }
/*    */   
/*    */ 
/*    */   protected void Run()
/*    */   {
/* 40 */     if (this.i >= 3)
/*    */     {
/* 42 */       getRelative1(this.OriginLocation).getBlock().setType(Material.STONE);
/* 43 */       getRelative1(this.OriginLocation).getBlock().setData((byte)4);
/*    */       
/* 45 */       setInput(true);
/* 46 */       if (Switches().size() != 0) {
/* 47 */         for (TestingElement e : Switches()) {
/* 48 */           if (!e.isOutput()) {
/* 49 */             setInput(false);
/* 50 */             break;
/*    */           }
/*    */         }
/*    */       }
/*    */       
/* 55 */       if ((isInput() != this.oldActivated) && (isInput()))
/*    */       {
/* 57 */         PortalDevice d = PortalDevice.getDeviceInstance(getTargetPlayer());
/* 58 */         d.LaunchPortal(this.color, getRelative1(this.OriginLocation).getBlock().getRelative(getDirection()).getLocation(), getDirection(), this.dir2);
/*    */       }
/*    */       
/*    */ 
/* 62 */       this.oldActivated = isInput();
/* 63 */       this.i = 0;
/*    */     }
/* 65 */     this.i += 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\fixture\PortalSpawner.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */