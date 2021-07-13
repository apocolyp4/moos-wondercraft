package tech.obliviondevelop.SF;

import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.inventory.ItemStack;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

import tech.obliviondevelop.SF.Lists.WonderItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;

public class Cannary extends AContainer {
	
	// This is for customer head items
	private ItemStack TIN_CARROTS = null;
	private ItemStack TIN_POTATOES = null;
	private ItemStack TIN_BEETROOT = null;
	
	private ItemStack item_stack = new ItemStack(Material.GRAY_TERRACOTTA); 
	private String name = "&bCannery";
	private String description = "&a&oPut things into cans";
	private String colour = "&7";
	private String id = "CANNARY";
	private Setup plugin;
	public ConsoleCommandSender console;
	
	private RecipeType CANNARY = new RecipeType(new CustomItem(item_stack, name, description), id);
	
	public Cannary(Category category) {
		super(
				category, 
				new SlimefunItemStack("CANNARY", WonderItems.CANNARY), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.HARDENED_METAL_INGOT, WonderItems.COGWHEEL, SlimefunItems.ELECTRIC_MOTOR, WonderItems.COGWHEEL, WonderItems.PRINTED_CIRCUIT_BOARD, WonderItems.ALUMINIUM_FOIL, SlimefunItems.POWER_CRYSTAL}
		);
		try {
			TIN_CARROTS = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjY1YzNiZGIzZDM2ZWVkNWNkZGY3NWIxZmI0NmRhZjhjMWZjODM0Mjg1NDBhMWU0YTZlNmQ2NjNlNWY4OWQ5OCJ9fX0="), "&cTin Carrots");
			TIN_POTATOES = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGU3MDhiMTNiYWZiNWE3NThkOWQyZjAxMzZmM2I3N2QxMzk1ODNiODZkNDY3ZGU4NmQyMTYyNmQwNDUzNGUifX19"), "&cTin Potatoes");
			TIN_BEETROOT = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWQ5MmZlZWExZGVlZTYxY2Q1ZTIzOWNiM2VkOWM0NDRlNTk3YzkxODMzMjZkMWZlODRiOTUyYzg1ZmVhMzNhIn19fQ=="), "&cTin Beetroot");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public RecipeType asRecipeType() 
	{
		return CANNARY;
	}

	@Override
	public String getMachineIdentifier() {
		return "CANNARY";
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
		return "&bCannary";
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.FLINT_AND_STEEL);
	}

	@Override
	public void registerDefaultRecipes() {}
	
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
			boolean can = false;
			int canSlot = -1;
			boolean produce = false;
			int produce_slot = -1;
			String produce_type = "";
			MachineRecipe r = null;
			
			for (int slot: getInputSlots()) 
			{
				if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new ItemStack(SlimefunItems.TIN_CAN), true)) { 
					can = true; 
					canSlot = slot; 
				}
				if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new CustomItem(new ItemStack(Material.CARROT), 2), true)) 
				{ 
					produce = true; 
					produce_slot = slot; 
					produce_type = "Carrot";
				}
				
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new CustomItem(new ItemStack(Material.BEETROOT), 2), true)) 
				{ 
					produce = true; 
					produce_slot = slot; 
					produce_type = "Beetroot";
				}		
				
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), new CustomItem(new ItemStack(Material.POTATO), 2), true)) 
				{ 
					produce = true; 
					produce_slot = slot; 
					produce_type = "Potato";
				}				
				if (can && produce) 
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
					
					if (produce_type == "Carrot")
					{
						r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {TIN_CARROTS});
					}
					else if (produce_type == "Beetroot")
					{
						r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {TIN_BEETROOT});
					}
					else if (produce_type == "Potato")
					{
						r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] {TIN_POTATOES});
					}
					
					if (produce_type != "")
					{
						BlockStorage.getInventory(b).replaceExistingItem(canSlot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(canSlot), 1));
						BlockStorage.getInventory(b).replaceExistingItem(produce_slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(produce_slot), 2));
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
