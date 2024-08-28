package io.github.mxylery.testplugin.enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum ShopPrices {
	COAL (new ItemStack(Material.COAL), 1),
	IRON_INGOT (new ItemStack(Material.IRON_INGOT), 1),
	GOLD_INGOT (new ItemStack(Material.GOLD_INGOT), 1),
	DIAMOND (new ItemStack(Material.DIAMOND), 5),
	NETHERITE_INGOT (new ItemStack(Material.NETHERITE_INGOT), 10);

	public final Integer price;
	public final ItemStack stack;

	ShopPrices(ItemStack stack, Integer price) {
	
		this.price = price;
		this.stack = stack;
		
	}
}
