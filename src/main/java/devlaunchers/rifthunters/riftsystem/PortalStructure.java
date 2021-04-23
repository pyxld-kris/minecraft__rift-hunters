package devlaunchers.rifthunters.riftsystem;

import java.util.List;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import devlaunchers.structuresystem.populator.StructureGeneratorConfig;
import devlaunchers.structuresystem.shapes.CompositeShape;
import devlaunchers.structuresystem.shapes.implementation.Cuboid;
import devlaunchers.structuresystem.shapes.implementation.Cylinder;
import devlaunchers.structuresystem.shapes.implementation.Sphere;
import devlaunchers.structuresystem.shapes.implementation.Transformers;

public class PortalStructure extends CompositeShape {

	private static List<Material> decorationMaterials;
	private static List<Material> spawnBlockExclusions;

	public PortalStructure(StructureGeneratorConfig structureConfig) {
		decorationMaterials = structureConfig.getMaterialList("portal_decoration_materials");
		spawnBlockExclusions = structureConfig.getMaterialList("portal_generation_exclusions");

		Sphere lowerSphere = new Sphere(9, Material.END_STONE, true);
		lowerSphere.filterTransformation(Transformers.filterUpperPart(3));

		addShape(0, -4, 0, lowerSphere);

		Cylinder decorations = new Cylinder(3, 1, true);
		decorations.blockTransformer(Transformers.randomMaterialSelector(decorationMaterials));

		decorations.filterTransformation(Transformers.filterCoordinate(3, 0, 3));

		addShape(1, 0, 1, decorations);

		addShape(4, 0, 4, new Cuboid(1, 1, 1, Material.DIAMOND_BLOCK));

		addShape(4, 1, 4, new Cuboid(1, 2, 1, Material.END_GATEWAY));

		// Transforms the Block Locations according to the original Terrain
		locationTransformer((locRequest) -> {
			Location origLocation = locRequest.originalLocation;
			if (locRequest.relativeY > 2)
				return origLocation;

			Location high = getHighestLocationAtWithExclusions(origLocation.getWorld(), origLocation.getBlockX(),
					origLocation.getBlockZ());
			return high.add(0, locRequest.relativeY, 0);
		});
	}

	private static Location getHighestLocationAtWithExclusions(World world, double x, double z) {
		Location location = world.getHighestBlockAt((int) x, (int) z).getLocation();
		while (spawnBlockExclusions.contains(location.getBlock().getType())) {
			location.subtract(0, 1, 0);
		}
		return location;
	}

}
