package io.github.mxylery.testplugin.tasks.abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import io.github.mxylery.testplugin.BobuxUtils;
import io.github.mxylery.testplugin.blueprints.BobuxAbility;

public class MeteorWandAbility extends BobuxAbility {

	public MeteorWandAbility(int cooldown) {
		super(cooldown);
	}

	@Override
	public void run() {
		
		Location blockLoc = BobuxUtils.pseudoRaycast(player, 25.0);
		Vector fireballDirection = new Vector(0.0, -0.5, 0.0);
		Vector zeroVector = new Vector();
		Fireball fireball;
		
		blockLoc.add(0.0, 2.0, 0.0);
		blockLoc.setDirection(zeroVector);
		
		boolean inBlock = false;
		
		for (double i = 0.0; i < 20.5 && inBlock == false; i += 0.5) {
			
			blockLoc.add(0.0, 0.5, 0.0);
			
			if (!blockLoc.getBlock().getType().equals(Material.AIR) && !blockLoc.getBlock().getType().equals(Material.WATER) && !blockLoc.getBlock().getType().equals(Material.SNOW) && !blockLoc.getBlock().getType().equals(Material.LAVA)) {
				
				blockLoc.add(0.0, -0.5, 0.0);
				fireball = player.getWorld().spawn(blockLoc, Fireball.class);
				fireball.setIsIncendiary(true);
				fireball.setYield(8.0f);
				fireball.setVelocity(fireballDirection);
				inBlock = true;
			
			} else if (i == 20.0) {
				
				fireball = player.getWorld().spawn(blockLoc, Fireball.class);
				fireball.setIsIncendiary(true);
				fireball.setYield(6.0f);
				fireball.setVelocity(fireballDirection);
				inBlock = true;
				
			}
		}
		
		
		
	}
}
