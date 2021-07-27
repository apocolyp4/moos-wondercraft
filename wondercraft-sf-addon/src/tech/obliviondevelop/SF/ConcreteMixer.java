package tech.obliviondevelop.SF;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import tech.obliviondevelop.SF.Lists.WonderItems;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class ConcreteMixer extends AContainer implements RecipeDisplayItem{
	
	public ConcreteMixer(Category category) 
	{
		super(
				category, 
				new SlimefunItemStack("CONCRETE_MIXER", WonderItems.MIXER), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, SlimefunItems.POWER_CRYSTAL, null, SlimefunItems.PLASTIC_SHEET, new ItemStack(Material.WATER_BUCKET), SlimefunItems.PLASTIC_SHEET, SlimefunItems.SYNTHETIC_EMERALD, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.SYNTHETIC_EMERALD}
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

	@Override
	public String getMachineIdentifier() {
		return "CONCRETE_MIXER";
	}

	@Override
	public String getInventoryTitle() {
		return "&3Concrete Mixer";
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.DIAMOND_SHOVEL);
	}

	@Override
	public void registerDefaultRecipes() 
	{
		registerRecipe(5, new ItemStack[] {new ItemStack(Material.RED_CONCRETE_POWDER)},  new ItemStack[] {new ItemStack(Material.RED_CONCRETE)});
		registerRecipe(5, new ItemStack[] {new ItemStack(Material.BLACK_CONCRETE_POWDER)},  new ItemStack[] {new ItemStack(Material.BLACK_CONCRETE)});
		registerRecipe(5, new ItemStack[] {new ItemStack(Material.BLUE_CONCRETE_POWDER)},  new ItemStack[] {new ItemStack(Material.BLUE_CONCRETE)});
		registerRecipe(5, new ItemStack[] {new ItemStack(Material.BROWN_CONCRETE_POWDER)},  new ItemStack[] {new ItemStack(Material.BROWN_CONCRETE)});
		registerRecipe(5, new ItemStack[] {new ItemStack(Material.CYAN_CONCRETE_POWDER)},  new ItemStack[] {new ItemStack(Material.CYAN_CONCRETE)});
		registerRecipe(5, new ItemStack[] {new ItemStack(Material.GRAY_CONCRETE_POWDER)},  new ItemStack[] {new ItemStack(Material.GRAY_CONCRETE)});
		registerRecipe(5, new ItemStack[] {new ItemStack(Material.GREEN_CONCRETE_POWDER)},  new ItemStack[] {new ItemStack(Material.GREEN_CONCRETE)});
		registerRecipe(5, new ItemStack[] {new ItemStack(Material.LIGHT_BLUE_CONCRETE_POWDER)},  new ItemStack[] {new ItemStack(Material.LIGHT_BLUE_CONCRETE)});
		registerRecipe(5, new ItemStack[] {new ItemStack(Material.LIGHT_GRAY_CONCRETE_POWDER)},  new ItemStack[] {new ItemStack(Material.LIGHT_GRAY_CONCRETE)});
		registerRecipe(5, new ItemStack[] {new ItemStack(Material.LIME_CONCRETE_POWDER)},  new ItemStack[] {new ItemStack(Material.LIME_CONCRETE)});
		registerRecipe(5, new ItemStack[] {new ItemStack(Material.MAGENTA_CONCRETE_POWDER)},  new ItemStack[] {new ItemStack(Material.MAGENTA_CONCRETE)});
		registerRecipe(5, new ItemStack[] {new ItemStack(Material.ORANGE_CONCRETE_POWDER)},  new ItemStack[] {new ItemStack(Material.ORANGE_CONCRETE)});
		registerRecipe(5, new ItemStack[] {new ItemStack(Material.PINK_CONCRETE_POWDER)},  new ItemStack[] {new ItemStack(Material.PINK_CONCRETE)});
		registerRecipe(5, new ItemStack[] {new ItemStack(Material.PURPLE_CONCRETE_POWDER)},  new ItemStack[] {new ItemStack(Material.PURPLE_CONCRETE)});
		registerRecipe(5, new ItemStack[] {new ItemStack(Material.YELLOW_CONCRETE_POWDER)},  new ItemStack[] {new ItemStack(Material.YELLOW_CONCRETE)});
		registerRecipe(5, new ItemStack[] {new ItemStack(Material.WHITE_CONCRETE_POWDER)},  new ItemStack[] {new ItemStack(Material.WHITE_CONCRETE)});
	}
	
	
	
	@Override
	public int getSpeed() {
		return 1;
	}

	@Override
	public int getEnergyConsumption() {
		return 10;
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 256;
	}
	
}
