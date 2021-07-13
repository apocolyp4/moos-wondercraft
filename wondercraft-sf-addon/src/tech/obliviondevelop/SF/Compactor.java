package tech.obliviondevelop.SF;


import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
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
import tech.obliviondevelop.SF.Lists.WonderMachines;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;



public class Compactor extends MultiBlockMachine
{
	
	private ItemStack item_stack = new ItemStack(Material.DISPENSER); 
	private String name = "&bCompactor";
	private String description = "&7Compacts";
	private String colour = "&7";
	private String id = "COMPACTOR";
	private Setup plugin;	
	private RecipeType COMPACTOR = new RecipeType(new CustomItem(item_stack, name, description), id);
	
	public Compactor(Category category)
	{
		super(
				category, 
				new SlimefunItemStack("COMPACTOR", WonderMachines.COMPACTOR), 
				new ItemStack[] {null, new ItemStack(Material.OAK_FENCE), null, new ItemStack(Material.PURPUR_BLOCK), new ItemStack(Material.DISPENSER), new ItemStack(Material.PURPUR_BLOCK), new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.PURPUR_BLOCK), new ItemStack(Material.IRON_BLOCK)},
				new ItemStack[] {new CustomItem(SlimefunItems.IRON_DUST, 4), new ItemStack(Material.IRON_ORE),
						new ItemStack(Material.CHARCOAL, 8), new ItemStack(Material.COAL_ORE),
						new CustomItem(SlimefunItems.GOLD_DUST, 4), new ItemStack(Material.GOLD_ORE),
						new ItemStack(Material.REDSTONE, 8), new ItemStack(Material.REDSTONE_ORE),
				        new ItemStack(Material.LAPIS_LAZULI, 8), new ItemStack(Material.LAPIS_ORE)}, 

				BlockFace.DOWN);
	}

	@Override
	public List<ItemStack> getDisplayRecipes() {
		return recipes.stream().map(items -> items[0]).collect(Collectors.toList());
	}
	
	public RecipeType asRecipeType() 
	{
		return COMPACTOR;
	}
	
	/*
	private static boolean isDoubleDropsEnabled() 
	{
		return (boolean) Slimefun.getItemValue("COMPACTOR", "double-ores");
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
	
	
	
	
