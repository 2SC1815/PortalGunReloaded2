/*     */ package com.murabi10.portalgunreloaded;
/*     */ 
/*     */ import com.murabi10.portalgunreloaded.chambereditor.ElementPlaceGUI;
/*     */ import com.murabi10.portalgunreloaded.chambereditor.FixturePlaceGUI;
/*     */ import com.murabi10.portalgunreloaded.chambereditor.TestChamberEditor;
/*     */ import com.murabi10.portalgunreloaded.devices.DeviceManager;
/*     */ import com.murabi10.portalgunreloaded.gui.DebugGUI;
/*     */ import com.murabi10.portalgunreloaded.gui.GUIManager;
/*     */ import com.murabi10.portalgunreloaded.portalgun.CubeTransportRunnable;
/*     */ import com.murabi10.portalgunreloaded.portalgun.GelTransportRunnable;
/*     */ import com.murabi10.portalgunreloaded.portalgun.HangingManager;
/*     */ import com.murabi10.portalgunreloaded.portalgun.PortalTransportRunnable;
/*     */ import com.murabi10.portalgunreloaded.portalgun.vector;
/*     */ import com.murabi10.portalgunreloaded.selector.EditChamberSelector;
/*     */ import com.murabi10.portalgunreloaded.selector.ItemClickManager;
/*     */ import com.murabi10.portalgunreloaded.selector.PlayChamberSelector;
/*     */ import com.murabi10.portalgunreloaded.selector.StringInputManager;
/*     */ import com.murabi10.portalgunreloaded.testingelement.objects.CubeManager;
/*     */ import com.murabi10.portalgunreloaded.testingelement.objects.GelManager;
/*     */ import java.io.File;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.command.PluginCommand;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ public class PortalGun extends JavaPlugin
/*     */ {
/*     */   public static final String PluginName = "PortalGunReloaded";
/*     */   public static final String TEST_CHAMBER_EXTENSION = ".chamber";
/*     */   public static final String TEST_CHAMBER_DATA_EXTENSION = ".cdata";
/*  36 */   public static String TEST_CHAMBER_FILE_PATH = "plugins" + File.separator + "PortalGunReloaded" + File.separator + 
/*  37 */     "TestChambers" + File.separator;
/*  38 */   public static Set<Material> transparentMaterials = new HashSet();
/*  39 */   public static Set<Material> PortalTransparentMaterials = new HashSet();
/*  40 */   public static Set<BlockData> PortalConductors = new HashSet();
/*  41 */   public static final BlockData WHITE_PANEL = new BlockData(Material.CONCRETE, (byte)0);
/*  42 */   public static final BlockData BLACK_PANEL = new BlockData(Material.CONCRETE, (byte)7);
/*     */   
/*     */   public static final String prefix = "config.";
/*     */   
/*     */   public static String EditorWorldName;
/*  47 */   public static DebugGUI debug = new DebugGUI();
/*  48 */   public static FixturePlaceGUI fixture = new FixturePlaceGUI();
/*  49 */   public static ElementPlaceGUI gimmicks = new ElementPlaceGUI();
/*     */   
/*     */   public static final int portalLen = 200;
/*     */   
/*  53 */   public static short ON_INDICATOR_MAP_ID = 40;
/*  54 */   public static short OFF_INDICATOR_MAP_ID = 42;
/*     */   
/*     */ 
/*     */ 
/*     */   static
/*     */   {
/*  60 */     PortalTransparentMaterials.add(Material.AIR);
/*  61 */     PortalTransparentMaterials.add(Material.TRIPWIRE);
/*  62 */     PortalTransparentMaterials.add(Material.CARPET);
/*  63 */     PortalTransparentMaterials.add(Material.IRON_FENCE);
/*  64 */     PortalTransparentMaterials.add(Material.LONG_GRASS);
/*     */     
/*  66 */     transparentMaterials.add(Material.AIR);
/*  67 */     transparentMaterials.add(Material.TRIPWIRE);
/*  68 */     transparentMaterials.add(Material.CARPET);
/*  69 */     transparentMaterials.add(Material.IRON_FENCE);
/*  70 */     transparentMaterials.add(Material.STAINED_GLASS_PANE);
/*  71 */     transparentMaterials.add(Material.THIN_GLASS);
/*  72 */     transparentMaterials.add(Material.GLASS);
/*  73 */     transparentMaterials.add(Material.STAINED_GLASS);
/*  74 */     transparentMaterials.add(Material.LADDER);
/*  75 */     transparentMaterials.add(Material.LONG_GRASS);
/*     */     
/*  77 */     PortalConductors.add(new BlockData(Material.QUARTZ_BLOCK, (byte)-1));
/*  78 */     PortalConductors.add(new BlockData(Material.CONCRETE, (byte)0));
/*  79 */     PortalConductors.add(new BlockData(Material.STONE, (byte)3));
/*  80 */     PortalConductors.add(new BlockData(Material.STONE, (byte)4));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void onEnable()
/*     */   {
/*  87 */     saveDefaultConfig();
/*  88 */     getConfig().options().copyDefaults(true);
/*     */     
/*  90 */     EditorWorldName = getConfig().getString("config.editWorld");
/*  91 */     String path = getConfig().getString("config.chamberFile");
/*  92 */     if ((!path.equals("default")) && (!path.equals(""))) {
/*  93 */       TEST_CHAMBER_FILE_PATH = path;
/*     */     }
/*  95 */     ON_INDICATOR_MAP_ID = (short)getConfig().getInt("config.on_indicator_map_id");
/*  96 */     OFF_INDICATOR_MAP_ID = (short)getConfig().getInt("config.off_indicator_map_id");
/*     */     
/*  98 */     TestChamberEditor.Check();
/*     */     
/* 100 */     DeviceManager.Enable();
/*     */     
/* 102 */     getServer().getPluginManager().registerEvents(new EventListener(), this);
/* 103 */     getServer().getPluginManager().registerEvents(new GUIManager(), this);
/* 104 */     getServer().getPluginManager().registerEvents(new StringInputManager(), this);
/* 105 */     getServer().getPluginManager().registerEvents(new DeviceManager(), this);
/* 106 */     getServer().getPluginManager().registerEvents(new CubeManager(), this);
/* 107 */     getServer().getPluginManager().registerEvents(new HangingManager(), this);
/* 108 */     getServer().getPluginManager().registerEvents(new ItemClickManager(), this);
/* 109 */     getServer().getPluginManager().registerEvents(new LongFallBoots(), this);
/*     */     
/*     */ 
/* 112 */     getCommand("debug").setExecutor(new Debug(this));
/* 113 */     getCommand("editor").setExecutor(new EditChamberSelector());
/* 114 */     getCommand("select").setExecutor(new PlayChamberSelector());
/* 115 */     getCommand("retire").setExecutor(new Retire(this));
/*     */     
/* 117 */     new PortalTransportRunnable().runTaskTimer(this, 10L, 1L);
/* 118 */     new CubeTransportRunnable().runTaskTimer(this, 10L, 1L);
/* 119 */     new GelTransportRunnable().runTaskTimer(this, 10L, 1L);
/* 120 */     new vector().runTaskTimer(this, 10L, 1L);
/*     */     
/* 122 */     EditChamberSelector.updateData();
/*     */   }
/*     */   
/*     */ 
/*     */   public void onDisable()
/*     */   {
/* 128 */     CubeManager.Disable();
/* 129 */     GelManager.Disable();
/*     */   }
/*     */   
/*     */   public static Plugin getPlugin() {
/* 133 */     return org.bukkit.Bukkit.getPluginManager().getPlugin("PortalGunReloaded");
/*     */   }
/*     */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\PortalGun.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */