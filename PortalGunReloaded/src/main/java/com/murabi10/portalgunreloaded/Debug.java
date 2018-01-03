/*    */ package com.murabi10.portalgunreloaded;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.gui.DebugGUI;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class Debug
/*    */   implements CommandExecutor
/*    */ {
/*    */   public Debug(PortalGun plugin) {}
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*    */   {
/* 16 */     PortalGun.debug.openGUI((Player)sender);
/*    */     
/* 18 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\Debug.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */