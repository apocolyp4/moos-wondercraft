package tech.obliviondevelop.SF.Lists;

import java.util.logging.Level;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.thebusybiscuit.slimefun4.utils.itemstack.ColoredFireworkStar;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;

import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import tech.obliviondevelop.SF.CustomFood;
import tech.obliviondevelop.SF.IcingSugar;
import tech.obliviondevelop.SF.Paracetamol;
import tech.obliviondevelop.SF.Pastry;
import tech.obliviondevelop.SF.CustomItem;
import tech.obliviondevelop.SF.CustomArmor;
import tech.obliviondevelop.SF.CustomPotion;
import tech.obliviondevelop.SF.CustomSkull;
import tech.obliviondevelop.SF.SkullItem;
import tech.obliviondevelop.SF.Toast;

public class WonderItems {
	
	/* DIGITAL MINER (09/09/19) */
	public static ItemStack DIGITAL_MINER = new tech.obliviondevelop.SF.CustomItem(Material.IRON_PICKAXE, "&bDigital Miner");
	public static ItemStack ADVANCED_DIGITAL_MINER = new tech.obliviondevelop.SF.CustomItem(Material.DIAMOND_PICKAXE, "&6Advanced Digital Miner");
	
	/* BUILDING (04/22/19) */
	public static ItemStack MIXER = new CustomItem(new ItemStack(Material.LIGHT_BLUE_CONCRETE), "&3Concrete Mixer", "&7Turns your concrete ","&7powder into wet concrete!");
	
	/* OCEAN (Date added unknown) */
    public static ItemStack BRAIN_CORAL_BLOCK = new ItemStack(Material.BRAIN_CORAL_BLOCK);
    public static ItemStack BRAIN_CORAL_DUST = new CustomItem(Material.MAGENTA_DYE, "&bBrain Coral Dust");
    public static ItemStack BUBBLE_CORAL_BLOCK = new ItemStack(Material.BUBBLE_CORAL_BLOCK);
    public static ItemStack BUBBLE_CORAL_DUST = new CustomItem(Material.PURPLE_DYE, "&bBubble Coral Dust");
    public static ItemStack FIRE_CORAL_BLOCK = new ItemStack(Material.FIRE_CORAL_BLOCK);
    public static ItemStack FIRE_CORAL_DUST = new CustomItem(Material.RED_DYE, "&bFire Coral Dust");
    public static ItemStack HORN_CORAL_BLOCK = new ItemStack(Material.HORN_CORAL_BLOCK);
    public static ItemStack HORN_CORAL_DUST = new CustomItem(Material.YELLOW_DYE, "&bHorn Coral Dust");
    public static ItemStack TUBE_CORAL_BLOCK = new ItemStack(Material.TUBE_CORAL_BLOCK);
    public static ItemStack TUBE_CORAL_DUST = new CustomItem(Material.CYAN_DYE, "&bTube Coral Dust");
    public static ItemStack MARBLE_BLOCK = new CustomItem(Material.WHITE_GLAZED_TERRACOTTA, "&bMarble Block");
    
    
    public static ItemStack MOOS_M_HOE = new CustomItem(Material.DIAMOND_PICKAXE, "&bMoo's Marvellous Hoe", "", "&rAllows you to hoe a humungous bit", "", "", "&93x3");
    
    public static ItemStack MOOS_M_PICKAXE = new CustomItem(Material.DIAMOND_PICKAXE, "&bMoo's Marvellous Pickaxe", "", "&rAllows you to mine a humungous bit", "&rof Blocks at once...BIG BOOM", "", "&93x3x3");
    public static ItemStack SWORD_OF_BEHEADING = new CustomItem(Material.IRON_SWORD, "&6Sword of Beheading", "&7Beheading VI", "", "&rHas a chance to behead Mobs", "&r(even a higher chance for Wither Skeletons)");
    @SuppressWarnings("deprecation")
    public static ItemStack MOOS_CLAYMORE = new CustomItem(Material.IRON_SWORD, "&bMoo's Claymore", 0, new String[] {"&7Life Steal I", "", "&rEverytime you attack something", "&ryou have a 45% chance to", "&rrecover 2 Hearts of your Health","&7Beheading VI", "", "&rHas a chance to behead Mobs", "&r(even a higher chance for Wither Skeletons)","Moo's Claymore is a premium item only available in /buy"}, new String[] {"DURABILITY-15", "DAMAGE_ALL-5","LOOT_BONUS_MOBS-12","SWEEPING_EDGE-3"});
    public static ItemStack MOOS_CROSSBOW = new CustomItem(Material.CROSSBOW, "&bMoo's Deadly Crossbow", 0, new String[] {"Moo's Deadly Crossbow is a premium item only available in /buy"}, new String[] {"DURABILITY-15", "LOOT_BONUS_MOBS-12","ARROW_DAMAGE-10","PIERCING-5","MULTISHOT-1","QUICK_CHARGE-3"});
    public static ItemStack MOOS_BOW = new CustomItem(Material.BOW, "&bMoo's Mighty Bow", 0, new String[] {"Moo's Mighty Bow is a premium item only available in /buy"}, new String[] {"DURABILITY-15", "LOOT_BONUS_MOBS-12","ARROW_DAMAGE-10","ARROW_INFINITE-1","ARROW_FIRE-1","ARROW_KNOCKBACK-3"});

    
    /* Scottish (Date added unknown) 
    //public static ItemStack OVEN = new CustomItem(Material.DISPENSER, "Oven", "", "&a&oCooks some yummy food");
    public static ItemStack SHEEP_GUTS = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTI4Y2JjYzUwMGFjOTFlY2YwNjMyN2Q1NDFkZjhlNjcxODM1NjMyODc0NTg1ZjUzNTlmODAyMjk1ZDc5NDRhOCJ9fX0="), "&6Sheep Guts", "", "&rEww Icky", "&rall the bits nobody wants");
    public static ItemStack JAR_OF_BLOOD = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmQ3MjkyNzNhYjdlZjM0MDllMjQzZThkYTc0YzFiZjM4NjNkMzE1OTIxYmJkYWMzNGM0NmRkMTI2NzVkMzAyMSJ9fX0="), "&6Jar Of Blood", "", "&rRed and Runny", "&rthis used to belong to a sheep");
    public static ItemStack OATS = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjAyMzE2MTZlYzYwMzIyY2M1MWUyZTE4NGVmMTg0ZTMzNTNmYTIxYjBhMTE0YjQyNzg0NTc0MDg5NjgxNDE1OCJ9fX0="), "&6Oats", "", "&rA staple food", "&rgrown in the fields of Auchtermuckety");
    
    public static ItemStack RAW_HAGGIS = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjFiZDkzNTk2YTM0NjRhNWU3NWYyODI5ZTFiYmM3MmY3Njk4MzhmOWQ5MWJjOWM4NDk0ZGFiYzJjMzk3NjYifX19"), "&6Raw Haggis", "", "&rA staple scottish food", "&rOnce cooked this should be piped in by a piper");
    public static ItemStack COOKED_HAGGIS = new CustomItem(Material.RABBIT_STEW, "&6Cooked Haggis", "A true delicacy", "best served Piped in by a Piper","Insane Saturation");
    public static ItemStack PORRIDGE = new CustomItem(Material.MUSHROOM_STEW, "&6Porridge", "A regular breakfast", "best served piping hot","Insane Saturation");
    public static ItemStack SMOKED_SALMON = new CustomItem(Material.COOKED_SALMON, "&6Smoked Salmon", "Fresh smoked salmon from the smoke houses of Peterheed", "best served very thinly","Insane Saturation");
    public static ItemStack SHORTBREAD = new CustomItem(Material.BREAD, "&6Shortbread", "&6Shortbread", "pefectly made with loads of butter","5 Min Sugar Rush");
    public static ItemStack IRN_BRU = new CustomPotion("IRN BRU", 8195, new String[0], new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 6000, 2));
	*/
    public static ItemStack MACE = new CustomItem(new ItemStack(Material.NETHER_WART),  "&8Mace", "", "&rSpicy Stuff", "&rgives a bit of a kick, like a stubborn mule");
    public static ItemStack ALUMINIUM_FOIL = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2E4Yjg3ZTQ2Y2ZlOGEyZGMzNTI1YzFjNjdkOGE2OWEyNWZkMGE3ZjcyNGE2ZmE5MTFhZDc0YWRiNmQ4MmMyIn19fQ=="), "&6Aluminium Foil", "", "A thin foil perfect for cooking with");
    public static ItemStack WOOD_CHIPS = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWYyZWZiNDJjYWFlNTYxZmUyZDk3YmY3MTFjY2MwNjQ3ZGU2OTVmZDJkZTIxZDljZGNiMTI5YmEyOTMzY2VkZiJ9fX0="), "&6Wood Chips", "", "Perfect for giving a nice smokey flavour"); 
   
	/* Moo's Armor (Date added unknown) */
    public static ItemStack HIDE_HELMET = new CustomArmor(new CustomItem(Material.LEATHER_HELMET, "&6Moo's Hide Helmet", 0, new String[0], new String[] {"DURABILITY-5", "THORNS-3", "PROTECTION_ENVIRONMENTAL-10"}), Color.PURPLE);
    public static ItemStack HIDE_CHESTPLATE = new CustomArmor(new CustomItem(Material.LEATHER_CHESTPLATE, "&6Moo's Hide Chestplate", 0, new String[0], new String[] {"DURABILITY-5", "THORNS-3", "PROTECTION_ENVIRONMENTAL-10"}), Color.PURPLE);
    public static ItemStack HIDE_LEGGINGS = new CustomArmor(new CustomItem(Material.LEATHER_LEGGINGS, "&6Moo's Hide Leggings", 0, new String[0], new String[] {"DURABILITY-5", "THORNS-3", "PROTECTION_ENVIRONMENTAL-10"}), Color.PURPLE);
    public static ItemStack HIDE_BOOTS = new CustomArmor(new CustomItem(Material.LEATHER_BOOTS, "&6Moo's Hide Boots", 0, new String[0], new String[] {"DURABILITY-5", "THORNS-3", "PROTECTION_ENVIRONMENTAL-10"}), Color.PURPLE);
	
    public static ItemStack MOOS_KILT = new CustomArmor(new CustomItem(Material.LEATHER_LEGGINGS, "&6Moo's Kilt", 0, new String[0], new String[] {"DURABILITY-10", "THORNS-10", "PROTECTION_ENVIRONMENTAL-10", "PROTECTION_FIRE-10" }), Color.BLUE);
    public static ItemStack MOOS_CAP = new CustomArmor(new CustomItem(Material.LEATHER_HELMET, "&6Moo's Cap", 0, new String[0], new String[] {"DURABILITY-10", "THORNS-10", "PROTECTION_ENVIRONMENTAL-10", "PROTECTION_EXPLOSIONS-10"}), Color.BLUE);
    public static ItemStack MOOS_TUNIC = new CustomArmor(new CustomItem(Material.LEATHER_CHESTPLATE, "&6Moo's Tunic", 0, new String[0], new String[] {"DURABILITY-10", "THORNS-10", "PROTECTION_ENVIRONMENTAL-10"}), Color.BLUE);
    public static ItemStack MOOS_BOOTS = new CustomArmor(new CustomItem(Material.LEATHER_BOOTS, "&6Moo's Boots", 0, new String[0], new String[] {"DURABILITY-5", "THORNS-3", "PROTECTION_ENVIRONMENTAL-10", "OXYGEN-1"}), Color.PURPLE);
	
	/* Hazmat Suit 2.0 (09/09/19) */
    public static ItemStack CARBON_FIBRE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWQzMTY2OGQxMTYzOTdmYTdiY2JlODk0NGQ1MWQyMjkwOWUzYjgwYzlkM2RkZTE5ZmY0ODZmZGRmZTA2ZCJ9fX0="), "&6Carbon Fibre");
    public static ItemStack LEAD_FIBRE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjc0MTc5MjQ1NjY3ZTYxNjY5Y2MxNDFhM2RjNDZkMjU5ZjE1OWEyOGVhMWI0NjkzNjQyZDlhMzEyNmRhOTU3ZCJ9fX0="), "&6Lead Fibre");
    public static ItemStack WONDER_FIBRE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjdiNzQ3YjM3OGE0MWEwYTZlZGM4NmMwMDBmMDQwYzY5OTRhODMzMjUxMTk2YzlkNTJjMmEyMzBmOTUxNjBjYyJ9fX0="), "&6Wonder Fibre");
    public static ItemStack WONDER_WOOL = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDRiMDM3OTRiOWIzZTNiNWQwN2UzYmU2OGI5NmFmODdkZjIxNWMzNzUyZTU0NzM2YzgwZjdkNTBiZDM0MzdhNCJ9fX0="), "&6Wonder Wool");
    public static ItemStack HAZMAT_CLOTH = new CustomItem(new ItemStack(Material.ORANGE_CARPET), "&bHazmat Cloth");
    public static ItemStack VISOR = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYThjODU2MzY2YzY0Nzc0YWY2MjJkZjkwY2ViMTNjYzkxNjcyNzk0ZTc0OWE2MmJkMDFjYjg3MmRhNzE2ZCJ9fX0="), "&6Visor");
    public static ItemStack RUBBER = new CustomItem(new ItemStack(Material.CHARCOAL), "&6Rubber");
    public static ItemStack CARBON_FIBRE_ROPE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjg5ZjRmOGYxMWNkNjA2YmNmZGU4MzQyZTIxNTM2YjFmYmMxNmY3YzU2MTFhN2ZlY2FiMzdjYWVlOGRiNWNhIn19fQ=="), "&bCarbon Fibre Rope");
	
    public static ItemStack HAZMAT_HELMET = new CustomArmor(new CustomItem(Material.LEATHER_HELMET, "&bHazmat Helmet", "", "&4&oPart of Hazmat Suit"), Color.ORANGE);
    public static ItemStack HAZMATSUIT_CHESTPLATE = new CustomArmor(new CustomItem(Material.LEATHER_CHESTPLATE, "&bHazmat Suit", "", "&4&oPart of Hazmat Suit"), Color.ORANGE);
    public static ItemStack HAZMATSUIT_LEGGINGS = new CustomArmor(new CustomItem(Material.LEATHER_LEGGINGS, "&bHazmat Leggings", "", "&4&oPart of Hazmat Suit"), Color.ORANGE);
    public static ItemStack HAZMAT_BOOTS = new CustomArmor(new CustomItem(Material.LEATHER_BOOTS, "&bHazmat Boots", "", "&4&oPart of Hazmat Suit"), Color.BLACK);
	
	
    public static ItemStack TITANIUM_DUST = new CustomItem(Material.SUGAR, "&6Titanium Dust");
    public static ItemStack CHROMIUM_DUST = new CustomItem(Material.SUGAR, "&6Chromium Dust");
    public static ItemStack TITANIUM_INGOT = new CustomItem(Material.IRON_INGOT, "&bTitanium Ingot");
    public static ItemStack CHROMIUM_INGOT = new CustomItem(Material.IRON_INGOT, "&bChromium Ingot");
    public static ItemStack WONDER_DUST = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY5ZTNlNmU1YjJiOTJmMGJlYjM2OGI3MzhiOTkzZDdiYTIyNWJmOWJiMjc1OGJmYzlmYzJkYWJhNGE1YTdkIn19fQ=="), "&bWonder Dust");
    public static ItemStack WONDER_SOLDER_INGOT = new CustomItem(Material.IRON_INGOT, "&bWonder Solder Ingot");
    public static ItemStack WONDER_ALLOY = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGVhZjE5YWIyN2E3Yjk4ZGQ0NmI1YmE5YjkwMWI3MWIwZTVlZGEyMzI0MzlmYzhiMjk2ZWJkYjQ1MGJkM2U0NiJ9fX0="), "&bWonder Alloy");
    public static ItemStack WONDER_ALLOY_BLOCK = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2ZmYzk3N2NjN2UxMGU1NjRhMDk2MzhhNTNiYmM0YzU0YzljOGRhYzc0NTBiYTNkZmEzYzkwOTlkOTRmNSJ9fX0="), "&bWonder Alloy Block");
    public static ItemStack CARBONADO_BLOCK = new CustomItem(Material.COAL_BLOCK, "&bCarbonado Block");
    public static ItemStack SAW_BLADE = new CustomItem(Material.MUSIC_DISC_MELLOHI, "&bSaw Blade");
	
    
	
    public static ItemStack WHEEL = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWIxZmRhMDQyOTkwODliMDFjY2VlMzljZjg4ZTljNzFiZGQzOTRiZmU0NzNlYWUzNjRmNTM3OTNhNmQ1OTMifX19"), "&bWheel");
    public static ItemStack SPINDEL = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY0NmE2MmZlYzJkZDk3ZjVlNGFhMzI5YWU0NDZjNjAxMWE4ZThlMmQ4MTdhZWViOGZkYzExY2M1NGVjMmViZSJ9fX0="), "&bSpindel");
    public static ItemStack COGWHEEL = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTEyM2I4ODg0NmQ2NmUxY2ZlMmY2NjRhMzZhZDRhMjJiMWE0YzJmMmU0ZDI5NWY0MWZlNWU5MjliOWU3ZDgifX19"), "&bCogwheel");
	
    //ItemStack NETHER_STAR = new CustomItem(Material.NETHER_STAR, "&bNether Star", "", "&rWonder Machine Forged Nether Star!");
    public static ItemStack WONDER_SOLAR_GENERATOR = new CustomItem(Material.DAYLIGHT_DETECTOR, "&bWonder Solar Generator", "&9Works at Night", "", "&4End-Game Generator", "&8\u21E8 &e\u26A1 &70 J Buffer", "&8\u21E8 &e\u26A1 &72048 J/s (Day)", "&8\u21E8 &e\u26A1 &71024 J/s (Night)");
    public static ItemStack WONDER_SOLAR_GENERATOR_II = new CustomItem(Material.DAYLIGHT_DETECTOR, "&bWonder Solar Generator II", "&9Works at Night", "", "&4End-Game Generator", "&8\u21E8 &e\u26A1 &70 J Buffer", "&8\u21E8 &e\u26A1 &74096 J/s (Day)", "&8\u21E8 &e\u26A1 &72048 J/s (Night)");
    public static ItemStack WONDER_SOLAR_GENERATOR_III = new CustomItem(Material.DAYLIGHT_DETECTOR, "&bWonder Solar Generator III", "&9Works at Night", "", "&4End-Game Generator", "&8\u21E8 &e\u26A1 &70 J Buffer", "&8\u21E8 &e\u26A1 &78192 J/s (Day)", "&8\u21E8 &e\u26A1 &74096 J/s (Night)");
    public static ItemStack VOID_SOLAR_GENERATOR = new CustomItem(Material.DAYLIGHT_DETECTOR, "&bVOID Solar Generator", "&9Works at Night", "", "&4End-Game Generator", "&8\u21E8 &e\u26A1 &70 J Buffer", "&8\u21E8 &e\u26A1 &72048 J/s (Day)", "&8\u21E8 &e\u26A1 &71024 J/s (Night)");
    public static ItemStack PITCH_FORK = new CustomItem(Material.IRON_HOE, "&6Pitch Fork", "", "&r3x3 hoe!");
	
	/* Elytra Recipe Remake (4/23/19) */
    public static ItemStack ELYTRA_REMAKE = new CustomItem(Material.ELYTRA, "&6Elytra", "&rZoom around in this Elytra");
	
	/* Hi Tech Components Recipes (09/06/19) */
    public static ItemStack PRINTED_CIRCUIT_BOARD = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTQ2MTIwNDk5YjMyNDYzOWNkOWExNzc5OWVhMWRmZmYzNzNlNzlhZmUxZjBkOGI1MzI4Y2Y0Nzg5NmM0Nzc1In19fQ=="), "&bPrinted Circuit Board");;
    public static ItemStack ANTENNA = new CustomItem(Material.END_ROD, "&bAntenna", "&rPicks up broadcast signals");
    public static ItemStack POWER_PISTON = new CustomItem(Material.STICKY_PISTON, "&bPower Piston", "&rCan Crush Almost Anything");
	
	/* Export Recipes (09/06/19) */
    public static ItemStack TV = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWM5MDE0ODg1ZWEzM2JkNjY3NWIzYzhmY2VmZWM0YWU3MmUzY2RlMzMyYTQ4ZTcxZjBkMGY0NTQ0ZjBhMiJ9fX0="), "&bTV");
    public static ItemStack DONUT = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3YzliODJiMTg2NjU2ZTlmNjM2M2EyYTFjNmE0YjViOTNjZmE5ZWY0ZGFkNmYxNmI5NGViYjVlMzYyNjc4In19fQ=="), "&bDonut", "&bYum Yum");
    public static ItemStack TOY_CAR = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTg3NzhjMzRmYmEyOTY1MmNmYWM4ZDNmNmZiMzVjNDljYmMyMzYyYjJiOGYxMzc4ZDgzYjMwZjQ2M2IxODEifX19"), "&bToy Car");
    public static ItemStack RECORD_PLAYER = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTQyOGI3MzQ4Mzc2ZDhmMzI5NjY4ZTEwNjQ2NmM1MTYxMGI3YWJlYzM2YTc4YmE1YzE4YWE3MzA2ZCJ9fX0="), "&bRecord Player");
	
	/* Food Items (10/06/19) */
    public static ItemStack DESALINATION_PLANT = new CustomItem(new ItemStack(Material.LIGHT_BLUE_CONCRETE_POWDER), "&3Desalination Plant", "&7Extract Salt From Water");
    public static ItemStack FRYER = new CustomItem(new ItemStack(Material.BONE_BLOCK), "&3Fryer", "&7Cook Yummy Food!");
    public static ItemStack ELECTRIC_SAW_MILL = new CustomItem(new ItemStack(Material.OAK_WOOD), "&bElectric Saw Mill", "&7Convert logs to planks");
    
    public static ItemStack INDUSTRIAL_GRINDER = new CustomItem(new ItemStack(Material.DROPPER), "&bIndustrial Grinder", "&7Required to make dust from very hard materials");
    
    public static ItemStack NETHERITE_DUST = new CustomItem(Material.BROWN_DYE, "&bNetherite Dust");  
    
    public static ItemStack DIAMOND_EDGED_CUTTER = new CustomItem(Material.MUSIC_DISC_11, "&bDiamond Edged Cutter");
    public static ItemStack HARDENED_NETHERITE_INGOT = new CustomItem(Material.NETHERITE_INGOT, "&b Hardened Netherite Ingot");  
    public static ItemStack MOOS_ENHANCED_M_PICKAXE = new CustomItem(Material.NETHERITE_PICKAXE, "&bMoo's Marvellous Enhanced Pickaxe", "", "&rAllows you to mine a humungous bit", "&rof Blocks at once...BIG BOOM", "", "&95x5x5");
   

    
    
    public static ItemStack WONDER_FURNACE = new CustomItem(new ItemStack(Material.FURNACE), "&bWonder Furnace", "&7No More Messy Fuel");
    public static ItemStack CANNARY = new CustomItem(new ItemStack(Material.BLACK_TERRACOTTA), "&3Cannary", "&7Put items in tin cans");
    public static ItemStack REANIMATOR = new CustomItem(new ItemStack(Material.NETHER_WART_BLOCK), "&3Re-animator", "&7Put items in eggs");
    public static ItemStack DEEP_FREEZE = new CustomItem(new ItemStack(Material.CYAN_CONCRETE_POWDER), "&bDeep Freeze", "&7It's like the Artic inside");
    public static ItemStack NETHER_MAKER = new CustomItem(new ItemStack(Material.NETHERRACK), "&3Nether Maker", "&7This makes nether things");
    
    public static ItemStack ELECTRIC_PURIFIER = new CustomItem(new ItemStack(Material.PURPLE_STAINED_GLASS), "&bElectric Purifier", "&7Cleans Pure Ore Clusters");
    public static ItemStack ELECTRIC_PURIFIER_II = new CustomItem(new ItemStack(Material.PURPLE_STAINED_GLASS), "&bElectric Purifier II", "&7Cleans Pure Ore Clusters, Less Waste");
    
    //new items 29/05/2020
    public static ItemStack SMALL_HADRON_COLLIDER = new CustomItem(new ItemStack(Material.GLOWSTONE), "&bSmall Hadron Collider", "&7Smash Patricles at High Speeds", "&7Make a new elements, energy intensive");
    public static ItemStack MOOINIUM = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWVkYTcxYmIyZmUyZDgxM2MzMmNlY2VjNzhhODFkY2I1YTY5YjA1YmNmOTcyNjJiN2NhMjhlYjZhZGMwNDE5MSJ9fX0="), "&6Mooinium", "", "&eSuper Rare Element");
    public static ItemStack CARBON_COLLECTOR = new CustomItem(new ItemStack(Material.COAL_BLOCK), "&bCarbon Collector", "&7Use Mooinium to react with air", "&7to produce 32 carbon");
    public static ItemStack ELECTRIC_RECYCLER = new CustomItem(new ItemStack(Material.OBSERVER), "&bElectric Recycler", "&7Electric Recycler Machine", "&7Repurpose Slimefun junk");
	
    public static ItemStack YEAST = new CustomItem(Material.MELON_SEEDS, "&bYeast");
    public static ItemStack DOUGH = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmI2MzQwMWEzY2YzMmYyODVhZjdlZTJkMzBlZTMzMzBjMzMzYTk0NmU4ZjBhODE2OWVlMTBkNTcxZmI4MDdmIn19fQ=="), "&bDough");
    public static ItemStack TIN_CARROTS = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjY1YzNiZGIzZDM2ZWVkNWNkZGY3NWIxZmI0NmRhZjhjMWZjODM0Mjg1NDBhMWU0YTZlNmQ2NjNlNWY4OWQ5OCJ9fX0="), "&bTin Carrots");
    public static ItemStack TIN_POTATOES = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGU3MDhiMTNiYWZiNWE3NThkOWQyZjAxMzZmM2I3N2QxMzk1ODNiODZkNDY3ZGU4NmQyMTYyNmQwNDUzNGUifX19"), "&bTin Potatoes");
    public static ItemStack TIN_BEETROOT = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWQ5MmZlZWExZGVlZTYxY2Q1ZTIzOWNiM2VkOWM0NDRlNTk3YzkxODMzMjZkMWZlODRiOTUyYzg1ZmVhMzNhIn19fQ=="), "&bTin Beetroot");
    
    public static ItemStack WONDER_PATTY = new CustomItem(Material.COOKED_BEEF, "&bWonder Patty");
    public static ItemStack BUN = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWIzYzdkOGQ4OWRhZTE2YTI5ZWVhMWE0Mzk4ZWVlYmFiZmFmYmYwMjhkNmUyNWVjYWU5NzdlZmUzMGM4In19fQ=="), "&bBun");
    public static ItemStack MOOS_BURGER = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjY4ZWZhNTZlZjMxMzZlNTNhOWJmNDMwZWY3NmQ1MDE1M2ZiYmNjMTI5NWU2NGIzNDdmNTNlMTBlNTU3ZjA3YSJ9fX0="), "&bMoo's Burger");
    public static ItemStack RAW_FRIES = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjllZjZmZGU2MzU2ZTA1YzBmZTk5NDFkNzM4MTNmY2JmZjlmNWFjNjEzMmVjNjk4MzY3NzM0NTUzYjRkODZjNyJ9fX0="), "&bRaw Fries");
    public static ItemStack MOOS_FRIES = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTBlYWNhYzQxYTllYWYwNTEzNzZlZjJmOTU5NzAxZTFiYmUxYmY0YWE2NzE1YWRjMzRiNmRjMjlhMTNlYTkifX19"), "&bMoo's Fries");
    public static ItemStack ICE_CREAM = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDQ3YmI3NWJhY2JjMmM4YzA2MTczZmEwYjRiZWZjOTg2Mjc0NWYyMzZiN2I4NmQyMjE0ZTk1ZmNkNmU5MDk3NyJ9fX0="), "&bIce Cream");
    public static ItemStack MOO_SHAKE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOThlNjlmYmRmYjNmZDlmMGM2OWE3MTkwNjYxYjhhNjZjNWFiODUxNmQ0YjExZDFiZGM4NGM1Yjc1M2U3MzVjIn19fQ=="), "&bMoo Shake");
    public static ItemStack MOO_MEAL = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTg0YzUwMzUxZDY0ZTYzOTY1NzQ4YTc2MWJkMjgyMDcwNjQyOTE1ZTdiN2Y4ZGEyOWEwNTA3NmYyOWY4In19fQ=="), "&bMoo Meal");
    public static ItemStack MOO_PIZZA = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjFkOWQyMzAxYzYwZDVjZDg5NDlkYTY2NGZkNWM2M2JkNWU1NzEzMTE2MTgzYjY2MzE4YTFmYzk5YWIwYjFlNCJ9fX0="), "&bMoo's Pizza");
    public static ItemStack MOO_SOUP = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2IyNDliNzc5NTM1ZDVmMjAyNGZkZjg4ZDUzZWI2Mjc5NWY1NzgwOWU1YWY0ZGI2NjhmNzVlMDZjZTNjMyJ9fX0="), "&bMoo's Soup");
    public static ItemStack MOOS_DONUT = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3YzliODJiMTg2NjU2ZTlmNjM2M2EyYTFjNmE0YjViOTNjZmE5ZWY0ZGFkNmYxNmI5NGViYjVlMzYyNjc4In19fQ=="), "&bMoo's Donut");
    
    public static ItemStack SUPER_TNT = new CustomItem(Material.TNT, "&6Super TNT", "", "&rBOOM!");


    
    
    //booze
    public static ItemStack ALCOHOL = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU5NzYyZTUzOTM1Y2I5NTYxNmE2MGY5MzZmOGYxNzgxZWQzYzI3OWY5NjVhMWI2ZWE5NTA2NDkyODFkNTY3In19fQ=="), "&bAlcohol");

    
	/* Misc Items*/
	
    public static ItemStack STEPS_DISCOGRAPHY = new CustomItem(Material.MUSIC_DISC_13, "&bSteps Discography", "&7Let's 5,6,7,8");
    public static ItemStack SULFURIC_ACID = new CustomItem(Material.HONEY_BOTTLE, "&6Sulphuric Acid", "", "&rCorrosive!");
    public static ItemStack CALCIUM = new CustomItem(Material.SUGAR, "&6Calcium", "", "");
    public static ItemStack EGG_SHELL = new CustomItem(Material.EGG, "&6Egg shell", "", "");
    public static ItemStack DNA = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmZmMDU1NTE0MGI2NzZhYzY0NzU5YmRhZjNhZjNmZDVlNWFjZDZkNDJhNTNiOWQwZDI2ZDRhNjc3ZmJlYzExMSJ9fX0="), "&6DNA", "", "&eEssence of life");
 
    
    
    public static ItemStack COW_ESSENCE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWQ2YzZlZGE5NDJmN2Y1ZjcxYzMxNjFjNzMwNmY0YWVkMzA3ZDgyODk1ZjlkMmIwN2FiNDUyNTcxOGVkYzUifX19"), "&bCow Essence");
    public static ItemStack PIG_ESSENCE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTIzOWRjNWUxYmFjZDUxOGY4YjJhMmZlZjUxNWM2YTRiYWRlYjJjNjQ1MzkzMTNiNWZlMjZmMGI4OTk5MDZhIn19fQ=="), "&bPig Essence");
    public static ItemStack SHEEP_ESSENCE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjk3MTllYzllZTZmNTNjMTdiMWY2Zjc0Nzg2NmIzMTIxNDAxZDM1Zjc3YTM5ODU5ZjEyMmQ0NzI4NjNlNDhlIn19fQ=="), "&bSheep Essence");
    public static ItemStack WOLF_ESSENCE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDM1OTUzN2MxNTUzNGY2MWMxY2Q4ODZiYzExODc3NGVkMjIyODBlN2NkYWI2NjEzODcwMTYwYWFkNGNhMzkifX19"), "&bWolf Essence");
    public static ItemStack SQUID_ESSENCE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMDE0MzNiZTI0MjM2NmFmMTI2ZGE0MzRiODczNWRmMWViNWIzY2IyY2VkZTM5MTQ1OTc0ZTljNDgzNjA3YmFjIn19fQ=="), "&bSquid Essence");
    public static ItemStack BEE_ESSENCE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTQ3MzIyZjgzMWUzYzE2OGNmYmQzZTI4ZmU5MjUxNDRiMjYxZTc5ZWIzOWM3NzEzNDlmYWM1NWE4MTI2NDczIn19fQ=="), "&bBee Essence");
    public static ItemStack VILLAGER_ESSENCE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2E0MTA2MWVkODU0MTUxZmRkYTEzZjY4M2RiZTI5OTdhMjczNWNhYTVhMmE1OWE1Njk5MzE0NjAyYTE0ZjkifX19"), "&bVillager Essence");
    public static ItemStack MOOSHROOM_ESSENCE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWQ5MDk4Nzk2MmYzYThiZjk0YTE5N2QyYjk1MGNkZjFkZDc4ZDhiOTcyNTc3YmNhY2Q0ZDU4MzhmNzBhNDcyOSJ9fX0="), "&bMooshroom Essence");
    public static ItemStack PANDA_ESSENCE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWMyZDI1ZTk1NjMzN2Q4Mjc5MWZhMGU2NjE3YTQwMDg2ZjAyZDZlYmZiZmQ1YTY0NTk4ODljZjIwNmZjYTc4NyJ9fX0="), "&bPanda Essence");
    public static ItemStack HORSE_ESSENCE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjI4ZDFhYjRiZTFlMjhiN2I0NjFmZGVhNDYzODFhYzM2M2E3ZTVjMzU5MWM5ZTVkMjY4M2ZiZTFlYzlmY2QzIn19fQ=="), "&bPanda Essence");
    public static ItemStack DOLPHIN_ESSENCE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGU5Njg4Yjk1MGQ4ODBiNTViN2FhMmNmY2Q3NmU1YTBmYTk0YWFjNmQxNmY3OGU4MzNmNzQ0M2VhMjlmZWQzIn19fQ=="), "&bDolphin Essence");
    public static ItemStack FOX_ESSENCE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmJkZmZlOTY0MmZjNTI4MGU2OGNlNDg4ZTNiY2Y0NDA2ODdlZDNiYzU2NmUzMTVhZjgyNGY0MjhiNmZmNzE1In19fQ=="), "&bFox Essence");
    public static ItemStack TURTLE_ESSENCE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWFlOTAzNzEzN2I2Mjg4NDJhMjkzODBmODI4YzI2ZWM2NjE4NjY0YzM4MDJjOTk0NWY0YzAxYTk0M2FhNDBlOSJ9fX0="), "&bTurtle Essence");
    public static ItemStack PARROT_ESSENCE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzEzNWY1ODM4ZTUxNTg1ZTE1OTQyZThmMmYxY2MzNmRhOTYzMDcwZGRkNWJhMzVhZjk1ZWQzN2ViNmMzIn19fQ=="), "&bParrot Essence");
    public static ItemStack RABBIT_ESSENCE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZThhNGZiMjVhZmJjNmI3YWVjMTVhYmU4NzJmY2VhZDFlNWIzM2MxYWIxMjUyNTE0MWQ3N2JmZDI5OGZjMzJkOSJ9fX0="), "&bRabbit Essence");
    public static ItemStack POLAR_BEAR_ESSENCE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWFmM2IxYWNjZjkyYzFlMDg0MWFmNDY1MTBiNTlkZjNjNDZkNmI2OTIzYzZiOWYzMDNiZjFlZWJhYWU4NSJ9fX0="), "&bPolar Bear Essence");
    public static ItemStack GUARDIAN_ESSENCE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzI1YWY5NjZhMzI2ZjlkOTg0NjZhN2JmODU4MmNhNGRhNjQ1M2RlMjcxYjNiYzllNTlmNTdhOTliNjM1MTFjNiJ9fX0="), "&bGuardian Essence");
    public static ItemStack LLAMA_ESSENCE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQyNDc4MGIzYzVjNTM1MWNmNDlmYjViZjQxZmNiMjg5NDkxZGY2YzQzMDY4M2M4NGQ3ODQ2MTg4ZGI0Zjg0ZCJ9fX0="), "&bLlama Essence");
    public static ItemStack SLIME_ESSENCE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODk1YWVlYzZiODQyYWRhODY2OWY4NDZkNjViYzQ5NzYyNTk3ODI0YWI5NDRmMjJmNDViZjNiYmI5NDFhYmU2YyJ9fX0="), "&bSlime Essence");
  
    
    
    public static ItemStack COW_SPAWN_EGG = new CustomItem(Material.COW_SPAWN_EGG, "&6Spawn egg of cow", "", "");
    public static ItemStack PIG_SPAWN_EGG = new CustomItem(Material.PIG_SPAWN_EGG, "&6Spawn egg of pig", "", "");
    public static ItemStack SHEEP_SPAWN_EGG = new CustomItem(Material.SHEEP_SPAWN_EGG, "&6Spawn egg of sheep", "", "");
    public static ItemStack WOLF_SPAWN_EGG = new CustomItem(Material.WOLF_SPAWN_EGG, "&6Spawn egg of wolf", "", "");
    public static ItemStack SQUID_SPAWN_EGG = new CustomItem(Material.SQUID_SPAWN_EGG, "&6Spawn egg of squid", "", "");
    public static ItemStack BEE_SPAWN_EGG = new CustomItem(Material.BEE_SPAWN_EGG, "&6Spawn egg of bee", "", "");
    public static ItemStack VILLAGER_SPAWN_EGG = new CustomItem(Material.VILLAGER_SPAWN_EGG, "&6Spawn egg of villager", "", "");
    public static ItemStack MOOSHROOM_SPAWN_EGG = new CustomItem(Material.MOOSHROOM_SPAWN_EGG, "&6Spawn egg of mooshroom", "", "");
    public static ItemStack PANDA_SPAWN_EGG = new CustomItem(Material.PANDA_SPAWN_EGG, "&6Spawn egg of panda", "", "");
    public static ItemStack HORSE_SPAWN_EGG = new CustomItem(Material.HORSE_SPAWN_EGG, "&6Spawn egg of horse", "", "");
    public static ItemStack DOLPHIN_SPAWN_EGG = new CustomItem(Material.DOLPHIN_SPAWN_EGG, "&6Spawn egg of dolphin", "", "");
    public static ItemStack FOX_SPAWN_EGG = new CustomItem(Material.FOX_SPAWN_EGG, "&6Spawn egg of fox", "", "");
    public static ItemStack TURTLE_SPAWN_EGG = new CustomItem(Material.TURTLE_SPAWN_EGG, "&6Spawn egg of turtle", "", "");
    public static ItemStack PARROT_SPAWN_EGG = new CustomItem(Material.PARROT_SPAWN_EGG, "&6Spawn egg of parrot", "", "");
    public static ItemStack RABBIT_SPAWN_EGG = new CustomItem(Material.RABBIT_SPAWN_EGG, "&6Spawn egg of rabbit", "", "");
    public static ItemStack POLAR_BEAR_SPAWN_EGG = new CustomItem(Material.POLAR_BEAR_SPAWN_EGG, "&6Spawn egg of polar bear", "", "");
    public static ItemStack GUARDIAN_SPAWN_EGG = new CustomItem(Material.GUARDIAN_SPAWN_EGG, "&6Spawn egg of guardian", "", "");
    public static ItemStack LLAMA_SPAWN_EGG = new CustomItem(Material.LLAMA_SPAWN_EGG, "&6Spawn egg of llama", "", "");
    public static ItemStack SLIME_SPAWN_EGG = new CustomItem(Material.SLIME_SPAWN_EGG, "&6Spawn egg of slime", "", "");
    



    
	/* Spawners */
    public static ItemStack WONDER_SPAWNER = new CustomItem(Material.SPAWNER, "&bWonder Spawner", "Can contain the power of life!!");
    public static ItemStack REINFORCED_BARS = new CustomItem(Material.IRON_BARS, "&bReinfornced Bars", "Unbreakable bars used to contain evil");
    public static ItemStack LIFE_ESSENCE = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjZlODg1OGM2NDRkOWFjY2RkNmJkMGU2YjY5OTU3NTE0NWM5OWY2YzQ2NjcyODY2ZjhiMjlhMWQ2OWM0MGZjIn19fQ=="), "&bLife Essence", "&cCannot be crafted", "", "Can be won or /buy");
    public static ItemStack WONDER_ZOMBIE_SPAWNER = new CustomItem(Material.SPAWNER, "&eZombie &rSpawner");
    public static ItemStack WONDER_SKELETON_SPAWNER = new CustomItem(Material.SPAWNER, "&eSkeleton &rSpawner");
    public static ItemStack WONDER_BLAZE_SPAWNER = new CustomItem(Material.SPAWNER, "&eBlaze &rSpawner");
    public static ItemStack WONDER_WITHER_SKELETON_SPAWNER = new CustomItem(Material.SPAWNER, "&eWither &rSpawner");
    public static ItemStack WONDER_VEX_SPAWNER = new CustomItem(Material.SPAWNER, "&eVex &rSpawner");
    public static ItemStack WONDER_COW_SPAWNER = new CustomItem(Material.SPAWNER, "&eCow &rSpawner");
    public static ItemStack WONDER_PIG_SPAWNER = new CustomItem(Material.SPAWNER, "&ePig &rSpawner");
	public static ItemStack WONDER_CHICKEN_SPAWNER = new CustomItem(Material.SPAWNER, "&eChicken &rSpawner");
	public static ItemStack WONDER_SHEEP_SPAWNER = new CustomItem(Material.SPAWNER, "&eSheep &rSpawner");

	
	public static ItemStack APPLE_SCHNAPPS = new CustomPotion("&cApple Schnapps", Color.GREEN, new PotionEffect(PotionEffectType.SATURATION, 1, 0), "&rApple Schnapps", "&cGets you drunk", "", "");
	public static ItemStack MIDURI = new CustomPotion("&cMiduri", Color.GREEN, new PotionEffect(PotionEffectType.SATURATION, 1, 0), "&rMiduri", "&cGets you drunk", "", "");
    public static ItemStack PENCIL = new CustomItem(Material.STICK, "&6Penicl", "", "Great for writing names");
    public static ItemStack DUBNIUM = new SlimefunItemStack("DUBNIUM", new ColoredFireworkStar(Color.fromRGB(153, 0, 255), "&6Dubnium Dust", "", "&7 Very short half life", "Very Radioactive"));
    public static ItemStack POWER_CELL = new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWFjNTI0MTliOTkwMjU4MjhjODlmYTgyNTk0NWU2OTQ4ZTQ1YmI1YTIyZTQ0MjVhNTllOTA5NmU0YzFhYzM4In19fQ=="), "&bPower Cell", "", "", "Extreme portable power");
    
    
    public static ItemStack WONDER_MINER_ANDROID_I =  new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjNlMWFkZjk5ODcyYjBmMDA4YWUyZGFlYzg2YjZmZDNhZDk5MjQ4M2Y3ZmI3MzNiZTk3M2UzOGU0MmY0NzZhOCJ9fX0="), "&6Wonder Miner Android I", "", "&eMines 1 Stone/Cobble to 2 Cobble", "&cToo heavy to be programmed", "&cto move. Strange consequences");
    public static ItemStack WONDER_MINER_ANDROID_II =  new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjNlMWFkZjk5ODcyYjBmMDA4YWUyZGFlYzg2YjZmZDNhZDk5MjQ4M2Y3ZmI3MzNiZTk3M2UzOGU0MmY0NzZhOCJ9fX0="), "&6Wonder Miner Android II", "", "&eMines 1 Stone/Cobble to 3 Cobble", "&cToo heavy to be programmed", "&cto move. Strange consequences");
    public static ItemStack WONDER_MINER_ANDROID_III =  new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjNlMWFkZjk5ODcyYjBmMDA4YWUyZGFlYzg2YjZmZDNhZDk5MjQ4M2Y3ZmI3MzNiZTk3M2UzOGU0MmY0NzZhOCJ9fX0="), "&6Wonder Miner Android III", "", "&eMines 1 Stone/Cobble to 4 Cobble", "&cToo heavy to be programmed", "&cto move. Strange consequences");
    public static ItemStack WONDER_MINER_ANDROID_IV =  new CustomItem(getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjNlMWFkZjk5ODcyYjBmMDA4YWUyZGFlYzg2YjZmZDNhZDk5MjQ4M2Y3ZmI3MzNiZTk3M2UzOGU0MmY0NzZhOCJ9fX0="), "&6Wonder Miner Android IV", "", "&eMines 1 Stone/Cobble to 5 Cobble", "&cToo heavy to be programmed", "&cto move. Strange consequences");
    
    public static ItemStack PIE_TIN =new CustomItem(Material.BOWL, "&bPie tin");
    
	
	public static CustomFood toast;
	public static CustomFood toast_jam;
	public static CustomFood toast_honey;
	public static CustomFood donut;
	public static CustomFood burger;
	public static CustomFood berry_jam;
	public static CustomFood berry_pie;
	public static CustomFood waffles;
	public static CustomFood chocolate_bar;
	public static CustomFood hot_chocolate;
	public static CustomFood chocolate_cake;
	public static CustomFood cream_cookie;
	public static CustomFood pumpkin_muffin;
	public static CustomFood hot_dog;
	public static CustomFood scrambled_egg;
	public static CustomFood custard;
	
	public static CustomFood apple_schnapps;
	public static CustomFood berry_schnapps;
	public static CustomFood miduri;
	
    public static CustomFood ORANGE;
    public static CustomFood BREADCRUMBS;  
    public static CustomFood LEMON;
    public static CustomFood LIME;
    public static CustomFood PLUM;
    public static CustomFood CHERRY;
    public static CustomFood BLUEBERRY;   
    public static CustomFood ORANGE_JAM;
    public static CustomFood LEMON_JAM;    
    public static CustomFood LIME_JAM;  
    public static CustomFood PLUM_JAM; 
    public static CustomFood CHERRY_JAM;  
    public static CustomFood BLUEBERRY_JAM;  
    public static CustomFood APPLE_JAM;
    public static CustomFood PASTA;
    public static Paracetamol PARACETMOL;
    public static CustomFood CREEPER_CAKE;  
    public static CustomFood ROAST_CHICKEN;  
    public static CustomFood FRIED_CHICKEN;  
    public static CustomFood ZOMBIE_COCKTAIL;
    public static CustomFood APPLE_PIE;  
    public static CustomFood LIME_PIE;
    public static CustomFood LEMON_PIE;  
    public static CustomFood CHERRY_PIE; 
    public static CustomFood BLUEBERRY_PIE;  
    public static CustomFood PLUM_PIE;  
    public static CustomFood SUSHI;  
    public static CustomFood LASAGNA;
    public static CustomFood HOT_LEMONADE;
    public static CustomFood TIRAMISU;  

	
	/* Space and tech age (In Progress started 09/16/19) 
	public static ItemStack VOID_RESEARCH_STATION = new CustomItem(Material.END_PORTAL_FRAME, "&cVoid &6Research Station", "&dWelcome to the age of Tech!");
	public static ItemStack SMR_BETA = new CustomItem(Material.BEACON, "&bSMR &cBETA", "&bSpacial Matter Reformation", "&rFormulate matter from nothing");
	public static ItemStack DRAGON_ESSENCE = new CustomItem(Material.FIREWORK_STAR, "&cDragon &4Essence", "&rHolds a new element inside");
	public static ItemStack UNOBTANIUM = new CustomItem(Material.FIRE_CHARGE, "&bUnobtanium", "&rSeems mysterious!", "&rYou will need to do some research", "&r   to really understand this material");
	*/
	private static ItemStack getSkull(String texture) {
		try {
			return CustomSkull.getItem(texture);
		}
		catch(Exception x) {
			//Slimefun.getLogger().log(Level.SEVERE, "An Error occured while initializing the Items for Slimefun ", x);
			
			return new ItemStack(Material.PLAYER_HEAD);
		}
	}
	/*
	WONDER_ZOMBIE_SPAWNER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzNmYjRlNWRiOTdmNDc5YzY2YTQyYmJkOGE3ZDc4MWRhZjIwMWE4ZGRhZjc3YWZjZjRhZWY4Nzc3OWFhOGI0In19fQ=="), "&bWonder Zombie Spawner", "Give this token to Mootilate or Yilmaz", "Who will redeem it for the correct spawner", "", "If Mootilate or Yilmaz is not available", "Place the token in a chest and DM", "Co-ords of the chest to Mootilate or Ylimaz");
	WONDER_SKELETON_SPAWNER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQ3MzkwMzFmMjFiM2ExNDFjN2MzNDE2ZGUwZDdiMjk3OWFjNzVhOTI1ZTQzOWM2YmIwN2JiMTkwNjY3NTdmIn19fQ=="), "&bWonder Skeleton Spawner", "Give this token to Mootilate or Yilmaz", "Who will redeem it for the correct spawner", "", "If Mootilate or Yilmaz is not available", "Place the token in a chest and DM", "Co-ords of the chest to Mootilate or Ylimaz");
	WONDER_BLAZE_SPAWNER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzc1YjkxMGQwMjVlMTNmMGUzMzA1Y2MzNDQ1NWY0YTc3NDJkYzUzNWY3Y2QwNmViYzY0YmRiZjc3MmNlOGE0NSJ9fX0="), "&bWonder Blaze Spawner", "Give this token to Mootilate or Yilmaz", "Who will redeem it for the correct spawner", "", "If Mootilate or Yilmaz is not available", "Place the token in a chest and DM", "Co-ords of the chest to Mootilate or Ylimaz");
	WONDER_WITHER_SKELETON_SPAWNER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTY0ZTFjM2UzMTVjOGQ4ZmZmYzM3OTg1YjY2ODFjNWJkMTZhNmY5N2ZmZDA3MTk5ZThhMDVlZmJlZjEwMzc5MyJ9fX0="), "&bWonder Wither Skeleton Spawner", "Give this token to Mootilate or Yilmaz", "Who will redeem it for the correct spawner", "", "If Mootilate or Yilmmaz is not available", "Place the token in a chest and DM", "Co-ords of the chest to Mootilate or Ylimaz");
	WONDER_VEX_SPAWNER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTY4ODZjYjk0ZjNiMzgyY2JjMzIxYWI0NjUzNTgxZjI4MmNhMDg0MTcyOGE0NzhlZTlkNDFhOGEzMjI0NzQzIn19fQ=="), "&bWonder Vex Spawner", "Give this token to Mootilate or Yilmaz", "Who will redeem it for the correct spawner", "", "If Mootilate or Yilmaz is not available", "Place the token in a chest and DM", "Co-ords of the chest to Mootilate or Ylimaz");
	WONDER_COW_SPAWNER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWFiOWRkODlkZmZlZWVjM2Q3NzdkYmQ4ZDRmYWIzMmY3NGY3NGZkNmFmZWE4YWVmOTEwOWIyYTg4NzU3YTkifX19"), "&bWonder Cow Spawner", "Give this token to Mootilate or Yilmaz", "Who will redeem it for the correct spawner", "", "If Mootilate or Yilmaz is not available", "Place the token in a chest and DM", "Co-ords of the chest to Mootilate or Ylimaz");
	WONDER_PIG_SPAWNER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjllNjM4MzU5MzczN2U2MDc4ODM0NGU3MDYxNDQ5MGNmZTZjZTljNTUxNjhmZjYxZDNjYTY4MzliNzYwIn19fQ=="), "&bWonder Pig Spawner", "Give this token to Mootilate or Yilmaz", "Who will redeem it for the correct spawner", "", "If Mootilate or Yilmaz is not available", "Place the token in a chest and DM", "Co-ords of the chest to Mootilate or Ylimaz");
	WONDER_SHEEP_SPAWNER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjMxZjljY2M2YjNlMzJlY2YxM2I4YTExYWMyOWNkMzNkMThjOTVmYzczZGI4YTY2YzVkNjU3Y2NiOGJlNzAifX19"), "&bWonder Sheep Spawner", "Give this token to Mootilate or Yilmaz", "Who will redeem it for the correct spawner", "", "If Mootilate or Yilmaz is not available", "Place the token in a chest and DM", "Co-ords of the chest to Mootilate or Ylimaz");
	WONDER_CHICKEN_SPAWNER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTRkODE2YTQ3NWIzZjMxM2I2MjBkNjViZjkzNTg5MzFiNDZhZjljOGY0ZTdjOWFlNjQ3MDg4ODBiZWE2YmUifX19"), "&bWonder Chicken Spawner", "Give this token to Mootilate or Yilmaz", "Who will redeem it for the correct spawner", "", "If Mootilate or Yilmaz is not available", "Place the token in a chest and DM", "Co-ords of the chest to Mootilate or Ylimaz");
	*/
}
