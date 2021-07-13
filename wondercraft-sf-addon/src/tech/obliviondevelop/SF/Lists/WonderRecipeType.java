package tech.obliviondevelop.SF.Lists;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import tech.obliviondevelop.SF.CustomItem;

public class WonderRecipeType {
	public static RecipeType CORAL_CRUSHER = new RecipeType(new CustomItem(Material.DISPENSER, "&bCoral Crusher", "", "&a&oCrush Coral"), "CORAL_CRUSHER");
	public static RecipeType WONDER_MACHINE = new RecipeType(new CustomItem(new ItemStack(Material.DISPENSER), "&bWonder &bMachine", "&7Do magical stuff with this"), "WONDER_MACHINE");
	public static RecipeType EXTRUDER = new RecipeType(new CustomItem(new ItemStack(Material.ORANGE_STAINED_GLASS), "&bExtruder", "&7Mix, shape, and cast items"), "CORAL_CRUSHER");
	public static RecipeType PURIFIER = new RecipeType(new CustomItem(new ItemStack(Material.DISPENSER), "&bPurifier", "&7Wash Pure Ore Clusters"), "PURIFIER");
	public static RecipeType FRYER = new RecipeType(new CustomItem(new ItemStack(Material.BONE_BLOCK), "&bFryer", "&7Deep Fry Tasty Food!"), "FRYER");
	public static RecipeType DEEP_FREEZE = new RecipeType(new CustomItem(new ItemStack(Material.CYAN_CONCRETE_POWDER), "&bDeep Freeze", "&7It's like the Artic inside"), "DEEP_FREEZE");
	public static RecipeType ELECTRIC_SAW_MILL = new RecipeType(new CustomItem(new ItemStack(Material.OAK_WOOD), "&bElectric Saw Mill", "&7Convert logs to planks"), "ELECTRIC_SAW_MILL");
	public static RecipeType WONDER_FURNACE = new RecipeType(new CustomItem(new ItemStack(Material.FURNACE), "&bWonder Furnace", "&7No More Messy Fuel"), "WONDER_FURNACE");
	public static RecipeType CANNARY = new RecipeType(new CustomItem(new ItemStack(Material.BLACK_TERRACOTTA), "&bCannary", "&7Put Items Into Tin Cans"), "CANNARY");
	public static RecipeType RECYCLER = new RecipeType(new CustomItem(new ItemStack(Material.DISPENSER), "&bRecycler", "&7Re-use your SF Junk"), "RECYCLER");
	public static RecipeType ELECTRIC_RECYCLER = new RecipeType(new CustomItem(new ItemStack(Material.OBSERVER), "&bElectric Recycler", "&7Electric Recycler Machine", "&7Repurpose Slimefun junk"),"ELECTRIC_RECYCLER");
	public static RecipeType COMPACTOR = new RecipeType(new CustomItem(new ItemStack(Material.DISPENSER), "&bCompactor", "&7Re-Forge Ore"), "COMPACTOR");
	public static RecipeType DESALINATION_PLANT = new RecipeType(new CustomItem(new ItemStack(Material.LIGHT_BLUE_CONCRETE_POWDER), "&bDesalination Plant", "&7Extract Salt from Water"), "DESALINATION_PLANT");
	public static RecipeType SMALL_HADRON_COLLIDER = new RecipeType(new CustomItem(new ItemStack(Material.GLOWSTONE), "&bSmall Hadron Collider", "&7Smash Uranium & Plutonium", "&7Make a new element.", "&7Energy intensive, may make blackholes."), "SMALL_HADRON_COLLIDER");
	public static RecipeType CARBON_COLLECTOR = new RecipeType(new CustomItem(new ItemStack(Material.COAL_BLOCK), "&bCarbon Collector", "&7Use Mooinium to react with air", "&7to produce 32 carbon"), "CARBON_COLLECTOR");
	public static RecipeType SMR_BETA = new RecipeType(new CustomItem(Material.BEACON, "&bSMR &cBETA", "&bSpacial Matter Reformation", "&rFormulate matter from nothing"), "SMR_BETA");
	public static RecipeType ELECTRIC_PURIFIER = new RecipeType(new CustomItem(Material.PURPLE_STAINED_GLASS, "&bElectric Purifier", "&7Cleans Pure Ore Clusters"), "ELECTRIC_PURIFIER");
	public static RecipeType ELECTRIC_PURIFIER_II = new RecipeType(new CustomItem(Material.PURPLE_STAINED_GLASS, "&bElectric Purifier II", "&7Cleans Pure Ore Clusters", "&7Less Waste By Products"), "ELECTRIC_PURIFIER");
	
}
