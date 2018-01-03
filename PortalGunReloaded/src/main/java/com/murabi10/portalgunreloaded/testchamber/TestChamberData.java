/*     */ package com.murabi10.portalgunreloaded.testchamber;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.UUID;
/*     */ 
/*     */ public class TestChamberData implements Serializable
/*     */ {
/*     */   private transient String FileName;
/*  11 */   private String ChamberName = "[Test Chamber Name Here]";
/*  12 */   private String DesignerName = "[Designer Name Here]";
/*     */   private UUID PlayerUUID;
/*     */   private Calendar time;
/*  15 */   private ArrayList<String> Discreption = new ArrayList();
/*  16 */   private int Popurality = 0;
/*  17 */   private int played = 0;
/*  18 */   private int cleared = 0;
/*  19 */   private boolean published = false;
/*  20 */   private GunType portalGunGive = GunType.DUAL_PORTAL_DEVICE;
/*     */   
/*     */   public TestChamberData(UUID uuid, String FileName, Calendar cal)
/*     */   {
/*  24 */     this.FileName = FileName;
/*  25 */     this.PlayerUUID = uuid;
/*  26 */     this.time = cal;
/*     */   }
/*     */   
/*     */   public void setFileName(String name) {
/*  30 */     this.FileName = name;
/*     */   }
/*     */   
/*  33 */   public String getFileName() { return this.FileName; }
/*     */   
/*     */   public UUID getUUID()
/*     */   {
/*  37 */     return this.PlayerUUID;
/*     */   }
/*     */   
/*     */   public String getChamberName() {
/*  41 */     return this.ChamberName;
/*     */   }
/*     */   
/*     */   public void setChamberName(String chamberName) {
/*  45 */     this.ChamberName = chamberName;
/*     */   }
/*     */   
/*     */   public String getDesignerName() {
/*  49 */     return this.DesignerName;
/*     */   }
/*     */   
/*     */   public void setDesignerName(String designerName) {
/*  53 */     this.DesignerName = designerName;
/*     */   }
/*     */   
/*     */   public ArrayList<String> Discreption() {
/*  57 */     return this.Discreption;
/*     */   }
/*     */   
/*     */   public Calendar getTime() {
/*  61 */     return this.time;
/*     */   }
/*     */   
/*     */   public void setTime(Calendar time) {
/*  65 */     this.time = time;
/*     */   }
/*     */   
/*     */   public int getPopurality() {
/*  69 */     return this.Popurality;
/*     */   }
/*     */   
/*     */   public void setPopurality(int popurality) {
/*  73 */     this.Popurality = popurality;
/*     */   }
/*     */   
/*     */   public int getPlayed() {
/*  77 */     return this.played;
/*     */   }
/*     */   
/*     */   public void incrementPlayed() {
/*  81 */     this.played += 1;
/*     */   }
/*     */   
/*     */   public int getCleared() {
/*  85 */     return this.cleared;
/*     */   }
/*     */   
/*     */   public void incrementCleared() {
/*  89 */     this.cleared += 1;
/*     */   }
/*     */   
/*     */   public boolean isPublished() {
/*  93 */     return this.published;
/*     */   }
/*     */   
/*     */   public void setPublished(boolean published) {
/*  97 */     this.published = published;
/*     */   }
/*     */   
/*     */   public GunType getPortalGunGive() {
/* 101 */     return this.portalGunGive;
/*     */   }
/*     */   
/*     */   public void setPortalGunGive(GunType portalGunGive) {
/* 105 */     this.portalGunGive = portalGunGive;
/*     */   }
/*     */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testchamber\TestChamberData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */