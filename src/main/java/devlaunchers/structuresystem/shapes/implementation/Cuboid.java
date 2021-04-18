package devlaunchers.structuresystem.shapes.implementation;

import org.bukkit.Material;

import devlaunchers.structuresystem.shapes.Shape;
import devlaunchers.structuresystem.shapes.ShapeSize;

public class Cuboid extends Shape {

	private int xEnd, yEnd, zEnd;

	public Cuboid(int xEnd, int yEnd, int zEnd) {
		this.xEnd = xEnd;
		this.yEnd = yEnd;
		this.zEnd = zEnd;
	}
	
	public Cuboid(int xEnd, int yEnd, int zEnd, Material material) {
		this.xEnd = xEnd;
		this.yEnd = yEnd;
		this.zEnd = zEnd;

		setMaterial(material);
	}

	@Override
	public void construct() {
		for (int x = 0; x < xEnd; x++) {
			for (int y = 0; y < yEnd; y++) {
				for (int z = 0; z < zEnd; z++) {
					placeBlock(x, y, z);
				}
			}
		}
	}

	

	@Override
	public ShapeSize size() {
		return new ShapeSize(0, xEnd, 0, yEnd, 0, zEnd);
	}

}
