package devlaunchers.rifthunters.populator;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Random;

public abstract class StructureGenerator {

	private StructureGeneratorConfig structureConfig;

	public void setStructureConfig(StructureGeneratorConfig structureConfig) {
		this.structureConfig = structureConfig;
	}

	public StructureGeneratorConfig getConfig() {
		return structureConfig;
	}
	
	public abstract void initGenerator(StructureGeneratorConfig structureConfig);

	public abstract void generate(World world, Random rand, Chunk chunk);
}
