package devlaunchers.structuresystem.shapes.implementation;

import org.bukkit.Material;

import devlaunchers.structuresystem.shapes.CompositeShape;

public class Sphere extends CompositeShape {

	public Sphere(int height, Material material) {
		this(height, material, false);
	}

	public Sphere(int height, Material material, boolean filled) {
		int y = 0;
		double maxRadius = height / 2;
		for (double i = 0; i <= Math.PI; i += Math.PI / (height - 1)) {
			int radius = (int) (Math.sin(i) * maxRadius);
			addShape(maxRadius - radius, y, maxRadius - radius, new Cylinder((int) radius, 1, material, filled));
			y++;
		}
	}
	
}
