package tech.obliviondevelop.SF;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import tech.obliviondevelop.SF.Lists.WonderItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class ElectricSawMill extends AContainer {
	
	public ElectricSawMill(Category category) {
		super(
				category, 
				new SlimefunItemStack("ELECTRIC_SAW_MILL", WonderItems.ELECTRIC_SAW_MILL), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {new ItemStack(Material.IRON_BARS), null, new ItemStack(Material.IRON_BARS), WonderItems.SAW_BLADE, SlimefunItems.ELECTRIC_MOTOR, WonderItems.SAW_BLADE, new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.IRON_BLOCK)}
		);
	}

	@Override
	public String getMachineIdentifier() {
		return "ELECTRIC_SAW_MILL";
	}

	@Override
	public String getInventoryTitle() {
		return "&bElectric Saw Mill";
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.DIAMOND_AXE);
	}
	
	
	@Override
	public int getSpeed() {
		return 420;
	}

	@Override
	public int getEnergyConsumption() {
		return 50;
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 128;
	}

	@Override
	public void registerDefaultRecipes() {}
	
	/*
	@SuppressWarnings("deprecation")
	protected void tick(Block b) 
	{
		
		BlockMenu menu = BlockStorage.getInventory(b.getLocation());
		
		if (isProcessing(b)) {
			int timeleft = progress.get(b);
			if (timeleft > 0) {
				ItemStack item = getProgressBar().clone();
				ItemMeta im = item.getItemMeta();
				ChestMenuUtils.updateProgressbar(menu, 22, timeleft, processing.get(b).getTicks(), getProgressBar());
				
				im.setDisplayName(" ");
				List<String> lore = new ArrayList<String>();
				im.setLore(lore);
				item.setItemMeta(im);
				
				BlockStorage.getInventory(b).replaceExistingItem(22, item);
				
				if (ChargableBlock.getCharge(b) < getEnergyConsumption()) return;
				ChargableBlock.addCharge(b, -getEnergyConsumption());
				
				progress.put(b, timeleft - 1);
			}
			else {
				BlockStorage.getInventory(b).replaceExistingItem(22, new CustomItem(Material.BLACK_STAINED_GLASS_PANE, " "));
				menu.pushItem(processing.get(b).getOutput()[0].clone(), getOutputSlots());
				
				progress.remove(b);
				processing.remove(b);
			}
		}
		else {
			for (int slot: getInputSlots()) {
				if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.ACACIA_LOG), true) || SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.STRIPPED_ACACIA_LOG), true)){
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.ACACIA_PLANKS, 8)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.BIRCH_LOG), true) || SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.STRIPPED_BIRCH_LOG), true)){
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.BIRCH_PLANKS, 8)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.OAK_LOG), true)|| SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.STRIPPED_OAK_LOG), true)) {
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.OAK_PLANKS, 8)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.JUNGLE_LOG), true) || SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.STRIPPED_JUNGLE_LOG), true)) {
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.JUNGLE_PLANKS, 8)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.DARK_OAK_LOG), true) || SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.STRIPPED_DARK_OAK_LOG), true))  {
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.DARK_OAK_PLANKS, 8)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.SPRUCE_LOG), true) || SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.STRIPPED_SPRUCE_LOG), true)) {
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.SPRUCE_PLANKS, 8)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				
				
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.WARPED_STEM), true))
						{
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.WARPED_PLANKS, 8)});
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}			
				
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(Material.CRIMSON_STEM), true))
				{
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null) {
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot) return;
					
					MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.CRIMSON_PLANKS, 8)});
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
