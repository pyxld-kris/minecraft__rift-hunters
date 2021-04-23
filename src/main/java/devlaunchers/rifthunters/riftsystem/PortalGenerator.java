package devlaunchers.rifthunters.riftsystem;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import devlaunchers.structuresystem.populator.StructureGenerator;
import devlaunchers.structuresystem.populator.StructureGeneratorConfig;
import devlaunchers.structuresystem.shapes.ShapeSize;

public class PortalGenerator extends StructureGenerator {

	private static PortalStructure portalStructure = new PortalStructure();

	// Unused in RiftSystem
	@Override
	public void initGenerator(StructureGeneratorConfig structureConfig) {
	}

	public void generate(World world, Random rand, Chunk chunk) {
		Vector chunkPos = new Vector(chunk.getX() * 16, 0, chunk.getZ() * 16);
		int portalY = 1 + (int) world.getHighestBlockAt((int) chunkPos.getX() + 8, (int) chunkPos.getZ() + 8)
				.getLocation().getY();
		Block block = chunk.getBlock(8, portalY, 8);
		Location blockLocation = block.getLocation();

		spawnPortal(world, (int) blockLocation.getX(), (int) blockLocation.getZ());
	}

	public static void spawnPortal(World world, int xPos, int zPos) {
		ShapeSize size = portalStructure.size();

		Block highestBlock = world.getHighestBlockAt(xPos - (size.maxX / 2), zPos - (size.maxZ / 2));

		portalStructure.construct(highestBlock.getLocation());
	}

}
