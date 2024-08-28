package io.github.mxylery.testplugin.blueprints;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.mxylery.testplugin.enums.BountyItems;

/* This class is made for the bounty system, which generates a bounty
 *  (involving 3 item stacks and a bobux prize) upon instantiating using the BountyItems enum.
 */
public class Bounty {
	
	//BountyItems properties
	private int max;
	private int min;
	private int bobux = 0;
	private ItemStack stack;
	
	private final int IDS = 20;
	BountyItems item;
	
	//Other stuff
	private ArrayList<ItemStack> bounty = new ArrayList<ItemStack>();
	private int rng;
	
	public Bounty() {
		
		for (int i = 0; i < 3; i++) {
			
			rng = (int) (Math.random()*IDS);
			
			switch (rng) {
				case 0: item = BountyItems.BROWN_MUSHROOM;
				break;
				case 1: item = BountyItems.CARROT;
				break;
				case 2: item = BountyItems.DIAMOND;
				break;
				case 3: item = BountyItems.ENDER_PEARL;
				break;
				case 4: item = BountyItems.GLASS;
				break;
				case 5: item = BountyItems.GOLD_INGOT;
				break;
				case 6: item = BountyItems.HEART_OF_THE_SEA;
				break;
				case 7: item = BountyItems.LAPIS_LAZULI;
				break;
				case 8: item = BountyItems.LEATHER;
				break;
				case 9: item = BountyItems.MUTTON;
				break;
				case 10: item = BountyItems.NAUTILUS_SHELL;
				break;
				case 11: item = BountyItems.NETHERITE_INGOT;
				break;
				case 12: item = BountyItems.OAK_LOG;
				break;
				case 13: item = BountyItems.POTATO;
				break;
				case 14: item = BountyItems.RED_MUSHROOM;
				break;
				case 15: item = BountyItems.REDSTONE_BLOCK;
				break;
				case 16: item = BountyItems.ROTTEN_FLESH;
				break;
				case 17: item = BountyItems.STEAK;
				break;
				case 18: item = BountyItems.WHEAT;
				break;
				case 19: item = BountyItems.WOOL;
				break;
			}
			
			bobux += item.bobux;
			stack = item.stack;
			min = item.min;
			max = item.max;
			
			int amount = (int) (min + Math.random()*max);
			stack.setAmount(amount);
			bounty.add(stack);
			
		}
		
	}
	
	public int getBobux() {
		return bobux;
	}
	
	public ItemStack[] getStacks() {
		ItemStack[] stackArray = {bounty.get(0), bounty.get(1), bounty.get(2)};
		return stackArray;
	}
	


}
