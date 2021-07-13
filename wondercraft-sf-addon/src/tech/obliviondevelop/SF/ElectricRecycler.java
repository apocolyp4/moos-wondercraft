package tech.obliviondevelop.SF;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;

import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import tech.obliviondevelop.SF.Lists.WonderItems;

public class ElectricRecycler extends AContainer {
	
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
			
			STONE_CHUNK = new SlimefunItemStack("STONE_CHUNK",SlimefunItems.STONE_CHUNK);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public void registerDefaultRecipes() {}
	
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
	
	@SuppressWarnings("deprecation")
	protected void tick(Block b)
	{
		
		BlockMenu menu = BlockStorage.getInventory(b.getLocation());
		BlockMenu inv = BlockStorage.getInventory(b);
		/*
		
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
				if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.FLINT, 16), true)) {
					
					MachineRecipe r = new MachineRecipe(3 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.CHARCOAL)});
					if (!menu.fits(r.getOutput()[0], getOutputSlots())) return;
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 16));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}	
				if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.CLAY_BALL, 4), true)) {
					
					MachineRecipe r = new MachineRecipe(3 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.COBBLESTONE)});
					if (!menu.fits(r.getOutput()[0], getOutputSlots())) return;
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 4));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}	
				if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.SANDSTONE), true)) {
					
					MachineRecipe r = new MachineRecipe(3 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.SAND)});
					if (!menu.fits(r.getOutput()[0], getOutputSlots())) return;
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}	
				if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new CustomItem(SlimefunItems.STONE_CHUNK, 4), true)) {
					
					MachineRecipe r = new MachineRecipe(3 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.SAND)});
					if (!menu.fits(r.getOutput()[0], getOutputSlots())) return;
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 4));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}	
			}
		}
		*/
	}
}


