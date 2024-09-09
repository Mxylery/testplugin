package io.github.mxylery.testplugin;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import io.github.mxylery.testplugin.blueprints.BobuxAbility;

public class AbilityManager {

	private static long counter = 0;
	private static JavaPlugin bobuxPlugin;
	
	public static void useAbility(Player player, BobuxAbility ability, JavaPlugin plugin) {
		
		HashMap<Player,Long> playerCooldownMap = ability.getCooldownMap();
		bobuxPlugin = plugin;
		long cooldown = ability.getCooldown();
		long delay = ability.getDelay();
		long reps = ability.getReps();
		BukkitTask task = ability.getTask();

		if (!playerCooldownMap.containsKey(player)) {
			playerCooldownMap.put(player, (long) 4);
		}
	
		long time = playerCooldownMap.get(player);
		
		if ((counter + (delay*(reps-1)) - time) > cooldown) {
			
			ability.run();
			playerCooldownMap.put(player, counter);
			
		} else {
			
			player.sendMessage("There is " + (((cooldown + 10 - (counter - time)) / 20)) + "s left before you can use this ability. ");
			
		}
		
		ability.setCooldownMap(playerCooldownMap);
	
	}
	
	public static void updateCounter() {
		counter += 4;
	}
	
}
