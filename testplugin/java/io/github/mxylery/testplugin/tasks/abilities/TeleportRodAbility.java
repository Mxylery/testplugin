package io.github.mxylery.testplugin.tasks.abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import io.github.mxylery.testplugin.BobuxUtils;
import io.github.mxylery.testplugin.blueprints.BobuxAbility;

public class TeleportRodAbility extends BobuxAbility {
	
	public TeleportRodAbility(int cooldown) {
		super(cooldown);
	}

	@Override
	public void run() {
		
		Vector playerEyeVector = player.getEyeLocation().getDirection();
		
		Location playerLoc = player.getLocation();
		
		Location blockLoc;
		
		boolean inBlock = true;
		
		for (double i = 8.0; i > 0.0 && inBlock == true; i -= 0.5) {
			
			blockLoc = BobuxUtils.getPlayerEyeCoordinate(player, i);
			
			if (blockLoc.getBlock().getType().equals(Material.AIR)) {
				
				player.teleport(blockLoc);
				
				inBlock = false;
				
			} 
			
		}
		
	}
	
}
