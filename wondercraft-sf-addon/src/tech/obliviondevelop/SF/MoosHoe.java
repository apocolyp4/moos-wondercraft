package tech.obliviondevelop.SF;

import java.util.Arrays;
import java.util.List;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.entity.EntityChangeBlockEvent;


import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
import me.mrCookieSlime.CSCoreLibPlugin.general.String.StringUtils;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.cscorelib2.protection.ProtectableAction;

import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;

import me.mrCookieSlime.Slimefun.Objects.Category;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;


import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import tech.obliviondevelop.SF.Lists.WonderItems;

public class MoosHoe  extends SimpleSlimefunItem<BlockBreakHandler> implements NotPlaceable, DamageableItem
{

	private boolean damageOnUse;


    public MoosHoe(Category category) 
    {
        super(category, 
				new SlimefunItemStack("MOOS_M_HOE", WonderItems.MOOS_M_HOE), 
				RecipeType.MAGIC_WORKBENCH, 
				new ItemStack[] {null, WonderItems.MARBLE_BLOCK, null, null, WonderItems.MARBLE_BLOCK, null, WonderItems.WONDER_ALLOY, WonderItems.SUPER_TNT, WonderItems.WONDER_ALLOY}
				//new String[0], 
				//new Object[] {Arrays.asList("BEDROCK", "BARRIER", "COMMAND", "COMMAND_CHAIN", "COMMAND_REPEATING")}
				);

    }
    
    
    



    @Override
    public boolean isDamageable() {
        return damageOnUse;
    }






	@Override
	public BlockBreakHandler getItemHandler() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
