package devlaunchers.rifthunters;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.util.Vector;

import java.util.Random;

public class PortalBlockPopulator extends BlockPopulator {
    @Override
    public void populate(World world, Random rand, Chunk chunk) {

        if (rand.nextInt()%20 == 0) {
            Vector chunkPos = new Vector(chunk.getX()*16, 0, chunk.getZ()*16);
            int portalY = 1+(int)world.getHighestBlockAt((int)chunkPos.getX()+8, (int)chunkPos.getZ()+8).getLocation().getY();
            Block block = chunk.getBlock(8, portalY, 8);
            Location blockLocation = block.getLocation();

            Portal.spawnPortal(world, (int)blockLocation.getX(), (int)blockLocation.getZ());
        }
    }
}
