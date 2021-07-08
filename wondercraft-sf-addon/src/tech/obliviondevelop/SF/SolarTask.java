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
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.armor.SlimefunArmorPiece;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.gadgets.SolarHelmet;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;



public class SolarTask implements Runnable
{
	public ConsoleCommandSender console;
	//private final ItemSetting<Double> chargeSetting = new ItemSetting<>("charge-amount", 0.1);
	
	public SolarTask() 
	{
		//console = SlimefunPlugin.instance.getServer().getConsoleSender();

	}
	/*
    @Override
    public void run()
    {
    	
        for (Player p : Bukkit.getOnlinePlayers()) 
        {
            if (!p.isValid() || p.isDead()) {
                continue;
            }

            PlayerProfile.get(p, profile -> {
                ItemStack[] armor = p.getInventory().getArmorContents();
                HashedArmorpiece[] cachedArmor = profile.getArmor();

                handleSlimefunArmor(p, armor, cachedArmor);
                checkForSolarHelmet(p);
            });
        }
    }

    private void handleSlimefunArmor(Player p, ItemStack[] armor, HashedArmorpiece[] cachedArmor) {
        for (int slot = 0; slot < 4; slot++) {
            ItemStack item = armor[slot];
            HashedArmorpiece armorpiece = cachedArmor[slot];

            if (armorpiece.hasDiverged(item)) {
                SlimefunItem sfItem = SlimefunItem.getByItem(item);
                if (!(sfItem instanceof SlimefunArmorPiece) || !Slimefun.hasUnlocked(p, sfItem, true)) {
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
    }

    private void checkForSolarHelmet(Player p) 
    {
    	
    	
        if (SlimefunUtils.isItemSimilar(p.getInventory().getHelmet(), SlimefunItems.SOLAR_HELMET, true) && Slimefun.hasUnlocked(p, SlimefunItem.getByID("SOLAR_HELMET"), true) && (p.getWorld().getTime() < 12300 || p.getWorld().getTime() > 23850) && p.getEyeLocation().getBlock().getLightFromSky() == 15) 
        {
        	ItemStack helmet = p.getInventory().getHelmet();
        	SlimefunItem item = SlimefunItem.getByItem(helmet);
           // ItemEnergy.chargeInventory(p, ((float) getChargeAmount()));
            ((SolarHelmet) item).rechargeItems(p);
        }
    }

    public double getChargeAmount() {
        return chargeSetting.getValue();
    }
    */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
