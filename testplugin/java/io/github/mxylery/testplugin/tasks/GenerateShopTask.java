package io.github.mxylery.testplugin.tasks;

import java.util.ArrayList;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.mxylery.testplugin.enums.ShopPrices;

public class GenerateShopTask extends BukkitRunnable {

	private final JavaPlugin plugin;
	private static Inventory shop;
	
	public GenerateShopTask(JavaPlugin plugin, Inventory inventory) {
		
		this.plugin = plugin;
		shop = inventory;
		
	}
	
	public void run() {
		
		int price;
		ItemStack stack;
		ItemMeta meta;
		ArrayList<String> arrayList;
		
		ShopPrices[] itemList = ShopPrices.values();
    	
    	for (int i = 0, j = 10; i < 5 ; i++, j++) {
    		
    		ShopPrices item = itemList[i];
    		
    		price = item.price;
    		stack = item.stack;
    		meta = stack.getItemMeta();
    		arrayList = new ArrayList<String>();
    		
    		arrayList.add("This item costs " + price + " bobux.");
    		meta.setLore(arrayList);
    		stack.setItemMeta(meta);
    		
    		shop.setItem(j, stack);
    		
    		String string = "" + price;
    		plugin.getServer().broadcastMessage(string);

    	}
		
	}
	
}
