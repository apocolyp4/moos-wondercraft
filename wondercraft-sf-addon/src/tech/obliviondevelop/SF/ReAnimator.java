package tech.obliviondevelop.SF;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import tech.obliviondevelop.SF.Lists.WonderItems;

public class ReAnimator extends AContainer implements RecipeDisplayItem  
{
	// This is for customer head items
	private ItemStack COW_SPAWN_EGG = null;
	private ItemStack PIG_SPAWN_EGG = null;
	private ItemStack SHEEP_SPAWN_EGG = null;
	private ItemStack WOLF_SPAWN_EGG = null;
	private ItemStack SQUID_SPAWN_EGG = null;
	private ItemStack BEE_SPAWN_EGG = null;
	private ItemStack VILLAGER_SPAWN_EGG = null;
	private ItemStack MOOSHROOM_SPAWN_EGG = null;
	private ItemStack PANDA_SPAWN_EGG = null;
	private ItemStack HORSE_SPAWN_EGG = null;	
	private ItemStack DOLPHIN_SPAWN_EGG = null;
	private ItemStack FOX_SPAWN_EGG = null;
	private ItemStack TURTLE_SPAWN_EGG = null;
	private ItemStack PARROT_SPAWN_EGG = null;
	private ItemStack RABBIT_SPAWN_EGG = null;
	private ItemStack POLAR_BEAR_SPAWN_EGG = null;
	private ItemStack GUARDIAN_SPAWN_EGG = null;
	private ItemStack LLAMA_SPAWN_EGG = null;
	private ItemStack SLIME_SPAWN_EGG = null;

	
	private ItemStack item_stack = new ItemStack(Material.NETHER_WART_BLOCK); 
	private String name = "&bReanimator";
	private String description = "&a&oPut things into eggs";
	private String colour = "&7";
	private String id = "REANIMATOR";
	private Setup plugin;
	public ConsoleCommandSender console;
	
	private RecipeType REANIMATOR = new RecipeType(new CustomItem(item_stack, name, description), id);
	
	public ReAnimator(Category category) {
		super(
				category, 
				new SlimefunItemStack("REANIMATOR", WonderItems.REANIMATOR), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_PLATE, SlimefunItems.BOOSTED_URANIUM, SlimefunItems.PLUTONIUM, SlimefunItems.BOOSTED_URANIUM, WonderItems.PRINTED_CIRCUIT_BOARD, WonderItems.WONDER_ALLOY, SlimefunItems.POWER_CRYSTAL}
		);
		try {
			COW_SPAWN_EGG = new CustomItem((Material.COW_SPAWN_EGG), "&cFrankencow");
			PIG_SPAWN_EGG = new CustomItem((Material.PIG_SPAWN_EGG), "&cFrankenpig");
			SHEEP_SPAWN_EGG = new CustomItem((Material.SHEEP_SPAWN_EGG), "&cFrankensheep");
			WOLF_SPAWN_EGG = new CustomItem((Material.WOLF_SPAWN_EGG), "&cFrankenwolf");
			SQUID_SPAWN_EGG = new CustomItem((Material.SQUID_SPAWN_EGG), "&cFrankensquid");
			BEE_SPAWN_EGG = new CustomItem((Material.BEE_SPAWN_EGG), "&cFrankenbee");
			VILLAGER_SPAWN_EGG = new CustomItem((Material.VILLAGER_SPAWN_EGG), "&cFrankenvillager");
			MOOSHROOM_SPAWN_EGG = new CustomItem((Material.MOOSHROOM_SPAWN_EGG), "&cFrankenmooshroom");
			PANDA_SPAWN_EGG = new CustomItem((Material.PANDA_SPAWN_EGG), "&cFrankenpanda");
			HORSE_SPAWN_EGG = new CustomItem((Material.HORSE_SPAWN_EGG), "&cFrankenhorse");
			
			DOLPHIN_SPAWN_EGG = new CustomItem((Material.DOLPHIN_SPAWN_EGG), "&cFrankendolphin");
			FOX_SPAWN_EGG = new CustomItem((Material.FOX_SPAWN_EGG), "&cFrankenfox");
			TURTLE_SPAWN_EGG = new CustomItem((Material.TURTLE_SPAWN_EGG), "&cFrankenturtle");
			PARROT_SPAWN_EGG = new CustomItem((Material.PARROT_SPAWN_EGG), "&cFrankenparrot");
			RABBIT_SPAWN_EGG = new CustomItem((Material.RABBIT_SPAWN_EGG), "&cFrankenrabbit");
			POLAR_BEAR_SPAWN_EGG = new CustomItem((Material.POLAR_BEAR_SPAWN_EGG), "&cFrankenpolar Bear");
			GUARDIAN_SPAWN_EGG = new CustomItem((Material.GUARDIAN_SPAWN_EGG), "&cFrankenguardian");
			LLAMA_SPAWN_EGG = new CustomItem((Material.LLAMA_SPAWN_EGG), "&cFrankenllama");
			SLIME_SPAWN_EGG = new CustomItem((Material.SLIME_SPAWN_EGG), "&cFrankenslime");
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
		return REANIMATOR;
	}

	@Override
	public String getMachineIdentifier() {
		return "REANIMATOR";
	}
	
	@Override
	public int getSpeed() {
		return 1;
	}

	@Override
	public int getEnergyConsumption() {
		return 1024;
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 2056;
	}

	@Override
	public String getInventoryTitle() {
		return "&bReAnimator";
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.FLINT_AND_STEEL);
	}

	@Override
	public void registerDefaultRecipes() 
	{
		registerRecipe(10, new ItemStack[] {WonderItems.EGG_SHELL, WonderItems.COW_ESSENCE},  new ItemStack[] {COW_SPAWN_EGG});
		registerRecipe(10, new ItemStack[] {WonderItems.EGG_SHELL, WonderItems.PIG_ESSENCE},  new ItemStack[] {PIG_SPAWN_EGG});
		registerRecipe(10, new ItemStack[] {WonderItems.EGG_SHELL, WonderItems.SHEEP_ESSENCE},  new ItemStack[] {SHEEP_SPAWN_EGG});
		registerRecipe(10, new ItemStack[] {WonderItems.EGG_SHELL, WonderItems.WOLF_ESSENCE},  new ItemStack[] {WOLF_SPAWN_EGG});
		registerRecipe(10, new ItemStack[] {WonderItems.EGG_SHELL, WonderItems.SQUID_ESSENCE},  new ItemStack[] {SQUID_SPAWN_EGG});
		registerRecipe(10, new ItemStack[] {WonderItems.EGG_SHELL, WonderItems.BEE_ESSENCE},  new ItemStack[] {BEE_SPAWN_EGG});
		registerRecipe(10, new ItemStack[] {WonderItems.EGG_SHELL, WonderItems.VILLAGER_ESSENCE},  new ItemStack[] {VILLAGER_SPAWN_EGG});
		registerRecipe(10, new ItemStack[] {WonderItems.EGG_SHELL, WonderItems.MOOSHROOM_ESSENCE},  new ItemStack[] {MOOSHROOM_SPAWN_EGG});
		registerRecipe(10, new ItemStack[] {WonderItems.EGG_SHELL, WonderItems.PANDA_ESSENCE},  new ItemStack[] {PANDA_SPAWN_EGG});
		registerRecipe(10, new ItemStack[] {WonderItems.EGG_SHELL, WonderItems.HORSE_ESSENCE},  new ItemStack[] {HORSE_SPAWN_EGG});
		
		registerRecipe(10, new ItemStack[] {WonderItems.EGG_SHELL, WonderItems.DOLPHIN_ESSENCE},  new ItemStack[] {DOLPHIN_SPAWN_EGG});
		registerRecipe(10, new ItemStack[] {WonderItems.EGG_SHELL, WonderItems.FOX_ESSENCE},  new ItemStack[] {FOX_SPAWN_EGG});
		registerRecipe(10, new ItemStack[] {WonderItems.EGG_SHELL, WonderItems.TURTLE_ESSENCE},  new ItemStack[] {TURTLE_SPAWN_EGG});
		registerRecipe(10, new ItemStack[] {WonderItems.EGG_SHELL, WonderItems.PARROT_ESSENCE},  new ItemStack[] {PARROT_SPAWN_EGG});
		registerRecipe(10, new ItemStack[] {WonderItems.EGG_SHELL, WonderItems.RABBIT_ESSENCE},  new ItemStack[] {RABBIT_SPAWN_EGG});
		registerRecipe(10, new ItemStack[] {WonderItems.EGG_SHELL, WonderItems.POLAR_BEAR_ESSENCE},  new ItemStack[] {POLAR_BEAR_SPAWN_EGG});
		registerRecipe(10, new ItemStack[] {WonderItems.EGG_SHELL, WonderItems.GUARDIAN_ESSENCE},  new ItemStack[] {GUARDIAN_SPAWN_EGG});
		registerRecipe(10, new ItemStack[] {WonderItems.EGG_SHELL, WonderItems.LLAMA_ESSENCE},  new ItemStack[] {LLAMA_SPAWN_EGG});
		registerRecipe(10, new ItemStack[] {WonderItems.EGG_SHELL, WonderItems.SLIME_ESSENCE},  new ItemStack[] {SLIME_SPAWN_EGG});
	}
}