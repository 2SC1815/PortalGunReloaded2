/*     */ package com.murabi10.portalgunreloaded.testchamber;
/*     */ 
/*     */ import com.murabi10.portalgunreloaded.PortalGun;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ public class DataSystem
/*     */ {
/*     */   public static boolean Save(TestChamber chamber, TestChamberData data, String dirName)
/*     */   {
/*  18 */     File dir = new File(PortalGun.TEST_CHAMBER_FILE_PATH + dirName);
/*     */     
/*  20 */     if (!dir.exists()) {
/*  21 */       dir.mkdirs();
/*     */     }
/*     */     try
/*     */     {
/*  25 */       ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(ChamberPath(dirName)));
/*     */       
/*  27 */       objOutStream.writeObject(chamber);
/*  28 */       objOutStream.close();
/*     */     }
/*     */     catch (FileNotFoundException e) {
/*  31 */       e.printStackTrace();
/*  32 */       return false;
/*     */     } catch (IOException e) {
/*  34 */       e.printStackTrace();
/*  35 */       return false;
/*     */     }
/*     */     try
/*     */     {
/*  39 */       ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(DataPath(dirName)));
/*     */       
/*  41 */       objOutStream.writeObject(data);
/*  42 */       objOutStream.close();
/*     */     }
/*     */     catch (FileNotFoundException e) {
/*  45 */       e.printStackTrace();
/*  46 */       return false;
/*     */     } catch (IOException e) {
/*  48 */       e.printStackTrace();
/*  49 */       return false;
/*     */     }
/*     */     
/*  52 */     return true;
/*     */   }
/*     */   
/*     */   public static TestChamber getChamberObject(String dirName)
/*     */   {
/*  57 */     if (!isCompleteExist(dirName)) {
/*  58 */       return null;
/*     */     }
/*     */     try {
/*  61 */       ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(new File(ChamberPath(dirName))));
/*  62 */       TestChamber chamber = (TestChamber)objInStream.readObject();
/*  63 */       objInStream.close();
/*     */       
/*  65 */       return chamber;
/*     */     } catch (FileNotFoundException e) {
/*  67 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/*  69 */       e.printStackTrace();
/*     */     } catch (ClassNotFoundException e) {
/*  71 */       e.printStackTrace();
/*     */     }
/*  73 */     return null;
/*     */   }
/*     */   
/*     */   public static TestChamberData getChamberData(String dirName) {
/*  77 */     if (!isCompleteExist(dirName)) {
/*  78 */       return null;
/*     */     }
/*     */     try {
/*  81 */       ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(new File(DataPath(dirName))));
/*  82 */       TestChamberData chamber = (TestChamberData)objInStream.readObject();
/*  83 */       objInStream.close();
/*  84 */       chamber.setFileName(dirName);
/*  85 */       return chamber;
/*     */     } catch (FileNotFoundException e) {
/*  87 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/*  89 */       e.printStackTrace();
/*     */     } catch (ClassNotFoundException e) {
/*  91 */       e.printStackTrace();
/*     */     }
/*  93 */     return null;
/*     */   }
/*     */   
/*     */   public static boolean isCompleteExist(String dirName) {
/*  97 */     File data = new File(DataPath(dirName));
/*  98 */     File chamber = new File(ChamberPath(dirName));
/*  99 */     return (data.exists()) && (chamber.exists()) && (data.isFile()) && (chamber.isFile()) && 
/* 100 */       (data.getName().endsWith(".cdata")) && 
/* 101 */       (chamber.getName().endsWith(".chamber"));
/*     */   }
/*     */   
/*     */   public static ArrayList<String> getChambers() {
/* 105 */     ArrayList<String> rtnfile = new ArrayList();
/* 106 */     File[] files = new File(PortalGun.TEST_CHAMBER_FILE_PATH).listFiles();
/* 107 */     for (int i = 0; i < files.length; i++) {
/* 108 */       File f = files[i];
/* 109 */       String fName = f.getName();
/* 110 */       if ((f.isDirectory()) && (isCompleteExist(fName))) {
/* 111 */         rtnfile.add(fName);
/*     */       }
/*     */     }
/* 114 */     return rtnfile;
/*     */   }
/*     */   
/*     */   public static void delete(String dirName)
/*     */   {
/* 119 */     new File(DataPath(dirName)).delete();
/* 120 */     new File(ChamberPath(dirName)).delete();
/* 121 */     new File(PortalGun.TEST_CHAMBER_FILE_PATH + dirName).delete();
/*     */   }
/*     */   
/*     */   public static void rename(String dirName, String out)
/*     */   {
/* 126 */     new File(DataPath(dirName)).renameTo(new File(
/* 127 */       PortalGun.TEST_CHAMBER_FILE_PATH + dirName + File.separator + out + ".cdata"));
/* 128 */     new File(ChamberPath(dirName)).renameTo(new File(PortalGun.TEST_CHAMBER_FILE_PATH + dirName + File.separator + 
/* 129 */       out + ".chamber"));
/* 130 */     new File(PortalGun.TEST_CHAMBER_FILE_PATH + dirName).renameTo(new File(PortalGun.TEST_CHAMBER_FILE_PATH + out));
/*     */   }
/*     */   
/*     */   private static String ChamberPath(String bindname) {
/* 134 */     return 
/* 135 */       PortalGun.TEST_CHAMBER_FILE_PATH + bindname + File.separator + bindname + ".chamber";
/*     */   }
/*     */   
/*     */   private static String DataPath(String bindname) {
/* 139 */     return 
/* 140 */       PortalGun.TEST_CHAMBER_FILE_PATH + bindname + File.separator + bindname + ".cdata";
/*     */   }
/*     */   
/*     */   public static boolean Save(TestChamberData td, String dirName)
/*     */   {
/* 145 */     File dir = new File(PortalGun.TEST_CHAMBER_FILE_PATH + dirName);
/*     */     
/* 147 */     if (!dir.exists()) {
/* 148 */       dir.mkdirs();
/*     */     }
/*     */     try
/*     */     {
/* 152 */       ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(DataPath(dirName)));
/*     */       
/* 154 */       objOutStream.writeObject(td);
/* 155 */       objOutStream.close();
/*     */     }
/*     */     catch (FileNotFoundException e) {
/* 158 */       e.printStackTrace();
/* 159 */       return false;
/*     */     } catch (IOException e) {
/* 161 */       e.printStackTrace();
/* 162 */       return false;
/*     */     }
/*     */     
/* 165 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\testchamber\DataSystem.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */