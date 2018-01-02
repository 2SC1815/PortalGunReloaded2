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

		setMenuName("設置するテスト装置を選んでください");
		setSize(3);

		/*
		 * addbutton(new Button(Material.REDSTONE_TORCH_ON, 1, (short) 0,
		 * "レーザー", 4, new GUIFunction() {
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
		 * p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。"); } } return true;
		 *
		 * }
		 *
		 * }, "高温阻止ビーム：", "殺傷能力の高い熱線、方向転換キューブで", "曲げたり、ポータルを通過する。"));
		 */

		addbutton(new Button(Material.GOLD_BLOCK, 1, (short) 0, "ゴール地点", 0, new GUIFunction() {

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
							p.sendMessage("範囲が広すぎます");
						}
					} else {
						p.sendMessage("範囲を確定してください");
					}
				}
				return true;

			}

		}, "ゴール地点：", "これに触れると", "チェンバーをクリアしたことになります"));

		addbutton(new Button(Material.IRON_BLOCK, 1, (short) 0, "ドア", 1, new GUIFunction() {

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
							p.sendMessage("範囲が広すぎます");
						}
					} else {
						p.sendMessage("範囲を確定してください");
					}
				}
				return true;

			}

		}, "ドア：", "スイッチとリンクして動く。", "リンク先がONだと開く"));

		addbutton(new Button(Material.BONE_BLOCK, 1, (short) 0, "1500MW 強化超衝突スーパーボタン", 2, new GUIFunction() {

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
						p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
					}
				}
				return true;

			}
		}, "1500MW 強化超衝突スーパーボタン:", "スイッチ。キューブやプレイヤーが乗るとONになる"));

		addbutton(new Button(Material.DISPENSER, 1, (short) 0, "荷重格納キューブドロッパー", 3, new GUIFunction() {

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
								p.sendMessage("下方向の空きが足りません");
							}
						} else {
							p.sendMessage("この装置は下向きにしか設置できません");
						}
					} else {
						p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
					}
				}
				return true;

			}
		}, "ドロッパー:", "キューブを供給する"));

	}

}
