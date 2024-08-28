package io.github.mxylery.testplugin.blueprints;

import org.bukkit.inventory.ItemStack;

//Class for any shop item (and the buy buttons so it's easier to retrieve the price)
public class ShopItem extends ItemStack {
	
	private int price;
	private ItemStack stack;
	
	public ShopItem(ItemStack newStack, int cost) {
		stack = newStack;
		price = cost;
	}
	
	public String toString() {
		return super.toString();
	}
	
	public int getPrice() {
		return price;
	}
	
	public ItemStack getStack() {
		return stack;
	}
	
}
