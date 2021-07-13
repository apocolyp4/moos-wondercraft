package tech.obliviondevelop.SF;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import tech.obliviondevelop.SF.Lists.WonderItems;
import tech.obliviondevelop.SF.Lists.WonderMachines;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class ElectricHydropress extends AContainer {

	public ElectricHydropress(Category category) {
		super(
				category, 
				new SlimefunItemStack("ELECTRIC_HYDROPRESS", WonderMachines.ELECTRIC_HYDROPRESS), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {WonderItems.CARBONADO_BLOCK, WonderItems.CARBONADO_BLOCK, WonderItems.CARBONADO_BLOCK, WonderItems.POWER_PISTON, WonderItems.POWER_PISTON, WonderItems.POWER_PISTON, SlimefunItems.ELECTRIC_MOTOR, WonderItems.PRINTED_CIRCUIT_BOARD, SlimefunItems.ELECTRIC_MOTOR}
		);
	}

	@Override
	public String getMachineIdentifier() {
		return "ELECTRIC_HYDROPRESS";
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
		return "&bElectric Hydropress";
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.DIAMOND_PICKAXE);
	}

	@Override
	public void registerDefaultRecipes() {}
		
/*
	protected void tick(Block b) {
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
			for (int slot: getInputSlots()) {
				if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(SlimefunItems.CARBON_CHUNK), true)) {
					ItemStack output = SlimefunItems.OIL_BUCKET;
					
					MachineRecipe r = new MachineRecipe(24 / getSpeed(), new ItemStack[0], new ItemStack[] {output});
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
