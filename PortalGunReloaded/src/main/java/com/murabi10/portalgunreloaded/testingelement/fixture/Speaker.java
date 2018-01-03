/*    */ package com.murabi10.portalgunreloaded.testingelement.fixture;
/*    */ 
/*    */ import com.murabi10.portalgunreloaded.testingelement.ElementType;
/*    */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ 
/*    */ public class Speaker extends TestingElement
/*    */ {
/* 13 */   private transient boolean oldActivated = true;
/* 14 */   transient int i = 0;
/* 15 */   transient boolean activated = false;
/* 16 */   private String message = "";
/*    */   
/*    */   public Speaker(Location OriginLoc, int x, int y, int z) {
/* 19 */     super(OriginLoc, ElementType.SPEAKER, com.murabi10.portalgunreloaded.testingelement.LinkType.INPUT, BlockFace.SOUTH, x, y, z);
/*    */   }
/*    */   
/*    */   public boolean check()
/*    */   {
/* 24 */     return true;
/*    */   }
/*    */   
/*    */   protected void destroy()
/*    */   {
/* 29 */     getRelative1(this.OriginLocation).getBlock().setType(Material.AIR);
/*    */   }
/*    */   
/*    */   protected void Run()
/*    */   {
/* 34 */     if (isEditMode()) {
/* 35 */       this.activated = false;
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 40 */     if (this.i >= 3)
/*    */     {
/* 42 */       getRelative1(this.OriginLocation).getBlock().setType(isEditMode() ? Material.JUKEBOX : Material.AIR);
/*    */       
/* 44 */       setInput(true);
/* 45 */       if (Switches().size() != 0) {
/* 46 */         for (TestingElement e : Switches()) {
/* 47 */           if (!e.isOutput()) {
/* 48 */             setInput(false);
/* 49 */             break;
/*    */           }
/*    */         }
/*    */       }
/*    */       
/* 54 */       if ((isInput() != this.oldActivated) && (isInput()))
/*    */       {
/* 56 */         getTargetPlayer().sendMessage("録音メッセージ： " + this.message);
/*    */         
/* 58 */         if (!isEditMode()) {
/* 59 */           this.activated = true;
/*    */         }
/*    */       }
/*    */       
/*    */ 
/* 64 */       this.oldActivated = isInput();
/* 65 */       this.i = 0;
/*    */     }
/* 67 */     this.i += 1;
/*    */   }
/*    */   
/*    */   public void setString(String message) {
/* 71 */     this.message = message;
/*    */   }
/*    */   
/*    */   public String getString() {
/* 75 */     return this.message;
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testingelement\fixture\Speaker.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */