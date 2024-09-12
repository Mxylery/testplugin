package io.github.mxylery.testplugin;

import org.bukkit.util.Vector;

import io.github.mxylery.testplugin.blueprints.BobuxItems;
import io.github.mxylery.testplugin.listeners.BobuxGUIOne;
import io.github.mxylery.testplugin.listeners.BobuxStats;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BallsCommandExecutor implements CommandExecutor {
	private final TestPlugin plugin;
	private final Inventory inv;
	
	public BallsCommandExecutor(TestPlugin plugin) {
		this.plugin = plugin;
		inv = Bukkit.createInventory(null,27,"Bobux Menu");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	
		if (sender instanceof Player) {
			
			
				//Shop cmd
			if (cmd.getName().equalsIgnoreCase("bobuxshop")) {
				
				Player player = (Player) sender;
				
				BobuxGUIOne.openMainMenu(player);
				
				return true;
				
				
				//Bank cmd
    		} else if (cmd.getName().equalsIgnoreCase("bobuxbank")) {
				
				Player player = (Player) sender;
				
				player.getServer().broadcastMessage("You have " + BobuxStats.getPlayerBobux(player) + " bobux.");
				
				return true;
				
				
				//Add cmd
    		} else if (cmd.getName().equalsIgnoreCase("bobuxadd") && args.length == 1) {
				
				Player player = (Player) sender;

				int parsedInt = Integer.parseInt(args[0]);
				
				BobuxStats.addPlayerBobux(player, parsedInt);
				
				return true;
				
				
				//Withdraw cmd
    		} else if (cmd.getName().equalsIgnoreCase("bobuxwithdraw") && args.length == 1) {
				
				Player player = (Player) sender;
				int parsedInt = Integer.parseInt(args[0]);
				
				if (parsedInt > BobuxStats.getPlayerBobux(player)) {
					
					player.sendMessage("You do not have enough bobux to withdraw that amount.");
					
				} else {
					
					player.sendMessage("Withdrawn " + parsedInt + " bobux.");
					ItemStack bobuxStack = BobuxItems.getBobux();
					bobuxStack.setAmount(parsedInt);
					
					BobuxStats.addPlayerBobux(player, -1*parsedInt);
					
					player.getInventory().addItem(bobuxStack);
					
				}
				
				return true;
				
				//Deposit cmd
    		} else if (cmd.getName().equalsIgnoreCase("bobuxdeposit") && args.length == 1) {
				
				Player player = (Player) sender;
				int parsedInt = Integer.parseInt(args[0]);
				ItemStack bobuxStack = BobuxItems.getBobux();
				
				if (!player.getInventory().containsAtLeast(bobuxStack, parsedInt)) {
					
					player.sendMessage("You do not have enough bobux to deposit that amount.");
					
				} else {
					
					player.sendMessage("Deposited " + parsedInt + " bobux.");
		
					BobuxStats.addPlayerBobux(player, parsedInt);
					
					bobuxStack.setAmount(parsedInt);
					player.getInventory().removeItem(bobuxStack);
					
				}
				
				return true;
				
    		} else if (cmd.getName().equalsIgnoreCase("bobuxremovesteak") && args.length == 1) {
    			
    			Player player = (Player) sender;
				int parsedInt = Integer.parseInt(args[0]);
				
				BobuxUtils.removeTotalMaterials(player, Material.COOKED_BEEF, parsedInt);
				
				return true;
    			
    		} else if (cmd.getName().equalsIgnoreCase("bobuxgive") && args.length == 1) {
    			
    			Player player = (Player) sender;
				String s = args[0];
				ItemStack stack;
				
				switch (s) {
				case "berserker_helmet": stack = BobuxItems.getBersHelmet().getStack();
				player.getInventory().addItem(stack);
				break;
				case "berserker_chestplate": stack = BobuxItems.getBersChestplate().getStack();
				player.getInventory().addItem(stack);
				break;
				case "berserker_leggings": stack = BobuxItems.getBersLeggings().getStack();
				player.getInventory().addItem(stack);
				break;
				case "berserker_boots": stack = BobuxItems.getBersBoots().getStack();
				player.getInventory().addItem(stack);
				break;
				case "teleport_rod": stack = BobuxItems.getTeleportRod().getStack();
				player.getInventory().addItem(stack);
				break;
				case "meteor_wand": stack = BobuxItems.getMeteorWand().getStack();
				player.getInventory().addItem(stack);
				break;
				case "railgun": stack = BobuxItems.getRailgun().getStack();
				player.getInventory().addItem(stack);
				break;
				case "katana": stack = BobuxItems.getKatana().getStack();
				player.getInventory().addItem(stack);
				break;
				case "excavator": stack = BobuxItems.getExcavator().getStack();
				player.getInventory().addItem(stack);
				break;
				default: player.getServer().broadcastMessage("Balls");
				}
				
				return true;
    			
    		} else {
    			
    			return false;
    			
    		}
			
		} else {
			
			return false;
			
		}
    }
	
}
