package tech.obliviondevelop.SF;

import org.bukkit.Material;


public class MaterialHook 
{
	
	public static Material parse(String name, String legacy) 
	{
		return Material.valueOf(name);
	}

}