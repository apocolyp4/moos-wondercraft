package tech.obliviondevelop.SF;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.core.categories.LockedCategory;



import me.mrCookieSlime.Slimefun.Lists.RecipeType;

import me.mrCookieSlime.Slimefun.Objects.Category;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.cscorelib2.skull.SkullItem;
import io.github.thebusybiscuit.slimefun4.core.SlimefunRegistry;
import io.github.thebusybiscuit.slimefun4.core.researching.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;

public class EnchantSetup extends JavaPlugin implements SlimefunAddon, Listener 
{
	private static WonderScribe wonder_scribe; 
	public static Category MOOS_MACHINES;
	public static Category ENCHANTING_MATERIALS;
	
	private static EnchantSetup setup = null;
	
	private final SlimefunRegistry registry = new SlimefunRegistry();
	
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

	public Category create_category(String id, String name, String lore, String item, int tier, boolean locked)
	{
		Category new_category;
		CustomItem custom_item;
		NamespacedKey building_Id = new NamespacedKey(this, id);
		String[] material_details;
		material_details = item.split("\\.");

		String material_detail;
		String material_name = "";
		 
		if (material_details.length > 1)
		{
			material_detail = material_details[0];
			material_name = material_details[1];
			
		}
		else
		{
			material_detail = item;
		}

		
		switch(material_detail)
		{
	  		case "Material":	  		
	  			custom_item = new CustomItem(get_material(material_name), name, "", lore);
	  			break;
			case "SlimefunItems":
				custom_item = new CustomItem(get_slimefun_item(material_name), name, "", lore);
				break;
			default:
				custom_item = new CustomItem(SkullItem.fromBase64(item), name);
		}
		
		if (!locked)
		{
			new_category = new Category(building_Id, custom_item, tier);	
		}
		else
		{
			new_category = new LockedCategory(building_Id, custom_item, tier);	
		}
		
		return new_category;
	}
	
	@Override
	public void onEnable()
	{
		ConsoleCommandSender console = getServer().getConsoleSender();
		console.sendMessage("---------------------------------------------");
		console.sendMessage("Wondercraft Enchants Slimefun Addon's Loading...");
				
		
		// CATEGORIES
		Category ENCHANTING = create_category("Enchanting", "&bEnchanting", "&a> Click to open", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjJmNzkwMTZjYWQ4NGQxYWUyMTYwOWM0ODEzNzgyNTk4ZTM4Nzk2MWJlMTNjMTU2ODI3NTJmMTI2ZGNlN2EifX19", 1, true);
		Category ADVANCED_ENCHANTING = create_category("Advanced_Enchanting", "&bAdvanced Enchanting", "&a> Click to open", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2Y2YmY5NThhYmQ3ODI5NWVlZDZmZmMyOTNiMWFhNTk1MjZlODBmNTQ5NzY4MjllYTA2ODMzN2MyZjVlOCJ9fX0=", 1, true);
		ENCHANTING_MATERIALS = create_category("ENCHANTING_MATERIALS", "&4Enchanting Materials", "", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjI3Mzc0MGQ0NTRkZTk2MjQ4NDcxMmY5ODM1ZTM1MTE5YjM3YWI4NjdmYTY5ODJkNWNjMWYzMzNjMjMzNGU1OSJ9fX0=", 1, false);
		/*         Items         */
		
		ItemStack ENCHANTING_DUST = new CustomItem(Material.NETHER_STAR, "&bEnchanting Dust", "&dUsed in creating enchantments","", "&c&lPremium Only, May be won by voting or","&c&lpurchased in /buy");
		ItemStack CONDENSED_ENCHANTING_DUST = new CustomItem(Material.NETHER_STAR, "&bCondensed Enchanting Dust", "&dUsed in creating enchantments","", "&c&lPremium Only, May be won by voting or","&c&lpurchased in /buy");
		ItemStack BOOK_BINDING = new CustomItem(Material.BOOK, "&bBook Binding");
		ItemStack GLUE = new CustomItem(Material.CLAY_BALL, "&bGlue");
		ItemStack ENCHANTING_TOME = null;
		ItemStack WONDER_DUST = null;
		
		try {
			WONDER_DUST = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY5ZTNlNmU1YjJiOTJmMGJlYjM2OGI3MzhiOTkzZDdiYTIyNWJmOWJiMjc1OGJmYzlmYzJkYWJhNGE1YTdkIn19fQ=="), "&bWonder Dust");
			ENCHANTING_TOME = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGM2ODU5MWI5MTE5OGU2ZTEzODQ4NjBjZjczMTZmMjllMDM4YjY5ZGVhOTg0YzczZjZjMWNiOTUwYTE0ZCJ9fX0="), "&bEnchanting Tome");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ItemStack[] item_recipe;
		SlimefunItemStack slimefun_stack;		
		
		
		
		item_recipe = new ItemStack[] {new ItemStack(Material.STRING), new ItemStack(Material.LEATHER), null, GLUE, new CustomItem(SlimefunItems.CLOTH), null, new ItemStack(Material.STRING), new ItemStack(Material.LEATHER), null};
		slimefun_stack = new SlimefunItemStack("BOOK_BINDING", BOOK_BINDING);
		new SlimefunItem(ENCHANTING_MATERIALS, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe)
		.register(this);
		
		item_recipe = new ItemStack[] {new ItemStack(Material.SLIME_BALL), new ItemStack(Material.CLAY_BALL), null, null, null, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("GLUE", GLUE);
		new SlimefunItem(ENCHANTING_MATERIALS, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe)
		.register(this);
		
		item_recipe = new ItemStack[] {null, null, null, null, new ItemStack(Material.BEDROCK), null, null, null, null};
		slimefun_stack = new SlimefunItemStack("ENCHANTING_DUST", ENCHANTING_DUST);
		new SlimefunItem(ENCHANTING_MATERIALS, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe)
		.register(this);		
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, null, null, null, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("CONDENSED_ENCHANTING_DUST", CONDENSED_ENCHANTING_DUST);
		new SlimefunItem(ENCHANTING_MATERIALS, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe)
		.register(this);
		
		wonder_scribe = new WonderScribe(this, ENCHANTING_MATERIALS);
        wonder_scribe.register(this);
        
        /* Enchantments */
		
	    ItemStack DURABILITY_3 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Unbreaking 3", "Disenchant to a book");
	    ItemStack DURABILITY_5 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Unbreaking 5", "Disenchant to a book");
	    ItemStack DURABILITY_7 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Unbreaking 7", "Disenchant to a book");
	    ItemStack DURABILITY_9 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Unbreaking 9", "Disenchant to a book");
	    ItemStack DURABILITY_13 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Unbreaking 13", "Disenchant to a book");
	    ItemStack DURABILITY_15 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Unbreaking 15", "Disenchant to a book");
	    ItemStack PROTECTION_ENVIRONMENTAL_3 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Protection 3", "Disenchant to a book");
	    ItemStack PROTECTION_ENVIRONMENTAL_5 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Protection 5", "Disenchant to a book");
	    ItemStack PROTECTION_ENVIRONMENTAL_7 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Protection 7", "Disenchant to a book");
	    ItemStack PROTECTION_ENVIRONMENTAL_9 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Protection 9", "Disenchant to a book");
	    ItemStack PROTECTION_ENVIRONMENTAL_13 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Protection 13", "Disenchant to a book");
	    ItemStack PROTECTION_ENVIRONMENTAL_15 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Protection 15", "Disenchant to a book");
	    ItemStack PROTECTION_EXPLOSIONS_3 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Blast Protection 3", "Disenchant to a book");
	    ItemStack PROTECTION_EXPLOSIONS_5 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Blast Protection 5", "Disenchant to a book");
	    ItemStack PROTECTION_EXPLOSIONS_7 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Blast Protection 7", "Disenchant to a book");
	    ItemStack PROTECTION_EXPLOSIONS_9 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Blast Protection 9", "Disenchant to a book");
	    ItemStack PROTECTION_EXPLOSIONS_13 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Blast Protection 13", "Disenchant to a book");
	    ItemStack PROTECTION_EXPLOSIONS_15 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Blast Protection 15", "Disenchant to a book");
	    ItemStack PROTECTION_PROJECTILE_3 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Projectile Protection 3", "Disenchant to a book");
	    ItemStack PROTECTION_PROJECTILE_5 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Projectile Protection 5", "Disenchant to a book");
	    ItemStack PROTECTION_PROJECTILE_7 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Projectile Protection 7", "Disenchant to a book");
	    ItemStack PROTECTION_PROJECTILE_9 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Projectile Protection 9", "Disenchant to a book");
	    ItemStack PROTECTION_PROJECTILE_13 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Projectile Protection 13", "Disenchant to a book");
	    ItemStack PROTECTION_PROJECTILE_15 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Projectile Protection 15", "Disenchant to a book");
	    ItemStack PROTECTION_FALLING_2 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Feather Falling 2", "Disenchant to a book");
	    ItemStack PROTECTION_FALLING_3 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Feather Falling 3", "Disenchant to a book");
	    ItemStack PROTECTION_FALLING_4 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Feather Falling 4", "Disenchant to a book");
	    ItemStack MENDING_BOOK = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Mending", "Disenchant to a book");
	    ItemStack LUCK_2 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Luck of the Sea 2", "Disenchant to a book");
	    ItemStack LUCK_4 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Luck of the Sea 4", "Disenchant to a book");
	    ItemStack LUCK_6 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Luck of the Sea 6", "Disenchant to a book");
	    ItemStack LOOT_BONUS_BLOCKS_3 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Fortune 3", "Disenchant to a book");
	    ItemStack LOOT_BONUS_BLOCKS_5 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Fortune 5", "Disenchant to a book");
	    ItemStack LOOT_BONUS_BLOCKS_7 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Fortune 7", "Disenchant to a book");
	    ItemStack LOOT_BONUS_BLOCKS_9 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Fortune 9", "Disenchant to a book");
	    ItemStack LOOT_BONUS_BLOCKS_13 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Fortune 13", "Disenchant to a book");
	    ItemStack LOOT_BONUS_BLOCKS_15 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Fortune 15", "Disenchant to a book");
	    ItemStack LOOT_BONUS_MOBS_3 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Looting 3", "Disenchant to a book");
	    ItemStack LOOT_BONUS_MOBS_5 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Looting 5", "Disenchant to a book");
	    ItemStack LOOT_BONUS_MOBS_7 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Looting 7", "Disenchant to a book");
	    ItemStack LOOT_BONUS_MOBS_9 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Looting 9", "Disenchant to a book");
	    ItemStack LOOT_BONUS_MOBS_13 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Looting 13", "Disenchant to a book");
	    ItemStack LOOT_BONUS_MOBS_15 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Looting 15", "Disenchant to a book");
	    ItemStack DIG_SPEED_3 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Efficency 3", "Disenchant to a book");
	    ItemStack DIG_SPEED_5 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Efficency 5", "Disenchant to a book");
	    ItemStack DIG_SPEED_7 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Efficency 7", "Disenchant to a book");
	    ItemStack THORNS_3 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Thorns 3", "Disenchant to a book");
	    ItemStack THORNS_5 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Thorns 5", "Disenchant to a book");
	    ItemStack THORNS_7 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Thorns 7", "Disenchant to a book");
	    ItemStack SILK_TOUCH = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Silk Touch", "Disenchant to a book");
	    ItemStack ARROW_INFINITE = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Infinity", "Disenchant to a book");
	    ItemStack ARROW_FIRE = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Flame", "Disenchant to a book");
	    ItemStack ARROW_DAMAGE_2 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Power 2", "Disenchant to a book");
	    ItemStack ARROW_DAMAGE_4 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Power 4", "Disenchant to a book");
	    ItemStack ARROW_DAMAGE_6 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Power 6", "Disenchant to a book");
	    ItemStack ARROW_DAMAGE_8 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Power 8", "Disenchant to a book");
	    ItemStack SWEEPING_EDGE_2 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Sweeping Edge 2", "Disenchant to a book");
	    ItemStack SWEEPING_EDGE_4 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Sweeping Edge 4", "Disenchant to a book");
	    ItemStack SWEEPING_EDGE_6 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Sweeping Edge 6", "Disenchant to a book");
	    ItemStack DAMAGE_UNDEAD_2 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Smite 2", "Disenchant to a book");
	    ItemStack DAMAGE_UNDEAD_4 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Smite 4", "Disenchant to a book");
	    ItemStack DAMAGE_UNDEAD_6 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Smite 6", "Disenchant to a book");
	    ItemStack DAMAGE_ARTHROPODS_2 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Bane of Arthropods 2", "Disenchant to a book");
	    ItemStack DAMAGE_ARTHROPODS_4 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Bane of Arthropods 4", "Disenchant to a book");
	    ItemStack DAMAGE_ARTHROPODS_6 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Bane of Arthropods 6", "Disenchant to a book");
	    ItemStack KNOCKBACK_3 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Knockback 3", "Disenchant to a book");
	    ItemStack KNOCKBACK_5 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Knockback 5", "Disenchant to a book");
	    ItemStack KNOCKBACK_7 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Knockback 7", "Disenchant to a book");
	    ItemStack KNOCKBACK_9 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Knockback 9", "Disenchant to a book");
	    ItemStack KNOCKBACK_13 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Knockback 13", "Disenchant to a book");
	    ItemStack KNOCKBACK_15 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Knockback 15", "Disenchant to a book");
	    ItemStack ARROW_KNOCKBACK_3 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Punch 3", "Disenchant to a book");
	    ItemStack ARROW_KNOCKBACK_5 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Punch 5", "Disenchant to a book");
	    ItemStack ARROW_KNOCKBACK_7 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Punch 7", "Disenchant to a book");
	    ItemStack ARROW_KNOCKBACK_9 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Punch 9", "Disenchant to a book");
	    ItemStack ARROW_KNOCKBACK_13 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Punch 13", "Disenchant to a book");
	    ItemStack ARROW_KNOCKBACK_15 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Punch 15", "Disenchant to a book");
	    ItemStack DAMAGE_ALL_3 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Sharpness 3", "Disenchant to a book");
	    ItemStack DAMAGE_ALL_5 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Sharpness 5", "Disenchant to a book");
	    ItemStack DAMAGE_ALL_7 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Sharpness 7", "Disenchant to a book");
	    ItemStack DAMAGE_ALL_9 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Sharpness 9", "Disenchant to a book");
	    ItemStack DAMAGE_ALL_13 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Sharpness 13", "Disenchant to a book");
	    ItemStack DAMAGE_ALL_15 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Sharpness 15", "Disenchant to a book");
	    ItemStack QUICK_CHARGE_1 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Quick Charge 1", "Disenchant to a book");
	    ItemStack QUICK_CHARGE_2 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Quick Charge 2", "Disenchant to a book");
	    ItemStack QUICK_CHARGE_3 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Quick Charge 3", "Disenchant to a book");
	    ItemStack PIERCING_2 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Piercing 2", "Disenchant to a book");
	    ItemStack PIERCING_4 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Piercing 4", "Disenchant to a book");
	    ItemStack PIERCING_6 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Piercing 6", "Disenchant to a book");
	    ItemStack LURE_2 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Lure 2", "Disenchant to a book");
	    ItemStack LURE_4 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Lure 4", "Disenchant to a book");
	    ItemStack LURE_6 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Lure 6", "Disenchant to a book");
	    ItemStack CHANNELING = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Channeling", "Disenchant to a book");
	    ItemStack LOYALTY_2 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Loyalty 2", "Disenchant to a book");
	    ItemStack LOYALTY_4 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Loyalty 4", "Disenchant to a book");
	    ItemStack LOYALTY_6 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Loyalty 6", "Disenchant to a book");
	    ItemStack IMPALING_2 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Imapaling 2", "Disenchant to a book");
	    ItemStack IMPALING_4 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Imapaling 4", "Disenchant to a book");
	    ItemStack IMPALING_6 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Imapaling 6", "Disenchant to a book");
	    ItemStack IMPALING_8 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Imapaling 8", "Disenchant to a book");
	    ItemStack RIPTIDE_3 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Riptide 3", "Disenchant to a book");
	    ItemStack RIPTIDE_4 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Riptide 4", "Disenchant to a book");
	    ItemStack RIPTIDE_5 = new CustomItem(Material.PAPER, "&eEnchantment Scroll", "Riptide 5", "Disenchant to a book");
	    
	    // Tome 
	    
	    ItemStack TOME_OF_SOTERIA_I  = new CustomItem(Material.BOOK, "&eTome of Soteria I", "Tome of Soteria I", "Disenchant to a book");
	    ItemStack TOME_OF_SOTERIA_II  = new CustomItem(Material.BOOK, "&eTome of Soteria II", "Tome of Soteria II", "Disenchant to a book");
	    ItemStack TOME_OF_SOTERIA_III  = new CustomItem(Material.BOOK, "&eTome of Soteria III", "Tome of Soteria III", "Disenchant to a book");
	    
	    
		item_recipe = new ItemStack[] {null, null, null, BOOK_BINDING, DURABILITY_5, PROTECTION_ENVIRONMENTAL_5, null, null, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_SOTERIA_I", TOME_OF_SOTERIA_I);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
	    
		slimefun_stack.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
		
		
		item_recipe = new ItemStack[] {null, null, null, BOOK_BINDING, DURABILITY_9, PROTECTION_ENVIRONMENTAL_9, null, null, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_SOTERIA_II", TOME_OF_SOTERIA_II);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.DURABILITY, 9);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 9);
		
		item_recipe = new ItemStack[] {null, null, null, BOOK_BINDING, DURABILITY_13, PROTECTION_ENVIRONMENTAL_13, null, null, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_SOTERIA_III", TOME_OF_SOTERIA_III);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);		
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.DURABILITY, 13);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 13);
		
		
	    ItemStack TOME_OF_FORTUNA_I  = new CustomItem(Material.BOOK, "&eTome of Fortuna I", "Tome of Fortuna I", "Disenchant to a book");
	    ItemStack TOME_OF_FORTUNA_II  = new CustomItem(Material.BOOK, "&eTome of Fortuna II", "Tome of Fortuna II", "Disenchant to a book");
	    ItemStack TOME_OF_FORTUNA_III  = new CustomItem(Material.BOOK, "&eTome of Fortuna III", "Tome of Fortuna III", "Disenchant to a book");
	    ItemStack TOME_OF_FORTUNA_IV  = new CustomItem(Material.BOOK, "&eTome of Fortuna IV", "Tome of Fortuna IIV", "Disenchant to a book");
	    
	    
		item_recipe = new ItemStack[] {null, null, null, BOOK_BINDING, DURABILITY_5, LOOT_BONUS_BLOCKS_5, null, null, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_FORTUNA_I", TOME_OF_FORTUNA_I);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);	    
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 5);
	    
		item_recipe = new ItemStack[] {null, null, null, BOOK_BINDING, DURABILITY_9, LOOT_BONUS_BLOCKS_9, null, null, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_FORTUNA_II", TOME_OF_FORTUNA_II);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.DURABILITY, 9);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 9);
		
		item_recipe = new ItemStack[] {null, null, null, BOOK_BINDING, DURABILITY_13, LOOT_BONUS_BLOCKS_13, null, null, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_FORTUNA_III", TOME_OF_FORTUNA_III);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.DURABILITY, 13);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 13);
		
		item_recipe = new ItemStack[] {BOOK_BINDING, SILK_TOUCH, null, BOOK_BINDING, DURABILITY_13, LOOT_BONUS_BLOCKS_13, null, null, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_FORTUNA_IV", TOME_OF_FORTUNA_IV);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);		
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.DURABILITY, 13);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 13);
		slimefun_stack.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);

	    
	    ItemStack TOME_OF_TARTARUS_I  = new CustomItem(Material.BOOK, "&eTome of Tartarus I", "Tome of Tartarus I", "Disenchant to a book");
	    ItemStack TOME_OF_TARTARUS_II  = new CustomItem(Material.BOOK, "&eTome of Tartarus II", "Tome of Tartarus II", "Disenchant to a book");
	    ItemStack TOME_OF_TARTARUS_III  = new CustomItem(Material.BOOK, "&eTome of Tartarus III", "Tome of Tartarus III", "Disenchant to a book");
	    ItemStack TOME_OF_TARTARUS_IV  = new CustomItem(Material.BOOK, "&eTome of Tartarus IV", "Tome of Tartarus IV", "Disenchant to a book");
	    ItemStack TOME_OF_TARTARUS_V  = new CustomItem(Material.BOOK, "&eTome of Tartarus V", "Tome of Tartarus V", "Disenchant to a book");
		
	    
		item_recipe = new ItemStack[] {BOOK_BINDING, SWEEPING_EDGE_2, null, BOOK_BINDING, DAMAGE_UNDEAD_2, null, BOOK_BINDING, KNOCKBACK_3, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_TARTARUS_I", TOME_OF_TARTARUS_I);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);	
	    
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 2);
		slimefun_stack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
		slimefun_stack.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 2);
		
		item_recipe = new ItemStack[] {BOOK_BINDING, SWEEPING_EDGE_4, null, BOOK_BINDING, DAMAGE_UNDEAD_4, null, BOOK_BINDING, KNOCKBACK_5, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_TARTARUS_II", TOME_OF_TARTARUS_II);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);		    
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 4);
		slimefun_stack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);
		slimefun_stack.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 4);

		item_recipe = new ItemStack[] {BOOK_BINDING, SWEEPING_EDGE_6, null, BOOK_BINDING, DAMAGE_UNDEAD_6, null, BOOK_BINDING, KNOCKBACK_9, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_TARTARUS_III", TOME_OF_TARTARUS_III);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 6);
		slimefun_stack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 9);
		slimefun_stack.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 6);
		
		item_recipe = new ItemStack[] {BOOK_BINDING, SWEEPING_EDGE_6, null, BOOK_BINDING, DAMAGE_UNDEAD_6, LOOT_BONUS_MOBS_7, BOOK_BINDING, KNOCKBACK_9, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_TARTARUS_IV", TOME_OF_TARTARUS_IV);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 6);
		slimefun_stack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 9);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 7);
		slimefun_stack.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 6);
		
		item_recipe = new ItemStack[] {BOOK_BINDING, SWEEPING_EDGE_6, null, BOOK_BINDING, DAMAGE_UNDEAD_6, LOOT_BONUS_MOBS_13, BOOK_BINDING, KNOCKBACK_9, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_TARTARUS_V", TOME_OF_TARTARUS_V);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 6);
		slimefun_stack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 9);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 13);
		slimefun_stack.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 6);
		
		
	    ItemStack TOME_OF_ALGOS_I  = new CustomItem(Material.BOOK, "&eTome of Algos I", "Tome of Algos I", "Disenchant to a book");
	    ItemStack TOME_OF_ALGOS_II  = new CustomItem(Material.BOOK, "&eTome of Algos II", "Tome of Algos II", "Disenchant to a book");
	    ItemStack TOME_OF_ALGOS_III  = new CustomItem(Material.BOOK, "&eTome of Algos III", "Tome of Algos III", "Disenchant to a book");
	    ItemStack TOME_OF_ALGOS_IV  = new CustomItem(Material.BOOK, "&eTome of Algos IV", "Tome of Algos IV", "Disenchant to a book");
	    ItemStack TOME_OF_ALGOS_V  = new CustomItem(Material.BOOK, "&eTome of Algos V", "Tome of Algos V", "Disenchant to a book");
		
		item_recipe = new ItemStack[] {BOOK_BINDING, SWEEPING_EDGE_2, null, BOOK_BINDING, DAMAGE_UNDEAD_2, null, BOOK_BINDING, DAMAGE_ALL_3, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_ALGOS_I", TOME_OF_ALGOS_I);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 2);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
		slimefun_stack.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 2);
	    
		item_recipe = new ItemStack[] {BOOK_BINDING, SWEEPING_EDGE_4, null, BOOK_BINDING, DAMAGE_UNDEAD_4, null, BOOK_BINDING, DAMAGE_ALL_5, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_ALGOS_II", TOME_OF_ALGOS_II);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 4);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 4);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
		
		item_recipe = new ItemStack[] {BOOK_BINDING, SWEEPING_EDGE_6, null, BOOK_BINDING, DAMAGE_UNDEAD_6, null, BOOK_BINDING, DAMAGE_ALL_9, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_ALGOS_III", TOME_OF_ALGOS_III);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 6);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 6);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 9);
		
		item_recipe = new ItemStack[] {BOOK_BINDING, SWEEPING_EDGE_6, null, BOOK_BINDING, DAMAGE_UNDEAD_6, LOOT_BONUS_MOBS_7, BOOK_BINDING, DAMAGE_ALL_9, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_ALGOS_IV", TOME_OF_ALGOS_IV);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 6);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 6);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 7);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 9);
		
		item_recipe = new ItemStack[] {BOOK_BINDING, SWEEPING_EDGE_6, null, BOOK_BINDING, DAMAGE_UNDEAD_6, LOOT_BONUS_MOBS_13, BOOK_BINDING, DAMAGE_ALL_13, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_ALGOS_V", TOME_OF_ALGOS_V);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 6);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 6);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 13);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 13);
		
	    ItemStack TOME_OF_ATLAHUA_I  = new CustomItem(Material.BOOK, "&eTome of Atlahua I", "Tome of Atlahua I", "Disenchant to a book");
	    ItemStack TOME_OF_ATLAHUA_II  = new CustomItem(Material.BOOK, "&eTome of Atlahua II", "Tome of Atlahua II", "Disenchant to a book");
	    ItemStack TOME_OF_ATLAHUA_III  = new CustomItem(Material.BOOK, "&eTome of Atlahua III", "Tome of Atlahua III", "Disenchant to a book");
	    
	    
		item_recipe = new ItemStack[] {BOOK_BINDING, DURABILITY_3, null, BOOK_BINDING, LUCK_2, null, BOOK_BINDING, LURE_2, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_ATLAHUA_I", TOME_OF_ATLAHUA_I);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LUCK, 2);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LURE, 2);

	    
		item_recipe = new ItemStack[] {BOOK_BINDING, DURABILITY_7, null, BOOK_BINDING, LUCK_4, null, BOOK_BINDING, LURE_4, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_ATLAHUA_II", TOME_OF_ATLAHUA_II);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LUCK, 4);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LURE, 4);
		
		item_recipe = new ItemStack[] {BOOK_BINDING, DURABILITY_9, null, BOOK_BINDING, LUCK_4, null, BOOK_BINDING, LURE_6, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_ATLAHUA_III", TOME_OF_ATLAHUA_III);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.DURABILITY, 9);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LUCK, 4);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LURE, 6);
				
	    ItemStack TOME_OF_URSULA_I  = new CustomItem(Material.BOOK, "&eTome of Ursula I", "Tome of Ursula I", "Disenchant to a book");
	    ItemStack TOME_OF_URSULA_II  = new CustomItem(Material.BOOK, "&eTome of Ursula II", "Tome of Ursula II", "Disenchant to a book");
	    ItemStack TOME_OF_URSULA_III  = new CustomItem(Material.BOOK, "&eTome of Ursula III", "Tome of Ursula III", "Disenchant to a book");
	    ItemStack TOME_OF_URSULA_IV  = new CustomItem(Material.BOOK, "&eTome of Ursula IV", "Tome of Ursula IV", "Disenchant to a book");
	    ItemStack TOME_OF_URSULA_V  = new CustomItem(Material.BOOK, "&eTome of Ursula V", "Tome of Ursula V", "Disenchant to a book");
	    
	    
		item_recipe = new ItemStack[] {BOOK_BINDING, DURABILITY_3, null, BOOK_BINDING, LOYALTY_2, null, BOOK_BINDING, IMPALING_2, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_URSULA_I", TOME_OF_URSULA_I);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);   
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOYALTY, 2);
		slimefun_stack.addUnsafeEnchantment(Enchantment.IMPALING, 2);
	    
		item_recipe = new ItemStack[] {BOOK_BINDING, DURABILITY_5, null, BOOK_BINDING, LOYALTY_4, null, BOOK_BINDING, IMPALING_4, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_URSULA_II", TOME_OF_URSULA_II);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOYALTY, 4);
		slimefun_stack.addUnsafeEnchantment(Enchantment.IMPALING, 4);
		
		item_recipe = new ItemStack[] {BOOK_BINDING, DURABILITY_7, null, BOOK_BINDING, LOYALTY_4, CHANNELING, BOOK_BINDING, IMPALING_4, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_URSULA_III", TOME_OF_URSULA_III);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);   
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOYALTY, 4);
		slimefun_stack.addUnsafeEnchantment(Enchantment.CHANNELING, 1);
		slimefun_stack.addUnsafeEnchantment(Enchantment.IMPALING, 4);
		
		
		item_recipe = new ItemStack[] {BOOK_BINDING, DURABILITY_7, null, BOOK_BINDING, LOYALTY_6, CHANNELING, BOOK_BINDING, IMPALING_6, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_URSULA_IV", TOME_OF_URSULA_IV);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);   
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOYALTY, 6);
		slimefun_stack.addUnsafeEnchantment(Enchantment.CHANNELING, 1);
		slimefun_stack.addUnsafeEnchantment(Enchantment.IMPALING, 6);
		
		item_recipe = new ItemStack[] {BOOK_BINDING, DURABILITY_7, RIPTIDE_5, BOOK_BINDING, LOYALTY_6, CHANNELING, BOOK_BINDING, IMPALING_6, null};
		slimefun_stack = new SlimefunItemStack("TOME_OF_URSULA_V", TOME_OF_URSULA_V);
		new SlimefunItem(ADVANCED_ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);   
		
		slimefun_stack.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
		slimefun_stack.addUnsafeEnchantment(Enchantment.RIPTIDE, 5);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOYALTY, 6);
		slimefun_stack.addUnsafeEnchantment(Enchantment.CHANNELING, 1);
		slimefun_stack.addUnsafeEnchantment(Enchantment.IMPALING, 6);
		

	    //Single Enchants
	    
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.COBBLESTONE_WALL), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("DURABILITY_3", DURABILITY_3);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this); 
		slimefun_stack.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, DURABILITY_3, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("DURABILITY_5", DURABILITY_5);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this); 
		slimefun_stack.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, DURABILITY_5, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("DURABILITY_7", DURABILITY_7);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this); 
		slimefun_stack.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, DURABILITY_7, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("DURABILITY_9", DURABILITY_9);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this); 
		slimefun_stack.addUnsafeEnchantment(Enchantment.DURABILITY, 9);
		

		item_recipe = new ItemStack[] {new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2), DURABILITY_9, new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2)};
		slimefun_stack = new SlimefunItemStack("DURABILITY_13", DURABILITY_13);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DURABILITY, 13);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, DURABILITY_13, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("DURABILITY_15", DURABILITY_15);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DURABILITY, 15);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.ARROW), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("PROTECTION_PROJECTILE_3", PROTECTION_PROJECTILE_3);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, PROTECTION_PROJECTILE_3, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("PROTECTION_PROJECTILE_5", PROTECTION_PROJECTILE_5);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 5);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, PROTECTION_PROJECTILE_5, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("PROTECTION_PROJECTILE_7", PROTECTION_PROJECTILE_7);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 7);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, PROTECTION_PROJECTILE_7, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("PROTECTION_PROJECTILE_9", PROTECTION_PROJECTILE_9);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 9);
		
		item_recipe = new ItemStack[] {new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2), PROTECTION_PROJECTILE_9, new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2)};
		slimefun_stack = new SlimefunItemStack("PROTECTION_PROJECTILE_13", PROTECTION_PROJECTILE_13);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 13);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, PROTECTION_PROJECTILE_13, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("PROTECTION_PROJECTILE_15", PROTECTION_PROJECTILE_15);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 15);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.TNT), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("PROTECTION_EXPLOSIONS_3", PROTECTION_EXPLOSIONS_3);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 3);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, PROTECTION_EXPLOSIONS_3, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("PROTECTION_EXPLOSIONS_5", PROTECTION_EXPLOSIONS_5);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 5);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, PROTECTION_EXPLOSIONS_5, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("PROTECTION_EXPLOSIONS_7", PROTECTION_EXPLOSIONS_7);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 7);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, PROTECTION_EXPLOSIONS_7, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("PROTECTION_EXPLOSIONS_9", PROTECTION_EXPLOSIONS_9);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 9);
		
		item_recipe = new ItemStack[] {new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2), PROTECTION_EXPLOSIONS_9, new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2)};
		slimefun_stack = new SlimefunItemStack("PROTECTION_EXPLOSIONS_13", PROTECTION_EXPLOSIONS_13);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 13);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, PROTECTION_EXPLOSIONS_13, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("PROTECTION_EXPLOSIONS_15", PROTECTION_EXPLOSIONS_15);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 15);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.FEATHER), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("PROTECTION_FALLING_2", PROTECTION_FALLING_2);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 2);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, PROTECTION_FALLING_2, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("PROTECTION_FALLING_3", PROTECTION_FALLING_3);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 3);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, PROTECTION_FALLING_3, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("PROTECTION_FALLING_4", PROTECTION_FALLING_4);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 4);
		
		item_recipe = new ItemStack[] {CONDENSED_ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, WONDER_DUST, new ItemStack(Material.ANVIL), WONDER_DUST, CONDENSED_ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("MENDING_BOOK", MENDING_BOOK);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.MENDING, 1);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.GOLD_INGOT), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("LUCK_2", LUCK_2);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LUCK, 2);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, LUCK_2, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("LUCK_4", LUCK_4);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LUCK, 4);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, LUCK_4, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("LUCK_6", LUCK_6);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LUCK, 6);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.CHEST), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("LOOT_BONUS_BLOCKS_3", LOOT_BONUS_BLOCKS_3);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, LOOT_BONUS_BLOCKS_3, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("LOOT_BONUS_BLOCKS_5", LOOT_BONUS_BLOCKS_5);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 5);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, LOOT_BONUS_BLOCKS_5, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("LOOT_BONUS_BLOCKS_7", LOOT_BONUS_BLOCKS_7);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 7);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, LOOT_BONUS_BLOCKS_7, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("LOOT_BONUS_BLOCKS_9", LOOT_BONUS_BLOCKS_9);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 9);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, LOOT_BONUS_BLOCKS_9, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("LOOT_BONUS_BLOCKS_13", LOOT_BONUS_BLOCKS_13);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 13);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, LOOT_BONUS_BLOCKS_13, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("LOOT_BONUS_BLOCKS_15", LOOT_BONUS_BLOCKS_15);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 15);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.BONE), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("LOOT_BONUS_MOBS_3", LOOT_BONUS_MOBS_3);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, LOOT_BONUS_MOBS_3, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("LOOT_BONUS_MOBS_5", LOOT_BONUS_MOBS_5);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 5);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, LOOT_BONUS_MOBS_5, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("LOOT_BONUS_MOBS_7", LOOT_BONUS_MOBS_7);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 7);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, LOOT_BONUS_MOBS_7, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("LOOT_BONUS_MOBS_9", LOOT_BONUS_MOBS_9);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 9);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, LOOT_BONUS_MOBS_9, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("LOOT_BONUS_MOBS_13", LOOT_BONUS_MOBS_13);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 13);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, LOOT_BONUS_MOBS_13, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("LOOT_BONUS_MOBS_15", LOOT_BONUS_MOBS_15);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 15);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.SUGAR), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("DIG_SPEED_3", DIG_SPEED_3);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DIG_SPEED, 3);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, WONDER_DUST, DIG_SPEED_3, WONDER_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("DIG_SPEED_5", DIG_SPEED_5);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);	
		slimefun_stack.addUnsafeEnchantment(Enchantment.DIG_SPEED, 5);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, DIG_SPEED_5, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("DIG_SPEED_7", DIG_SPEED_7);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);	
		slimefun_stack.addUnsafeEnchantment(Enchantment.DIG_SPEED, 7);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.DEAD_BUSH), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("THORNS_3", THORNS_3);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.THORNS, 3);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, WONDER_DUST, THORNS_3, WONDER_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("THORNS_5", THORNS_5);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);	
		slimefun_stack.addUnsafeEnchantment(Enchantment.THORNS, 5);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, THORNS_5, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("THORNS_7", THORNS_7);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);		
		slimefun_stack.addUnsafeEnchantment(Enchantment.THORNS, 7);
		
		item_recipe = new ItemStack[] {CONDENSED_ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, new ItemStack(Material.SHEARS), CONDENSED_ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("SILK_TOUCH", SILK_TOUCH);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);		
		slimefun_stack.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, ENCHANTING_DUST, WONDER_DUST, new ItemStack(Material.BOW), WONDER_DUST, ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("ARROW_INFINITE", ARROW_INFINITE);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, ENCHANTING_DUST, WONDER_DUST, new ItemStack(Material.FIRE_CHARGE), WONDER_DUST, ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("ARROW_FIRE", ARROW_FIRE);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.WOODEN_PICKAXE), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("ARROW_DAMAGE_2", ARROW_DAMAGE_2);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ARROW_DAMAGE_2, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("ARROW_DAMAGE_4", ARROW_DAMAGE_4);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);	
		slimefun_stack.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 4);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ARROW_DAMAGE_4, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("ARROW_DAMAGE_6", ARROW_DAMAGE_6);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);	
		slimefun_stack.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 6);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ARROW_DAMAGE_6, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("ARROW_DAMAGE_8", ARROW_DAMAGE_8);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);	
		slimefun_stack.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 8);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.STONE_SWORD), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("SWEEPING_EDGE_2", SWEEPING_EDGE_2);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);	
		slimefun_stack.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 2);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, SWEEPING_EDGE_2, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("SWEEPING_EDGE_4", SWEEPING_EDGE_4);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 4);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, SWEEPING_EDGE_4, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("SWEEPING_EDGE_6", SWEEPING_EDGE_6);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 6);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.ROTTEN_FLESH), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("DAMAGE_UNDEAD_2", DAMAGE_UNDEAD_2);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 2);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, DAMAGE_UNDEAD_2, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("DAMAGE_UNDEAD_4", DAMAGE_UNDEAD_4);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 4);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, DAMAGE_UNDEAD_4, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("DAMAGE_UNDEAD_6", DAMAGE_UNDEAD_6);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 6);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.COBWEB), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("DAMAGE_ARTHROPODS_2", DAMAGE_ARTHROPODS_2);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 2);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, DAMAGE_ARTHROPODS_2, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("DAMAGE_ARTHROPODS_4", DAMAGE_ARTHROPODS_4);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 4);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, DAMAGE_ARTHROPODS_4, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("DAMAGE_ARTHROPODS_6", DAMAGE_ARTHROPODS_6);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 6);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.PISTON), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("KNOCKBACK_3", KNOCKBACK_3);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, KNOCKBACK_3, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("KNOCKBACK_5", KNOCKBACK_5);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, KNOCKBACK_5, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("KNOCKBACK_7", KNOCKBACK_7);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 7);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, KNOCKBACK_7, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("KNOCKBACK_9", KNOCKBACK_9);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 9);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, KNOCKBACK_9, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("KNOCKBACK_13", KNOCKBACK_13);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 13);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, KNOCKBACK_13, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("KNOCKBACK_15", KNOCKBACK_15);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 15);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.STICKY_PISTON), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("ARROW_KNOCKBACK_3", ARROW_KNOCKBACK_3);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 3);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ARROW_KNOCKBACK_3, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("ARROW_KNOCKBACK_5", ARROW_KNOCKBACK_5);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 5);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ARROW_KNOCKBACK_5, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("ARROW_KNOCKBACK_7", ARROW_KNOCKBACK_7);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 7);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ARROW_KNOCKBACK_7, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("ARROW_KNOCKBACK_9", ARROW_KNOCKBACK_9);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 9);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ARROW_KNOCKBACK_9, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("ARROW_KNOCKBACK_13", ARROW_KNOCKBACK_13);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 13);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, ARROW_KNOCKBACK_13, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("ARROW_KNOCKBACK_15", ARROW_KNOCKBACK_15);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 15);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.ENDER_EYE), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("DAMAGE_ALL_3", DAMAGE_ALL_3);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, DAMAGE_ALL_3, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("DAMAGE_ALL_5", DAMAGE_ALL_5);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, DAMAGE_ALL_5, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("DAMAGE_ALL_7", DAMAGE_ALL_7);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 7);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, DAMAGE_ALL_7, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("DAMAGE_ALL_9", DAMAGE_ALL_9);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 9);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, DAMAGE_ALL_9, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("DAMAGE_ALL_13", DAMAGE_ALL_13);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 13);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, DAMAGE_ALL_13, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("DAMAGE_ALL_15", DAMAGE_ALL_15);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 15);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.SHIELD), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("PROTECTION_ENVIRONMENTAL_3", PROTECTION_ENVIRONMENTAL_3);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, PROTECTION_ENVIRONMENTAL_3, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("PROTECTION_ENVIRONMENTAL_5", PROTECTION_ENVIRONMENTAL_5);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, PROTECTION_ENVIRONMENTAL_5, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("PROTECTION_ENVIRONMENTAL_7", PROTECTION_ENVIRONMENTAL_7);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, PROTECTION_ENVIRONMENTAL_7, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("PROTECTION_ENVIRONMENTAL_9", PROTECTION_ENVIRONMENTAL_9);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 9);
		
		item_recipe = new ItemStack[] {new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2), PROTECTION_ENVIRONMENTAL_9, new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2), new CustomItem(ENCHANTING_DUST, 2)};
		slimefun_stack = new SlimefunItemStack("PROTECTION_ENVIRONMENTAL_13", PROTECTION_ENVIRONMENTAL_13);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 13);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, PROTECTION_ENVIRONMENTAL_13, WONDER_DUST, ENCHANTING_DUST, WONDER_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("PROTECTION_ENVIRONMENTAL_15", PROTECTION_ENVIRONMENTAL_15);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 15);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.REDSTONE), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("QUICK_CHARGE_1", QUICK_CHARGE_1);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 1);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, QUICK_CHARGE_1, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("QUICK_CHARGE_2", QUICK_CHARGE_2);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 2);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, QUICK_CHARGE_2, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("QUICK_CHARGE_3", QUICK_CHARGE_3);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 3);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.IRON_BARS), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("PIERCING_2", PIERCING_2);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PIERCING, 2);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, PIERCING_2, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("PIERCING_4", PIERCING_4);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PIERCING, 4);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, PIERCING_4, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("PIERCING_6", PIERCING_6);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.PIERCING, 6);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.PUFFERFISH), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("LURE_2", LURE_2);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LURE, 2);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, LURE_2, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("LURE_4", LURE_4);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LURE, 4);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, LURE_4, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("LURE_6", LURE_6);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LURE, 6);
		
		item_recipe = new ItemStack[] {CONDENSED_ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, new ItemStack(Material.COMPARATOR), CONDENSED_ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("CHANNELING", CHANNELING);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.CHANNELING, 1);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, LOYALTY_2, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("LOYALTY_4", LOYALTY_4);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOYALTY, 4);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, LOYALTY_4, CONDENSED_ENCHANTING_DUST, ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("LOYALTY_6", LOYALTY_6);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.LOYALTY, 6);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.PUFFERFISH), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("IMPALING_2", IMPALING_2);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.IMPALING, 2);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, IMPALING_2, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("IMPALING_4", IMPALING_4);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.IMPALING, 4);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, IMPALING_4, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("IMPALING_6", IMPALING_6);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.IMPALING, 6);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, IMPALING_6, CONDENSED_ENCHANTING_DUST, ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("IMPALING_8", IMPALING_8);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.IMPALING, 8);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, new ItemStack(Material.NAUTILUS_SHELL), ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("RIPTIDE_3", RIPTIDE_3);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.RIPTIDE, 3);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, RIPTIDE_3, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("RIPTIDE_4", RIPTIDE_4);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.RIPTIDE, 4);
		
		item_recipe = new ItemStack[] {ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST, WONDER_DUST, CONDENSED_ENCHANTING_DUST, RIPTIDE_4, CONDENSED_ENCHANTING_DUST, WONDER_DUST, CONDENSED_ENCHANTING_DUST, ENCHANTING_DUST};
		slimefun_stack = new SlimefunItemStack("RIPTIDE_5", RIPTIDE_5);
		new SlimefunItem(ENCHANTING, slimefun_stack, wonder_scribe.asRecipeType(), item_recipe)
		.register(this);
		slimefun_stack.addUnsafeEnchantment(Enchantment.RIPTIDE, 5);
		
		
		/*        Research       */		

		Research research = new Research(new NamespacedKey(this, "ENCHANTING_BASICS"), 904, "Enchanting Basics", 40);	
		research.addItems(wonder_scribe.getItem(), ENCHANTING_DUST, CONDENSED_ENCHANTING_DUST);
		research.register();
			
		research = new Research(new NamespacedKey(this, "DURABILITY"), 905, "Durability", 40);	
		research.addItems(wonder_scribe.getItem(), DURABILITY_3, DURABILITY_5, DURABILITY_7, DURABILITY_9, DURABILITY_13, DURABILITY_15);
		research.register();
		
		research = new Research(new NamespacedKey(this, "PROTECTION"), 906, "Protection", 40);	
		research.addItems(wonder_scribe.getItem(), PROTECTION_PROJECTILE_3, PROTECTION_PROJECTILE_5, PROTECTION_PROJECTILE_7, PROTECTION_PROJECTILE_9, PROTECTION_PROJECTILE_13, PROTECTION_PROJECTILE_15,PROTECTION_ENVIRONMENTAL_3, PROTECTION_ENVIRONMENTAL_5, PROTECTION_ENVIRONMENTAL_7, PROTECTION_ENVIRONMENTAL_9, PROTECTION_ENVIRONMENTAL_13, PROTECTION_ENVIRONMENTAL_15, PROTECTION_EXPLOSIONS_3, PROTECTION_EXPLOSIONS_5, PROTECTION_EXPLOSIONS_7, PROTECTION_EXPLOSIONS_9, PROTECTION_EXPLOSIONS_13, PROTECTION_EXPLOSIONS_15);
		research.register();		
				
		research = new Research(new NamespacedKey(this, "PROTECTION"), 907, "Protection_Falling", 20);	
		research.addItems(wonder_scribe.getItem(), PROTECTION_FALLING_2, PROTECTION_FALLING_3, PROTECTION_FALLING_4);
		research.register();		
		
		research = new Research(new NamespacedKey(this, "MENDING_BOOK"), 908, "Mending", 30);	
		research.addItems(wonder_scribe.getItem(), MENDING_BOOK);
		research.register();		
		
		research = new Research(new NamespacedKey(this, "LUCK"), 909, "luck", 30);	
		research.addItems(wonder_scribe.getItem(), LUCK_2, LUCK_4, LUCK_6);
		research.register();				

		research = new Research(new NamespacedKey(this, "LOOT_BONUS_BLOCKS"), 910, "Loot_Bonus_Blocks", 30);	
		research.addItems(wonder_scribe.getItem(), LOOT_BONUS_BLOCKS_3, LOOT_BONUS_BLOCKS_5, LOOT_BONUS_BLOCKS_7, LOOT_BONUS_BLOCKS_9, LOOT_BONUS_BLOCKS_13, LOOT_BONUS_BLOCKS_15);
		research.register();		
		
		research = new Research(new NamespacedKey(this, "LOOT_BONUS_MOBS"), 911, "Loot_Bonus_Mobs", 30);	
		research.addItems(wonder_scribe.getItem(), LOOT_BONUS_MOBS_3, LOOT_BONUS_MOBS_5, LOOT_BONUS_MOBS_7, LOOT_BONUS_MOBS_9, LOOT_BONUS_MOBS_13, LOOT_BONUS_MOBS_15);
		research.register();		
		
		research = new Research(new NamespacedKey(this, "DIG_SPEED"), 912, "Dig_Speed", 30);	
		research.addItems(wonder_scribe.getItem(), DIG_SPEED_3, DIG_SPEED_5, DIG_SPEED_7);
		research.register();		
		
		research = new Research(new NamespacedKey(this, "THORNS"), 913, "Thorns", 30);	
		research.addItems(wonder_scribe.getItem(), THORNS_3, THORNS_5, THORNS_7);
		research.register();	

		research = new Research(new NamespacedKey(this, "BOWS"), 914, "bows", 30);	
		research.addItems(wonder_scribe.getItem(), ARROW_INFINITE, ARROW_FIRE, ARROW_DAMAGE_2, ARROW_DAMAGE_4, ARROW_DAMAGE_6, ARROW_DAMAGE_8, ARROW_KNOCKBACK_3, ARROW_KNOCKBACK_5, ARROW_KNOCKBACK_7, ARROW_KNOCKBACK_9, ARROW_KNOCKBACK_13, ARROW_KNOCKBACK_15);
		research.register();	
				
		research = new Research(new NamespacedKey(this, "SWEEPING"), 915, "Sweeping_Edge", 30);	
		research.addItems(wonder_scribe.getItem(), SWEEPING_EDGE_2, SWEEPING_EDGE_4, SWEEPING_EDGE_6);
		research.register();				
		
		research = new Research(new NamespacedKey(this, "SILK"), 916, "Silk_Touch", 30);	
		research.addItems(wonder_scribe.getItem(), SILK_TOUCH);
		research.register();			
		
		research = new Research(new NamespacedKey(this, "DAMAGE_UNDEAD"), 917, "Misc_Damage", 30);	
		research.addItems(wonder_scribe.getItem(), DAMAGE_UNDEAD_2, DAMAGE_UNDEAD_4, DAMAGE_UNDEAD_6, KNOCKBACK_5, KNOCKBACK_7, KNOCKBACK_9, KNOCKBACK_13, KNOCKBACK_15, DAMAGE_ALL_3, DAMAGE_ALL_5, DAMAGE_ALL_7, DAMAGE_ALL_9, DAMAGE_ALL_13, DAMAGE_ALL_15, DAMAGE_ARTHROPODS_2, DAMAGE_ARTHROPODS_4, DAMAGE_ARTHROPODS_6);
		research.register();	
					
		research = new Research(new NamespacedKey(this, "QUICK_CHARGE"), 918, "Crossbows", 30);	
		research.addItems(wonder_scribe.getItem(), QUICK_CHARGE_1, QUICK_CHARGE_2, QUICK_CHARGE_3, PIERCING_6, PIERCING_4, PIERCING_6);
		research.register();				
		
		research = new Research(new NamespacedKey(this, "LURE"), 919, "Fishing_Rods", 30);	
		research.addItems(wonder_scribe.getItem(), LURE_2, LURE_4, LURE_6);
		research.register();			
		
				
		research = new Research(new NamespacedKey(this, "CHANNELING"), 920, "Tridents", 30);	
		research.addItems(wonder_scribe.getItem(), CHANNELING, LOYALTY_2, LOYALTY_4, LOYALTY_6, RIPTIDE_3, RIPTIDE_4, RIPTIDE_5, IMPALING_2, IMPALING_4, IMPALING_6, IMPALING_8);
		research.register();			
		
		console.sendMessage("Wondercraft Enchants Slimefun Addon's Loaded...");
		console.sendMessage("---------------------------------------------");
	}

	public static JavaPlugin getPlugin() {
		return setup;
	}

	@Override
	public JavaPlugin getJavaPlugin() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public String getBugTrackerURL() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
