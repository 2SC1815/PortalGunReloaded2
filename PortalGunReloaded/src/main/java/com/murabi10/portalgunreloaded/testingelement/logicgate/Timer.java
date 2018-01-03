/*    */ package com.murabi10.portalgunreloaded.testingelement.logicgate;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.testingelement.ElementType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ public class Timer extends TestingElement
/*    */ {
/* 16 */   private transient boolean oldActivated = true;
/* 17 */   private int timer = 3;
/* 18 */   transient BukkitRunnable timerRunnable = null;
/*    */   
/*    */   public Timer(Location OriginLoc, int x, int y, int z) {
/* 21 */     super(OriginLoc, ElementType.TIMER, com.murabi10.portalgunreloaded.testingelement.LinkType.OUT_IN, org.bukkit.block.BlockFace.SOUTH, x, y, z);
/*    */   }
/*    */   
/*    */   public boolean check()
/*    */   {
/* 26 */     return true;
/*    */   }
/*    */   
/*    */   protected void destroy()
/*    */   {
/* 31 */     getRelative1(this.OriginLocation).getBlock().setType(Material.AIR);
/* 32 */     this.timerRunnable.cancel();
/*    */   }
/*    */   
/*    */ 
/*    */   protected void Run()
/*    */   {
/* 38 */     getRelative1(this.OriginLocation).getBlock().setType(isEditMode() ? Material.GOLD_BLOCK : Material.AIR);
/*    */     
/* 40 */     setInput(true);
/* 41 */     if (Switches().size() != 0) {
/* 42 */       for (TestingElement e : Switches()) {
/* 43 */         if (!e.isOutput()) {
/* 44 */           setInput(false);
/* 45 */           break;
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 50 */     if ((isInput() != this.oldActivated) && (isInput()))
/*    */     {
/* 52 */       if ((this.timerRunnable != null) && (!this.timerRunnable.isCancelled())) {
/* 53 */         this.timerRunnable.cancel();
/*    */       }
/*    */       
/* 56 */       final int time = this.timer;
/* 57 */       final Timer t = this;
/*    */       
/* 59 */       t.setOutput(true);
/*    */       
/* 61 */       this.timerRunnable = new BukkitRunnable()
/*    */       {
/* 63 */         int i = 0;
/*    */         
/*    */ 
/*    */         public void run()
/*    */         {
/* 68 */           if (this.i > time) {
/* 69 */             t.setOutput(false);
/* 70 */             cancel();
/* 71 */             Timer.this.OriginLocation.getWorld().playSound(Timer.this.getTargetPlayer().getLocation(), Sound.BLOCK_NOTE_CHIME, 1.0F, 1.8F);
/*    */           } else {
/* 73 */             Timer.this.OriginLocation.getWorld().playSound(Timer.this.getTargetPlayer().getLocation(), Sound.BLOCK_NOTE_SNARE, 1.0F, 1.2F);
/*    */           }
/*    */           
/* 76 */           this.i += 1;
/*    */ 
/*    */         }
/*    */         
/*    */ 
/* 81 */       };
/* 82 */       this.timerRunnable.runTaskTimer(com.murabi10.portalgunreloaded.PortalGun.getPlugin(), 0L, 20L);
/*    */     }
/*    */     
/*    */ 
/* 86 */     this.oldActivated = isInput();
/*    */   }
/*    */   
/*    */   public void setDelaySec(int delay) {
/* 90 */     this.timer = delay;
/*    */   }
/*    */   
/*    */   public int getDelaySec() {
/* 94 */     return this.timer;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\logicgate\Timer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */