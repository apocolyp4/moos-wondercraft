package tech.obliviondevelop.SF;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.inventory.ItemStack;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import tech.obliviondevelop.SF.Lists.WonderItems;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;

public class ElectricPurifierII extends AContainer implements RecipeDisplayItem{
	
	// This is for customer head items
	private ItemStack PURE_ORE_CLUSTER = null;
	private ItemStack TITANIUM_DUST = null;
	private ItemStack CHROMIUM_DUST = null;
	private ItemStack DUBNIUM = null;
	private ItemStack STONE_CHUNK = null;
	private ItemStack CLAY_BALL = null;
	
	
	private ItemStack item_stack = new ItemStack(Material.GRAY_TERRACOTTA); 
	private String name = "&bElectric Purifier II";
	private String description = "&a&oWash Pure Ore Clusters, less waste";
	private String colour = "&7";
	private String id = "ELECTRIC_PURIFIER_II";
	private Setup plugin;
	public ConsoleCommandSender console;
	private final ItemStack pure_ore_cluster = new ItemStack(SlimefunItems.PURE_ORE_CLUSTER);
	
	private RecipeType ELECTRIC_PURIFIER_II = new RecipeType(new CustomItem(item_stack, name, description), id);
	
	public ElectricPurifierII(Category category) {
		super(
				category, 
				new SlimefunItemStack("ELECTRIC_PURIFIER_II", WonderItems.ELECTRIC_PURIFIER_II), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {SlimefunItems.REINFORCED_PLATE, WonderItems.WONDER_ALLOY, SlimefunItems.REINFORCED_PLATE, SlimefunItems.ELECTRIC_MOTOR, WonderItems.ELECTRIC_PURIFIER, SlimefunItems.ELECTRIC_MOTOR, WonderItems.PRINTED_CIRCUIT_BOARD, SlimefunItems.POWER_CRYSTAL, WonderItems.POWER_CELL}
		);
		try {
			PURE_ORE_CLUSTER = new SlimefunItemStack("PURE_ORE_CLUSTER", SlimefunItems.PURE_ORE_CLUSTER);
			TITANIUM_DUST = new SlimefunItemStack("TITANIUM_DUST", WonderItems.TITANIUM_DUST);
			CHROMIUM_DUST = new SlimefunItemStack("CHROMIUM_DUST", WonderItems.CHROMIUM_DUST);
			DUBNIUM = new SlimefunItemStack("DUBNIUM", WonderItems.DUBNIUM);
			STONE_CHUNK = new SlimefunItemStack("TITANIUM_DUST", SlimefunItems.STONE_CHUNK);
		
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
		return ELECTRIC_PURIFIER_II;
	}

	@Override
	public String getMachineIdentifier() {
		return "ELECTRIC_PURIFIER_II";
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
	public String getInventoryTitle() {
		return "&bElectric Purifier II";
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.FLINT_AND_STEEL);
	}

	public ItemStack get_random_item()
	{
		ItemStack adding = new ItemStack(Material.GRAVEL);
		Random r = new Random();
		int chance =  r.nextInt(100); // get random number to pick which dust to give
		
		if (chance >= 0 && chance <= 30) 
		{
			adding = WonderItems.TITANIUM_DUST;
		}
		else if (chance >= 31 && chance <= 60)
		{
			adding = WonderItems.CHROMIUM_DUST;
		}
		else if (chance >= 61 && chance <= 90)
		{
			adding = WonderItems.DUBNIUM;
		}
		else if (chance >= 91 && chance <= 100)
		{
			adding = new ItemStack(Material.GRAVEL);
		}
		
		return adding;
	}
	
	@Override
	public void registerDefaultRecipes() {}
	
	   @Override
	    protected MachineRecipe findNextRecipe(BlockMenu menu) {
	        if (!hasFreeSlot(menu)) 
	        {
	            return null;
	        }

	        for (int slot : getInputSlots()) {
	            ItemStack item = menu.getItemInSlot(slot);

	            if (SlimefunUtils.isItemSimilar(item, SlimefunItems.PURE_ORE_CLUSTER, true, false)) {
	                ItemStack output = get_random_item();
	                MachineRecipe recipe = new MachineRecipe(5 / getSpeed(), new ItemStack[] { pure_ore_cluster }, new ItemStack[] { output });

	                if (output.getType() != Material.AIR && menu.fits(output, getOutputSlots())) {
	                    menu.consumeItem(slot);
	                    return recipe;
	                }
	            }
	        }

	        return null;
	    }

	    private boolean hasFreeSlot(@Nonnull BlockMenu menu) {
	        for (int slot : getOutputSlots()) {
	            if (menu.getItemInSlot(slot) == null) {
	                return true;
	            }
	        }

	        return false;
	    }
}
