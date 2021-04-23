package devlaunchers.rifthunters.riftsystem;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.World;

import devlaunchers.structuresystem.populator.StructureGeneratorConfig;
import devlaunchers.structuresystem.populator.StructurePlacementDeterminator;

public class PortalPlacementDeterminator extends StructurePlacementDeterminator {

	private double portalProbability;

	@Override
	public void initPlacementDeterminator(StructureGeneratorConfig structureConfig) {
		portalProbability = structureConfig.getStructureConfig().getDouble("portal_spawn_probability", 0.005);
	}

	public boolean determinePlacement(World world, Random rand, Chunk chunk) {
		if (rand.nextDouble() <= portalProbability)
			return true;
		return false;
	}

}
