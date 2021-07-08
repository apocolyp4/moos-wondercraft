package tech.obliviondevelop.SF;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.thebusybiscuit.slimefun4.api.items.HashedArmorpiece;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactive;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import io.github.thebusybiscuit.slimefun4.implementation.items.armor.SlimefunArmorPiece;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.gadgets.SolarHelmet;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
//import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class HazmatTask implements Runnable 
{

    private final Set<PotionEffect> radiationEffects;
    private ItemStack hazmat_helmet;
    private ItemStack hazmat_chest;
    private ItemStack hazmat_boots;
    private ItemStack hazmat_leggings;
    
	public ConsoleCommandSender console;
	

    public HazmatTask(ItemStack helmet, ItemStack chest, ItemStack leggings, ItemStack boots) 
    {
    	
        Set<PotionEffect> effects = new HashSet<>();
        effects.add(new PotionEffect(PotionEffectType.WITHER, 400, 2));
        effects.add(new PotionEffect(PotionEffectType.BLINDNESS, 400, 3));
        effects.add(new PotionEffect(PotionEffectType.CONFUSION, 400, 3));
        effects.add(new PotionEffect(PotionEffectType.WEAKNESS, 400, 2));
        effects.add(new PotionEffect(PotionEffectType.SLOW, 400, 1));
        effects.add(new PotionEffect(PotionEffectType.SLOW_DIGGING, 400, 1));
        radiationEffects = Collections.unmodifiableSet(effects);

        hazmat_helmet = helmet;	
        hazmat_chest = chest;
        hazmat_leggings = leggings;
        hazmat_boots = boots;

    }

    @Override
    public void run() 
    {
    	
    	
        for (Player p : Bukkit.getOnlinePlayers()) 
        {
            if (!p.isValid() || p.isDead()) 
            {
                continue;
            }

            PlayerProfile.get(p, profile ->
            {
            	
                ItemStack[] armor = p.getInventory().getArmorContents();
                HashedArmorpiece[] cachedArmor = profile.getArmor();

                handleSlimefunArmor(p, armor, cachedArmor);
                checkForHazardHelmet(p);
                checkForRadiation(p);
            });
        }
    }

    private void handleSlimefunArmor(Player p, ItemStack[] armor, HashedArmorpiece[] cachedArmor) 
    {
    	/*
        for (int slot = 0; slot < 4; slot++) {
            ItemStack item = armor[slot];
            HashedArmorpiece armorpiece = cachedArmor[slot];

            if (armorpiece.hasDiverged(item)) {
                SlimefunItem sfItem = SlimefunItem.getByItem(item);
                if (!(sfItem instanceof SlimefunArmorPiece) || !Slimefun.hasUnlocked(p, sfItem, true)) 
                {
                    sfItem = null;
                }

                armorpiece.update(item, sfItem);
            }

            if (item != null && armorpiece.getItem().isPresent()) {
                Slimefun.runSync(() -> {
                    for (PotionEffect effect : armorpiece.getItem().get().getPotionEffects()) {
                        p.removePotionEffect(effect.getType());
                        p.addPotionEffect(effect);
                    }
                });
            }
        }
        */
    }

    private void checkForHazardHelmet(Player p)
    {
    	
    	  // Temporary performance improvement
        if (!SlimefunUtils.isItemSimilar(p.getInventory().getHelmet(), hazmat_helmet, true)) {
            return;
        }
    	
    	SlimefunItem item = SlimefunItem.getByItem(p.getInventory().getHelmet());
    
        if (SlimefunUtils.isItemSimilar(p.getInventory().getHelmet(), hazmat_helmet, true) && SlimefunUtils.canPlayerUseItem(p, hazmat_helmet, true) &&hasSunlight(p)) 
        {
        	
        	 //ItemEnergy.chargeInventory(p, (float) ((SolarHelmet) item).getChargeAmount());
        }
    }
    
    private boolean hasSunlight(Player p) {
        return (p.getWorld().getTime() < 12300 || p.getWorld().getTime() > 23850) && p.getEyeLocation().getBlock().getLightFromSky() == 15;
    }
        
    private void checkForRadiation(Player p)
    {    	
        for (ItemStack item : p.getInventory()) 
        {
            if (isRadioactive(p, item)) 
            {
                break;
            }
        }
    	
        // Check for a Hazmat Suit
		if (SlimefunUtils.isItemSimilar(hazmat_helmet, p.getInventory().getHelmet(), true) 
			&& SlimefunUtils.isItemSimilar(hazmat_chest, p.getInventory().getChestplate(), true)	
			&& SlimefunUtils.isItemSimilar(hazmat_leggings, p.getInventory().getLeggings(),true)
			&& SlimefunUtils.isItemSimilar(hazmat_boots, p.getInventory().getBoots(), true))
		
        {
			p.removePotionEffect(PotionEffectType.WITHER);
			p.removePotionEffect(PotionEffectType.BLINDNESS);
			p.removePotionEffect(PotionEffectType.CONFUSION);
			p.removePotionEffect(PotionEffectType.WEAKNESS);
			p.removePotionEffect(PotionEffectType.SLOW);
			p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
			p.setFireTicks(0);
 	
        }
    }

    private boolean isRadioactive(Player p, ItemStack item) 
    {
    	/*
        for (SlimefunItem radioactiveItem : SlimefunPlugin.getRegistry().getRadioactiveItems()) 
        {
            if (radioactiveItem.isItem(item) && Slimefun.isEnabled(p, radioactiveItem, true)) 
            {
                // If the item is enabled in the world, then make radioactivity do its job
                SlimefunPlugin.getLocalization().sendMessage(p, "messages.radiation");

                Slimefun.runSync(() -> {
                    p.addPotionEffects(radiationEffects);
                    p.setFireTicks(400);
                });

                return true;
            }
        }
	*/
        return false;
    }
}

