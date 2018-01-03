/*     */ package com.murabi10.portalgunreloaded.chambereditor;
/*     */ 
/*     */ import com.murabi10.portalgunreloaded.Methods;
/*     */ import com.murabi10.portalgunreloaded.gui.Button;
/*     */ import com.murabi10.portalgunreloaded.gui.GUI;
/*     */ import com.murabi10.portalgunreloaded.gui.GUIFunction;
/*     */ import com.murabi10.portalgunreloaded.portalgun.Portal;
/*     */ import com.murabi10.portalgunreloaded.portalgun.PortalColor;
/*     */ import com.murabi10.portalgunreloaded.testingelement.area.AreaSwitch;
/*     */ import com.murabi10.portalgunreloaded.testingelement.area.GoalPoint;
/*     */ import com.murabi10.portalgunreloaded.testingelement.dropper.CubeDropper;
/*     */ import com.murabi10.portalgunreloaded.testingelement.field.Fizzler;
/*     */ import com.murabi10.portalgunreloaded.testingelement.field.LaserField;
/*     */ import com.murabi10.portalgunreloaded.testingelement.fixture.AerialFaithPlate;
/*     */ import com.murabi10.portalgunreloaded.testingelement.fixture.Door;
/*     */ import com.murabi10.portalgunreloaded.testingelement.fixture.Indicator;
/*     */ import com.murabi10.portalgunreloaded.testingelement.fixture.ItemFrameElement;
/*     */ import com.murabi10.portalgunreloaded.testingelement.fixture.PortalSpawner;
/*     */ import com.murabi10.portalgunreloaded.testingelement.fixture.Speaker;
/*     */ import com.murabi10.portalgunreloaded.testingelement.fixture.SuperCollidingSuperButton;
/*     */ import com.murabi10.portalgunreloaded.testingelement.logicgate.NOT;
/*     */ import com.murabi10.portalgunreloaded.testingelement.logicgate.OR;
/*     */ import com.murabi10.portalgunreloaded.testingelement.logicgate.RSInputInterface;
/*     */ import com.murabi10.portalgunreloaded.testingelement.logicgate.RSOutputInterface;
/*     */ import com.murabi10.portalgunreloaded.testingelement.logicgate.Timer;
/*     */ import com.murabi10.portalgunreloaded.testingelement.objects.CubeType;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.ClickType;
/*     */ 
/*     */ public class ElementPlaceGUI extends GUI
/*     */ {
/*     */   public void init()
/*     */   {
/*  38 */     setMenuName("設置するテスト装置を選んでください");
/*  39 */     setSize(3);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  69 */     addbutton(new Button(Material.GOLD_BLOCK, 1, (short)0, "ゴール地点", 0, new GUIFunction()
/*     */     {
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/*  73 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/*  75 */         if (editor != null) {
/*  76 */           if ((!editor.Pos1Empt()) && (!editor.Pos2Empt()))
/*     */           {
/*  78 */             if (editor.getPos1().distance(editor.getPos2()) < 16.0D)
/*     */             {
/*  80 */               GoalPoint goal = new GoalPoint(editor.getOrigin(), editor.getX(editor.getPos1()), 
/*  81 */                 editor.getY(editor.getPos1()), editor.getZ(editor.getPos1()), 
/*  82 */                 editor.getX(editor.getPos2()), editor.getY(editor.getPos2()), 
/*  83 */                 editor.getZ(editor.getPos2()));
/*     */               
/*  85 */               editor.AddTestElement(goal);
/*  86 */               goal.initRunnable();
/*     */             } else {
/*  88 */               p.sendMessage("範囲が広すぎます");
/*     */             }
/*     */           } else {
/*  91 */             p.sendMessage("範囲を確定してください");
/*     */           }
/*     */         }
/*  94 */         return true; } }, new String[] {
/*     */     
/*     */ 
/*     */ 
/*  98 */       "ゴール地点：", "これに触れると", "チェンバーをクリアしたことになります" }));
/*     */     
/* 100 */     addbutton(new Button(Material.IRON_BLOCK, 1, (short)0, "ドア", 1, new GUIFunction()
/*     */     {
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 104 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 106 */         if (editor != null) {
/* 107 */           if ((!editor.Pos1Empt()) && (!editor.Pos2Empt())) {
/* 108 */             if (editor.getPos1().distance(editor.getPos2()) < 16.0D) {
/* 109 */               if ((editor.getPos1().getBlockX() == editor.getPos2().getBlockX()) || 
/* 110 */                 (editor.getPos1().getBlockY() == editor.getPos2().getBlockY()) || 
/* 111 */                 (editor.getPos1().getBlockZ() == editor.getPos2().getBlockZ()))
/*     */               {
/* 113 */                 Door door = new Door(editor.getOrigin(), editor.getX(editor.getPos1()), 
/* 114 */                   editor.getY(editor.getPos1()), editor.getZ(editor.getPos1()), 
/* 115 */                   editor.getX(editor.getPos2()), editor.getY(editor.getPos2()), 
/* 116 */                   editor.getZ(editor.getPos2()));
/*     */                 
/* 118 */                 editor.AddTestElement(door);
/* 119 */                 door.initRunnable();
/*     */               } else {
/* 121 */                 p.sendMessage("立体な配置にすることはできません");
/*     */               }
/*     */             } else {
/* 124 */               p.sendMessage("範囲が広すぎます");
/*     */             }
/*     */           } else {
/* 127 */             p.sendMessage("範囲を確定してください");
/*     */           }
/*     */         }
/* 130 */         return true; } }, new String[] {
/*     */     
/*     */ 
/*     */ 
/* 134 */       "ドア：", "スイッチとリンクして動く。", "リンク先がONだと開く" }));
/*     */     
/* 136 */     addbutton(new Button(Material.BONE_BLOCK, 1, (short)0, "1500MW 強化超衝突スーパーボタン", 2, new GUIFunction()
/*     */     {
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 140 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 142 */         if (editor != null)
/*     */         {
/* 144 */           if (editor.getPointLoc() != null)
/*     */           {
/* 146 */             SuperCollidingSuperButton button = new SuperCollidingSuperButton(editor.getOrigin(), 
/* 147 */               editor.getsel(), editor.getX(editor.getPointLoc()), editor.getY(editor.getPointLoc()), 
/* 148 */               editor.getZ(editor.getPointLoc()));
/*     */             
/* 150 */             editor.AddTestElement(button);
/* 151 */             button.initRunnable();
/*     */           } else {
/* 153 */             p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
/*     */           }
/*     */         }
/* 156 */         return true; } }, new String[] {
/*     */     
/*     */ 
/* 159 */       "1500MW 強化超衝突スーパーボタン:", "スイッチ。キューブやプレイヤーが乗るとONになる" }));
/*     */     
/* 161 */     addbutton(new Button(Material.DISPENSER, 1, (short)0, "荷重格納キューブドロッパー", 3, new GUIFunction()
/*     */     {
/*     */ 
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 166 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 168 */         if (editor != null)
/*     */         {
/* 170 */           if (editor.getPointLoc() != null) {
/* 171 */             if (editor.getsel().equals(BlockFace.DOWN)) {
/* 172 */               if ((editor.getPointLoc().getBlock().getRelative(editor.getsel(), 1).isEmpty()) && 
/* 173 */                 (editor.getPointLoc().getBlock().getRelative(editor.getsel(), 2).isEmpty())) {
/* 174 */                 editor.getPointLoc().getBlock().getRelative(editor.getsel()).setType(Material.DROPPER);
/* 175 */                 editor.getPointLoc().getBlock().getRelative(editor.getsel()).setData((byte)8);
/*     */                 
/* 177 */                 CubeDropper dropper = new CubeDropper(editor.getOrigin(), editor.getsel(), 
/* 178 */                   CubeType.NORMAL, 
/* 179 */                   editor.getX(editor.getPointLoc().getBlock().getRelative(editor.getsel())
/* 180 */                   .getLocation()), 
/* 181 */                   editor.getY(editor.getPointLoc().getBlock().getRelative(editor.getsel())
/* 182 */                   .getLocation()), 
/* 183 */                   editor.getZ(editor.getPointLoc().getBlock().getRelative(editor.getsel())
/* 184 */                   .getLocation()));
/*     */                 
/* 186 */                 editor.AddTestElement(dropper);
/* 187 */                 dropper.initRunnable();
/*     */               } else {
/* 189 */                 p.sendMessage("下方向の空きが足りません");
/*     */               }
/*     */             } else {
/* 192 */               p.sendMessage("この装置は下向きにしか設置できません");
/*     */             }
/*     */           } else {
/* 195 */             p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
/*     */           }
/*     */         }
/* 198 */         return true; } }, new String[] {
/*     */     
/*     */ 
/* 201 */       "ドロッパー:", "キューブを供給する" }));
/*     */     
/* 203 */     addbutton(new Button(Material.ITEM_FRAME, 1, (short)0, "額縁", 4, new GUIFunction()
/*     */     {
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 207 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 209 */         if (editor != null)
/*     */         {
/* 211 */           if (editor.getPointLoc() != null) {
/* 212 */             if ((!editor.getsel().equals(BlockFace.UP)) && (!editor.getsel().equals(BlockFace.DOWN))) {
/* 213 */               if (editor.getPointLoc().getBlock().getType().isSolid()) {
/* 214 */                 ItemFrameElement fr = new ItemFrameElement(editor.getOrigin(), editor.getsel(), 
/* 215 */                   editor.getX(editor.getPointLoc()), editor.getY(editor.getPointLoc()), 
/* 216 */                   editor.getZ(editor.getPointLoc()));
/*     */                 
/* 218 */                 editor.AddTestElement(fr);
/* 219 */                 fr.initRunnable();
/*     */               } else {
/* 221 */                 p.sendMessage("固体ブロックにのみ設置できます");
/*     */               }
/*     */             } else {
/* 224 */               p.sendMessage("額縁は上と下には設置できません");
/*     */             }
/*     */           } else {
/* 227 */             p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
/*     */           }
/*     */         }
/* 230 */         return true; } }, new String[] {
/*     */     
/*     */ 
/* 233 */       "額縁:  普通の額縁は保存されませんが", "これで呼び出された額縁と", "その中身は保持されます", "ただしエンチャントは失われます" }));
/*     */     
/* 235 */     addbutton(new Button(Material.ITEM_FRAME, 2, (short)0, "インジケーター", 5, new GUIFunction()
/*     */     {
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 239 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 241 */         if (editor != null)
/*     */         {
/* 243 */           if (editor.getPointLoc() != null) {
/* 244 */             if ((!editor.getsel().equals(BlockFace.UP)) && (!editor.getsel().equals(BlockFace.DOWN))) {
/* 245 */               if (editor.getPointLoc().getBlock().getType().isSolid()) {
/* 246 */                 Indicator fr = new Indicator(editor.getOrigin(), editor.getsel(), 
/* 247 */                   editor.getX(editor.getPointLoc()), editor.getY(editor.getPointLoc()), 
/* 248 */                   editor.getZ(editor.getPointLoc()));
/*     */                 
/* 250 */                 editor.AddTestElement(fr);
/* 251 */                 fr.initRunnable();
/*     */               } else {
/* 253 */                 p.sendMessage("固体ブロックにのみ設置できます");
/*     */               }
/*     */             } else {
/* 256 */               p.sendMessage("インジケーターは上と下には設置できません");
/*     */             }
/*     */           } else {
/* 259 */             p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
/*     */           }
/*     */         }
/* 262 */         return true; } }, new String[] {
/*     */     
/*     */ 
/* 265 */       "インジケーター: 入力装置。", "スイッチとつなげて", "状態を確認するのに使う", "本体は張り付いてるブロック。" }));
/*     */     
/* 267 */     addbutton(new Button(Material.PISTON_BASE, 1, (short)0, "Aerial Faith Plate", 6, new GUIFunction()
/*     */     {
/*     */ 
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 272 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 274 */         if (editor != null)
/*     */         {
/* 276 */           if (editor.getPointLoc() != null)
/*     */           {
/* 278 */             editor.getPointLoc().getBlock().setType(Material.PISTON_BASE);
/* 279 */             editor.getPointLoc().getBlock().setData((byte)1);
/*     */             
/* 281 */             AerialFaithPlate afp = new AerialFaithPlate(editor.getOrigin(), 
/* 282 */               editor.getX(editor.getPointLoc()), editor.getY(editor.getPointLoc()), 
/* 283 */               editor.getZ(editor.getPointLoc()), 0.0F, -90.0F, 4);
/*     */             
/* 285 */             editor.AddTestElement(afp);
/* 286 */             afp.initRunnable();
/*     */           }
/*     */           else {
/* 289 */             p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
/*     */           }
/*     */         }
/* 292 */         return true; } }, new String[] {
/*     */     
/*     */ 
/* 295 */       "空中信頼性プレート:", "機械式のジャンプ台。", "詳細エディタで設定できる", "空中に射出された被験者の問題解決能力をテストする" }));
/*     */     
/* 297 */     addbutton(new Button(Material.WATCH, 1, (short)0, "タイマー", 7, new GUIFunction()
/*     */     {
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 301 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 303 */         if (editor != null)
/*     */         {
/* 305 */           if (editor.getPointLoc() != null)
/*     */           {
/* 307 */             Timer timer = new Timer(editor.getOrigin(), editor.getX(editor.getPointLoc()), 
/* 308 */               editor.getY(editor.getPointLoc()), editor.getZ(editor.getPointLoc()));
/*     */             
/* 310 */             editor.AddTestElement(timer);
/* 311 */             timer.initRunnable();
/*     */           }
/*     */           else {
/* 314 */             p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
/*     */           }
/*     */         }
/* 317 */         return true; } }, new String[] {
/*     */     
/*     */ 
/* 320 */       "タイマー:", "立ち上がりエッジでONになり、", "指定時間たつとOFFに戻る。", "詳細エディタで時間を指定できる" }));
/*     */     
/* 322 */     addbutton(new Button(Material.DIODE, 1, (short)0, "NOT Gate", 8, new GUIFunction()
/*     */     {
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 326 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 328 */         if (editor != null)
/*     */         {
/* 330 */           if (editor.getPointLoc() != null)
/*     */           {
/* 332 */             NOT not = new NOT(editor.getOrigin(), editor.getX(editor.getPointLoc()), 
/* 333 */               editor.getY(editor.getPointLoc()), editor.getZ(editor.getPointLoc()));
/*     */             
/* 335 */             editor.AddTestElement(not);
/* 336 */             not.initRunnable();
/*     */           }
/*     */           else {
/* 339 */             p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
/*     */           }
/*     */         }
/* 342 */         return true; } }, new String[] {
/*     */     
/*     */ 
/* 345 */       "論理否定:", "入力がONだとOFFを出力し、", "OFFだとONを出力する", "入力が複数ある場合は否定論理積として動作する" }));
/*     */     
/* 347 */     addbutton(new Button(Material.DIODE, 2, (short)0, "OR Gate", 9, new GUIFunction()
/*     */     {
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 351 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 353 */         if (editor != null)
/*     */         {
/* 355 */           if (editor.getPointLoc() != null)
/*     */           {
/* 357 */             OR or = new OR(editor.getOrigin(), editor.getX(editor.getPointLoc()), 
/* 358 */               editor.getY(editor.getPointLoc()), editor.getZ(editor.getPointLoc()));
/*     */             
/* 360 */             editor.AddTestElement(or);
/* 361 */             or.initRunnable();
/*     */           }
/*     */           else {
/* 364 */             p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
/*     */           }
/*     */         }
/* 367 */         return true; } }, new String[] {
/*     */     
/*     */ 
/* 370 */       "論理和:", "入力のいずれかがONだとONになる", "入力がすべてOFFだとOFFになる" }));
/*     */     
/* 372 */     addbutton(new Button(Material.IRON_FENCE, 1, (short)0, "エリアスイッチ", 10, new GUIFunction()
/*     */     {
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 376 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 378 */         if (editor != null)
/*     */         {
/* 380 */           if ((!editor.Pos1Empt()) && (!editor.Pos2Empt())) {
/* 381 */             if (editor.getPos1().distance(editor.getPos2()) < 16.0D)
/*     */             {
/* 383 */               AreaSwitch as = new AreaSwitch(editor.getOrigin(), editor.getX(editor.getPos1()), 
/* 384 */                 editor.getY(editor.getPos1()), editor.getZ(editor.getPos1()), 
/* 385 */                 editor.getX(editor.getPos2()), editor.getY(editor.getPos2()), 
/* 386 */                 editor.getZ(editor.getPos2()));
/*     */               
/* 388 */               editor.AddTestElement(as);
/* 389 */               as.initRunnable();
/*     */             } else {
/* 391 */               p.sendMessage("範囲が広すぎます");
/*     */             }
/*     */           } else {
/* 394 */             p.sendMessage("範囲を確定してください");
/*     */           }
/*     */         }
/* 397 */         return true; } }, new String[] {
/*     */     
/*     */ 
/* 400 */       "エリアスイッチ:", "範囲内にプレイヤーが入ると", "ONになる" }));
/*     */     
/* 402 */     addbutton(new Button(Material.STAINED_GLASS, 1, (short)3, "物質消去グリッド", 11, new GUIFunction()
/*     */     {
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 406 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 408 */         if (editor != null)
/*     */         {
/* 410 */           if ((!editor.Pos1Empt()) && (!editor.Pos2Empt())) {
/* 411 */             if (editor.getPos1().distance(editor.getPos2()) < 16.0D)
/*     */             {
/* 413 */               if ((editor.getPos1().getBlockX() == editor.getPos2().getBlockX()) || 
/* 414 */                 (editor.getPos1().getBlockY() == editor.getPos2().getBlockY()) || 
/* 415 */                 (editor.getPos1().getBlockZ() == editor.getPos2().getBlockZ()))
/*     */               {
/* 417 */                 Fizzler fizzler = new Fizzler(editor.getOrigin(), editor.getX(editor.getPos1()), 
/* 418 */                   editor.getY(editor.getPos1()), editor.getZ(editor.getPos1()), 
/* 419 */                   editor.getX(editor.getPos2()), editor.getY(editor.getPos2()), 
/* 420 */                   editor.getZ(editor.getPos2()));
/*     */                 
/* 422 */                 editor.AddTestElement(fizzler);
/* 423 */                 fizzler.initRunnable();
/*     */               } else {
/* 425 */                 p.sendMessage("立体な配置にすることはできません");
/*     */               }
/*     */             } else {
/* 428 */               p.sendMessage("範囲が広すぎます");
/*     */             }
/*     */           } else {
/* 431 */             p.sendMessage("範囲を確定してください");
/*     */           }
/*     */         }
/* 434 */         return true; } }, new String[] {
/*     */     
/*     */ 
/* 437 */       "物質消去グリッド:", "通過する未認可機器を蒸発させる", "スイッチとつなげられる" }));
/*     */     
/* 439 */     addbutton(new Button(Material.STAINED_GLASS, 1, (short)14, "レーザーフィールド", 12, new GUIFunction()
/*     */     {
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 443 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 445 */         if (editor != null)
/*     */         {
/* 447 */           if ((!editor.Pos1Empt()) && (!editor.Pos2Empt())) {
/* 448 */             if (editor.getPos1().distance(editor.getPos2()) < 16.0D)
/*     */             {
/* 450 */               if ((editor.getPos1().getBlockX() == editor.getPos2().getBlockX()) || 
/* 451 */                 (editor.getPos1().getBlockY() == editor.getPos2().getBlockY()) || 
/* 452 */                 (editor.getPos1().getBlockZ() == editor.getPos2().getBlockZ()))
/*     */               {
/* 454 */                 LaserField lf = new LaserField(editor.getOrigin(), editor.getX(editor.getPos1()), 
/* 455 */                   editor.getY(editor.getPos1()), editor.getZ(editor.getPos1()), 
/* 456 */                   editor.getX(editor.getPos2()), editor.getY(editor.getPos2()), 
/* 457 */                   editor.getZ(editor.getPos2()));
/*     */                 
/* 459 */                 editor.AddTestElement(lf);
/* 460 */                 lf.initRunnable();
/*     */               } else {
/* 462 */                 p.sendMessage("立体な配置にすることはできません");
/*     */               }
/*     */             } else {
/* 465 */               p.sendMessage("範囲が広すぎます");
/*     */             }
/*     */           } else {
/* 468 */             p.sendMessage("範囲を確定してください");
/*     */           }
/*     */         }
/* 471 */         return true; } }, new String[] {
/*     */     
/*     */ 
/* 474 */       "レーザーフィールド:", "通過する生物を絶命させてしまう", "スイッチとつなげられる" }));
/*     */     
/* 476 */     addbutton(new Button(Material.NOTE_BLOCK, 1, (short)0, "スピーカー", 13, new GUIFunction()
/*     */     {
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 480 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 482 */         if (editor != null)
/*     */         {
/* 484 */           if (editor.getPointLoc() != null)
/*     */           {
/* 486 */             Speaker spk = new Speaker(editor.getOrigin(), editor.getX(editor.getPointLoc()), 
/* 487 */               editor.getY(editor.getPointLoc()), editor.getZ(editor.getPointLoc()));
/*     */             
/* 489 */             editor.AddTestElement(spk);
/* 490 */             spk.initRunnable();
/*     */           }
/*     */           else {
/* 493 */             p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
/*     */           }
/*     */         }
/* 496 */         return true; } }, new String[] {
/*     */     
/*     */ 
/* 499 */       "スピーカー:", "メッセージを録音しておける", "詳細エディタで設定できる", "立ち上がりで一回起動（エディターでは無制限）" }));
/*     */     
/* 501 */     addbutton(new Button(Material.BANNER, 1, (short)4, "ポータルスポナー（青）", 14, new GUIFunction()
/*     */     {
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 505 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 507 */         if (editor != null)
/*     */         {
/* 509 */           if (editor.getPointLoc() != null) {
/* 510 */             Portal portal = new Portal(p, PortalColor.BLUE, editor.getsel(), 
/* 511 */               Methods.YawToBlockFace(p.getEyeLocation().getYaw()), 
/* 512 */               editor.getPointLoc().getBlock().getRelative(editor.getsel()).getLocation(), new Location[0]);
/* 513 */             if (Methods.isSuitable(portal, false))
/*     */             {
/* 515 */               PortalSpawner spawner = new PortalSpawner(editor.getOrigin(), editor.getsel(), 
/* 516 */                 Methods.YawToBlockFace(p.getEyeLocation().getYaw()), 
/* 517 */                 editor.getX(editor.getPointLoc()), editor.getY(editor.getPointLoc()), 
/* 518 */                 editor.getZ(editor.getPointLoc()), PortalColor.BLUE);
/*     */               
/* 520 */               editor.AddTestElement(spawner);
/* 521 */               spawner.initRunnable();
/*     */             } else {
/* 523 */               p.sendMessage("その位置にはポータルが設置できません");
/*     */             }
/*     */             
/* 526 */             portal.stopParticle();
/* 527 */             portal = null;
/*     */           }
/*     */           else {
/* 530 */             p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
/*     */           }
/*     */         }
/* 533 */         return true; } }, new String[] {
/*     */     
/*     */ 
/* 536 */       "ポータルスポナー:", "ポータルを発生させる", "スイッチをつなげると", "立ち上がりで発生する" }));
/*     */     
/* 538 */     addbutton(new Button(Material.BANNER, 1, (short)14, "ポータルスポナー（橙）", 15, new GUIFunction()
/*     */     {
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 542 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 544 */         if (editor != null)
/*     */         {
/* 546 */           if (editor.getPointLoc() != null) {
/* 547 */             Portal portal = new Portal(p, PortalColor.ORANGE, editor.getsel(), 
/* 548 */               Methods.YawToBlockFace(p.getEyeLocation().getYaw()), 
/* 549 */               editor.getPointLoc().getBlock().getRelative(editor.getsel()).getLocation(), new Location[0]);
/* 550 */             if (Methods.isSuitable(portal, false))
/*     */             {
/* 552 */               PortalSpawner spawner = new PortalSpawner(editor.getOrigin(), editor.getsel(), 
/* 553 */                 Methods.YawToBlockFace(p.getEyeLocation().getYaw()), 
/* 554 */                 editor.getX(editor.getPointLoc()), editor.getY(editor.getPointLoc()), 
/* 555 */                 editor.getZ(editor.getPointLoc()), PortalColor.ORANGE);
/*     */               
/* 557 */               editor.AddTestElement(spawner);
/* 558 */               spawner.initRunnable();
/*     */             } else {
/* 560 */               p.sendMessage("その位置にはポータルが設置できません");
/*     */             }
/*     */             
/* 563 */             portal.stopParticle();
/* 564 */             portal = null;
/*     */           }
/*     */           else {
/* 567 */             p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
/*     */           }
/*     */         }
/* 570 */         return true; } }, new String[] {
/*     */     
/*     */ 
/* 573 */       "ポータルスポナー:", "ポータルを発生させる", "スイッチをつなげると", "立ち上がりで発生する" }));
/*     */     
/* 575 */     addbutton(new Button(Material.REDSTONE_COMPARATOR, 1, (short)0, "RSインプットインターフェース", 16, new GUIFunction()
/*     */     {
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 579 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 581 */         if (editor != null)
/*     */         {
/* 583 */           if (editor.getPointLoc() != null)
/*     */           {
/* 585 */             RSInputInterface rsif = new RSInputInterface(editor.getOrigin(), editor.getX(editor.getPointLoc()), 
/* 586 */               editor.getY(editor.getPointLoc()), editor.getZ(editor.getPointLoc()));
/*     */             
/* 588 */             editor.AddTestElement(rsif);
/* 589 */             rsif.initRunnable();
/*     */           }
/*     */           else {
/* 592 */             p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
/*     */           }
/*     */         }
/* 595 */         return true; } }, new String[] {
/*     */     
/*     */ 
/* 598 */       "RSインプットインターフェース:", "レッドストーン入力するとONになるスイッチ" }));
/*     */     
/* 600 */     addbutton(new Button(Material.REDSTONE_TORCH_ON, 1, (short)0, "RSアウトプットインターフェース", 17, new GUIFunction()
/*     */     {
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 604 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 606 */         if (editor != null)
/*     */         {
/* 608 */           if (editor.getPointLoc() != null)
/*     */           {
/* 610 */             RSOutputInterface rsif = new RSOutputInterface(editor.getOrigin(), editor.getX(editor.getPointLoc()), 
/* 611 */               editor.getY(editor.getPointLoc()), editor.getZ(editor.getPointLoc()));
/*     */             
/* 613 */             editor.AddTestElement(rsif);
/* 614 */             rsif.initRunnable();
/*     */           }
/*     */           else {
/* 617 */             p.sendMessage("選択範囲を確定するか選択範囲を一点にしてください。");
/*     */           }
/*     */         }
/* 620 */         return true; } }, new String[] {
/*     */     
/*     */ 
/* 623 */       "RSアウトプットインターフェース:", "ONだとRSブロックになり、OFFだと鉄ブロックになる" }));
/*     */   }
/*     */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\chambereditor\ElementPlaceGUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */