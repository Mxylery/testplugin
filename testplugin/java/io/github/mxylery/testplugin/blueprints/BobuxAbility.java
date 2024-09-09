package io.github.mxylery.testplugin.blueprints;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public abstract class BobuxAbility implements Runnable {

	protected JavaPlugin plugin; 
	protected HashMap<Player, Long> hashMap = new HashMap<Player, Long>();
	protected Player player;
	protected long timePassed = 0;
	protected long cooldown;
	protected long delay;
	protected long reps;
	protected BukkitTask task;
	
	//Constructor
	
	public BobuxAbility(long cooldown) {
		this.cooldown = cooldown;
		this.delay = 4;
		this.reps = 1;
	}
	
	public BobuxAbility(long cooldown, long delay, long reps) {
		this.cooldown = cooldown;
		this.delay = delay;
		this.reps = reps;
	}
	
	//Mutators
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void setTime(long timePassed) {
		this.timePassed = timePassed;
	}
	
	public void setCooldownMap(HashMap<Player, Long> hashMap) {
		this.hashMap = hashMap;
	}

	public void setPlugin (JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void setTask (BukkitTask task) {
		this.task = task;
	}
	
	//Accessors
	
	public HashMap<Player, Long> getCooldownMap() {
		return hashMap;
	}

	public long getCooldown() {
		return cooldown;
	}
	
	public long getTime() {
		return timePassed;
	}
	
	public long getDelay() {
		return delay;
	}
	
	public long getReps() {
		return reps;
	}

	public JavaPlugin getPlugin() {
		return plugin;
	}
	
	public BukkitTask getTask() {
		return task;
	}
	
	public abstract void run();
	
}
