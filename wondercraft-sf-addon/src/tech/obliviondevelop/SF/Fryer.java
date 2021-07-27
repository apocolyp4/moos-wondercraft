package tech.obliviondevelop.SF;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.misc.OrganicFood;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import tech.obliviondevelop.SF.Lists.WonderItems;

public class Fryer extends AContainer implements RecipeDisplayItem
{
	
	private ItemStack DOUGH = null;
	private ItemStack DONUT = null;
	private ItemStack RAW_FRIES = null;
	private ItemStack MOOS_FRIES = null;
	
	
	private ItemStack item_stack = new ItemStack(Material.DISPENSER); 
	private String name = "&bFryer";
	private String description = "&7Fry Things";
	private String id = "FRYER";
	private Setup plugin;	
	private RecipeType FRYER_Recipe = new RecipeType(new CustomItem(item_stack, name, description), id);

	public Fryer(Category category) 
	{
		super(
				category, 
				new SlimefunItemStack("FRYER", WonderItems.FRYER), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {SlimefunItems.REINFORCED_PLATE, SlimefunItems.BUTTER, SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_PLATE, SlimefunItems.BUTTER, SlimefunItems.REINFORCED_PLATE, WonderItems.PRINTED_CIRCUIT_BOARD, SlimefunItems.HEATING_COIL, SlimefunItems.POWER_CRYSTAL }
		);
		

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
	
	public RecipeType asRecipeType() 
	{
		return FRYER_Recipe;
	}
	
	@Override
	public String getMachineIdentifier() {
		return "FRYER";
	}

	@Override
	public String getInventoryTitle() {
		return "&bFryer";
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.FLINT_AND_STEEL);
	}

	@Override
	public void registerDefaultRecipes() 
	{
		//registerRecipe(8, new ItemStack[] {WonderItems.RAW_FRIES}, new ItemStack[] { WonderItems.MOOS_FRIES });
		//registerRecipe(8, new ItemStack[] {WonderItems.DOUGH}, new ItemStack[] { WonderItems.DONUT});
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
