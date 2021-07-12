package tech.obliviondevelop.SF;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

@Deprecated
public class CustomArmor extends ItemStack {
	
	public CustomArmor(ItemStack item, Color color) {
		super(item.clone());
		ItemMeta im = getItemMeta();
		((LeatherArmorMeta) im).setColor(color);
		setItemMeta(im);
	}

}