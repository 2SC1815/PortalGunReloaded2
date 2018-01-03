/*    */ package com.murabi10.portalgunreloaded;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.portalgun.PortalDevice;
/*    */ import com.murabi10.portalgunreloaded.testchamber.TestQueue;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class Retire
/*    */   implements CommandExecutor
/*    */ {
/*    */   public Retire(PortalGun plugin) {}
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*    */   {
/* 17 */     if ((sender instanceof Player)) {
/* 18 */       PortalDevice d = PortalDevice.getDeviceInstance(((Player)sender).getUniqueId());
/* 19 */       if ((d != null) && 
/* 20 */         (d.getQueue() != null)) {
/* 21 */         d.getQueue().StopTest();
/*    */       }
/*    */     }
/*    */     
/* 25 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\Retire.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */