package tech.obliviondevelop.SF;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.chat.ChatColors;
import me.mrCookieSlime.Slimefun.cscorelib2.inventory.ItemUtils;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

import java.util.List;
import java.util.stream.Collectors;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.player.PlayerBackpack;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import io.github.thebusybiscuit.slimefun4.implementation.items.backpacks.SlimefunBackpack;
import io.github.thebusybiscuit.slimefun4.utils.PatternUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.Slimefun;

public class WonderScribe extends MultiBlockMachine
{
	private ItemStack item_stack = new ItemStack(Material.DISPENSER); 
	private String name = "&bWonder Scribe";
	private String description = "&a&o&Write Your Own Enchants";
	private String colour = "&7";
	private String id = "WONDERSCRIBE";
	private EnchantSetup plugin;
	public ConsoleCommandSender console;
	
	private RecipeType WONDERSCRIBE = new RecipeType(new CustomItem(item_stack, name, description), id);

	
	public WonderScribe(EnchantSetup plugin, Category category) 
	{
		super(
				category, 
				new SlimefunItemStack("WONDERSCRIBE", Material.ENCHANTING_TABLE, "&oWonder Scribe", "", "&7Write Your Own Enchants"),
				new ItemStack[] {null, null, null, null, new ItemStack(Material.ENCHANTING_TABLE), null, new ItemStack(Material.BOOKSHELF), new ItemStack(Material.DISPENSER), new ItemStack(Material.BOOKSHELF)},
				new ItemStack[0],
				BlockFace.SELF
		);
		
		this.plugin = plugin;
	}
	
	public RecipeType asRecipeType() 
	{
		return WONDERSCRIBE;
	}
	
	@Override
	public List<ItemStack> getDisplayRecipes()
	{
		return recipes.stream().map(items -> items[0]).collect(Collectors.toList());
	}
	
	
    @Override
    public void onInteract(Player p, Block b) {
        Block dispenser = b.getRelative(BlockFace.DOWN);
        Dispenser disp = (Dispenser) dispenser.getState();
        Inventory inv = disp.getInventory();

        List<ItemStack[]> inputs = RecipeType.getRecipeInputList(this);

        for (int i = 0; i < inputs.size(); i++) {
            if (isCraftable(inv, inputs.get(i))) {
                ItemStack output = RecipeType.getRecipeOutputList(this, inputs.get(i)).clone();
                if (Slimefun.hasUnlocked(p, output, true)) {
                    craft(inv, dispenser, p, b, output);
                }

                return;
            }
        }
        SlimefunPlugin.getLocalization().sendMessage(p, "machines.pattern-not-found", true);
    }

    protected Inventory createVirtualInventory(Inventory inv) {
        Inventory fakeInv = Bukkit.createInventory(null, 9, "Fake Inventory");

        for (int j = 0; j < inv.getContents().length; j++) {
            ItemStack stack = inv.getContents()[j] != null && inv.getContents()[j].getAmount() > 1 ? new CustomItem(inv.getContents()[j], inv.getContents()[j].getAmount() - 1) : null;
            fakeInv.setItem(j, stack);
        }

        return fakeInv;
    }
    
    
    protected void upgradeBackpack(Player p, Inventory inv, SlimefunBackpack backpack, ItemStack output) {
        ItemStack backpackItem = null;

        for (int j = 0; j < 9; j++) {
            if (inv.getContents()[j] != null && inv.getContents()[j].getType() != Material.AIR && SlimefunItem.getByItem(inv.getContents()[j]) instanceof SlimefunBackpack) {
                backpackItem = inv.getContents()[j];
                break;
            }
        }

        int size = backpack.getSize();
        Optional<String> id = retrieveID(backpackItem, size);

        if (id.isPresent()) {
            for (int line = 0; line < output.getItemMeta().getLore().size(); line++) {
                if (output.getItemMeta().getLore().get(line).equals(ChatColors.color("&7ID: <ID>"))) {
                    ItemMeta im = output.getItemMeta();
                    List<String> lore = im.getLore();
                    lore.set(line, lore.get(line).replace("<ID>", id.get()));
                    im.setLore(lore);
                    output.setItemMeta(im);
                    break;
                }
            }
        }
        else {
            for (int line = 0; line < output.getItemMeta().getLore().size(); line++) {
                if (output.getItemMeta().getLore().get(line).equals(ChatColors.color("&7ID: <ID>"))) {
                    int target = line;

                    PlayerProfile.get(p, profile -> {
                        int backpackId = profile.createBackpack(size).getId();
                        SlimefunPlugin.getBackpackListener().setBackpackId(p, output, target, backpackId);
                    });

                    break;
                }
            }
        }
    }
    
    private void craft(Inventory inv, Block dispenser, Player p, Block b, ItemStack output) {
        Inventory fakeInv = createVirtualInventory(inv);
        Inventory outputInv = findOutputInventory(output, dispenser, inv, fakeInv);

        if (outputInv != null) {
            SlimefunItem sfItem = SlimefunItem.getByItem(output);

            if (sfItem instanceof SlimefunBackpack) {
                upgradeBackpack(p, inv, (SlimefunBackpack) sfItem, output);
            }

            for (int j = 0; j < 9; j++) {
                ItemStack item = inv.getContents()[j];

                if (item != null && item.getType() != Material.AIR) {
                    ItemUtils.consumeItem(item, true);
                }
            }
            p.getWorld().playSound(b.getLocation(), Sound.BLOCK_WOODEN_BUTTON_CLICK_ON, 1, 1);

            outputInv.addItem(output);

        }
        else SlimefunPlugin.getLocalization().sendMessage(p, "machines.full-inventory", true);
    }

    private boolean isCraftable(Inventory inv, ItemStack[] recipe) {
        for (int j = 0; j < inv.getContents().length; j++) {
            if (!SlimefunUtils.isItemSimilar(inv.getContents()[j], recipe[j], true)) {
                if (SlimefunItem.getByItem(recipe[j]) instanceof SlimefunBackpack) {
                    if (!SlimefunUtils.isItemSimilar(inv.getContents()[j], recipe[j], false)) {
                        return false;
                    }
                }
                else {
                    return false;
                }
            }
        }

        return true;
    }
    
    
    private Optional<String> retrieveID(ItemStack backpack, int size) {
        if (backpack != null) {
            for (String line : backpack.getItemMeta().getLore()) {
                if (line.startsWith(ChatColors.color("&7ID: ")) && line.contains("#")) {
                    String id = line.replace(ChatColors.color("&7ID: "), "");
                    String[] idSplit = PatternUtils.HASH.split(id);

                    PlayerProfile.fromUUID(UUID.fromString(idSplit[0]), profile -> {
                        Optional<PlayerBackpack> optional = profile.getBackpack(Integer.parseInt(idSplit[1]));

                        if (optional.isPresent()) {
                            optional.get().setSize(size);
                        }
                    });

                    return Optional.of(id);
                }
            }
        }

        return Optional.empty();
    }
}