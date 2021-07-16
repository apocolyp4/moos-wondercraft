package tech.obliviondevelop.SF;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import tech.obliviondevelop.SF.Lists.WonderItems;
import tech.obliviondevelop.SF.Lists.WonderMachines;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

public class ElectricHydropress extends AContainer {

	public ElectricHydropress(Category category) {
		super(
				category, 
				new SlimefunItemStack("ELECTRIC_HYDROPRESS", WonderMachines.ELECTRIC_HYDROPRESS), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {WonderItems.CARBONADO_BLOCK, WonderItems.CARBONADO_BLOCK, WonderItems.CARBONADO_BLOCK, WonderItems.POWER_PISTON, WonderItems.POWER_PISTON, WonderItems.POWER_PISTON, SlimefunItems.ELECTRIC_MOTOR, WonderItems.PRINTED_CIRCUIT_BOARD, SlimefunItems.ELECTRIC_MOTOR}
		);
	}

	@Override
	public String getMachineIdentifier() {
		return "ELECTRIC_HYDROPRESS";
	}
	
	@Override
	public int getSpeed() {
		return 1;
	}

	@Override
	public int getEnergyConsumption() {
		return 512;
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 1024;
	}
	
	@Override
	public String getInventoryTitle() {
		return "&bElectric Hydropress";
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.DIAMOND_PICKAXE);
	}

	@Override
	public void registerDefaultRecipes() 
	{
		registerRecipe(3, new ItemStack[] {new CustomItem(SlimefunItems.CARBON_CHUNK)},  new ItemStack[] {new CustomItem(SlimefunItems.OIL_BUCKET)});
	}
		
}
