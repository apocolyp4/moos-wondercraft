package tech.obliviondevelop.SF;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;

import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import tech.obliviondevelop.SF.Lists.WonderItems;

public class SmallHadronCollider extends AContainer {
	
	private ItemStack URANIUM = null;
	private ItemStack PLUTONIUM = null;
	private ItemStack MOOINIUM = null;
	private ItemStack DUBNIUM = null;
	
	private ItemStack item_stack = new ItemStack(Material.DISPENSER); 
	private String name = "&bSmall_Hadron_Collider";
	private String description = "&7Smash Patricles at High Speeds";
	private String id = "SMALL_HADRON_COLLIDER";
	private Setup plugin;	
	private RecipeType SMALL_HADRON_COLLIDER_Recipe = new RecipeType(new CustomItem(item_stack, name, description), id);
	
	public SmallHadronCollider(Category category) {
		super(
				category, 
				new SlimefunItemStack("SMALL_HADRON_COLLIDER", WonderItems.SMALL_HADRON_COLLIDER), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {WonderItems.WONDER_ALLOY, WonderItems.WONDER_ALLOY, WonderItems.WONDER_ALLOY, SlimefunItems.INFUSED_MAGNET, SlimefunItems.INFUSED_MAGNET, SlimefunItems.INFUSED_MAGNET, WonderItems.WONDER_ALLOY, WonderItems.WONDER_ALLOY, WonderItems.WONDER_ALLOY }
		);
		
		try {
			URANIUM = new SlimefunItemStack("URANIUM", SlimefunItems.BOOSTED_URANIUM);
			PLUTONIUM = new SlimefunItemStack("PLUTONIUM",SlimefunItems.PLUTONIUM);
			MOOINIUM = new SlimefunItemStack("MOOINIUM", WonderItems.MOOINIUM);
			DUBNIUM = new SlimefunItemStack("DUBNIUM", WonderItems.DUBNIUM);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RecipeType asRecipeType() 
	{
		return SMALL_HADRON_COLLIDER_Recipe;
	}
	
	@Override
	public String getMachineIdentifier() {
		return "SMALL_HADRON_COLLIDER";
	}

	@Override
	public String getInventoryTitle() {
		return "&bSmall Hadron Collider";
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
		return 2048;
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 4092;
	}
	
	/*
	@SuppressWarnings("deprecation")
	protected void tick(Block b)
	{
		
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
			boolean slot_1_filled = false;
			int slot_1 = -1;
			boolean slot_2_filled = false;
			int slot_2 = -1;
			String produce_type = "";
			String slot_1_type = "";
			String slot_2_type = "";	
			MachineRecipe r = null;
			
			for (int slot: getInputSlots()) 
			{
				
				ItemStack item = BlockStorage.getInventory(b).getItemInSlot(slot);
				
				if (SlimefunUtils.isItemSimilar(item,new CustomItem(SlimefunItems.URANIUM), true)) 
				{ 
					slot_1_filled = true; 
					slot_1 = slot;  
					slot_1_type = "Uranium";
				}
				if (SlimefunUtils.isItemSimilar(item, new CustomItem(WonderItems.DUBNIUM), true)) 
				{ 
					slot_2_filled = true; 
					slot_2 = slot;  
					slot_2_type = "Dubnium";
				}
				
					
				if(slot_1_filled && slot_2_filled)
				{
					produce_type = "Mooinium";
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
					
					if (produce_type == "Mooinium")
					{
						r = new MachineRecipe(120 / getSpeed(), new ItemStack[0], new ItemStack[] {MOOINIUM});
					}

					
					if (produce_type != "")
					{						
						if (produce_type == "Mooinium")
						{
							BlockStorage.getInventory(b).replaceExistingItem(slot_1, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot_1), 1));
							BlockStorage.getInventory(b).replaceExistingItem(slot_2, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot_2), 1));
						}
						/*else if (produce_type == "Nether Quartz Ore")
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
