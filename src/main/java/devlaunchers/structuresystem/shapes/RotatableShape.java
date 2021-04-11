package devlaunchers.structuresystem.shapes;

import org.bukkit.Location;

public abstract class RotatableShape extends Shape {

	private boolean xAxisRotatable, yAxisRotatable, zAxisRotatable;

	public RotatableShape(boolean xAxisRotatable, boolean yAxisRotatable, boolean zAxisRotatable) {
		this.xAxisRotatable = xAxisRotatable;
		this.yAxisRotatable = yAxisRotatable;
		this.zAxisRotatable = zAxisRotatable;
	}

	public boolean isXAxisRotatable() {
		return xAxisRotatable;
	}

	public boolean isYAxisRotatable() {
		return yAxisRotatable;
	}

	public boolean isZAxisRotatable() {
		return zAxisRotatable;
	}

	public abstract boolean rotateXAxis();

	public abstract boolean rotateYAxis();

	public abstract boolean rotateZAxis();

}
