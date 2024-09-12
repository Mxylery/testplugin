package io.github.mxylery.testplugin.tasks.abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.testplugin.blueprints.BobuxAbility;
import io.github.mxylery.testplugin.blueprints.BobuxRepeatedAbility;

public class ExcavatorRCAbility extends BobuxAbility {

	private Location centerBlockLoc;
	
	public ExcavatorRCAbility(long cooldown) {
		super(cooldown);
	}

	public void setLoc(Location loc) {
		centerBlockLoc = loc;
	}
	
	@Override
	public void run() {
		
		Block[][][] blocks = new Block[5][5][5];
		
		Block block = centerBlockLoc.getBlock();
		
		Location loc; 
		BlockData blockData;
		
		for (int i = 0; i < 5; i++) {
			
			for (int j = 0; j < 5; j++) {
				
				for (int h = 0; h < 5; h++) {
					
					loc = block.getLocation();
				
					loc.add(2 - j, 2 - i, 2 - h);
				
					blocks[i][j][h] = block;
					block.setType(Material.AIR);
					blockData = block.getBlockData();
					player.getWorld().setBlockData(loc, blockData);
					
					}
				}	
			}
		
		player.playSound(player, Sound.ENTITY_GENERIC_EXPLODE, 0.1f, 1.0f);
		
	}

}
