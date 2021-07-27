package tech.obliviondevelop.SF;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
//import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;

import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import tech.obliviondevelop.SF.Lists.WonderItems;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class DeepFreeze extends AContainer implements RecipeDisplayItem{
	
	private ItemStack ICE_CREAM = null;
	
	public DeepFreeze(Category category) {
		super(
				category, 
				new SlimefunItemStack("DEEP_FREEZE", WonderItems.DEEP_FREEZE), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.HARDENED_METAL_INGOT, WonderItems.PRINTED_CIRCUIT_BOARD, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.COOLING_UNIT, WonderItems.WONDER_ALLOY, SlimefunItems.HEATING_COIL, SlimefunItems.POWER_CRYSTAL}
		);
		
		try {
			ICE_CREAM = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDQ3YmI3NWJhY2JjMmM4YzA2MTczZmEwYjRiZWZjOTg2Mjc0NWYyMzZiN2I4NmQyMjE0ZTk1ZmNkNmU5MDk3NyJ9fX0="), "&bIce Cream");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes = new ArrayList<>(recipes.size() * 2);

        for (MachineRecipe recipe : recipes) {
            displayRecipes.add(recipe.getInput()[0]);
            displayRecipes.add(recipe.getOutput()[recipe.getOutput().length - 1]);
        }

        return displayRecipes;
    }

	@Override
	public int getSpeed()
	{
		return 1;
	}

	@Override
	public int getEnergyConsumption() {
		return 125;
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 256;
	}
	
	@Override
	public String getMachineIdentifier() {
		return "DEEP_FREEZE";
	}

	@Override
	public String getInventoryTitle() {
		return "&bDeep Freeze";
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.FLINT_AND_STEEL);
	}

	@Override
	public void registerDefaultRecipes() 
	{
		registerRecipe(18, new ItemStack[] {new CustomItem(SlimefunItems.HEAVY_CREAM)}, new ItemStack[] {ICE_CREAM});
	}
	
}
