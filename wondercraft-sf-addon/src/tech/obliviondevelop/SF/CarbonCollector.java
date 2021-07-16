package tech.obliviondevelop.SF;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;

import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import tech.obliviondevelop.SF.Lists.WonderItems;

public class CarbonCollector extends AContainer {
	
	private ItemStack CARBON = null;
	private ItemStack MOOINIUM = null;
	
	
	private ItemStack item_stack = new ItemStack(Material.DISPENSER); 
	private String name = "&bCarbon_Collector";
	private String description = "&7Use Mooinium to collect Carbon from air";
	private String id = "CARBON_COLLECTOR";
	private Setup plugin;	
	private RecipeType CARBON_COLLECTOR_Recipe = new RecipeType(new CustomItem(item_stack, name, description), id);
	
	public CarbonCollector(Category category) {
		super(
				category, 
				new SlimefunItemStack("CARBON_COLLECTOR", WonderItems.CARBON_COLLECTOR), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {WonderItems.POWER_PISTON, WonderItems.POWER_PISTON, WonderItems.POWER_PISTON, null, null, null, SlimefunItems.ELECTRIC_PRESS, SlimefunItems.ELECTRIC_PRESS, SlimefunItems.ELECTRIC_PRESS }
		);
		
		try {
			CARBON = new SlimefunItemStack("CARBON",SlimefunItems.CARBON);
			MOOINIUM = new SlimefunItemStack("MOOINIUM", WonderItems.MOOINIUM);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RecipeType asRecipeType() 
	{
		return CARBON_COLLECTOR_Recipe;
	}
	
	@Override
	public String getMachineIdentifier() {
		return "CARBON_COLLECTOR";
	}

	@Override
	public String getInventoryTitle() {
		return "&bCarbon Collector";
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.FLINT_AND_STEEL);
	}

	@Override
	public void registerDefaultRecipes() 
	{
		registerRecipe(45, new ItemStack[] {WonderItems.MOOINIUM},  new ItemStack[] {new CustomItem(SlimefunItems.CARBON, 32)});
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
		return 2048;
	}
	

}


