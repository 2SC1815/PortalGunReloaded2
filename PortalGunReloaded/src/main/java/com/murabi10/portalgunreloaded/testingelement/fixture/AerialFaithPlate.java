/*    */ package com.murabi10.portalgunreloaded.testingelement.fixture;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class AerialFaithPlate extends TestingElement
/*    */ {
/*    */   private float power;
/*    */   private float Yaw;
/*    */   private float Pitch;
/*    */   
/*    */   public float getPower()
/*    */   {
/* 18 */     return this.power;
/*    */   }
/*    */   
/*    */   public void setPower(float power) {
/* 22 */     this.power = power;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public float getYaw()
/*    */   {
/* 30 */     return this.Yaw;
/*    */   }
/*    */   
/*    */   public void setYaw(float yaw) {
/* 34 */     this.Yaw = yaw;
/*    */   }
/*    */   
/*    */ 
/*    */   public float getPitch()
/*    */   {
/* 40 */     return this.Pitch;
/*    */   }
/*    */   
/*    */   public void setPitch(float pitch) {
/* 44 */     this.Pitch = pitch;
/*    */   }
/*    */   
/*    */   public AerialFaithPlate(Location OriginLoc, int x, int y, int z, float yaw, float pitch, int power) {
/* 48 */     super(OriginLoc, com.murabi10.portalgunreloaded.testingelement.ElementType.AERIAL_FAITH_PLATE, com.murabi10.portalgunreloaded.testingelement.LinkType.DNC, org.bukkit.block.BlockFace.SOUTH, x, y, z);
/* 49 */     this.Yaw = yaw;
/* 50 */     this.Pitch = pitch;
/* 51 */     this.power = power;
/*    */   }
/*    */   
/*    */   public boolean check()
/*    */   {
/* 56 */     return getRelative1(this.OriginLocation).getBlock().getType().equals(Material.PISTON_BASE);
/*    */   }
/*    */   
/*    */   protected void destroy()
/*    */   {
/* 61 */     getRelative1(this.OriginLocation).getBlock().setType(Material.AIR);
/*    */   }
/*    */   
/*    */   protected void Run()
/*    */   {
/* 66 */     for (Entity ent : this.OriginLocation.getWorld().getNearbyEntities(getRelative1(this.OriginLocation).clone().add(0.0D, 1.0D, 0.0D), 2.0D, 2.0D, 2.0D)) {
/* 67 */       if ((com.murabi10.portalgunreloaded.Methods.LocationEquals(ent.getLocation(), getRelative1(this.OriginLocation).clone().add(0.0D, 1.0D, 0.0D))) && (
/* 68 */         (!isEditMode()) || ((isEditMode()) && ((ent instanceof Player)) && (((Player)ent).isSneaking())))) {
/* 69 */         ent.setVelocity(com.murabi10.portalgunreloaded.Methods.DirectionToVector(this.Pitch, this.Yaw).clone().multiply(this.power));
/* 70 */         this.OriginLocation.getWorld().playSound(getRelative1(this.OriginLocation), org.bukkit.Sound.ENTITY_GENERIC_EXPLODE, 1.2F, 2.0F);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\fixture\AerialFaithPlate.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */