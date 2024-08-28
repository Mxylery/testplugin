package io.github.mxylery.testplugin.listeners;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.testplugin.TestPlugin;
import io.github.mxylery.testplugin.blueprints.BobuxItems;

public class BobuxStats implements Listener {
	
	private static HashMap<String,Object> bobuxMap = new HashMap<>();
	
	public BobuxStats(TestPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onLogin(PlayerJoinEvent e) {
		
		String playerString = e.getPlayer().getUniqueId().toString();
		
		if (!bobuxMap.containsKey(playerString)) {
			
			bobuxMap.put(playerString, 0);
			
			e.getPlayer().getServer().broadcastMessage(playerString);
			
		} else {
			
			e.getPlayer().getServer().broadcastMessage(playerString);
			
		}
		
	}
	
	//Uses player as a field and returns their bobux count
	public static int getPlayerBobux(Player player) {
		
		String playerString = player.getUniqueId().toString();
		
		if (bobuxMap.containsKey(playerString)) {
			
			int bobuxResult = (int) bobuxMap.get(playerString);
			
			return bobuxResult;
			
		} else {
			return -1;
		}
		
	}
	
	public static void addPlayerBobux(Player player, int index) {
		
		String playerString = player.getUniqueId().toString();
		
		if (bobuxMap.containsKey(playerString)) {
			
			int previousVal = (int) bobuxMap.get(playerString);
			
			int currentVal = previousVal + index;
			
			bobuxMap.replace(playerString, currentVal);
			
		} 
		
	}
	
	public static void buyItem(Player player, int bobux, ItemStack stack) {
		
		String playerString = player.getUniqueId().toString();
		
		if (bobuxMap.containsKey(playerString)) {
			
			Integer playerBank = (Integer) bobuxMap.get(playerString);
			
			if (playerBank < bobux) {
				player.getServer().broadcastMessage("You do not have enough bobux to buy this item.");
			} else {
				player.getInventory().addItem(stack);
				BobuxStats.addPlayerBobux(player, bobux);
				player.getServer().broadcastMessage("You have spent " + bobux + " bobux.");
			}
			
		}
		
	}
	
	public static HashMap<String, Object> getHashMap() {
		return bobuxMap;
	}
	
	public static void setHashMap(HashMap<String, Object> map) {
		bobuxMap = map;
	}
	
}
