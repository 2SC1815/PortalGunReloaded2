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

public class FixturePlaceGUI
		extends GUI {
	public void init() {
		setMenuName("アクションを選んでください");
		setSize(2);

		addbutton(new Button(Material.DIAMOND_BLOCK, 1, (short) 0, "スタート地点を設定", 0, new GUIFunction() {
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
		}, new String[] {

				"スタート地点を今立っている場所に", "設定します。", "スタート地点が埋まっているとテストが開始できません" }));

		addbutton(new Button(Material.CONCRETE, 1, (short) 7, "白い壁を黒くする", 1, new GUIFunction() {

			@SuppressWarnings("deprecation")
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if ((editor != null) && (editor.isCuboid())) {
					int minx = Math.min(editor.getPos1().getBlockX(), editor.getPos2().getBlockX());
					int miny = Math.min(editor.getPos1().getBlockY(), editor.getPos2().getBlockY());
					int minz = Math.min(editor.getPos1().getBlockZ(), editor.getPos2().getBlockZ());
					int maxx = Math.max(editor.getPos1().getBlockX(), editor.getPos2().getBlockX());
					int maxy = Math.max(editor.getPos1().getBlockY(), editor.getPos2().getBlockY());
					int maxz = Math.max(editor.getPos1().getBlockZ(), editor.getPos2().getBlockZ());

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
		}, new String[] {

				"選択範囲の白い壁を黒くします。", "黒い壁にはポータルが打てません。" }));

		addbutton(new Button(Material.CONCRETE, 1, (short) 0, "黒い壁を白くする", 2, new GUIFunction() {

			@SuppressWarnings("deprecation")
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if ((editor != null) && (editor.isCuboid())) {
					int minx = Math.min(editor.getPos1().getBlockX(), editor.getPos2().getBlockX());
					int miny = Math.min(editor.getPos1().getBlockY(), editor.getPos2().getBlockY());
					int minz = Math.min(editor.getPos1().getBlockZ(), editor.getPos2().getBlockZ());
					int maxx = Math.max(editor.getPos1().getBlockX(), editor.getPos2().getBlockX());
					int maxy = Math.max(editor.getPos1().getBlockY(), editor.getPos2().getBlockY());
					int maxz = Math.max(editor.getPos1().getBlockZ(), editor.getPos2().getBlockZ());

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
		}, new String[] {

				"選択範囲の黒い壁を白くします。", "白い壁にはポータルが打てます。" }));

		addbutton(new Button(Material.BARRIER, 1, (short) 0, "選択範囲を解除", 3, new GUIFunction() {
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					editor.clearPos();
				}
				return true;
			}
		}, new String[] {

				"選択範囲を解除します。" }));

		addbutton(new Button(Material.REDSTONE_TORCH_ON, 1, (short) 0, "テスト装置を設置する", 4, new GUIFunction() {
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					p.closeInventory();
					PortalGun.gimmicks.openGUI(p);
				}
				return false;
			}
		}, new String[] {

				"レーザーや物質消去グリッドなどの", "テスト用ギミックを設置します。" }));

		addbutton(new Button(Material.BARRIER, 1, (short) 0, "選択範囲内クリア", 5, new GUIFunction() {

			@SuppressWarnings("deprecation")
			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if ((editor != null) && (editor.isCuboid())) {
					int minx = Math.min(editor.getPos1().getBlockX(), editor.getPos2().getBlockX());
					int miny = Math.min(editor.getPos1().getBlockY(), editor.getPos2().getBlockY());
					int minz = Math.min(editor.getPos1().getBlockZ(), editor.getPos2().getBlockZ());
					int maxx = Math.max(editor.getPos1().getBlockX(), editor.getPos2().getBlockX());
					int maxy = Math.max(editor.getPos1().getBlockY(), editor.getPos2().getBlockY());
					int maxz = Math.max(editor.getPos1().getBlockZ(), editor.getPos2().getBlockZ());

					for (int x = minx; x < maxx + 1; x++) {
						for (int y = miny; y < maxy + 1; y++) {
							for (int z = minz; z < maxz + 1; z++) {
								Block b = new Location(editor.getOrigin().getWorld(), x, y, z).getBlock();

								if (editor.getElement(b.getLocation()) != null) {
									editor.RemoveElement(b.getLocation());
								}
								if (((!b.isLiquid()) && (!b.isEmpty())) || (b.getType().equals(Material.CONCRETE))) {
									b.setType(PortalGun.BLACK_PANEL.getMaterial());
									b.setData(PortalGun.BLACK_PANEL.GetData());
								}
							}
						}
					}
				}

				return true;
			}
		}, new String[] {

				"選択範囲内の白/黒壁以外の設置物", "（テスト装置や光源やその他ブロック）を除去し、", "代わりに黒い壁を設置します。" }));

		addbutton(new Button(Material.STONE, 1, (short) 0, "セーブ", 6, new GUIFunction() {

			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					editor.SaveToFile();
				}
				return true;
			}
		}, new String[] {

				"テストチェンバーを保存します。" }));

		addbutton(new Button(Material.STONE, 1, (short) 0, "セーブして終了", 7, new GUIFunction() {

			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					editor.Exit();
				}
				return false;
			}
		}, new String[] {

				"テストチェンバーを保存してから", "終了します。" }));

		addbutton(new Button(Material.IRON_FENCE, 1, (short) 0, "リンク", 9, new GUIFunction() {

			public boolean click(Player p, ClickType type) {
				TestChamberEditor editor = TestChamberEditor.getEditor(p);

				if (editor != null) {
					if ((!editor.Pos1Empt()) && (!editor.Pos2Empt())) {
						TestingElement e1 = editor.getElement(editor.getPos1());
						TestingElement e2 = editor.getElement(editor.getPos2());

						if ((e1 != null) && (e2 != null)) {
							if (e1.equals(e2)) {
								p.sendMessage("入出力が同じ装置です");
								return false;
							}

							if (e1.getLinkType().equals(LinkType.DNC)) {
								p.sendMessage("右クリック選択した装置は入出力不可能です");
								return false;
							}

							if (e2.getLinkType().equals(LinkType.DNC)) {
								p.sendMessage("左クリック選択した装置は入出力不可能です");
								return false;
							}

							if ((e1.getLinkType().equals(LinkType.OUTPUT))
									&& (e2.getLinkType().equals(LinkType.OUTPUT))) {
								p.sendMessage("出力装置同士はリンクできません");
								return false;
							}
							if ((e1.getLinkType().equals(LinkType.INPUT)) &&
									(e2.getLinkType().equals(LinkType.INPUT))) {
								p.sendMessage("入力装置同士はリンクできません");
								return false;
							}

							if (((e1.getLinkType().equals(LinkType.OUT_IN))
									&& (e2.getLinkType().equals(LinkType.OUT_IN)))
									|| (((!e1.getLinkType().equals(LinkType.OUTPUT)) &&
											(!e1.getLinkType().equals(LinkType.OUT_IN)))
											|| ((e2.getLinkType().equals(LinkType.INPUT))
													|| (((e2.getLinkType().equals(LinkType.INPUT)) ||
															(e2.getLinkType().equals(LinkType.OUT_IN))) &&
															(e1.getLinkType().equals(LinkType.OUTPUT)))))) {
								if ((e1.getLinkType().equals(LinkType.OUT_IN))
										&& (e2.getLinkType().equals(LinkType.OUT_IN))) {
									p.sendMessage("入出力関係が自動検出できなかったので右クリック選択した装置を出力としました");
								}
								editor.Link(e1, e2);
								p.sendMessage("リンクに成功しました:右クリック選択がスイッチ");
								return false;
							}
							if (((!e1.getLinkType().equals(LinkType.INPUT)) &&
									(!e1.getLinkType().equals(LinkType.OUT_IN)))
									|| ((e2.getLinkType().equals(LinkType.OUTPUT))
											|| (((e2.getLinkType().equals(LinkType.OUTPUT)) ||
													(e2.getLinkType().equals(LinkType.OUT_IN))) &&
													(e1.getLinkType().equals(LinkType.INPUT))))) {
								editor.Link(e2, e1);
								p.sendMessage("リンクに成功しました:左クリック選択がスイッチ");
								return false;
							}

							p.sendMessage("何らかのエラーでリンクに失敗しました");
						} else {
							p.sendMessage("テスト装置が選択されていません");
						}
					} else {
						p.sendMessage("選択範囲を確定してください");
					}
				}

				return false;
			}
		}, new String[] {

				"右/左クリックで選択したテスト装置同士を", "リンクさせます。", "入出力関係が自動検出できなかった場合", "右クリック選択した装置を出力として判断します" }));
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\chambereditor\FixturePlaceGUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */