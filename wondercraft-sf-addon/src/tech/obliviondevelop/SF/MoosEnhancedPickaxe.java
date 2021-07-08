package tech.obliviondevelop.SF;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import io.github.thebusybiscuit.slimefun4.implementation.items.tools.ExplosivePickaxe;

import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.cscorelib2.protection.ProtectableAction;
import tech.obliviondevelop.SF.Lists.WonderItems;

public class MoosEnhancedPickaxe extends SimpleSlimefunItem<ToolUseHandler> implements NotPlaceable, DamageableItem
{

	/*
    private final ItemSetting<Boolean> damageOnUse = new ItemSetting<>("damage-on-use", true);
    private final ItemSetting<Boolean> callExplosionEvent = new ItemSetting<>("call-explosion-event", false);
	*/

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
    
    
    @Override
    public ToolUseHandler getItemHandler() {
        return (e, tool, fortune, drops) -> {
            Player p = e.getPlayer();
            Block b = e.getBlock();

            b.getWorld().createExplosion(b.getLocation(), 0.0F);
            b.getWorld().playSound(b.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.2F, 1F);

            List<Block> blocks = findBlocks(b);
            breakBlocks(p, tool, b, blocks, fortune, drops);
        };
    }

    private void breakBlocks(Player p, ItemStack item, Block b, List<Block> blocks, int fortune, List<ItemStack> drops) 
    {
    	/*
        if (callExplosionEvent.getValue().booleanValue()) {
            BlockExplodeEvent blockExplodeEvent = new BlockExplodeEvent(b, blocks, 0);
            Bukkit.getServer().getPluginManager().callEvent(blockExplodeEvent);

            if (!blockExplodeEvent.isCancelled()) {
                for (Block block : blockExplodeEvent.blockList()) {
                    if (canBreak(p, block)) {
                        breakBlock(p, item, block, fortune, drops);
                    }
                }
            }
        }
        else {
            for (Block block : blocks) {
                if (canBreak(p, block)) {
                    breakBlock(p, item, block, fortune, drops);
                }
            }
        }
        */
    }

    private List<Block> findBlocks(Block b) {
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
    /*
    @Override
    public boolean isDamageable() {
        return damageOnUse.getValue();
    }

    protected boolean canBreak(Player p, Block b) {
        if (b.isEmpty() || b.isLiquid()) {
            return false;
        }
        else if (MaterialCollections.getAllUnbreakableBlocks().contains(b.getType())) {
            return false;
        }
        else if (!b.getWorld().getWorldBorder().isInside(b.getLocation())) {
            return false;
        }
        else {
            return SlimefunPlugin.getProtectionManager().hasPermission(p, b.getLocation(), ProtectableAction.BREAK_BLOCK);
        }
    }

    private void breakBlock(Player p, ItemStack item, Block b, int fortune, List<ItemStack> drops) {
        SlimefunPlugin.getProtectionManager().logAction(p, b, ProtectableAction.BREAK_BLOCK);

        b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, b.getType());
        SlimefunItem sfItem = BlockStorage.check(b);

        if (sfItem != null && !sfItem.useVanillaBlockBreaking()) {
            SlimefunBlockHandler handler = SlimefunPlugin.getRegistry().getBlockHandlers().get(sfItem.getID());

            if (handler != null && !handler.onBreak(p, b, sfItem, UnregisterReason.PLAYER_BREAK)) {
                drops.add(BlockStorage.retrieve(b));
            }
        }
        else if (b.getType() == Material.PLAYER_HEAD || b.getType() == Material.SHULKER_BOX || b.getType().name().endsWith("_SHULKER_BOX")) {
            b.breakNaturally(item);
        }
        else {
            boolean applyFortune = b.getType().name().endsWith("_ORE") && b.getType() != Material.IRON_ORE && b.getType() != Material.GOLD_ORE;

            for (ItemStack drop : b.getDrops(getItem())) {
                // For some reason this check is necessary with Paper
                if (drop != null && drop.getType() != Material.AIR) {
                    b.getWorld().dropItemNaturally(b.getLocation(), applyFortune ? new CustomItem(drop, fortune) : drop);
                }
            }

            b.setType(Material.AIR);
        }

       //damageItem(p, item);
    }
    */


	@Override
	public boolean isDamageable() {
		// TODO Auto-generated method stub
		return false;
	}

}