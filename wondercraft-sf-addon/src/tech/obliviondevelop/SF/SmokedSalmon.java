package tech.obliviondevelop.SF;

import org.bukkit.Sound;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;


public class SmokedSalmon extends SlimefunItem
{

	protected SmokedSalmon(SlimefunItem item) 
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

}
