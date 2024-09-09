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

public class KatanaDashAbility extends BobuxAbility {

	private ArrayList<Mob> mobList = new ArrayList<Mob>();
	private long repsToBeDone;
	private BukkitScheduler scheduler;
	
	public KatanaDashAbility(long cooldown, long delay, long reps) {
		super(cooldown, delay, reps);
		repsToBeDone = reps;
	}

	@Override
	public void run() {
		
		scheduler = player.getServer().getScheduler();
		
		if (repsToBeDone == reps) {
			for (int i = 0; i < reps; i++) {
				scheduler.runTaskLater(plugin, this, i*delay);
			}
		}
		
		Vector playerEyeVector = player.getEyeLocation().getDirection();
		playerEyeVector.add(new Vector(0.0, 0.1, 0.0));
		
		if (repsToBeDone > 0) {
		
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

			repsToBeDone = reps;
			
		}
	}
}

