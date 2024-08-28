package io.github.mxylery.testplugin.enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum BountyItems {
	
	BROWN_MUSHROOM (1, 8, 16, new ItemStack(Material.BROWN_MUSHROOM)),
	CARROT (1, 16, 32, new ItemStack(Material.CARROT)),
	DIAMOND (3, 2, 4, new ItemStack(Material.DIAMOND)),
	ENDER_PEARL (2, 2, 5, new ItemStack(Material.ENDER_PEARL)),
	GLASS (1,16,32, new ItemStack(Material.GLASS)),
	GOLD_INGOT (2, 6, 10, new ItemStack(Material.GOLD_INGOT)),
	HEART_OF_THE_SEA (5, 1, 1, new ItemStack(Material.HEART_OF_THE_SEA)),
	LAPIS_LAZULI (2, 6, 10, new ItemStack(Material.LAPIS_LAZULI)),
	LEATHER (2, 6, 10, new ItemStack(Material.LEATHER)),
	MUTTON (1, 8, 16, new ItemStack(Material.MUTTON)),
	NAUTILUS_SHELL (3, 1, 2, new ItemStack(Material.NAUTILUS_SHELL)),
	NETHERITE_INGOT (8, 1, 1, new ItemStack(Material.NETHERITE_INGOT)),
	OAK_LOG (1, 16, 32, new ItemStack(Material.OAK_LOG)),
	POTATO (1, 16, 32, new ItemStack(Material.POTATO)),
	RED_MUSHROOM (1, 8, 16, new ItemStack(Material.RED_MUSHROOM)),
	REDSTONE_BLOCK (1, 2, 4, new ItemStack(Material.REDSTONE_BLOCK)),
	ROTTEN_FLESH (1, 12, 24, new ItemStack(Material.ROTTEN_FLESH)),
	STEAK (2, 8, 16, new ItemStack(Material.COOKED_BEEF)),
	WHEAT (2, 16, 32, new ItemStack(Material.WHEAT)),
	WOOL (2, 8, 16, new ItemStack(Material.WHITE_WOOL));

	public int bobux;
	public int min;
	public int max;
	public ItemStack stack;

	BountyItems(int bobux, int min, int max, ItemStack stack) {
	
		this.bobux = bobux;
		this.stack = stack;
		
		if (min < max) {
		this.min = min;
		this.max = max;
		} else {
		this.min = max;
		this.max = min;
		}
	
}

}
