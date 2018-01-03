package com.murabi10.portalgunreloaded;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.murabi10.portalgunreloaded.chambereditor.ElementPlaceGUI;
import com.murabi10.portalgunreloaded.chambereditor.FixturePlaceGUI;
import com.murabi10.portalgunreloaded.chambereditor.TestChamberEditor;
import com.murabi10.portalgunreloaded.devices.DeviceManager;
import com.murabi10.portalgunreloaded.gui.DebugGUI;
import com.murabi10.portalgunreloaded.gui.GUIManager;
import com.murabi10.portalgunreloaded.portalgun.CubeTransportRunnable;
import com.murabi10.portalgunreloaded.portalgun.GelTransportRunnable;
import com.murabi10.portalgunreloaded.portalgun.HangingManager;
import com.murabi10.portalgunreloaded.portalgun.PortalTransportRunnable;
import com.murabi10.portalgunreloaded.portalgun.vector;
import com.murabi10.portalgunreloaded.selector.EditChamberSelector;
import com.murabi10.portalgunreloaded.selector.ItemClickManager;
import com.murabi10.portalgunreloaded.selector.PlayChamberSelector;
import com.murabi10.portalgunreloaded.selector.StringInputManager;
import com.murabi10.portalgunreloaded.testingelement.objects.CubeManager;
import com.murabi10.portalgunreloaded.testingelement.objects.GelManager;

public class PortalGun extends JavaPlugin {
	public static final String PluginName = "PortalGunReloaded";
	public static final String TEST_CHAMBER_EXTENSION = ".chamber";
	public static final String TEST_CHAMBER_DATA_EXTENSION = ".cdata";
	public static String TEST_CHAMBER_FILE_PATH = "plugins" + File.separator + "PortalGunReloaded" + File.separator +
			"TestChambers" + File.separator;
	public static Set<Material> transparentMaterials = new HashSet<Material>();
	public static Set<Material> PortalTransparentMaterials = new HashSet<Material>();
	public static Set<BlockData> PortalConductors = new HashSet<BlockData>();
	public static final BlockData WHITE_PANEL = new BlockData(Material.CONCRETE, (byte) 0);
	public static final BlockData BLACK_PANEL = new BlockData(Material.CONCRETE, (byte) 7);

	public static final String prefix = "config.";

	public static String EditorWorldName;
	public static DebugGUI debug = new DebugGUI();
	public static FixturePlaceGUI fixture = new FixturePlaceGUI();
	public static ElementPlaceGUI gimmicks = new ElementPlaceGUI();

	public static final int portalLen = 200;

	public static short ON_INDICATOR_MAP_ID = 40;
	public static short OFF_INDICATOR_MAP_ID = 42;

	static {
		PortalTransparentMaterials.add(Material.AIR);
		PortalTransparentMaterials.add(Material.TRIPWIRE);
		PortalTransparentMaterials.add(Material.CARPET);
		PortalTransparentMaterials.add(Material.IRON_FENCE);
		PortalTransparentMaterials.add(Material.LONG_GRASS);

		transparentMaterials.add(Material.AIR);
		transparentMaterials.add(Material.TRIPWIRE);
		transparentMaterials.add(Material.CARPET);
		transparentMaterials.add(Material.IRON_FENCE);
		transparentMaterials.add(Material.STAINED_GLASS_PANE);
		transparentMaterials.add(Material.THIN_GLASS);
		transparentMaterials.add(Material.GLASS);
		transparentMaterials.add(Material.STAINED_GLASS);
		transparentMaterials.add(Material.LADDER);
		transparentMaterials.add(Material.LONG_GRASS);

		PortalConductors.add(new BlockData(Material.QUARTZ_BLOCK, (byte) -1));
		PortalConductors.add(new BlockData(Material.CONCRETE, (byte) 0));
		PortalConductors.add(new BlockData(Material.STONE, (byte) 3));
		PortalConductors.add(new BlockData(Material.STONE, (byte) 4));
	}

	public void onEnable() {
		saveDefaultConfig();
		getConfig().options().copyDefaults(true);

		EditorWorldName = getConfig().getString("config.editWorld");
		String path = getConfig().getString("config.chamberFile");
		if ((!path.equals("default")) && (!path.equals(""))) {
			TEST_CHAMBER_FILE_PATH = path;
		}
		ON_INDICATOR_MAP_ID = (short) getConfig().getInt("config.on_indicator_map_id");
		OFF_INDICATOR_MAP_ID = (short) getConfig().getInt("config.off_indicator_map_id");

		TestChamberEditor.Check();

		DeviceManager.Enable();

		getServer().getPluginManager().registerEvents(new EventListener(), this);
		getServer().getPluginManager().registerEvents(new GUIManager(), this);
		getServer().getPluginManager().registerEvents(new StringInputManager(), this);
		getServer().getPluginManager().registerEvents(new DeviceManager(), this);
		getServer().getPluginManager().registerEvents(new CubeManager(), this);
		getServer().getPluginManager().registerEvents(new HangingManager(), this);
		getServer().getPluginManager().registerEvents(new ItemClickManager(), this);
		getServer().getPluginManager().registerEvents(new LongFallBoots(), this);

		getCommand("debug").setExecutor(new Debug(this));
		getCommand("editor").setExecutor(new EditChamberSelector());
		getCommand("select").setExecutor(new PlayChamberSelector());
		getCommand("retire").setExecutor(new Retire(this));

		new PortalTransportRunnable().runTaskTimer(this, 10L, 1L);
		new CubeTransportRunnable().runTaskTimer(this, 10L, 1L);
		new GelTransportRunnable().runTaskTimer(this, 10L, 1L);
		new vector().runTaskTimer(this, 10L, 1L);

		EditChamberSelector.updateData();
	}

	public void onDisable() {
		CubeManager.Disable();
		GelManager.Disable();
	}

	public static Plugin getPlugin() {
		return org.bukkit.Bukkit.getPluginManager().getPlugin("PortalGunReloaded");
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\PortalGun.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */