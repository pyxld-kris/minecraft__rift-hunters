package devlaunchers.structuresystem.shapes;

import org.bukkit.Location;
import org.bukkit.Material;

import com.google.common.base.Supplier;

public class Cuboid extends RotatableShape {

	private int xEnd, yEnd, zEnd;
	private Material material;

	public Cuboid(int xEnd, int yEnd, int zEnd, Material material) {
		super(true, true, true);

		this.xEnd = xEnd;
		this.yEnd = yEnd;
		this.zEnd = zEnd;

		this.material = material;
	}

	@Override
	public void construct(ShapeConstruction shapeConstruction) {
		for (int x = 0; x < xEnd; x++) {
			for (int y = 0; y < yEnd; y++) {
				for (int z = 0; z < zEnd; z++) {
					shapeConstruction.setBlockType(x, y, z, material);
				}
			}
		}
	}

	@Override
	public boolean rotateXAxis() {
		int newY = -zEnd;
		int newZ = yEnd;

		yEnd = newY;
		zEnd = newZ;
		return true;
	}

	@Override
	public boolean rotateYAxis() {
		int newX = -zEnd;
		int newZ = xEnd;

		xEnd = newX;
		zEnd = newZ;
		return true;
	}

	@Override
	public boolean rotateZAxis() {
		int newX = -yEnd;
		int newY = xEnd;

		xEnd = newX;
		yEnd = newY;
		return true;
	}

}
