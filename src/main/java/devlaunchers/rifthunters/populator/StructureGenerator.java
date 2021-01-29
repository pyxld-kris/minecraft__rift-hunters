package devlaunchers.rifthunters.populator;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Random;

public interface StructureGenerator {
    public void generate(World world, Random rand, Chunk chunk);
}
