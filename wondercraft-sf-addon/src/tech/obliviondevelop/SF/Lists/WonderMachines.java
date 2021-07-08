package tech.obliviondevelop.SF.Lists;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;

public class WonderMachines {
	public static ItemStack OVEN = new CustomItem(Material.DISPENSER, "&bOven", "", "&a&oOven");
	public static ItemStack CORAL_CRUSHER = new CustomItem(Material.DISPENSER, "&bCoral Crusher", "", "&a&oCrush Coral");
	public static ItemStack WONDER_MACHINE = new CustomItem(new ItemStack(Material.DISPENSER), "&bWonder &bMachine", "&7Do magical stuff with this");
	public static ItemStack EXTRUDER = new CustomItem(new ItemStack(Material.ORANGE_STAINED_GLASS), "&bExtruder", "&7Mix, shape, and cast items");
	public static ItemStack PURIFIER = new CustomItem(new ItemStack(Material.DISPENSER), "&bPurifier", "&7Wash Pure Ore Clusters");
	public static ItemStack ELECTRIC_HYDROPRESS = new CustomItem(new ItemStack(Material.PISTON), "&bElectric Hydropress", "&7Advanced oil generator");
	public static ItemStack ELECTRIC_SAW_MILL = new CustomItem(new ItemStack(Material.OAK_WOOD), "&bElectric Saw Mill", "&7Convert logs to planks");
	public static ItemStack WONDER_FURNACE = new CustomItem(new ItemStack(Material.FURNACE), "&bWonder Furnace", "&7No More Messy Fuel");
	public static ItemStack FRYER = new CustomItem(new ItemStack(Material.BONE_BLOCK), "&bFryer", "&7Deep fry tasty foods");
	public static ItemStack DEEP_FREEZE = new CustomItem(new ItemStack(Material.CYAN_CONCRETE_POWDER), "&bDeep Freeze", "&7It's like the Artic inside");
	public static ItemStack CANNARY = new CustomItem(new ItemStack(Material.GRAY_TERRACOTTA), "&bCannary", "&7Put Items Into Tin Cans");
	public static ItemStack RECYCLER = new CustomItem(new ItemStack(Material.DISPENSER), "&bRecycler", "&7Re-use your SF Junk");
	public static ItemStack COMPACTOR = new CustomItem(new ItemStack(Material.PISTON), "&bCompactor", "&7Re-Forge Ore");
	public static ItemStack DESALINATION_PLANT = new CustomItem(new ItemStack(Material.LIGHT_BLUE_CONCRETE_POWDER), "&bDesalination Plant", "&7Extract Salt from Water");
	public static ItemStack ELECTRIC_PURIFIER = new CustomItem(new ItemStack(Material.PURPLE_STAINED_GLASS), "&bElectric Purifier", "&7Cleans Pure Ore Clusters");
	public static ItemStack ELECTRIC_PURIFIER_II = new CustomItem(new ItemStack(Material.PURPLE_STAINED_GLASS), "&bElectric Purifier II", "&7Cleans Pure Ore Clusters", "&7Less Waste By-products");
}
