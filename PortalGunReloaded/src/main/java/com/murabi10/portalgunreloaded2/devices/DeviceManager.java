package com.murabi10.portalgunreloaded2.devices;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.murabi10.portalgunreloaded2.PortalGun;
import com.murabi10.portalgunreloaded2.chambereditor.EditorFunction;
import com.murabi10.portalgunreloaded2.chambereditor.TestChamberEditor;
import com.murabi10.portalgunreloaded2.portalgun.PortalColor;
import com.murabi10.portalgunreloaded2.portalgun.PortalDevice;
import com.murabi10.portalgunreloaded2.selector.StringInputWait;
import com.murabi10.portalgunreloaded2.testingelement.TestingElement;
import com.murabi10.portalgunreloaded2.testingelement.fixture.AerialFaithPlate;
import com.murabi10.portalgunreloaded2.testingelement.fixture.Speaker;
import com.murabi10.portalgunreloaded2.testingelement.logicgate.Timer;

public class DeviceManager implements org.bukkit.event.Listener {
	public static ArrayList<Device> devices = new ArrayList<Device>();

	public static void Enable() {
		new Device(new Function() {
			public void func(Player player, Action action) {
				if ((action.equals(Action.LEFT_CLICK_AIR)) || (action.equals(Action.LEFT_CLICK_BLOCK))) {
					PortalDevice.getDeviceInstance(player).LaunchPortal(PortalColor.BLUE);
				}

				if ((action.equals(Action.RIGHT_CLICK_AIR)) || (action.equals(Action.RIGHT_CLICK_BLOCK))) {
					PortalDevice.getDeviceInstance(player).LaunchPortal(PortalColor.ORANGE);
				}

			}
		}, Material.WOOD_SWORD, (short) 0, "PORTALGUN", "デュアルポータルガン",
				new String[] { "Aperture Handheld Portal Device", "右クリックでオレンジ",
						"左クリックでブルーの", "ポータルが打てる" }).Register();

		new Device(new Function() {
			public void func(Player player, Action a) {
				PortalDevice.getDeviceInstance(player).LaunchPortal(PortalColor.BLUE);
			}
		}, Material.WOOD_SWORD, (short) 0, "BROKEN_PORTALGUN", "シングルポータルガン",
				new String[] { "Aperture Handheld Portal Device",
						"クリックでブルーの", "ポータルが打てる" }).Register();

		new Device(new Function() {
			public void func(Player player, Action action) {
				TestChamberEditor editor = TestChamberEditor.getEditor(player);

				if (editor != null) {
					if (action.equals(Action.LEFT_CLICK_BLOCK)) {
						editor.LClick();
					}

					if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
						editor.RClick();
					}

				}
			}
		}, Material.WOOD_AXE, (short) 0, "SELECTOR", "セレクタ", new String[] { "右/左クリックで", "範囲選択ができる" }).Register();

		new Device(new Function() {
			public void func(Player player, Action action) {
				TestChamberEditor editor = TestChamberEditor.getEditor(player);

				if (editor != null) {
					if ((action.equals(Action.RIGHT_CLICK_BLOCK)) || (action.equals(Action.RIGHT_CLICK_AIR))) {
						PortalGun.fixture.openGUI(player);
					}

				}
			}
		}, Material.WOOD_HOE, (short) 0, "PLACER", "エディターメニュー",
				new String[] { "右クリックでメニューを開く。", "セーブしたり", "選択範囲内の編集を行ったり",
						"テスト装置を設置したりできる" }).Register();

		new Device(new Function() {
			public void func(Player player, Action action) {
				TestChamberEditor editor = TestChamberEditor.getEditor(player);

				if (editor != null) {
					final Location loc = player.getTargetBlock(null, 20).getLocation();

					TestingElement target = editor.getElement(loc);

					if (target != null) {
						if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
							player.sendMessage("データタイプ: " + target.getType().toString());

							/*if (!target.getLinkType().equals(LinkType.DNC)) {
							  player.sendMessage("入出力タイプ: " + target.getLinkType().toString());
							  player.sendMessage("この装置の入力につながっている装置：");
							  for (TestingElement sws : target.Switches()) {
							    player.sendMessage("座標: " + sws.getRelative1(editor.getOrigin()).toString());
							    player.sendMessage("タイプ: " + sws.getType().toString());
							  }
							  player.sendMessage("この装置の出力につながっている装置：");
							  Iterator localIterator2; for (??? = editor.elements().iterator(); ???.hasNext();
							      localIterator2.hasNext())
							  {
							    TestingElement in = (TestingElement)???.next();
							    localIterator2 = in.Switches().iterator(); continue;TestingElement sws = (TestingElement)localIterator2.next();
							    if (sws.equals(target)) {
							      player.sendMessage("座標: " + in.getRelative1(editor.getOrigin()).toString());
							      player.sendMessage("タイプ: " + in.getType().toString());
							    }
							  }
							}
							else {
							  player.sendMessage("(入出力関係を持つことができない装置です)");
							}*/

							switch (target.getType()) {
							case GEL_DROPPER:
								AerialFaithPlate afp = (AerialFaithPlate) target;
								player.sendMessage("発射方向(Pitch): " + afp.getPitch());
								player.sendMessage("発射方向(Yaw):   " + afp.getYaw());
								player.sendMessage("発射力(Power):   " + afp.getPower());
								break;
							case OR:
								Speaker speaker = (Speaker) target;
								player.sendMessage("設定されたメッセージ: " + speaker.getString());
								break;
							case TIMER:
								Timer timer = (Timer) target;
								player.sendMessage("設定秒数: " + timer.getDelaySec());
								break;
							default:
								break;

							}

						} else if (action.equals(Action.LEFT_CLICK_BLOCK)) {
							final Player p = player;

							switch (target.getType()) {
							case GEL_DROPPER:
								player.sendMessage("Pitch(90~-90), Yaw(360~0), Powerの順にチャット入力してください >");
								try {
									new StringInputWait(player, new EditorFunction() {
										int phase = 0;

										public boolean reveive(String str) {
											if (TestChamberEditor.getEditor(p) == null) {
												return false;
											}
											try {
												float input = Float.parseFloat(str);

												AerialFaithPlate afp = (AerialFaithPlate) TestChamberEditor.getEditor(p)
														.getElement(loc);

												if (this.phase == 0) {
													if ((input < 90.0F) && (input > -90.0F)) {
														afp.setPitch(input);
														this.phase += 1;
													} else {
														p.sendMessage("範囲を超えています。Pitchは90~-90の範囲にしてください");
													}
													return true;
												}
												if (this.phase == 1) {
													if ((input < 360.0F) && (input > 0.0F)) {
														afp.setYaw(input);
														this.phase += 1;
													} else {
														p.sendMessage("範囲を超えています。Yawは360~0の範囲にしてください");
													}
													return true;
												}

												if ((input < 100.0F) && (input > 0.0F)) {
													afp.setPower(input);
													p.sendMessage("完了しました");
													return false;
												}
												p.sendMessage("範囲を超えています。Powerは100~0の範囲にしてください");
												return true;

											} catch (NumberFormatException e) {
												p.sendMessage("数を入力してください: " + e.getMessage());
											}
											return true;
										}
									});
								} catch (Exception e) {
									e.printStackTrace();
								}

							case OR:
								player.sendMessage("新しいメッセージをチャット入力してください >");
								try {
									new StringInputWait(player, new EditorFunction() {
										public boolean reveive(String str) {
											if (TestChamberEditor.getEditor(p) == null) {
												return false;
											}
											Speaker speaker = (Speaker) TestChamberEditor.getEditor(p).getElement(loc);
											speaker.setString(str);
											p.sendMessage("完了しました");
											return false;
										}
									});
								} catch (Exception e) {
									e.printStackTrace();
								}

							case TIMER:
								player.sendMessage("設定秒数をチャット入力してください");
								try {
									new StringInputWait(player, new EditorFunction() {
										public boolean reveive(String str) {
											if (TestChamberEditor.getEditor(p) == null) {
												return false;
											}
											Timer timer = (Timer) TestChamberEditor.getEditor(p).getElement(loc);
											try {
												int delay = Integer.parseInt(str);
												if (delay <= 60) {
													timer.setDelaySec(delay);
													p.sendMessage("完了しました");
													return false;
												}
												p.sendMessage("大きすぎます: 60以下にしてください");
												return true;
											} catch (NumberFormatException e) {
												p.sendMessage("整数で入力してください: " + e.getMessage());
											}
											return true;
										}
									});
								} catch (Exception e) {
									e.printStackTrace();
								}
							default:
								break;

							}

						}
					} else {
						player.sendMessage("ターゲットが存在しません");
					}

				}
			}
		}, Material.WOOD_HOE, (short) 0, "INFO", "詳細エディタ",
				new String[] { "右でタイプ表示、左で詳細編集。", "ロジックゲートの種類を調べたり", "空中信頼性プレートやタイマーの",
						"詳細設定ができる" }).Register();
	}

	public static void add(Device d) {
		if (!devices.contains(d)) {
			devices.add(d);
		}
	}

	public static Device getDevice(String str) {
		for (Device d : devices) {
			if (d.getName().equals(str)) {
				return d;
			}
		}
		return null;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		ItemStack item = e.getItem();
		if ((item != null) && (item.hasItemMeta())) {
			ItemMeta meta = item.getItemMeta();
			if (meta.hasLore()) {
				List<String> lore = meta.getLore();
				Material material = item.getType();
				short data = item.getDurability();
				for (Device device : devices) {
					ItemStack d = device.getItem();
					if ((d.getType().equals(material)) && (d.getDurability() == data) &&
							(d.getItemMeta().getLore().equals(lore))) {
						device.runFunction(e.getPlayer(), e.getAction());
						e.setCancelled(true);
					}
				}
			}
		}
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\devices\DeviceManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */