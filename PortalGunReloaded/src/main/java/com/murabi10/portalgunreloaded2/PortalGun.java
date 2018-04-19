package com.murabi10.portalgunreloaded2;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.murabi10.portalgunreloaded2.chambereditor.ElementPlaceGUI;
import com.murabi10.portalgunreloaded2.chambereditor.FixturePlaceGUI;
import com.murabi10.portalgunreloaded2.chambereditor.TestChamberEditor;
import com.murabi10.portalgunreloaded2.devices.DeviceManager;
import com.murabi10.portalgunreloaded2.gui.DebugGUI;
import com.murabi10.portalgunreloaded2.gui.GUIManager;
import com.murabi10.portalgunreloaded2.portalgun.CubeTransportRunnable;
import com.murabi10.portalgunreloaded2.portalgun.GelTransportRunnable;
import com.murabi10.portalgunreloaded2.portalgun.HangingManager;
import com.murabi10.portalgunreloaded2.portalgun.PortalTransportRunnable;
import com.murabi10.portalgunreloaded2.portalgun.vector;
import com.murabi10.portalgunreloaded2.selector.EditChamberSelector;
import com.murabi10.portalgunreloaded2.selector.ItemClickManager;
import com.murabi10.portalgunreloaded2.selector.PlayChamberSelector;
import com.murabi10.portalgunreloaded2.selector.PlayProcessCommand;
import com.murabi10.portalgunreloaded2.selector.StringInputManager;
import com.murabi10.portalgunreloaded2.testingelement.objects.CubeManager;
import com.murabi10.portalgunreloaded2.testingelement.objects.GelManager;

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

	/*
	 * PortalGunReloaded2
	 * (C) 2017 - 2018 Code by 2SC1815 / W.T.  idea from Portal2
	 *
	 * Special thanks : おうえんしてくれたみんな。
	 *
	 *
	 * PortalGun : 初期リリース。static使いすぎ。オブジェクト指向を理解していなかったころのコード。
	 * 基本的な機能しかなかったうえ、バグが多かったが、これがすべての始まりである。
	 *
	 * PortalGunReloaded : PortalGunの後継。オブジェクト指向を理解したのでスマートに作られるようになり、
	 * さらにキューブが物理的に動くようになった！そしてチェンバーを簡単に作れるようにチェンバーエディターを実装する。
	 *
	 * PortalGunReloaded2 : PortalGunReloadedのソースが入ったハードディスクが壊れてしまい、心が折れかけたが、
	 * ビルド済みのjarファイルから逆コンパイルしてサルベージすることに成功。その後手直しをしたバージョン
	 *
	 *
	 * 偉大なる科学は巨人たちの肩に支えられていると言う者もいるが、Aperture Science は違う。
	 * 我々はゼロからすべてを築き上げる。介添えは一切なしだ。
	 *
	 * 宇宙に飛び出し、海岸を攻め、金塊を持ち帰ったのは我々だ! いまや世界中が敵に回り、
	 * 信頼できるのはお前だけだ! お前の根性が気に入った! もう少し速く解いてくれると申し分ないが。さっそく取りかかれ!
	 *
	 * いいか、レモンを与えられたからといって、レモネードなど作ってはならない。そんなものは突き返してやれ!
	 * 「これで何をしろというのだ! こんなものはいらん!」と吠えてやるのだ!
	 * 上の者を出せ! このCave Johnson様にレモンなどを与えようとしたことを、心底後悔させてやる! 私を誰だと思っている?!
	 * お前の家など軽く焼き払える男だ! そのレモンでな! エンジニア連中に可燃性レモンを開発させ、お前の家など焼き討ちしてやる!
	 *
	 */

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
		getCommand("process").setExecutor(new PlayProcessCommand());

		new PortalTransportRunnable().runTaskTimer(this, 10L, 1L);
		new CubeTransportRunnable().runTaskTimer(this, 10L, 1L);
		new GelTransportRunnable().runTaskTimer(this, 10L, 1L);
		new vector().runTaskTimer(this, 10L, 1L);

		//EditChamberSelector.updateData(null);
	}

	public void onDisable() {
		CubeManager.Disable();
		GelManager.Disable();
	}

	public static Plugin getPlugin() {
		return org.bukkit.Bukkit.getPluginManager().getPlugin("PortalGunReloaded2");
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\PortalGun.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */