  package com.murabi10.portalgunreloaded.testchamber;

  import java.io.Serializable;

import org.bukkit.Material;

import com.murabi10.portalgunreloaded.BlockData;
import com.murabi10.portalgunreloaded.PortalGun;

  public class Dimension implements Serializable
  {
    private BlockData[][][] Dim;
    private int Xsize;
    private int Ysize;
    private int Zsize = 0;

    public Dimension(int X, int Y, int Z) throws IllegalArgumentException
    {
      if ((X < 0) || (Y < 0) || (Z < 0)) {
        throw new IllegalArgumentException();
      }

      this.Dim = new BlockData[X][Y][Z];

      this.Xsize = X;
      this.Ysize = Y;
      this.Zsize = Z;

      for (int x = 0; x < this.Xsize; x++) {
        for (int y = 0; y < this.Ysize; y++) {
          for (int z = 0; z < this.Zsize; z++) {
            if ((x == 0) || (y == 0) || (z == 0) || (x == this.Xsize - 1) || (y == this.Ysize - 1) || (z == this.Zsize - 1)) {
              this.Dim[x][y][z] = new BlockData(Material.BEDROCK, (byte) 0);
            } else {
              this.Dim[x][y][z] = PortalGun.BLACK_PANEL;
            }
          }
        }
      }
    }


    public BlockData getBlock(int X, int Y, int Z)
    {
      if ((X < 0) || (Y < 0) || (Z < 0) || (X >= this.Xsize) || (Y >= this.Ysize) || (Z >= this.Zsize)) {
        throw new IllegalArgumentException();
      }
      return this.Dim[X][Y][Z];
    }

    public void setBlock(int X, int Y, int Z, BlockData block)
    {
      if ((X < 0) || (Y < 0) || (Z < 0) || (X >= this.Xsize) || (Y >= this.Ysize) || (Z >= this.Zsize)) {
        throw new IllegalArgumentException();
      }
      this.Dim[X][Y][Z] = block;
    }

    public int getXSize()
    {
      return this.Xsize;
    }

    public int getYSize() {
      return this.Ysize;
    }

    public int getZSize() {
      return this.Zsize;
    }

    public static Dimension getStartingChamber() {
      Dimension dim = new Dimension(64, 64, 64);
      for (int x = 22; x < 42; x++) {
        for (int y = 24; y < 39; y++) {
          for (int z = 17; z < 47; z++) {
            BlockData b;
            if ((x == 0) || (y == 0) || (z == 0) || (x == 19) || (y == 14) || (z == 29)) {
              if (y > 5) {
                b = PortalGun.BLACK_PANEL;
              } else {
                b = PortalGun.WHITE_PANEL;
              }
            } else {
              b = new BlockData(Material.AIR, (byte)0);
            }

            dim.setBlock(x, y, z, b);
          }
        }
      }
      return dim;
    }
  }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testchamber\Dimension.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */