package tech.obliviondevelop.SF;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;

import me.mrCookieSlime.Slimefun.api.BlockStorage;

import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import tech.obliviondevelop.SF.Lists.WonderItems;
import tech.obliviondevelop.SF.Lists.WonderMachines;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

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
	public void registerDefaultRecipes() {}
	
	@SuppressWarnings("deprecation")
	protected void tick(Block b) 
	{
		/*
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
				if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(SlimefunItems.LEAD_INGOT), true)) {
					ItemStack output = LEAD_FIBRE;
					
					MachineRecipe r = new MachineRecipe(12 / getSpeed(), new ItemStack[0], new ItemStack[] {output});
					if (!menu.fits(r.getOutput()[0], getOutputSlots())) return;
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(SlimefunItems.CARBON), true)) {
					ItemStack output = CARBON_FIBRE;
					
					MachineRecipe r = new MachineRecipe(12 / getSpeed(), new ItemStack[0], new ItemStack[] {output});
					if (!menu.fits(r.getOutput()[0], getOutputSlots())) return;
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), WONDER_ALLOY , true)) {
					ItemStack output = WONDER_FIBRE;
					
					MachineRecipe r = new MachineRecipe(12 / getSpeed(), new ItemStack[0], new ItemStack[] {output});
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
