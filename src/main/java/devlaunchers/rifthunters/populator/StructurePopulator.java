package devlaunchers.rifthunters.populator;

import devlaunchers.rifthunters.populator.generator.StructureGenerator;
import devlaunchers.rifthunters.populator.placementdeterminator.StructurePlacementDeterminator;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class StructurePopulator extends BlockPopulator {

    private StructureGenerator structureGenerator;
    private StructurePlacementDeterminator structurePlacementDeterminator;
    private Material[] ignoredMaterials;

    @Override
    public void populate(World world, Random rand, Chunk chunk) {

    }
}