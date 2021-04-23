package devlaunchers.structuresystem.populator;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.plugin.java.JavaPlugin;

public class StructurePopulator extends BlockPopulator {

	private static boolean initializedListener = false;

	private StructureGenerator structureGenerator;
	private StructurePlacementDeterminator structurePlacementDeterminator;
	private JavaPlugin plugin;
	private StructureGeneratorConfig generationConfig;

	public StructurePopulator(String structureName, StructureGenerator _structureGenerator,
			StructurePlacementDeterminator _structurePlacementDeterminator, JavaPlugin plugin) {
		this.structureGenerator = _structureGenerator;
		this.structurePlacementDeterminator = _structurePlacementDeterminator;
		this.plugin = plugin;
		this.generationConfig = new StructureGeneratorConfig(structureName, plugin);

		StructureWorldListener.registerPopulator(this);

		if (!initializedListener) {
			Bukkit.getPluginManager().registerEvents(new StructureWorldListener(), plugin);
			initializedListener = true;
		}
		
		this.structureGenerator.setStructureConfig(generationConfig);
		this.structureGenerator.initGenerator(generationConfig);
		this.structurePlacementDeterminator.setStructureConfig(generationConfig);
		this.structurePlacementDeterminator.initPlacementDeterminator(generationConfig);
	}

	@Override
	public void populate(World world, Random rand, Chunk chunk) {
		if (structurePlacementDeterminator.determinePlacement(world, rand, chunk)) {
			structureGenerator.generate(world, rand, chunk);
		}
	}
}
