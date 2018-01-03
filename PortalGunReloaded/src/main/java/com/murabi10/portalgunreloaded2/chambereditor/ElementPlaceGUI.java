package com.murabi10.portalgunreloaded2.chambereditor;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import com.murabi10.portalgunreloaded2.Methods;
import com.murabi10.portalgunreloaded2.gui.Button;
import com.murabi10.portalgunreloaded2.gui.GUI;
import com.murabi10.portalgunreloaded2.gui.GUIFunction;
import com.murabi10.portalgunreloaded2.portalgun.Portal;
import com.murabi10.portalgunreloaded2.portalgun.PortalColor;
import com.murabi10.portalgunreloaded2.testingelement.area.AreaSwitch;
import com.murabi10.portalgunreloaded2.testingelement.area.GoalPoint;
import com.murabi10.portalgunreloaded2.testingelement.dropper.CubeDropper;
import com.murabi10.portalgunreloaded2.testingelement.field.Fizzler;
import com.murabi10.portalgunreloaded2.testingelement.field.LaserField;
import com.murabi10.portalgunreloaded2.testingelement.fixture.AerialFaithPlate;
import com.murabi10.portalgunreloaded2.testingelement.fixture.Door;
import com.murabi10.portalgunreloaded2.testingelement.fixture.Indicator;
import com.murabi10.portalgunreloaded2.testingelement.fixture.ItemFrameElement;
import com.murabi10.portalgunreloaded2.testingelement.fixture.PortalSpawner;
import com.murabi10.portalgunreloaded2.testingelement.fixture.Speaker;
import com.murabi10.portalgunreloaded2.testingelement.fixture.SuperCollidingSuperButton;
import com.murabi10.portalgunreloaded2.testingelement.logicgate.NOT;
import com.murabi10.portalgunreloaded2.testingelement.logicgate.OR;
import com.murabi10.portalgunreloaded2.testingelement.logicgate.RSInputInterface;
import com.murabi10.portalgunreloaded2.testingelement.logicgate.RSOutputInterface;
import com.murabi10.portalgunreloaded2.testingelement.logicgate.Timer;
import com.murabi10.portalgunreloaded2.testingelement.objects.CubeType;

public class ElementPlaceGUI extends GUI {
	public void init() {
		setMenuName("設置するテスト装置を選んでください");
		setSize(3);

		addbutton(new Button(Material.GOLD_BLOCK, 1, (short) 0, "ゴール地点", 0, new GUIFunction() {
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					if ((!editor.Pos1Empt()) && (!editor.Pos2Empt())) {
						if (editor.getPos1().distance(editor.getPos2()) < 16.0D) {
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
		}, new String[] {

				"ゴール地点：", "これに触れると", "チェンバーをクリアしたことになります" }));

		addbutton(new Button(Material.IRON_BLOCK, 1, (short) 0, "ドア", 1, new GUIFunction() {
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					if ((!editor.Pos1Empt()) && (!editor.Pos2Empt())) {
						if (editor.getPos1().distance(editor.getPos2()) < 16.0D) {
							if ((editor.getPos1().getBlockX() == editor.getPos2().getBlockX()) ||
									(editor.getPos1().getBlockY() == editor.getPos2().getBlockY()) ||
									(editor.getPos1().getBlockZ() == editor.getPos2().getBlockZ())) {
								Door door = new Door(editor.getOrigin(), editor.getX(editor.getPos1()),
										editor.getY(editor.getPos1()), editor.getZ(editor.getPos1()),
										editor.getX(editor.getPos2()), editor.getY(editor.getPos2()),
										editor.getZ(editor.getPos2()));

								editor.AddTestElement(door);
								door.initRunnable();
							} else {
								p.sendMessage("立体な配置にすることはできません");
							}
						} else {
							p.sendMessage("範囲が広すぎます");
						}
					} else {
						p.sendMessage("範囲を確定してください");
					}
				}
				return true;
			}
		}, new String[] {

				"ドア：", "スイッチとリンクして動く。", "リンク先がONだと開く" }));

		addbutton(new Button(Material.BONE_BLOCK, 1, (short) 0, "1500MW 強化超衝突スーパーボタン", 2, new GUIFunction() {
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
		}, new String[] {

				"1500MW 強化超衝突スーパーボタン:", "スイッチ。キューブやプレイヤーが乗るとONになる" }));

		addbutton(new Button(Material.DISPENSER, 1, (short) 0, "荷重格納キューブドロッパー", 3, new GUIFunction() {

			@SuppressWarnings("deprecation")
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					if (editor.getPointLoc() != null) {
						if (editor.getsel().equals(BlockFace.DOWN)) {
							if ((editor.getPointLoc().getBlock().getRelative(editor.getsel(), 1).isEmpty()) &&
									(editor.getPointLoc().getBlock().getRelative(editor.getsel(), 2).isEmpty())) {
								editor.getPointLoc().getBlock().getRelative(editor.getsel()).setType(Material.DROPPER);
								editor.getPointLoc().getBlock().getRelative(editor.getsel()).setData((byte) 8);

								CubeDropper dropper = new CubeDropper(editor.getOrigin(), editor.getsel(),
										CubeType.NORMAL,
										editor.getX(editor.getPointLoc().getBlock().getRelative(editor.getsel())
												.getLocation()),
										editor.getY(editor.getPointLoc().getBlock().getRelative(editor.getsel())
												.getLocation()),
										editor.getZ(editor.getPointLoc().getBlock().getRelative(editor.getsel())
												.getLocation()));

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
		}, new String[] {

				"ドロッパー:", "キューブを供給する" }));

		addbutton(new Button(Material.ITEM_FRAME, 1, (short) 0, "額縁", 4, new GUIFunction() {
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					if (editor.getPointLoc() != null) {
						if ((!editor.getsel().equals(BlockFace.UP)) && (!editor.getsel().equals(BlockFace.DOWN))) {
							if (editor.getPointLoc().getBlock().getType().isSolid()) {
								ItemFrameElement fr = new ItemFrameElement(editor.getOrigin(), editor.getsel(),
										editor.getX(editor.getPointLoc()), editor.getY(editor.getPointLoc()),
										editor.getZ(editor.getPointLoc()));

								editor.AddTestElement(fr);
								fr.initRunnable();
							} else {
								p.sendMessage("固体ブロックにのみ設置できます");
							}
						} else {
							p.sendMessage("額縁は上と下には設置できません");
						}
					} else {
						p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
					}
				}
				return true;
			}
		}, new String[] {

				"額縁:  普通の額縁は保存されませんが", "これで呼び出された額縁と", "その中身は保持されます", "ただしエンチャントは失われます" }));

		addbutton(new Button(Material.ITEM_FRAME, 2, (short) 0, "インジケーター", 5, new GUIFunction() {
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					if (editor.getPointLoc() != null) {
						if ((!editor.getsel().equals(BlockFace.UP)) && (!editor.getsel().equals(BlockFace.DOWN))) {
							if (editor.getPointLoc().getBlock().getType().isSolid()) {
								Indicator fr = new Indicator(editor.getOrigin(), editor.getsel(),
										editor.getX(editor.getPointLoc()), editor.getY(editor.getPointLoc()),
										editor.getZ(editor.getPointLoc()));

								editor.AddTestElement(fr);
								fr.initRunnable();
							} else {
								p.sendMessage("固体ブロックにのみ設置できます");
							}
						} else {
							p.sendMessage("インジケーターは上と下には設置できません");
						}
					} else {
						p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
					}
				}
				return true;
			}
		}, new String[] {

				"インジケーター: 入力装置。", "スイッチとつなげて", "状態を確認するのに使う", "本体は張り付いてるブロック。" }));

		addbutton(new Button(Material.PISTON_BASE, 1, (short) 0, "Aerial Faith Plate", 6, new GUIFunction() {

			@SuppressWarnings("deprecation")
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					if (editor.getPointLoc() != null) {
						editor.getPointLoc().getBlock().setType(Material.PISTON_BASE);
						editor.getPointLoc().getBlock().setData((byte) 1);

						AerialFaithPlate afp = new AerialFaithPlate(editor.getOrigin(),
								editor.getX(editor.getPointLoc()), editor.getY(editor.getPointLoc()),
								editor.getZ(editor.getPointLoc()), 0.0F, -90.0F, 4);

						editor.AddTestElement(afp);
						afp.initRunnable();
					} else {
						p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
					}
				}
				return true;
			}
		}, new String[] {

				"空中信頼性プレート:", "機械式のジャンプ台。", "詳細エディタで設定できる", "空中に射出された被験者の問題解決能力をテストする" }));

		addbutton(new Button(Material.WATCH, 1, (short) 0, "タイマー", 7, new GUIFunction() {
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					if (editor.getPointLoc() != null) {
						Timer timer = new Timer(editor.getOrigin(), editor.getX(editor.getPointLoc()),
								editor.getY(editor.getPointLoc()), editor.getZ(editor.getPointLoc()));

						editor.AddTestElement(timer);
						timer.initRunnable();
					} else {
						p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
					}
				}
				return true;
			}
		}, new String[] {

				"タイマー:", "立ち上がりエッジでONになり、", "指定時間たつとOFFに戻る。", "詳細エディタで時間を指定できる" }));

		addbutton(new Button(Material.DIODE, 1, (short) 0, "NOT Gate", 8, new GUIFunction() {
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					if (editor.getPointLoc() != null) {
						NOT not = new NOT(editor.getOrigin(), editor.getX(editor.getPointLoc()),
								editor.getY(editor.getPointLoc()), editor.getZ(editor.getPointLoc()));

						editor.AddTestElement(not);
						not.initRunnable();
					} else {
						p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
					}
				}
				return true;
			}
		}, new String[] {

				"論理否定:", "入力がONだとOFFを出力し、", "OFFだとONを出力する", "入力が複数ある場合は否定論理積として動作する", "入力に何もつながっていないと常にOFF" }));

		addbutton(new Button(Material.DIODE, 2, (short) 0, "OR Gate", 9, new GUIFunction() {
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					if (editor.getPointLoc() != null) {
						OR or = new OR(editor.getOrigin(), editor.getX(editor.getPointLoc()),
								editor.getY(editor.getPointLoc()), editor.getZ(editor.getPointLoc()));

						editor.AddTestElement(or);
						or.initRunnable();
					} else {
						p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
					}
				}
				return true;
			}
		}, new String[] {

				"論理和:", "入力のいずれかがONだとONになる", "入力がすべてOFFだとOFFになる" }));

		addbutton(new Button(Material.IRON_FENCE, 1, (short) 0, "エリアスイッチ", 10, new GUIFunction() {
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					if ((!editor.Pos1Empt()) && (!editor.Pos2Empt())) {
						if (editor.getPos1().distance(editor.getPos2()) < 16.0D) {
							AreaSwitch as = new AreaSwitch(editor.getOrigin(), editor.getX(editor.getPos1()),
									editor.getY(editor.getPos1()), editor.getZ(editor.getPos1()),
									editor.getX(editor.getPos2()), editor.getY(editor.getPos2()),
									editor.getZ(editor.getPos2()));

							editor.AddTestElement(as);
							as.initRunnable();
						} else {
							p.sendMessage("範囲が広すぎます");
						}
					} else {
						p.sendMessage("範囲を確定してください");
					}
				}
				return true;
			}
		}, new String[] {

				"エリアスイッチ:", "範囲内にプレイヤーが入ると", "ONになる" }));

		addbutton(new Button(Material.STAINED_GLASS, 1, (short) 3, "物質消去グリッド", 11, new GUIFunction() {
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					if ((!editor.Pos1Empt()) && (!editor.Pos2Empt())) {
						if (editor.getPos1().distance(editor.getPos2()) < 16.0D) {
							if ((editor.getPos1().getBlockX() == editor.getPos2().getBlockX()) ||
									(editor.getPos1().getBlockY() == editor.getPos2().getBlockY()) ||
									(editor.getPos1().getBlockZ() == editor.getPos2().getBlockZ())) {
								Fizzler fizzler = new Fizzler(editor.getOrigin(), editor.getX(editor.getPos1()),
										editor.getY(editor.getPos1()), editor.getZ(editor.getPos1()),
										editor.getX(editor.getPos2()), editor.getY(editor.getPos2()),
										editor.getZ(editor.getPos2()));

								editor.AddTestElement(fizzler);
								fizzler.initRunnable();
							} else {
								p.sendMessage("立体な配置にすることはできません");
							}
						} else {
							p.sendMessage("範囲が広すぎます");
						}
					} else {
						p.sendMessage("範囲を確定してください");
					}
				}
				return true;
			}
		}, new String[] {

				"物質消去グリッド:", "通過する未認可機器を蒸発させる", "スイッチとつなげられる" }));

		addbutton(new Button(Material.STAINED_GLASS, 1, (short) 14, "レーザーフィールド", 12, new GUIFunction() {
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					if ((!editor.Pos1Empt()) && (!editor.Pos2Empt())) {
						if (editor.getPos1().distance(editor.getPos2()) < 16.0D) {
							if ((editor.getPos1().getBlockX() == editor.getPos2().getBlockX()) ||
									(editor.getPos1().getBlockY() == editor.getPos2().getBlockY()) ||
									(editor.getPos1().getBlockZ() == editor.getPos2().getBlockZ())) {
								LaserField lf = new LaserField(editor.getOrigin(), editor.getX(editor.getPos1()),
										editor.getY(editor.getPos1()), editor.getZ(editor.getPos1()),
										editor.getX(editor.getPos2()), editor.getY(editor.getPos2()),
										editor.getZ(editor.getPos2()));

								editor.AddTestElement(lf);
								lf.initRunnable();
							} else {
								p.sendMessage("立体な配置にすることはできません");
							}
						} else {
							p.sendMessage("範囲が広すぎます");
						}
					} else {
						p.sendMessage("範囲を確定してください");
					}
				}
				return true;
			}
		}, new String[] {

				"レーザーフィールド:", "通過する生物を絶命させてしまう", "スイッチとつなげられる" }));

		addbutton(new Button(Material.NOTE_BLOCK, 1, (short) 0, "スピーカー", 13, new GUIFunction() {
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					if (editor.getPointLoc() != null) {
						Speaker spk = new Speaker(editor.getOrigin(), editor.getX(editor.getPointLoc()),
								editor.getY(editor.getPointLoc()), editor.getZ(editor.getPointLoc()));

						editor.AddTestElement(spk);
						spk.initRunnable();
					} else {
						p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
					}
				}
				return true;
			}
		}, new String[] {

				"スピーカー:", "メッセージを録音しておける", "詳細エディタで設定できる", "立ち上がりで一回起動（エディターでは無制限）" }));

		addbutton(new Button(Material.BANNER, 1, (short) 4, "ポータルスポナー（青）", 14, new GUIFunction() {
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					if (editor.getPointLoc() != null) {
						Portal portal = new Portal(p, PortalColor.BLUE, editor.getsel(),
								Methods.YawToBlockFace(p.getEyeLocation().getYaw()),
								editor.getPointLoc().getBlock().getRelative(editor.getsel()).getLocation(),
								new Location[0]);
						if (Methods.isSuitable(portal, false)) {
							PortalSpawner spawner = new PortalSpawner(editor.getOrigin(), editor.getsel(),
									Methods.YawToBlockFace(p.getEyeLocation().getYaw()),
									editor.getX(editor.getPointLoc()), editor.getY(editor.getPointLoc()),
									editor.getZ(editor.getPointLoc()), PortalColor.BLUE);

							editor.AddTestElement(spawner);
							spawner.initRunnable();
						} else {
							p.sendMessage("その位置にはポータルが設置できません");
						}

						portal.stopParticle();
						portal = null;
					} else {
						p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
					}
				}
				return true;
			}
		}, new String[] {

				"ポータルスポナー:", "ポータルを発生させる", "スイッチをつなげると", "立ち上がりで発生する" }));

		addbutton(new Button(Material.BANNER, 1, (short) 14, "ポータルスポナー（橙）", 15, new GUIFunction() {
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					if (editor.getPointLoc() != null) {
						Portal portal = new Portal(p, PortalColor.ORANGE, editor.getsel(),
								Methods.YawToBlockFace(p.getEyeLocation().getYaw()),
								editor.getPointLoc().getBlock().getRelative(editor.getsel()).getLocation(),
								new Location[0]);
						if (Methods.isSuitable(portal, false)) {
							PortalSpawner spawner = new PortalSpawner(editor.getOrigin(), editor.getsel(),
									Methods.YawToBlockFace(p.getEyeLocation().getYaw()),
									editor.getX(editor.getPointLoc()), editor.getY(editor.getPointLoc()),
									editor.getZ(editor.getPointLoc()), PortalColor.ORANGE);

							editor.AddTestElement(spawner);
							spawner.initRunnable();
						} else {
							p.sendMessage("その位置にはポータルが設置できません");
						}

						portal.stopParticle();
						portal = null;
					} else {
						p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
					}
				}
				return true;
			}
		}, new String[] {

				"ポータルスポナー:", "ポータルを発生させる", "スイッチをつなげると", "立ち上がりで発生する" }));

		addbutton(new Button(Material.REDSTONE_COMPARATOR, 1, (short) 0, "RSインプットインターフェース", 16, new GUIFunction() {
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					if (editor.getPointLoc() != null) {
						RSInputInterface rsif = new RSInputInterface(editor.getOrigin(),
								editor.getX(editor.getPointLoc()),
								editor.getY(editor.getPointLoc()), editor.getZ(editor.getPointLoc()));

						editor.AddTestElement(rsif);
						rsif.initRunnable();
					} else {
						p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
					}
				}
				return true;
			}
		}, new String[] {

				"RSインプットインターフェース:", "レッドストーン入力するとONになるスイッチ" }));

		addbutton(new Button(Material.REDSTONE_TORCH_ON, 1, (short) 0, "RSアウトプットインターフェース", 17, new GUIFunction() {
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					if (editor.getPointLoc() != null) {
						RSOutputInterface rsif = new RSOutputInterface(editor.getOrigin(),
								editor.getX(editor.getPointLoc()),
								editor.getY(editor.getPointLoc()), editor.getZ(editor.getPointLoc()));

						editor.AddTestElement(rsif);
						rsif.initRunnable();
					} else {
						p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
					}
				}
				return true;
			}
		}, new String[] {

				"RSアウトプットインターフェース:", "ONだとRSブロックになり、OFFだと鉄ブロックになる" }));
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\chambereditor\ElementPlaceGUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */