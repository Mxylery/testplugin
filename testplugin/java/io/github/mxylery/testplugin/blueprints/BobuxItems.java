package io.github.mxylery.testplugin.blueprints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

//This class is where all the item stacks/prices are stored

public class BobuxItems {
	
	private final static int ITEMS = 4;
	
	//Map that takes button and returns shop item associated with it
	private static HashMap<ItemStack, ShopItem> buttonItemMap = new HashMap<ItemStack, ShopItem>();
	
	public static HashMap<ItemStack, ShopItem> getButtonItemMap() {
		return buttonItemMap;
	}
	
	//Retrieves the item stack for THE MAIN CURRENCY!!! (YOU WILL LIKELY USE THIS METHOD A LOT!)			
	public static ItemStack getBobux() {
		
		ItemStack stack = new ItemStack(Material.LIME_DYE);
		ItemMeta meta = (ItemMeta) stack.getItemMeta();
		List<String> lore = new ArrayList<>();
		
		lore.add("This is the currency for the Bobux SMP");
		meta.setDisplayName("Bobux");
		meta.setLore(lore);
		
		stack.setItemMeta(meta);
		
		return stack;
		
	} 
	
	//Retrieves the item stack for the market
	public static ItemStack getMarketStack() {
		
		ItemStack stack = new ItemStack(Material.LIME_DYE);
		ItemMeta meta = (ItemMeta) stack.getItemMeta();
		List<String> lore = new ArrayList<>();
		
		lore.add("Click here to check the rotating shop! (rotates every in-game day)");
		meta.setDisplayName("Daily Shop");
		meta.setLore(lore);
		
		stack.setItemMeta(meta);
		
		return stack;
		
	} 
	
	//Retrieves the item stack for the shop
	public static ItemStack getShopStack() {
		
		ItemStack stack = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
		ItemMeta meta = (ItemMeta) stack.getItemMeta();
		List<String> lore = new ArrayList<>();
		
		lore.add("Click here to check the shop! (everyday goods)");
		meta.setDisplayName("Shop");
		meta.setLore(lore);
		
		stack.setItemMeta(meta);
		
		return stack;
		
	}
	
	public static ItemStack getBountyStack() {
		
		ItemStack stack = new ItemStack(Material.MAP);
		ItemMeta meta = (ItemMeta) stack.getItemMeta();
		List<String> lore = new ArrayList<>();
		
		lore.add("Click here to access Bobux Bounties...");
		meta.setDisplayName("Bounties");
		meta.setLore(lore);
		
		stack.setItemMeta(meta);
		
		return stack;
		
	}
	
	/*
	 *
	 * MARKET ITEMS
	 * 
	 */
	
	public static ShopItem getBersHelmet() {
		
		ItemStack stack = new ItemStack(Material.LEATHER_HELMET);
		ItemMeta meta = (ItemMeta) stack.getItemMeta();
		List<String> lore = new ArrayList<>();
		
		lore.add("The Helmet for the Berserker set.");
		meta.setDisplayName("Berserker Helmet");
		meta.setLore(lore);
		
		stack.setItemMeta(meta);
		
		int price = 10;
		ShopItem item = new ShopItem(stack, price);
		
		return item;
		
	}
	
	public static ShopItem getBersChestplate() {
		
		ItemStack stack = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemMeta meta = (ItemMeta) stack.getItemMeta();
		List<String> lore = new ArrayList<>();
		
		lore.add("The Chestplate for the Berserker set.");
		meta.setDisplayName("Berserker Chestplate");
		meta.setLore(lore);
		
		stack.setItemMeta(meta);
		
		int price = 10;
		ShopItem item = new ShopItem(stack, price);
		
		return item;
		
	} 
	
	public static ShopItem getBersLeggings() {
		
		ItemStack stack = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemMeta meta = (ItemMeta) stack.getItemMeta();
		List<String> lore = new ArrayList<>();
		
		lore.add("The Leggings for the Berserker set.");
		meta.setDisplayName("Berserker Leggings");
		meta.setLore(lore);
		
		stack.setItemMeta(meta);
		
		int price = 20;
		ShopItem item = new ShopItem(stack, price);
		
		return item;
		
	}
	
	public static ShopItem getBersBoots() {
		
		ItemStack stack = new ItemStack(Material.LEATHER_BOOTS);
		ItemMeta meta = (ItemMeta) stack.getItemMeta();
		List<String> lore = new ArrayList<>();
		
		lore.add("The Boots for the Berserker set.");
		meta.setDisplayName("Berserker Boots");
		meta.setLore(lore);
		
		stack.setItemMeta(meta);
		
		int price = 12;
		ShopItem item = new ShopItem(stack, price);
		
		return item;
		
	}
	
	public static ShopItem getRailgun() {
		
		ItemStack stack = new ItemStack(Material.IRON_HOE);
		ItemMeta meta = (ItemMeta) stack.getItemMeta();
		List<String> lore = new ArrayList<>();
		
		lore.add("A very powerful railgun... (Deals 10 Damage with a 40 Block Range)");
		meta.setDisplayName("Railgun");
		meta.setUnbreakable(true);
		meta.setLore(lore);
		
		stack.setItemMeta(meta);
		
		int price = 25;
		ShopItem item = new ShopItem(stack, price);
		
		return item;
		
	} 
	
}


