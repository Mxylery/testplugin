package io.github.mxylery.testplugin.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.mxylery.testplugin.BobuxUtils;
import io.github.mxylery.testplugin.TestPlugin;
import io.github.mxylery.testplugin.blueprints.Bounty;

public class GenerateBountiesTask extends BukkitRunnable {

	private ArrayList<Bounty> bountyList = new ArrayList<Bounty>();
	private static HashMap<Inventory, Bounty[]> inventoryBountyMap = new HashMap<Inventory, Bounty[]>();
	private static HashMap<Player, Inventory> playerInventoryMap = new HashMap<Player, Inventory>();
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private JavaPlugin plugin;
	
	
	
	public GenerateBountiesTask(JavaPlugin plugin, HashMap<Player, Inventory> hashMap) {
		
		this.plugin = plugin;
		
	}
	
	@Override
	public void run() {
		
		bountyList.clear();
		playerInventoryMap.clear();
		inventoryBountyMap.clear();
		
		this.recheckPlayers(plugin.getServer().getOnlinePlayers());
		
		for (int i = 0; i < playerList.size(); i++) {
			
			Player player = playerList.get(i);
			Inventory inventory = Bukkit.createInventory(player, 27, "Bobux Bounties");
			Bounty bounty;
			int amount;
			
			for (int j = 0; j < 3; j++) {
				
				bounty = new Bounty();
				amount = bounty.getBobux();
				
				bountyList.add(bounty);
				
				player.getServer().broadcastMessage("Bounty #" + (j + 1) + " created! This one gives " + amount + " bobux.");
				
			}
			
			playerInventoryMap.put(player, inventory);
			Bounty[] bounties = {bountyList.get(0), bountyList.get(1), bountyList.get(2)};
			bountyList.clear();
			inventoryBountyMap.put(inventory, bounties);
			
			}
		
		}
	
	public static HashMap<Inventory, Bounty[]> getInventoryBountyMap() {
		return inventoryBountyMap;
	}
	
	public static HashMap<Player, Inventory> getPlayerInventoryMap() {
		return playerInventoryMap;
	}
	
	public static void removeTag(Player player, int index) {
		
		Inventory inventory = playerInventoryMap.get(player);
		
		inventory.setItem(10 + 3*index, new ItemStack(Material.AIR));
		
		playerInventoryMap.put(player, inventory);
		
	}
	
	public void recheckPlayers(Collection<? extends Player> players) {
		
		playerList.clear();
		
		Iterator<? extends Player> it = players.iterator();
		
		for (Player player : players) {
			playerList.add( it.next() );
			plugin.getServer().broadcastMessage("Added a player.");
		}
		
	}
		
}
	

