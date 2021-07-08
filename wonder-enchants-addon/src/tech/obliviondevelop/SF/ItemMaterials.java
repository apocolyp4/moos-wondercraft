package tech.obliviondevelop.SF;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


public class ItemMaterials 
{

	public static void main(String[] args)
	{
	}
	
	public Material get_material(String id)
	{
		Material the_material = Material.BRICK;
				
		for (Material material_type : Material.values()) 
		{
		    if (id == material_type.name())
		    {
		    	the_material = material_type;
		    	break;
		    }
		}
		
		return the_material;
	}
	
	public ItemStack get_slimefun_item(String id)
	{
		SlimefunItem the_material = SlimefunItem.getByID(id);	
		ItemStack item_stack = the_material.getItem();
		return item_stack;
	}
}
