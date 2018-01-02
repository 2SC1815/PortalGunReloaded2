package com.murabi10.portalgunreloaded.chambereditor;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import com.murabi10.portalgunreloaded.gui.Button;
import com.murabi10.portalgunreloaded.gui.GUI;
import com.murabi10.portalgunreloaded.gui.GUIFunction;
import com.murabi10.portalgunreloaded.testingelement.area.GoalPoint;
import com.murabi10.portalgunreloaded.testingelement.dropper.CubeDropper;
import com.murabi10.portalgunreloaded.testingelement.fixture.Door;
import com.murabi10.portalgunreloaded.testingelement.fixture.SuperCollidingSuperButton;
import com.murabi10.portalgunreloaded.testingelement.objects.CubeType;

public class ElementPlaceGUI extends GUI {

	@Override
	public void init() {

		setMenuName("���֤���ƥ������֤�����Ǥ�������");
		setSize(3);

		/*
		 * addbutton(new Button(Material.REDSTONE_TORCH_ON, 1, (short) 0,
		 * "�졼����", 4, new GUIFunction() {
		 *
		 * @Override public boolean click(Player p, ClickType type) {
		 * TestChamberEditor editor = TestChamberEditor.getEditor(p);
		 *
		 * if (editor != null) {
		 *
		 * if (editor.getPointLoc() != null) {
		 *
		 * ThermalDiscouragementBeam beam = new
		 * ThermalDiscouragementBeam(editor.getOrigin(), editor.getsel(),
		 * editor.getX(editor.getPointLoc().getBlock().getRelative(editor.getsel
		 * ()).getLocation()),
		 * editor.getY(editor.getPointLoc().getBlock().getRelative(editor.getsel
		 * ()).getLocation()),
		 * editor.getZ(editor.getPointLoc().getBlock().getRelative(editor.getsel
		 * ()).getLocation()));
		 *
		 * editor.AddTestElement(beam); beam.initRunnable(); } else {
		 * p.sendMessage("�����ϰϤ���ꤹ�뤫�����ϰϤ�����ˤ��Ƥ���������"); } } return true;
		 *
		 * }
		 *
		 * }, "�ⲹ�˻ߥӡ��ࡧ", "����ǽ�Ϥι⤤Ǯ��������ž�����塼�֤�", "�ʤ����ꡢ�ݡ�������̲᤹�롣"));
		 */

		addbutton(new Button(Material.GOLD_BLOCK, 1, (short) 0, "����������", 0, new GUIFunction() {

			@Override
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					if (!editor.Pos1Empt() && !editor.Pos2Empt()) {

						if (editor.getPos1().distance(editor.getPos2()) < 16) {

							GoalPoint goal = new GoalPoint(editor.getOrigin(), editor.getX(editor.getPos1()),
									editor.getY(editor.getPos1()), editor.getZ(editor.getPos1()),
									editor.getX(editor.getPos2()), editor.getY(editor.getPos2()),
									editor.getZ(editor.getPos2()));

							editor.AddTestElement(goal);
							goal.initRunnable();
						} else {
							p.sendMessage("�ϰϤ��������ޤ�");
						}
					} else {
						p.sendMessage("�ϰϤ���ꤷ�Ƥ�������");
					}
				}
				return true;

			}

		}, "������������", "����˿�����", "������С��򥯥ꥢ�������Ȥˤʤ�ޤ�"));

		addbutton(new Button(Material.IRON_BLOCK, 1, (short) 0, "�ɥ�", 1, new GUIFunction() {

			@Override
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					if (!editor.Pos1Empt() && !editor.Pos2Empt()) {
						if (editor.getPos1().distance(editor.getPos2()) < 16) {

							Door door = new Door(editor.getOrigin(), editor.getX(editor.getPos1()),
									editor.getY(editor.getPos1()), editor.getZ(editor.getPos1()),
									editor.getX(editor.getPos2()), editor.getY(editor.getPos2()),
									editor.getZ(editor.getPos2()));

							editor.AddTestElement(door);
							door.initRunnable();
						} else {
							p.sendMessage("�ϰϤ��������ޤ�");
						}
					} else {
						p.sendMessage("�ϰϤ���ꤷ�Ƥ�������");
					}
				}
				return true;

			}

		}, "�ɥ���", "�����å��ȥ�󥯤���ư����", "����褬ON���ȳ���"));

		addbutton(new Button(Material.BONE_BLOCK, 1, (short) 0, "1500MW ����Ķ���ͥ����ѡ��ܥ���", 2, new GUIFunction() {

			@Override
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {

					if (editor.getPointLoc() != null) {

						SuperCollidingSuperButton button = new SuperCollidingSuperButton(editor.getOrigin(),
								editor.getsel(), editor.getX(editor.getPointLoc()), editor.getY(editor.getPointLoc()),
								editor.getZ(editor.getPointLoc()));

						editor.AddTestElement(button);
						button.initRunnable();
					} else {
						p.sendMessage("�����ϰϤ���ꤹ�뤫�����ϰϤ�����ˤ��Ƥ���������");
					}
				}
				return true;

			}
		}, "1500MW ����Ķ���ͥ����ѡ��ܥ���:", "�����å������塼�֤�ץ쥤�䡼������ON�ˤʤ�"));

		addbutton(new Button(Material.DISPENSER, 1, (short) 0, "�ٽų�Ǽ���塼�֥ɥ�åѡ�", 3, new GUIFunction() {

			@SuppressWarnings("deprecation")
			@Override
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {

					if (editor.getPointLoc() != null) {
						if (editor.getsel().equals(BlockFace.DOWN)) {
							if (editor.getPointLoc().getBlock().getRelative(editor.getsel(),1).isEmpty() && editor.getPointLoc().getBlock().getRelative(editor.getsel(),2).isEmpty()) {
								editor.getPointLoc().getBlock().getRelative(editor.getsel()).setType(Material.DROPPER);
								editor.getPointLoc().getBlock().getRelative(editor.getsel()).setData((byte)1);

								CubeDropper dropper = new CubeDropper(editor.getOrigin(), editor.getsel(), CubeType.NORMAL,
								editor.getX(editor.getPointLoc().getBlock().getRelative(editor.getsel()).getLocation()),
								editor.getY(editor.getPointLoc().getBlock().getRelative(editor.getsel()).getLocation()),
								editor.getZ(
										editor.getPointLoc().getBlock().getRelative(editor.getsel()).getLocation()));

						editor.AddTestElement(dropper);
						dropper.initRunnable();
							} else {
								p.sendMessage("�������ζ�����­��ޤ���");
							}
						} else {
							p.sendMessage("�������֤ϲ������ˤ������֤Ǥ��ޤ���");
						}
					} else {
						p.sendMessage("�����ϰϤ���ꤹ�뤫�����ϰϤ�����ˤ��Ƥ���������");
					}
				}
				return true;

			}
		}, "�ɥ�åѡ�:", "���塼�֤򶡵뤹��"));

	}

}
