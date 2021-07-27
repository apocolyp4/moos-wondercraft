package tech.obliviondevelop.SF;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;


public class SkullItem extends ItemStack {
	
	String owner;
	
	public SkullItem(OfflinePlayer player) {
		super(new ItemStack(MaterialHook.parse("PLAYER_HEAD", "SKULL_ITEM")));
		this.owner = player.getName();
		SkullMeta meta = (SkullMeta) getItemMeta();
		meta.setOwningPlayer(player);
		setItemMeta(meta);
	}
	

	public SkullItem(String name, String owner) {
		super(new ItemStack(MaterialHook.parse("PLAYER_HEAD", "SKULL_ITEM")));
		
		/*
		if (ReflectionUtils.isVersion("v1_10_", "v1_11_", "v1_12_")) {
			setDurability((short) 3);
		}
		*/
		
		ItemMeta im = getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

		((SkullMeta) im).setOwner(owner);
		
		setItemMeta(im);
		this.owner = owner;
	}


	public SkullItem(String owner) {
		super(new ItemStack(MaterialHook.parse("PLAYER_HEAD", "SKULL_ITEM")));

		/*
		if (ReflectionUtils.isVersion("v1_10_", "v1_11_", "v1_12_")) {
			setDurability((short) 3);
		}
		*/
		
		ItemMeta im = getItemMeta();
		((SkullMeta) im).setOwner(owner);
		setItemMeta(im);
		this.owner = owner;
	}
	

	public SkullItem(String name, String owner, String... lore) {
		super(new ItemStack(MaterialHook.parse("PLAYER_HEAD", "SKULL_ITEM")));

		/*
		if (ReflectionUtils.isVersion("v1_10_", "v1_11_", "v1_12_")) {
			setDurability((short) 3);
		}
		*/
		
		ItemMeta im = getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		List<String> lines = new ArrayList<>();
		for (String line: lore) {
			lines.add(ChatColor.translateAlternateColorCodes('&', line));
		}
		im.setLore(lines);
		((SkullMeta) im).setOwner(owner);
		setItemMeta(im);
		this.owner = owner;
	}
	
	public String getOwner() {
		return this.owner;
	}

}