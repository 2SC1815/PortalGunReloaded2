package com.murabi10.portalgunreloaded.selector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.murabi10.portalgunreloaded.chambereditor.EditorFunction;
import com.murabi10.portalgunreloaded.testchamber.DataSystem;
import com.murabi10.portalgunreloaded.testchamber.TestChamberData;
import com.murabi10.portalgunreloaded.testchamber.TestQueue;

public class PlayChamberSelector implements CommandExecutor {

	public static ArrayList<TestChamberData> data = new ArrayList<TestChamberData>();
	private static int tpage = 0;
	private static final int pageper = (9 * 3) - 3;

	private static final String next = "@NEXT";
	private static final String search = "@GREP";
	private static final String ret = "@RETURN";
	private static final String chamber = "@CHAMBER";
	private static final String exit = "@exit";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof BlockCommandSender) {

			BlockCommandSender s = (BlockCommandSender) sender;
			for (Entity ent : s.getBlock().getWorld().getNearbyEntities(s.getBlock().getLocation(), 5, 5, 5)) {
				if (ent instanceof Player) {

					if (args.length == 0) {
						try {
							((Player) ent).closeInventory();
							PlayChamberSelector.OpenGUI((Player) ent, 0, SortType.NAME, "");
						} catch (Exception e) {
							e.printStackTrace();
							sender.sendMessage(ChatColor.RED + "なんかえらーでたんですまんけど管理人に報告してください。");
							ent.sendMessage(ChatColor.RED + "なんかえらーでたんですまんけど管理人に報告してください。");
						}
					} else {
						final String chamberFileName = args[0];

						((Player) ent).closeInventory();

						ent.sendMessage("しばらくおまちください");

						TestQueue queue = new TestQueue((Player) ent, DataSystem.getChamberData(chamberFileName),
								ent.getLocation().clone(), ent.getLocation().clone());

						queue.BeginTest();
					}

				}
			}

		} else if (sender instanceof Player) {

			if (args.length == 0) {
				try {
					((Player) sender).closeInventory();
					PlayChamberSelector.OpenGUI((Player) sender, 0, SortType.NAME, "");
				} catch (Exception e) {
					e.printStackTrace();
					sender.sendMessage(ChatColor.RED + "なんかえらーでたんですまんけど管理人に報告してください。");
				}
			} else {

				final String chamberFileName = args[0];

				((Player) sender).closeInventory();

				sender.sendMessage("しばらくおまちください");

				TestQueue queue = new TestQueue((Player) sender, DataSystem.getChamberData(chamberFileName),
						((Player) sender).getLocation().clone(), ((Player) sender).getLocation().clone());

				queue.BeginTest();

			}
		} else {
			sender.sendMessage("this command usable only player/commandblock");
		}

		return true;
	}

	public static void updateData() {
		data.clear();
		for (String name : DataSystem.getChambers()) {
			data.add(DataSystem.getChamberData(name));
		}
		tpage = ((int) (data.size() / pageper));
	}

	public static void OpenGUI(Player p, final int page, SortType type, final String grep) throws Exception {

		PlayChamberSelector.updateData();
		ArrayList<TestChamberData> refinedData = new ArrayList<TestChamberData>();

		for (TestChamberData d : data) {
			if (d.isPublished()/* | true*/) { //TODO
				if (grep.equals("")) {
					refinedData.add(d);
				} else {
					if (d.getFileName().indexOf(grep) != -1 || d.getChamberName().indexOf(grep) != -1) {
						refinedData.add(d);
					}
				}
			}
		}

		// p.closeInventory();

		String disp = "チェンバー選択";

		if (refinedData.isEmpty()) {
			disp = "見つかりません";
		} else if (!grep.equals("")) {
			disp = "\"" + grep + "\"";
		}

		Inventory UI = Bukkit.getServer().createInventory(null, 9 * 3, disp + " (ページ" + page + "/" + tpage + ")");
		if (page > 0) {
			UI.setItem(0, createItem(Material.ARROW, 1, 0, page - 1 + "ページに戻る", ret));
		} else {
			UI.setItem(0, createItem(Material.BARRIER, 1, 0, "これ以上戻れません"));
		}

		UI.setItem(1, createItem(Material.GLASS, 1, 0, "チェンバーを検索する", search));

		if (page < tpage) {
			UI.setItem(26, createItem(Material.ARROW, 1, 0, page + 1 + "ページに進む", next));
		} else {
			UI.setItem(26, createItem(Material.BARRIER, 1, 0, "これ以上進めません"));
		}

		switch (type) {
		case NAME:
			Collections.sort(refinedData, new Comparator<TestChamberData>() {
				public int compare(TestChamberData t1, TestChamberData t2) {
					return t1.getChamberName().compareTo(t2.getChamberName());
				}

			});
			break;
		case PLAY:
			Collections.sort(refinedData, new Comparator<TestChamberData>() {
				public int compare(TestChamberData t1, TestChamberData t2) {
					if (t1.getPlayed() > t2.getPlayed()) {
						return 1;

					} else if (t1.getPlayed() == t1.getPlayed()) {
						return 0;

					} else {
						return -1;

					}
				}

			});
			break;
		case POPULARITY:
			Collections.sort(refinedData, new Comparator<TestChamberData>() {
				public int compare(TestChamberData t1, TestChamberData t2) {
					if (t1.getPopurality() > t2.getPopurality()) {
						return 1;

					} else if (t1.getPopurality() == t1.getPopurality()) {
						return 0;

					} else {
						return -1;

					}
				}

			});
			break;
		case UPDATE:
			Collections.sort(refinedData, new Comparator<TestChamberData>() {
				public int compare(TestChamberData t1, TestChamberData t2) {
					return t1.getTime().compareTo(t2.getTime());
				}

			});
			break;
		default:
			break;
		}

		for (int i = 0; i < pageper; i++) {

			int index = i + (pageper * page);

			if (!(index < refinedData.size())) {
				break;
			}

			TestChamberData d = refinedData.get(index);

			UI.setItem(i + 2, createItem(d));

		}

		p.openInventory(UI);

		final SortType t = type;

		new ItemClickWait(p, new ClickFunction() {

			@Override
			public boolean click(Player p, ItemStack item) {

				if (item.hasItemMeta() && item.getItemMeta().hasLore() && item.getItemMeta().hasDisplayName()) {

					if (item.getItemMeta().getLore().contains(next)) {
						p.closeInventory();
						try {
							OpenGUI(p, page + 1, t, grep);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return false;
					} else if (item.getItemMeta().getLore().contains(ret)) {
						p.closeInventory();
						try {
							OpenGUI(p, page - 1, t, grep);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return false;

					} else if (item.getItemMeta().getLore().contains(search)) {

						p.closeInventory();
						p.sendMessage("@exit を入力するとフィルターをリセットして戻ります");
						p.sendMessage("検索文字列をチャット入力してください >");
						final Player pl = p;
						try {
							new StringInputWait(p, new EditorFunction() {

								@Override
								public boolean reveive(String str) {
									if (str.equalsIgnoreCase(exit)) {

										try {
											OpenGUI(pl, page, t, "");
										} catch (Exception e) {
											e.printStackTrace();
										}
									} else {
										try {
											OpenGUI(pl, 0, t, str);
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
									return false;
								}

							});
						} catch (Exception e) {
							e.printStackTrace();
						}

						return false;

					} else if (item.getItemMeta().getLore().contains(chamber)) {

						final String chamberFileName = item.getItemMeta().getDisplayName();

						p.closeInventory();

						p.sendMessage("しばらくおまちください");

						TestQueue queue = new TestQueue(p, DataSystem.getChamberData(chamberFileName),
								p.getLocation().clone(), p.getLocation().clone());

						queue.BeginTest();

						return false;

					} else {
						return true;
					}

				} else {
					return true;
				}

			}

		});/**/

	}

	private static ItemStack createItem(Material material, int stack, int dataValue, String Name, String... Lore) {
		ItemStack item = new ItemStack(material, stack, (short) dataValue);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Name);
		ArrayList<String> lore = new ArrayList<String>();
		for (int i = 0; i < Lore.length; i++) {
			lore.add(((i < Lore.length - 1) ? ChatColor.RESET + "" : "") + Lore[i]);
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	private static ItemStack createItem(TestChamberData d) {
		return createItem(Material.IRON_BLOCK, 1, 0, d.getFileName(), "タイトル: " + d.getChamberName(),
				"作成者: " + d.getDesignerName(), "作成日時: " + "WIP", "評価値: " + d.getPopurality(),
				"" + d.getPlayed() + "回テストされている", chamber);
	}

}
