/*    */ package com.murabi10.portalgunreloaded2.testingelement.area;
import com.murabi10.portalgunreloaded2.Cuboid;
import com.murabi10.portalgunreloaded2.Methods;
import com.murabi10.portalgunreloaded2.testingelement.ElementType;
import com.murabi10.portalgunreloaded2.testingelement.LinkType;
import com.murabi10.portalgunreloaded2.testingelement.TestingElement;

/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class AreaSwitch extends TestingElement
/*    */ {
/*    */   public AreaSwitch(Location OriginLoc, int x1, int y1, int z1, int x2, int y2, int z2)
/*    */   {
/* 19 */     super(OriginLoc, ElementType.AREA_SWITCH, LinkType.OUTPUT, BlockFace.SOUTH, x1, y1, z1, x2, y2, z2);
/*    */   }
/*    */   
/*    */   public boolean check()
/*    */   {
/* 24 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 32 */   private transient int i = 0;
/*    */   
/*    */   protected void destroy() {}
/*    */   
/* 36 */   protected void Run() { if (this.i >= 4)
/*    */     {
/* 38 */       setOutput(false);
/*    */       
/* 40 */       for (Block b : new Cuboid(getRelative1(this.OriginLocation), getRelative2(this.OriginLocation))) {
/* 41 */         Location loc = b.getLocation();
/*    */         
/* 43 */         for (Entity ent : loc.getWorld().getNearbyEntities(loc, 2.0D, 2.0D, 2.0D)) {
/* 44 */           if (((ent instanceof Player)) && 
/* 45 */             (Methods.LocationEquals(ent.getLocation(), loc)))
/*    */           {
/* 47 */             setOutput(true);
/* 48 */             break;
/*    */           }
/*    */         }
/*    */         
/*    */ 
/*    */ 
/* 54 */         if (isEditMode()) {
/* 55 */           Methods.spawnParticle(Methods.LocationNormalize(loc), (byte)100, (byte)-56, (byte)100);
/*    */         }
/*    */       }
/*    */       
/*    */ 
/* 60 */       getRelative1(this.OriginLocation).getBlock().setType(isEditMode() ? Material.GOLD_BLOCK : Material.AIR);
/*    */       
/* 62 */       this.i = 0;
/*    */     }
/* 64 */     this.i += 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\area\AreaSwitch.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */