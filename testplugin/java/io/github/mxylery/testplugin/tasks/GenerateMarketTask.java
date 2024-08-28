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
	private final int ITEMS = 5;
	
	public GenerateMarketTask(JavaPlugin plugin, Inventory inventory) {
		this.plugin = plugin;
		shop = inventory;
	}
	
	@Override
	public void run() {
		
		buttonItemMap.clear();
		plugin.getServer().broadcastMessage("There are new items in the market!");
		ArrayList<Integer> arrayList = new ArrayList<>();
		int rng;
		arrayList.clear();
		
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
