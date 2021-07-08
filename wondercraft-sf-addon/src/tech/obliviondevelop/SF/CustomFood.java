package tech.obliviondevelop.SF;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import tech.obliviondevelop.SF.EGPlant;
import tech.obliviondevelop.SF.Lists.WonderRecipeType;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class CustomFood extends EGPlant {

	private final float food;

	public CustomFood(Category category, SlimefunItemStack item,  RecipeType recipe_type, ItemStack[] recipe, int food) 
	{
		super(category, item, recipe_type, recipe, true);
		this.food = food;
	}

	@Override
	public void restoreHunger(Player p) 
	{
		int level = p.getFoodLevel() + (int) food;
		p.setFoodLevel(Math.min(level, 20));
		p.setSaturation(food);
	}
	

}