  
package tech.obliviondevelop.SF;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;


import io.github.thebusybiscuit.slimefun4.implementation.items.tools.ExplosiveTool;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import tech.obliviondevelop.SF.Lists.WonderItems;

/**
 * This {@link SlimefunItem} is a super class for items like the {@link ExplosivePickaxe} or {@link ExplosiveShovel}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see ExplosivePickaxe
 * @see ExplosiveShovel
 *
 */
class MoosPickaxe extends ExplosiveTool
{


    public MoosPickaxe(Category category) 
    {
        super(category, 
				new SlimefunItemStack("MOOS_M_PICKAXE", WonderItems.MOOS_M_PICKAXE), 
				RecipeType.MAGIC_WORKBENCH,
				new ItemStack[] 
				{
					WonderItems.WONDER_ALLOY, WonderItems.SUPER_TNT, WonderItems.WONDER_ALLOY,
					null, WonderItems.MARBLE_BLOCK, null, 
					null, WonderItems.MARBLE_BLOCK, null
					
				}
        
				);
        getItem().addUnsafeEnchantment(Enchantment.DURABILITY, 15);

    }
    

    


}