package tech.obliviondevelop.SF;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import tech.obliviondevelop.SF.Lists.WonderItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class IndustrialGrinder  extends AContainer 
{
	
	private ItemStack NETHERITE_DUST = null;
	
	private ItemStack NETHERITE_SCRAP = new ItemStack(Material.NETHERITE_SCRAP);
	
	public IndustrialGrinder(Category category) {
		super(
				category, 
				new SlimefunItemStack("INDUSTRIAL_GRINDER", WonderItems.INDUSTRIAL_GRINDER), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {SlimefunItems.POWER_CRYSTAL, SlimefunItems.ELECTRIC_MOTOR, WonderItems.PRINTED_CIRCUIT_BOARD, null, WonderItems.DIAMOND_EDGED_CUTTER, null, SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.HARDENED_METAL_INGOT}
		);
		try 
		{
			NETHERITE_DUST = new SlimefunItemStack("NETHERITE_DUST", WonderItems.NETHERITE_DUST);
		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	

	@Override
	public String getMachineIdentifier() {
		return "INDUSTRIAL_GRINDER";
	}

	@Override
	public String getInventoryTitle() {
		return "&bIndustrial Grinder";
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.NETHERITE_PICKAXE);
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
	public void registerDefaultRecipes() {}
	
	/*
	@SuppressWarnings("deprecation")
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
		else 
		{
		
			for (int slot: getInputSlots()) 
			{
				if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot),  NETHERITE_SCRAP, true)) 
				{
					ItemStack output = NETHERITE_DUST;
					
					MachineRecipe r = new MachineRecipe(30 / getSpeed(), new ItemStack[0], new ItemStack[] {output});
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
