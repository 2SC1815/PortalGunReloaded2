/*    */ package com.murabi10.portalgunreloaded.testingelement.field;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.Cuboid;
/*    */ import com.murabi10.portalgunreloaded.Methods;
/*    */ import com.murabi10.portalgunreloaded.testingelement.ElementType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.LinkType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class LaserField extends TestingElement
/*    */ {
/*    */   public LaserField(Location OriginLoc, int x1, int y1, int z1, int x2, int y2, int z2)
/*    */   {
/* 22 */     super(OriginLoc, ElementType.LASER_FIELD, LinkType.INPUT, BlockFace.SOUTH, x1, y1, z1, x2, y2, z2);
/*    */   }
/*    */   
/*    */   public boolean check()
/*    */   {
/* 27 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 35 */   private transient int i = 0;
/* 36 */   transient Random rnd = null;
/*    */   
/*    */   protected void destroy() {}
/*    */   
/*    */   protected void Run() {
/* 41 */     if (this.i >= 2)
/*    */     {
/*    */ 
/* 44 */       getRelative1(this.OriginLocation).getBlock().setType(isEditMode() ? Material.IRON_BLOCK : Material.AIR);
/*    */       
/* 46 */       setInput(true);
/* 47 */       if (Switches().size() != 0) {
/* 48 */         for (TestingElement e : Switches()) {
/* 49 */           if (!e.isOutput()) {
/* 50 */             setInput(false);
/* 51 */             break;
/*    */           }
/*    */         }
/*    */       }
/*    */       
/* 56 */       if (isInput()) {
/* 57 */         if (this.rnd == null) {
/* 58 */           this.rnd = new Random();
/*    */         }
/*    */         
/* 61 */         for (Block b : new Cuboid(getRelative1(this.OriginLocation), getRelative2(this.OriginLocation))) {
/* 62 */           Location loc = b.getLocation();
/*    */           
/* 64 */           for (Entity ent : loc.getWorld().getNearbyEntities(loc, 2.0D, 2.0D, 2.0D)) {
/* 65 */             if (Methods.LocationEquals(ent.getLocation(), loc))
/*    */             {
/* 67 */               if ((!ent.getType().equals(EntityType.ARMOR_STAND)) && ((ent instanceof LivingEntity)) && (
/* 68 */                 (!ent.getType().equals(EntityType.PLAYER)) || (!isEditMode())))
/*    */               {
/*    */ 
/* 71 */                 ((LivingEntity)ent).damage(1000.0D);
/*    */               }
/*    */             }
/*    */           }
/*    */           
/*    */ 
/* 77 */           Methods.spawnParticle(loc.clone().add(this.rnd.nextDouble(), this.rnd.nextDouble(), this.rnd.nextDouble()), 
/* 78 */             (byte)-2, (byte)45, (byte)45);
/*    */         }
/*    */       }
/*    */       
/* 82 */       this.i = 0;
/*    */     }
/* 84 */     this.i += 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\field\LaserField.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */