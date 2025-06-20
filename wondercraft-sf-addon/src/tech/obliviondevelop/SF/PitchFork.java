package tech.obliviondevelop.SF;

import java.util.ArrayList;
import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;

import tech.obliviondevelop.SF.Lists.WonderItems;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class PitchFork extends SimpleSlimefunItem<ItemUseHandler> implements NotPlaceable
{
	private Setup plugin;
	ArrayList<Material> PITCH_FORK_WHITELIST = new ArrayList<Material>() {{
		add(Material.DIRT);
		add(Material.GRASS_BLOCK);
	}};
	
	public PitchFork (Setup plugin, Category category)
	{
	
		super(
				category, 
				new SlimefunItemStack("PITCH_FORK", WonderItems.PITCH_FORK), 
				RecipeType.MAGIC_WORKBENCH,
				new ItemStack[] {WonderItems.TITANIUM_INGOT, WonderItems.CHROMIUM_INGOT, null, null, WonderItems.CHROMIUM_INGOT, null, null, WonderItems.RUBBER, null}
		);
		this.plugin = plugin;
	}



    @Override
    public ItemUseHandler getItemHandler() 
    {

        return e ->
        {
    		if (SlimefunUtils.isItemSimilar(getItem(), WonderItems.PITCH_FORK, true) && PITCH_FORK_WHITELIST.contains(e.getClickedBlock().get().getType()))
    		{
	        	
	            Optional<Block> block = e.getClickedBlock();
	
	            if (block.isPresent()) 
	            {
	                //Block b = block.get();
	
	    			Location b = block.get().getLocation().add(-1, 0, -1);
	    			
	    		    for (int ix = 0; ix < 3; ix++) 
	    		    {
	    		    	for (int iy = 0; iy < 3; iy++)
	    		    	{
	    		    		if (PITCH_FORK_WHITELIST.contains(b.add(ix, 0, iy).getBlock().getType()) && b.add(ix, 1, iy).getBlock().getType() == Material.AIR) 
	    		    		{
	    		    			b.add(ix, 0, iy).getBlock().setType(Material.FARMLAND);
	    		    		}
	    		    	}
	    		    }
	            }
    		}

            e.cancel();
        };
    }


}