package io.github.mxylery.testplugin.tasks.abilities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import io.github.mxylery.testplugin.BobuxUtils;
import io.github.mxylery.testplugin.blueprints.BobuxAbility;
import io.github.mxylery.testplugin.blueprints.BobuxRepeatedAbility;

public class KatanaRCAbility extends BobuxRepeatedAbility {

	private ArrayList<Mob> mobList = new ArrayList<Mob>();
	
	public KatanaRCAbility(long cooldown, long delay, long reps) {
		super(cooldown, delay, reps);
		repsToBeDone = reps;
	}

	@Override
	//Must use super.run() method for repetitions to work.
	public void run() {
		
		super.run();
		
		Vector playerEyeVector = player.getEyeLocation().getDirection();
		playerEyeVector.add(new Vector(0.0, 0.1, 0.0));
		
		if (repsToBeDone > 1) {
		
			player.setVelocity(playerEyeVector);
			List<Entity> list;
			list = player.getNearbyEntities(4,3,4);
			player.getWorld().playSound(player, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
			
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					Entity entity = list.get(i);
					Mob mob;
					if (list.get(i) instanceof Mob && !mobList.contains(entity)) {
						mob = (Mob) entity;
						mobList.add(mob);
					}
				}
			}
			
			repsToBeDone--;	
			
		} else {
			
			player.setVelocity(new Vector());
			
			if (mobList != null) {
				for (int i = 0; i < mobList.size(); i++) {	
					Mob mob = mobList.get(i);
					Location mobLoc = mob.getLocation();
					mobLoc.add(0.0, 0.5, 0.0);
					player.getWorld().spawnParticle(Particle.REDSTONE, mobLoc, 10, 0.25, 0.5, 0.25, 0.0, new DustOptions(Color.RED, 3.0f)); 
					mob.damage(20.0);
				}
				mobList.clear();
			} 

			player.getWorld().playSound(player, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.5f, 0.5f) ;
			repsToBeDone = reps;
			
		}
	}
}

