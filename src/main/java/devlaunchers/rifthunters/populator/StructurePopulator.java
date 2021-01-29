package devlaunchers.rifthunters.populator;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class StructurePopulator extends BlockPopulator {

    private StructureGenerator structureGenerator;
    private StructurePlacementDeterminator structurePlacementDeterminator;
    private Material[] ignoredMaterials;

    public StructurePopulator(StructureGenerator structureGenerator, StructurePlacementDeterminator structurePlacementDeterminator) {
        structureGenerator = structureGenerator;
        structurePlacementDeterminator = structurePlacementDeterminator;
    }

    @Override
    public void populate(World world, Random rand, Chunk chunk) {
        if (structurePlacementDeterminator.determinePlacement(world, rand, chunk)) {
            structureGenerator.generate(world, rand, chunk);
        }
    }

}