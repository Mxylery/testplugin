package io.github.mxylery.testplugin.listeners;

import java.util.List;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import io.github.mxylery.testplugin.*;
import io.github.mxylery.testplugin.blueprints.BobuxItems;
import io.github.mxylery.testplugin.tasks.abilities.*;

public final class BobuxItemListener implements Listener {
	
	private JavaPlugin plugin;
	private RailgunRCAbility railgunRCAbil = new RailgunRCAbility(100);
	private TeleportRCAbility teleportRCAbil = new TeleportRCAbility(60);
	private MeteorRCAbility explosiveRCAbil = new MeteorRCAbility(300);
	private KatanaRCAbility katanaRCAbil = new KatanaRCAbility(60, 4, 5);
	private ExcavatorRCAbility excavatorRCAbil = new ExcavatorRCAbility(100);
	private MolemanRCAbility molemanRCAbil = new MolemanRCAbility(100, 4, 5);
	private ItemStack railgunStack = BobuxItems.getRailgun().getStack();
	private ItemStack teleportRodStack = BobuxItems.getTeleportRod().getStack();
	private ItemStack explosiveWandStack = BobuxItems.getMeteorWand().getStack();
	private ItemStack katanaStack = BobuxItems.getKatana().getStack();
	private ItemStack excavatorStack = BobuxItems.getExcavator().getStack();
	private ItemStack molemanStack = BobuxItems.getMolemanShovel().getStack();
	
	public BobuxItemListener(JavaPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
		railgunRCAbil.setPlugin(plugin);
		teleportRCAbil.setPlugin(plugin);
		explosiveRCAbil.setPlugin(plugin);
		katanaRCAbil.setPlugin(plugin);
		excavatorRCAbil.setPlugin(plugin);
		molemanRCAbil.setPlugin(plugin);
	}
	
	//There should be an event handler for each type of activation there are for abilities
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		
		if ( ( e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR) )) {
			
			Player player = e.getPlayer();
			ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
			
			//Checks for Railgun
			if ( item.equals(railgunStack) ) {
			
				railgunRCAbil.setPlayer(player);
				AbilityManager.useAbility(player, railgunRCAbil, plugin);
				
			//Checks for Teleport Rod
			} else if ( item.equals(teleportRodStack) ) {
				
				teleportRCAbil.setPlayer(player);
				AbilityManager.useAbility(player, teleportRCAbil, plugin);
				
				if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					
					e.setCancelled(true);
					
				}
			
			//Checks for Explosive Wand
			} else if ( item.equals(explosiveWandStack) ) {
				
				explosiveRCAbil.setPlayer(player);
				AbilityManager.useAbility(player, explosiveRCAbil, plugin);
				
			//Checks for Katana
			} else if ( item.equals(katanaStack)) {
				
				katanaRCAbil.setPlayer(player);
				AbilityManager.useAbility(player, katanaRCAbil, plugin);
				
			//Checks for Excavator
			} else if ( item.equals(excavatorStack) && !e.getAction().equals(Action.RIGHT_CLICK_AIR) ) {
				
				Location blockLoc = e.getClickedBlock().getLocation();
			
				excavatorRCAbil.setPlayer(player);
				excavatorRCAbil.setLoc(blockLoc);
				AbilityManager.useAbility(player, excavatorRCAbil, plugin);
				
			} else if ( item.equals(molemanStack) ) {
				
				molemanRCAbil.setPlayer(player);
				AbilityManager.useAbility(player, molemanRCAbil, plugin);
				
			}
		}
	}
	
	@EventHandler
	public void onLeftClick(PlayerInteractEvent e) {
		
		if ( ( e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_AIR) )) {
			
			
			
		}
		
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		
		
		
		
	}
}
