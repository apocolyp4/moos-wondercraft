package tech.obliviondevelop.SF;

import java.util.Collection;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.events.AndroidMineEvent;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import io.github.thebusybiscuit.slimefun4.implementation.items.androids.AndroidInstance;
import io.github.thebusybiscuit.slimefun4.implementation.items.androids.AndroidType;
import io.github.thebusybiscuit.slimefun4.implementation.items.androids.ProgrammableAndroid;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.cscorelib2.protection.ProtectableAction;
import tech.obliviondevelop.SF.Lists.WonderItems;

public class WonderMinerAndroidI extends ProgrammableAndroid 
{

    // Determines the drops a miner android will get
    private final ItemStack effectivePickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
 
	public WonderMinerAndroidI(Category category) {
        super(category,
        		1,
				new SlimefunItemStack("WONDER_MINER_ANDROID_I", WonderItems.WONDER_MINER_ANDROID_I), 
				RecipeType.ENHANCED_CRAFTING_TABLE, 
				new ItemStack[] {SlimefunItems.POWER_CRYSTAL, WonderItems.PRINTED_CIRCUIT_BOARD, SlimefunItems.ANDROID_MEMORY_CORE, WonderItems.CHROMIUM_INGOT, SlimefunItems.PROGRAMMABLE_ANDROID_MINER, WonderItems.CHROMIUM_INGOT, WonderItems.WONDER_ALLOY, new ItemStack(Material.DIAMOND_PICKAXE), WonderItems.WONDER_ALLOY}
				);
        
	}

	
    @Override
    public AndroidType getAndroidType() {
        return AndroidType.MINER;
    }

    @Override
    protected void dig(Block b, BlockMenu menu, Block block) {
        Collection<ItemStack> drops = block.getDrops(effectivePickaxe);

        if (block.getType() == Material.COBBLESTONE || block.getType() == Material.STONE && !drops.isEmpty() && SlimefunPlugin.getProtectionManager().hasPermission(Bukkit.getOfflinePlayer(UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner"))), block.getLocation(), ProtectableAction.BREAK_BLOCK)) {
            String item = BlockStorage.checkID(block);
            
            AndroidMineEvent event = new AndroidMineEvent(block, new AndroidInstance(this, b));
            Bukkit.getPluginManager().callEvent(event);

            if (event.isCancelled()) {
                return;
            }


            // We only want to break non-Slimefun blocks
            if (item == null) {
                for (ItemStack drop : drops) {
                    if (menu.fits(drop, getOutputSlots())) {
                    	for(int i=0;i<2;i++)
                    	{
                        menu.pushItem(drop, getOutputSlots());
                    	}
                        block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block.getType());
                        block.setType(Material.AIR);
                    }
                }
            }
        }
    }

    /*
    @Override
    protected void moveAndDig(Block b, BlockMenu menu, BlockFace face, Block block) 
    {
        Collection<ItemStack> drops = block.getDrops(effectivePickaxe);

        if (!MaterialCollections.getAllUnbreakableBlocks().contains(block.getType()) && !drops.isEmpty() && SlimefunPlugin.getProtectionManager().hasPermission(Bukkit.getOfflinePlayer(UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner"))), block.getLocation(), ProtectableAction.BREAK_BLOCK)) {
            SlimefunItem item = BlockStorage.check(block);

            AndroidMineEvent event = new AndroidMineEvent(block, new AndroidInstance(this, b));
            Bukkit.getPluginManager().callEvent(event);

            if (event.isCancelled()) {
                return;
            }

            // We only want to break non-Slimefun blocks
            if (item == null) {
                for (ItemStack drop : drops) {
                    if (menu.fits(drop, getOutputSlots())) {
                    	for(int i=0;i<2;i++)
                    	{
                        menu.pushItem(drop, getOutputSlots());
                    	}
                        block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block.getType());

                        block.setType(Material.AIR);
                        move(b, face, block);

                        b.setType(Material.AIR);
                        BlockStorage.moveBlockInfo(b.getLocation(), block.getLocation());
                    }
                }
            }
        }
        else {
            move(b, face, block);
        }
    }

	*/
	public float getFuelEfficiency() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getTier() {
		// TODO Auto-generated method stub
		return 1;
	}
}
