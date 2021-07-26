package tech.obliviondevelop.SF;

import java.util.Collection;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.events.AndroidMineEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import io.github.thebusybiscuit.slimefun4.implementation.items.androids.AndroidInstance;
import io.github.thebusybiscuit.slimefun4.implementation.items.androids.AndroidType;
import io.github.thebusybiscuit.slimefun4.implementation.items.androids.ProgrammableAndroid;
import io.github.thebusybiscuit.slimefun4.utils.InfiniteBlockGenerator;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.cscorelib2.protection.ProtectableAction;
import tech.obliviondevelop.SF.Lists.WonderItems;

public class WonderMinerAndroidIV extends ProgrammableAndroid {

    // Determines the drops a miner android will get
    private final ItemStack effectivePickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
    private final ItemSetting<Boolean> firesEvent = new ItemSetting<>(this, "trigger-event-for-generators", false);
    private final ItemSetting<Boolean> applyOptimizations = new ItemSetting<>(this, "reduced-block-updates", true);
 
	public WonderMinerAndroidIV(Category category) {
        super(category, 
        		1,
				new SlimefunItemStack("WONDER_MINER_ANDROID_IV", WonderItems.WONDER_MINER_ANDROID_IV), 
				RecipeType.ENHANCED_CRAFTING_TABLE, 
				new ItemStack[] {WonderItems.POWER_CELL, WonderItems.PRINTED_CIRCUIT_BOARD, SlimefunItems.ANDROID_MEMORY_CORE, WonderItems.CHROMIUM_INGOT, WonderItems.WONDER_MINER_ANDROID_III, WonderItems.CHROMIUM_INGOT, WonderItems.WONDER_ALLOY, new ItemStack(Material.DIAMOND_PICKAXE), WonderItems.WONDER_ALLOY}
				);
        
	}

    @Override
    public AndroidType getAndroidType() {
        return AndroidType.MINER;
    }

    @Override
    protected void dig(Block b, BlockMenu menu, Block block) {
        Collection<ItemStack> drops = block.getDrops(effectivePickaxe);

        if (!SlimefunTag.UNBREAKABLE_MATERIALS.isTagged(block.getType()) && !drops.isEmpty()) {
            OfflinePlayer owner = Bukkit.getOfflinePlayer(UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner")));

            if (SlimefunPlugin.getProtectionManager().hasPermission(owner, block.getLocation(), ProtectableAction.BREAK_BLOCK)) {
                AndroidMineEvent event = new AndroidMineEvent(block, new AndroidInstance(this, b));
                Bukkit.getPluginManager().callEvent(event);

                if (event.isCancelled()) {
                    return;
                }

                // We only want to break non-Slimefun blocks
                if (!BlockStorage.hasBlockInfo(block)) {
                    block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block.getType());
                    breakBlock(menu, drops, block);
                }
            }
        }
    }

    protected void moveAndDig(Block b, BlockMenu menu, BlockFace face, Block block) {
        Collection<ItemStack> drops = block.getDrops(effectivePickaxe);

        if (!SlimefunTag.UNBREAKABLE_MATERIALS.isTagged(block.getType()) && !drops.isEmpty()) {
            OfflinePlayer owner = Bukkit.getOfflinePlayer(UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner")));

            if (SlimefunPlugin.getProtectionManager().hasPermission(owner, block.getLocation(), ProtectableAction.BREAK_BLOCK)) {
                AndroidMineEvent event = new AndroidMineEvent(block, new AndroidInstance(this, b));
                Bukkit.getPluginManager().callEvent(event);

                if (event.isCancelled()) {
                    return;
                }

                // We only want to break non-Slimefun blocks
                if (!BlockStorage.hasBlockInfo(block)) {
                    block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block.getType());
                    breakBlock(menu, drops, block);
                    move(b, face, block);
                }
            } else {
                move(b, face, block);
            }
        } else {
            move(b, face, block);
        }
    }

	
  
    private void breakBlock(BlockMenu menu, Collection<ItemStack> drops, Block block) 
    {
        // Push our drops to the inventory
        for (ItemStack drop : drops) 
        {
        	for(int i=0;i<5;i++)
        	{
        		menu.pushItem(drop.clone(), getOutputSlots());
        	}
        }

        // Check if Block Generator optimizations should be applied.
        if (applyOptimizations.getValue()) {
            InfiniteBlockGenerator generator = InfiniteBlockGenerator.findAt(block);

            // If we found a generator, continue.
            if (generator != null) {
                if (firesEvent.getValue()) {
                    generator.callEvent(block);
                }

                // "poof" a "new" block was generated
                block.getWorld().playSound(block.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 0.075F, 0.8F);
                block.getWorld().spawnParticle(Particle.SMOKE_NORMAL, block.getX() + 0.5, block.getY() + 1.25, block.getZ() + 0.5, 8, 0.5, 0.5, 0.5, 0.015);
            } else {
                block.setType(Material.AIR);
            }
        } else {
            block.setType(Material.AIR);
        }
    }
    
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
