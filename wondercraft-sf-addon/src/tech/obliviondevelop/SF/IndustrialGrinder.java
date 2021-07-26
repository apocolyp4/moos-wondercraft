package tech.obliviondevelop.SF;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import tech.obliviondevelop.SF.Lists.WonderItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

public class IndustrialGrinder extends AContainer 
{
	
	private ItemStack NETHERITE_DUST = null;
	
	private ItemStack NETHERITE_SCRAP = new ItemStack(Material.NETHERITE_SCRAP);
	
	public IndustrialGrinder(Category category) {
		super(
				category, 
				new SlimefunItemStack("INDUSTRIAL_GRINDER", WonderItems.INDUSTRIAL_GRINDER), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {SlimefunItems.POWER_CRYSTAL, SlimefunItems.ELECTRIC_MOTOR, WonderItems.PRINTED_CIRCUIT_BOARD, null, WonderItems.DIAMOND_EDGED_CUTTER, null, SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.HARDENED_METAL_INGOT}
		);
		try 
		{
			NETHERITE_DUST = new SlimefunItemStack("NETHERITE_DUST", WonderItems.NETHERITE_DUST);
		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	

	@Override
	public String getMachineIdentifier() {
		return "INDUSTRIAL_GRINDER";
	}

	@Override
	public String getInventoryTitle() {
		return "&bIndustrial Grinder";
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.NETHERITE_PICKAXE);
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

	@Override
	public void registerDefaultRecipes() 
	{
		registerRecipe(15, new ItemStack[] {NETHERITE_SCRAP},  new ItemStack[] {NETHERITE_DUST});
	}
	
}
