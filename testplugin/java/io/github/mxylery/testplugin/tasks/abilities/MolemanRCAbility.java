package io.github.mxylery.testplugin.tasks.abilities;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.util.Vector;

import io.github.mxylery.testplugin.BobuxUtils;
import io.github.mxylery.testplugin.blueprints.BobuxRepeatedAbility;

public class MolemanRCAbility extends BobuxRepeatedAbility {
	
	public MolemanRCAbility(long cooldown, long delay, long reps) {
		super(cooldown, delay, reps);
		repsToBeDone = reps;
	}
	
	@Override
	public void run() {
		
		super.run();
		
		Vector playerEyeVector = player.getEyeLocation().getDirection();
		Location playerLoc = player.getLocation();
		
		ArrayList<Block> blockList = BobuxUtils.getNearbyBlocks(playerLoc, 4, 4, 4);
		Location blockLoc;
		BlockData blockData;
		Block block;
		
		player.getServer().broadcastMessage("Block List size: " + blockList.size());
		
		if (repsToBeDone > 1) {
			
			player.setVelocity(playerEyeVector);
			
			player.playSound(player, Sound.ITEM_SHIELD_BREAK, 1.0f, 1.0f);
			
			for (int i = 0; i < blockList.size(); i++) {
				
				block = blockList.get(i);
				block.setType(Material.AIR);
				blockLoc = block.getLocation();
				blockData = block.getBlockData();
				
				block.getWorld().setBlockData(blockLoc, blockData);
				
			}
			
			repsToBeDone--;
			
		} else {
			
			player.setVelocity(new Vector());
			
			repsToBeDone = reps;
			
		}
		
	}

}
