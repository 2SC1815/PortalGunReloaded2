/*    */ package com.murabi10.portalgunreloaded2.selector;
import com.murabi10.portalgunreloaded2.PortalGun;
import com.murabi10.portalgunreloaded2.portalgun.PortalDevice;

/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ public class ItemClickWait
/*    */   extends BukkitRunnable
/*    */ {
/*    */   private PortalDevice d;
/*    */   private ClickFunction f;
/*    */   
/*    */   public ItemClickWait(Player p, ClickFunction func) throws Exception
/*    */   {
/* 17 */     this.f = func;
/*    */     
/* 19 */     this.d = PortalDevice.getDeviceInstance(p);
/*    */     
/* 21 */     if (this.d.hasItemwait()) {
/* 22 */       this.d.getItemwait().cancel();
/*    */     }
/*    */     
/* 25 */     if (this.d.hasStrWait()) {
/* 26 */       this.d.getStrWait().cancel();
/*    */     }
/*    */     
/* 29 */     if (this.d != null) {
/* 30 */       this.d.setItemInput(true);
/*    */     } else {
/* 32 */       throw new Exception("player " + p.getName() + " has not have portaldevice instance!");
/*    */     }
/*    */     
/* 35 */     this.d.setItemwait(this);
/*    */     
/* 37 */     runTaskTimer(PortalGun.getPlugin(), 1L, 1L);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void run()
/*    */   {
/* 44 */     if (!this.d.isEmptyItem()) {
/* 45 */       ItemStack item = this.d.getInputItem();
/* 46 */       this.d.resetInputItem();
/*    */       
/* 48 */       if (!this.f.click(this.d.getOwner(), item)) {
/* 49 */         cancel();
/*    */       } else {
/* 51 */         this.d.setItemInput(true);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\selector\ItemClickWait.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */