package tech.obliviondevelop.SF;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AGenerator;

import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class WonderSolarGeneratorIII extends SlimefunItem implements EnergyNetProvider
{
	
	public WonderSolarGeneratorIII(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

	}

    @Override
    public final int getCapacity() {
        return 0;
    }

    @Override
    public final boolean isChargeable() {
        return false;
    }

	
    @Override
    public int getGeneratedOutput(Location l, Config data) 
    {
		if (!l.getWorld().isChunkLoaded(l.getBlockX() >> 4, l.getBlockZ() >> 4) || l.getBlock().getLightFromSky() != 15) 
		{
			return 4096;	
		}
		if (l.getWorld().getTime() < 12300 || l.getWorld().getTime() > 23850) 
		{
			return 8192;
		}
		
		return 4096;

    }


}
