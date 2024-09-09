package io.github.mxylery.testplugin.listeners;

import java.util.List;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import io.github.mxylery.testplugin.*;
import io.github.mxylery.testplugin.blueprints.BobuxItems;
import io.github.mxylery.testplugin.tasks.abilities.*;

public final class BobuxItemListener implements Listener {
	
	private JavaPlugin plugin;
	private RailgunShootAbility railgunAbil = new RailgunShootAbility(100);
	private TeleportRodAbility teleportRodAbil = new TeleportRodAbility(60);
	private MeteorWandAbility explosiveWandAbil = new MeteorWandAbility(300);
	private KatanaDashAbility katanaDashAbil = new KatanaDashAbility(60, 4, 5);
	private ItemStack railgunStack = BobuxItems.getRailgun().getStack();
	private ItemStack teleportRodStack = BobuxItems.getTeleportRod().getStack();
	private ItemStack explosiveWandStack = BobuxItems.getMeteorWand().getStack();
	private ItemStack katanaStack = BobuxItems.getKatana().getStack();
	
	public BobuxItemListener(JavaPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
		railgunAbil.setPlugin(plugin);
		teleportRodAbil.setPlugin(plugin);
		explosiveWandAbil.setPlugin(plugin);
		katanaDashAbil.setPlugin(plugin);
	}
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		
		if ( ( e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR) )) {
			
			Player player = e.getPlayer();
			ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
			
			//Checks for Railgun
			if ( item.equals(railgunStack) ) {
			
				railgunAbil.setPlayer(player);
				AbilityManager.useAbility(player, railgunAbil, plugin);
				
			//Checks for Teleport Rod
			} else if ( item.equals(teleportRodStack) ) {
				
				teleportRodAbil.setPlayer(player);
				AbilityManager.useAbility(player, teleportRodAbil, plugin);
				
				if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					
					e.setCancelled(true);
					
				}
			
			//Checks for Explosive Wand
			} else if ( item.equals(explosiveWandStack) ) {
				
				explosiveWandAbil.setPlayer(player);
				AbilityManager.useAbility(player, explosiveWandAbil, plugin);
				
			} else if ( item.equals(katanaStack)) {
				
				katanaDashAbil.setPlayer(player);
				AbilityManager.useAbility(player, katanaDashAbil, plugin);
				
			}
		}
	}
}
