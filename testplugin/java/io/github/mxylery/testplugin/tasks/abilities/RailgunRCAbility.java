package io.github.mxylery.testplugin.tasks.abilities;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import io.github.mxylery.testplugin.BobuxUtils;
import io.github.mxylery.testplugin.blueprints.BobuxAbility;

public class RailgunRCAbility extends BobuxAbility {
	
	public RailgunRCAbility(long cooldown) {
		super(cooldown);
	}
	
	@Override
	public void run() {
		
		Vector playerEyeVector = player.getEyeLocation().getDirection();
		
		Double xCoord;
		Double yCoord;
		Double zCoord;
		Double adjxCoord;
		Double adjyCoord;
		Double adjzCoord;
		
		for (double i = 0; i < 40; i += 0.5) {
			
			xCoord = BobuxUtils.getPlayerEyeCoordinate(player, i, 'x');
			yCoord = BobuxUtils.getPlayerEyeCoordinate(player, i, 'y');
			zCoord = BobuxUtils.getPlayerEyeCoordinate(player, i, 'z');
			
			adjxCoord = playerEyeVector.getY()*Math.cos(i);
			adjzCoord = -playerEyeVector.getY()*Math.sin(i);
			adjyCoord = playerEyeVector.getZ();
			
			player.getWorld().spawnParticle(Particle.CRIT, xCoord, yCoord, zCoord, 1, 0, 0, 0);
			player.getWorld().spawnParticle(Particle.REDSTONE, xCoord + 0.5*adjxCoord, yCoord + 0.5*adjyCoord, zCoord + 0.5*adjzCoord, 1, 0, 0, 0, 0.0, new DustOptions(Color.RED, 2.0f));
			player.getWorld().spawnParticle(Particle.REDSTONE, xCoord - 0.5*adjxCoord, yCoord - 0.5*adjyCoord, zCoord - 0.5*adjzCoord, 1, 0, 0, 0, 0.0, new DustOptions(Color.RED, 2.0f));
			
			player.playSound(player, Sound.ENTITY_GENERIC_EXPLODE, 0.04f, 1.0f);
		
		}
		
		List<?> entityList = player.getNearbyEntities(40, 40, 40);
		
		Mob mob;
		
		if (entityList.size() > 0) {
			
			for (int i = 0; i < entityList.size(); i++) {
				
				if (entityList.get(i) instanceof Mob) {
					
					mob = (Mob) entityList.get(i);
					
					if (BobuxUtils.entityFromCrosshair(player, mob).length() < 1) {
						
						player.getServer().broadcastMessage( entityList.get(i) + " " + BobuxUtils.entityFromCrosshair(player, mob).length());
					
						mob.damage(10);
						
					}
					
				}
			
			}
			
		}
		
	}

	
	
}
