package devlaunchers.rifthunters.populator;

import org.bukkit.Chunk;
import org.bukkit.World;

import java.util.Random;

public interface StructurePlacementDeterminator {
    public boolean determinePlacement(World world, Random rand, Chunk chunk);
}
