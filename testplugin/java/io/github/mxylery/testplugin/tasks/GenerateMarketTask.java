package io.github.mxylery.testplugin.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.mxylery.testplugin.TestPlugin;
import io.github.mxylery.testplugin.blueprints.BobuxItems;
import io.github.mxylery.testplugin.blueprints.ShopItem;

public class GenerateMarketTask extends BukkitRunnable {

	private ShopItem item;
	private final JavaPlugin plugin;
	private static Inventory shop;
	private static HashMap<ItemStack, ShopItem> buttonItemMap = new HashMap<ItemStack, ShopItem>();
	private final int ITEMS = 10;
	
	public GenerateMarketTask(JavaPlugin plugin, Inventory inventory) {
		this.plugin = plugin;
		shop = inventory;
	}
	
	//This tasks runs every 20 minutes (24000 ticks)
	@Override
	public void run() {
		
		ArrayList<Integer> arrayList = new ArrayList<>();
		int rng;
		
		buttonItemMap.clear();
		arrayList.clear();
		
		plugin.getServer().broadcastMessage("There are new items in the market!");
		
		//Goes through the items randomly and picks 3 (the arraylist makes sure no dupes are added)
		for (int i = 0; i < 3; i++) {
			do {
				rng = (int) (Math.random()*ITEMS);
				switch (rng) {
					case 0: item = BobuxItems.getBersHelmet();
					break;
					case 1: item = BobuxItems.getBersChestplate();
					break;
					case 2: item = BobuxItems.getBersLeggings();
					break;
					case 3: item = BobuxItems.getBersBoots();
					break;	
					case 4: item = BobuxItems.getRailgun();
					break;	
					case 5: item = BobuxItems.getTeleportRod();
					break;
					case 6: item = BobuxItems.getMeteorWand();
					break;
					case 7: item = BobuxItems.getKatana();
					break;
					case 8: item = BobuxItems.getExcavator();
					break;
					case 9: item = BobuxItems.getMolemanShovel();
					break;
				} 
			} 
			while (arrayList.contains(rng));
			
			arrayList.add(rng);
			
			//Copies the item stack and price of whatever item obtained from the switch statement
			ItemStack stack = item.getStack();
			int price = item.getPrice();
			
			ItemStack buttonStack = new ItemStack(Material.LIME_CONCRETE);
			buttonStack.setAmount(1);
			List<String> buttonLore = new ArrayList<>();
			buttonLore.add(item.getStack().getItemMeta().getDisplayName() + " costs " + price + " Bobux.");
			ItemMeta buttonMeta = buttonStack.getItemMeta();
			buttonMeta.setLore(buttonLore);
			buttonMeta.setDisplayName("§aBUY");
			buttonStack.setItemMeta(buttonMeta);
			
			//Sets the item stacks and the buy buttons in the inventory
			shop.setItem(10 + 3*i, stack);
			shop.setItem(19 + 3*i, buttonStack);
	
			//Stores the hash map to the button-item map
			buttonItemMap.put(buttonStack, item);
			
		}
		
	}
	
	public static HashMap<ItemStack, ShopItem> getButtonItemMap() {
		
		return buttonItemMap;
		
	}

}
