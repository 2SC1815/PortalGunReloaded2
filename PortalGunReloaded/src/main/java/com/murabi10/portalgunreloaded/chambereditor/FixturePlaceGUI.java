/*     */ package com.murabi10.portalgunreloaded.chambereditor;
/*     */ 
/*     */ import com.murabi10.portalgunreloaded.BlockData;
/*     */ import com.murabi10.portalgunreloaded.PortalGun;
/*     */ import com.murabi10.portalgunreloaded.gui.Button;
/*     */ import com.murabi10.portalgunreloaded.gui.GUI;
/*     */ import com.murabi10.portalgunreloaded.gui.GUIFunction;
/*     */ import com.murabi10.portalgunreloaded.testingelement.LinkType;
/*     */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*     */ import com.murabi10.portalgunreloaded.testingelement.area.StartPoint;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.ClickType;
/*     */ 
/*     */ public class FixturePlaceGUI
/*     */   extends GUI
/*     */ {
/*     */   public void init()
/*     */   {
/*  22 */     setMenuName("アクションを選んでください");
/*  23 */     setSize(2);
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
/*  37 */     addbutton(new Button(Material.DIAMOND_BLOCK, 1, (short)0, "スタート地点を設定", 0, new GUIFunction()
/*     */     {
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/*  41 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/*  43 */         if (editor != null)
/*     */         {
/*  45 */           StartPoint start = new StartPoint(editor.getOrigin(), editor.getX(p.getLocation()), 
/*  46 */             editor.getY(p.getLocation()), editor.getZ(p.getLocation()));
/*     */           
/*  48 */           editor.AddTestElement(start);
/*     */           
/*  50 */           start.initRunnable();
/*     */         }
/*     */         
/*  53 */         return true; } }, new String[] {
/*     */     
/*     */ 
/*     */ 
/*  57 */       "スタート地点を今立っている場所に", "設定します。", "スタート地点が埋まっているとテストが開始できません" }));
/*     */     
/*  59 */     addbutton(new Button(Material.CONCRETE, 1, (short)7, "白い壁を黒くする", 1, new GUIFunction()
/*     */     {
/*     */ 
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/*  64 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/*  66 */         if ((editor != null) && (editor.isCuboid()))
/*     */         {
/*  68 */           int minx = Math.min(editor.getPos1().getBlockX(), editor.getPos2().getBlockX());
/*  69 */           int miny = Math.min(editor.getPos1().getBlockY(), editor.getPos2().getBlockY());
/*  70 */           int minz = Math.min(editor.getPos1().getBlockZ(), editor.getPos2().getBlockZ());
/*  71 */           int maxx = Math.max(editor.getPos1().getBlockX(), editor.getPos2().getBlockX());
/*  72 */           int maxy = Math.max(editor.getPos1().getBlockY(), editor.getPos2().getBlockY());
/*  73 */           int maxz = Math.max(editor.getPos1().getBlockZ(), editor.getPos2().getBlockZ());
/*     */           
/*  75 */           for (int x = minx; x < maxx + 1; x++) {
/*  76 */             for (int y = miny; y < maxy + 1; y++) {
/*  77 */               for (int z = minz; z < maxz + 1; z++)
/*     */               {
/*  79 */                 Block b = new Location(editor.getOrigin().getWorld(), x, y, z).getBlock();
/*     */                 
/*  81 */                 if (b.getType().equals(Material.CONCRETE)) {
/*  82 */                   b.setType(PortalGun.BLACK_PANEL.getMaterial());
/*  83 */                   b.setData(PortalGun.BLACK_PANEL.GetData());
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*  90 */         return true; } }, new String[] {
/*     */     
/*     */ 
/*     */ 
/*  94 */       "選択範囲の白い壁を黒くします。", "黒い壁にはポータルが打てません。" }));
/*     */     
/*  96 */     addbutton(new Button(Material.CONCRETE, 1, (short)0, "黒い壁を白くする", 2, new GUIFunction()
/*     */     {
/*     */ 
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 101 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 103 */         if ((editor != null) && (editor.isCuboid()))
/*     */         {
/* 105 */           int minx = Math.min(editor.getPos1().getBlockX(), editor.getPos2().getBlockX());
/* 106 */           int miny = Math.min(editor.getPos1().getBlockY(), editor.getPos2().getBlockY());
/* 107 */           int minz = Math.min(editor.getPos1().getBlockZ(), editor.getPos2().getBlockZ());
/* 108 */           int maxx = Math.max(editor.getPos1().getBlockX(), editor.getPos2().getBlockX());
/* 109 */           int maxy = Math.max(editor.getPos1().getBlockY(), editor.getPos2().getBlockY());
/* 110 */           int maxz = Math.max(editor.getPos1().getBlockZ(), editor.getPos2().getBlockZ());
/*     */           
/* 112 */           for (int x = minx; x < maxx + 1; x++) {
/* 113 */             for (int y = miny; y < maxy + 1; y++) {
/* 114 */               for (int z = minz; z < maxz + 1; z++)
/*     */               {
/* 116 */                 Block b = new Location(editor.getOrigin().getWorld(), x, y, z).getBlock();
/*     */                 
/* 118 */                 if (b.getType().equals(Material.CONCRETE)) {
/* 119 */                   b.setType(PortalGun.WHITE_PANEL.getMaterial());
/* 120 */                   b.setData(PortalGun.WHITE_PANEL.GetData());
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 127 */         return true; } }, new String[] {
/*     */     
/*     */ 
/*     */ 
/* 131 */       "選択範囲の黒い壁を白くします。", "白い壁にはポータルが打てます。" }));
/*     */     
/* 133 */     addbutton(new Button(Material.BARRIER, 1, (short)0, "選択範囲を解除", 3, new GUIFunction()
/*     */     {
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 137 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 139 */         if (editor != null) {
/* 140 */           editor.clearPos();
/*     */         }
/* 142 */         return true; } }, new String[] {
/*     */     
/*     */ 
/*     */ 
/* 146 */       "選択範囲を解除します。" }));
/*     */     
/* 148 */     addbutton(new Button(Material.REDSTONE_TORCH_ON, 1, (short)0, "テスト装置を設置する", 4, new GUIFunction()
/*     */     {
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 152 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 154 */         if (editor != null) {
/* 155 */           p.closeInventory();
/* 156 */           PortalGun.gimmicks.openGUI(p);
/*     */         }
/* 158 */         return false; } }, new String[] {
/*     */     
/*     */ 
/*     */ 
/* 162 */       "レーザーや物質消去グリッドなどの", "テスト用ギミックを設置します。" }));
/*     */     
/* 164 */     addbutton(new Button(Material.BARRIER, 1, (short)0, "選択範囲内クリア", 5, new GUIFunction()
/*     */     {
/*     */ 
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 169 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 171 */         if ((editor != null) && (editor.isCuboid()))
/*     */         {
/* 173 */           int minx = Math.min(editor.getPos1().getBlockX(), editor.getPos2().getBlockX());
/* 174 */           int miny = Math.min(editor.getPos1().getBlockY(), editor.getPos2().getBlockY());
/* 175 */           int minz = Math.min(editor.getPos1().getBlockZ(), editor.getPos2().getBlockZ());
/* 176 */           int maxx = Math.max(editor.getPos1().getBlockX(), editor.getPos2().getBlockX());
/* 177 */           int maxy = Math.max(editor.getPos1().getBlockY(), editor.getPos2().getBlockY());
/* 178 */           int maxz = Math.max(editor.getPos1().getBlockZ(), editor.getPos2().getBlockZ());
/*     */           
/* 180 */           for (int x = minx; x < maxx + 1; x++) {
/* 181 */             for (int y = miny; y < maxy + 1; y++) {
/* 182 */               for (int z = minz; z < maxz + 1; z++)
/*     */               {
/* 184 */                 Block b = new Location(editor.getOrigin().getWorld(), x, y, z).getBlock();
/*     */                 
/* 186 */                 if (editor.getElement(b.getLocation()) != null) {
/* 187 */                   editor.RemoveElement(b.getLocation());
/*     */                 }
/* 189 */                 if (((!b.isLiquid()) && (!b.isEmpty())) || (b.getType().equals(Material.CONCRETE))) {
/* 190 */                   b.setType(PortalGun.BLACK_PANEL.getMaterial());
/* 191 */                   b.setData(PortalGun.BLACK_PANEL.GetData());
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 198 */         return true; } }, new String[] {
/*     */     
/*     */ 
/*     */ 
/* 202 */       "選択範囲内の白/黒壁以外の設置物", "（テスト装置や光源やその他ブロック）を除去し、", "代わりに黒い壁を設置します。" }));
/*     */     
/* 204 */     addbutton(new Button(Material.STONE, 1, (short)0, "セーブ", 6, new GUIFunction()
/*     */     {
/*     */ 
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 209 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 211 */         if (editor != null) {
/* 212 */           editor.SaveToFile();
/*     */         }
/* 214 */         return true; } }, new String[] {
/*     */     
/*     */ 
/*     */ 
/* 218 */       "テストチェンバーを保存します。" }));
/*     */     
/* 220 */     addbutton(new Button(Material.STONE, 1, (short)0, "セーブして終了", 7, new GUIFunction()
/*     */     {
/*     */ 
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 225 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 227 */         if (editor != null) {
/* 228 */           editor.Exit();
/*     */         }
/* 230 */         return false; } }, new String[] {
/*     */     
/*     */ 
/*     */ 
/* 234 */       "テストチェンバーを保存してから", "終了します。" }));
/*     */     
/* 236 */     addbutton(new Button(Material.IRON_FENCE, 1, (short)0, "リンク", 9, new GUIFunction()
/*     */     {
/*     */ 
/*     */       public boolean click(Player p, ClickType type)
/*     */       {
/* 241 */         TestChamberEditor editor = TestChamberEditor.getEditor(p);
/*     */         
/* 243 */         if (editor != null)
/*     */         {
/* 245 */           if ((!editor.Pos1Empt()) && (!editor.Pos2Empt()))
/*     */           {
/* 247 */             TestingElement e1 = editor.getElement(editor.getPos1());
/* 248 */             TestingElement e2 = editor.getElement(editor.getPos2());
/*     */             
/* 250 */             if ((e1 != null) && (e2 != null))
/*     */             {
/* 252 */               if (e1.equals(e2)) {
/* 253 */                 p.sendMessage("入出力が同じ装置です");
/* 254 */                 return false;
/*     */               }
/*     */               
/* 257 */               if (e1.getLinkType().equals(LinkType.DNC)) {
/* 258 */                 p.sendMessage("右クリック選択した装置は入出力不可能です");
/* 259 */                 return false;
/*     */               }
/*     */               
/* 262 */               if (e2.getLinkType().equals(LinkType.DNC)) {
/* 263 */                 p.sendMessage("左クリック選択した装置は入出力不可能です");
/* 264 */                 return false;
/*     */               }
/*     */               
/* 267 */               if ((e1.getLinkType().equals(LinkType.OUTPUT)) && (e2.getLinkType().equals(LinkType.OUTPUT))) {
/* 268 */                 p.sendMessage("出力装置同士はリンクできません");
/* 269 */                 return false; }
/* 270 */               if ((e1.getLinkType().equals(LinkType.INPUT)) && 
/* 271 */                 (e2.getLinkType().equals(LinkType.INPUT))) {
/* 272 */                 p.sendMessage("入力装置同士はリンクできません");
/* 273 */                 return false;
/*     */               }
/*     */               
/* 276 */               if (((e1.getLinkType().equals(LinkType.OUT_IN)) && (e2.getLinkType().equals(LinkType.OUT_IN))) || (
/* 277 */                 ((!e1.getLinkType().equals(LinkType.OUTPUT)) && 
/* 278 */                 (!e1.getLinkType().equals(LinkType.OUT_IN))) || (
/* 279 */                 (e2.getLinkType().equals(LinkType.INPUT)) || (
/* 280 */                 ((e2.getLinkType().equals(LinkType.INPUT)) || 
/* 281 */                 (e2.getLinkType().equals(LinkType.OUT_IN))) && 
/* 282 */                 (e1.getLinkType().equals(LinkType.OUTPUT)))))) {
/* 283 */                 if ((e1.getLinkType().equals(LinkType.OUT_IN)) && (e2.getLinkType().equals(LinkType.OUT_IN))) {
/* 284 */                   p.sendMessage("入出力関係が自動検出できなかったので右クリック選択した装置を出力としました");
/*     */                 }
/* 286 */                 editor.Link(e1, e2);
/* 287 */                 p.sendMessage("リンクに成功しました:右クリック選択がスイッチ");
/* 288 */                 return false;
/*     */               }
/* 290 */               if (((!e1.getLinkType().equals(LinkType.INPUT)) && 
/* 291 */                 (!e1.getLinkType().equals(LinkType.OUT_IN))) || (
/* 292 */                 (e2.getLinkType().equals(LinkType.OUTPUT)) || (
/* 293 */                 ((e2.getLinkType().equals(LinkType.OUTPUT)) || 
/* 294 */                 (e2.getLinkType().equals(LinkType.OUT_IN))) && 
/* 295 */                 (e1.getLinkType().equals(LinkType.INPUT)))))
/*     */               {
/* 297 */                 editor.Link(e2, e1);
/* 298 */                 p.sendMessage("リンクに成功しました:左クリック選択がスイッチ");
/* 299 */                 return false;
/*     */               }
/*     */               
/* 302 */               p.sendMessage("何らかのエラーでリンクに失敗しました");
/*     */             }
/*     */             else
/*     */             {
/* 306 */               p.sendMessage("テスト装置が選択されていません");
/*     */             }
/*     */           }
/*     */           else {
/* 310 */             p.sendMessage("選択範囲を確定してください");
/*     */           }
/*     */         }
/*     */         
/* 314 */         return false; } }, new String[] {
/*     */     
/*     */ 
/*     */ 
/* 318 */       "右/左クリックで選択したテスト装置同士を", "リンクさせます。", "入出力関係が自動検出できなかった場合", "右クリック選択した装置を出力として判断します" }));
/*     */   }
/*     */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\chambereditor\FixturePlaceGUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */