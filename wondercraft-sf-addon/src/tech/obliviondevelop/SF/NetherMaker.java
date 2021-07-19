package tech.obliviondevelop.SF;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;

import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import tech.obliviondevelop.SF.Lists.WonderItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class NetherMaker extends AContainer
{
	// This is for customer head items
	private ItemStack NETHER_QUARTZ_ORE = null;
	private ItemStack SOULSAND = null;
	private ItemStack NETHERRACK = null;
	
	private ItemStack item_stack = new ItemStack(Material.GRAY_TERRACOTTA); 
	private String name = "&bNether Maker";
	private String description = "&a&oThis makes nether things";
	private String colour = "&7";
	private String id = "NETHER_MAKER";
	private Setup plugin;
	public ConsoleCommandSender console;
	
	private RecipeType NETHER_MAKER = new RecipeType(new CustomItem(item_stack, name, description), id);
	
	public NetherMaker(Category category) {
		super(
				category, 
				new SlimefunItemStack("NETHER_MAKER", WonderItems.NETHER_MAKER), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {new ItemStack(Material.RED_NETHER_BRICKS), SlimefunItems.HEATING_COIL, new ItemStack(Material.RED_NETHER_BRICKS), SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.POWER_CRYSTAL, SlimefunItems.ELECTRIC_MOTOR, WonderItems.PRINTED_CIRCUIT_BOARD, SlimefunItems.ALUMINUM_BRONZE_INGOT, SlimefunItems.ALUMINUM_BRONZE_INGOT}
		);
		try {
			NETHER_QUARTZ_ORE = new ItemStack(Material.NETHER_QUARTZ_ORE);
			SOULSAND = new ItemStack(Material.SOUL_SAND);
			NETHERRACK = new ItemStack(Material.NETHERRACK);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public RecipeType asRecipeType() 
	{
		return NETHER_MAKER;
	}

	@Override
	public String getMachineIdentifier() {
		return "NETHER_MAKER";
	}
	
	@Override
	public int getSpeed() {
		return 650;
	}

	@Override
	public int getEnergyConsumption() {
		return 25;
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 256;
	}

	@Override
	public String getInventoryTitle() {
		return "&bNether Maker";
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.FLINT_AND_STEEL);
	}

	@Override
	public void registerDefaultRecipes() 
	{
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.CLAY_BALL, 8)}, new ItemStack[] {SOULSAND});
		registerRecipe(4, new ItemStack[] {new CustomItem(SlimefunItems.STONE_CHUNK, 2), new CustomItem(SlimefunItems.MAGNESIUM_DUST, 2)},  new ItemStack[] {NETHER_QUARTZ_ORE});
		registerRecipe(4, new ItemStack[] {new ItemStack(Material.DIRT), new CustomItem(SlimefunItems.MAGNESIUM_DUST, 2)},  new ItemStack[] {NETHERRACK});
	}
	
	/*
	@SuppressWarnings("deprecation")
	protected void tick(Block b)
	{
		
		BlockMenu menu = BlockStorage.getInventory(b.getLocation());
		BlockMenu inv = BlockStorage.getInventory(b);
		

			
			for (int slot: getInputSlots()) 
			{
				
				ItemStack item = BlockStorage.getInventory(b).getItemInSlot(slot);
				
				if (SlimefunUtils.isItemSimilar(item,new CustomItem(new ItemStack(Material.CLAY_BALL, 8)), true)) 
				{ 
					slot_1_filled = true; 
					slot_2_filled = true; 
					slot_1 = slot;  
					produce_type = "Soul Sand";
				}
				else if (SlimefunUtils.isItemSimilar(item, new CustomItem(SlimefunItems.STONE_CHUNK, 2), true)) 
				{ 
					slot_1_filled = true; 
					slot_1 = slot;  
					slot_1_type = "Stone chunk";
				}
				else if (SlimefunUtils.isItemSimilar(item, new CustomItem(new ItemStack(Material.DIRT), 1), true)) 
				{ 
					slot_1_filled = true; 
					slot_1 = slot;  
					slot_1_type = "Dirt";
				}				
				
				else if (SlimefunUtils.isItemSimilar(item, new CustomItem(SlimefunItems.MAGNESIUM_DUST, 2), true)) 
				{ 
					slot_2_filled = true; 
					slot_2 = slot; 
					
					if(slot_1_type == "Stone chunk")
					{
						produce_type = "Nether Quartz Ore";
					}
					else if (slot_1_type == "Dirt")
					{
						produce_type = "Netherrack";
					}
				}		
				
				if (slot_1_filled && slot_2_filled) 
				{
					boolean empty_slot = false;
					for (int output_slot: getOutputSlots()) {
						if (BlockStorage.getInventory(b).getItemInSlot(output_slot) == null)
						{
							empty_slot = true;
							break;
						}
					}
					if (!empty_slot)
					{
						return;
					}
					
					if (produce_type == "Soul Sand")
					{
						r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {SOULSAND});
					}
					else if (produce_type == "Nether Quartz Ore")
					{
						r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {NETHER_QUARTZ_ORE});
					}
					else if (produce_type == "Netherrack")
					{
						r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {NETHERRACK});
					}
					
					if (produce_type != "")
					{						
						if (produce_type == "Soul Sand")
						{
							BlockStorage.getInventory(b).replaceExistingItem(slot_1, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot_1), 8));
						}
						else if (produce_type == "Nether Quartz Ore")
						{
							BlockStorage.getInventory(b).replaceExistingItem(slot_1, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot_1), 2));
							BlockStorage.getInventory(b).replaceExistingItem(slot_2, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot_2), 2));
						}
						else if (produce_type == "Netherrack")
						{
							BlockStorage.getInventory(b).replaceExistingItem(slot_1, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot_1), 1));
							BlockStorage.getInventory(b).replaceExistingItem(slot_2, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot_2), 2));
						}
						
						processing.put(b, r);
						progress.put(b, r.getTicks());
					}
					break;
				}
			}
		}
	}
	*/
}
