package tech.obliviondevelop.SF;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.tools.ExplosiveTool;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.protection.ProtectableAction;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import tech.obliviondevelop.SF.Lists.WonderItems;

public class MoosEnhancedPickaxe extends SimpleSlimefunItem<ToolUseHandler> implements NotPlaceable, DamageableItem
{

    private final ItemSetting<Boolean> damageOnUse = new ItemSetting<>(this, "damage-on-use", true);
    private final ItemSetting<Boolean> callExplosionEvent = new ItemSetting<>(this, "call-explosion-event", false);

    public MoosEnhancedPickaxe(Category category) 
    {
        super(category, 
				new SlimefunItemStack("MOOS_ENHANCED_M_PICKAXE", WonderItems.MOOS_ENHANCED_M_PICKAXE), 
				RecipeType.MAGIC_WORKBENCH,
				new ItemStack[] 
				{
					WonderItems.HARDENED_NETHERITE_INGOT, WonderItems.MOOS_M_PICKAXE, WonderItems.HARDENED_NETHERITE_INGOT,
					null, WonderItems.MARBLE_BLOCK, null, 
					null, WonderItems.MARBLE_BLOCK, null
					
				}
        
				);
        getItem().addUnsafeEnchantment(Enchantment.DURABILITY, 15);
        //addItemSetting(damageOnUse, callExplosionEvent);
    }
    
    @Nonnull
    @Override
    public ToolUseHandler getItemHandler() {
        return (e, tool, fortune, drops) -> {
            Player p = e.getPlayer();
            Block b = e.getBlock();

            b.getWorld().createExplosion(b.getLocation(), 0);
            b.getWorld().playSound(b.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.2F, 1F);

            List<Block> blocks = findBlocks(b);
            breakBlocks(e, p, tool, b, blocks, drops);
        };
    }

    @ParametersAreNonnullByDefault
    private void breakBlocks(BlockBreakEvent e, Player p, ItemStack item, Block b, List<Block> blocks, List<ItemStack> drops) {
        List<Block> blocksToDestroy = new ArrayList<>();

        if (callExplosionEvent.getValue().booleanValue()) {
            BlockExplodeEvent blockExplodeEvent = new BlockExplodeEvent(b, blocks, 0);
            Bukkit.getServer().getPluginManager().callEvent(blockExplodeEvent);

            if (!blockExplodeEvent.isCancelled()) {
                for (Block block : blockExplodeEvent.blockList()) {
                    if (canBreak(p, block)) {
                        blocksToDestroy.add(block);
                    }
                }
            }
        } else {
            for (Block block : blocks) {
                if (canBreak(p, block)) {
                    blocksToDestroy.add(block);
                }
            }
        }

        ExplosiveToolBreakBlocksEventPickAxeE event = new ExplosiveToolBreakBlocksEventPickAxeE(p, b, blocksToDestroy, item, this);
        Bukkit.getServer().getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            for (Block block : blocksToDestroy) {
                breakBlock(e, p, item, block, drops);
            }
        }
    }

    @Nonnull
    private List<Block> findBlocks(@Nonnull Block b) {
        List<Block> blocks = new ArrayList<>(26);

        for (int x = -2; x <= 2; x++) {
            for (int y = -2; y <= 2; y++) {
                for (int z = -2; z <= 2; z++) {
                    // We can skip the center block since that will break as usual
                    if (x == 0 && y == 0 && z == 0) {
                        continue;
                    }

                    blocks.add(b.getRelative(x, y, z));
                }
            }
        }

        return blocks;
    }

    @Override
    public boolean isDamageable() {
        return damageOnUse.getValue();
    }

    protected boolean canBreak(@Nonnull Player p, @Nonnull Block b) {
        if (b.isEmpty() || b.isLiquid()) {
            return false;
        } else if (SlimefunTag.UNBREAKABLE_MATERIALS.isTagged(b.getType())) {
            return false;
        } else if (!b.getWorld().getWorldBorder().isInside(b.getLocation())) {
            return false;
        } else if (SlimefunPlugin.getIntegrations().isCustomBlock(b)) {
            return false;
        } else {
            return SlimefunPlugin.getProtectionManager().hasPermission(p, b.getLocation(), ProtectableAction.BREAK_BLOCK);
        }
    }

    @ParametersAreNonnullByDefault
    private void breakBlock(BlockBreakEvent e, Player p, ItemStack item, Block b, List<ItemStack> drops) {
        SlimefunPlugin.getProtectionManager().logAction(p, b, ProtectableAction.BREAK_BLOCK);
        Material material = b.getType();

        b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, material);
        SlimefunItem sfItem = BlockStorage.check(b);

        if (sfItem != null && !sfItem.useVanillaBlockBreaking()) {
            /*
             * Fixes #2989
             * We create a dummy here to pass onto the BlockBreakHandler.
             * This will set the correct block context.
             */
            BlockBreakEvent dummyEvent = new BlockBreakEvent(b, e.getPlayer());

            /*
             * Fixes #3036 and handling in general.
             * Call the BlockBreakHandler if the block has one to allow for proper handling.
             */
            sfItem.callItemHandler(BlockBreakHandler.class, handler -> handler.onPlayerBreak(dummyEvent, item, drops));

            // Make sure the event wasn't cancelled by the BlockBreakHandler.
            if (!dummyEvent.isCancelled()) {
                drops.addAll(sfItem.getDrops(p));
                b.setType(Material.AIR);
                BlockStorage.clearBlockInfo(b);
            }
        } else {
            b.breakNaturally(item);
        }

        damageItem(p, item);
    }

}
