package tech.obliviondevelop.SF.Schematic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.math.BlockVector3;
import tech.obliviondevelop.SF.Setup;


public class Schematic
{
	
	private Clipboard schematic;
	
	Location loc;
	
	private int width = 0;
	private int height = 0;
	private int length = 0;
	
	public Schematic(String schem, Location location)
    {
    	loc = location;
    	File file = new File(Setup.getPlugin().getDataFolder() + "/schematics/" + schem + ".schem");
    	ClipboardFormat format = ClipboardFormats.findByFile(file);
    	ClipboardReader reader;
		try {
			reader = format.getReader(new FileInputStream(file));
	    	Clipboard clipboard = reader.read();
	    	schematic = clipboard;
	    	width = schematic.getRegion().getWidth();
	    	height = schematic.getRegion().getHeight();
	    	length = schematic.getRegion().getHeight();
	    	reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void compareSchamatic() {    	
    	BlockVector3 minV = schematic.getMinimumPoint();
    	Location minL = loc.subtract(2, 2, 1);
    	
    	for(int x = 0; x < width; ++x) {
			for(int y = 0; y < height; ++y) {
				for(int z = 0; z < length; ++z) {
					BlockVector3 blockV = minV.add(x, y, z);
					Location blockL = minL.add(x, y, z);
					//schematic.getBlock(blockV).getId();
					Material schemType = BukkitAdapter.adapt(schematic.getBlock(blockV).getBlockType());
					Material worldType = blockL.getBlock().getType();
					if (schemType == worldType) {
						Bukkit.getPlayer("OblivionDevelop").sendMessage("Match! - " + worldType.name());
					}
					else {
						Bukkit.getPlayer("OblivionDevelop").sendMessage("Fail! - " + worldType.name() + " - " + schemType.name());
					}
					//if ()
					//Bukkit.getPlayer("OblivionDevelop").sendMessage("Material ID is - " + BukkitAdapter.adapt(schematic.getBlock(blockV).getBlockType()));
					//Bukkit.getPlayer("OblivionDevelop").sendMessage("Material Name is - " + ItemType.toName(schematic.getBlock(blockV).getId()));
				}
			}
    	}
    }
    
    @SuppressWarnings("deprecation")
	public void reloadSchematic(String schem, Location loc) {
    	File file = new File(Setup.getPlugin().getDataFolder() + "/schematics/" + schem + ".schem");
    	ClipboardFormat format = ClipboardFormats.findByFile(file);
    	ClipboardReader reader;
		try {
			reader = format.getReader(new FileInputStream(file));
	    	Clipboard clipboard = reader.read();
	    	schematic = clipboard;

	    	width = schematic.getRegion().getWidth();
	    	height = schematic.getRegion().getHeight();
	    	length = schematic.getRegion().getHeight();
	    	reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}