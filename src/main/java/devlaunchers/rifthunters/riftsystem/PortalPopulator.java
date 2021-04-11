package devlaunchers.rifthunters.riftsystem;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import devlaunchers.structuresystem.populator.StructurePopulator;

import java.util.Random;
// TODO: Register portals as "StructureType" using Bukkit


public class PortalPopulator extends StructurePopulator {

    public PortalPopulator(JavaPlugin plugin) {
        super("RiftSystem", new PortalGenerator(), new PortalPlacementDeterminator(), plugin);
        
        Bukkit.getPluginManager().registerEvents(new PortalListener(), plugin);
    }

}
