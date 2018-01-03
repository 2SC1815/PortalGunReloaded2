/*    */ package com.murabi10.portalgunreloaded.portalgun;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Trigonometrics
/*    */ {
/*    */   private static final int NUM = 360;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 17 */   private static double[] sinMap = new double['Ũ'];
/* 18 */   static { for (int i = 0; i < 360; i++) {
/* 19 */       sinMap[i] = Math.sin(i / 360.0D * 3.141592653589793D * 2.0D);
/*    */     }
/* 21 */     cosMap = new double['Ũ'];
/* 22 */     for (int i = 0; i < 360; i++) {
/* 23 */       cosMap[i] = Math.cos(i / 360.0D * 3.141592653589793D * 2.0D);
/*    */     }
/* 25 */     sinMap2 = new double['Ũ'];
/* 26 */     for (int i = 0; i < 360; i++) {
/* 27 */       sinMap2[i] = Math.sin((i + 180) / 360.0D * 3.141592653589793D * 2.0D);
/*    */     }
/* 29 */     cosMap2 = new double['Ũ'];
/* 30 */     for (int i = 0; i < 360; i++)
/* 31 */       cosMap2[i] = Math.cos((i + 180) / 360.0D * 3.141592653589793D * 2.0D); }
/*    */   
/*    */   private static double[] cosMap;
/*    */   private static double[] sinMap2;
/*    */   private static double[] cosMap2;
/* 36 */   public static final double sin(double t) { t = t / 6.283185307179586D * 360.0D;
/* 37 */     return sinMap[((int)t % 360)];
/*    */   }
/*    */   
/*    */   public static final double cos(double t) {
/* 41 */     t = t / 6.283185307179586D * 360.0D;
/* 42 */     return cosMap[((int)t % 360)];
/*    */   }
/*    */   
/*    */   public static final double sin2(double t) {
/* 46 */     t = t / 6.283185307179586D * 360.0D;
/* 47 */     return sinMap2[((int)t % 360)];
/*    */   }
/*    */   
/*    */   public static final double cos2(double t) {
/* 51 */     t = t / 6.283185307179586D * 360.0D;
/* 52 */     return cosMap2[((int)t % 360)];
/*    */   }
/*    */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalgun\Trigonometrics.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */