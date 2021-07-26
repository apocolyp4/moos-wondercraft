package tech.obliviondevelop.SF;

import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.core.categories.LockedCategory;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
//import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import io.github.thebusybiscuit.slimefun4.core.SlimefunRegistry;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import io.github.thebusybiscuit.slimefun4.implementation.items.RadioactiveItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.armor.SlimefunArmorPiece;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import io.github.thebusybiscuit.slimefun4.core.researching.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.skull.SkullItem;
import tech.obliviondevelop.SF.Lists.WonderItems;
import tech.obliviondevelop.SF.Lists.WonderMachines;
import tech.obliviondevelop.SF.Lists.WonderRecipeType;


public class Setup extends JavaPlugin implements SlimefunAddon, Listener
{
	private ItemMaterials item_materials = new ItemMaterials();
	private static Setup setup = null;
	public static Category OCEANOGRAPHY;
	public static Category LIFE;
	public static Category SCOTTISH;
	public static Category BUILDING;
	public static Category ADVANCED_MACHINES;
	public static Category HI_TECH_COMPONENTS;
	public static Category EXPORTS;
	public static Category SPAWNERS;
	public static Category SPACE;	
	public static Category wonderFood;
	
	public static Category MOOS_ELECTRICITY;
	public static Category MOOS_MISC;
	public static Category MOOS_RESOURCES;
	public static Category MOOS_ARMOR;
	public static Category MOOS_MAGIC;
	public static Category MOOS_FOOD;
	public static Category MOOS_MACHINES;
	public static Category MOOS_TOOLS;
	public static Category MOOS_WEAPONS;
	
	private Config config;
	private final SlimefunRegistry registry = new SlimefunRegistry();
	
	private static Oven oven;
	private HazmatTask hazmat_task;
	private static CoralCrusher coral_crusher ;
	private static DesalinationPlant desalination_plant;
	public ConsoleCommandSender console;
	
	private Set<PotionEffect> radiationEffects = null;
	
	
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
	  			custom_item = new CustomItem(item_materials.get_material(material_name), name, "", lore);
	  			break;
			case "SlimefunItems":
				custom_item = new CustomItem(item_materials.get_slimefun_item(material_name), name, "", lore);
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
		//setup = this;
		console = getServer().getConsoleSender();
		console.sendMessage("---------------------------------------------");
		console.sendMessage("Wondercraft Extra Slimefun Addon's Loading...");

        // Setup config.yml
        config = new Config(this);
       
		//**************LOAD CATEGORIES****************************
		
		//BUILDING = create_category("building", "&7Building", "open", "Material.BRICKS", 3, false);
		//SCOTTISH = create_category("scottish", "&bScottish", "&a> Click to open", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjgzOWFkNzM2MmRjNGUwOTk3NDcwMDk2MGZkMzdkN2VkMTcxM2U5NDExOWI4M2QwNGY1MjEzMjY4NjA2Mjc0YiJ9fX0=", 1, true);
		ADVANCED_MACHINES = create_category("advanced_machines", "&bAdvanced Machines", "&a> Click to open", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTg5OTI4YjU4YTg3ZWMxODRmMTY4NjkxNTQ0Yjc4YmM2MmM5YWY0ZGY3ZmFlYTIxOTQ0YmIzMjFlNWFmNjEyIn19fQ==", 4, true);
		HI_TECH_COMPONENTS = create_category("hitech_components", "&bHi Tech Components", "&a> Click to open", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjk0NTg4NmZjNDBkZmQ3ZGNiMDk1MzFmMTNhN2I1NWRhZTkyZGU1MjI1NGRhM2Q1MzYzNmVmOTNiZGM1NzEifX19", 1, true);
		EXPORTS = create_category("exports", "&bExports", "&a> Click to open", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDk2NDk2ODVjM2FkZmJkN2U2NWY5OTA1ZjcwNWZjNTY3NGJlNGM4ZWE1YTVkNmY1ZjcyZThlYmFkMTkyOSJ9fX0=", 1, true);
		//SPAWNERS = create_category("spawners", "&7Spawners", "open", "Material.SPAWNER", 1, false);
		//SPACE = create_category("space", "&4Space &rand &bTech", "open", "Material.END_PORTAL", 1, false);		
		OCEANOGRAPHY = create_category("oceanography", "&4Oceanography", "open", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODEyNmNhYzE2ZmQ4ZTQ3NTE2ZTg0NTIwY2QzOTgxYzQ1ZDcwOGY1NWQzNDU4NDk0ZDhmMDgxYzUwNWQ2ZDMwNCJ9fX0=", 1, false);
		wonderFood = create_category("wonder_food", "&4Wonder Food", "open", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWI4OWYwNzFjOThhNWZlYTM1OGQwOGE1ODZmY2E0YmM3MzliMGQ5ZGJhMGE1NmQxMDczYmM2ZDgzODcyZDI2YSJ9fX0=", 1, false);
		
		LIFE = create_category("life", "&4Life", "&4Create Life", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmZmMDU1NTE0MGI2NzZhYzY0NzU5YmRhZjNhZjNmZDVlNWFjZDZkNDJhNTNiOWQwZDI2ZDRhNjc3ZmJlYzExMSJ9fX0=", 1, false);
		
		MOOS_ELECTRICITY = create_category("moos_electricty", "&4Moos Electricty", "", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2E3Y2RhOTAwNGZjMTk3ZDY2YWZiYzJiMDAzYTViOWVmMTNjZjQ2MDBiMWZjNzQ5MDA2NzU5MGYwNDcxODFlIn19fQ==", 1, false);
		MOOS_MISC = create_category("moos_misc", "&4Moos Misc", "", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2FkOGNjOTgyNzg2ZmI0ZDQwYjBiNmU2NGE0MWYwZDk3MzZmOWMyNmFmZmI4OThmNGE3ZmFlYTg4Y2NmODk5NyJ9fX0=", 1, false);
		MOOS_RESOURCES = create_category("moos_resources", "&4Moos Resources", "", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTRhODQ1NTVmZjM3NzljMjBmMDhjNDgzNTkyNTViZjlmODFmMWMzNjczYzYzOTgyNjE4YzY0MDYyZTg5MmFlMiJ9fX0=", 1, false);
		//MOOS_MAGIC = create_category("moos_magic", "&4Moos Magic", "", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmZmMDU1NTE0MGI2NzZhYzY0NzU5YmRhZjNhZjNmZDVlNWFjZDZkNDJhNTNiOWQwZDI2ZDRhNjc3ZmJlYzExMSJ9fX0=", 1, false);
		MOOS_ARMOR = create_category("moos_armor", "&4Moos Armor", "", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGU4NmUwYjI5M2RlZjVjMjU3MzM4NDNiYmU2Nzc5Y2NkYTc3NTkwZTEzMjI0YTJkYjYyNzZhZDgyM2VkNTVlIn19fQ==", 1, false);
		MOOS_FOOD = create_category("moos_food", "&4Moos Food", "", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGUxYjU4NWFiZGQ2NDc3ZjU5NGJjZmNlZjg0NzcxYWEyZDQyYWI4NWRkNGMyMmE0Y2M0MGMyNzM2MDUwOGM4In19fQ==", 1, false);
		MOOS_MACHINES = create_category("moos_machines", "&4Moos Machines", "", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjE2NzcyNmI2YTIwYTFmMTc4NGE5ZjM2OGM4NTI2NWZiOTQ0ODRlNGI1YmIwYTFlOTcwNjFiZWE4MzU2OTUifX19", 1, false);
		MOOS_TOOLS = create_category("moos_tools", "&4Moos Tools", "", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTZiMWQ0OTQ3NzYzOTE3ODE0YWUxNjMyYjgyMDY5NjA5ODkyNzg5NWFhYWYxMjRjZDI5ZWIzNTg1NmFhYTViOSJ9fX0=", 1, false);
		//MOOS_WEAPONS = create_category("moos_weapons", "&4Moos Weapons", "", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmZmMDU1NTE0MGI2NzZhYzY0NzU5YmRhZjNhZjNmZDVlNWFjZDZkNDJhNTNiOWQwZDI2ZDRhNjc3ZmJlYzExMSJ9fX0=", 1, false);
		
		if (!new File("plugins/WondercraftSFAddon/schematics").exists()) new File("plugins/WondercraftSFAddon/schematics").mkdirs();
		if (!new File(getDataFolder() + "/schematics/VoidResearchStation.schem").exists()) saveResource("schematics/VoidResearchStation.schem", false);
		
				
		load_machines_1();
		
		Compactor compactor = new Compactor(MOOS_MACHINES); 
		compactor.register(this);
		
		IndustrialGrinder industrial_grinder = new IndustrialGrinder(ADVANCED_MACHINES);
		industrial_grinder.register(this);
		
		ItemStack[] item_recipe;
		SlimefunItemStack slimefun_stack;
			
		oven = new Oven(this.setup, MOOS_MACHINES);
		oven.register(this);
		
		getServer().getScheduler().runTaskTimerAsynchronously(this, new SolarTask(), 0L, 10 * 20L);
		
		coral_crusher = new CoralCrusher(this, MOOS_MACHINES);
		coral_crusher.register(this);
		
		WonderMachine wonder_machine = new WonderMachine(ADVANCED_MACHINES);
		wonder_machine.register(this);
		
		Fryer fryer = new Fryer(ADVANCED_MACHINES);
		fryer.register(this);
			
		Purifier purifier = new Purifier(ADVANCED_MACHINES);
		purifier.register(this);
		
		Recycler recycler = new Recycler(MOOS_MACHINES);
		recycler.register(this);
		
		DesalinationPlant desalination_plant = new DesalinationPlant(ADVANCED_MACHINES);	
		desalination_plant.register(this);
		
		ConcreteMixer concrete_mixer = new ConcreteMixer(ADVANCED_MACHINES);	
		concrete_mixer.register(this);
	
		ElectricPurifier electric_purifier = new ElectricPurifier(ADVANCED_MACHINES, purifier);
		electric_purifier.register(this);
		
		ElectricPurifierII electric_purifierII = new ElectricPurifierII(ADVANCED_MACHINES);
		electric_purifierII.register(this);
		
		SmallHadronCollider small_hadron_collider = new SmallHadronCollider(ADVANCED_MACHINES);
		small_hadron_collider.register(this);
		
		CarbonCollector carbon_collector = new CarbonCollector(ADVANCED_MACHINES);
		carbon_collector.register(this);
		
		ElectricRecycler electric_recycler = new ElectricRecycler(ADVANCED_MACHINES);
		electric_recycler.register(this);
		
		ElectricSawMill electric_saw_mill = new ElectricSawMill(MOOS_ELECTRICITY);
		electric_saw_mill.register(this);	
	
		WonderFurnace wonder_furnace = new WonderFurnace(MOOS_ELECTRICITY);
		wonder_furnace.register(this);	
		
		DeepFreeze deep_freeze = new DeepFreeze(ADVANCED_MACHINES);
		deep_freeze.register(this);

		//Cannary cannary = new Cannary(ADVANCED_MACHINES);
		//cannary.register(this);
		
		NetherMaker nether_maker = new NetherMaker(ADVANCED_MACHINES);
		nether_maker.register(this);
		
		ReAnimator reanimator = new ReAnimator(ADVANCED_MACHINES);
		reanimator.register(this);

		Extruder extruder = new Extruder(MOOS_ELECTRICITY);
		extruder.register(this);	
		
		Still still = new Still(this, MOOS_MACHINES);
		still.register(this);	
		
		ElectricHydropress electric_hydropress = new ElectricHydropress(MOOS_ELECTRICITY);
		electric_hydropress.register(this);		
		
		SlimefunItemStack pastry = new SlimefunItemStack("PASTRY", new CustomItem(Material.CLAY_BALL), "&rPastry", "", "&7&oIngredient &b&o");	
		//SlimefunItemStack icingSugar = new SlimefunItemStack("ICING_SUGAR", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDdmMzhlYTI4Njk1MmEzMzc4OTVkNTE1NDhmMWIxYzE5NGU5ZGYyMjE4OTg5ZjcyZDhkMzI0ZmI5YTMyOSJ9fX0=", "&rIcing Sugar", "", "&7&oIngredient &b&o");
		SlimefunItemStack chocolateBar = new SlimefunItemStack("CHOCOLATE_BAR", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODE5Zjk0OGQxNzcxOGFkYWNlNWRkNmUwNTBjNTg2MjI5NjUzZmVmNjQ1ZDcxMTNhYjk0ZDE3YjYzOWNjNDY2In19fQ==", "&rChocolate Bar", "", "&7&oIngredient &b&o");
		SlimefunItemStack berryJam = new SlimefunItemStack("BERRY_JAM", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzBiOGI1ODg5ZWUxYzYzODhkYzZjMmM1ZGJkNzBiNjk4NGFlZmU1NDMxOWEwOTVlNjRkYjc2MzgwOTdiODIxIn19fQ==", "&rBerry Jam", "", "&7&oRestores &b&o" + "1" + " &7&oHunger");

		item_recipe = new ItemStack[] {null, null, null, null, new ItemStack(Material.SUGAR), null, null, null, null};
		SlimefunItemStack icingSugar = new SlimefunItemStack("ICING_SUGAR", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDdmMzhlYTI4Njk1MmEzMzc4OTVkNTE1NDhmMWIxYzE5NGU5ZGYyMjE4OTg5ZjcyZDhkMzI0ZmI5YTMyOSJ9fX0=", "&rIcing Sugar", "", "&7&oIngredient &b&o");
		new SlimefunItem(MOOS_MISC, icingSugar, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);	
		
		
		SlimefunItemStack toast_stack = new SlimefunItemStack("TOAST", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTE5OTdkYTY0MDQzYjI4NDgyMjExNTY0M2E2NTRmZGM0ZThhNzIyNjY2NGI0OGE0ZTFkYmI1NTdiNWMwZmUxNCJ9fX0=", "&rToast", "", "&7&oRestores &b&o" + "0.5" + " &7&oHunger");
		WonderItems.toast = new CustomFood(wonderFood,
				toast_stack,
				 oven.asRecipeType()
				,new ItemStack[] {new ItemStack(Material.BREAD), SlimefunItems.BUTTER, null, null, null, null, null, null, null}, 1);
		WonderItems.toast.register(this);
		
		WonderItems.donut = new CustomFood(wonderFood,
				new SlimefunItemStack("DONUT", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3YzliODJiMTg2NjU2ZTlmNjM2M2EyYTFjNmE0YjViOTNjZmE5ZWY0ZGFkNmYxNmI5NGViYjVlMzYyNjc4In19fQ==", "&rDonut", "", "&7&oRestores &b&o" + "1.5" + " &7&oHunger"),
				 fryer.asRecipeType()
				,new ItemStack[] {WonderItems.DOUGH, null, null, null, null, null, null, null, null},
				3);
		WonderItems.donut.register(this);
		
		WonderItems.toast_jam = new CustomFood(wonderFood,
				new SlimefunItemStack("TOAST_JAM", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTlkY2YxNGVhNzI0OWZlYTZlNzIwMmVlNTg4MjU0YTZjMzJlMzM0NWYxZjNkYTNhYTI2NDlhZmFhNTgyIn19fQ==", "&rToast & Jam", "", "&7&oRestores &b&o" + "2.5" + " &7&oHunger"),
				 oven.asRecipeType()
				,new ItemStack[] {toast_stack, berryJam, null, null, null, null, null, null, null},
				5);
		WonderItems.toast_jam.register(this);
		

		WonderItems.toast_honey = new CustomFood(wonderFood,
				new SlimefunItemStack("TOAST_HONEY", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjg5MjJjODQyM2QyODExODQwZjU2Yjc1YzQ1M2FlM2UyYmIzN2ZhMWQ3ZDIzNDhhNWVmOWI0NDU2YzM1ODIxYyJ9fX0=", "&rToast & Honey", "", "&7&oRestores &b&o" + "2.5" + " &7&oHunger"),
				 oven.asRecipeType()
				,new ItemStack[] {toast_stack, new ItemStack(Material.HONEY_BOTTLE), null, null, null, null, null, null, null},
				5);
		WonderItems.toast_honey.register(this);
						
		WonderItems.burger = new CustomFood(wonderFood, new SlimefunItemStack("BURGER", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWI4OWYwNzFjOThhNWZlYTM1OGQwOGE1ODZmY2E0YmM3MzliMGQ5ZGJhMGE1NmQxMDczYmM2ZDgzODcyZDI2YSJ9fX0=", "&rBurger", "", "&7&oRestores &b&o" + "3" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {null, new ItemStack(Material.BREAD), null, SlimefunItems.CHEESE, new ItemStack(Material.COOKED_BEEF), SlimefunItems.MONSTER_JERKY, null, new ItemStack(Material.BREAD), null},
				3);
		
		WonderItems.burger.register(this);
		
		WonderItems.berry_jam = new CustomFood(wonderFood, new SlimefunItemStack("BERRY_JAM", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzBiOGI1ODg5ZWUxYzYzODhkYzZjMmM1ZGJkNzBiNjk4NGFlZmU1NDMxOWEwOTVlNjRkYjc2MzgwOTdiODIxIn19fQ==", "&rBerry Jam", "", "&7&oRestores &b&o" + "1" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {new ItemStack(Material.SUGAR), new ItemStack(Material.SUGAR), new ItemStack(Material.SUGAR), new ItemStack(Material.SUGAR), new ItemStack(Material.SWEET_BERRIES), new ItemStack(Material.SUGAR), new ItemStack(Material.SUGAR), new ItemStack(Material.SUGAR), new ItemStack(Material.SUGAR)},
				1);
		WonderItems.berry_jam.register(this);
		
		new Pastry(wonderFood, new SlimefunItemStack("PASTRY", new CustomItem(item_materials.get_material("CLAY_BALL")), "&rPastry", "", "&7&oIngredient &b&o"),RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, null, null, SlimefunItems.WHEAT_FLOUR, new ItemStack(Material.SUGAR), SlimefunItems.BUTTER, new ItemStack(Material.EGG), null, null})
		.register(this);
		
		WonderItems.berry_pie = new CustomFood(wonderFood, new SlimefunItemStack("BERRY_PIE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDUzYzFlODdlNTM3ZjFhYjI3NzRkZGFmYjgzNDM5YjMzNmY0YTc3N2I0N2FkODJiY2IzMGQ1ZmNiZGY5YmMifX19", "&rBerry Pie", "", "&7&oRestores &b&o" + "5" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {null, icingSugar, null, berryJam, berryJam, berryJam, pastry, pastry, pastry},
				5);
		WonderItems.berry_pie.register(this);
		
		WonderItems.waffles = new CustomFood(wonderFood, new SlimefunItemStack("WAFFLES", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0=", "&rWaffles", "", "&7&oRestores &b&o" + "6.0" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {SlimefunItems.WHEAT_FLOUR, new ItemStack(Material.EGG), new ItemStack(Material.SUGAR), SlimefunItems.BUTTER, null, null, null, null, null},
				3);
		WonderItems.waffles.register(this);
		
		
		WonderItems.chocolate_bar = new CustomFood(wonderFood, new SlimefunItemStack("CHOCOLATE_BAR", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODE5Zjk0OGQxNzcxOGFkYWNlNWRkNmUwNTBjNTg2MjI5NjUzZmVmNjQ1ZDcxMTNhYjk0ZDE3YjYzOWNjNDY2In19fQ==", "&rChocolate Bar", "", "&7&oRestores &b&o" + "1.5" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {new ItemStack(Material.COCOA_BEANS), SlimefunItems.HEAVY_CREAM, null, null, null, null, null, null, null},
				3);
		WonderItems.chocolate_bar.register(this);
		
		WonderItems.hot_chocolate = new CustomFood(wonderFood, new SlimefunItemStack("HOT_CHOCOLATE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDExNTExYmRkNTViY2I4MjgwM2M4MDM5ZjFjMTU1ZmQ0MzA2MjYzNmUyM2Q0ZDQ2YzRkNzYxYzA0ZDIyYzIifX19", "&6Hot Chocolate", "", "&7&oRestores &b&o" + "4.0" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {chocolateBar, SlimefunItems.HEAVY_CREAM, null, null, null, null, null, null, null},
				8);
				WonderItems.hot_chocolate.register(this);
		
		WonderItems.chocolate_cake = new CustomFood(wonderFood, new SlimefunItemStack("CHOCOLATE_CAKE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTExOWZjYTRmMjhhNzU1ZDM3ZmJlNWRjZjZkOGMzZWY1MGZlMzk0YzFhNzg1MGJjN2UyYjcxZWU3ODMwM2M0YyJ9fX0=", "&rChocolate Cake", "", "&7&oRestores &b&o" + "8.5" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {chocolateBar, new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, new ItemStack(Material.EGG), null, null, null, null},
				17);
		WonderItems.chocolate_cake.register(this);
		
		WonderItems.cream_cookie = new CustomFood(wonderFood, new SlimefunItemStack("CREAM_COOKIE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGZkNzFlMjBmYzUwYWJmMGRlMmVmN2RlY2ZjMDFjZTI3YWQ1MTk1NTc1OWUwNzJjZWFhYjk2MzU1ZjU5NGYwIn19fQ==", "&rCream Cookie", "", "&7&oRestores &b&o" + "6.0" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {chocolateBar, new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, null, null, null, null},
				12);
		WonderItems.cream_cookie.register(this);
		
		WonderItems.pumpkin_muffin = new CustomFood(wonderFood, new SlimefunItemStack("PUMPKIN_MUFFIN", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19", "&rPumpkin Muffin", "", "&7&oRestores &b&o" + "6.5" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {new ItemStack(Material.PUMPKIN), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
				13);
		WonderItems.pumpkin_muffin.register(this);
		
		WonderItems.hot_dog = new CustomFood(wonderFood, new SlimefunItemStack("HOT_DOG", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzNmMmQ3ZDdhOGIxYjk2OTE0Mjg4MWViNWE4N2U3MzdiNWY3NWZiODA4YjlhMTU3YWRkZGIyYzZhZWMzODIifX19", "&rHot Dog", "", "&7&oRestores &b&o" + "5.0" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {null, null, null, null, new ItemStack(Material.COOKED_PORKCHOP), null, null, new ItemStack(Material.BREAD), null},
				10);
		WonderItems.hot_dog.register(this);
			
		WonderItems.scrambled_egg = new CustomFood(wonderFood, new SlimefunItemStack("SCRAMBLED_EGG", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjFlZTYzMjVjZmNjMDVhNjBlNjFiNjQwNmI4YjdkZTE0NmFjOTNhOTA0YmI0YzRlZDcyNWZjZTc3ZTUzM2UwOCJ9fX0=", "&rScrambled Eggs", "", "&7&oRestores &b&o" + "5.0" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {null, null, null, new ItemStack(Material.MILK_BUCKET), new ItemStack(Material.EGG), SlimefunItems.BUTTER, null, null, null},
				10);
		WonderItems.scrambled_egg.register(this);
		
		WonderItems.custard = new CustomFood(wonderFood, new SlimefunItemStack("CUSTARD", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWUxNjJkYWEzMzFjNmNmOGQ2MTM4YjgzZmI4NDhiZDM1Yzc4ZDJmNWYyMTk5ZGU2ZTllMThhNDM4ODI2NWI3In19fQ==", "&rCustard", "", "&7&oRestores &b&o" + "1.0" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {null, null, null, new ItemStack(Material.MILK_BUCKET), new ItemStack(Material.EGG), new ItemStack(Material.SUGAR), null, null, null},
				2);
		WonderItems.custard.register(this);
		
		/*
		Juice apple_schnapps = new Juice(wonderFood, new SlimefunItemStack("BERRY_SCHNAPPS", new CustomPotion("&cBerry Schnapps", Color.PURPLE, new PotionEffect(PotionEffectType.CONFUSION, 1200, 11), "&rBerry Schnapps", "&cGets you drunk", "", "")), still.asRecipeType(),
		        new ItemStack[] {null, null, null, new ItemStack(Material.SWEET_BERRIES), WonderItems.ALCOHOL, null, null, null, null});
		apple_schnapps.register(this);
		
		Juice berry_schnapps = new Juice(wonderFood, new SlimefunItemStack("APPLE_SCHNAPPS", new CustomPotion("&cApple Schnapps", Color.GREEN, new PotionEffect(PotionEffectType.CONFUSION, 1200, 11), "&rApple Schnapps", "&cGets you drunk", "", "")), still.asRecipeType(),
		        new ItemStack[] {null, null, null, new ItemStack(Material.APPLE), WonderItems.ALCOHOL, null, null, null, null});
		berry_schnapps.register(this);
		
		Juice miduri = new Juice(wonderFood, new SlimefunItemStack("MIDURI", new CustomPotion("&cMiduri", Color.GREEN, new PotionEffect(PotionEffectType.CONFUSION, 1200, 11), "&rMiduri", "&cGets you drunk", "", "")), still.asRecipeType(),
		        new ItemStack[] {null, null, null, new ItemStack(Material.MELON_SLICE), WonderItems.ALCOHOL, new ItemStack(Material.MELON_SLICE), null, null, null});
		miduri.register(this);
		
		Juice zombie_cocktail = new Juice(wonderFood, new SlimefunItemStack("ZOMBIE_COCKTAIL", new CustomPotion("&cZombie Cocktail", Color.GREEN, new PotionEffect(PotionEffectType.CONFUSION, 1200, 11), "&rZombie Cocktail", "&cGets you drunk", "", "")), still.asRecipeType(),
		        new ItemStack[] {null, null, null, new ItemStack(Material.ROTTEN_FLESH), WonderItems.ALCOHOL, new ItemStack(Material.SUGAR), null, null, null});
		zombie_cocktail.register(this);		
		*/
		
		item_recipe = new ItemStack[] {null, null, null, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ALUMINUM_INGOT, null, null, null};
		slimefun_stack = new SlimefunItemStack("PIE_TIN", WonderItems.PIE_TIN);
		new SlimefunItem(wonderFood, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		
		WonderItems.ORANGE = new CustomFood(wonderFood, new SlimefunItemStack("ORANGE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWZkMGUzMzBhNjg4ZDhiYjk1MTliZWZlMWJmYzM0MzM3YjM3MWFjNzUxNTAyMTZmZGQwMzk1OWViN2I0NCJ9fX0=", "&rOrange", "", "&7&oRestores &b&o" + "1" + " &7&oHunger"),
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, null, null, new ItemStack(Material.APPLE), new ItemStack(Material.ORANGE_DYE), null, null, null, null},
				1);
		WonderItems.ORANGE.register(this);
		
		
		WonderItems.BREADCRUMBS = new CustomFood(wonderFood, new SlimefunItemStack("BREADCRUMBS", new CustomItem(item_materials.get_material("PUMPKIN_SEEDS")), "&rBreadcrumbs", "", "&7&oRestores &b&o" + "5" + " &7&oHunger"),
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, null, null, null, new ItemStack(Material.BREAD), null, null, null, null},
				1);
		WonderItems.BREADCRUMBS.register(this);	
		
		WonderItems.LEMON = new CustomFood(wonderFood, new SlimefunItemStack("LEMON", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDM3OGI1ODJkMTljY2M1NWIwMjNlYjgyZWRhMjcxYmFjNDc0NGZhMjAwNmNmNWUxOTAyNDZlMmI0ZDVkIn19fQ==", "&rLemon", "", "&7&oRestores &b&o" + "1" + " &7&oHunger"),
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, null, null, new ItemStack(Material.APPLE), new ItemStack(Material.YELLOW_DYE), null, null, null, null},
				1);
		WonderItems.LEMON.register(this);
		
		WonderItems.PLUM = new CustomFood(wonderFood, new SlimefunItemStack("PLUM", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmY3NWZlY2IzNmFiODc0OGM5NDdjMjEzOWEzZDIwZmYyMjMxOTk3ODMwMWEyN2E2ODgzNjQwM2NjNjlhMzNmNiJ9fX0=", "&rPlum", "", "&7&oRestores &b&o" + "1" + " &7&oHunger"),
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, null, null, new ItemStack(Material.APPLE), new ItemStack(Material.PURPLE_DYE), null, null, null, null},
				1);
		WonderItems.PLUM.register(this);		
		
		WonderItems.LIME = new CustomFood(wonderFood, new SlimefunItemStack("LIME", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2YyNGI3MTM1Nzg5ZmU3OTlkZjM0NTk0ZDY4MDVmNTExMmJlZTYyMzI2MDViYTZkZTIxNTE4NmFkOTQifX19", "&rLime", "", "&7&oRestores &b&o" + "1" + " &7&oHunger"),
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, null, null, new ItemStack(Material.APPLE), new ItemStack(Material.GREEN_DYE), null, null, null, null},
				1);
		WonderItems.LIME.register(this);		
		
		WonderItems.CHERRY = new CustomFood(wonderFood, new SlimefunItemStack("CHERRY", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzUyMDc2NmI4N2QyNDYzYzM0MTczZmZjZDU3OGIwZTY3ZDE2M2QzN2EyZDdjMmU3NzkxNWNkOTExNDRkNDBkMSJ9fX0=", "&rCherry", "", "&7&oRestores &b&o" + "1" + " &7&oHunger"),
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, null, null, new ItemStack(Material.APPLE), new ItemStack(Material.RED_DYE), null, null, null, null},
				1);
		WonderItems.CHERRY.register(this);
		
		WonderItems.BLUEBERRY = new CustomFood(wonderFood, new SlimefunItemStack("BLUEBERRY", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmVlN2NhMmI0ZDRhMDg4ZmQzMjYyZjc2NGYzZWUwZDEyZmQ2MjViYjYxOTY0ZmY0YTkxNzIyMTRlNzZkNGI5ZSJ9fX0=", "&rBlueberry", "", "&7&oRestores &b&o" + "1" + " &7&oHunger"),
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, null, null, new ItemStack(Material.APPLE), new ItemStack(Material.BLUE_DYE), null, null, null, null},
				1);
		WonderItems.BLUEBERRY.register(this);		
		
		
		WonderItems.ORANGE_JAM = new CustomFood(wonderFood, new SlimefunItemStack("ORANGE_JAM", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDM2MjllODZhZmRiNTgyZGI4MjM1MzI3NDQ5NTczMTNmYzI2YjYyMTk0ODI5YzhkM2Y4MTRjODAyODk2YjIifX19", "&rOrange Jam", "", "&7&oRestores &b&o" + "2" + " &7&oHunger"),
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, null, null, new ItemStack(Material.SUGAR), WonderItems.ORANGE.getItem(), new ItemStack(Material.SUGAR), null, null, null},
				2);
		WonderItems.ORANGE_JAM.register(this);			
		
		WonderItems.LEMON_JAM = new CustomFood(wonderFood, new SlimefunItemStack("LEMON_JAM", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDlmNDBlZjc3ZmYzMzlhN2MzYWNhOGVlYWRmMTVlNjMyYmU2YzI1NzRlNmNiYTJlNzFjMmNjNzBkYzNiYmU2NCJ9fX0=", "&rLemon Jam", "", "&7&oRestores &b&o" + "2" + " &7&oHunger"),
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, null, null, new ItemStack(Material.SUGAR), WonderItems.LEMON.getItem(), new ItemStack(Material.SUGAR), null, null, null},
				2);
		WonderItems.LEMON_JAM.register(this);	
		
		
		WonderItems.LIME_JAM = new CustomFood(wonderFood, new SlimefunItemStack("LIME_JAM", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzNmYTkxZGUzMWUyZGRkYTI5Y2E0MTljMDhiMzRhNWEyZDZiNGIxYTFiNTliMjM2Y2RjMTRjYjEzMWRjYjgwNyJ9fX0=", "&rLime Jam", "", "&7&oRestores &b&o" + "2" + " &7&oHunger"),
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, null, null, new ItemStack(Material.SUGAR), WonderItems.LIME.getItem(), new ItemStack(Material.SUGAR), null, null, null},
				2);
		WonderItems.LIME_JAM.register(this);		
		
		
		WonderItems.PLUM_JAM = new CustomFood(wonderFood, new SlimefunItemStack("PLUM_JAM", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzQ5MmNhOTQwNzkxMzZkMjUyNTcwM2QzNzVjMjU1N2VhYzIwMWVlN2RkMzljZTExYzY0YTljMzgxNDdlY2M0ZCJ9fX0=", "&Plum Jam", "", "&7&oRestores &b&o" + "2" + " &7&oHunger"),
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, null, null, new ItemStack(Material.SUGAR), WonderItems.PLUM.getItem(), new ItemStack(Material.SUGAR), null, null, null},
				2);
		WonderItems.PLUM_JAM.register(this);		
		
		
		WonderItems.CHERRY_JAM = new CustomFood(wonderFood, new SlimefunItemStack("CHERRY_JAM", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjNkMzU2Yjg2MTE4ZmY4YzQ4YzExOWVmM2QyYWM5MGMxYWQxMzkyODkzMDIyZjgwYjlkYzE2OGMzNmRiMmE1NiJ9fX0=", "&rCherry Jam", "", "&7&oRestores &b&o" + "2" + " &7&oHunger"),
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, null, null, new ItemStack(Material.SUGAR), WonderItems.CHERRY.getItem(), new ItemStack(Material.SUGAR), null, null, null},
				2);
		WonderItems.CHERRY_JAM.register(this);		
		
		
		WonderItems.BLUEBERRY_JAM = new CustomFood(wonderFood, new SlimefunItemStack("BLUEBERRY_JAM", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWRjOWZmMTg0YWU3NjdkM2NiZmQ5YzNhYTJjN2U4OGIxMGY5YjU5MTI5N2ZmNjc2ZGE2MzlmYjQ0NDYyMzhjOCJ9fX0=", "&rBlueberry Jam", "", "&7&oRestores &b&o" + "2" + " &7&oHunger"),
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, null, null, new ItemStack(Material.SUGAR), WonderItems.BLUEBERRY.getItem(), new ItemStack(Material.SUGAR), null, null, null},
				2);
		WonderItems.BLUEBERRY_JAM.register(this);		
		
		
		WonderItems.APPLE_JAM = new CustomFood(wonderFood, new SlimefunItemStack("APPLE_JAM", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDJmNTdjOWVlZDlmOTBiN2MzM2IwYTQ0NzU2ODE1MGNiN2I1ZWM2MmFmZGRmMjgwYjRmOTgxZmZkNDgwYTc2NiJ9fX0=", "&rApple Jam", "", "&7&oRestores &b&o" + "2" + " &7&oHunger"),
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, null, null, new ItemStack(Material.SUGAR), new ItemStack(Material.APPLE), new ItemStack(Material.SUGAR), null, null, null},
				2);
		WonderItems.APPLE_JAM.register(this);	
		
		
		WonderItems.PASTA = new CustomFood(wonderFood, new SlimefunItemStack("PASTA", new CustomItem(item_materials.get_material("MAP")), "&rPasta", "", "&7&oRestores &b&o" + "5" + " &7&oHunger"),
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, null, null, SlimefunItems.WHEAT_FLOUR, new ItemStack(Material.EGG), SlimefunItems.WHEAT_FLOUR, null, null, null},
				5);
		WonderItems.PASTA.register(this);
		
		
		WonderItems.PARACETMOL = new Paracetamol(wonderFood, new SlimefunItemStack("PARACETMOL", new CustomItem(item_materials.get_material("IRON_NUGGET")), "&rParacetmol", "", "&7&oRestores &b&o" + "5" + " &7&oHealth"),
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, null, null, new ItemStack(Material.SUGAR), WonderItems.ORANGE.getItem(), new ItemStack(Material.SUGAR), null, null, null},
				null,
				5);
		WonderItems.PARACETMOL.register(this);	
		

		
		WonderItems.CREEPER_CAKE = new CustomFood(wonderFood, new SlimefunItemStack("CREEPER_CAKE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY1NDk1MWJhOTI5MGQ4Yjg0MWZjNDJjNmNjZDI4NjZiZDM0Njk2MTQ1ZTk1NWM0NjY5MTU2MDExY2NjNTkyMiJ9fX0=", "&rCreeper Cake", "", "&7&oRestores &b&o" + "5" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] { new ItemStack(Material.MILK_BUCKET), new ItemStack(Material.MILK_BUCKET), new ItemStack(Material.MILK_BUCKET), new ItemStack(Material.GUNPOWDER),  new ItemStack(Material.EGG), new ItemStack(Material.GUNPOWDER), SlimefunItems.WHEAT_FLOUR, new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR},
				5);
		WonderItems.CREEPER_CAKE.register(this);		
		
		
		WonderItems.ROAST_CHICKEN = new CustomFood(wonderFood, new SlimefunItemStack("ROAST_CHICKEN", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmI3ZjhhZTM5MGVjNTliNzE3ZDU2OTE0YjQ1Yjk2NjRmYzRjYjhhMjhlZTI3M2JlMTQzZThmZGNmYzYzMjcwNiJ9fX0=", "&rRoast Chicken", "", "&7&oRestores &b&o" + "5" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {WonderItems.MACE, SlimefunItems.BUTTER, new ItemStack(Material.NETHER_SPROUTS), null, new ItemStack(Material.CHICKEN), null, new ItemStack(Material.POTATO), new ItemStack(Material.CARROT), new ItemStack(Material.BEETROOT)},
				5);
		WonderItems.ROAST_CHICKEN.register(this);			
		
		
		WonderItems.FRIED_CHICKEN = new CustomFood(wonderFood, new SlimefunItemStack("FRIED_CHICKEN", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjhmYjA1MDJhM2FhNWY4YmQzMmE1ZWE1ZTUxOWMzZGQzNTMyMzQxNzBkZmVmOTU5ZWU4YWRiOTQ4N2ZlYSJ9fX0=", "&rFried Chicken", "", "&7&oRestores &b&o" + "5" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {WonderItems.BREADCRUMBS.getItem(), WonderItems.BREADCRUMBS.getItem(), WonderItems.BREADCRUMBS.getItem(), WonderItems.BREADCRUMBS.getItem(), new ItemStack(Material.CHICKEN), WonderItems.BREADCRUMBS.getItem(), SlimefunItems.BUTTER, SlimefunItems.BUTTER, SlimefunItems.BUTTER},
				5);
		WonderItems.FRIED_CHICKEN.register(this);
		
		
		WonderItems.APPLE_PIE = new CustomFood(wonderFood, new SlimefunItemStack("APPLE_PIE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjMwZWUyMzcyOTBkYmExMzNkYTAxYWY1Zjg4Yjg3Y2NlM2I5NGJjNDBjNTA4OWU5ODg4NTJkNDc4ZmExNTRhMiJ9fX0=", "&rApple Pie", "", "&7&oRestores &b&o" + "5" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {new ItemStack(Material.APPLE), new ItemStack(Material.APPLE), new ItemStack(Material.APPLE), null, pastry, null, null, WonderItems.PIE_TIN, null},
				5);
		WonderItems.APPLE_PIE.register(this);		
		
		WonderItems.LIME_PIE = new CustomFood(wonderFood, new SlimefunItemStack("LIME_PIE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTU0MTgxOWU4Zjc5NDVmYWY3YThmN2NjMTc1ZGNjNjRkNGUzOTc3MzE3ZjAxMjc0ZTNmZGYxOGE1NTE5NDQyMSJ9fX0=", "&rLime Pie", "", "&7&oRestores &b&o" + "5" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {WonderItems.LIME.getItem(), WonderItems.LIME.getItem(), WonderItems.LIME.getItem(), null, pastry, null, null, WonderItems.PIE_TIN, null},
				5);
		WonderItems.LIME_PIE.register(this);
		
		WonderItems.CHERRY_PIE = new CustomFood(wonderFood, new SlimefunItemStack("CHERRY_PIE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDUzYzFlODdlNTM3ZjFhYjI3NzRkZGFmYjgzNDM5YjMzNmY0YTc3N2I0N2FkODJiY2IzMGQ1ZmNiZGY5YmMifX19", "&rCherry Pie", "", "&7&oRestores &b&o" + "5" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {WonderItems.CHERRY.getItem(), WonderItems.CHERRY.getItem(), WonderItems.CHERRY.getItem(), null, pastry, null, null, WonderItems.PIE_TIN, null},
				5);
		WonderItems.CHERRY_PIE.register(this);	
	
		WonderItems.LEMON_PIE = new CustomFood(wonderFood, new SlimefunItemStack("LEMON_PIE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGQxNDBiNDI4YmFiMzYxMDM1NWM5NmNkNzQ2ZTU1NjA2ODRiOTM5ZWExYTFjNTkxMjg3MjlmZDNjNDZiYTgxMSJ9fX0=", "&rLemon Pie", "", "&7&oRestores &b&o" + "5" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {WonderItems.LEMON.getItem(), WonderItems.LEMON.getItem(), WonderItems.LEMON.getItem(), null, pastry, null, null, WonderItems.PIE_TIN, null},
				5);
		WonderItems.LEMON_PIE.register(this);	
		
		WonderItems.PLUM_PIE = new CustomFood(wonderFood, new SlimefunItemStack("PLUM_PIE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQxOGM2YjBhMjlmYzFmZTc5MWM4OTc3NGQ4MjhmZjYzZDJhOWZhNmM4MzM3M2VmM2FhNDdiZjNlYjc5In19fQ==", "&rPlum Pie", "", "&7&oRestores &b&o" + "5" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {WonderItems.PLUM.getItem(), WonderItems.PLUM.getItem(), WonderItems.PLUM.getItem(), null, pastry, null, null, WonderItems.PIE_TIN, null},
				5);
		WonderItems.PLUM_PIE.register(this);	
		
		WonderItems.SUSHI = new CustomFood(wonderFood, new SlimefunItemStack("SUSHI", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTUzNDdkYWRmNjgxOTlmYTdhMWI2NmYwNDgxYWQ4ZTlkYWVlMTUxMDg2NWFkZDZmMzNkMTVmYjM3OGQxM2U5MSJ9fX0=", "&rSushi", "", "&7&oRestores &b&o" + "5" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {null, new ItemStack(Material.NETHER_SPROUTS), null, new ItemStack(Material.TROPICAL_FISH), new ItemStack(Material.SALMON), new ItemStack(Material.PUFFERFISH), new ItemStack(Material.KELP), new ItemStack(Material.KELP), new ItemStack(Material.KELP)},
				5);
		WonderItems.SUSHI.register(this);
		
		
		WonderItems.TIRAMISU = new CustomFood(wonderFood, new SlimefunItemStack("TIRAMISU", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ==", "&rTiramisu", "", "&7&oRestores &b&o" + "5" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {null, WonderItems.chocolate_bar.getItem(), null, new ItemStack(Material.EGG), SlimefunItems.HEAVY_CREAM, new ItemStack(Material.MILK_BUCKET), null, new ItemStack(Material.BREAD), null},
				5);
		WonderItems.TIRAMISU.register(this);		

		WonderItems.HOT_LEMONADE = new CustomFood(wonderFood, new SlimefunItemStack("HOT_LEMONADE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjJhMjFmNGRmZjUxOGViNWUxNDYwNDk3ODEyYjI0MGQxNTliNjM0ZDA0ZTI3NTQ1YmZhM2VhN2ZkM2RkNDgifX19", "&rHot Lemonade", "", "&7&oRestores &b&o" + "5" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {null, new ItemStack(Material.SUGAR), null, null, WonderItems.LEMON.getItem(), null, null, new ItemStack(Material.GLASS), null},
				5);
		WonderItems.HOT_LEMONADE.register(this);	
		
		WonderItems.LASAGNA = new CustomFood(wonderFood, new SlimefunItemStack("LASAGNA", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNThkNTgzZDNmNmExMjQ3NjM1OWVmODI4OGI2MGJhMzU1ZTMyYTNhNGQ5N2JjODUyMzIzY2U3NGM4OWU0NTE0MyJ9fX0=", "&rLasagna", "", "&7&oRestores &b&o" + "5" + " &7&oHunger"),
				oven.asRecipeType(),
				new ItemStack[] {SlimefunItems.CHEESE, SlimefunItems.CHEESE, SlimefunItems.CHEESE, WonderItems.PASTA.getItem(), WonderItems.PASTA.getItem(), WonderItems.PASTA.getItem(), new ItemStack(Material.COOKED_BEEF),  new ItemStack(Material.COOKED_BEEF),  new ItemStack(Material.COOKED_BEEF)},
				5);
		WonderItems.LASAGNA.register(this);
		
		item_recipe = new ItemStack[] {null, new ItemStack(Material.SUGAR), null, WonderItems.donut.getItem(), SlimefunItems.HEAVY_CREAM, berryJam, null, null, null};
		slimefun_stack = new SlimefunItemStack("MOOS_DONUT", WonderItems.MOOS_DONUT);
		new SlimefunItem(EXPORTS, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		
		FoodListener food_listener = new FoodListener(this);
		
		item_recipe = new ItemStack[] {null, null, null, null,new CustomItem(WonderItems.WONDER_DUST, 4), null, null, null, null};
		slimefun_stack = new SlimefunItemStack("WONDER_ALLOY", WonderItems.WONDER_ALLOY);
		new SlimefunItem(MOOS_RESOURCES, slimefun_stack, wonder_machine.asRecipeType(), item_recipe).register(this);

				
		item_recipe = new ItemStack[] {null, null, null, null, WonderItems.WONDER_ALLOY, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("WONDER_FIBRE", WonderItems.WONDER_FIBRE);
		new SlimefunItem(MOOS_RESOURCES, slimefun_stack, WonderRecipeType.EXTRUDER, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {new CustomItem(SlimefunItems.GOLD_24K), new CustomItem(WonderItems.WONDER_FIBRE), new CustomItem(SlimefunItems.GOLD_24K), null, null, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("WONDER_WOOL", WonderItems.WONDER_WOOL);
		new SlimefunItem(MOOS_MISC, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {WonderItems.WONDER_WOOL, null, WonderItems.WONDER_WOOL, WonderItems.WONDER_WOOL, null, WonderItems.WONDER_WOOL, WonderItems.RUBBER, null, WonderItems.RUBBER};
		slimefun_stack = new SlimefunItemStack("MOOS_BOOTS", WonderItems.MOOS_BOOTS);
		new SlimefunItem(MOOS_ARMOR, slimefun_stack, RecipeType.ARMOR_FORGE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {WonderItems.WONDER_WOOL, null, WonderItems.WONDER_WOOL, WonderItems.WONDER_WOOL, WonderItems.WONDER_WOOL, WonderItems.WONDER_WOOL, WonderItems.WONDER_WOOL, WonderItems.WONDER_WOOL, WonderItems.WONDER_WOOL};
		slimefun_stack = new SlimefunItemStack("MOOS_TUNIC", WonderItems.MOOS_TUNIC);
		new SlimefunItem(MOOS_ARMOR, slimefun_stack, RecipeType.ARMOR_FORGE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {WonderItems.WONDER_WOOL, WonderItems.WONDER_WOOL, WonderItems.WONDER_WOOL, WonderItems.WONDER_WOOL, null, WonderItems.WONDER_WOOL, null, null, null};
		slimefun_stack = new SlimefunItemStack("MOOS_CAP", WonderItems.MOOS_CAP);
		new SlimefunItem(MOOS_ARMOR, slimefun_stack, RecipeType.ARMOR_FORGE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {WonderItems.WONDER_WOOL, WonderItems.WONDER_WOOL, WonderItems.WONDER_WOOL, WonderItems.WONDER_WOOL, WonderItems.WONDER_WOOL, WonderItems.WONDER_WOOL, null, null, null};
		slimefun_stack = new SlimefunItemStack("MOOS_KILT", WonderItems.MOOS_KILT);
		new SlimefunItem(MOOS_ARMOR, slimefun_stack, RecipeType.ARMOR_FORGE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, null, null, SlimefunItems.PLASTIC_SHEET, new ItemStack(Material.PAPER), SlimefunItems.PLASTIC_SHEET, new ItemStack(Material.BIRCH_STAIRS), new ItemStack(Material.COBBLESTONE_STAIRS), new ItemStack(Material.OAK_STAIRS)};
		slimefun_stack = new SlimefunItemStack("STEPS_DISCOGRAPHY", WonderItems.STEPS_DISCOGRAPHY);
		new SlimefunItem(MOOS_MISC, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, SlimefunItems.CHEESE, null, new ItemStack(Material.BROWN_MUSHROOM), new ItemStack(Material.BEEF), new ItemStack(Material.PORKCHOP), null, WonderItems.DOUGH, null};
		slimefun_stack = new SlimefunItemStack("MOO_PIZZA", WonderItems.MOO_PIZZA);
		new SlimefunItem(EXPORTS, slimefun_stack, oven.asRecipeType(), item_recipe).register(this);
		
		/*
		item_recipe = new ItemStack[] {SlimefunItems.MAGIC_SUGAR, null, SlimefunItems.SALT, new ItemStack(Material.CARROT), new ItemStack(Material.POTATO), new ItemStack(Material.BEETROOT), null, SlimefunItems.CAN, null};
		slimefun_stack = new SlimefunItemStack("MOO_SOUP", WonderItems.MOO_SOUP);
		new SlimefunItem(EXPORTS, slimefun_stack, oven.asRecipeType(), item_recipe).register(this);
		
		
		item_recipe = new ItemStack[] {null, null, null, SlimefunItems.CAN, new ItemStack(Material.CARROT, 2),  null, null, null, null};
		slimefun_stack = new SlimefunItemStack("TIN_CARROTS", WonderItems.TIN_CARROTS);
		new SlimefunItem(EXPORTS, slimefun_stack, cannary.asRecipeType(), item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, null, null, SlimefunItems.CAN, new ItemStack(Material.POTATO, 2),  null, null, null, null};
		slimefun_stack = new SlimefunItemStack("TIN_POTATOES", WonderItems.TIN_POTATOES);
		new SlimefunItem(EXPORTS, slimefun_stack, cannary.asRecipeType(), item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, null, null, SlimefunItems.CAN, new ItemStack(Material.BEETROOT, 2),  null, null, null, null};
		slimefun_stack = new SlimefunItemStack("TIN_BEETROOT", WonderItems.TIN_BEETROOT);
		new SlimefunItem(EXPORTS, slimefun_stack, cannary.asRecipeType(), item_recipe).register(this);
		*/

		item_recipe = new ItemStack[] {null, SlimefunItems.STEEL_PLATE, null, SlimefunItems.STEEL_PLATE, null, SlimefunItems.STEEL_PLATE, null, SlimefunItems.STEEL_PLATE, null};
		slimefun_stack = new SlimefunItemStack("SAW_BLADE", WonderItems.SAW_BLADE);
		new SlimefunItem(MOOS_MISC, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.MOOS_FRIES, WonderItems.MOOS_BURGER, WonderItems.MOO_SHAKE, null, null, null};
		slimefun_stack = new SlimefunItemStack("MOO_MEAL", WonderItems.MOO_MEAL);
		new SlimefunItem(EXPORTS, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, WonderItems.STEPS_DISCOGRAPHY, null, null, new ItemStack(Material.NOTE_BLOCK), new ItemStack(Material.TRIPWIRE_HOOK), SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.BATTERY, new ItemStack(Material.LEVER)};
		slimefun_stack = new SlimefunItemStack("RECORD_PLAYER", WonderItems.RECORD_PLAYER);
		new SlimefunItem(EXPORTS, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, new ItemStack(Material.MILK_BUCKET), null, new ItemStack(Material.MELON_SLICE), WonderItems.ICE_CREAM, new ItemStack(Material.APPLE), null, null, null};
		slimefun_stack = new SlimefunItemStack("MOO_SHAKE", WonderItems.MOO_SHAKE);
		new SlimefunItem(EXPORTS, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
	
		item_recipe = new ItemStack[] {null, null, null, null, SlimefunItems.HEAVY_CREAM, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("ICE_CREAM", WonderItems.ICE_CREAM);
		new SlimefunItem(MOOS_FOOD, slimefun_stack, WonderRecipeType.DEEP_FREEZE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.IRON_BLOCK), WonderItems.WONDER_ALLOY, WonderItems.WONDER_ALLOY, WonderItems.WONDER_ALLOY, new ItemStack(Material.OAK_LOG), WonderItems.PRINTED_CIRCUIT_BOARD, new ItemStack(Material.OAK_LOG)};
		slimefun_stack = new SlimefunItemStack("POWER_PISTON", WonderItems.POWER_PISTON);
		new SlimefunItem(HI_TECH_COMPONENTS, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, null, null, null, WonderItems.WONDER_ALLOY, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("WONDER_ALLOY_BLOCK", WonderItems.WONDER_ALLOY_BLOCK);
		new SlimefunItem(MOOS_RESOURCES, slimefun_stack, WonderRecipeType.WONDER_MACHINE, item_recipe).register(this);		
		
		item_recipe = new ItemStack[] {null, null, null, null, WonderItems.RAW_FRIES, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("MOOS_FRIES", WonderItems.MOOS_FRIES);
		new SlimefunItem(EXPORTS, slimefun_stack, WonderRecipeType.FRYER, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, null, null, null, new ItemStack(Material.POTATO), SlimefunItems.SALT, null, null, null};
		slimefun_stack = new SlimefunItemStack("RAW_FRIES", WonderItems.RAW_FRIES);
		new SlimefunItem(MOOS_FOOD, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, SlimefunItems.CHEESE, null, null, WonderItems.WONDER_PATTY, null, null, WonderItems.BUN, null};
		slimefun_stack = new SlimefunItemStack("MOOS_BURGER", WonderItems.MOOS_BURGER);
		new SlimefunItem(EXPORTS, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, null, null, SlimefunItems.SALT, WonderItems.DOUGH, new ItemStack(Material.SUGAR), null, null, null};
		slimefun_stack = new SlimefunItemStack("BUN", WonderItems.BUN);
		new SlimefunItem(MOOS_FOOD, slimefun_stack, oven.asRecipeType(), item_recipe).register(this);
		
		item_recipe = new ItemStack[] {WonderItems.MACE, SlimefunItems.SALT, WonderItems.MACE, null, new ItemStack(Material.BEEF), null, null, SlimefunItems.BUTTER, null};
		slimefun_stack = new SlimefunItemStack("WONDER_PATTY", WonderItems.WONDER_PATTY);
		new SlimefunItem(MOOS_FOOD, slimefun_stack, oven.asRecipeType(), item_recipe).register(this);
		
		item_recipe = new ItemStack[] {WonderItems.WHEEL, new ItemStack(Material.RED_DYE), WonderItems.WHEEL, SlimefunItems.TIN_INGOT, SlimefunItems.TIN_INGOT, SlimefunItems.TIN_INGOT, WonderItems.WHEEL, WonderItems.COGWHEEL, WonderItems.WHEEL};
		slimefun_stack = new SlimefunItemStack("TOY_CAR", WonderItems.TOY_CAR);
		new SlimefunItem(EXPORTS, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		/*
		item_recipe = new ItemStack[] {null, null, null, null, WonderItems.DOUGH, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("DONUT", WonderItems.DONUT);
		new SlimefunItem(EXPORTS, slimefun_stack, WonderRecipeType.FRYER, item_recipe).register(this);
		*/
		
		
		item_recipe = new ItemStack[] {SlimefunItems.FIRE_RUNE, new ItemStack(Material.NETHER_WART_BLOCK),null,null, null, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("MACE", WonderItems.MACE);
		new SlimefunItem(MOOS_MISC, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, null, null, SlimefunItems.WHEAT_FLOUR, new ItemStack(Material.WATER_BUCKET), SlimefunItems.SALT, null, WonderItems.YEAST, null};
		slimefun_stack = new SlimefunItemStack("DOUGH", WonderItems.DOUGH);
		new SlimefunItem(MOOS_MISC, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, null, null, null, new ItemStack(Material.BROWN_MUSHROOM), null, null, null, null};
		slimefun_stack = new SlimefunItemStack("YEAST", WonderItems.YEAST);
		new SlimefunItem(MOOS_MISC, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, SlimefunItems.STEEL_INGOT, null, null, WonderItems.PRINTED_CIRCUIT_BOARD, null, null, WonderItems.COGWHEEL, null};
		slimefun_stack = new SlimefunItemStack("ANTENNA", WonderItems.ANTENNA);
		new SlimefunItem(HI_TECH_COMPONENTS, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, WonderItems.ANTENNA, SlimefunItems.PLASTIC_SHEET, SlimefunItems.HOLOGRAM_PROJECTOR, new ItemStack(Material.GLASS_PANE), SlimefunItems.POWER_CRYSTAL, WonderItems.PRINTED_CIRCUIT_BOARD, SlimefunItems.PLASTIC_SHEET};
		slimefun_stack = new SlimefunItemStack("TV", WonderItems.TV);
		new SlimefunItem(EXPORTS, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {WonderItems.WONDER_SOLDER_INGOT, WonderItems.WONDER_SOLDER_INGOT, WonderItems.WONDER_SOLDER_INGOT, SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.BASIC_CIRCUIT_BOARD, null, null, null};
		slimefun_stack = new SlimefunItemStack("PRINTED_CIRCUIT_BOARD", WonderItems.PRINTED_CIRCUIT_BOARD);
		new SlimefunItem(HI_TECH_COMPONENTS, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {WonderItems.TITANIUM_INGOT, null, WonderItems.TITANIUM_INGOT, null, SlimefunItems.STEEL_INGOT, null, WonderItems.TITANIUM_INGOT, null, WonderItems.TITANIUM_INGOT};
		slimefun_stack = new SlimefunItemStack("COGWHEEL", WonderItems.COGWHEEL);
		new SlimefunItem(MOOS_MISC, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {SlimefunItems.MAGNESIUM_INGOT, SlimefunItems.SILVER_INGOT, SlimefunItems.TIN_INGOT, null, null, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("WONDER_SOLDER_INGOT", WonderItems.WONDER_SOLDER_INGOT);
		new SlimefunItem(MOOS_RESOURCES, slimefun_stack, RecipeType.SMELTERY, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {WonderItems.CHROMIUM_DUST, WonderItems.TITANIUM_DUST, WonderItems.CHROMIUM_INGOT, SlimefunItems.SYNTHETIC_DIAMOND, SlimefunItems.SYNTHETIC_EMERALD, SlimefunItems.SYNTHETIC_SAPPHIRE, null, SlimefunItems.RAINBOW_RUNE, null};
		slimefun_stack = new SlimefunItemStack("WONDER_DUST", WonderItems.WONDER_DUST);
		new SlimefunItem(MOOS_RESOURCES, slimefun_stack, RecipeType.SMELTERY, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, null, null, null, SlimefunItems.PURE_ORE_CLUSTER, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("TITANIUM_DUST", WonderItems.TITANIUM_DUST);
		new SlimefunItem(MOOS_RESOURCES, slimefun_stack, purifier.asRecipeType(), item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, null, null, null, SlimefunItems.PURE_ORE_CLUSTER, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("CHROMIUM_DUST", WonderItems.CHROMIUM_DUST);
		new SlimefunItem(MOOS_RESOURCES, slimefun_stack, purifier.asRecipeType(), item_recipe).register(this);
		
		item_recipe = new ItemStack[] {new CustomItem(WonderItems.TITANIUM_DUST, 2), null, null, null, null, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("TITANIUM_INGOT", WonderItems.TITANIUM_INGOT);
		new SlimefunItem(MOOS_RESOURCES, slimefun_stack, RecipeType.SMELTERY, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {new CustomItem(WonderItems.CHROMIUM_DUST, 2), null, null, null, null, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("CHROMIUM_INGOT", WonderItems.CHROMIUM_INGOT);
		new SlimefunItem(MOOS_RESOURCES, slimefun_stack, RecipeType.SMELTERY, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {new CustomItem(SlimefunItems.CARBONADO,9), null, null, null, null, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("CARBONADO_BLOCK", WonderItems.CARBONADO_BLOCK);
		new SlimefunItem(MOOS_MISC, slimefun_stack, WonderRecipeType.WONDER_MACHINE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {new ItemStack(Material.OAK_PRESSURE_PLATE), new ItemStack(Material.OAK_PRESSURE_PLATE), new ItemStack(Material.OAK_PRESSURE_PLATE), null, WonderItems.CHROMIUM_INGOT, null, new ItemStack(Material.OAK_PRESSURE_PLATE), new ItemStack(Material.OAK_PRESSURE_PLATE), new ItemStack(Material.OAK_PRESSURE_PLATE)};
		slimefun_stack = new SlimefunItemStack("SPINDEL", WonderItems.SPINDEL);
		new SlimefunItem(MOOS_MISC, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {WonderItems.CARBON_FIBRE, null, null, null, WonderItems.CARBON_FIBRE, null, null, null, WonderItems.CARBON_FIBRE};
		slimefun_stack = new SlimefunItemStack("CARBON_FIBRE_ROPE", WonderItems.CARBON_FIBRE_ROPE);
		new SlimefunItem(MOOS_RESOURCES, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {WonderItems.RUBBER, WonderItems.RUBBER, WonderItems.RUBBER, WonderItems.RUBBER, WonderItems.CHROMIUM_INGOT, WonderItems.RUBBER, WonderItems.RUBBER, WonderItems.RUBBER, WonderItems.RUBBER};
		slimefun_stack = new SlimefunItemStack("WHEEL", WonderItems.WHEEL);
		new SlimefunItem(MOOS_RESOURCES, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
	
		item_recipe = new ItemStack[] {null, null, null, null, SlimefunItems.CARBON, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("CARBON_FIBRE", WonderItems.CARBON_FIBRE);
		new SlimefunItem(MOOS_RESOURCES, slimefun_stack, WonderRecipeType.EXTRUDER, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, null, null, null, SlimefunItems.LEAD_INGOT, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("LEAD_FIBRE", WonderItems.LEAD_FIBRE);
		new SlimefunItem(MOOS_RESOURCES, slimefun_stack, WonderRecipeType.EXTRUDER, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {WonderItems.LEAD_FIBRE, WonderItems.LEAD_FIBRE, WonderItems.LEAD_FIBRE, WonderItems.CARBON_FIBRE, WonderItems.CARBON_FIBRE, WonderItems.CARBON_FIBRE, WonderItems.LEAD_FIBRE, WonderItems.LEAD_FIBRE, WonderItems.LEAD_FIBRE};
		slimefun_stack = new SlimefunItemStack("HAZMAT_CLOTH", WonderItems.HAZMAT_CLOTH);
		new SlimefunItem(MOOS_MISC, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, new ItemStack(Material.MAGMA_BLOCK), SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET};
		slimefun_stack = new SlimefunItemStack("VISOR", WonderItems.VISOR);
		new SlimefunItem(MOOS_MISC, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);		
		
		item_recipe = new ItemStack[] {null, SlimefunItems.PLASTIC_SHEET, null, null, new ItemStack(Material.CHARCOAL), null, null, null, null};
		slimefun_stack = new SlimefunItemStack("RUBBER", WonderItems.RUBBER);
		new SlimefunItem(MOOS_RESOURCES, slimefun_stack, RecipeType.SMELTERY, item_recipe).register(this);	
	
		//item_recipe = new ItemStack[] {SlimefunItems.ELYTRA_SCALE, SlimefunItems.ELYTRA_SCALE, SlimefunItems.ELYTRA_SCALE, SlimefunItems.ELYTRA_SCALE, SlimefunItems.ELYTRA_SCALE, SlimefunItems.ELYTRA_SCALE, SlimefunItems.RUNE_AIR, SlimefunItems.ELYTRA_SCALE, SlimefunItems.RUNE_AIR};
		//slimefun_stack = new SlimefunItemStack("ELYTRA_REMAKE", WonderItems.ELYTRA_REMAKE);
		//new SlimefunItem(MOOS_MAGIC, slimefun_stack, RecipeType.ANCIENT_ALTAR, item_recipe).register(this);	

		
		item_recipe = new ItemStack[] {SlimefunItems.SOLAR_GENERATOR_4, SlimefunItems.SOLAR_GENERATOR_4, SlimefunItems.SOLAR_GENERATOR_4, WonderItems.WONDER_ALLOY, SlimefunItems.ELECTRO_MAGNET, WonderItems.WONDER_ALLOY, WonderItems.WONDER_ALLOY, WonderItems.WONDER_ALLOY, WonderItems.WONDER_ALLOY };
		slimefun_stack = new SlimefunItemStack("WONDER_SOLAR_GENERATOR", WonderItems.WONDER_SOLAR_GENERATOR);	
		WonderSolarGenerator wonder_soloar_generator =  new WonderSolarGenerator(MOOS_ELECTRICITY, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe);
		wonder_soloar_generator.register(this);
		
		item_recipe = new ItemStack[] {WonderItems.MOOINIUM, WonderItems.MOOINIUM, WonderItems.MOOINIUM, null, WonderItems.WONDER_SOLAR_GENERATOR, null, SlimefunItems.MAGNESIUM_INGOT, SlimefunItems.MAGNESIUM_INGOT, SlimefunItems.MAGNESIUM_INGOT };
		slimefun_stack = new SlimefunItemStack("WONDER_SOLAR_GENERATOR_II", WonderItems.WONDER_SOLAR_GENERATOR_II);	
		WonderSolarGeneratorII wonder_soloar_generator_II =  new WonderSolarGeneratorII(MOOS_ELECTRICITY, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe);
		wonder_soloar_generator_II.register(this);
		
		item_recipe = new ItemStack[] {WonderItems.MOOINIUM, WonderItems.MOOINIUM, WonderItems.MOOINIUM, null, WonderItems.WONDER_SOLAR_GENERATOR_II, null, SlimefunItems.MAGNESIUM_INGOT, SlimefunItems.MAGNESIUM_INGOT, SlimefunItems.MAGNESIUM_INGOT };
		slimefun_stack = new SlimefunItemStack("WONDER_SOLAR_GENERATOR_III", WonderItems.WONDER_SOLAR_GENERATOR_III);	
		WonderSolarGeneratorIII wonder_soloar_generator_III =  new WonderSolarGeneratorIII(MOOS_ELECTRICITY, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe);
		wonder_soloar_generator_III.register(this);

		item_recipe = new ItemStack[] {null, WonderItems.YEAST, null, new ItemStack(Material.SUGAR), new ItemStack(Material.SUGAR), new ItemStack(Material.SUGAR), new ItemStack(Material.POTATO), new ItemStack(Material.POTATO), new ItemStack(Material.POTATO)};
		slimefun_stack = new SlimefunItemStack("ALCOHOL", WonderItems.ALCOHOL);
		new SlimefunItem(MOOS_MISC, slimefun_stack, still.asRecipeType(), item_recipe).register(this);	
		

		item_recipe = new ItemStack[] {null, null, null, WonderItems.CALCIUM, new ItemStack(Material.BLUE_DYE), WonderItems.CALCIUM, null, null, null};
		slimefun_stack = new SlimefunItemStack("TUBE_CORAL_BLOCK", WonderItems.TUBE_CORAL_BLOCK);
		new SlimefunItem(OCEANOGRAPHY, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		  
		item_recipe = new ItemStack[] {null, null, null, WonderItems.CALCIUM, new ItemStack(Material.PINK_DYE), WonderItems.CALCIUM, null, null, null};
		slimefun_stack = new SlimefunItemStack("BRAIN_CORAL_BLOCK", WonderItems.BRAIN_CORAL_BLOCK);
		new SlimefunItem(OCEANOGRAPHY, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.CALCIUM, new ItemStack(Material.PURPLE_DYE), WonderItems.CALCIUM, null, null, null};
		slimefun_stack = new SlimefunItemStack("BUBBLE_CORAL_BLOCK", WonderItems.BUBBLE_CORAL_BLOCK);
		new SlimefunItem(OCEANOGRAPHY, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.CALCIUM, new ItemStack(Material.RED_DYE), WonderItems.CALCIUM, null, null, null};
		slimefun_stack = new SlimefunItemStack("FIRE_CORAL_BLOCK", WonderItems.FIRE_CORAL_BLOCK);
		new SlimefunItem(OCEANOGRAPHY, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.CALCIUM, new ItemStack(Material.YELLOW_DYE), WonderItems.CALCIUM, null, null, null};
		slimefun_stack = new SlimefunItemStack("HORN_CORAL_BLOCK", WonderItems.HORN_CORAL_BLOCK);
		new SlimefunItem(OCEANOGRAPHY, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
				
		item_recipe = new ItemStack[] {new ItemStack(Material.BRAIN_CORAL_BLOCK, 4)};
		SlimefunItemStack brain_coral_dust = new SlimefunItemStack("BRAIN_CORAL_DUST", WonderItems.BRAIN_CORAL_DUST);
		new SlimefunItem(OCEANOGRAPHY, brain_coral_dust, coral_crusher.asRecipeType(), item_recipe).register(this);	

		item_recipe = new ItemStack[] {new ItemStack(Material.BUBBLE_CORAL_BLOCK, 4)};
		SlimefunItemStack bubble_coral_dust = new SlimefunItemStack("BUBBLE_CORAL_DUST", WonderItems.BUBBLE_CORAL_DUST);
		new SlimefunItem(OCEANOGRAPHY, bubble_coral_dust,  coral_crusher.asRecipeType(), item_recipe).register(this);			

		item_recipe = new ItemStack[] {new ItemStack(Material.FIRE_CORAL_BLOCK, 4)};
		SlimefunItemStack fire_coral_dust = new SlimefunItemStack("FIRE_CORAL_DUST", WonderItems.FIRE_CORAL_DUST);
		new SlimefunItem(OCEANOGRAPHY, fire_coral_dust,  coral_crusher.asRecipeType(), item_recipe).register(this);	

		item_recipe = new ItemStack[] {new ItemStack(Material.HORN_CORAL_BLOCK, 4)};
		SlimefunItemStack horn_coral_dust = new SlimefunItemStack("HORN_CORAL_DUST", WonderItems.HORN_CORAL_DUST);
		new SlimefunItem(OCEANOGRAPHY, horn_coral_dust,  coral_crusher.asRecipeType(), item_recipe).register(this);	

		item_recipe = new ItemStack[] {new ItemStack(Material.TUBE_CORAL_BLOCK, 4)};
		SlimefunItemStack tube_coral_dust = new SlimefunItemStack("TUBE_CORAL_DUST", WonderItems.TUBE_CORAL_DUST);
		new SlimefunItem(OCEANOGRAPHY, tube_coral_dust,  coral_crusher.asRecipeType(), item_recipe).register(this);	

		item_recipe = new ItemStack[] {bubble_coral_dust, brain_coral_dust, fire_coral_dust, horn_coral_dust, SlimefunItems.STONE_CHUNK, tube_coral_dust, null, null, null};
		SlimefunItemStack marble_block = new SlimefunItemStack("MARBLE_BLOCK", WonderItems.MARBLE_BLOCK);
		new SlimefunItem(OCEANOGRAPHY, marble_block, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);	
			 
		
		item_recipe = new ItemStack[] {new ItemStack(Material.STRIPPED_SPRUCE_LOG), null, null, null, null, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("WOOD_CHIPS", WonderItems.WOOD_CHIPS);
		new SlimefunItem(MOOS_MISC, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);    

		item_recipe = new ItemStack[] {SlimefunItems.ALUMINUM_INGOT, null, null, null, null, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("ALUMINIUM_FOIL", WonderItems.ALUMINIUM_FOIL);
		new SlimefunItem(MOOS_MISC, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);   
		
		item_recipe = new ItemStack[] {new ItemStack(Material.NETHERITE_SCRAP), null, null, null, null, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("NETHERITE_DUST", WonderItems.NETHERITE_DUST);
		new SlimefunItem(MOOS_RESOURCES, slimefun_stack, WonderRecipeType.FRYER, item_recipe).register(this);
		

		MoosClaymore moos_claymore = new MoosClaymore(MOOS_TOOLS);
		moos_claymore.register(this);
		
		MoosCrossbow moos_crossbow = new MoosCrossbow(MOOS_TOOLS);
		moos_crossbow.register(this);
		
		MoosBow moos_bow = new MoosBow(MOOS_TOOLS);
		//moos_bow.register(this);
		

		item_recipe = new ItemStack[] {new ItemStack(Material.LEATHER), new ItemStack(Material.LEATHER), new ItemStack(Material.LEATHER), new ItemStack(SlimefunItems.BILLON_INGOT), new ItemStack(Material.PURPLE_DYE), new ItemStack(SlimefunItems.BILLON_INGOT), null, null, null};
		slimefun_stack = new SlimefunItemStack("HIDE_HELMET", WonderItems.HIDE_HELMET);
		new SlimefunItem(MOOS_ARMOR, slimefun_stack, RecipeType.ARMOR_FORGE, item_recipe).register(this);
	
		item_recipe = new ItemStack[] {new ItemStack(Material.LEATHER), null , new ItemStack(Material.LEATHER), new ItemStack(SlimefunItems.BILLON_INGOT), new ItemStack(SlimefunItems.BILLON_INGOT), new ItemStack(SlimefunItems.BILLON_INGOT), new ItemStack(SlimefunItems.STEEL_INGOT), new ItemStack(Material.PURPLE_WOOL), new ItemStack(SlimefunItems.STEEL_INGOT)};
		slimefun_stack = new SlimefunItemStack("HIDE_CHESTPLATE", WonderItems.HIDE_CHESTPLATE);
		new SlimefunItem(MOOS_ARMOR, slimefun_stack, RecipeType.ARMOR_FORGE, item_recipe).register(this);

		item_recipe = new ItemStack[] {new ItemStack(Material.LEATHER), new ItemStack(Material.LEATHER) , new ItemStack(Material.LEATHER), new ItemStack(SlimefunItems.BILLON_INGOT), null, new ItemStack(SlimefunItems.BILLON_INGOT) , new ItemStack(Material.PURPLE_WOOL), null, new ItemStack(Material.PURPLE_WOOL)};
		slimefun_stack = new SlimefunItemStack("HIDE_LEGGINGS", WonderItems.HIDE_LEGGINGS);
		new SlimefunItem(MOOS_ARMOR, slimefun_stack, RecipeType.ARMOR_FORGE, item_recipe).register(this);

		item_recipe = new ItemStack[] {null,null,null, new ItemStack(Material.PURPLE_WOOL), null, new ItemStack(Material.PURPLE_WOOL), new ItemStack(SlimefunItems.BILLON_INGOT), null, new ItemStack(SlimefunItems.BILLON_INGOT)};
		slimefun_stack = new SlimefunItemStack("HIDE_BOOTS", WonderItems.HIDE_BOOTS);
		new SlimefunItem(MOOS_ARMOR, slimefun_stack, RecipeType.ARMOR_FORGE, item_recipe).register(this);

		item_recipe = new ItemStack[] {new ItemStack(Material.GUNPOWDER), SlimefunItems.SULFATE, new ItemStack(Material.GUNPOWDER), SlimefunItems.SULFATE, WonderItems.WONDER_DUST, SlimefunItems.SULFATE, new ItemStack(Material.GUNPOWDER), SlimefunItems.SULFATE, new ItemStack(Material.GUNPOWDER)};
		SlimefunItemStack super_tnt = new SlimefunItemStack("SUPER_TNT", WonderItems.SUPER_TNT);
		new SlimefunItem(MOOS_MISC, super_tnt, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.HAZMAT_CLOTH, WonderItems.HAZMAT_CLOTH, WonderItems.HAZMAT_CLOTH, WonderItems.HAZMAT_CLOTH, WonderItems.VISOR, WonderItems.HAZMAT_CLOTH};
		SlimefunItemStack hazmat_helmet = new SlimefunItemStack("HAZMATSUIT_HELMET", WonderItems.HAZMAT_HELMET);
		PotionEffect potion_effect1[] = {new PotionEffect(PotionEffectType.WATER_BREATHING, 300, 1)};		
		new HazmatTask(MOOS_ARMOR, hazmat_helmet, RecipeType.ARMOR_FORGE, item_recipe, potion_effect1).register(this);		
		
		item_recipe = new ItemStack[] {WonderItems.HAZMAT_CLOTH, null, WonderItems.HAZMAT_CLOTH, WonderItems.HAZMAT_CLOTH, WonderItems.HAZMAT_CLOTH, WonderItems.HAZMAT_CLOTH, WonderItems.HAZMAT_CLOTH, WonderItems.HAZMAT_CLOTH, WonderItems.HAZMAT_CLOTH};
		SlimefunItemStack hazmat_chest = new SlimefunItemStack("HAZMATSUIT_CHESTPLATE", WonderItems.HAZMATSUIT_CHESTPLATE);
		PotionEffect potion_effect2[] = {new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 1)};
		new HazmatTask(MOOS_ARMOR, hazmat_chest, RecipeType.ARMOR_FORGE, item_recipe, potion_effect2).register(this);	
		
		item_recipe = new ItemStack[] {WonderItems.HAZMAT_CLOTH, WonderItems.HAZMAT_CLOTH, WonderItems.HAZMAT_CLOTH, new ItemStack(Material.LEATHER), null, new ItemStack(Material.LEATHER), WonderItems.HAZMAT_CLOTH, null, WonderItems.HAZMAT_CLOTH};
		SlimefunItemStack hazmat_leggings = new SlimefunItemStack("HAZMATSUIT_LEGGINGS", WonderItems.HAZMATSUIT_LEGGINGS);
		new HazmatTask(MOOS_ARMOR, hazmat_leggings, RecipeType.ARMOR_FORGE, item_recipe, null).register(this);	
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.HAZMAT_CLOTH, null, WonderItems.HAZMAT_CLOTH, WonderItems.RUBBER, null, WonderItems.RUBBER};
		SlimefunItemStack hazmat_boots  = new SlimefunItemStack("HAZMATSUIT_BOOTS", WonderItems.HAZMAT_BOOTS);
		new HazmatTask(MOOS_ARMOR, hazmat_boots, RecipeType.ARMOR_FORGE, item_recipe, null).register(this);	

		
		item_recipe = new ItemStack[] {null, null, null, null, new ItemStack(Material.WATER_BUCKET), null, new CustomItem(SlimefunItems.SULFATE), new CustomItem(SlimefunItems.SULFATE), new CustomItem(SlimefunItems.SULFATE)};
		slimefun_stack = new SlimefunItemStack("SULFURIC_ACID", WonderItems.SULFURIC_ACID);
		new SlimefunItem(MOOS_MISC, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {new ItemStack(Material.BONE_MEAL), new ItemStack(Material.BONE_MEAL), new ItemStack(Material.BONE_MEAL), new ItemStack(Material.BONE_MEAL), WonderItems.SULFURIC_ACID, new ItemStack(Material.BONE_MEAL), new ItemStack(Material.BONE_MEAL), new ItemStack(Material.BONE_MEAL), new ItemStack(Material.BONE_MEAL)};
		slimefun_stack = new SlimefunItemStack("CALCIUM", WonderItems.CALCIUM);
		new SlimefunItem(MOOS_MISC, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {WonderItems.CALCIUM, WonderItems.CALCIUM, WonderItems.CALCIUM, WonderItems.CALCIUM, null, WonderItems.CALCIUM, WonderItems.CALCIUM, WonderItems.CALCIUM, WonderItems.CALCIUM};
		slimefun_stack = new SlimefunItemStack("EGG_SHELL", WonderItems.EGG_SHELL);
		new SlimefunItem(LIFE, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {new ItemStack(Material.PORKCHOP), new ItemStack(Material.CHICKEN), new ItemStack(Material.BEEF), WonderItems.SULFURIC_ACID, new ItemStack(Material.GLASS_BOTTLE), WonderItems.ALCOHOL, new ItemStack(Material.COD), new ItemStack(Material.MUTTON), new ItemStack(Material.SALMON)};
		slimefun_stack = new SlimefunItemStack("DNA", WonderItems.DNA);
		new SlimefunItem(LIFE, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);	
		
		
		item_recipe = new ItemStack[] {null, WonderItems.DNA, null, new ItemStack(Material.LEATHER), new ItemStack(Material.BEEF), new ItemStack(Material.MILK_BUCKET), null, WonderItems.DNA, null};
		slimefun_stack = new SlimefunItemStack("COW_ESSENCE", WonderItems.COW_ESSENCE);
		new SlimefunItem(LIFE, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, WonderItems.DNA, null, new ItemStack(Material.PORKCHOP), new ItemStack(Material.PINK_DYE), new ItemStack(Material.PORKCHOP), null, WonderItems.DNA, null};
		slimefun_stack = new SlimefunItemStack("PIG_ESSENCE", WonderItems.PIG_ESSENCE);
		new SlimefunItem(LIFE, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, WonderItems.DNA, null, new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.MUTTON), new ItemStack(Material.WHITE_WOOL), null, WonderItems.DNA, null};
		slimefun_stack = new SlimefunItemStack("SHEEP_ESSENCE", WonderItems.SHEEP_ESSENCE);
		new SlimefunItem(LIFE, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, WonderItems.DNA, null, new ItemStack(Material.LEAD), new ItemStack(Material.BONE), new ItemStack(Material.NAME_TAG), null, WonderItems.DNA, null};
		slimefun_stack = new SlimefunItemStack("WOLF_ESSENCE", WonderItems.WOLF_ESSENCE);
		new SlimefunItem(LIFE, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, WonderItems.DNA, null, new ItemStack(Material.INK_SAC), new ItemStack(Material.SPIDER_EYE), new ItemStack(Material.SLIME_BALL), null, WonderItems.DNA, null};
		slimefun_stack = new SlimefunItemStack("SQUID_ESSENCE", WonderItems.SQUID_ESSENCE);
		new SlimefunItem(LIFE, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, WonderItems.DNA, null, new ItemStack(Material.HONEY_BOTTLE), new ItemStack(Material.FEATHER), new ItemStack(Material.HONEYCOMB), null, WonderItems.DNA, null};
		slimefun_stack = new SlimefunItemStack("BEE_ESSENCE", WonderItems.BEE_ESSENCE);
		new SlimefunItem(LIFE, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, WonderItems.DNA, null, new ItemStack(Material.EMERALD), new ItemStack(Material.BOOK), new ItemStack(Material.MAP), null, WonderItems.DNA, null};
		slimefun_stack = new SlimefunItemStack("VILLAGER_ESSENCE", WonderItems.VILLAGER_ESSENCE);
		new SlimefunItem(LIFE, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, WonderItems.DNA, null, new ItemStack(Material.RED_MUSHROOM), new ItemStack(Material.BEEF), new ItemStack(Material.MILK_BUCKET), null, WonderItems.DNA, null};
		slimefun_stack = new SlimefunItemStack("MOOSHROOM_ESSENCE", WonderItems.MOOSHROOM_ESSENCE);
		new SlimefunItem(LIFE, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, WonderItems.DNA, null, new ItemStack(Material.BLACK_DYE), new ItemStack(Material.BAMBOO), new ItemStack(Material.WHITE_DYE), null, WonderItems.DNA, null};
		slimefun_stack = new SlimefunItemStack("PANDA_ESSENCE", WonderItems.PANDA_ESSENCE);
		new SlimefunItem(LIFE, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, WonderItems.DNA, null, new ItemStack(Material.HAY_BLOCK), new ItemStack(Material.SADDLE), new ItemStack(Material.LEATHER_HORSE_ARMOR), null, WonderItems.DNA, null};
		slimefun_stack = new SlimefunItemStack("HORSE_ESSENCE", WonderItems.HORSE_ESSENCE);
		new SlimefunItem(LIFE, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, WonderItems.DNA, null, new ItemStack(Material.COD), new ItemStack(Material.KELP), WonderItems.RUBBER, null, WonderItems.DNA, null};
		slimefun_stack = new SlimefunItemStack("DOLPHIN_ESSENCE", WonderItems.DOLPHIN_ESSENCE);
		new SlimefunItem(LIFE, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, WonderItems.DNA, null, new ItemStack(Material.SWEET_BERRIES), new ItemStack(Material.ORANGE_DYE), new ItemStack(Material.MUTTON), null, WonderItems.DNA, null};
		slimefun_stack = new SlimefunItemStack("FOX_ESSENCE", WonderItems.FOX_ESSENCE);
		new SlimefunItem(LIFE, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, WonderItems.DNA, null, new ItemStack(Material.SCUTE), new ItemStack(Material.SEAGRASS), new ItemStack(Material.TURTLE_EGG), null, WonderItems.DNA, null};
		slimefun_stack = new SlimefunItemStack("TURTLE_ESSENCE", WonderItems.TURTLE_ESSENCE);
		new SlimefunItem(LIFE, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, WonderItems.DNA, null, new ItemStack(Material.COOKIE), new ItemStack(Material.FEATHER), SlimefunItems.RAINBOW_RUNE, null, WonderItems.DNA, null};
		slimefun_stack = new SlimefunItemStack("PARROT_ESSENCE", WonderItems.PARROT_ESSENCE);
		new SlimefunItem(LIFE, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, WonderItems.DNA, null, new ItemStack(Material.RABBIT_FOOT), new ItemStack(Material.RABBIT_HIDE), new ItemStack(Material.RABBIT_STEW), null, WonderItems.DNA, null};
		slimefun_stack = new SlimefunItemStack("RABBIT_ESSENCE", WonderItems.RABBIT_ESSENCE);
		new SlimefunItem(LIFE, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, WonderItems.DNA, null, new ItemStack(Material.SNOWBALL), new ItemStack(Material.BLUE_ICE), new ItemStack(Material.SALMON), null, WonderItems.DNA, null};
		slimefun_stack = new SlimefunItemStack("POLAR_BEAR_ESSENCE", WonderItems.POLAR_BEAR_ESSENCE);
		new SlimefunItem(LIFE, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, WonderItems.DNA, null, new ItemStack(Material.PRISMARINE_CRYSTALS), new ItemStack(Material.TROPICAL_FISH), new ItemStack(Material.PRISMARINE_SHARD), null, WonderItems.DNA, null};
		slimefun_stack = new SlimefunItemStack("GUARDIAN_ESSENCE", WonderItems.GUARDIAN_ESSENCE);
		new SlimefunItem(LIFE, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, WonderItems.DNA, null, new ItemStack(Material.CHEST), new ItemStack(Material.LEAD), new ItemStack(Material.WHITE_WOOL), null, WonderItems.DNA, null};
		slimefun_stack = new SlimefunItemStack("LLAMA_ESSENCE", WonderItems.LLAMA_ESSENCE);
		new SlimefunItem(LIFE, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, WonderItems.DNA, null, new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SLIME_BALL), null, WonderItems.DNA, null};
		slimefun_stack = new SlimefunItemStack("SLIME_ESSENCE", WonderItems.SLIME_ESSENCE);
		new SlimefunItem(LIFE, slimefun_stack, RecipeType.MAGIC_WORKBENCH, item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.COW_ESSENCE, WonderItems.EGG_SHELL, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("PIG_SPAWN_EGG", WonderItems.PIG_SPAWN_EGG);
		new SlimefunItem(LIFE, slimefun_stack, reanimator.asRecipeType(), item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.SHEEP_ESSENCE, WonderItems.EGG_SHELL, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("SHEEP_SPAWN_EGG", WonderItems.SHEEP_SPAWN_EGG);
		new SlimefunItem(LIFE, slimefun_stack, reanimator.asRecipeType(), item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.WOLF_ESSENCE, WonderItems.EGG_SHELL, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("WOLF_SPAWN_EGG", WonderItems.WOLF_SPAWN_EGG);
		new SlimefunItem(LIFE, slimefun_stack, reanimator.asRecipeType(), item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.SQUID_ESSENCE, WonderItems.EGG_SHELL, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("SQUID_SPAWN_EGG", WonderItems.SQUID_SPAWN_EGG);
		new SlimefunItem(LIFE, slimefun_stack, reanimator.asRecipeType(), item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.BEE_ESSENCE, WonderItems.EGG_SHELL, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("BEE_SPAWN_EGG", WonderItems.BEE_SPAWN_EGG);
		new SlimefunItem(LIFE, slimefun_stack, reanimator.asRecipeType(), item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.VILLAGER_ESSENCE, WonderItems.EGG_SHELL, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("VILLAGER_SPAWN_EGG", WonderItems.VILLAGER_SPAWN_EGG);
		new SlimefunItem(LIFE, slimefun_stack, reanimator.asRecipeType(), item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.MOOSHROOM_ESSENCE, WonderItems.EGG_SHELL, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("MOOSHROOM_SPAWN_EGG", WonderItems.MOOSHROOM_SPAWN_EGG);
		new SlimefunItem(LIFE, slimefun_stack, reanimator.asRecipeType(), item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.PANDA_ESSENCE, WonderItems.EGG_SHELL, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("PANDA_SPAWN_EGG", WonderItems.PANDA_SPAWN_EGG);
		new SlimefunItem(LIFE, slimefun_stack, reanimator.asRecipeType(), item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.HORSE_ESSENCE, WonderItems.EGG_SHELL, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("HORSE_SPAWN_EGG", WonderItems.HORSE_SPAWN_EGG);
		new SlimefunItem(LIFE, slimefun_stack, reanimator.asRecipeType(), item_recipe).register(this);	
				
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.DOLPHIN_ESSENCE, WonderItems.EGG_SHELL, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("DOLPHIN_SPAWN_EGG", WonderItems.DOLPHIN_SPAWN_EGG);
		new SlimefunItem(LIFE, slimefun_stack, reanimator.asRecipeType(), item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.FOX_ESSENCE, WonderItems.EGG_SHELL, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("FOX_SPAWN_EGG", WonderItems.FOX_SPAWN_EGG);
		new SlimefunItem(LIFE, slimefun_stack, reanimator.asRecipeType(), item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.TURTLE_ESSENCE, WonderItems.EGG_SHELL, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("TURTLE_SPAWN_EGG", WonderItems.TURTLE_SPAWN_EGG);
		new SlimefunItem(LIFE, slimefun_stack, reanimator.asRecipeType(), item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.PARROT_ESSENCE, WonderItems.EGG_SHELL, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("PARROT_SPAWN_EGG", WonderItems.PARROT_SPAWN_EGG);
		new SlimefunItem(LIFE, slimefun_stack, reanimator.asRecipeType(), item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.RABBIT_ESSENCE, WonderItems.EGG_SHELL, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("RABBIT_SPAWN_EGG", WonderItems.RABBIT_SPAWN_EGG);
		new SlimefunItem(LIFE, slimefun_stack, reanimator.asRecipeType(), item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.GUARDIAN_ESSENCE, WonderItems.EGG_SHELL, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("GUARDIAN_SPAWN_EGG", WonderItems.GUARDIAN_SPAWN_EGG);
		new SlimefunItem(LIFE, slimefun_stack, reanimator.asRecipeType(), item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.LLAMA_ESSENCE, WonderItems.EGG_SHELL, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("LLAMA_SPAWN_EGG", WonderItems.LLAMA_SPAWN_EGG);
		new SlimefunItem(LIFE, slimefun_stack, reanimator.asRecipeType(), item_recipe).register(this);	
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.SLIME_ESSENCE, WonderItems.EGG_SHELL, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("SLIME_SPAWN_EGG", WonderItems.SLIME_SPAWN_EGG);
		new SlimefunItem(LIFE, slimefun_stack, reanimator.asRecipeType(), item_recipe).register(this);	
		
		
		item_recipe = new ItemStack[] {SlimefunItems.SYNTHETIC_DIAMOND, SlimefunItems.SYNTHETIC_DIAMOND, SlimefunItems.SYNTHETIC_DIAMOND, 
				SlimefunItems.SYNTHETIC_DIAMOND, WonderItems.SAW_BLADE, SlimefunItems.SYNTHETIC_DIAMOND, 
				SlimefunItems.SYNTHETIC_DIAMOND, SlimefunItems.SYNTHETIC_DIAMOND, SlimefunItems.SYNTHETIC_DIAMOND};
		
		slimefun_stack = new SlimefunItemStack("DIAMOND_EDGED_CUTTER", WonderItems.DIAMOND_EDGED_CUTTER);
		new SlimefunItem(HI_TECH_COMPONENTS, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		
		item_recipe = new ItemStack[] {WonderItems.WONDER_ALLOY, SlimefunItems.REINFORCED_ALLOY_INGOT, WonderItems.WONDER_ALLOY, 
				WonderItems.NETHERITE_DUST, SlimefunItems.LAVA_CRYSTAL, WonderItems.NETHERITE_DUST, 
				WonderItems.WONDER_ALLOY, SlimefunItems.DAMASCUS_STEEL_INGOT, WonderItems.WONDER_ALLOY};
		
		slimefun_stack = new SlimefunItemStack("HARDENED_NETHERITE_INGOT", WonderItems.HARDENED_NETHERITE_INGOT);
		new SlimefunItem(MOOS_RESOURCES, slimefun_stack, RecipeType.SMELTERY, item_recipe).register(this);
				
		MoosEnhancedPickaxe moos_enhanced_pickaxe = new MoosEnhancedPickaxe(MOOS_TOOLS);
		moos_enhanced_pickaxe.register(this);
		
		
		
		MoosPickaxe moos_pickaxe = new MoosPickaxe(MOOS_TOOLS);
		moos_pickaxe.register(this);
		
		item_recipe = new ItemStack[] {null, null, null, WonderItems.DUBNIUM, SlimefunItems.URANIUM, null, null, null, null};
		slimefun_stack = new SlimefunItemStack("MOOINIUM", WonderItems.MOOINIUM);
		new SlimefunItem(MOOS_RESOURCES, slimefun_stack, small_hadron_collider.asRecipeType(), item_recipe).register(this);
		
		item_recipe = new ItemStack[] {null, null, null, new ItemStack(Material.STICK),SlimefunItems.LEAD_INGOT, new ItemStack(Material.STICK), null, null, null};
		slimefun_stack = new SlimefunItemStack("PENCIL", WonderItems.PENCIL);
		new SlimefunItem(MOOS_MISC, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		new RadioactiveItem(MOOS_MISC, Radioactivity.VERY_HIGH, (SlimefunItemStack) WonderItems.DUBNIUM, purifier.asRecipeType(),
		new ItemStack[] {null, null, null, null, SlimefunItems.PURE_ORE_CLUSTER, null, null, null, null})
		.register(this);
		
		item_recipe = new ItemStack[] {SlimefunItems.HARDENED_METAL_INGOT, WonderItems.WONDER_ALLOY, SlimefunItems.HARDENED_METAL_INGOT, WonderItems.MOOINIUM, SlimefunItems.LAVA_CRYSTAL, WonderItems.MOOINIUM, SlimefunItems.HARDENED_METAL_INGOT, WonderItems.WONDER_ALLOY, SlimefunItems.HARDENED_METAL_INGOT};
		slimefun_stack = new SlimefunItemStack("POWER_CELL", WonderItems.POWER_CELL);
		new SlimefunItem(HI_TECH_COMPONENTS, slimefun_stack, RecipeType.ENHANCED_CRAFTING_TABLE, item_recipe).register(this);
		
		WonderMinerAndroidI wonder_miner_android_i = new WonderMinerAndroidI(ADVANCED_MACHINES);
		wonder_miner_android_i.register(this);
		
		WonderMinerAndroidII wonder_miner_android_ii = new WonderMinerAndroidII(ADVANCED_MACHINES);
		wonder_miner_android_ii.register(this);
		
		WonderMinerAndroidIII wonder_miner_android_iii = new WonderMinerAndroidIII(ADVANCED_MACHINES);
		wonder_miner_android_iii.register(this);
		
		WonderMinerAndroidIV wonder_miner_android_iv = new WonderMinerAndroidIV(ADVANCED_MACHINES);
		wonder_miner_android_iv.register(this);
		
		
		//MoosHoe moos_hoes = new MoosHoe(MOOS_TOOLS);
		//moos_hoes.register(this);
		
		//PitchFork pitch_fork = new PitchFork(this, MOOS_TOOLS);
		//pitch_fork.register(this);

		
		ArrayList<Material> PITCH_FORK_WHITELIST = new ArrayList<Material>() 
		{{
			add(Material.DIRT);
			add(Material.GRASS_BLOCK);
		}};
		
		//      Research   
		//      Research   
				
		
		Research research = new Research(new NamespacedKey(this, "Moos_Suit"), 340, "Moo's Suit", 30);
		research.addItems(WonderItems.HIDE_BOOTS, WonderItems.HIDE_CHESTPLATE, WonderItems.HIDE_HELMET, WonderItems.HIDE_LEGGINGS);
		research.register();
		
		research = new Research(new NamespacedKey(this, "Oceanography"), 341, "Oceanography", 20);	
		research.addItems(WonderItems.BRAIN_CORAL_BLOCK, WonderItems.BUBBLE_CORAL_BLOCK, WonderItems.FIRE_CORAL_BLOCK, WonderItems.HORN_CORAL_BLOCK, WonderItems.TUBE_CORAL_BLOCK);
		research.register();
		
		research = new Research(new NamespacedKey(this, "Marvelous_Marble"), 342, "Marvelous Marble", 30);	
		research.addItems(WonderItems.MARBLE_BLOCK);
		research.register();
		
		research = new Research(new NamespacedKey(this, "Moos_Marvelous_Pickaxe"), 343, "Moo's Marvelous Pickaxe", 30);
		research.addItems(WonderItems.MOOS_M_PICKAXE);
		research.register();
		
		research = new Research(new NamespacedKey(this, "Concrete_Mixer"), 344, "Concrete Mixer", 35);
		research.addItems(WonderItems.MIXER);
		research.register();
		
		research = new Research(new NamespacedKey(this, "Advanced_Alloys"), 346, "Advanced Alloy's", 50);
		research.addItems(WonderItems.TITANIUM_INGOT, WonderItems.TITANIUM_DUST, WonderItems.CHROMIUM_DUST, WonderItems.CHROMIUM_INGOT, WonderItems.WONDER_SOLDER_INGOT);
		research.register();
		
		research = new Research(new NamespacedKey(this, "Misc_Hazmat"), 347, "Misc Hazmat", 35);
		research.addItems(WonderItems.WHEEL, WonderItems.SPINDEL, WonderItems.VISOR, WonderItems.HAZMAT_CLOTH);
		research.register();
		
		research = new Research(new NamespacedKey(this, "Hazmat_Resources"), 348, "Hazmat Resources", 40);
		research.addItems(WonderItems.LEAD_FIBRE, WonderItems.CARBON_FIBRE, WonderItems.RUBBER, WonderItems.CARBON_FIBRE_ROPE, WonderItems.WONDER_FIBRE, WonderItems.WONDER_WOOL);
		research.register();
			
		research = new Research(new NamespacedKey(this, "Extruder_Machine"), 349, "Extruder Machine", 35);
		research.addItems(WonderMachines.EXTRUDER);
		research.register();
		
		research = new Research(new NamespacedKey(this, "Wonder_Machine"), 350, "Wonder Machine", 55);	
		research.addItems(WonderMachines.WONDER_MACHINE);
		research.register();

		research = new Research(new NamespacedKey(this, "Purifier_Machine"), 351, "Purifier Machine", 40);	
		research.addItems(WonderMachines.PURIFIER);
		research.register();
		
		research = new Research(new NamespacedKey(this, "Elytra_Remake"), 352, "Elytra Remake", 28);	
		research.addItems(WonderItems.ELYTRA_REMAKE);
		research.register();		
		
		research = new Research(new NamespacedKey(this, "Wonder_Resource"), 353, "Wonder Resource", 45);	
		research.addItems(WonderItems.WONDER_DUST, WonderItems.WONDER_ALLOY);
		research.register();
		
		research = new Research(new NamespacedKey(this, "Resource_Blocks"), 354, "Resource Blocks", 50);
		research.addItems(WonderItems.CARBONADO_BLOCK, WonderItems.WONDER_ALLOY_BLOCK);
		research.register();
		
		research = new Research(new NamespacedKey(this, "Pitch_Fork"), 356, "Pitch Fork", 16);
		research.addItems(WonderItems.PITCH_FORK);
		research.register();
		
		research = new Research(new NamespacedKey(this, "TV"), 388, "TV", 30);
		research.addItems(WonderItems.TV);
		research.register();		
		
		research = new Research(new NamespacedKey(this, "Hi_Tech_Components"), 389, "Hi Tech Components", 30);
		research.addItems(WonderItems.ANTENNA, WonderItems.COGWHEEL, WonderItems.PRINTED_CIRCUIT_BOARD, WonderItems.POWER_PISTON);
		research.register();
		
		research = new Research(new NamespacedKey(this, "Advanced_Ingredients"), 390, "Advanced Ingredients", 30);
		research.addItems(WonderItems.YEAST, WonderItems.DOUGH, WonderItems.WONDER_PATTY, WonderItems.BUN, WonderItems.RAW_FRIES, WonderItems.ICE_CREAM);
		research.register();		
		
		research = new Research(new NamespacedKey(this, "Fryer"), 391, "Fryer", 30);	
		research.addItems(WonderItems.FRYER);
		research.register();		
		
		research = new Research(new NamespacedKey(this, "Deep_Freeze"), 392, "Deep Freeze", 30);	
		research.addItems(WonderItems.DEEP_FREEZE);
		research.register();
			
		research = new Research(new NamespacedKey(this, "Cannary"), 393, "Cannary", 30);	
		research.addItems(WonderItems.CANNARY, WonderItems.TIN_CARROTS);
		research.register();
			
		research = new Research(new NamespacedKey(this, "Toy_Car"), 394, "Toy Car", 30);	
		research.addItems(WonderItems.TOY_CAR);
		research.register();
			
		research = new Research(new NamespacedKey(this, "WonderItems.DONUT"), 395, "WonderItems.DONUT", 20);	
		research.addItems(WonderItems.DONUT);
		research.register();
				
		research = new Research(new NamespacedKey(this, "Moos_Burger"), 396, "Moo's Burger", 30);	
		research.addItems(WonderItems.MOOS_BURGER, WonderItems.MOOS_FRIES, WonderItems.MOO_SHAKE, WonderItems.MOO_MEAL);
		research.register();
		
		research = new Research(new NamespacedKey(this, "Wonder_Furnace"), 397, "Wonder Furnace", 30);
		research.addItems(WonderItems.WONDER_FURNACE);
		research.register();
		
		research = new Research(new NamespacedKey(this, "Moos_Soup"), 398, "Moo's Soup", 15);
		research.addItems(WonderItems.MOO_SOUP);
		research.register();
		
		research = new Research(new NamespacedKey(this, "Desalination_Plant"), 399, "Desalination Plant", 20);
		research.addItems(WonderItems.DESALINATION_PLANT);
		research.register();
		
		research = new Research(new NamespacedKey(this, "Moos_Pizza"), 800, "Moo's Pizza", 15);
		research.addItems(WonderItems.MOO_PIZZA);
		research.register();
		
		research = new Research(new NamespacedKey(this, "Record_Player"), 801, "Record Player", 30);
		research.addItems(WonderItems.RECORD_PLAYER);
		research.register();
		
		research = new Research(new NamespacedKey(this, "Steps_Discography"), 802, "Steps Discography", 10);
		research.addItems(WonderItems.STEPS_DISCOGRAPHY);
		research.register();
		
		research = new Research(new NamespacedKey(this, "Moos_Armor"), 803, "Moo's Armor", 40);
		research.addItems(WonderItems.MOOS_KILT, WonderItems.MOOS_CAP, WonderItems.MOOS_BOOTS, WonderItems.MOOS_TUNIC);
		research.register();
		
		research = new Research(new NamespacedKey(this, "SUPER_TNT"), 804, "Super TNT", 40);
		research.addItems(WonderItems.SUPER_TNT);
		research.register();
		
		research = new Research(new NamespacedKey(this, "ALCOHOL"), 805, "Alcohol", 20);
		research.addItems(WonderItems.ALCOHOL);
		research.register();
		
		research = new Research(new NamespacedKey(this, "Hydropress"), 806, "Hydropress", 40);
		research.addItems(WonderMachines.ELECTRIC_HYDROPRESS);
		research.register();
		
		research = new Research(new NamespacedKey(this, "SULFURIC_ACID"), 807, "Sulfuric Acid", 20);
		research.addItems(WonderItems.SULFURIC_ACID);
		research.register();
		
		research = new Research(new NamespacedKey(this, "CALCIUM"), 808, "Calcium", 20);
		research.addItems(WonderItems.CALCIUM);
		research.register();
		
		research = new Research(new NamespacedKey(this, "EGG_SHELL"), 809, "Egg shell", 20);		
		research.addItems(WonderItems.EGG_SHELL);
		research.register();
		
		research = new Research(new NamespacedKey(this, "DNA"), 810, "DNA", 30);	
		research.addItems(WonderItems.DNA);
		research.register();
		
		research = new Research(new NamespacedKey(this, "COW_ESSENCE"), 811, "Cow essence", 20);	
		research.addItems(WonderItems.COW_ESSENCE);
		research.register();
	
		research = new Research(new NamespacedKey(this, "PIG_ESSENCE"), 812, "Pig essence", 20);	
		research.addItems(WonderItems.PIG_ESSENCE);
		research.register();
		
		research = new Research(new NamespacedKey(this, "SHEEP_ESSENCE"), 813, "Sheep essence", 20);	
		research.addItems(WonderItems.SHEEP_ESSENCE);
		research.register();
		
		research = new Research(new NamespacedKey(this, "WOLF_ESSENCE"), 814, "Wolf essence", 20);	
		research.addItems(WonderItems.WOLF_ESSENCE);
		research.register();
		
		research = new Research(new NamespacedKey(this, "SQUID_ESSENCE"), 815, "Squid essence", 20);	
		research.addItems(WonderItems.SQUID_ESSENCE);
		research.register();
		
		research = new Research(new NamespacedKey(this, "BEE_ESSENCE"), 816, "Bee essence", 20);	
		research.addItems(WonderItems.BEE_ESSENCE);
		research.register();
		
		research = new Research(new NamespacedKey(this, "VILLAGER_ESSENCE"), 817, "Villager essence", 20);
		research.addItems(WonderItems.VILLAGER_ESSENCE);
		research.register();
		
		research = new Research(new NamespacedKey(this, "MOOSHROOM_ESSENCE"), 818, "Mooshroom essence", 20);	
		research.addItems(WonderItems.MOOSHROOM_ESSENCE);
		research.register();
		
		research = new Research(new NamespacedKey(this, "PANDA_ESSENCE"), 819, "Panda essence", 20);	
		research.addItems(WonderItems.PANDA_ESSENCE);
		research.register();
		
		research = new Research(new NamespacedKey(this, "HORSE_ESSENCE"), 820, "Horse essence", 20);	
		research.addItems(WonderItems.HORSE_ESSENCE);
		research.register();
		
		research = new Research(new NamespacedKey(this, "ANIMAL_ESSENCES"), 820, "Animal essence", 20);	
		research.addItems(WonderItems.DOLPHIN_ESSENCE, WonderItems.FOX_ESSENCE, WonderItems.TURTLE_ESSENCE, WonderItems.PARROT_ESSENCE, WonderItems.RABBIT_ESSENCE, WonderItems.POLAR_BEAR_ESSENCE, WonderItems.GUARDIAN_ESSENCE, WonderItems.LLAMA_ESSENCE, WonderItems.SLIME_ESSENCE);
		research.register();
		
		research = new Research(new NamespacedKey(this, "REANIMATOR"), 821, "Reanimator", 30);
		research.addItems(WonderItems.REANIMATOR);
		research.register();
		
		research = new Research(new NamespacedKey(this, "LIFE_EGGS"), 822, "Life eggs", 20);	
		research.addItems(WonderItems.COW_SPAWN_EGG, WonderItems.PIG_SPAWN_EGG, WonderItems.SHEEP_SPAWN_EGG, WonderItems.WOLF_SPAWN_EGG, WonderItems.SQUID_SPAWN_EGG, WonderItems.BEE_SPAWN_EGG, WonderItems.VILLAGER_SPAWN_EGG, WonderItems.MOOSHROOM_SPAWN_EGG, WonderItems.PANDA_SPAWN_EGG, WonderItems.HORSE_SPAWN_EGG, WonderItems.DOLPHIN_SPAWN_EGG, WonderItems.TURTLE_SPAWN_EGG, WonderItems.PARROT_SPAWN_EGG, WonderItems.RABBIT_SPAWN_EGG, WonderItems.POLAR_BEAR_SPAWN_EGG, WonderItems.GUARDIAN_SPAWN_EGG, WonderItems.LLAMA_SPAWN_EGG, WonderItems.SLIME_SPAWN_EGG);
		research.register();
		
		//research = new Research(new NamespacedKey(this, "SCHNAPPS"), 823, "Schnapps", 20);	
		//research.addItems(apple_schnapps.getItem(), berry_schnapps.getItem(), miduri.getItem());
		//research.register();
		
		research = new Research(new NamespacedKey(this, "NETHER_MAKER"), 824, "Nether Maker", 30);	
		research.addItems(WonderItems.NETHER_MAKER);
		research.register();
		
		research = new Research(new NamespacedKey(this, "SMALL_HADRON_COLLIDER"), 825, "Small Hadron Collider", 30);	
		research.addItems(WonderItems.SMALL_HADRON_COLLIDER);
		research.register();	
		
		research = new Research(new NamespacedKey(this, "MOOINIUM"), 826, "MOOINIUM", 30);	
		research.addItems(WonderItems.MOOINIUM);
		research.register();	
		
		research = new Research(new NamespacedKey(this, "CARBON_COLLECTOR"), 827, "Carbon Collector", 30);	
		research.addItems(WonderItems.CARBON_COLLECTOR);
		research.register();	
		
		research = new Research(new NamespacedKey(this, "ELECTRIC_RECYCLER"), 828, "Electric Recycler", 30);	
		research.addItems(WonderItems.ELECTRIC_RECYCLER);
		research.register();	
		
		research = new Research(new NamespacedKey(this, "PENCIL"), 829, "Pencil", 15);	
		research.addItems(WonderItems.PENCIL);
		research.register();	
		
		research = new Research(new NamespacedKey(this, "DUBNIUM"), 830, "DUBNIUM", 15);	
		research.addItems(WonderItems.DUBNIUM);
		research.register();
		
		research = new Research(new NamespacedKey(this, "WONDER_MINER_ANDROID_I"), 831, "Wonder Miner Android I", 30);	
		research.addItems(WonderItems.WONDER_MINER_ANDROID_I);
		research.register();	
		
		research = new Research(new NamespacedKey(this, "WONDER_MINER_ANDROID_II"), 832, "Wonder Miner Android II", 30);	
		research.addItems(WonderItems.WONDER_MINER_ANDROID_II);
		research.register();
		
		research = new Research(new NamespacedKey(this, "WONDER_MINER_ANDROID_III"), 833, "Wonder Miner Android III", 30);	
		research.addItems(WonderItems.WONDER_MINER_ANDROID_III);
		research.register();
		
		research = new Research(new NamespacedKey(this, "WONDER_MINER_ANDROID_IV"), 834, "Wonder Miner Android IV", 30);	
		research.addItems(WonderItems.WONDER_MINER_ANDROID_IV);
		research.register();
		
		research = new Research(new NamespacedKey(this, "POWER_CELL"), 835, "Power Cell", 30);	
		research.addItems(WonderItems.POWER_CELL);
		research.register();
		
		research = new Research(new NamespacedKey(this, "ELECTRIC_PURIFIER"), 836, "Electric Purifier", 30);	
		research.addItems(WonderItems.ELECTRIC_PURIFIER, WonderItems.ELECTRIC_PURIFIER_II);
		research.register();	
		
		//hazmat_task = new HazmatTask(hazmat_helmet, hazmat_chest, hazmat_leggings, hazmat_boots);
		//getServer().getScheduler().runTaskTimerAsynchronously(this,  new HazmatTask(WonderItems.HAZMAT_HELMET, WonderItems.HAZMATSUIT_CHESTPLATE, WonderItems.HAZMATSUIT_LEGGINGS, WonderItems.HAZMAT_BOOTS), 0L, 10 * 20L);
		//hazmat_task = new HazmatTask(WonderItems.HAZMAT_HELMET, WonderItems.HAZMATSUIT_CHESTPLATE, WonderItems.HAZMATSUIT_LEGGINGS, WonderItems.HAZMAT_BOOTS);
		// Armor Update Task	
		
		String item_text;
		ItemStack[] item_stack;
		
	
		getServer().getScheduler().runTaskTimer(this, () -> 
		{		
		
			for (Player p : Bukkit.getOnlinePlayers()) 
			{
				
				for (ItemStack armor : p.getInventory().getArmorContents())
				 {
					if (armor != null) 
					{
						/*
						if (p.hasUnlocked(p, armor, true)) 
						{
							if (SlimefunItem.getByItem(armor) instanceof SlimefunArmorPiece) 
							{
								for (PotionEffect effect : ((SlimefunArmorPiece) SlimefunItem.getByItem(armor)).getPotionEffects()) //.getEffects()) 
								{
									p.removePotionEffect(effect.getType());
									p.addPotionEffect(effect);
								}
							}
							
							if (SlimefunUtils.isItemSimilar(armor, hazmat_helmet, false)) 
							{
								if (p.getWorld().getTime() < 12300 || p.getWorld().getTime() > 23850) 
								{
									if (p.getEyeLocation().getBlock().getLightFromSky() == 15)
									{
										 //ItemEnergy.chargeInventory(p, Float.valueOf(String.valueOf(Slimefun.getItemValue("HAZMAT_HELMET_2", "charge-amount"))));
										// ItemEnergy.chargeInventory(p, (float) ((SolarHelmet) hazmat_helmet.getItem()).getChargeAmount());
									}
								}
							}
						}
						*/
					}
				}

				
				for (SlimefunItem radioactive : SlimefunPlugin.getRegistry().getRadioactiveItems()) //getUtilities().radioactiveItems) 
				{
					
					Boolean is_radioactive = false;
					
					if (radioactive.isItem(p.getInventory().getItemInOffHand()))
					{
						is_radioactive = true;
					}
					else
					{
						for (ItemStack item : p.getInventory()) 
						{
							
			                if (radioactive.isItem(item))
			                {
			                	is_radioactive = true;
			                	break;
			                }
						}
					}
										
					if (is_radioactive)
					{
														
						// Check if player is wearing the hazmat suit
						// If so, break the loop
						if (SlimefunUtils.isItemSimilar(WonderItems.HAZMAT_HELMET, p.getInventory().getHelmet(), true)
							&& SlimefunUtils.isItemSimilar(WonderItems.HAZMATSUIT_CHESTPLATE,p.getInventory().getChestplate(), true)
							&& SlimefunUtils.isItemSimilar(WonderItems.HAZMATSUIT_LEGGINGS, p.getInventory().getLeggings(),true)	
							&& SlimefunUtils.isItemSimilar(WonderItems.HAZMAT_BOOTS, p.getInventory().getBoots(), true))
								
						{
							p.removePotionEffect(PotionEffectType.WITHER);
							p.removePotionEffect(PotionEffectType.BLINDNESS);
							p.removePotionEffect(PotionEffectType.CONFUSION);
							p.removePotionEffect(PotionEffectType.WEAKNESS);
							p.removePotionEffect(PotionEffectType.SLOW);
							p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
							p.setFireTicks(0);
						
							break;
							
						}
	
						else
						{
							/*
							// If the item is enabled in the world, then make radioactivity do its job
							if (Slimefun.isEnabled(p, radioactive, true)) 
							{	
								SlimefunPlugin.getLocalization().sendMessage(p, "messages.radiation");
								p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 6));
								p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 6));
								p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 6));
								p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 6));
								p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 1));
								p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 200, 1));
								p.setFireTicks(200);
								break; // Break the loop to save some calculations
							}
							*/
						}
						
					}
				}
				
			}
						
		}, 0L, 10 * 20L);

	

		console.sendMessage("Wondercraft Extra Slimefun Addon's Loaded...");
		console.sendMessage("---------------------------------------------");
	}
	
	static void load_machines_1()
	{
	
	}
	
	public static Oven getOven() {
		return oven;
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
