package tech.obliviondevelop.SF;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;

import io.github.thebusybiscuit.slimefun4.core.handlers.EntityKillHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;

import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import tech.obliviondevelop.SF.Lists.WonderItems;

public class MoosClaymore extends SimpleSlimefunItem<EntityKillHandler> implements Listener 
{
  
	/*
    //private final ItemSetting<Integer> chance = new ItemSetting<>("chance", 45);
    private final ItemSetting<Integer> chanceZombie; = new ItemSetting<>("chance.ZOMBIE", 70);
    private final ItemSetting<Integer> chanceSkeleton; = new ItemSetting<>("chance.SKELETON", 40);
    private final ItemSetting<Integer> chanceCreeper; = new ItemSetting<>("chance.CREEPER", 25);
    private final ItemSetting<Integer> chanceWitherSkeleton; = new ItemSetting<>("chance.WITHER_SKELETON", 40);
    private final ItemSetting<Integer> chancePlayer = new ItemSetting<>("chance.PLAYERE", 40);
*/
	
    public MoosClaymore(Category category) 
    {
        super(category, 
				new SlimefunItemStack("MOOS_CLAYMORE", WonderItems.MOOS_CLAYMORE), 
				RecipeType.ENHANCED_CRAFTING_TABLE, 
				new ItemStack[] {null, new ItemStack(Material.BEDROCK), null, null, new ItemStack(Material.BEDROCK), null, null, new ItemStack(Material.BEDROCK), null});
        
        getItem().addUnsafeEnchantment(Enchantment.DURABILITY, 15);
        getItem().addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
        getItem().addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 12);
        
        //addItemSetting(chance);
        /*
        addItemSetting(chanceZombie);
        addItemSetting(chanceSkeleton);
        addItemSetting(chanceCreeper);
        addItemSetting(chanceWitherSkeleton);
        addItemSetting(chancePlayer);
        */

    }
    

    @Override
    public EntityKillHandler getItemHandler() 
    { 
    	
        return (e, entity, killer, item) -> {
            Random random = ThreadLocalRandom.current();
            /*
            if (e.getEntity() instanceof Zombie) {
                if (random.nextInt(100) < chanceZombie.getValue()) {
                    e.getDrops().add(new ItemStack(Material.ZOMBIE_HEAD));
                }
            }
            else if (e.getEntity() instanceof WitherSkeleton) {
                if (random.nextInt(100) < chanceWitherSkeleton.getValue()) {
                    e.getDrops().add(new ItemStack(Material.WITHER_SKELETON_SKULL));
                }
            }
            else if (e.getEntity() instanceof Skeleton) {
                if (random.nextInt(100) < chanceSkeleton.getValue()) {
                    e.getDrops().add(new ItemStack(Material.SKELETON_SKULL));
                }
            }
            else if (e.getEntity() instanceof Creeper) {
                if (random.nextInt(100) < chanceCreeper.getValue()) {
                    e.getDrops().add(new ItemStack(Material.CREEPER_HEAD));
                }
            }
            else if (e.getEntity() instanceof Player && random.nextInt(100) < chancePlayer.getValue())
            {
                ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                ItemMeta meta = skull.getItemMeta();
                ((SkullMeta) meta).setOwningPlayer((Player) e.getEntity());
                skull.setItemMeta(meta);

                e.getDrops().add(skull);
            }
            */
        };
        
    }

    @Override
    public void postRegister() {

    }
    
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent e) 
    {

        if (e.getDamager() instanceof Player && ThreadLocalRandom.current().nextInt(100) < 5) 
        {
            Player p = (Player) e.getDamager();

            if (SlimefunUtils.isItemSimilar(p.getInventory().getItemInMainHand(), this.getItem(), true)) 
            {
                p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 0.7F, 0.7F);
                p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1));
            }
        }
        
        
    }

}

