  package com.murabi10.portalgunreloaded;

  import java.io.Serializable;

import org.bukkit.Material;

  public class BlockData implements Serializable
  {
    private Material m;
    private byte data;

    public BlockData(Material mat, byte data)
    {
      this.m = mat;
      this.data = data;
    }

    public Material getMaterial() {
      return this.m;
    }

    public byte GetData() {
      return this.data;
    }
 }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\BlockData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */