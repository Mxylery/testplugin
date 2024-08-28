package io.github.mxylery.testplugin.blueprints;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class BobuxAbility extends BukkitRunnable {

	protected HashMap<Player, Long> hashMap = new HashMap<Player, Long>();
	protected Player player;
	protected long cooldown;
	protected long timePassed = 0;
	
	//Constructor
	
	public BobuxAbility(long cooldown) {
		this.cooldown = cooldown;
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
	
	public abstract void run();
	
}
