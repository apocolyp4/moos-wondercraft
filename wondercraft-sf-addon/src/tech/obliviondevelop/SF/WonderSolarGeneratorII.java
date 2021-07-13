package tech.obliviondevelop.SF;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AGenerator;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class WonderSolarGeneratorII extends AGenerator
{
	
	public WonderSolarGeneratorII(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getInventoryTitle() {
		// TODO Auto-generated method stub
		return "Wonder Solar Generator II";
	}

	@Override
	public ItemStack getProgressBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getEnergyProduction() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void registerDefaultFuelTypes() {
		// TODO Auto-generated method stub
		
	}

    @Override
    public int getGeneratedOutput(Location l, Config data) 
    {
		if (!l.getWorld().isChunkLoaded(l.getBlockX() >> 4, l.getBlockZ() >> 4) || l.getBlock().getLightFromSky() != 15) 
		{
			return 2048;	
		}
		if (l.getWorld().getTime() < 12300 || l.getWorld().getTime() > 23850) 
		{
			return 4096;
		}
		
		return 2048;

    }
	
}
