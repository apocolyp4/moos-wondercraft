package tech.obliviondevelop.SF;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
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
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import tech.obliviondevelop.SF.Lists.WonderItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class ElectricPurifier extends AContainer {
	
	// This is for customer head items
	private ItemStack PURE_ORE_CLUSTER = null;
	private ItemStack TITANIUM_DUST = null;
	private ItemStack CHROMIUM_DUST = null;
	private ItemStack DUBNIUM = null;
	private ItemStack STONE_CHUNK = null;
	private ItemStack CLAY_BALL = null;
	
	
	private ItemStack item_stack = new ItemStack(Material.GRAY_TERRACOTTA); 
	private String name = "&bElectric Purifier";
	private String description = "&a&oWash Pure Ore Clusters";
	private String colour = "&7";
	private String id = "ELECTRIC_PURIFIER";
	private Setup plugin;
	public ConsoleCommandSender console;
	
	private RecipeType ELECTRIC_PURIFIER = new RecipeType(new CustomItem(item_stack, name, description), id);
	
	public ElectricPurifier(Category category) {
		super(
				category, 
				new SlimefunItemStack("ELECTRIC_PURIFIER", WonderItems.ELECTRIC_PURIFIER), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {SlimefunItems.REINFORCED_PLATE, WonderItems.WONDER_ALLOY, SlimefunItems.REINFORCED_PLATE, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRIC_DUST_WASHER_3, SlimefunItems.ELECTRIC_MOTOR, WonderItems.PRINTED_CIRCUIT_BOARD, SlimefunItems.POWER_CRYSTAL, WonderItems.POWER_CELL}
		);
		try {
			PURE_ORE_CLUSTER = new SlimefunItemStack("PURE_ORE_CLUSTER", SlimefunItems.PURE_ORE_CLUSTER);
			TITANIUM_DUST = new SlimefunItemStack("TITANIUM_DUST", WonderItems.TITANIUM_DUST);
			CHROMIUM_DUST = new SlimefunItemStack("CHROMIUM_DUST", WonderItems.CHROMIUM_DUST);
			DUBNIUM = new SlimefunItemStack("DUBNIUM", WonderItems.DUBNIUM);
			STONE_CHUNK = new SlimefunItemStack("TITANIUM_DUST", SlimefunItems.STONE_CHUNK);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public RecipeType asRecipeType() 
	{
		return ELECTRIC_PURIFIER;
	}

	@Override
	public String getMachineIdentifier() {
		return "ELECTRIC_PURIFIER";
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
	public String getInventoryTitle() {
		return "&bElectric Purifier";
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.FLINT_AND_STEEL);
	}

	@Override
	public void registerDefaultRecipes() {}
	
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
			MachineRecipe r = null;
			Random ran = new Random();
			int chance =  ran.nextInt(100); // get random number to pick which dust to give
			
			for (int slot: getInputSlots()) 
			{
				{
					if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), SlimefunItems.PURE_ORE_CLUSTER, true)) 
					{				
						if (chance >= 0 && chance <= 20) {
							r = new MachineRecipe(5 / getSpeed(), new ItemStack[0], new ItemStack[] {WonderItems.TITANIUM_DUST});
						}
						else if (chance >= 21 && chance <= 40)
						{
							r = new MachineRecipe(5 / getSpeed(), new ItemStack[0], new ItemStack[] {WonderItems.CHROMIUM_DUST});
						}
						else if (chance >= 41 && chance <= 50)
						{
							r = new MachineRecipe(5 / getSpeed(), new ItemStack[0], new ItemStack[] {WonderItems.DUBNIUM});
						}
						else if (chance >= 51 && chance <= 60)
						{
							r = new MachineRecipe(5 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.GRAVEL)});
						}
						else if (chance >= 61 && chance <= 70)
						{
							r = new MachineRecipe(5 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.CLAY_BALL)});
						}
						else if (chance >= 71 && chance <= 80)
						{
							r = new MachineRecipe(5 / getSpeed(), new ItemStack[0], new ItemStack[] {new ItemStack(Material.FLINT)});
						}
						else if (chance >= 81 && chance <= 100)
						{
							r = new MachineRecipe(5 / getSpeed(), new ItemStack[0], new ItemStack[] {SlimefunItems.STONE_CHUNK});
						}
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
}
