package tech.obliviondevelop.SF;


import java.util.List;
import java.util.stream.Collectors;

import java.io.IOException;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import tech.obliviondevelop.SF.Lists.WonderItems;
import tech.obliviondevelop.SF.Lists.WonderMachines;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

public class CoralCrusher extends MultiBlockMachine
{
	private ItemStack item_stack = new ItemStack(Material.DISPENSER); 
	private String name = "&bCoral Crusher";
	private String description = "&7Breaks Down Coral";
	private String colour = "&7";
	private String id = "CORAL_CRUSHER";
	private Setup plugin;
	
	//Adding the extra text needed for name and description
	//name = "&b" + name;
	//description  = "&7" + description; 
	
	private RecipeType CORAL_CRUSHER = new RecipeType(new CustomItem(item_stack, name, description), id);
	
	public CoralCrusher(Setup plugin, Category category) 
	{
		super(
				category, 
				new SlimefunItemStack("CORAL_CRUSHER", WonderMachines.CORAL_CRUSHER), 
				new ItemStack[] {null, null, null, new ItemStack(Material.PRISMARINE_SLAB), new ItemStack(Material.IRON_BARS), new ItemStack(Material.PRISMARINE_SLAB), new ItemStack(Material.PRISMARINE_BRICKS), new CustomItem(Material.DISPENSER, "Dispenser (Facing up)"), new ItemStack(Material.PRISMARINE_BRICKS)},
				new ItemStack[]
				{
					new ItemStack(Material.BRAIN_CORAL_BLOCK, 4), new ItemStack(WonderItems.BRAIN_CORAL_DUST),
					new ItemStack(Material.BUBBLE_CORAL_BLOCK, 4), new ItemStack(WonderItems.BUBBLE_CORAL_DUST),
					new ItemStack(Material.FIRE_CORAL_BLOCK, 4), new ItemStack(WonderItems.FIRE_CORAL_DUST),
					new ItemStack(Material.HORN_CORAL_BLOCK, 4), new ItemStack(WonderItems.HORN_CORAL_DUST),
					new ItemStack(Material.TUBE_CORAL_BLOCK, 4), new ItemStack(WonderItems.TUBE_CORAL_DUST),
				},
				BlockFace.SELF
		);

		this.plugin = plugin;
		//Slimefun.registerResearch(new NamespacedKey(SlimefunPlugin.instance, "Coral_Crusher"), 600, "Coral_Crusher", 30, getItem());
	}
	
	
	@Override
	public List<ItemStack> getDisplayRecipes() {
		return recipes.stream().map(items -> items[0]).collect(Collectors.toList());
	}
	
	public RecipeType asRecipeType() 
	{
		return CORAL_CRUSHER;
	}
	
	
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
