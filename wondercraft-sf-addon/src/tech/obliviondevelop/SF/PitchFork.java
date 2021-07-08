package tech.obliviondevelop.SF;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
import me.mrCookieSlime.CSCoreLibPlugin.events.ItemUseEvent;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;


import me.mrCookieSlime.Slimefun.api.BlockStorage;

import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import tech.obliviondevelop.SF.Lists.WonderItems;
import tech.obliviondevelop.SF.Lists.WonderMachines;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
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
	public ItemUseHandler getItemHandler() {
		// TODO Auto-generated method stub
		return null;
	}
	

/*
    @Override
    public ItemUseHandler getItemHandler() 
    {

        return e ->
        {
    		if (SlimefunUtils.isItemSimilar(item, WonderItems.PITCH_FORK, true) && PITCH_FORK_WHITELIST.contains(e.getClickedBlock().get().getType()))
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
    */

}