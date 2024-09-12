package io.github.mxylery.testplugin.blueprints;

import org.bukkit.scheduler.BukkitScheduler;

//All repeated abilities must use super.run() in their first line of the child's run() method for the repitions to work.
public abstract class BobuxRepeatedAbility extends BobuxAbility {

	protected BukkitScheduler scheduler;
	protected long repsToBeDone;
	
	public BobuxRepeatedAbility(long cooldown, long delay, long reps) {
		super(cooldown, delay, reps);
		repsToBeDone = reps;
	}
	
	@Override
	public void run() {
		
		scheduler = player.getServer().getScheduler();
		
		if (repsToBeDone == reps) {
			for (int i = 1; i < reps; i++) {
				scheduler.runTaskLater(plugin, this, delay*i);
			}
		}
		
	}

}
