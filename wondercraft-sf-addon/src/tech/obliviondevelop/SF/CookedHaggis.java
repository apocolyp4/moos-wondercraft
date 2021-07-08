package tech.obliviondevelop.SF;

import org.bukkit.Sound;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Player.PlayerInventory;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class CookedHaggis extends SlimefunItem
{

	protected CookedHaggis(SlimefunItem item) 
	{
		super(item.getCategory(), item.getItem(), item.getId(), item.getRecipeType(), item.getRecipe());
		// TODO Auto-generated constructor stub
	}
	
    @Override
    public void preRegister() {
    	ItemUseHandler itemUseHandler = this::onItemUseRightClick;
    	addItemHandler(itemUseHandler);

    }
    
    private void onItemUseRightClick(PlayerRightClickEvent event) 
    {    	
    	event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.ENTITY_GENERIC_EAT, 1, 1);
    	event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 600, 0));   
     
    }

    /*
    public boolean onRightClick(ItemUseEvent e, Player p, ItemStack item)
    {
        if (SlimefunUtils.isItemSimilar(item, WonderItems.COOKED_HAGGIS, true))
        {
            PlayerInventory.consumeItemInHand(p);
            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 1, 1);
            p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 600, (Integer) Slimefun.getItemValue("COOKED_HAGGIS", "effects.SATURATION")));
            return true;
        }
        else return false;
    }
    */
}

