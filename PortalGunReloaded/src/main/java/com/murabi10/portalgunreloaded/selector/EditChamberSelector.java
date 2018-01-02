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
						sender.sendMessage(ChatColor.RED + "�ʤ󤫤��顼�Ǥ���Ǥ��ޤ󤱤ɴ����ͤ���𤷤Ƥ���������");
						ent.sendMessage(ChatColor.RED + "�ʤ󤫤��顼�Ǥ���Ǥ��ޤ󤱤ɴ����ͤ���𤷤Ƥ���������");
					}
				}
			}

		} else if (sender instanceof Player) {

			try {
				((Player) sender).closeInventory();
				EditChamberSelector.OpenGUI((Player) sender, 0, SortType.NAME, "");
			} catch (Exception e) {
				e.printStackTrace();
				sender.sendMessage(ChatColor.RED + "�ʤ󤫤��顼�Ǥ���Ǥ��ޤ󤱤ɴ����ͤ���𤷤Ƥ���������");
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

		String disp = "������С�����";

		if (refinedData.isEmpty()) {
			disp = "���Ĥ���ޤ���";
		} else if (!grep.equals("")) {
			disp = "\"" + grep + "\"";
		}

		Inventory UI = Bukkit.getServer().createInventory(null, 9 * 3, disp + " (�ڡ���" + page + "/" + tpage + ")");
		if (page > 0) {
			UI.setItem(0, createItem(Material.ARROW, 1, 0, page - 1 + "�ڡ��������", ret));
		} else {
			UI.setItem(0, createItem(Material.BARRIER, 1, 0, "����ʾ����ޤ���"));
		}

		UI.setItem(1, createItem(Material.GLASS, 1, 0, "������С��򸡺�����", search));
		UI.setItem(2, createItem(Material.IRON_INGOT, 1, 0, "������С��򿷵���������", create));

		if (page < tpage) {
			UI.setItem(26, createItem(Material.ARROW, 1, 0, page + 1 + "�ڡ����˿ʤ�", next));
		} else {
			UI.setItem(26, createItem(Material.BARRIER, 1, 0, "����ʾ�ʤ�ޤ���"));
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
						p.sendMessage("@exit �����Ϥ���ȥե��륿����ꥻ�åȤ������ޤ�");
						p.sendMessage("����ʸ��������å����Ϥ��Ƥ������� >");
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
							p.sendMessage("���ǥ������������Ǥ���");
							try {
								OpenGUI(p, page, t, grep);
							} catch (Exception e) {
								e.printStackTrace();
							}
							return false;
						} else {
							p.sendMessage("@exit �����Ϥ���ȥ���󥻥뤷�����ޤ�");
							p.sendMessage("�����������������С��Υե�����̾�����å����Ϥ��Ƥ������� >");
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
										pl.sendMessage("������С��Υե�����̾�˥ޥ���Х���ʸ���ϻ��ѤǤ��ޤ���");
										pl.sendMessage("�ѿ����ʤɤ�ȤäƤ������� >");

										return true;
									} else if (str.indexOf("@") != -1) {
										pl.sendMessage("������С�̾��@�ϻȤ��ޤ���");
										pl.sendMessage("�ۤ��Υե�����̾�����Ϥ��Ƥ������� >");
										return true;
									} else if (DataSystem.isCompleteExist(str)) {
										pl.sendMessage(str + " �ϴ���¸�ߤ��������С��ե�����Ǥ���");
										pl.sendMessage("�ۤ��Υե�����̾�����Ϥ��Ƥ������� >");
										return true;
									} else {

										try {

											pl.sendMessage("���Ф餯���Ԥ���������");

											if (TestChamberEditor.getEditor(pl) != null) {
												pl.sendMessage(ChatColor.RED + "���顼�����Υ��顼�ϵ����äƤϤ����ʤ��ΤǴ����ͤ���𤷤Ƥ���������");
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
		UI.setItem(1, createItem(Material.EMPTY_MAP, 1, 0, "�����ȥ륻�å�", setName));
		UI.setItem(2, createItem(Material.MAP, 1, 0, "������С��Խ�", editChamber));
		UI.setItem(3, createItem(Material.EMPTY_MAP, 1, 0, "������С�̾�ѹ�", renameChamber));
		UI.setItem(4, createItem(Material.BARRIER, 1, 0, "������С����", deleteChamber));
		UI.setItem(5, createItem(Material.PAPER, 1, 0, d.isPublished() ? "������ˤ���" : "��������", publish));

		String label = "�ٵ륿�����ѹ�:";
		String lore = "���ߤΥ�����:";

		switch (d.getPortalGunGive()) {
		case DUAL_PORTAL_DEVICE:
			label += "���󥰥�";
			lore += "�ǥ奢��";
			break;
		case SINGLE_PORTAL_DEVICE:
			label += "�ٵ�ʤ�";
			lore += "���󥰥�";
			break;
		case NONE:
			label += "�ǥ奢��";
			lore += "�ٵ�ʤ�";
			break;
		default:
			label += "ERROR";
			lore += "ERROR";
			break;

		}

		UI.setItem(6, createItem(Material.WOOD_SWORD, 1, 0, label, lore, gunTypeChange));

		UI.setItem(8, createItem(Material.ARROW, 1, 0, "���", returnMenu));

		p.openInventory(UI);
		try {
			new ItemClickWait(p, new ClickFunction() {

				@Override
				public boolean click(final Player p, ItemStack item) {

					// System.out.println(item.getItemMeta().getLore().get(0));

					if (item.getItemMeta().getLore().contains(editChamber)) {

						p.sendMessage("���Ф餯���Ԥ���������");
						p.closeInventory();

						Location loc = reserve();

						if (loc == null) {
							p.sendMessage("���ǥ������������Ǥ���");
							OpenChamberEditMenu(p, fileName, page, type, grep);
							return false;
						}

						if (TestChamberEditor.getEditor(p) != null) {
							p.sendMessage(ChatColor.RED + "���顼�����Υ��顼�ϵ����äƤϤ����ʤ��ΤǴ����ͤ���𤷤Ƥ���������");
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
						p.sendMessage("@exit �����Ϥ���ȥ���󥻥뤷�����ޤ�");
						p.sendMessage("�����ȥ�����å����Ϥ��Ƥ������� >");
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
						p.sendMessage("@exit �����Ϥ���ȥ���󥻥뤷�����ޤ�");
						p.sendMessage("������̾�������å����Ϥ��Ƥ������� >");
						final Player pl = p;
						try {
							new StringInputWait(p, new EditorFunction() {

								@Override
								public boolean reveive(String str) {

									if (str.equalsIgnoreCase(exit)) {
										OpenChamberEditMenu(p, fileName, page, type, str);
										return false;
									} else if (containsMultiByteChar(str)) {
										pl.sendMessage("������С��Υե�����̾�˥ޥ���Х���ʸ���ϻ��ѤǤ��ޤ���");
										pl.sendMessage("�ѿ����ʤɤ�ȤäƤ������� >");

										return true;
									} else if (DataSystem.isCompleteExist(str)) {
										pl.sendMessage(str + " �ϴ���¸�ߤ��������С��ե�����Ǥ���");
										pl.sendMessage("�ۤ��Υե�����̾�����Ϥ��Ƥ������� >");
										return true;
									} else if (str.indexOf("@") != -1) {
										pl.sendMessage("������С�̾��@�ϻȤ��ޤ���");
										pl.sendMessage("�ۤ��Υե�����̾�����Ϥ��Ƥ������� >");
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
						p.sendMessage("y �����Ϥ���ȥ�����С��������ޤ� ������줿������С����᤻�ޤ���");
						p.sendMessage("y�ʳ������Ϥ���ȥ���󥻥뤷�ޤ� >");
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
			if (!((c <= '\u007e') || // �ѿ���
					(c == '\u00a5') || // \����
					(c == '\u203e') || // ~����
					(c >= '\uff61' && c <= '\uff9f') // Ⱦ�ѥ���
			)) {
				return true;
			}
		}
		return false;
	}

	private static ItemStack createItem(TestChamberData d) {
		return createItem(Material.IRON_BLOCK, 1, 0, d.getFileName(), "�����ȥ�: " + d.getChamberName(),
				"������: " + d.getDesignerName(), "��������: " + "WIP", "ɾ����: " + d.getPopurality(),
				"" + d.getPlayed() + "��ƥ��Ȥ���Ƥ���", d.isPublished() ? "�����Ѥ�" : "�����", chamber);
	}

}
