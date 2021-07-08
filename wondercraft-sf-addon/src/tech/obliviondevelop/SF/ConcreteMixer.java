package tech.obliviondevelop.SF;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
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

public class ConcreteMixer extends AContainer {
	
	public ConcreteMixer(Category category) 
	{
		super(
				category, 
				new SlimefunItemStack("CONCRETE_MIXER", WonderItems.MIXER), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, SlimefunItems.POWER_CRYSTAL, null, SlimefunItems.PLASTIC_SHEET, new ItemStack(Material.WATER_BUCKET), SlimefunItems.PLASTIC_SHEET, SlimefunItems.SYNTHETIC_EMERALD, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.SYNTHETIC_EMERALD}
		);
	}

	@Override
	public ItemStack getProgressBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMachineIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	@Override
	public String getMachineIdentifier() {
		return "CONCRETE_MACHINE";
	}

	@Override
	public String getInventoryTitle() {
		return "&3Concrete Machine";
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.DIAMOND_SHOVEL);
	}

	@Override
	public void registerDefaultRecipes() {}
	
	
	@Override
	public int getSpeed() {
		return 1;
	}

	@Override
	public int getEnergyConsumption() {
		return 10;
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 256;
	}
	
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
		else {
			for (int slot: getInputSlots()) {
				if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.RED_CONCRETE_POWDER), true)) {
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.RED_CONCRETE)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.BLACK_CONCRETE_POWDER), true)) {
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.BLACK_CONCRETE)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.BLUE_CONCRETE_POWDER), true)) {
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.BLUE_CONCRETE)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.BROWN_CONCRETE_POWDER), true)) {
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.BROWN_CONCRETE)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.CYAN_CONCRETE_POWDER), true)) {
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.CYAN_CONCRETE)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.GRAY_CONCRETE_POWDER), true)) {
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.GRAY_CONCRETE)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.GREEN_CONCRETE_POWDER), true)) {
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.GREEN_CONCRETE)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.LIGHT_BLUE_CONCRETE_POWDER), true)) {
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.LIGHT_BLUE_CONCRETE)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.LIGHT_GRAY_CONCRETE_POWDER), true)) {
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.LIGHT_GRAY_CONCRETE)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.LIME_CONCRETE_POWDER), true)) {
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.LIME_CONCRETE)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.MAGENTA_CONCRETE_POWDER), true)) {
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.MAGENTA_CONCRETE)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.ORANGE_CONCRETE_POWDER), true)) {
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.ORANGE_CONCRETE)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.PINK_CONCRETE_POWDER), true)) {
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.PINK_CONCRETE)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.PURPLE_CONCRETE_POWDER), true)) {
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.PURPLE_CONCRETE)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.YELLOW_CONCRETE_POWDER), true)) {
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.YELLOW_CONCRETE)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.WHITE_CONCRETE_POWDER), true)) {
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.WHITE_CONCRETE)});
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
