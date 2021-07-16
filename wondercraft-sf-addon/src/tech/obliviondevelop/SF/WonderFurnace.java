package tech.obliviondevelop.SF;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import tech.obliviondevelop.SF.Lists.WonderItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class WonderFurnace extends AContainer {
	
	public WonderFurnace(Category category)
	{
		super(
				category, 
				new SlimefunItemStack("WONDER_FURNACE", WonderItems.WONDER_FURNACE), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {new ItemStack(Material.NETHER_BRICK),new ItemStack(Material.NETHER_BRICK),new ItemStack(Material.NETHER_BRICK),new ItemStack(Material.NETHER_BRICK),null,new ItemStack(Material.NETHER_BRICK),SlimefunItems.HEATING_COIL,SlimefunItems.HEATING_COIL,SlimefunItems.POWER_CRYSTAL}
		);
	}

	
	@Override
	public int getSpeed() {
		return 8;
	}

	@Override
	public int getEnergyConsumption() 
	{
		return 512;
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 2018;
	}
	
	@Override
	public String getMachineIdentifier() {
		return "WONDER_FURNACE";
	}

	@Override
	public String getInventoryTitle() {
		return "&bWonder Furnace";
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.FLINT_AND_STEEL);
	}

	@Override
	public void registerDefaultRecipes() 
	{
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.ACACIA_LOG)}, new ItemStack[] {new ItemStack(Material.ACACIA_PLANKS, 8)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.STRIPPED_ACACIA_LOG)}, new ItemStack[] {new ItemStack(Material.CHARCOAL)});
		
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.BIRCH_LOG)}, new ItemStack[] {new ItemStack(Material.CHARCOAL)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.STRIPPED_BIRCH_LOG)}, new ItemStack[] {new ItemStack(Material.CHARCOAL)});
		
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.OAK_LOG)}, new ItemStack[] {new ItemStack(Material.CHARCOAL)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.STRIPPED_OAK_LOG)}, new ItemStack[] {new ItemStack(Material.CHARCOAL)});
		
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.JUNGLE_LOG)}, new ItemStack[] {new ItemStack(Material.CHARCOAL)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.STRIPPED_JUNGLE_LOG)}, new ItemStack[] {new ItemStack(Material.CHARCOAL)});
		
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.DARK_OAK_LOG)}, new ItemStack[] {new ItemStack(Material.CHARCOAL)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.STRIPPED_DARK_OAK_LOG)}, new ItemStack[] {new ItemStack(Material.CHARCOAL)});
		
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.SPRUCE_LOG)}, new ItemStack[] {new ItemStack(Material.CHARCOAL)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.STRIPPED_SPRUCE_LOG)}, new ItemStack[] {new ItemStack(Material.CHARCOAL)});
		
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.SAND)}, new ItemStack[]  {new ItemStack(Material.GLASS)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.RAW_IRON)}, new ItemStack[]  {new ItemStack(Material.IRON_INGOT)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.RAW_GOLD)}, new ItemStack[]  {new ItemStack(Material.GOLD_INGOT)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.RAW_COPPER)}, new ItemStack[]  {new ItemStack(Material.COPPER_INGOT)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.COBBLESTONE)}, new ItemStack[]  {new ItemStack(Material.STONE)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.BEEF)}, new ItemStack[]  {new ItemStack(Material.COOKED_BEEF)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.MUTTON)}, new ItemStack[]  {new ItemStack(Material.COOKED_MUTTON)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.COD)}, new ItemStack[]  {new ItemStack(Material.COOKED_COD)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.SALMON)}, new ItemStack[]  {new ItemStack(Material.COOKED_SALMON)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.RABBIT)}, new ItemStack[]  {new ItemStack(Material.COOKED_RABBIT)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.POTATO)}, new ItemStack[]  {new ItemStack(Material.BAKED_POTATO)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.CLAY_BALL)}, new ItemStack[]  {new ItemStack(Material.BRICK)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.NETHERRACK)}, new ItemStack[]  {new ItemStack(Material.NETHER_BRICK)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.CACTUS)}, new ItemStack[]  {new ItemStack(Material.GREEN_DYE)});
	}
	

}
