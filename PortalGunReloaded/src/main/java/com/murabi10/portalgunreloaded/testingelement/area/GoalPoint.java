/*    */ package com.murabi10.portalgunreloaded.testingelement.area;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.Cuboid;
/*    */ import com.murabi10.portalgunreloaded.Methods;
/*    */ import com.murabi10.portalgunreloaded.portalevents.PlayerChamberGoalEvent;
/*    */ import com.murabi10.portalgunreloaded.testingelement.ElementType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ public class GoalPoint extends TestingElement
/*    */ {
/*    */   public GoalPoint(Location OriginLoc, int x1, int y1, int z1, int x2, int y2, int z2)
/*    */   {
/* 20 */     super(OriginLoc, ElementType.GOAL_POINT, com.murabi10.portalgunreloaded.testingelement.LinkType.DNC, BlockFace.SOUTH, x1, y1, z1, x2, y2, z2);
/*    */   }
/*    */   
/*    */   public boolean check()
/*    */   {
/* 25 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 33 */   private transient int i = 0;
/*    */   
/*    */   protected void destroy() {}
/*    */   
/* 37 */   protected void Run() { if (this.i >= 4)
/*    */     {
/* 39 */       for (Block b : new Cuboid(getRelative1(this.OriginLocation), getRelative2(this.OriginLocation))) {
/* 40 */         Location loc = b.getLocation();
/*    */         
/* 42 */         if (!isEditMode())
/*    */         {
/* 44 */           for (Entity ent : loc.getWorld().getNearbyEntities(loc, 2.0D, 2.0D, 2.0D)) {
/* 45 */             if (((ent instanceof Player)) && 
/* 46 */               (Methods.LocationEquals(ent.getLocation(), loc))) {
/* 47 */               PlayerChamberGoalEvent event = new PlayerChamberGoalEvent(getTargetPlayer());
/* 48 */               org.bukkit.Bukkit.getServer().getPluginManager().callEvent(event);
/*    */             }
/*    */           }
/*    */         }
/*    */         
/*    */ 
/*    */ 
/* 55 */         Methods.spawnParticle(Methods.LocationNormalize(loc), (byte)-56, (byte)-56, (byte)10);
/*    */       }
/*    */       
/* 58 */       this.i = 0;
/*    */     }
/* 60 */     this.i += 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\area\GoalPoint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */