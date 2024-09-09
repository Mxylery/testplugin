package io.github.mxylery.testplugin;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.mxylery.testplugin.blueprints.Bounty;
import io.github.mxylery.testplugin.blueprints.ShopItem;
import io.github.mxylery.testplugin.enums.ShopPrices;
import io.github.mxylery.testplugin.listeners.*;
import io.github.mxylery.testplugin.tasks.AbilityManagerCounterTask;
import io.github.mxylery.testplugin.tasks.GenerateBountiesTask;
import io.github.mxylery.testplugin.tasks.GenerateMarketTask;
import io.github.mxylery.testplugin.tasks.GenerateShopTask;

public final class TestPlugin extends JavaPlugin {
    
	private static Inventory menu = Bukkit.createInventory(null,27,"Bobux Menu");
	private static Inventory market = Bukkit.createInventory(null,36,"The Market");
	private static Inventory shop = Bukkit.createInventory(null,45,"The Shop");
	
	private static HashMap<String, Object> bobuxMap = new HashMap<String, Object>();
	
	private static HashMap<Player, Inventory> playerInventoryMap = new HashMap<Player, Inventory>();
	
	private AbilityManagerCounterTask aMCT = new AbilityManagerCounterTask(this);
	private GenerateMarketTask generateMarketTask = new GenerateMarketTask(this, market);
	private GenerateShopTask generateShopTask = new GenerateShopTask(this, shop);
	private GenerateBountiesTask generateBountiesTask = new GenerateBountiesTask(this, playerInventoryMap);
	
	@Override
    public void onEnable() {
		
		//This map stores all bobux in the config file for the plugin
		Map<String, Object> map = this.getConfig().getConfigurationSection("bobux").getValues(false);
		bobuxMap.putAll(map);
		BobuxStats.setHashMap(bobuxMap);
		
    	new BobuxItemListener(this);
    	new BobuxGUIOne(this);
    	new BobuxStats(this);
    	new BobuxGiver(this);
    	getLogger().info("onEnable has been invoked!");
    	this.getCommand("bobuxshop").setExecutor(new BallsCommandExecutor(this));
    	this.getCommand("bobuxbank").setExecutor(new BallsCommandExecutor(this));
    	this.getCommand("bobuxadd").setExecutor(new BallsCommandExecutor(this));
    	this.getCommand("bobuxwithdraw").setExecutor(new BallsCommandExecutor(this));
    	this.getCommand("bobuxdeposit").setExecutor(new BallsCommandExecutor(this));
    	this.getCommand("bobuxremovesteak").setExecutor(new BallsCommandExecutor(this));
    	
    	//Server-wide constant tasks
    	generateMarketTask.runTaskTimer(this, 0, 1200);
    	generateShopTask.run();
    	generateBountiesTask.runTaskTimer(this, 300, 1200);
    	aMCT.runTaskTimer(this, 0, 4);
    	
    }
    
    @Override
    public void onDisable() {
    	
    	getLogger().info("onDisable has been invoked!");
    	
    	bobuxMap = BobuxStats.getHashMap();
    	
    	this.getConfig().set("bobux", bobuxMap);
    	
    	this.saveConfig();
    	
    }
    
    public static Inventory getMenu() {
    	return menu;
    }

    public static Inventory getShop() {
    	return shop;
    }
    
    public static Inventory getMarket() {
    	return market;
    }
    
    public static HashMap<Player, Inventory> getPlayerInventoryMap() {
    	return GenerateBountiesTask.getPlayerInventoryMap();
    }
    
}
