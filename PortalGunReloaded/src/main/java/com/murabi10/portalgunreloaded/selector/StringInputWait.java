/*    */ package com.murabi10.portalgunreloaded.selector;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.PortalGun;
/*    */ import com.murabi10.portalgunreloaded.chambereditor.EditorFunction;
/*    */ import com.murabi10.portalgunreloaded.portalgun.PortalDevice;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ public class StringInputWait
/*    */   extends BukkitRunnable
/*    */ {
/*    */   private PortalDevice d;
/*    */   private EditorFunction f;
/*    */   
/*    */   public StringInputWait(Player p, EditorFunction func) throws Exception
/*    */   {
/* 17 */     this.f = func;
/*    */     
/* 19 */     this.d = PortalDevice.getDeviceInstance(p);
/*    */     
/* 21 */     if (this.d.hasStrWait()) {
/* 22 */       this.d.getStrWait().cancel();
/*    */     }
/*    */     
/* 25 */     if (this.d != null) {
/* 26 */       this.d.setStringInput(true);
/*    */     } else {
/* 28 */       throw new Exception("player " + p.getName() + " has not have portaldevice instance!");
/*    */     }
/*    */     
/* 31 */     this.d.setStrWait(this);
/*    */     
/* 33 */     runTaskTimer(PortalGun.getPlugin(), 1L, 1L);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void run()
/*    */   {
/* 40 */     if (!this.d.isEmptyString()) {
/* 41 */       String str = this.d.getInputString();
/* 42 */       this.d.resetInputString();
/*    */       
/* 44 */       if (!this.f.reveive(str)) {
/* 45 */         cancel();
/*    */       } else {
/* 47 */         this.d.setStringInput(true);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\selector\StringInputWait.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */