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
import tech.obliviondevelop.SF.Lists.WonderItems;
import tech.obliviondevelop.SF.Lists.WonderMachines;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

public class Extruder extends AContainer implements RecipeDisplayItem{
	
	private ItemStack CARBON_FIBRE = null;
	private ItemStack LEAD_FIBRE = null;
	private ItemStack WONDER_FIBRE = null;
	private ItemStack WONDER_ALLOY = null;
	
	public Extruder(Category category) {
		super(
				category, 
				new SlimefunItemStack("EXTRUDER", WonderMachines.EXTRUDER), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {WonderItems.WHEEL, WonderItems.SPINDEL, WonderItems.WHEEL, SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.IRON_TRAPDOOR), SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.HARDENED_GLASS, null, SlimefunItems.HARDENED_GLASS}
		);
		try {

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
	public String getMachineIdentifier() {
		return "EXTRUDER";
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
		return "&bExtruder";
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.SHEARS);
	}

	@Override
	public void registerDefaultRecipes() 
	{
		registerRecipe(3, new ItemStack[] {new CustomItem(SlimefunItems.LEAD_INGOT)}, new ItemStack[] {WonderItems.LEAD_FIBRE});
		registerRecipe(3, new ItemStack[] {new CustomItem(SlimefunItems.CARBON)}, new ItemStack[] {WonderItems.CARBON_FIBRE});
		registerRecipe(3, new ItemStack[] {WonderItems.WONDER_ALLOY}, new ItemStack[] {WonderItems.WONDER_FIBRE});
	}

	
}
