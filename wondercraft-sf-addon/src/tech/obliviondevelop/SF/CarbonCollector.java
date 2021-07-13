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
	public void registerDefaultRecipes() {}
	
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
	
	/*
	@SuppressWarnings("deprecation")
	protected void tick(Block b)
	{
		
		BlockMenu menu = BlockStorage.getInventory(b.getLocation());
		BlockMenu inv = BlockStorage.getInventory(b);
		
		if (isProcessing(b)) {
			int timeleft = progress.get(b);


            if (timeleft > 0) {
                ChestMenuUtils.updateProgressbar(inv, 22, timeleft, processing.get(b).getTicks(), getProgressBar());

                if (ChargableBlock.isChargable(b)) {
                    if (ChargableBlock.getCharge(b) < getEnergyConsumption()) return;
                    ChargableBlock.addCharge(b, -getEnergyConsumption());
                    progress.put(b, timeleft - 1);
                }
                else progress.put(b, timeleft - 1);
            }
            else {
                inv.replaceExistingItem(22, new CustomItem(Material.BLACK_STAINED_GLASS_PANE, " "));

                for (ItemStack output : processing.get(b).getOutput()) {
                    inv.pushItem(output.clone(), getOutputSlots());
                }

                progress.remove(b);
                processing.remove(b);
			}

		}
		else {
			for (int slot: getInputSlots()) 
			{
				if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), MOOINIUM, true)) {
					
					MachineRecipe r = new MachineRecipe(45 / getSpeed(), new ItemStack[0], new ItemStack[] {new CustomItem(SlimefunItems.CARBON, 32)});
					if (!menu.fits(r.getOutput()[0], getOutputSlots())) return;
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}	
			}
		}
	}
	*/
}


