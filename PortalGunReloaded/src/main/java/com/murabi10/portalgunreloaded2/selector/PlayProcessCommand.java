package com.murabi10.portalgunreloaded2.selector;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.murabi10.portalgunreloaded2.testchamber.DataSystem;
import com.murabi10.portalgunreloaded2.testchamber.TestChamberData;
import com.murabi10.portalgunreloaded2.testchamber.TestProcess;
import com.murabi10.portalgunreloaded2.testchamber.TestQueue;

public class PlayProcessCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


		if ((sender instanceof BlockCommandSender)) {
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("play")) {

					BlockCommandSender s = (BlockCommandSender) sender;
					for (Entity ent : s.getBlock().getWorld().getNearbyEntities(s.getBlock().getLocation(), 5.0D, 5.0D, 5.0D)) {
						if ((ent instanceof Player)) {

							String chamberFileName = args[1];


							try {
								TestProcess p = DataSystem.getTestProcess(chamberFileName);

								((Player) ent).closeInventory();

								ent.sendMessage("しばらくおまちください");

								ArrayList<TestChamberData> data = new ArrayList<TestChamberData>();

								for (int i=0; i<p.getProcessSize(); i++)
									data.add( p.getTestChamber(i));

								TestQueue queue = new TestQueue((Player) ent, data,
										p.getExit() != null ? p.getExit() : ent.getLocation().clone(), ent.getLocation().clone());

								queue.BeginTest();
							} catch (ClassNotFoundException | IOException e) {
								e.printStackTrace();
							}


							break;

						}
					}

				} else {
					sender.sendMessage("このコマンドはプレイヤーからしか実行できないか、無効なコマンドです");
				}
			} else if (args.length == 1) {
				sender.sendMessage("引数が必要です");
			} else {
				sender.sendMessage("引数が長すぎます");
			}

		} else if (sender instanceof Player) {

			if (args.length >= 1) {
				if (args[0].equalsIgnoreCase("play")) {
					sender.sendMessage("このコマンドはコマンドブロックからしか実行できません");// list play edit [list add insert remove] create delete
				} else if (args[0].equalsIgnoreCase("list")) {

					for (String str : DataSystem.getTestProcesses())
						sender.sendMessage(str);

				} else if (args[0].equalsIgnoreCase("edit")) {



						try {
							TestProcess p = DataSystem.getTestProcess(args[1]);
							ArrayList<String> deleted = new ArrayList<String>();
							for (;;) {
								boolean y = false;
								for (int i=0; i < p.getProcessSize(); i++) {
									if (p.getTestChamber(i) == null) {
										deleted.add(p.getRegisteredName(i));
										p.removeChamber(i);
										y = true;
										break;
									}
								}
								if (y) continue;
								break;
							}



							if (args.length > 3) {

							if (args[2].equalsIgnoreCase("add")) {
								if (DataSystem.isCompleteExist(args[3])) {
									p.addChamber(args[3]);
									sender.sendMessage("追加しました");
								} else {
									sender.sendMessage("そのようなテストチェンバーは存在しません");
								}

							} else if (args[2].equalsIgnoreCase("insert")) {
								if (args.length > 4) {
								if (DataSystem.isCompleteExist(args[3])) {
									if (p.addChamberIn(args[3], Integer.parseInt(args[4]))) {
										sender.sendMessage("追加しました");
									} else {
										sender.sendMessage("追加できませんでした");
									}
								} else {
									sender.sendMessage("そのようなテストチェンバーは存在しません");
								}
								} else {
									sender.sendMessage("引数が足りません usage : /process insert [process] [chambername] [index]");
								}
							} else if (args[2].equalsIgnoreCase("remove")) {
								for (int i=0; i < p.getProcessSize(); i++) {
									if (p.getTestChamber(i).getFileName().equals(args[3])) {
										p.removeChamber(i);
										sender.sendMessage("削除完了");
										return true;
									}
								}
								sender.sendMessage("そのようなテストチェンバーは存在しません");
							} else if (args[2].equalsIgnoreCase("setreturn")) {
								try {
									p.setDoller(Integer.parseInt(args[3]));
									sender.sendMessage("完了");
								} catch (Exception e) {
									sender.sendMessage("数値を入力してください");
								}


							} else {
								sender.sendMessage("引数が認識できません");
							}

							if (!deleted.isEmpty()) {
								sender.sendMessage("存在しない " + deleted.size() + " 個のテストチェンバーをプロセス内に発見しました。削除します。");
								for (String name : deleted) {
									sender.sendMessage("削除 : " + name);
								}
							}

							DataSystem.SaveTestProcess(p, p.getFilename());

						} else if (args.length > 2) {

							if (args[2].equalsIgnoreCase("list")) {
								sender.sendMessage("プロセス名 : " + p.getProcessName());
								for (int i=0; i < p.getProcessSize(); i++) {
										sender.sendMessage(i + " : " + p.getTestChamber(i).getFileName() + " : (" + p.getTestChamber(i).getChamberName() + ")");
								}
							} else if (args[2].equalsIgnoreCase("info")) {
								sender.sendMessage("プロセス名 : " + p.getProcessName());
								sender.sendMessage("報酬 : " + p.getDoller() + "$");
								sender.sendMessage("出口 : " + p.getExit() != null ? p.getExit().toString() : "指定なし");
							} else if (args[2].equalsIgnoreCase("setloc")) {
								p.setExit(((Player) sender).getLocation());
								sender.sendMessage("現在位置を出口に設定しました");
								sender.sendMessage(p.getExit().toString());
							} else if (args[2].equalsIgnoreCase("resetloc")) {
								p.setExit(null);
								sender.sendMessage("現在位置を指定なしにしました");
							}

						} else {
							sender.sendMessage("名前とコマンド引数とチェンバー名が必要です");
						}
						} catch (FileNotFoundException e) {
							sender.sendMessage("そのファイルは存在しません");
						} catch (ClassNotFoundException e) {
							sender.sendMessage("不明なエラー");
						} catch (IOException e) {
							sender.sendMessage("不明なエラー");
						}


				} else if (args[0].equalsIgnoreCase("create")) {

					if (args.length > 1) {

						for (String name : DataSystem.getTestProcesses()) {
							//System.out.println(name);
							if (name.equals(args[1]+ ".seq")) {
								sender.sendMessage("既に同じ名前のプロセスが存在します");
								return true;
							}
						}

						TestProcess p = new TestProcess();
						sender.sendMessage("作成成功");
						p.setProcessName(args[1]);
						p.setFilename(p.getProcessName());
						DataSystem.SaveTestProcess(p, p.getFilename());

					} else {
						sender.sendMessage("さらに名前引数が必要です");
					}

				} else if (args[0].equalsIgnoreCase("delete")) {

					if (args.length > 1) {

						for (String name : DataSystem.getTestProcesses()) {
							if (name.equals(args[1] + ".seq")) {
								DataSystem.deleteProcess(args[1]);
								sender.sendMessage("プロセスの削除が完了しました");
								return true;
							}
						}
						sender.sendMessage("そのような名前のプロセスは存在しません");

					} else {
						sender.sendMessage("さらに名前引数が必要です");
					}

				} else {
					sender.sendMessage("引数が認識できません");
				}
			} else {
				sender.sendMessage("引数が必要です");
			}

		} else {
			sender.sendMessage("このコマンドはコンソールからは実行できません");
		}

		return true;
	}


}
