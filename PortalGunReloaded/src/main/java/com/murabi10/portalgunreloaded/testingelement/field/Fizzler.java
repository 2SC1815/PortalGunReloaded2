/*    */ package com.murabi10.portalgunreloaded.testingelement.field;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.Cuboid;
/*    */ import com.murabi10.portalgunreloaded.Methods;
/*    */ import com.murabi10.portalgunreloaded.portalgun.PortalDevice;
/*    */ import com.murabi10.portalgunreloaded.testingelement.ElementType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.LinkType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*    */ import com.murabi10.portalgunreloaded.testingelement.objects.Cube;
/*    */ import com.murabi10.portalgunreloaded.testingelement.objects.CubeManager;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class Fizzler extends TestingElement
/*    */ {
/*    */   public Fizzler(Location OriginLoc, int x1, int y1, int z1, int x2, int y2, int z2)
/*    */   {
/* 25 */     super(OriginLoc, ElementType.MATERIAL_EMANCIPATION_GRILL, LinkType.INPUT, BlockFace.SOUTH, x1, y1, z1, x2, y2, z2);
/*    */   }
/*    */   
/*    */   public boolean check()
/*    */   {
/* 30 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 38 */   private transient int i = 0;
/* 39 */   transient Random rnd = null;
/*    */   
/*    */   protected void destroy() {}
/*    */   
/* 43 */   protected void Run() { if (this.i >= 2)
/*    */     {
/*    */ 
/* 46 */       getRelative1(this.OriginLocation).getBlock().setType(isEditMode() ? Material.IRON_BLOCK : Material.AIR);
/*    */       
/* 48 */       setInput(true);
/* 49 */       if (Switches().size() != 0) {
/* 50 */         for (TestingElement e : Switches()) {
/* 51 */           if (!e.isOutput()) {
/* 52 */             setInput(false);
/* 53 */             break;
/*    */           }
/*    */         }
/*    */       }
/*    */       
/* 58 */       if (isInput())
/*    */       {
/* 60 */         if (this.rnd == null) {
/* 61 */           this.rnd = new Random();
/*    */         }
/*    */         
/* 64 */         for (Block b : new Cuboid(getRelative1(this.OriginLocation), getRelative2(this.OriginLocation))) {
/* 65 */           Location loc = b.getLocation();
/*    */           
/* 67 */           for (Entity ent : loc.getWorld().getNearbyEntities(loc, 2.0D, 2.0D, 2.0D)) {
/* 68 */             if (Methods.LocationEquals(ent.getLocation(), loc)) {
/* 69 */               Cube c = CubeManager.getCube(ent.getUniqueId());
/*    */               
/* 71 */               if (c != null) {
/* 72 */                 c.Destroy(true);
/*    */ 
/*    */ 
/*    */               }
/* 76 */               else if ((ent instanceof Player))
/*    */               {
/* 78 */                 PortalDevice.getDeviceInstance(ent.getUniqueId()).clearPortal();
/*    */               }
/*    */             }
/*    */           }
/*    */           
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 87 */           Methods.spawnParticle(loc.clone().add(this.rnd.nextDouble(), this.rnd.nextDouble(), this.rnd.nextDouble()), 
/* 88 */             (byte)-71, (byte)-3, (byte)-3);
/*    */         }
/*    */       }
/*    */       
/* 92 */       this.i = 0;
/*    */     }
/* 94 */     this.i += 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\field\Fizzler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */