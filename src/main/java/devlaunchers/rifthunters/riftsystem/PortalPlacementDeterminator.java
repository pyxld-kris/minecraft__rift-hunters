package devlaunchers.rifthunters.riftsystem;

import devlaunchers.rifthunters.populator.StructurePlacementDeterminator;
import org.bukkit.Chunk;
import org.bukkit.World;

import java.util.Random;

public class PortalPlacementDeterminator implements StructurePlacementDeterminator {

    public PortalPlacementDeterminator() {
        System.out.println("CREATING DETERMINATOR");
    }

    public boolean determinePlacement(World world, Random rand, Chunk chunk) {
        if (rand.nextInt() % 200 == 0)
            return true;
        return false;
    }

}
