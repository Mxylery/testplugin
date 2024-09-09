package io.github.mxylery.testplugin.tasks.abilities;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Particle.DustOptions;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import io.github.mxylery.testplugin.BobuxUtils;
import io.github.mxylery.testplugin.blueprints.BobuxAbility;

public class TeleportRodAbility extends BobuxAbility {
	
	public TeleportRodAbility(int cooldown) {
		super(cooldown);
	}

	@Override
	public void run() {
		
		Location blockLoc = BobuxUtils.pseudoRaycast(player, 12.0);
		
		player.getWorld().spawnParticle(Particle.REDSTONE, player.getLocation(), 5, 0.5, 0.5, 0.5, 0.0, new DustOptions(Color.PURPLE, 2.0f));
		
		player.teleport(blockLoc);
		
		player.getWorld().spawnParticle(Particle.REDSTONE, player.getLocation(), 5, 0.5, 0.5, 0.5, 0.0, new DustOptions(Color.PURPLE, 2.0f));
		
		player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 0.5f);
		
	}
	
}
