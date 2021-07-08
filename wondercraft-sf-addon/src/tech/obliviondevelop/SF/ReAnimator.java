package tech.obliviondevelop.SF;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
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

public class ReAnimator extends AContainer  
{
	// This is for customer head items
	private ItemStack COW_SPAWN_EGG = null;
	private ItemStack PIG_SPAWN_EGG = null;
	private ItemStack SHEEP_SPAWN_EGG = null;
	private ItemStack WOLF_SPAWN_EGG = null;
	private ItemStack SQUID_SPAWN_EGG = null;
	private ItemStack BEE_SPAWN_EGG = null;
	private ItemStack VILLAGER_SPAWN_EGG = null;
	private ItemStack MOOSHROOM_SPAWN_EGG = null;
	private ItemStack PANDA_SPAWN_EGG = null;
	private ItemStack HORSE_SPAWN_EGG = null;	
	private ItemStack DOLPHIN_SPAWN_EGG = null;
	private ItemStack FOX_SPAWN_EGG = null;
	private ItemStack TURTLE_SPAWN_EGG = null;
	private ItemStack PARROT_SPAWN_EGG = null;
	private ItemStack RABBIT_SPAWN_EGG = null;
	private ItemStack POLAR_BEAR_SPAWN_EGG = null;
	private ItemStack GUARDIAN_SPAWN_EGG = null;
	private ItemStack LLAMA_SPAWN_EGG = null;
	private ItemStack SLIME_SPAWN_EGG = null;

	
	private ItemStack item_stack = new ItemStack(Material.NETHER_WART_BLOCK); 
	private String name = "&bReanimator";
	private String description = "&a&oPut things into eggs";
	private String colour = "&7";
	private String id = "REANIMATOR";
	private Setup plugin;
	public ConsoleCommandSender console;
	
	private RecipeType REANIMATOR = new RecipeType(new CustomItem(item_stack, name, description), id);
	
	public ReAnimator(Category category) {
		super(
				category, 
				new SlimefunItemStack("REANIMATOR", WonderItems.REANIMATOR), 
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_PLATE, SlimefunItems.BOOSTED_URANIUM, SlimefunItems.PLUTONIUM, SlimefunItems.BOOSTED_URANIUM, WonderItems.PRINTED_CIRCUIT_BOARD, WonderItems.WONDER_ALLOY, SlimefunItems.POWER_CRYSTAL}
		);
		try {
			COW_SPAWN_EGG = new CustomItem((Material.COW_SPAWN_EGG), "&cFrankencow");
			PIG_SPAWN_EGG = new CustomItem((Material.PIG_SPAWN_EGG), "&cFrankenpig");
			SHEEP_SPAWN_EGG = new CustomItem((Material.SHEEP_SPAWN_EGG), "&cFrankensheep");
			WOLF_SPAWN_EGG = new CustomItem((Material.WOLF_SPAWN_EGG), "&cFrankenwolf");
			SQUID_SPAWN_EGG = new CustomItem((Material.SQUID_SPAWN_EGG), "&cFrankensquid");
			BEE_SPAWN_EGG = new CustomItem((Material.BEE_SPAWN_EGG), "&cFrankenbee");
			VILLAGER_SPAWN_EGG = new CustomItem((Material.VILLAGER_SPAWN_EGG), "&cFrankenvillager");
			MOOSHROOM_SPAWN_EGG = new CustomItem((Material.MOOSHROOM_SPAWN_EGG), "&cFrankenmooshroom");
			PANDA_SPAWN_EGG = new CustomItem((Material.PANDA_SPAWN_EGG), "&cFrankenpanda");
			HORSE_SPAWN_EGG = new CustomItem((Material.HORSE_SPAWN_EGG), "&cFrankenhorse");
			
			DOLPHIN_SPAWN_EGG = new CustomItem((Material.DOLPHIN_SPAWN_EGG), "&cFrankendolphin");
			FOX_SPAWN_EGG = new CustomItem((Material.FOX_SPAWN_EGG), "&cFrankenfox");
			TURTLE_SPAWN_EGG = new CustomItem((Material.TURTLE_SPAWN_EGG), "&cFrankenturtle");
			PARROT_SPAWN_EGG = new CustomItem((Material.PARROT_SPAWN_EGG), "&cFrankenparrot");
			RABBIT_SPAWN_EGG = new CustomItem((Material.RABBIT_SPAWN_EGG), "&cFrankenrabbit");
			POLAR_BEAR_SPAWN_EGG = new CustomItem((Material.POLAR_BEAR_SPAWN_EGG), "&cFrankenpolar Bear");
			GUARDIAN_SPAWN_EGG = new CustomItem((Material.GUARDIAN_SPAWN_EGG), "&cFrankenguardian");
			LLAMA_SPAWN_EGG = new CustomItem((Material.LLAMA_SPAWN_EGG), "&cFrankenllama");
			SLIME_SPAWN_EGG = new CustomItem((Material.SLIME_SPAWN_EGG), "&cFrankenslime");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public RecipeType asRecipeType() 
	{
		return REANIMATOR;
	}

	@Override
	public String getMachineIdentifier() {
		return "REANIMATOR";
	}
	
	@Override
	public int getSpeed() {
		return 1;
	}

	@Override
	public int getEnergyConsumption() {
		return 1024;
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 2056;
	}

	@Override
	public String getInventoryTitle() {
		return "&bReAnimator";
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
			boolean egg = false;
			int eggSlot = -1;
			boolean produce = false;
			int produce_slot = -1;
			String produce_type = "";
			MachineRecipe r = null;
			
			for (int slot: getInputSlots()) 
			{
				if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), WonderItems.EGG_SHELL, true)) 
				{ 
					egg = true; 
					eggSlot = slot; 
				}
				if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), WonderItems.COW_ESSENCE, true)) 
				{ 
					produce = true; 
					produce_slot = slot; 
					produce_type = "Cow Spawn Egg";
				}
				
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), WonderItems.PIG_ESSENCE, true)) 
				{ 
					produce = true; 
					produce_slot = slot; 
					produce_type = "Pig Spawn Egg";
				}		
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), WonderItems.SHEEP_ESSENCE, true)) 
				{ 
					produce = true; 
					produce_slot = slot; 
					produce_type = "Sheep Spawn Egg";
				}
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), WonderItems.WOLF_ESSENCE, true)) 
				{ 
					produce = true; 
					produce_slot = slot; 
					produce_type = "Wolf Spawn Egg";
				}	
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), WonderItems.SQUID_ESSENCE, true)) 
				{ 
					produce = true; 
					produce_slot = slot; 
					produce_type = "Squid Spawn Egg";
				}	
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), WonderItems.BEE_ESSENCE, true)) 
				{ 
					produce = true; 
					produce_slot = slot; 
					produce_type = "Bee Spawn Egg";
				}	
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), WonderItems.VILLAGER_ESSENCE, true)) 
				{ 
					produce = true; 
					produce_slot = slot; 
					produce_type = "Villager Spawn Egg";
				}	
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), WonderItems.MOOSHROOM_ESSENCE, true)) 
				{ 
					produce = true; 
					produce_slot = slot; 
					produce_type = "Mooshroom Spawn Egg";
				}	
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), WonderItems.PANDA_ESSENCE, true)) 
				{ 
					produce = true; 
					produce_slot = slot; 
					produce_type = "Panda Spawn Egg";
				}	
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), WonderItems.HORSE_ESSENCE, true)) 
				{ 
					produce = true; 
					produce_slot = slot; 
					produce_type = "Horse Spawn Egg";
				}			
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), WonderItems.DOLPHIN_ESSENCE, true)) 
				{ 
					produce = true; 
					produce_slot = slot; 
					produce_type = "Dolphin Spawn Egg";
				}	
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), WonderItems.FOX_ESSENCE, true)) 
				{ 
					produce = true; 
					produce_slot = slot; 
					produce_type = "Fox Spawn Egg";
				}	
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), WonderItems.TURTLE_ESSENCE, true)) 
				{ 
					produce = true; 
					produce_slot = slot; 
					produce_type = "Turtle Spawn Egg";
				}	
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), WonderItems.PARROT_ESSENCE, true)) 
				{ 
					produce = true; 
					produce_slot = slot; 
					produce_type = "Parrot Spawn Egg";
				}	
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), WonderItems.POLAR_BEAR_ESSENCE, true)) 
				{ 
					produce = true; 
					produce_slot = slot; 
					produce_type = "Polar Bear Spawn Egg";
				}	
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), WonderItems.GUARDIAN_ESSENCE, true)) 
				{ 
					produce = true; 
					produce_slot = slot; 
					produce_type = "Guardian Spawn Egg";
				}	
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), WonderItems.LLAMA_ESSENCE, true)) 
				{ 
					produce = true; 
					produce_slot = slot; 
					produce_type = "Llama Spawn Egg";
				}	
				else if (SlimefunUtils.isItemSimilar(BlockStorage.getInventory(b).getItemInSlot(slot), WonderItems.SLIME_ESSENCE, true)) 
				{ 
					produce = true; 
					produce_slot = slot; 
					produce_type = "Slime Spawn Egg";
				}		
				if (egg && produce) 
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
					
					int speed_ratio = 240; 
					
					if (produce_type == "Cow Spawn Egg")
					{
						r = new MachineRecipe(speed_ratio / getSpeed(), new ItemStack[0], new ItemStack[] {COW_SPAWN_EGG});
					}
					else if (produce_type == "Pig Spawn Egg")
					{
						r = new MachineRecipe(speed_ratio / getSpeed(), new ItemStack[0], new ItemStack[] {PIG_SPAWN_EGG});
					}
					else if (produce_type == "Sheep Spawn Egg")
					{
						r = new MachineRecipe(speed_ratio / getSpeed(), new ItemStack[0], new ItemStack[] {SHEEP_SPAWN_EGG});
					}
					else if (produce_type == "Wolf Spawn Egg")
					{
						r = new MachineRecipe(speed_ratio / getSpeed(), new ItemStack[0], new ItemStack[] {WOLF_SPAWN_EGG});
					}
					else if (produce_type == "Squid Spawn Egg")
					{
						r = new MachineRecipe(speed_ratio / getSpeed(), new ItemStack[0], new ItemStack[] {SQUID_SPAWN_EGG});
					}
					else if (produce_type == "Bee Spawn Egg")
					{
						r = new MachineRecipe(speed_ratio/ getSpeed(), new ItemStack[0], new ItemStack[] {BEE_SPAWN_EGG});
					}
					else if (produce_type == "Villager Spawn Egg")
					{
						r = new MachineRecipe(speed_ratio / getSpeed(), new ItemStack[0], new ItemStack[] {VILLAGER_SPAWN_EGG});
					}
					else if (produce_type == "Mooshroom Spawn Egg")
					{
						r = new MachineRecipe(speed_ratio / getSpeed(), new ItemStack[0], new ItemStack[] {MOOSHROOM_SPAWN_EGG});
					}
					else if (produce_type == "Panda Spawn Egg")
					{
						r = new MachineRecipe(speed_ratio / getSpeed(), new ItemStack[0], new ItemStack[] {PANDA_SPAWN_EGG});
					}
					else if (produce_type == "Horse Spawn Egg")
					{
						r = new MachineRecipe(speed_ratio / getSpeed(), new ItemStack[0], new ItemStack[] {HORSE_SPAWN_EGG});
					}
					
					else if (produce_type == "Dolphin Spawn Egg")
					{
						r = new MachineRecipe(speed_ratio / getSpeed(), new ItemStack[0], new ItemStack[] {DOLPHIN_SPAWN_EGG});
					}
					else if (produce_type == "Fox Spawn Egg")
					{
						r = new MachineRecipe(speed_ratio / getSpeed(), new ItemStack[0], new ItemStack[] {FOX_SPAWN_EGG});
					}
					else if (produce_type == "Turtle Spawn Egg")
					{
						r = new MachineRecipe(speed_ratio / getSpeed(), new ItemStack[0], new ItemStack[] {TURTLE_SPAWN_EGG});
					}
					else if (produce_type == "Parrot Spawn Egg")
					{
						r = new MachineRecipe(speed_ratio / getSpeed(), new ItemStack[0], new ItemStack[] {PARROT_SPAWN_EGG});
					}
					else if (produce_type == "Rabbit Spawn Egg")
					{
						r = new MachineRecipe(speed_ratio / getSpeed(), new ItemStack[0], new ItemStack[] {RABBIT_SPAWN_EGG});
					}
					else if (produce_type == "Polar Bear Spawn Egg")
					{
						r = new MachineRecipe(speed_ratio / getSpeed(), new ItemStack[0], new ItemStack[] {POLAR_BEAR_SPAWN_EGG});
					}
					else if (produce_type == "Guardian Spawn Egg")
					{
						r = new MachineRecipe(speed_ratio / getSpeed(), new ItemStack[0], new ItemStack[] {GUARDIAN_SPAWN_EGG});
					}
					else if (produce_type == "Llama Spawn Egg")
					{
						r = new MachineRecipe(speed_ratio / getSpeed(), new ItemStack[0], new ItemStack[] {LLAMA_SPAWN_EGG});
					}
					else if (produce_type == "Slime Spawn Egg")
					{
						r = new MachineRecipe(speed_ratio / getSpeed(), new ItemStack[0], new ItemStack[] {SLIME_SPAWN_EGG});
					}
					
					
					if (produce_type != "")
					{
						BlockStorage.getInventory(b).replaceExistingItem(eggSlot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(eggSlot), 1));
						BlockStorage.getInventory(b).replaceExistingItem(produce_slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(produce_slot), 1));
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