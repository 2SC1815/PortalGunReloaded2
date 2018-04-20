 package com.murabi10.portalgunreloaded2.testchamber;

 import java.io.Serializable;
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.block.Block;

import com.murabi10.portalgunreloaded2.BlockData;
import com.murabi10.portalgunreloaded2.testingelement.TestingElement;


 public class TestChamber
   implements Serializable
 {
   private ArrayList<TestingElement> elements = new ArrayList<TestingElement>();

   private Dimension Chamber = null;

   public TestChamber() {
     this.Chamber = Dimension.getStartingChamber();
   }

   public Dimension Chamber() {
     return this.Chamber;
   }

   public ArrayList<TestingElement> TestingElements() {
     return this.elements;
   }


   @SuppressWarnings("deprecation")
public void placeToWorld(Location loc)
   {
	   //System.out.println("PLACE");
     for (int x = 0; x < this.Chamber.getXSize(); x++) {
       for (int y = 0; y < this.Chamber.getYSize(); y++) {
         for (int z = 0; z < this.Chamber.getZSize(); z++) {
           Location tmp = loc.clone().add(x, y, z);
           BlockData b = this.Chamber.getBlock(x, y, z);
           if (!tmp.getBlock().getType().equals(b.getMaterial())) {
	           tmp.getBlock().setType(b.getMaterial());
	           tmp.getBlock().setData(b.GetData());
           }
         }
       }
     }
   }

   public void deleteFromWorld(Location loc)
   {
	  // System.out.println("DELETE");
	   /*
     for (int x = 0; x < this.Chamber.getXSize(); x++) {
       for (int y = 0; y < this.Chamber.getYSize(); y++) {
         for (int z = 0; z < this.Chamber.getZSize(); z++) {
           Location tmp = loc.clone().add(x, y, z);
           tmp.getBlock().setType(Material.AIR);
         }
       }
     }*/
   }


   @SuppressWarnings("deprecation")
public void importFromWorld(Location loc)
   {
     for (int x = 0; x < this.Chamber.getXSize(); x++) {
       for (int y = 0; y < this.Chamber.getYSize(); y++) {
         for (int z = 0; z < this.Chamber.getZSize(); z++) {
           Block tmp = loc.clone().add(x, y, z).getBlock();
           this.Chamber.setBlock(x, y, z, new BlockData(tmp.getType(), tmp.getData()));
         }
       }
     }
   }
 }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testchamber\TestChamber.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */