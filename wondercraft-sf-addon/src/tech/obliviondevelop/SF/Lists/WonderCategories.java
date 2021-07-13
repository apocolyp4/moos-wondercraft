package tech.obliviondevelop.SF.Lists;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import tech.obliviondevelop.SF.CustomSkull;

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
