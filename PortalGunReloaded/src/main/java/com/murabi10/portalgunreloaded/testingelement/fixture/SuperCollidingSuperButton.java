/*    */ package com.murabi10.portalgunreloaded.testingelement.fixture;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.Methods;
/*    */ import com.murabi10.portalgunreloaded.testingelement.ElementType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.LinkType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*    */ import com.murabi10.portalgunreloaded.testingelement.objects.CubeManager;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class SuperCollidingSuperButton
/*    */   extends TestingElement
/*    */ {
/*    */   public SuperCollidingSuperButton(Location OriginLoc, BlockFace Dir, int x, int y, int z)
/*    */   {
/* 22 */     super(OriginLoc, ElementType.BUTTON, LinkType.OUTPUT, Dir, x, y, z);
/*    */   }
/*    */   
/* 25 */   private boolean oldActivated = false;
/*    */   
/*    */ 
/*    */   public boolean check()
/*    */   {
/* 30 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void destroy() {}
/*    */   
/*    */ 
/*    */ 
/*    */   protected void Run()
/*    */   {
/* 42 */     if (isEditMode()) {
/* 43 */       Block b = getRelative1(this.OriginLocation).getBlock();
/* 44 */       if (!b.getType().equals(Material.BONE_BLOCK)) {
/* 45 */         b.setType(Material.BONE_BLOCK);
/* 46 */         switch (getDirection()) {
/*    */         case NORTH: 
/*    */         case NORTH_EAST: 
/*    */         default: 
/* 50 */           b.setData((byte)0);
/* 51 */           break;
/*    */         case DOWN: 
/*    */         case EAST_NORTH_EAST: 
/* 54 */           b.setData((byte)8);
/* 55 */           break;
/*    */         case EAST: 
/*    */         case EAST_SOUTH_EAST: 
/* 58 */           b.setData((byte)4);
/*    */         }
/*    */         
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 65 */     setOutput(false);
/* 66 */     Location judge = getRelative1(this.OriginLocation).getBlock().getRelative(getDirection()).getLocation();
/* 67 */     for (Entity ent : judge.getWorld().getNearbyEntities(judge, 2.0D, 2.0D, 2.0D)) {
/* 68 */       if ((((ent instanceof LivingEntity)) && (Methods.LocationEquals(judge, ent.getLocation()))) || (
/* 69 */         (CubeManager.getCube(ent.getUniqueId()) != null) && (Methods.LocationEquals(judge, ent.getLocation())))) {
/* 70 */         setOutput(true);
/* 71 */         break;
/*    */       }
/*    */     }
/*    */     
/* 75 */     if (this.oldActivated != isOutput())
/*    */     {
/*    */ 
/* 78 */       this.OriginLocation.getWorld().playSound(getRelative1(this.OriginLocation), isOutput() ? 
/* 79 */         Sound.BLOCK_STONE_PRESSUREPLATE_CLICK_ON : Sound.BLOCK_STONE_PRESSUREPLATE_CLICK_OFF, 
/* 80 */         1.0F, 0.3F);
/*    */     }
/*    */     
/* 83 */     this.oldActivated = isOutput();
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\fixture\SuperCollidingSuperButton.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */