package tech.obliviondevelop.SF;


import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import io.github.thebusybiscuit.slimefun4.implementation.items.backpacks.SlimefunBackpack;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.inventory.ItemUtils;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

public class Oven extends MultiBlockMachine
{
	private ItemStack item_stack = new ItemStack(Material.DISPENSER); 
	private String name = "&bOven";
	private String description = "&a&oSmelt it in an Oven";
	private String id = "OVEN";
	private Setup plugin;
	public ConsoleCommandSender console;
	
	private RecipeType OVEN = new RecipeType(new CustomItem(item_stack, name, description), id);
	
	
	public Oven(Setup plugin, Category category) 
	{
		super(
				category, 
				new SlimefunItemStack("OVEN", Material.CAULDRON, "&oOven", "", "&a&oYou can make a bunch of different yummies here!"),
				new ItemStack[] {null, new ItemStack(Material.NETHER_BRICK_FENCE), null, new ItemStack(Material.NETHER_BRICKS), new ItemStack(Material.DISPENSER), new ItemStack(Material.NETHER_BRICKS), new ItemStack(Material.NETHER_BRICKS), new ItemStack(Material.MAGMA_BLOCK), new ItemStack(Material.NETHER_BRICKS)},
				new ItemStack[0],
				BlockFace.DOWN
		);
		
		this.plugin = plugin;
		//console = SlimefunPlugin.instance.getServer().getConsoleSender();
	}
	
	public RecipeType asRecipeType() 
	{
		return OVEN;
	}
	
	@Override
	public List<ItemStack> getDisplayRecipes()
	{
		return recipes.stream().map(items -> items[0]).collect(Collectors.toList());
	}
	
	
    @Override
    public void onInteract(Player p, Block b)
    {
        	
        Block dispenser = b.getRelative(BlockFace.DOWN);    
        Dispenser disp = (Dispenser) dispenser.getState();
        Inventory inv = disp.getInventory();

        asRecipeType();
		List<ItemStack[]> inputs = RecipeType.getRecipeInputList(this);

		
      // this.plugin.console.sendMessage(String.valueOf(inputs.size())); 
        
        for (int i = 0; i < inputs.size(); i++) 
        {
        	
            if (isCraftable(inv, inputs.get(i)))
            {
                ItemStack output = RecipeType.getRecipeOutputList(this, inputs.get(i)).clone();
                if (SlimefunUtils.canPlayerUseItem(p, output, true)) 
                {
                    craft(inv, dispenser, p, b, output);
                }

                return;
            }
        }
        SlimefunPlugin.getLocalization().sendMessage(p, "machines.pattern-not-found", true);
      
    }
    
    
    private void craft(Inventory inv, Block dispenser, Player p, Block b, ItemStack output) {
        Inventory outputInv = findOutputInventory(output, dispenser, inv);

        if (outputInv != null) 
        {
            for (int j = 0; j < 9; j++) 
            {
                ItemStack item = inv.getContents()[j];

                if (item != null && item.getType() != Material.AIR) 
                {
                    ItemUtils.consumeItem(item, true);
                }
            }
            p.getWorld().playSound(b.getLocation(), Sound.BLOCK_WOODEN_BUTTON_CLICK_ON, 1, 1);

            outputInv.addItem(output);

        }
        else SlimefunPlugin.getLocalization().sendMessage(p, "machines.full-inventory", true);
    }

    private boolean isCraftable(Inventory inv, ItemStack[] recipe)
    {
        for (int j = 0; j < inv.getContents().length; j++) 
        {        	
            if (!SlimefunUtils.isItemSimilar(inv.getContents()[j], recipe[j], true)) 
            {
                if (SlimefunItem.getByItem(recipe[j]) instanceof SlimefunBackpack) 
                {
                    if (!SlimefunUtils.isItemSimilar(inv.getContents()[j], recipe[j], false))
                    {

                        return false;
                    }
                }
                else {
                    return false;
                }
            }
        }

        return true;
    }
    
}

