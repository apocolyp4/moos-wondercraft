package tech.obliviondevelop.SF;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import tech.obliviondevelop.SF.Lists.WonderItems;
import tech.obliviondevelop.SF.Lists.WonderMachines;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

public class Extruder extends AContainer {
	
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
			
			CARBON_FIBRE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWQzMTY2OGQxMTYzOTdmYTdiY2JlODk0NGQ1MWQyMjkwOWUzYjgwYzlkM2RkZTE5ZmY0ODZmZGRmZTA2ZCJ9fX0="), "&6Carbon Fibre");
			LEAD_FIBRE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjc0MTc5MjQ1NjY3ZTYxNjY5Y2MxNDFhM2RjNDZkMjU5ZjE1OWEyOGVhMWI0NjkzNjQyZDlhMzEyNmRhOTU3ZCJ9fX0="), "&6Lead Fibre");
			WONDER_FIBRE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjdiNzQ3YjM3OGE0MWEwYTZlZGM4NmMwMDBmMDQwYzY5OTRhODMzMjUxMTk2YzlkNTJjMmEyMzBmOTUxNjBjYyJ9fX0="), "&6Wonder Fibre");
			WONDER_ALLOY = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGVhZjE5YWIyN2E3Yjk4ZGQ0NmI1YmE5YjkwMWI3MWIwZTVlZGEyMzI0MzlmYzhiMjk2ZWJkYjQ1MGJkM2U0NiJ9fX0="), "&bWonder Alloy");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		registerRecipe(3, new ItemStack[] {new CustomItem(SlimefunItems.LEAD_INGOT)}, new ItemStack[] {LEAD_FIBRE});
		registerRecipe(3, new ItemStack[] {new CustomItem(SlimefunItems.CARBON)}, new ItemStack[] {CARBON_FIBRE});
		registerRecipe(3, new ItemStack[] {WONDER_ALLOY}, new ItemStack[] {WONDER_FIBRE});
	}

	
}
