package com.murabi10.portalgunreloaded.selector;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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

import com.murabi10.portalgunreloaded.PortalGun;
import com.murabi10.portalgunreloaded.chambereditor.EditorFunction;
import com.murabi10.portalgunreloaded.chambereditor.TestChamberEditor;
import com.murabi10.portalgunreloaded.testchamber.DataSystem;
import com.murabi10.portalgunreloaded.testchamber.GunType;
import com.murabi10.portalgunreloaded.testchamber.TestChamber;
import com.murabi10.portalgunreloaded.testchamber.TestChamberData;

public class EditChamberSelector implements CommandExecutor {

	private static ArrayList<Location> locs = new ArrayList<Location>();

	public static ArrayList<TestChamberData> data = new ArrayList<TestChamberData>();
	private static int tpage = 0;
	private static final int pageper = (9 * 3) - 4;

	private static final String next = "@NEXT";
	private static final String search = "@GREP";
	private static final String ret = "@RETURN";
	private static final String create = "@NEW";
	private static final String chamber = "@CHAMBER";
	private static final String exit = "@exit";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof BlockCommandSender) {

			BlockCommandSender s = (BlockCommandSender) sender;
			for (Entity ent : s.getBlock().getWorld().getNearbyEntities(s.getBlock().getLocation(), 5, 5, 5)) {
				if (ent instanceof Player) {
					try {
						((Player) ent).closeInventory();
						EditChamberSelector.OpenGUI((Player) ent, 0, SortType.NAME, "");
					} catch (Exception e) {
						e.printStackTrace();
						sender.sendMessage(ChatColor.RED + "なんかえらーでたんですまんけど管理人に報告してください。");
						ent.sendMessage(ChatColor.RED + "なんかえらーでたんですまんけど管理人に報告してください。");
					}
				}
			}

		} else if (sender instanceof Player) {

			try {
				((Player) sender).closeInventory();
				EditChamberSelector.OpenGUI((Player) sender, 0, SortType.NAME, "");
			} catch (Exception e) {
				e.printStackTrace();
				sender.sendMessage(ChatColor.RED + "なんかえらーでたんですまんけど管理人に報告してください。");
			}
		} else {
			sender.sendMessage("this command usable only player/commandblock");
		}

		return true;
	}

	public static void remove(Location loc) {
		locs.remove(loc);
	}

	public static void updateData() {
		data.clear();
		for (String name : DataSystem.getChambers()) {
			data.add(DataSystem.getChamberData(name));
		}
		tpage = ((int) (data.size() / pageper));
	}

	public static void OpenGUI(Player p, final int page, SortType type, final String grep) throws Exception {

		EditChamberSelector.updateData();
		ArrayList<TestChamberData> refinedData = new ArrayList<TestChamberData>();

		for (TestChamberData d : data) {
			if (grep.equals("")) {
				refinedData.add(d);
			} else {
				if (d.getFileName().indexOf(grep) != -1 || d.getChamberName().indexOf(grep) != -1) {
					refinedData.add(d);
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
		UI.setItem(2, createItem(Material.IRON_INGOT, 1, 0, "チェンバーを新規作成する", create));

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

			UI.setItem(i + 3, createItem(d));

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
					} else if (item.getItemMeta().getLore().contains(create)) {

						final Location loc = reserve();

						if (loc == null) {
							p.sendMessage("エディターが満員です。");
							try {
								OpenGUI(p, page, t, grep);
							} catch (Exception e) {
								e.printStackTrace();
							}
							return false;
						} else {
							p.sendMessage("@exit を入力するとキャンセルして戻ります");
							p.sendMessage("新規作成するチェンバーのファイル名をチャット入力してください >");
						}

						p.closeInventory();

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
										return false;
									} else if (containsMultiByteChar(str)) {
										pl.sendMessage("チェンバーのファイル名にマルチバイト文字は使用できません");
										pl.sendMessage("英数字などを使ってください >");

										return true;
									} else if (str.indexOf("@") != -1) {
										pl.sendMessage("チェンバー名に@は使えません");
										pl.sendMessage("ほかのファイル名を入力してください >");
										return true;
									} else if (DataSystem.isCompleteExist(str)) {
										pl.sendMessage(str + " は既に存在するチェンバーファイルです。");
										pl.sendMessage("ほかのファイル名を入力してください >");
										return true;
									} else {

										try {

											pl.sendMessage("しばらくお待ちください");

											if (TestChamberEditor.getEditor(pl) != null) {
												pl.sendMessage(ChatColor.RED + "エラー：このエラーは起こってはいけないので管理人に報告してください。");
												System.out.println("ERROR! " + pl.getName());
											}

											TestChamber tc = new TestChamber();
											TestChamberData td = new TestChamberData(pl.getUniqueId(), str,
													Calendar.getInstance());
											//td.setDesignerName(pl.getDisplayName().split(" ")[0]);
											td.setDesignerName(pl.getName());

											try {

												DataSystem.Save(tc, td, str);

												OpenChamberEditMenu(pl, str, page, t, grep);

											} catch (Exception e) {
												e.printStackTrace();
											}

										} catch (Exception e) {
											e.printStackTrace();
										}

										return false;
									}
								}

							});
						} catch (Exception e) {
							e.printStackTrace();
						}

						return false;
					} else if (item.getItemMeta().getLore().contains(chamber)) {

						final String chamberFileName = item.getItemMeta().getDisplayName();

						OpenChamberEditMenu(p, chamberFileName, page, t, grep);

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

	private static final String editChamber = "@EDIT";
	private static final String renameChamber = "@RENAME";
	private static final String deleteChamber = "@DELETE";
	private static final String returnMenu = "@RETURNMENU";
	private static final String publish = "@PUBLISH";
	private static final String gunTypeChange = "@GUNTYPE";
	private static final String setName = "@SETNAME";

	private static void OpenChamberEditMenu(Player p, final String fileName, final int page, final SortType type,
			final String grep) {
		p.closeInventory();

		Inventory UI = Bukkit.getServer().createInventory(null, 9, fileName);

		final TestChamberData d = DataSystem.getChamberData(fileName);

		UI.setItem(0, createItem(d));
		UI.setItem(1, createItem(Material.EMPTY_MAP, 1, 0, "タイトルセット", setName));
		UI.setItem(2, createItem(Material.MAP, 1, 0, "チェンバー編集", editChamber));
		UI.setItem(3, createItem(Material.EMPTY_MAP, 1, 0, "チェンバー名変更", renameChamber));
		UI.setItem(4, createItem(Material.BARRIER, 1, 0, "チェンバー削除", deleteChamber));
		UI.setItem(5, createItem(Material.PAPER, 1, 0, d.isPublished() ? "非公開にする" : "公開する", publish));

		String label = "支給タイプ変更:";
		String lore = "現在のタイプ:";

		switch (d.getPortalGunGive()) {
		case DUAL_PORTAL_DEVICE:
			label += "シングル";
			lore += "デュアル";
			break;
		case SINGLE_PORTAL_DEVICE:
			label += "支給なし";
			lore += "シングル";
			break;
		case NONE:
			label += "デュアル";
			lore += "支給なし";
			break;
		default:
			label += "ERROR";
			lore += "ERROR";
			break;

		}

		UI.setItem(6, createItem(Material.WOOD_SWORD, 1, 0, label, lore, gunTypeChange));

		UI.setItem(8, createItem(Material.ARROW, 1, 0, "戻る", returnMenu));

		p.openInventory(UI);
		try {
			new ItemClickWait(p, new ClickFunction() {

				@Override
				public boolean click(final Player p, ItemStack item) {

					// System.out.println(item.getItemMeta().getLore().get(0));

					if (item.getItemMeta().getLore().contains(editChamber)) {

						p.sendMessage("しばらくお待ちください");
						p.closeInventory();

						Location loc = reserve();

						if (loc == null) {
							p.sendMessage("エディターが満員です。");
							OpenChamberEditMenu(p, fileName, page, type, grep);
							return false;
						}

						if (TestChamberEditor.getEditor(p) != null) {
							p.sendMessage(ChatColor.RED + "エラー：このエラーは起こってはいけないので管理人に報告してください。");
							System.out.println("ERROR! " + p.getName());
							return false;
						}

						TestChamber tc = DataSystem.getChamberObject(fileName);
						TestChamberData td = d;

						try {
							TestChamberEditor edit = new TestChamberEditor(loc, tc, td, p);

							locs.add(loc);

							edit.setVisible(true);

							edit.StartEdit();

							return false;

						} catch (Exception e) {
							e.printStackTrace();
						}

						return false;

					} else if (item.getItemMeta().getLore().contains(setName)) {

						p.closeInventory();
						p.sendMessage("@exit を入力するとキャンセルして戻ります");
						p.sendMessage("タイトルをチャット入力してください >");
						try {
							new StringInputWait(p, new EditorFunction() {

								@Override
								public boolean reveive(String str) {

									d.setChamberName(str);
									DataSystem.Save(d, fileName);

									OpenChamberEditMenu(p, fileName, page, type, grep);
									return false;
								}

							});
						} catch (Exception e) {
							e.printStackTrace();
						}
						return false;

					} else if (item.getItemMeta().getLore().contains(renameChamber)) {

						p.closeInventory();
						p.sendMessage("@exit を入力するとキャンセルして戻ります");
						p.sendMessage("新しい名前をチャット入力してください >");
						final Player pl = p;
						try {
							new StringInputWait(p, new EditorFunction() {

								@Override
								public boolean reveive(String str) {

									if (str.equalsIgnoreCase(exit)) {
										OpenChamberEditMenu(p, fileName, page, type, str);
										return false;
									} else if (containsMultiByteChar(str)) {
										pl.sendMessage("チェンバーのファイル名にマルチバイト文字は使用できません");
										pl.sendMessage("英数字などを使ってください >");

										return true;
									} else if (DataSystem.isCompleteExist(str)) {
										pl.sendMessage(str + " は既に存在するチェンバーファイルです。");
										pl.sendMessage("ほかのファイル名を入力してください >");
										return true;
									} else if (str.indexOf("@") != -1) {
										pl.sendMessage("チェンバー名に@は使えません");
										pl.sendMessage("ほかのファイル名を入力してください >");
										return true;
									} else {
										DataSystem.rename(fileName, str);
										OpenChamberEditMenu(p, str, page, type, grep);
										return false;
									}
								}

							});
						} catch (Exception e) {
							e.printStackTrace();
						}
						return false;

					} else if (item.getItemMeta().getLore().contains(deleteChamber)) {

						p.closeInventory();
						p.sendMessage("y を入力するとチェンバーを削除します 削除されたチェンバーは戻せません。");
						p.sendMessage("y以外を入力するとキャンセルします >");
						try {
							new StringInputWait(p, new EditorFunction() {

								@Override
								public boolean reveive(String str) {

									if (str.equalsIgnoreCase("y")) {
										DataSystem.delete(fileName);
										try {
											OpenGUI(p, page, type, grep);
										} catch (Exception e) {
											e.printStackTrace();
										}
										return false;
									} else {
										OpenChamberEditMenu(p, fileName, page, type, str);
										return false;
									}
								}

							});
						} catch (Exception e) {
							e.printStackTrace();
						}
						return false;

					} else if (item.getItemMeta().getLore().contains(returnMenu)) {
						try {
							OpenGUI(p, page, type, grep);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return false;
					} else if (item.getItemMeta().getLore().contains(publish)) {
						d.setPublished(!d.isPublished());
						DataSystem.Save(d, fileName);
						OpenChamberEditMenu(p, fileName, page, type, grep);
						return false;
					} else if (item.getItemMeta().getLore().contains(gunTypeChange)) {

						switch (d.getPortalGunGive()) {

						case DUAL_PORTAL_DEVICE:
							d.setPortalGunGive(GunType.SINGLE_PORTAL_DEVICE);
							break;
						case SINGLE_PORTAL_DEVICE:
							d.setPortalGunGive(GunType.NONE);
							break;
						case NONE:
							d.setPortalGunGive(GunType.DUAL_PORTAL_DEVICE);
							break;
						default:
							d.setPortalGunGive(GunType.DUAL_PORTAL_DEVICE);
							break;

						}

						DataSystem.Save(d, fileName);
						OpenChamberEditMenu(p, fileName, page, type, grep);
						return false;
					} else {
						return true;
					}

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Location reserve() {
		Location loc = new Location(Bukkit.getWorld(PortalGun.EditorWorldName), 0, 0, 0);
		int i = 0;
		while (true) {

			if (i >= 5) {
				return null;
			}

			if (locs.contains(loc)) {
				loc = loc.add(65, 0, 0);
				i++;
			} else {
				return loc;
			}

		}
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

	private static boolean containsMultiByteChar(String s) {
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (!((c <= '\u007e') || // 英数字
					(c == '\u00a5') || // \記号
					(c == '\u203e') || // ~記号
					(c >= '\uff61' && c <= '\uff9f') // 半角カナ
			)) {
				return true;
			}
		}
		return false;
	}

	private static ItemStack createItem(TestChamberData d) {
		return createItem(Material.IRON_BLOCK, 1, 0, d.getFileName(), "タイトル: " + d.getChamberName(),
				"作成者: " + d.getDesignerName(), "作成日時: " + "WIP", "評価値: " + d.getPopurality(),
				"" + d.getPlayed() + "回テストされている", d.isPublished() ? "公開済み" : "非公開", chamber);
	}

}
