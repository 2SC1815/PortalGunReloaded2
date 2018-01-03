/*     */ package com.murabi10.portalgunreloaded.devices;
/*     */ 
/*     */ import com.murabi10.portalgunreloaded.PortalGun;
/*     */ import com.murabi10.portalgunreloaded.chambereditor.EditorFunction;
/*     */ import com.murabi10.portalgunreloaded.chambereditor.FixturePlaceGUI;
/*     */ import com.murabi10.portalgunreloaded.chambereditor.TestChamberEditor;
/*     */ import com.murabi10.portalgunreloaded.portalgun.PortalColor;
/*     */ import com.murabi10.portalgunreloaded.portalgun.PortalDevice;
/*     */ import com.murabi10.portalgunreloaded.selector.StringInputWait;
/*     */ import com.murabi10.portalgunreloaded.testingelement.ElementType;
/*     */ import com.murabi10.portalgunreloaded.testingelement.LinkType;
/*     */ import com.murabi10.portalgunreloaded.testingelement.TestingElement;
/*     */ import com.murabi10.portalgunreloaded.testingelement.fixture.AerialFaithPlate;
/*     */ import com.murabi10.portalgunreloaded.testingelement.fixture.Speaker;
/*     */ import com.murabi10.portalgunreloaded.testingelement.logicgate.Timer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public class DeviceManager implements org.bukkit.event.Listener
/*     */ {
/*  30 */   public static ArrayList<Device> devices = new ArrayList();
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
/*     */   public static void Enable()
/*     */   {
/*  49 */     new Device(new Function()
/*     */     {
/*     */       public void func(Player player, Action action)
/*     */       {
/*  39 */         if ((action.equals(Action.LEFT_CLICK_AIR)) || (action.equals(Action.LEFT_CLICK_BLOCK))) {
/*  40 */           PortalDevice.getDeviceInstance(player).LaunchPortal(PortalColor.BLUE);
/*     */         }
/*     */         
/*  43 */         if ((action.equals(Action.RIGHT_CLICK_AIR)) || (action.equals(Action.RIGHT_CLICK_BLOCK))) {
/*  44 */           PortalDevice.getDeviceInstance(player).LaunchPortal(PortalColor.ORANGE);
/*     */         }
/*     */         
/*     */       }
/*  48 */     }, Material.WOOD_SWORD, (short)0, "PORTALGUN", "デュアルポータルガン", new String[] { "Aperture Handheld Portal Device", "右クリックでオレンジ", 
/*  49 */       "左クリックでブルーの", "ポータルが打てる" }).Register();
/*     */     
/*  51 */     new Device(new Function()
/*     */     {
/*     */       public void func(Player player, Action a)
/*     */       {
/*  55 */         PortalDevice.getDeviceInstance(player).LaunchPortal(PortalColor.BLUE);
/*     */       }
/*  57 */     }, Material.WOOD_SWORD, (short)0, "BROKEN_PORTALGUN", "シングルポータルガン", new String[] { "Aperture Handheld Portal Device", 
/*  58 */       "クリックでブルーの", "ポータルが打てる" }).Register();
/*     */     
/*  60 */     new Device(new Function()
/*     */     {
/*     */       public void func(Player player, Action action)
/*     */       {
/*  64 */         TestChamberEditor editor = TestChamberEditor.getEditor(player);
/*     */         
/*  66 */         if (editor != null)
/*     */         {
/*  68 */           if (action.equals(Action.LEFT_CLICK_BLOCK)) {
/*  69 */             editor.LClick();
/*     */           }
/*     */           
/*  72 */           if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
/*  73 */             editor.RClick();
/*     */           }
/*     */           
/*     */         }
/*     */       }
/*  78 */     }, Material.WOOD_AXE, (short)0, "SELECTOR", "セレクタ", new String[] { "右/左クリックで", "範囲選択ができる" }).Register();
/*     */     
/*  80 */     new Device(new Function()
/*     */     {
/*     */       public void func(Player player, Action action)
/*     */       {
/*  84 */         TestChamberEditor editor = TestChamberEditor.getEditor(player);
/*     */         
/*  86 */         if (editor != null)
/*     */         {
/*  88 */           if ((action.equals(Action.RIGHT_CLICK_BLOCK)) || (action.equals(Action.RIGHT_CLICK_AIR))) {
/*  89 */             PortalGun.fixture.openGUI(player);
/*     */           }
/*     */           
/*     */         }
/*     */       }
/*  94 */     }, Material.WOOD_HOE, (short)0, "PLACER", "エディターメニュー", new String[] { "右クリックでメニューを開く。", "セーブしたり", "選択範囲内の編集を行ったり", 
/*  95 */       "テスト装置を設置したりできる" }).Register();
/*     */     
/*  97 */     new Device(new Function()
/*     */     {
/*     */       public void func(Player player, Action action)
/*     */       {
/* 101 */         TestChamberEditor editor = TestChamberEditor.getEditor(player);
/*     */         
/* 103 */         if (editor != null)
/*     */         {
/* 105 */           final Location loc = player.getTargetBlock(null, 20).getLocation();
/*     */           
/* 107 */           TestingElement target = editor.getElement(loc);
/*     */           
/* 109 */           if (target != null) {
/* 110 */             if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
/* 111 */               player.sendMessage("データタイプ: " + target.getType().toString());
/*     */               
/* 113 */               if (!target.getLinkType().equals(LinkType.DNC)) {
/* 114 */                 player.sendMessage("入出力タイプ: " + target.getLinkType().toString());
/* 115 */                 player.sendMessage("この装置の入力につながっている装置：");
/* 116 */                 for (TestingElement sws : target.Switches()) {
/* 117 */                   player.sendMessage("座標: " + sws.getRelative1(editor.getOrigin()).toString());
/* 118 */                   player.sendMessage("タイプ: " + sws.getType().toString());
/*     */                 }
/* 120 */                 player.sendMessage("この装置の出力につながっている装置：");
/* 121 */                 Iterator localIterator2; for (??? = editor.elements().iterator(); ???.hasNext(); 
/* 122 */                     localIterator2.hasNext())
/*     */                 {
/* 121 */                   TestingElement in = (TestingElement)???.next();
/* 122 */                   localIterator2 = in.Switches().iterator(); continue;TestingElement sws = (TestingElement)localIterator2.next();
/* 123 */                   if (sws.equals(target)) {
/* 124 */                     player.sendMessage("座標: " + in.getRelative1(editor.getOrigin()).toString());
/* 125 */                     player.sendMessage("タイプ: " + in.getType().toString());
/*     */                   }
/*     */                 }
/*     */               }
/*     */               else {
/* 130 */                 player.sendMessage("(入出力関係を持つことができない装置です)");
/*     */               }
/*     */               
/* 133 */               switch (target.getType()) {
/*     */               case GEL_DROPPER: 
/* 135 */                 AerialFaithPlate afp = (AerialFaithPlate)target;
/* 136 */                 player.sendMessage("発射方向(Pitch): " + afp.getPitch());
/* 137 */                 player.sendMessage("発射方向(Yaw):   " + afp.getYaw());
/* 138 */                 player.sendMessage("発射力(Power):   " + afp.getPower());
/* 139 */                 break;
/*     */               case OR: 
/* 141 */                 Speaker speaker = (Speaker)target;
/* 142 */                 player.sendMessage("設定されたメッセージ: " + speaker.getString());
/* 143 */                 break;
/*     */               case TIMER: 
/* 145 */                 Timer timer = (Timer)target;
/* 146 */                 player.sendMessage("設定秒数: " + timer.getDelaySec());
/* 147 */                 break;
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
/*     */               }
/*     */               
/*     */             }
/* 171 */             else if (action.equals(Action.LEFT_CLICK_BLOCK))
/*     */             {
/* 173 */               final Player p = player;
/*     */               
/* 175 */               switch (target.getType()) {
/*     */               case GEL_DROPPER: 
/* 177 */                 player.sendMessage("Pitch(90~-90), Yaw(360~0), Powerの順にチャット入力してください >");
/*     */                 try
/*     */                 {
/* 180 */                   new StringInputWait(player, new EditorFunction() {
/* 181 */                     int phase = 0;
/*     */                     
/*     */                     public boolean reveive(String str)
/*     */                     {
/* 185 */                       if (TestChamberEditor.getEditor(p) == null) {
/* 186 */                         return false;
/*     */                       }
/*     */                       try {
/* 189 */                         float input = Float.parseFloat(str);
/*     */                         
/* 191 */                         AerialFaithPlate afp = (AerialFaithPlate)TestChamberEditor.getEditor(p).getElement(loc);
/*     */                         
/* 193 */                         if (this.phase == 0)
/*     */                         {
/* 195 */                           if ((input < 90.0F) && (input > -90.0F))
/*     */                           {
/* 197 */                             afp.setPitch(input);
/* 198 */                             this.phase += 1;
/*     */                           } else {
/* 200 */                             p.sendMessage("範囲を超えています。Pitchは90~-90の範囲にしてください");
/*     */                           }
/* 202 */                           return true;
/*     */                         }
/* 204 */                         if (this.phase == 1)
/*     */                         {
/* 206 */                           if ((input < 360.0F) && (input > 0.0F))
/*     */                           {
/* 208 */                             afp.setYaw(input);
/* 209 */                             this.phase += 1;
/*     */                           }
/*     */                           else {
/* 212 */                             p.sendMessage("範囲を超えています。Yawは360~0の範囲にしてください");
/*     */                           }
/* 214 */                           return true;
/*     */                         }
/*     */                         
/* 217 */                         if ((input < 100.0F) && (input > 0.0F)) {
/* 218 */                           afp.setPower(input);
/* 219 */                           p.sendMessage("完了しました");
/* 220 */                           return false;
/*     */                         }
/* 222 */                         p.sendMessage("範囲を超えています。Powerは100~0の範囲にしてください");
/* 223 */                         return true;
/*     */ 
/*     */                       }
/*     */                       catch (NumberFormatException e)
/*     */                       {
/* 228 */                         p.sendMessage("数を入力してください: " + e.getMessage()); }
/* 229 */                       return true;
/*     */                     }
/*     */                   });
/*     */                 }
/*     */                 catch (Exception e)
/*     */                 {
/* 235 */                   e.printStackTrace();
/*     */                 }
/*     */               
/*     */ 
/*     */               case OR: 
/* 240 */                 player.sendMessage("新しいメッセージをチャット入力してください >");
/*     */                 try
/*     */                 {
/* 243 */                   new StringInputWait(player, new EditorFunction()
/*     */                   {
/*     */                     public boolean reveive(String str)
/*     */                     {
/* 247 */                       if (TestChamberEditor.getEditor(p) == null) {
/* 248 */                         return false;
/*     */                       }
/* 250 */                       Speaker speaker = (Speaker)TestChamberEditor.getEditor(p).getElement(loc);
/* 251 */                       speaker.setString(str);
/* 252 */                       p.sendMessage("完了しました");
/* 253 */                       return false;
/*     */                     }
/*     */                   });
/*     */                 }
/*     */                 catch (Exception e) {
/* 258 */                   e.printStackTrace();
/*     */                 }
/*     */               
/*     */ 
/*     */               case TIMER: 
/* 263 */                 player.sendMessage("設定秒数をチャット入力してください");
/*     */                 try
/*     */                 {
/* 266 */                   new StringInputWait(player, new EditorFunction()
/*     */                   {
/*     */                     public boolean reveive(String str)
/*     */                     {
/* 270 */                       if (TestChamberEditor.getEditor(p) == null) {
/* 271 */                         return false;
/*     */                       }
/* 273 */                       Timer timer = (Timer)TestChamberEditor.getEditor(p).getElement(loc);
/*     */                       try {
/* 275 */                         int delay = Integer.parseInt(str);
/* 276 */                         if (delay <= 60) {
/* 277 */                           timer.setDelaySec(delay);
/* 278 */                           p.sendMessage("完了しました");
/* 279 */                           return false;
/*     */                         }
/* 281 */                         p.sendMessage("大きすぎます: 60以下にしてください");
/* 282 */                         return true;
/*     */                       }
/*     */                       catch (NumberFormatException e) {
/* 285 */                         p.sendMessage("整数で入力してください: " + e.getMessage()); }
/* 286 */                       return true;
/*     */                     }
/*     */                   });
/*     */                 }
/*     */                 catch (Exception e)
/*     */                 {
/* 292 */                   e.printStackTrace();
/*     */                 }
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
/*     */               }
/*     */               
/*     */             }
/*     */           }
/*     */           else {
/* 321 */             player.sendMessage("ターゲットが存在しません");
/*     */           }
/*     */           
/*     */         }
/*     */       }
/* 326 */     }, Material.WOOD_HOE, (short)0, "INFO", "詳細エディタ", new String[] { "右でタイプ表示、左で詳細編集。", "ロジックゲートの種類を調べたり", "空中信頼性プレートやタイマーの", 
/* 327 */       "詳細設定ができる" }).Register();
/*     */   }
/*     */   
/*     */   public static void add(Device d)
/*     */   {
/* 332 */     if (!devices.contains(d)) {
/* 333 */       devices.add(d);
/*     */     }
/*     */   }
/*     */   
/*     */   public static Device getDevice(String str) {
/* 338 */     for (Device d : devices) {
/* 339 */       if (d.getName().equals(str)) {
/* 340 */         return d;
/*     */       }
/*     */     }
/* 343 */     return null;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onInteract(PlayerInteractEvent e) {
/* 348 */     ItemStack item = e.getItem();
/* 349 */     if ((item != null) && (item.hasItemMeta())) {
/* 350 */       ItemMeta meta = item.getItemMeta();
/* 351 */       if (meta.hasLore()) {
/* 352 */         List<String> lore = meta.getLore();
/* 353 */         Material material = item.getType();
/* 354 */         short data = item.getDurability();
/* 355 */         for (Device device : devices) {
/* 356 */           ItemStack d = device.getItem();
/* 357 */           if ((d.getType().equals(material)) && (d.getDurability() == data) && 
/* 358 */             (d.getItemMeta().getLore().equals(lore))) {
/* 359 */             device.runFunction(e.getPlayer(), e.getAction());
/* 360 */             e.setCancelled(true);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\devices\DeviceManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */