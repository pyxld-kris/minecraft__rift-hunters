package devlaunchers.structuresystem.shapes.implementation;

import java.util.function.Function;

import org.bukkit.Material;
import org.bukkit.util.Vector;

import devlaunchers.structuresystem.shapes.Shape;
import devlaunchers.structuresystem.shapes.ShapeSize;

public class Cylinder extends Shape {

	private int radius, height;
	private Material material;
	private Function<Vector, Material> materialSelector;
	private boolean filled;

	public Cylinder(int radius, int height, Material material) {
		this(radius, height, material, false);
	}

	public Cylinder(int radius, int height, Material material, boolean filled) {
		this.radius = radius;
		this.height = height;
		this.material = material;
		this.filled = filled;
	}

	public Cylinder(int radius, int height, Function<Vector, Material> material, boolean filled) {
		this.radius = radius;
		this.height = height;
		this.materialSelector = material;
		this.filled = filled;
	}

	@Override
	public void construct() {
		if (radius == 0) {
			setBlockType(0, 0, 0, material);
			return;
		}

		if (filled) {
			int rSquared = radius * radius;
			for (int x = -radius; x <= radius; x++) {
				for (int z = -radius; z <= radius; z++) {
					if (x * x + z * z <= rSquared) {
						for (int currentheight = 0; currentheight < height; currentheight++) {
							Material localMaterial = material;
							if (materialSelector != null) {
								localMaterial = materialSelector.apply(new Vector(x, currentheight, z));
							}

							setBlockType((int) Math.round(radius - x), currentheight, (int) Math.round(radius - z),
									localMaterial);
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
					Material localMaterial = material;
					if (materialSelector != null) {
						localMaterial = materialSelector.apply(new Vector(x, currentheight, z));
					}

					setBlockType((int) Math.round(radius - x), currentheight, (int) Math.round(radius - z),
							localMaterial);
				}
			}
		}
	}

	@Override
	public ShapeSize size() {
		return new ShapeSize(0, 2 * radius, 0, height, 0, 2 * radius);
	}

}
