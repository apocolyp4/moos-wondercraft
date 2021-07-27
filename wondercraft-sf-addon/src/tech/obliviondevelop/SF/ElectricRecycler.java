package tech.obliviondevelop.SF;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;

import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import tech.obliviondevelop.SF.Lists.WonderItems;

public class ElectricRecycler extends AContainer  implements RecipeDisplayItem{
	
	private ItemStack FLINT = null;
	private ItemStack CLAY = null;
	private ItemStack SANDSTONE = null;
	private ItemStack STONE_CHUNK = null;
	private ItemStack COBBLESTONE = null;
	private ItemStack SAND = null;

	
	
	private ItemStack item_stack = new ItemStack(Material.DISPENSER); 
	private String name = "&bElectric Recycler";
	private String description = "&7Electric Version of Recycler";
	private String id = "ELECTRIC_RECYCLER";
	private Setup plugin;	
	private RecipeType ELECTRIC_RECYCLER_Recipe = new RecipeType(new CustomItem(item_stack, name, description), id);
	
	public ElectricRecycler(Category category) {
		super(
				category, 
				new SlimefunItemStack("ELECTRIC_RECYCLER", WonderItems.ELECTRIC_RECYCLER), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.STEEL_PLATE, SlimefunItems.ELECTRIC_PRESS, SlimefunItems.STEEL_PLATE, SlimefunItems.POWER_CRYSTAL, SlimefunItems.ELECTRIC_MOTOR, WonderItems.PRINTED_CIRCUIT_BOARD }
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

	public RecipeType asRecipeType() 
	{
		return ELECTRIC_RECYCLER_Recipe;
	}
	
	@Override
	public String getMachineIdentifier() {
		return "ELECTRIC_RECYCLER";
	}

	@Override
	public String getInventoryTitle() {
		return "&bElectric Recycler";
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.FLINT_AND_STEEL);
	}

	@Override
	public void registerDefaultRecipes() 
	{
		registerRecipe(3, new ItemStack[] {new ItemStack(Material.FLINT, 16)}, new ItemStack[] {new ItemStack(Material.CHARCOAL)});
		registerRecipe(3, new ItemStack[] {new ItemStack(Material.CLAY_BALL, 4)}, new ItemStack[] {new ItemStack(Material.COBBLESTONE)});
		registerRecipe(3, new ItemStack[] {new ItemStack(Material.SANDSTONE)}, new ItemStack[] {new ItemStack(Material.SAND)});
		registerRecipe(3, new ItemStack[] {new CustomItem(SlimefunItems.STONE_CHUNK, 4)}, new ItemStack[] {new ItemStack(Material.SAND)});
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


