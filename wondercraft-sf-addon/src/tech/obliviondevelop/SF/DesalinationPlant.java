package tech.obliviondevelop.SF;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import tech.obliviondevelop.SF.Lists.WonderItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class DesalinationPlant extends AContainer 
{
		
	public DesalinationPlant(Category category) {
		super(
				category, 
				new SlimefunItemStack("DESALINATION_PLANT", WonderItems.DESALINATION_PLANT), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {SlimefunItems.REINFORCED_PLATE, SlimefunItems.HEATING_COIL, SlimefunItems.REINFORCED_PLATE, SlimefunItems.HEATING_COIL, SlimefunItems.CLOTH, SlimefunItems.HEATING_COIL, WonderItems.PRINTED_CIRCUIT_BOARD, SlimefunItems.HEATING_COIL, SlimefunItems.POWER_CRYSTAL}
		);
	}

	@Override
	public String getMachineIdentifier() {
		return "Desalination_Plant";
	}
	
	@Override
	public String getInventoryTitle() {
		return "&bDesalination Plant";
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
	
	protected void tick(Block b) {
		
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
			for (int slot: getInputSlots()) {
				if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.WATER_BUCKET), true)) {
					MachineRecipe r = new MachineRecipe(128 / getSpeed(), new ItemStack[0], new ItemStack[] {new CustomItem(SlimefunItems.SALT, 32)});
						if (!menu.fits(r.getOutput()[0], getOutputSlots())) return;
						BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
						processing.put(b, r);
						progress.put(b, r.getTicks());
					
					break;
				}
			}
		}
		*/
	}
}
