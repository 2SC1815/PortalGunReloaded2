package com.murabi10.portalgunreloaded2.portalgun;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.murabi10.portalgunreloaded2.Methods;
import com.murabi10.portalgunreloaded2.portalevents.PortalThroughEvent;

public class PortalTransportRunnable
		extends BukkitRunnable {
	public void run()
   {
     for (Player p : Bukkit.getOnlinePlayers())
     {
       PortalDevice instance = PortalDevice.getDeviceInstance(p);
       Portal portal = Methods.getPortal(p.getLocation(), true);

       if (portal != null)
       {
         Portal dest = portal.getDest();
         if ((dest != null) && (!instance.getFlag()))
         {
           boolean isUPDOWN = (portal.getLaunchDirection().equals(BlockFace.UP)) ||
             (portal.getLaunchDirection().equals(BlockFace.DOWN));
           if ((isUPDOWN) || ((!isUPDOWN) &&
             (portal.getLaunchDirection().equals(Methods.YawToBlockFace(p.getEyeLocation().getYaw()).getOppositeFace()))))
           {


             Location loc = dest.getRepresentativeLocation();
             loc = Methods.BlockFaceFormat(dest.getLaunchDirection(), dest.getSubstitutionDirection(), loc);
             loc = Methods.LocationNormalize(loc);
             PortalThroughEvent event = new PortalThroughEvent(p, portal, dest, loc);
             Bukkit.getServer().getPluginManager().callEvent(event);

             p.teleport(event.getDest());

             p.setVelocity(Methods.BlockFaceToVector(dest.getLaunchDirection(), event.getV()));

             instance.setFlag(true);
           }
         }
       } else {
         instance.setFlag(false);
       }
     }
   }
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalgun\PortalTransportRunnable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */