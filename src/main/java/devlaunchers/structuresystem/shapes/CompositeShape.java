package devlaunchers.structuresystem.shapes;

import java.util.LinkedHashMap;

import org.bukkit.util.BlockVector;

public class CompositeShape extends Shape {

	private LinkedHashMap<BlockVector, Shape> shapes = new LinkedHashMap<>();

	public void addShape(double x, double y, double z, Shape shape) {
		shapes.put(new BlockVector(x, y, z), shape);
	}

	public void addShape(BlockVector vec, Shape shape) {
		shapes.put(vec, shape);
	}

	@Override
	public void construct() {
		for (BlockVector vec : shapes.keySet()) {
			shapes.get(vec).subregionConstruction(this, vec.getBlockX(), vec.getBlockY(), vec.getBlockZ());
		}
	}

	@Override
	public ShapeSize size() {
		int minX = 0, maxX = 0, minY = 0, maxY = 0, minZ = 0, maxZ = 0;

		for (BlockVector vec : shapes.keySet()) {
			ShapeSize size = shapes.get(vec).size();
			minX = Math.min(minX, size.minX + vec.getBlockX());
			maxX = Math.max(maxX, size.maxX + vec.getBlockX());
			minY = Math.min(minY, size.minY + vec.getBlockY());
			maxY = Math.max(maxY, size.maxY + vec.getBlockY());
			minZ = Math.min(minZ, size.minZ + vec.getBlockZ());
			maxZ = Math.max(maxZ, size.maxZ + vec.getBlockZ());
		}

		return new ShapeSize(minX, maxX, minY, maxY, minZ, maxZ);
	}

}
