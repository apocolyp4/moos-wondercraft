package tech.obliviondevelop.SF.Lists;

import java.util.logging.Level;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import tech.obliviondevelop.SF.Setup;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;

public class WonderCategories 
{
	
	public void WonderCategories() 
	{

	}
	
	private static ItemStack getSkull(String texture) {
		try {
			return CustomSkull.getItem(texture);
		}
		catch(Exception x) {
			//Slimefun.getLogger().log(Level.SEVERE, "An Error occured while initializing the Items for Slimefun " , x);
			
			return new ItemStack(Material.PLAYER_HEAD);
		}
	}
}
