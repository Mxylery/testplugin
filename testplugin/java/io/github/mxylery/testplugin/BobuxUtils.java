package io.github.mxylery.testplugin;

import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BobuxUtils {
	
	//Retrieves the location of the desired distance in front of where the player is facing
	public static Location getPlayerEyeCoordinate(Player player, Double dist) {
		
		Vector vector = player.getEyeLocation().getDirection();
		
		Location location = player.getLocation();
			
		location.setX(location.getX() + vector.getX()*dist);
		location.setY(location.getY() + vector.getY()*dist);
		location.setZ(location.getZ() + vector.getZ()*dist);
		
		return location;
		
	}
	
	//Same as above but with a specific coordinate
	public static Double getPlayerEyeCoordinate(Player player, Double dist, char character) {
		
		Vector vector = player.getEyeLocation().getDirection();
		
		Double coord = 0.0;
			
		switch(character) {
		
		case 'x': 
			coord = player.getLocation().getX() + vector.getX()*dist;
		break;
		case 'y': 
			coord = player.getLocation().getY() + 1 + vector.getY()*dist;
		break;
		case 'z':
			coord = player.getLocation().getZ() + vector.getZ()*dist;
		default:
		
		}
		
		return coord;	
		
	}
	
	public static Location pseudoRaycast(Player player, Double dist) {
		
		Location blockLoc = BobuxUtils.getPlayerEyeCoordinate(player, 0.0);
		boolean inBlock = false;
		
		//Checks in steps of 0.5 blocks to detect if the block being teleported to has a block there
		for (double i = 0.5; i < dist + 0.5 && inBlock == false; i += 0.5) {
			
			blockLoc = BobuxUtils.getPlayerEyeCoordinate(player, i);
			blockLoc.add(0.0, 1.0, 0.0);

			if (!blockLoc.getBlock().getType().equals(Material.AIR) && !blockLoc.getBlock().getType().equals(Material.WATER) && !blockLoc.getBlock().getType().equals(Material.SNOW) && !blockLoc.getBlock().getType().equals(Material.LAVA)) {
				
				blockLoc.add(0.0, -1.0, 0.0);
				blockLoc = BobuxUtils.getPlayerEyeCoordinate(player, i - 0.5);
				inBlock = true;	
				
			} 
			
			if (i >= dist - 0.5) {
				
				blockLoc = BobuxUtils.getPlayerEyeCoordinate(player, dist);
				
			}
			
		}
		
		return blockLoc;
		
	}
	
	//Gets the vector from one location to another
	public static Vector getLocationDifference(Location loc1, Location loc2) {
		
		Vector result = new Vector(loc2.getX() - loc1.getX(), loc2.getY() - loc1.getY(), loc2.getZ() - loc1.getZ());
		
		return result;
		
	}
	
	//Method for returning distance of an entity from crosshair position
	public static Vector entityFromCrosshair(Player player, Entity entity) {
	
		double yPos = entity.getLocation().getY() + 1;
		
		double entityDist = player.getPlayer().getLocation().distance(entity.getLocation());
		
		Location playerEyeLoc = BobuxUtils.getPlayerEyeCoordinate(player, entityDist);
		
		Vector locDiff = BobuxUtils.getLocationDifference(playerEyeLoc, entity.getLocation());
		
		return locDiff;
		
	}
	
	//Method for returning the totalToRemove amount of items a player has IN THEIR INVENTORY
	public static int getTotalMaterials(Player player, Material material) {
		
		Inventory inventory = player.getInventory();
	
		HashMap<Integer, ? extends ItemStack> intStackMap = inventory.all(material);
		
		if (intStackMap.isEmpty()) {
			
			return 0;
			
		} else {
			int amount = 0;
			ItemStack stack;
			ArrayList<Integer> arrayList = new ArrayList<Integer>();
			
			for (int i = 0; i < 36; i++) {
				
				if (intStackMap.containsKey(i)) {
					arrayList.add(i);
				} 
				
			}
			
			for (int j = 0; j < arrayList.size(); j++) {
				
				stack = inventory.getItem(arrayList.get(j));
				amount += stack.getAmount();
				
			}
			
			return amount;
			
		}
		
	}
	
	public static void removeTotalMaterials(Player player, Material material, int amount) {
		
		int totalToRemove = amount;
		int difference = 0;
		
		int stackAmount;
		int lastIndex = 0;
		
		int index1;
		int index2;
		
		int amount1;
		int amount2;
		
		int lastIntISwear = -1;
	
		Inventory inventory = player.getInventory();
		HashMap<Integer, ? extends ItemStack> intStackMap = inventory.all(material);
		
		if (intStackMap.isEmpty() || amount < 0) {
			
		} else {
			
			ItemStack stack;
			HashMap<Integer, Integer> indexAmountMap = new HashMap<Integer, Integer>();
			ArrayList<Integer> arrayList = new ArrayList<Integer>();
			arrayList.clear();
			indexAmountMap.clear();
			
			//Gets all indices of items in an arraylist and also puts them in a hash map
			for (int i = 0; i < 36; i++) {
				if (intStackMap.containsKey(i)) {
					indexAmountMap.put(i, inventory.getItem(i).getAmount());
					arrayList.add(i);
				}
			}
			
			//Sorts the indices in the arraylist from least stacks to most stacks using the hash map for help
			if (arrayList.size() > 1) {
				for (int h = 0; h < arrayList.size() - 1; h++) {
					for (int j = 0; j < arrayList.size() - 1; j++) {
				
						index1 = arrayList.get(j);
						index2 = arrayList.get(j+1);
				
						amount1 = indexAmountMap.get(index1);
						amount2 = indexAmountMap.get(index2);
				
						if (amount1 > amount2) {
							arrayList.set(j, index2);
							arrayList.set(j+1, index1);
						} 
					}
				}
			}
			
			//This while loop goes through the ordered inventory indices from lowest amount to highest amount and stops when it hits 
			while (totalToRemove > 0) {
			
				//ArrayList index to check
				lastIntISwear++;
				
				
				lastIndex = arrayList.get(lastIntISwear);
				stackAmount = indexAmountMap.get(lastIndex);
				
				totalToRemove -= stackAmount;
				
				if (totalToRemove > 0) {
					inventory.setItem(lastIndex, new ItemStack(Material.AIR));
					player.getServer().broadcastMessage("Deleted a slot. (" + lastIndex + ")");
				} 
	
				difference = stackAmount + totalToRemove;
				
			}
			
			stack = inventory.getItem(lastIndex);
			
			if (stack.getAmount() - difference > 0) {
			
			stack.setAmount(stack.getAmount() - difference);
			inventory.setItem(lastIndex, stack);
			
			}
			
			
			
		}
		
	}
	
}