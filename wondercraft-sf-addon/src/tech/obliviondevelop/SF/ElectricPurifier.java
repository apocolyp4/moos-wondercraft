package tech.obliviondevelop.SF;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.inventory.ItemStack;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import tech.obliviondevelop.SF.Lists.WonderItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;

public class ElectricPurifier extends AContainer {
	
	// This is for customer head items
	private ItemStack PURE_ORE_CLUSTER = null;
	private ItemStack TITANIUM_DUST = null;
	private ItemStack CHROMIUM_DUST = null;
	private ItemStack DUBNIUM = null;
	private ItemStack STONE_CHUNK = null;
	private ItemStack CLAY_BALL = null;
	
	
	private ItemStack item_stack = new ItemStack(Material.GRAY_TERRACOTTA); 
	private String name = "&bElectric Purifier";
	private String description = "&a&oWash Pure Ore Clusters";
	private String colour = "&7";
	private String id = "ELECTRIC_PURIFIER";
	private Setup plugin;
	public ConsoleCommandSender console;
	
	private RecipeType ELECTRIC_PURIFIER = new RecipeType(new CustomItem(item_stack, name, description), id);
	private  Purifier purifier;
	private final ItemStack pure_ore_cluster = new ItemStack(SlimefunItems.PURE_ORE_CLUSTER);
	
	public ElectricPurifier(Category category, Purifier server_purifier) {
		super(
				category, 
				new SlimefunItemStack("ELECTRIC_PURIFIER", WonderItems.ELECTRIC_PURIFIER), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {SlimefunItems.REINFORCED_PLATE, WonderItems.WONDER_ALLOY, SlimefunItems.REINFORCED_PLATE, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRIC_DUST_WASHER_3, SlimefunItems.ELECTRIC_MOTOR, WonderItems.PRINTED_CIRCUIT_BOARD, SlimefunItems.POWER_CRYSTAL, WonderItems.POWER_CELL}
		);
		try {
			PURE_ORE_CLUSTER = new SlimefunItemStack("PURE_ORE_CLUSTER", SlimefunItems.PURE_ORE_CLUSTER);
			TITANIUM_DUST = new SlimefunItemStack("TITANIUM_DUST", WonderItems.TITANIUM_DUST);
			CHROMIUM_DUST = new SlimefunItemStack("CHROMIUM_DUST", WonderItems.CHROMIUM_DUST);
			DUBNIUM = new SlimefunItemStack("DUBNIUM", WonderItems.DUBNIUM);
			STONE_CHUNK = new SlimefunItemStack("TITANIUM_DUST", SlimefunItems.STONE_CHUNK);
		
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		purifier = server_purifier;
		
	}
	
	public RecipeType asRecipeType() 
	{
		return ELECTRIC_PURIFIER;
	}

	@Override
	public String getMachineIdentifier() {
		return "ELECTRIC_PURIFIER";
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
		return "&bElectric Purifier";
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.FLINT_AND_STEEL);
	}

	@Override
	public void registerDefaultRecipes() {}
	
	   @Override
	    protected MachineRecipe findNextRecipe(BlockMenu menu) {
	        if (!hasFreeSlot(menu)) {
	            return null;
	        }

	        for (int slot : getInputSlots()) {
	            ItemStack item = menu.getItemInSlot(slot);

	            if (SlimefunUtils.isItemSimilar(item, SlimefunItems.PURE_ORE_CLUSTER, true, false)) {
	                ItemStack output = purifier.get_random_item();
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

