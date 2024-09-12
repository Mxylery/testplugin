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
		
		lore.add("§r§7This is the currency for the Bobux SMP");
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
		
		lore.add("§r§7Click here to check the rotating shop! (rotates every in-game day)");
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
		
		lore.add("§r§7Click here to check the shop! (everyday goods)");
		meta.setDisplayName("Shop");
		meta.setLore(lore);
		
		stack.setItemMeta(meta);
		
		return stack;
		
	}
	
	public static ItemStack getBountyStack() {
		
		ItemStack stack = new ItemStack(Material.MAP);
		ItemMeta meta = (ItemMeta) stack.getItemMeta();
		List<String> lore = new ArrayList<>();
		
		lore.add("§r§7Click here to access Bobux Bounties...");
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
		
		lore.add("§r§7The helmet for the Berserker set.");
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
		
		lore.add("§r§7The Chestplate for the Berserker set.");
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
		
		lore.add("§r§7The leggings for the Berserker set.");
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
		
		lore.add("§r§7The boots for the Berserker set.");
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
		
		lore.add("§r§7A very powerful railgun... \n10 DMG\n5s CD\n40 Block Range");
		meta.setDisplayName("Railgun");
		meta.setUnbreakable(true);
		meta.setLore(lore);
		
		stack.setItemMeta(meta);
		
		int price = 25;
		ShopItem item = new ShopItem(stack, price);
		
		return item;
		
	} 
	
	public static ShopItem getTeleportRod() {
		
		ItemStack stack = new ItemStack(Material.END_ROD);
		ItemMeta meta = (ItemMeta) stack.getItemMeta();
		List<String> lore = new ArrayList<>();
		
		lore.add("§r§7A rod that teleports you forward 12 blocks.");
		lore.add("§r§63s CD (Right Click)");
		meta.setDisplayName("Teleport Rod");
		meta.setUnbreakable(true);
		meta.setLore(lore);
		
		stack.setItemMeta(meta);
		
		int price = 16;
		ShopItem item = new ShopItem(stack, price);
		
		return item;
		
	}
	
	public static ShopItem getMeteorWand() {
		
		ItemStack stack = new ItemStack(Material.BLAZE_ROD);
		ItemMeta meta = (ItemMeta) stack.getItemMeta();
		List<String> lore = new ArrayList<>();
		
		lore.add("§r§7A wand that summons a fireball from the sky.");
		lore.add("§r§615s CD (Right Click)");
		meta.setDisplayName("Meteor Wand");
		meta.setUnbreakable(true);
		meta.setLore(lore);
		
		stack.setItemMeta(meta);
		
		int price = 30;
		ShopItem item = new ShopItem(stack, price);
		
		return item;
		
	}
	
	public static ShopItem getKatana() {
		
		ItemStack stack = new ItemStack(Material.IRON_SWORD);
		ItemMeta meta = (ItemMeta) stack.getItemMeta();
		List<String> lore = new ArrayList<>();
		
		lore.add("§r§7A katana that lets you harness");
		lore.add("§r§7the powers and techniques used by");
		lore.add("§r§7ninjas worldwide...");
		lore.add("§r§630s CD (Right Click)");
		meta.setDisplayName("Katana");
		meta.setUnbreakable(true);
		meta.setLore(lore);
		
		stack.setItemMeta(meta);
		
		int price = 50;
		ShopItem item = new ShopItem(stack, price);
		
		return item;
		
	}
	
	public static ShopItem getExcavator() {
		
		ItemStack stack = new ItemStack(Material.GOLDEN_SHOVEL);
		ItemMeta meta = (ItemMeta) stack.getItemMeta();
		List<String> lore = new ArrayList<>();
		
		lore.add("§r§7The excavator allows you to break");
		lore.add("§r§7and gather blocks in a 3x3x3 cube");
		lore.add("§r§7wherever you click (collected blocks");
		lore.add("§r§7must be of iron-level toughness.)");
		lore.add("§r§65s CD (Right Click on Block)");
		meta.setDisplayName("Katana");
		meta.setUnbreakable(true);
		meta.setLore(lore);
		
		stack.setItemMeta(meta);
		
		int price = 30;
		ShopItem item = new ShopItem(stack, price);
		
		return item;
		
	}
	
	public static ShopItem getMolemanShovel() {
		
		ItemStack stack = new ItemStack(Material.DIAMOND_SHOVEL);
		ItemMeta meta = (ItemMeta) stack.getItemMeta();
		List<String> lore = new ArrayList<>();
		
		lore.add("§r§7mole dig");
		lore.add("§r§65s CD (Right Click)");
		meta.setDisplayName("Moleman's Shovel");
		meta.setUnbreakable(true);
		meta.setLore(lore);
		
		stack.setItemMeta(meta);
		
		int price = 50;
		ShopItem item = new ShopItem(stack, price);
		
		return item;
		
	}
	
}


