package io.github.mxylery.testplugin.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.DoubleChestInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import io.github.mxylery.testplugin.BobuxUtils;
import io.github.mxylery.testplugin.TestPlugin;
import io.github.mxylery.testplugin.blueprints.*;
import io.github.mxylery.testplugin.enums.ShopPrices;
import io.github.mxylery.testplugin.tasks.GenerateBountiesTask;
import io.github.mxylery.testplugin.tasks.GenerateMarketTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;

public class BobuxGUIOne implements Listener {

	private static HashMap<Player, Inventory> playerInventoryMap = new HashMap<Player, Inventory>();
	private static HashMap<Inventory, Bounty[]> inventoryBountyMap = new HashMap<Inventory, Bounty[]>();
	
	private static Inventory menu = TestPlugin.getMenu();
	private static Inventory market = TestPlugin.getMarket();
	private static Inventory shop = TestPlugin.getShop();
	private static Inventory error = Bukkit.createInventory(null, 27);
	
	public BobuxGUIOne(TestPlugin plugin) {
		
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
		
	}
	
	public static void openMainMenu(final Player ent) {
		
		menu.setItem(11, BobuxItems.getMarketStack());
		menu.setItem(13, BobuxItems.getBountyStack());
		menu.setItem(15, BobuxItems.getShopStack());
		
		ent.openInventory(menu);
		
	}
	
	public static void openMarket(final Player ent) {
		
		ent.openInventory(market);
		
	}
	
	public static void openShop(final Player ent) {
		
		ent.openInventory(shop);
		
	}
	
	public static void openBounty(final Player ent) {
		
		playerInventoryMap = GenerateBountiesTask.getPlayerInventoryMap();
		inventoryBountyMap = GenerateBountiesTask.getInventoryBountyMap();
		
		if (playerInventoryMap.containsKey(ent)) {
			
			Inventory inventory = playerInventoryMap.get(ent);
			ItemStack tagStack;
			ItemStack[] stacks;
			ItemStack stack;
			ItemMeta tagMeta;
			Material material;
			int prize;
			int subAmount;
			int playerAmount;
			Bounty[] bounties = inventoryBountyMap.get(inventory);
			Bounty bounty;
			String desc;
			String stackName;
			ArrayList<String> stringList = new ArrayList<String>();
			
			for (int i = 0; i < 3; i++) {
			
				stringList.clear();
				bounty = bounties[i];
				tagStack = new ItemStack(Material.NAME_TAG);
				prize = bounty.getBobux();
				tagStack.setAmount(prize);
				tagMeta = tagStack.getItemMeta();
			
				for (int h = 0; h < 3; h++) {
				
					stacks = bounty.getStacks();
					stack = stacks[h];
				
					material = stack.getType();
					subAmount = stack.getAmount();
					stackName = stack.getType().toString().toLowerCase();
					playerAmount = BobuxUtils.getTotalMaterials(ent, material);
				
					desc = String.format(" %d / %d %s", playerAmount, subAmount, stackName);
					stringList.add(desc);
				
				}
			
				tagMeta.setLore(stringList);
				tagMeta.setDisplayName("Bounty #" + (i + 1) + " (" + prize + " bobux)");
				tagStack.setItemMeta(tagMeta);
				inventory.setItem(10 + 3*i, tagStack);
			
			}
			
			ent.openInventory(inventory);
			
		} else {
			
			ent.openInventory(error);
			
		}
		
	}
	
	@EventHandler
	public void onInvInteract(InventoryClickEvent e) {
		
		Player player = (Player) e.getWhoClicked();
		
		try {
		
			//In the main menu...
			if (e.getClickedInventory().equals(menu)) {
			
				e.setCancelled(true);
			
					if (e.getCurrentItem().equals(BobuxItems.getMarketStack())) {
						BobuxGUIOne.openMarket(player);
					} else if (e.getCurrentItem().equals(BobuxItems.getShopStack())) {
						BobuxGUIOne.openShop(player);
					} else if (e.getCurrentItem().equals(BobuxItems.getBountyStack())) {
						BobuxGUIOne.openBounty(player);
					}
					
					
					
			//In the market menu...
			} else if (e.getClickedInventory().equals(market)) {
			
				e.setCancelled(true);
					
				//If clicking one of the BUY buttons
					if (e.getCurrentItem().getType().equals(Material.LIME_CONCRETE)) {
					
						//Returns the button clicked as an item stack
						ItemStack button =  e.getCurrentItem();
						HashMap<ItemStack, ShopItem> hashMap = GenerateMarketTask.getButtonItemMap();
						
						//Retrieves shop item associated with button using the hash map
						ShopItem shopItem = hashMap.get(button);
						
						//Retrieves shop item's price and stack
						int price = shopItem.getPrice();
						ItemStack stack = shopItem.getStack();
						
						//Checks if player has bobux to buy item
						BobuxStats.buyItem(player, price, stack);
						
					}
					
					
					
			//In the shop menu...
			} else if (e.getClickedInventory().equals(shop)) {
					
				e.setCancelled(true);
				int price;
				ItemStack stack;
					
				if (!e.getCurrentItem().getType().equals(null)) {
					switch (e.getCurrentItem().getType().toString()) {
						case "COAL": price = 1;
						stack = new ItemStack(Material.COAL);
						BobuxStats.buyItem(player, price, stack);
						break;
						case "IRON_INGOT": price = 1;
						stack = new ItemStack(Material.IRON_INGOT);
						BobuxStats.buyItem(player, price, stack);
						break;
						case "GOLD_INGOT": price = 1;
						stack = new ItemStack(Material.GOLD_INGOT);
						BobuxStats.buyItem(player, price, stack);
						break;
						case "DIAMOND": price = 5;
						stack = new ItemStack(Material.DIAMOND);
						BobuxStats.buyItem(player, price, stack);
						break;
						case "NETHERITE_INGOT": price = 10;
						stack = new ItemStack(Material.NETHERITE_INGOT);
						BobuxStats.buyItem(player, price, stack);
						break;
					}	
				} 
					
				
				
			//In the bounty menu...
			} else if ( playerInventoryMap.containsValue( e.getClickedInventory() ) ) {
					
					e.setCancelled(true);
					
					player = (Player) e.getClickedInventory().getHolder();
					Inventory inventory = e.getClickedInventory();
					Bounty[] bounties = inventoryBountyMap.get(inventory);
					ArrayList<String> stringList = new ArrayList<String>();
					Bounty bounty;
					ItemStack[] stacks;
					ItemStack stack;
					ItemStack bobuxStack;
					int prize;
					Material material;
					boolean canGive = true;
					
				//Clicking on the first bounty...
					if (e.getSlot() == 10) {
						
						bounty = bounties[0];
						stacks = bounty.getStacks();
						prize = bounty.getBobux();
						
						for (int i = 0; i < 3; i++) {
							
							stack = stacks[i];
							material = stack.getType();
							
							if (BobuxUtils.getTotalMaterials(player, material) < stack.getAmount()) {
								canGive = false;
								player.getServer().broadcastMessage("Cannot give.");
							}
						}
						
						if (canGive == true) {
							for (int i = 0; i < 3; i++) {
								
								stack = stacks[i];
								material = stack.getType();
								BobuxUtils.removeTotalMaterials(player, material, stack.getAmount());
								
							} 
							stack = e.getCurrentItem();
							GenerateBountiesTask.removeTag(player, 0);
							bobuxStack = BobuxItems.getBobux();
							bobuxStack.setAmount(prize);
							player.getInventory().addItem(bobuxStack);
						}
					
					//Clicking on the second bounty...
					} else if (e.getSlot() == 13) {
						
						bounty = bounties[1];
						stacks = bounty.getStacks();
						prize = bounty.getBobux();
						
						for (int i = 0; i < 3; i++) {
							
							stack = stacks[i];
							material = stack.getType();
							
							if (BobuxUtils.getTotalMaterials(player, material) < stack.getAmount()) {
								canGive = false;
							}
							
						}
						
						if (canGive == true) {
							
							for (int i = 0; i < 3; i++) {
								
								stack = stacks[i];
								material = stack.getType();
								
								BobuxUtils.removeTotalMaterials(player, material, stack.getAmount());
							
							} 
							
							stack = e.getCurrentItem();
							GenerateBountiesTask.removeTag(player, 1);
							bobuxStack = BobuxItems.getBobux();
							bobuxStack.setAmount(prize);
							player.getInventory().addItem(bobuxStack);
							
						}
						
					//Clicking on the third bounty...
					} else if (e.getSlot() == 16) {
						
						bounty = bounties[2];
						stacks = bounty.getStacks();
						prize = bounty.getBobux();
						
						for (int i = 0; i < 3; i++) {
							
							stack = stacks[i];
							material = stack.getType();
							
							if (BobuxUtils.getTotalMaterials(player, material) < stack.getAmount()) {
								canGive = false;
							}
							
						}
						
						if (canGive == true) {
							
							for (int i = 0; i < 3; i++) {
								
								stack = stacks[i];
								material = stack.getType();
								
								BobuxUtils.removeTotalMaterials(player, material, stack.getAmount());
							
							} 
							
							stack = e.getCurrentItem();
							GenerateBountiesTask.removeTag(player, 2);
							bobuxStack = BobuxItems.getBobux();
							bobuxStack.setAmount(prize);
							player.getInventory().addItem(bobuxStack);
							
							
						}
						
					}
					
				}
			
			} catch (Exception excep) {
									
		}
		
	}
	
}
