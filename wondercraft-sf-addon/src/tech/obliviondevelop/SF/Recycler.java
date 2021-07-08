package tech.obliviondevelop.SF;


import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import tech.obliviondevelop.SF.Lists.WonderItems;
import tech.obliviondevelop.SF.Lists.WonderMachines;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

public class Recycler extends MultiBlockMachine
{
	
	private ItemStack item_stack = new ItemStack(Material.DISPENSER); 
	private String name = "&bRecycler";
	private String description = "&7Recycles";
	private String colour = "&7";
	private String id = "RECYCLER";
	private Setup plugin;	
	private RecipeType RECYCLER = new RecipeType(new CustomItem(item_stack, name, description), id);
	
	public Recycler(Category category) 
	{
		super(
				category, 
				new SlimefunItemStack("RECYCLER", WonderMachines.RECYCLER), 
				new ItemStack[] {null, null, null, new ItemStack(Material.STONE_SLAB), new ItemStack(Material.DARK_OAK_FENCE), new ItemStack(Material.STONE_SLAB), new ItemStack(Material.MOSSY_STONE_BRICKS), new ItemStack(Material.DISPENSER), new ItemStack(Material.MOSSY_STONE_BRICKS)},
						new ItemStack[] {new ItemStack(Material.FLINT, 16), new ItemStack(Material.CHARCOAL),
						new ItemStack(Material.CLAY, 4), new ItemStack(Material.COBBLESTONE),
						new ItemStack(Material.SANDSTONE), new ItemStack(Material.SAND),
						new CustomItem(SlimefunItems.STONE_CHUNK, 4), new ItemStack(Material.SAND)},
				BlockFace.SELF
		);
	}

	@Override
	public List<ItemStack> getDisplayRecipes() {
		return recipes.stream().map(items -> items[0]).collect(Collectors.toList());
	}
	
	public RecipeType asRecipeType() 
	{
		return RECYCLER;
	}
	
	/*
	private static boolean isDoubleDropsEnabled() 
	{
		return (boolean) Slimefun.getItemValue("RECYCLER", "double-ores");
	}
	*/

	
	@Override
	public void onInteract(Player p, Block b) {
		Block dispBlock = b.getRelative(BlockFace.DOWN);
		Dispenser disp = (Dispenser) dispBlock.getState();
		Inventory inv = disp.getInventory();
		
		for (ItemStack current : inv.getContents()) {
			for (ItemStack convert : RecipeType.getRecipeInputs(this)) {
				if (convert != null && SlimefunUtils.isItemSimilar(current, convert, true)) {
					ItemStack adding = RecipeType.getRecipeOutput(this, convert);
					Inventory outputInv = findOutputInventory(adding, dispBlock, inv);
					if (outputInv != null) {
						ItemStack removing = current.clone();
						removing.setAmount(convert.getAmount());
						inv.removeItem(removing);
						outputInv.addItem(adding);
						p.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 1);
					}
					else SlimefunPlugin.getLocalization().sendMessage(p, "machines.full-inventory", true);
					
					return;
				}
			}
		}
		
		SlimefunPlugin.getLocalization().sendMessage(p, "machines.unknown-material", true);
	}

    
}
