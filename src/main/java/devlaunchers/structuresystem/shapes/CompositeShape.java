package devlaunchers.structuresystem.shapes;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class CompositeShape extends Shape {

	private HashMap<Vector, Shape> shapes = new HashMap<>();

	public void addShape(int x, int y, int z, Shape shape) {
		shapes.put(new Vector(x, y, z), shape);
	}

	public void addShape(Vector vec, Shape shape) {
		shapes.put(vec, shape);
	}

	@Override
	public void construct(ShapeConstruction shapeConstruction) {
		for (Vector vec : shapes.keySet()) {
			shapes.get(vec).construct(shapeConstruction.subregion(vec.getBlockX(), vec.getBlockY(), vec.getBlockZ()));
		}
	}

}
