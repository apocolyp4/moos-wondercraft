package tech.obliviondevelop.SF;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.Dispenser;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.block.CreatureSpawner;

import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;



import me.mrCookieSlime.Slimefun.Lists.RecipeType;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import tech.obliviondevelop.SF.Lists.WonderItems;
import tech.obliviondevelop.SF.Lists.WonderMachines;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;

public class WonderMachine extends MultiBlockMachine
{
	
	private ItemStack item_stack = new ItemStack(Material.DISPENSER); 
	private String name = "&bWonder Machine";
	private String description = "&7Wonder Machines Things";
	private String colour = "&7";
	private String id = "WONDER_MACHINE";
	private Setup plugin;	
	private RecipeType WONDER_MACHINE = new RecipeType(new CustomItem(item_stack, name, description), id);
	
	public WonderMachine(Category category) 
	{
		super(
				category, 
				new SlimefunItemStack("WONDER_MACHINE", WonderMachines.WONDER_MACHINE), 
				new ItemStack[] {null, new ItemStack(Material.NETHER_BRICK_FENCE), null, new ItemStack(Material.EMERALD_BLOCK), new ItemStack(Material.DISPENSER), new ItemStack(Material.EMERALD_BLOCK), new ItemStack(Material.SEA_LANTERN), SlimefunItems.GOLD_24K_BLOCK, new ItemStack(Material.SEA_LANTERN)},
				new ItemStack[] {new CustomItem(WonderItems.WONDER_DUST, 4), new CustomItem(WonderItems.WONDER_ALLOY), 
						new CustomItem(WonderItems.WONDER_ALLOY, 4), new CustomItem(WonderItems.WONDER_ALLOY_BLOCK), 
						new CustomItem(WonderItems.WONDER_ALLOY_BLOCK), new ItemStack(Material.NETHER_STAR), 
						new CustomItem(SlimefunItems.CARBONADO, 9), WonderItems.CARBONADO_BLOCK,
						WonderItems.CARBONADO_BLOCK, new CustomItem(SlimefunItems.CARBONADO, 9)},
				BlockFace.DOWN
		);
	}

	@Override
	public List<ItemStack> getDisplayRecipes() {
		return recipes.stream().map(items -> items[0]).collect(Collectors.toList());
	}
	
	public RecipeType asRecipeType() 
	{
		return WONDER_MACHINE;
	}
	


	
	@Override
	public void onInteract(Player p, Block b) {
		Block dispBlock = b.getRelative(BlockFace.DOWN);
		Dispenser disp = (Dispenser) dispBlock.getState();
		Inventory inv = disp.getInventory();
		
		for (ItemStack current : inv.getContents()) {
			for (ItemStack convert : RecipeType.getRecipeInputs(this)) {
				if (convert != null && SlimefunUtils.isItemSimilar(current, convert, true)) {
					ItemStack adding = RecipeType.getRecipeOutput(this, convert);
					Inventory outputInv = findOutputInventory(adding, dispBlock, inv);
					if (outputInv != null) {
						ItemStack removing = current.clone();
						removing.setAmount(convert.getAmount());
						inv.removeItem(removing);
						outputInv.addItem(adding);
						p.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 1);
					}
					else SlimefunPlugin.getLocalization().sendMessage(p, "machines.full-inventory", true);
					
					return;
				}
			}
		}
		
		SlimefunPlugin.getLocalization().sendMessage(p, "machines.unknown-material", true);
	}
	
}