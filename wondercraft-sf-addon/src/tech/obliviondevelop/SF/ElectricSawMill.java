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

public class ElectricSawMill extends AContainer implements RecipeDisplayItem{
	
	public ElectricSawMill(Category category) {
		super(
				category, 
				new SlimefunItemStack("ELECTRIC_SAW_MILL", WonderItems.ELECTRIC_SAW_MILL), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {new ItemStack(Material.IRON_BARS), null, new ItemStack(Material.IRON_BARS), WonderItems.SAW_BLADE, SlimefunItems.ELECTRIC_MOTOR, WonderItems.SAW_BLADE, new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.IRON_BLOCK)}
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
		return "ELECTRIC_SAW_MILL";
	}

	@Override
	public String getInventoryTitle() {
		return "&bElectric Saw Mill";
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.DIAMOND_AXE);
	}
	
	
	@Override
	public int getSpeed() {
		return 420;
	}

	@Override
	public int getEnergyConsumption() 
	{
		return 50;
	}

	@Override
	public int getCapacity() 
	{
		// TODO Auto-generated method stub
		return 128;
	}

	@Override
	public void registerDefaultRecipes() 
	{
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.ACACIA_LOG)}, new ItemStack[] {new ItemStack(Material.ACACIA_PLANKS, 8)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.STRIPPED_ACACIA_LOG)}, new ItemStack[] {new ItemStack(Material.ACACIA_PLANKS, 8)});
		
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.BIRCH_LOG)}, new ItemStack[] {new ItemStack(Material.BIRCH_PLANKS, 8)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.STRIPPED_BIRCH_LOG)}, new ItemStack[] {new ItemStack(Material.BIRCH_PLANKS, 8)});
		
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.OAK_LOG)}, new ItemStack[] {new ItemStack(Material.OAK_PLANKS, 8)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.STRIPPED_OAK_LOG)}, new ItemStack[] {new ItemStack(Material.OAK_PLANKS, 8)});
		
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.JUNGLE_LOG)}, new ItemStack[] {new ItemStack(Material.JUNGLE_PLANKS, 8)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.STRIPPED_JUNGLE_LOG)}, new ItemStack[] {new ItemStack(Material.JUNGLE_PLANKS, 8)});
		
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.DARK_OAK_LOG)}, new ItemStack[] {new ItemStack(Material.DARK_OAK_PLANKS, 8)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.STRIPPED_DARK_OAK_LOG)}, new ItemStack[] {new ItemStack(Material.DARK_OAK_PLANKS, 8)});
		
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.SPRUCE_LOG)}, new ItemStack[] {new ItemStack(Material.SPRUCE_PLANKS, 8)});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.STRIPPED_SPRUCE_LOG)}, new ItemStack[] {new ItemStack(Material.SPRUCE_PLANKS, 8)});
		
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.WARPED_STEM)}, new ItemStack[] {new ItemStack(Material.WARPED_PLANKS, 8)});
		
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.CRIMSON_STEM)}, new ItemStack[] {new ItemStack(Material.CRIMSON_PLANKS, 8)});
		
	}
	

}
