package tech.obliviondevelop.SF;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import me.mrCookieSlime.Slimefun.cscorelib2.reflection.ReflectionUtils;

/*

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.reflection.ReflectionUtils;
import tech.obliviondevelop.SF.EGPlant;
import tech.obliviondevelop.SF.Lists.WonderRecipeType;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;


public class CustomPotion extends EGPlant {


	public CustomPotion(Category category, SlimefunItemStack item,  PotionEffect potion, RecipeType recipe_type, ItemStack[] recipe) 
	{
		super(category, item, recipe_type, recipe, true);
	}

	@Override
	public void restoreHunger(Player p) 
	{

	}
	

}
*/


public class CustomPotion extends CustomItem {


	public CustomPotion(String name, PotionType type, PotionEffect effect, String... lore) {
		super(Material.POTION, name, lore);
		PotionMeta meta = (PotionMeta) getItemMeta();
		meta.setBasePotionData(new PotionData(type));
		meta.addCustomEffect(effect, true);
		setItemMeta(meta);
	}
	
	public CustomPotion(String name, Color color, PotionEffect effect, String... lore) {
		super(Material.POTION, name, lore);
		PotionMeta meta = (PotionMeta) getItemMeta();
		meta.setColor(color);
		meta.addCustomEffect(effect, true);
		setItemMeta(meta);
	}

}