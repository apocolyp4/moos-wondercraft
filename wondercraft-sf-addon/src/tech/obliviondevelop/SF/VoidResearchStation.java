package tech.obliviondevelop.SF;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import tech.obliviondevelop.SF.Lists.WonderItems;
import tech.obliviondevelop.SF.Schematic.Schematic;

public class VoidResearchStation extends SlimefunItem {

	//private static final int[] border1 = {0, 1, 2, 9, 11, 18, 19, 20};
    //private static final int[] border2 = {3, 5, 12, 13, 14, 21, 23};
    private static final int[] border1 = {18, 19, 20, 21, 23, 24, 25, 26};
	
    private int level = 0;
    private int page = 1;
    
	public VoidResearchStation(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, name, recipeType, recipe);
		
		new BlockMenuPreset(name, getInventoryTitle()) {

            @Override
            public void init() {
                constructMenu(this);
            }
            
            /*
            @Override
            public void newInstance(final BlockMenu menu, final Block b) {

                registerEvent((slot, prev, next) -> {
                	 //updateBarrel(b);
                     return next;
                });

                
                
                if (BlockStorage.getLocationInfo(b.getLocation(), "storedItems") == null) {
                    menu.replaceExistingItem(4, new CustomItem(new ItemStack(Material.BARRIER), "&7Empty"), false);
                    menu.replaceExistingItem(22, new CustomItem(new ItemStack(Material.BARRIER), "&7Empty"), false);
                }

            }
            
            */
            @Override
            public boolean canOpen(Block b, Player p) {
                //boolean protect = BlockStorage.getLocationInfo(b.getLocation(), "protected") == null || BlockStorage.getLocationInfo(b.getLocation(), "owner").equals(p.getUniqueId().toString()) || (BlockStorage.getLocationInfo(b.getLocation(), "whitelist") != null && BlockStorage.getLocationInfo(b.getLocation(), "whitelist").contains(p.getUniqueId().toString()));
            	boolean open = false;
            	//if (!Slimefun.hasUnlocked(p, WonderItems.VOID_RESEARCH_STATION, true))
            	//	return false;
            	level = checkResearchStationLevel(b);
            	if (level < 1) {
            		p.sendMessage("&cResearch Station Invalid!");
            		return false;
            	}
            	if (p.hasPermission("slimefun.inventory.bypass"))
            		open = true;
            	if (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true))
            		open = true;
            	
                return open;
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow itemTransportFlow) {
                return new int[0];
            }

            public int[] getSlotsAccessedByItemTransport(BlockMenu menu, ItemTransportFlow flow, ItemStack item) {
                return new int[0];
            }
		};
	}
	
	public int checkResearchStationLevel(Block b) {
		Location base = b.getLocation();
		if (compareToSchematic(b.getLocation(), "VoidResearchStation")) {
			return 1;
		}
		
		return 0;
	}
	
	public boolean compareToSchematic(Location loc, String schematic) {
		Schematic schem = new Schematic(schematic, loc);
		schem.compareSchamatic();
		/*
		if (schem.compareSchematic(loc))
			return true;
			*/
		return false;
	}
	
	public String getInventoryTitle() {
        return "&cVoid &6Research Station";
    }
    
    private void constructMenu(final BlockMenuPreset preset) {
        for (int i : border1) {
            preset.addItem(i, new CustomItem(Material.GRAY_STAINED_GLASS_PANE, " "), (Player player, int j, ItemStack itemStack, ClickAction clickAction) -> {
            	
            	return false;
            });
        }

       // preset.addItem(22, WonderItems.UNOBTANIUM,(Player player, int i, ItemStack itemStack, ClickAction clickAction) -> false);
    }
}
