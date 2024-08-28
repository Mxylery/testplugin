package io.github.mxylery.testplugin;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.mxylery.testplugin.blueprints.BobuxAbility;

public class AbilityManager {

	private static long counter = 0;
	
	public static void useAbility(Player player, BobuxAbility ability) {
		
		HashMap<Player,Long> hashMap = ability.getCooldownMap();
		
		long cooldown = ability.getCooldown();
		
		if (!hashMap.containsKey(player)) {
			hashMap.put(player, (long) 4);
		}
	
		long time = hashMap.get(player);
		
		if ( (counter - time) > cooldown) {
			
			ability.run();
			hashMap.put(player, counter);
			
		} else {
			
			player.sendMessage("There is " + (5 - ((counter - time) / 20)) + "s left before you can use this ability. ");
			
		}
		
		ability.setCooldownMap(hashMap);
	
	}
	
	public static void updateCounter() {
		counter += 4;
	}
	
}
