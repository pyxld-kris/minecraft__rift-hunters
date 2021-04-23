package devlaunchers.structuresystem.populator;

import org.bukkit.Chunk;
import org.bukkit.World;

import java.util.Random;

public abstract class StructurePlacementDeterminator {

	private StructureGeneratorConfig structureConfig;

	public void setStructureConfig(StructureGeneratorConfig structureConfig) {
		this.structureConfig = structureConfig;
	}

	public StructureGeneratorConfig getConfig() {
		return structureConfig;
	}
	
	public abstract void initPlacementDeterminator(StructureGeneratorConfig structureConfig);

	public abstract boolean determinePlacement(World world, Random rand, Chunk chunk);

}
