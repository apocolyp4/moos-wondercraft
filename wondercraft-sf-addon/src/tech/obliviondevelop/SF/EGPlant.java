package tech.obliviondevelop.SF;

import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
//import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.HandledBlock;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class EGPlant extends SlimefunItem 
{

	private static final int FOOD = 2;

	private final boolean edible;

	public EGPlant(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, boolean edible)
	{
		super(category, item, recipeType, recipe);
		this.edible = edible;
	}

	public boolean isEdible()
	{
		return this.edible;
	}

	public void restoreHunger(Player p) 
	{
		int level = p.getFoodLevel() + FOOD;
		p.setFoodLevel(Math.min(level, 20));
		p.setSaturation(FOOD);
	}

}