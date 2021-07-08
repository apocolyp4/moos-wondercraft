package tech.obliviondevelop.SF;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;


import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class ChocolateBar extends SlimefunItem{
	
	public ChocolateBar(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, recipeType, recipe);
	}

}