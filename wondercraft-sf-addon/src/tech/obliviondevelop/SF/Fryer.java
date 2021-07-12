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

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineProcessor;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.operations.CraftingOperation;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import tech.obliviondevelop.SF.Lists.WonderItems;

public class Fryer extends AContainer
{
	
	private ItemStack DOUGH = null;
	private ItemStack DONUT = null;
	private ItemStack RAW_FRIES = null;
	private ItemStack MOOS_FRIES = null;
	
	
	private ItemStack item_stack = new ItemStack(Material.DISPENSER); 
	private String name = "&bFryer";
	private String description = "&7Fry Things";
	private String id = "FRYER";
	private Setup plugin;	
	private RecipeType FRYER_Recipe = new RecipeType(new CustomItem(item_stack, name, description), id);
	private final MachineProcessor<CraftingOperation> processor = new MachineProcessor<>(this);
	
	public Fryer(Category category) 
	{
		super(
				category, 
				new SlimefunItemStack("FRYER", WonderItems.FRYER), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {SlimefunItems.REINFORCED_PLATE, SlimefunItems.BUTTER, SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_PLATE, SlimefunItems.BUTTER, SlimefunItems.REINFORCED_PLATE, WonderItems.PRINTED_CIRCUIT_BOARD, SlimefunItems.HEATING_COIL, SlimefunItems.POWER_CRYSTAL }
		);
		
		try {
			DONUT = new SlimefunItemStack("DONUT", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzMwMTMzNmU0MjMzNmI2ZjIwZDNkNzY3NGZhOTk5NzEwN2M1Yjk5NDc3MGRjODE3NDBjMDEyZWZkZjVlMmIzNyJ9fX0=", "&rDonut", "", "&7&oRestores &b&o" + "1.5" + " &7&oHunger");  // new SlimefunItemStack("DONUT", (WonderItems.donut.getItem()));
			DOUGH = new SlimefunItemStack("DOUGH", WonderItems.DOUGH);
			RAW_FRIES = new SlimefunItemStack("RAW_FRIES", WonderItems.RAW_FRIES);
			MOOS_FRIES = new SlimefunItemStack("MOOS_FRIES", WonderItems.MOOS_FRIES);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RecipeType asRecipeType() 
	{
		return FRYER_Recipe;
	}
	
	@Override
	public String getMachineIdentifier() {
		return "FRYER";
	}

	@Override
	public String getInventoryTitle() {
		return "&bFryer";
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
	/*
	
    protected void tick(Block b)
    {
        BlockMenu inv = BlockStorage.getInventory(b);
        CraftingOperation currentOperation = processor.getOperation(b);

        if (currentOperation != null) 
        {
            if (takeCharge(b.getLocation())) 
            {

                if (!currentOperation.isFinished()) 
                {
                    processor.updateProgressBar(inv, 22, currentOperation);
                    currentOperation.addProgress(1);
                } 
                else 
                {
                    inv.replaceExistingItem(22, new CustomItem(Material.BLACK_STAINED_GLASS_PANE, " "));

                    for (ItemStack output : currentOperation.getResults()) {
                        inv.pushItem(output.clone(), getOutputSlots());
                    }

                    processor.endOperation(b);
                }
            }
        } 
        else 
        {
            MachineRecipe next = findNextRecipe(inv);

            if (next != null) {
                processor.startOperation(b, new CraftingOperation(next));
            }
        }
    }
	*/
	@SuppressWarnings("deprecation")
	protected void tick1(Block b) 
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
		else 
		{
			for (int slot: getInputSlots()) 
			{
				if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), RAW_FRIES, true)) {
					ItemStack output = MOOS_FRIES;
					
					MachineRecipe r = new MachineRecipe(12 / getSpeed(), new ItemStack[0], new ItemStack[] {output});
					if (!menu.fits(r.getOutput()[0], getOutputSlots())) return;
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), DOUGH, true)) {
					ItemStack output = DONUT;
					
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
