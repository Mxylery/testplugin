package io.github.mxylery.testplugin.tasks;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.mxylery.testplugin.AbilityManager;

public class AbilityManagerCounterTask extends BukkitRunnable {

	private JavaPlugin plugin;
	
	public AbilityManagerCounterTask(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		AbilityManager.updateCounter();
	}

}
