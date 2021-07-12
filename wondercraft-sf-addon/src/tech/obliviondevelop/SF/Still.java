package tech.obliviondevelop.SF;

import java.util.List;
import java.util.stream.Collectors;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import io.github.thebusybiscuit.slimefun4.implementation.items.armor.SlimefunArmorPiece;
import io.github.thebusybiscuit.slimefun4.implementation.items.backpacks.SlimefunBackpack;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;

import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import tech.obliviondevelop.SF.Lists.WonderItems;
import tech.obliviondevelop.SF.Lists.WonderMachines;
import me.mrCookieSlime.Slimefun.cscorelib2.inventory.ItemUtils;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

public class Still extends MultiBlockMachine
{
	private ItemStack item_stack = new ItemStack(Material.DISPENSER); 
	private String name = "&bStill";
	private String description = "&a&oDistill alochol";
	private String colour = "&7";
	private String id = "STILL";
	private Setup plugin;
	public ConsoleCommandSender console;
	
	private RecipeType STILL = new RecipeType(new CustomItem(item_stack, name, description), id);
	
	
	public Still(Setup plugin, Category category) 
	{
		super(
				category, 
				new SlimefunItemStack("STILL", Material.CAULDRON, "&oStill", "", "&a&oThis make alcohol!"),
				new ItemStack[] {new ItemStack(Material.IRON_BARS), new ItemStack(Material.NETHER_BRICK_FENCE), new ItemStack(Material.IRON_BARS), new ItemStack(Material.IRON_BARS), new ItemStack(Material.DISPENSER), new ItemStack(Material.IRON_BARS), new ItemStack(Material.IRON_BARS), new ItemStack(Material.CAULDRON), new ItemStack(Material.IRON_BARS)},
				new ItemStack[0],
				BlockFace.DOWN
		);
		
		this.plugin = plugin;
		//console = SlimefunPlugin.instance.getServer().getConsoleSender();
	}
	
	public RecipeType asRecipeType() 
	{
		return STILL;
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
        
        for (int i = 0; i < inputs.size(); i++) 
        {
        	
            if (isCraftable(inv, inputs.get(i)))
            {
                ItemStack output = RecipeType.getRecipeOutputList(this, inputs.get(i)).clone();
                if (SlimefunUtils.canPlayerUseItem(p, output, true)) {
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
                else 
                {
                    return false;
                }
            }
        }

        return true;
    }
}
