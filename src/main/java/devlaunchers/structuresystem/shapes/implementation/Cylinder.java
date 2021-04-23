package devlaunchers.structuresystem.shapes.implementation;

import org.bukkit.Material;

import devlaunchers.structuresystem.shapes.Shape;
import devlaunchers.structuresystem.shapes.ShapeSize;

public class Cylinder extends Shape {

	private int radius, height;
	private boolean filled;

	public Cylinder(int radius, int height) {
		this(radius, height, false);
	}

	public Cylinder(int radius, int height, boolean filled) {
		this.radius = radius;
		this.height = height;
		this.filled = filled;
	}

	public Cylinder(int radius, int height, Material material) {
		this(radius, height, material, false);
	}

	public Cylinder(int radius, int height, Material material, boolean filled) {
		this.radius = radius;
		this.height = height;
		this.filled = filled;

		setMaterial(material);
	}

	@Override
	public void construct() {
		if (radius == 0) {
			placeBlock(0, 0, 0);
			return;
		}

		if (filled) {
			int rSquared = radius * radius;
			for (int x = -radius; x <= radius; x++) {
				for (int z = -radius; z <= radius; z++) {
					if (x * x + z * z <= rSquared) {
						for (int currentheight = 0; currentheight < height; currentheight++) {
							placeBlock((int) Math.round(radius - x), currentheight, (int) Math.round(radius - z));
						}
					}
				}
			}
		} else {
			for (int i = 0; i < 360; i++) {
				double angle = (i * Math.PI / 180);
				double x = radius * Math.cos(angle);
				double z = radius * Math.sin(angle);
				for (int currentheight = 0; currentheight < height; currentheight++) {
					placeBlock((int) Math.round(radius - x), currentheight, (int) Math.round(radius - z));
				}
			}
		}
	}

	@Override
	public ShapeSize size() {
		return new ShapeSize(0, 2 * radius, 0, height, 0, 2 * radius);
	}

}
