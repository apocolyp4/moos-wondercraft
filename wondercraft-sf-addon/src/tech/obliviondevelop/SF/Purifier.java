package tech.obliviondevelop.SF;


import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import tech.obliviondevelop.SF.Lists.WonderItems;
import tech.obliviondevelop.SF.Lists.WonderMachines;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.cscorelib2.protection.ProtectableAction;


public class Purifier extends MultiBlockMachine
{
	
	private ItemStack item_stack = new ItemStack(Material.DISPENSER); 
	private String name = "&bPurifier";
	private String description = "&7Purifies";
	private String colour = "&7";
	private String id = "PURIFIER";
	private Setup plugin;	
	private RecipeType PURIFIER = new RecipeType(new CustomItem(item_stack, name, description), id);
	public ConsoleCommandSender console;
	
	public Purifier(Category category) 
	{
		super(
				category, 
				new SlimefunItemStack("PURIFIER", WonderMachines.PURIFIER), 
				new ItemStack[] {null, new ItemStack(Material.NETHER_BRICK_FENCE), null, new ItemStack(Material.IRON_TRAPDOOR), new ItemStack(Material.DISPENSER), new ItemStack(Material.NETHER_BRICK_FENCE), new ItemStack(Material.HOPPER), new ItemStack(Material.CAULDRON), new ItemStack(Material.HOPPER)},
				new ItemStack[0],
				BlockFace.DOWN
		);
		//console = SlimefunPlugin.instance.getServer().getConsoleSender();
	}

	
	public RecipeType asRecipeType() 
	{
		return PURIFIER;
	}
	
	@Override
	public List<ItemStack> getDisplayRecipes()
	{
		return recipes.stream().map(items -> items[0]).collect(Collectors.toList());
	}
	
	public ItemStack get_random_item()
	{
		ItemStack adding = new ItemStack(Material.GLOWSTONE_DUST);
		Random r = new Random();
		int chance =  r.nextInt(100); // get random number to pick which dust to give
		
		if (chance >= 0 && chance <= 20)
		{
			adding = WonderItems.TITANIUM_DUST;
		}
		else if (chance >= 21 && chance <= 40)
		{
			adding = WonderItems.CHROMIUM_DUST;
		}
		else if (chance >= 40 && chance <= 47)
		{
			adding = WonderItems.DUBNIUM;
		}
		else if (chance >= 47 && chance <= 59)
		{
			adding = new ItemStack(Material.GRAVEL);
		}							
		else if (chance >= 60 && chance <= 79)
		{
			adding = SlimefunItems.STONE_CHUNK;
		}		
		else if (chance >= 80 && chance <= 99)
		{
			adding = new ItemStack(Material.CLAY_BALL);
		}	
		
		return adding;
	}
	
	@Override
	public void onInteract(Player p, Block b) 
	{
		//if (SlimefunPlugin.getProtectionManager().canAccessChest(p.getUniqueId(), b, true)) 
		//if (SlimefunPlugin.getProtectionManager().hasPermission(p.getUniqueId(), b, true)) 
			
		if (SlimefunPlugin.getProtectionManager().hasPermission(p, b.getLocation(), ProtectableAction.INTERACT_BLOCK))
		{		

				Dispenser disp = (Dispenser) b.getRelative(BlockFace.DOWN).getState();
				Inventory inv = disp.getInventory();

				for (ItemStack current: inv.getContents()) 
				{
					if (current != null) 
					{
						if (SlimefunUtils.isItemSimilar(current, SlimefunItems.PURE_ORE_CLUSTER, true)) 
						{
			
							if (inv.firstEmpty() != -1) {
								ItemStack removing = current.clone();
								removing.setAmount(1);
								inv.removeItem(removing);
								inv.addItem(get_random_item());
								p.getWorld().playSound(b.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1, 1);
								p.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, Material.WATER);
							}
							else SlimefunPlugin.getLocalization().sendMessage(p, "machines.full-inventory", true);
							
							return;
			
						}
						
					}
			
				}
			
				SlimefunPlugin.getLocalization().sendMessage(p, "machines.unknown-material", true);
			}
		

	}
		
}

