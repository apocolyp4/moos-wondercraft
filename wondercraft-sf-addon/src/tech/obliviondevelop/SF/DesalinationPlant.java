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

public class DesalinationPlant extends AContainer 
{
		
	public DesalinationPlant(Category category) {
		super(
				category, 
				new SlimefunItemStack("DESALINATION_PLANT", WonderItems.DESALINATION_PLANT), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {SlimefunItems.REINFORCED_PLATE, SlimefunItems.HEATING_COIL, SlimefunItems.REINFORCED_PLATE, SlimefunItems.HEATING_COIL, SlimefunItems.CLOTH, SlimefunItems.HEATING_COIL, WonderItems.PRINTED_CIRCUIT_BOARD, SlimefunItems.HEATING_COIL, SlimefunItems.POWER_CRYSTAL}
		);
	}

	@Override
	public String getMachineIdentifier() {
		return "Desalination_Plant";
	}
	
	@Override
	public String getInventoryTitle() {
		return "&bDesalination Plant";
	}

	
	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.FLINT_AND_STEEL);
	}

	@Override
	public void registerDefaultRecipes() 
	{
		registerRecipe(8, new ItemStack[] {new ItemStack(Material.WATER_BUCKET)},  new ItemStack[] {new CustomItem(SlimefunItems.SALT, 32)});
	}
	

	
	@Override
	public int getSpeed() {
		return 1;
	}

	@Override
	public int getEnergyConsumption() {
		return 128;
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 256;
	}
	

}
