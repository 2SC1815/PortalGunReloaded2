package com.murabi10.portalgunreloaded.chambereditor;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import com.murabi10.portalgunreloaded.PortalGun;
import com.murabi10.portalgunreloaded.gui.Button;
import com.murabi10.portalgunreloaded.gui.GUI;
import com.murabi10.portalgunreloaded.gui.GUIFunction;
import com.murabi10.portalgunreloaded.testingelement.LinkType;
import com.murabi10.portalgunreloaded.testingelement.TestingElement;
import com.murabi10.portalgunreloaded.testingelement.area.StartPoint;

public class FixturePlaceGUI extends GUI {

	@Override
	public void init() {

		setMenuName("��������������Ǥ�������");
		setSize(2);

		/*
		 * addbutton(new Button(true, Material.STONE, 1, (short)0,
		 * "DEBUG : PORTAL SPECIFICATIONS", 3, new Function() {
		 *
		 * @Override public void click(Player p, ClickType type) { // TODO
		 * ��ư�������줿�᥽�åɡ�������
		 *
		 * }
		 *
		 * }, "", ""));
		 */

		addbutton(new Button(Material.DIAMOND_BLOCK, 1, (short) 0, "������������������", 0, new GUIFunction() {

			@Override
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {

					StartPoint start = new StartPoint(editor.getOrigin(), editor.getX(p.getLocation()),
							editor.getY(p.getLocation()), editor.getZ(p.getLocation()));

					editor.AddTestElement(start);

					start.initRunnable();

				}
				return true;

			}

		}, "��������������Ω�äƤ������", "���ꤷ�ޤ���", "����������������ޤäƤ���ȥƥ��Ȥ����ϤǤ��ޤ���"));

		addbutton(new Button(Material.CONCRETE, 1, (short) 7, "���ɤ��������", 1, new GUIFunction() {

			@SuppressWarnings("deprecation")
			@Override
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null && editor.isCuboid()) {

					int minx = Math.min(editor.getPos1().getBlockX(), editor.getPos2().getBlockX()),
							miny = Math.min(editor.getPos1().getBlockY(), editor.getPos2().getBlockY()),
							minz = Math.min(editor.getPos1().getBlockZ(), editor.getPos2().getBlockZ()),
							maxx = Math.max(editor.getPos1().getBlockX(), editor.getPos2().getBlockX()),
							maxy = Math.max(editor.getPos1().getBlockY(), editor.getPos2().getBlockY()),
							maxz = Math.max(editor.getPos1().getBlockZ(), editor.getPos2().getBlockZ());

					for (int x = minx; x < maxx + 1; x++) {
						for (int y = miny; y < maxy + 1; y++) {
							for (int z = minz; z < maxz + 1; z++) {

								Block b = new Location(editor.getOrigin().getWorld(), x, y, z).getBlock();

								if (b.getType().equals(Material.CONCRETE)) {
									b.setType(PortalGun.BLACK_PANEL.getMaterial());
									b.setData(PortalGun.BLACK_PANEL.GetData());
								}

							}
						}
					}
				}
				return true;

			}

		}, "�����ϰϤ����ɤ�������ޤ���", "�����ɤˤϥݡ����뤬�ǤƤޤ���"));

		addbutton(new Button(Material.CONCRETE, 1, (short) 0, "�����ɤ��򤯤���", 2, new GUIFunction() {

			@SuppressWarnings("deprecation")
			@Override
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null && editor.isCuboid()) {

					int minx = Math.min(editor.getPos1().getBlockX(), editor.getPos2().getBlockX()),
							miny = Math.min(editor.getPos1().getBlockY(), editor.getPos2().getBlockY()),
							minz = Math.min(editor.getPos1().getBlockZ(), editor.getPos2().getBlockZ()),
							maxx = Math.max(editor.getPos1().getBlockX(), editor.getPos2().getBlockX()),
							maxy = Math.max(editor.getPos1().getBlockY(), editor.getPos2().getBlockY()),
							maxz = Math.max(editor.getPos1().getBlockZ(), editor.getPos2().getBlockZ());

					for (int x = minx; x < maxx + 1; x++) {
						for (int y = miny; y < maxy + 1; y++) {
							for (int z = minz; z < maxz + 1; z++) {

								Block b = new Location(editor.getOrigin().getWorld(), x, y, z).getBlock();

								if (b.getType().equals(Material.CONCRETE)) {
									b.setType(PortalGun.WHITE_PANEL.getMaterial());
									b.setData(PortalGun.WHITE_PANEL.GetData());
								}

							}
						}
					}
				}
				return true;

			}

		}, "�����ϰϤι����ɤ��򤯤��ޤ���", "���ɤˤϥݡ����뤬�ǤƤޤ���"));

		addbutton(new Button(Material.BARRIER, 1, (short) 0, "�����ϰϤ���", 3, new GUIFunction() {

			@Override
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					editor.clearPos();
				}
				return true;

			}

		}, "�����ϰϤ������ޤ���"));

		addbutton(new Button(Material.REDSTONE_TORCH_ON, 1, (short) 0, "�ƥ������֤����֤���", 4, new GUIFunction() {

			@Override
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					p.closeInventory();
					PortalGun.gimmicks.openGUI(p);
				}
				return false;

			}

		}, "�졼������ʪ���õ��åɤʤɤ�", "�ƥ����ѥ��ߥå������֤��ޤ���"));

		addbutton(new Button(Material.BARRIER, 1, (short) 0, "�����ϰ��⥯�ꥢ", 5, new GUIFunction() {

			@SuppressWarnings("deprecation")
			@Override
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null && editor.isCuboid()) {

					int minx = Math.min(editor.getPos1().getBlockX(), editor.getPos2().getBlockX()),
							miny = Math.min(editor.getPos1().getBlockY(), editor.getPos2().getBlockY()),
							minz = Math.min(editor.getPos1().getBlockZ(), editor.getPos2().getBlockZ()),
							maxx = Math.max(editor.getPos1().getBlockX(), editor.getPos2().getBlockX()),
							maxy = Math.max(editor.getPos1().getBlockY(), editor.getPos2().getBlockY()),
							maxz = Math.max(editor.getPos1().getBlockZ(), editor.getPos2().getBlockZ());

					for (int x = minx; x < maxx + 1; x++) {
						for (int y = miny; y < maxy + 1; y++) {
							for (int z = minz; z < maxz + 1; z++) {

								Block b = new Location(editor.getOrigin().getWorld(), x, y, z).getBlock();

								if (editor.getElement(b.getLocation()) != null) {
									editor.RemoveElement(b.getLocation());
								}
								if (!(b.isLiquid() || b.isEmpty()) || b.getType().equals(Material.CONCRETE)) {
									b.setType(PortalGun.BLACK_PANEL.getMaterial());
									b.setData(PortalGun.BLACK_PANEL.GetData());// TODO
								}

							}
						}
					}
				}
				return true;

			}

		}, "�����ϰ������/���ɰʳ�������ʪ", "�ʥƥ������֤�����䤽��¾�֥�å��ˤ�����", "����˹����ɤ����֤��ޤ���"));

		addbutton(new Button(Material.STONE, 1, (short) 0, "������", 6, new GUIFunction() {

			@Override
			public boolean click(Player p, ClickType type) {

				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					editor.SaveToFile();
				}
				return true;

			}

		}, "�ƥ��ȥ�����С�����¸���ޤ���"));

		addbutton(new Button(Material.STONE, 1, (short) 0, "�����֤��ƽ�λ", 7, new GUIFunction() {

			@Override
			public boolean click(Player p, ClickType type) {

				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					editor.Exit();
				}
				return false;

			}

		}, "�ƥ��ȥ�����С�����¸���Ƥ���", "��λ���ޤ���"));

		addbutton(new Button(Material.IRON_FENCE, 1, (short) 0, "���", 9, new GUIFunction() {

			@Override
			public boolean click(Player p, ClickType type) {

				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {

					if (!editor.Pos1Empt() && !editor.Pos2Empt()) {

						TestingElement e1 = editor.getElement(editor.getPos1());
						TestingElement e2 = editor.getElement(editor.getPos2());

						if (e1 != null && e2 != null) {

							if (e1.equals(e2)) {
								p.sendMessage("�����Ϥ�Ʊ�����֤Ǥ�");
								return false;
							}

							if (e1.getLinkType().equals(LinkType.DNC)) {
								p.sendMessage("������å����򤷤����֤��������Բ�ǽ�Ǥ�");
								return false;
							}

							if (e2.getLinkType().equals(LinkType.DNC)) {
								p.sendMessage("������å����򤷤����֤��������Բ�ǽ�Ǥ�");
								return false;
							}

							if (e1.getLinkType().equals(LinkType.OUTPUT) && e2.getLinkType().equals(LinkType.OUTPUT)) {
								p.sendMessage("��������Ʊ�Τϥ�󥯤Ǥ��ޤ���");
								return false;
							} else if (e1.getLinkType().equals(LinkType.INPUT)
									&& e2.getLinkType().equals(LinkType.INPUT)) {
								p.sendMessage("��������Ʊ�Τϥ�󥯤Ǥ��ޤ���");
								return false;
							}

							if ((e1.getLinkType().equals(LinkType.OUT_IN) && e1.getLinkType().equals(LinkType.OUT_IN))
									|| ((e1.getLinkType().equals(LinkType.OUTPUT)
											|| e1.getLinkType().equals(LinkType.OUT_IN))
											&& e2.getLinkType().equals(LinkType.INPUT))
									|| ((e2.getLinkType().equals(LinkType.INPUT)
											|| e2.getLinkType().equals(LinkType.OUT_IN))
											&& e1.getLinkType().equals(LinkType.OUTPUT))) {
								if (e1.getLinkType().equals(LinkType.OUT_IN) && e1.getLinkType().equals(LinkType.OUT_IN)) {
									p.sendMessage("�����ϴط�����ư���ФǤ��ʤ��ä��ΤǱ�����å����򤷤����֤���ϤȤ��ޤ���");
								}
								editor.Link(e1, e2);
								p.sendMessage("��󥯤��������ޤ���:������å����򤬥����å�");
								return false;
								// e1 = output e2 = input
							} else if (((e1.getLinkType().equals(LinkType.INPUT)
									|| e1.getLinkType().equals(LinkType.OUT_IN))
									&& e2.getLinkType().equals(LinkType.OUTPUT))
									|| ((e2.getLinkType().equals(LinkType.OUTPUT)
											|| e2.getLinkType().equals(LinkType.OUT_IN))
											&& e1.getLinkType().equals(LinkType.INPUT))) {
								// e1 = input e2 = output
								editor.Link(e2, e1);
								p.sendMessage("��󥯤��������ޤ���:������å����򤬥����å�");
								return false;
							}

							p.sendMessage("���餫�Υ��顼�ǥ�󥯤˼��Ԥ��ޤ���");


						} else {
							p.sendMessage("�ƥ������֤����򤵤�Ƥ��ޤ���");
						}

					} else {
						p.sendMessage("�����ϰϤ���ꤷ�Ƥ�������");
					}

				}
				return false;

			}

		}, "��/������å������򤷤��ƥ�������Ʊ�Τ�", "��󥯤����ޤ���", "�����ϴط�����ư���ФǤ��ʤ��ä����", "������å����򤷤����֤���ϤȤ���Ƚ�Ǥ��ޤ�"));

	}

}
