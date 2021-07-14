package tech.obliviondevelop.SF;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemConsumptionHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;

public class CustomFood extends SimpleSlimefunItem<ItemConsumptionHandler> {

	private final float food;
	private ItemSetting<Integer> saturation;

	public CustomFood(Category category, SlimefunItemStack item,  RecipeType recipe_type, ItemStack[] recipe, int food) 
	{
		super(category, item, recipe_type, recipe);
		
		this.food = food;
		saturation = new IntRangeSetting(this, "saturation-level", 0, food, Integer.MAX_VALUE);
		addItemSetting(saturation);
	}


    @Override
    public ItemConsumptionHandler getItemHandler() 
    {
        return (e, p, item) -> p.setSaturation(p.getSaturation() + saturation.getValue());
    }

}