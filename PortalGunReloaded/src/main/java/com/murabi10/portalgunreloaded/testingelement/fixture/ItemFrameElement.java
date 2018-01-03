/*    */ package com.murabi10.portalgunreloaded.testingelement.fixture;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.testingelement.ElementType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.LinkType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Rotation;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.ItemFrame;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ public class ItemFrameElement extends TestingElement
/*    */ {
/* 19 */   private ArrayList<String> itemLore = new ArrayList();
/* 20 */   private String itemName = "";
/* 21 */   private short data = 0;
/* 22 */   private Material m = null;
/* 23 */   private Rotation r = Rotation.NONE;
/*    */   
/* 25 */   private transient ItemFrame frame = null;
/*    */   
/*    */   public ItemFrameElement(Location OriginLoc, BlockFace Dir, int x, int y, int z) {
/* 28 */     super(OriginLoc, ElementType.ITEM_FRAME, LinkType.DNC, Dir, x, y, z);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean check()
/*    */   {
/* 34 */     return (getRelative1(this.OriginLocation).getBlock().getType().isSolid()) && 
/* 35 */       (getRelative1(this.OriginLocation).getBlock().getRelative(getDirection()).isEmpty());
/*    */   }
/*    */   
/*    */   protected void destroy()
/*    */   {
/* 40 */     if (this.frame != null) {
/* 41 */       this.frame.setItem(new ItemStack(Material.AIR));
/* 42 */       this.frame.remove();
/* 43 */       this.frame = null;
/*    */     }
/*    */   }
/*    */   
/* 47 */   private transient int i = 0;
/*    */   
/*    */   protected void Run()
/*    */   {
/* 51 */     if (this.i >= 7) {
/* 52 */       if (this.frame == null) {
/* 53 */         this.frame = ((ItemFrame)this.OriginLocation.getWorld().spawnEntity(
/* 54 */           getRelative1(this.OriginLocation).getBlock().getRelative(getDirection()).getLocation(), 
/* 55 */           EntityType.ITEM_FRAME));
/* 56 */         this.frame.setFacingDirection(getDirection());
/* 57 */         ItemStack i = new ItemStack(this.m != null ? this.m : Material.AIR);
/* 58 */         i.setDurability(this.data);
/* 59 */         if (!this.itemName.equals("")) {
/* 60 */           i.getItemMeta().setDisplayName(this.itemName);
/*    */         }
/* 62 */         if (!this.itemLore.isEmpty()) {
/* 63 */           i.getItemMeta().setLore(this.itemLore);
/*    */         }
/* 65 */         this.frame.setItem(i);
/* 66 */         this.frame.setRotation(this.r);
/*    */       }
/* 68 */       if (isEditMode()) {
/* 69 */         ItemStack item = this.frame.getItem();
/* 70 */         if (item != null) {
/* 71 */           this.m = item.getType();
/* 72 */           this.data = item.getDurability();
/* 73 */           if (item.hasItemMeta()) {
/* 74 */             if (item.getItemMeta().hasDisplayName()) {
/* 75 */               this.itemName = item.getItemMeta().getDisplayName();
/*    */             }
/* 77 */             if (item.getItemMeta().hasLore()) {
/* 78 */               this.itemLore = ((ArrayList)item.getItemMeta().getLore());
/*    */             }
/*    */           }
/* 81 */           this.r = this.frame.getRotation();
/*    */         }
/*    */       }
/* 84 */       this.i = 0;
/*    */     }
/* 86 */     this.i += 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\fixture\ItemFrameElement.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */