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
import org.bukkit.util.Vector;

import io.github.mxylery.testplugin.*;
import io.github.mxylery.testplugin.blueprints.BobuxItems;
import io.github.mxylery.testplugin.tasks.abilities.RailgunShootAbility;
import io.github.mxylery.testplugin.tasks.abilities.TeleportRodAbility;

public final class BobuxItemListener implements Listener {
	
	private RailgunShootAbility railgunAbil = new RailgunShootAbility(100);
	private TeleportRodAbility teleportRodAbil = new TeleportRodAbility(200);
	private ItemStack railgunStack = BobuxItems.getRailgun().getStack();
	private ItemStack teleportRodStack = BobuxItems.getTeleportWand().getStack();
	
	public BobuxItemListener(TestPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		
		if ( ( e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR) )) {
			
			Player player = e.getPlayer();
			
			ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
			
			if ( item.equals(railgunStack) ) {
			
				railgunAbil.setPlayer(player);
			
				AbilityManager.useAbility(player, railgunAbil);
				
			} else if ( item.equals(teleportRodStack) ) {
				
				teleportRodAbil.setPlayer(player);
				
				AbilityManager.useAbility(player, teleportRodAbil);
				
			}
			
		}
		
	}
	
}
