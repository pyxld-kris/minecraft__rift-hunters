package devlaunchers.structuresystem.shapes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.function.Function;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;

public abstract class Shape {

	// Used for compositing Shapes
	private Shape parent;
	private int xOffset, yOffset, zOffset;

	private Location startLocation;

	private LinkedList<Consumer<Vector>> vectorTransformations = new LinkedList<>();
	private LocationTransformer locationTransformer;
	private ArrayList<Function<Vector, Boolean>> filterTransformations = new ArrayList<>();

	public abstract void construct();

	public abstract ShapeSize size();

	public void construct(Location startLocation) {
		this.startLocation = startLocation;
		construct();
		this.startLocation = null;
	}

	protected void subregionConstruction(Shape parent, int xOffset, int yOffset, int zOffset) {
		this.parent = parent;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.zOffset = zOffset;
		construct();
		this.parent = null;
		this.xOffset = 0;
		this.yOffset = 0;
		this.zOffset = 0;
	}

	public void setBlockType(int relativeX, int relativeY, int relativeZ, Material material) {
		Location location = transformLocation(relativeX, relativeY, relativeZ);
		if (location == null) {
			return;
		}
		location.getBlock().setType(material);
	}

	public Material getBlockType(int relativeX, int relativeY, int relativeZ) {
		Location location = transformLocation(relativeX, relativeY, relativeZ);
		if (location == null) {
			return Material.AIR;
		}
		return location.getBlock().getType();
	}

	public Location transformLocation(int relativeX, int relativeY, int relativeZ) {
		if (isRotated()) {
			Vector rotated = transformVector(new BlockVector(relativeX, relativeY, relativeZ));
			relativeX = rotated.getBlockX();
			relativeY = rotated.getBlockY();
			relativeZ = rotated.getBlockZ();
		}

		if (hasFilters()) {
			if (isFiltered(new Vector(relativeX, relativeY, relativeZ))) {
				return null;
			}
		}

		Location originalLocation = null;
		if (parent == null) {
			originalLocation = startLocation.clone();
			originalLocation = originalLocation.add(xOffset + relativeX, yOffset + relativeY, zOffset + relativeZ);
		} else {
			originalLocation = parent.transformLocation(xOffset + relativeX, yOffset + relativeY, zOffset + relativeZ);
			if (originalLocation == null) {
				return null;
			}
		}

		if (locationTransformer != null) {
			return locationTransformer.transformLocation(new LocationTransformationRequest(originalLocation,
					xOffset + relativeX, yOffset + relativeY, zOffset + relativeZ));
		}
		return originalLocation;
	}

	// Rotation Transformations

	public void rotateXAxis() {
		rotateXAxis(90);
	}

	public void rotateXAxis(double angle) {
		vectorTransformations.add((vec) -> vec.rotateAroundX(Math.toRadians(angle)));
	}

	public void rotateYAxis() {
		rotateYAxis(90);
	}

	public void rotateYAxis(double angle) {
		vectorTransformations.add((vec) -> vec.rotateAroundY(Math.toRadians(angle)));
	}

	public void rotateZAxis() {
		rotateZAxis(90);
	}

	public void rotateZAxis(double angle) {
		vectorTransformations.add((vec) -> vec.rotateAroundZ(Math.toRadians(angle)));
	}

	public BlockVector transformVector(BlockVector vec) {
		for (Consumer<Vector> transformer : vectorTransformations) {
			transformer.accept(vec);
		}
		return new BlockVector(Math.round(vec.getX()), Math.round(vec.getY()), Math.round(vec.getZ()));
	}

	public boolean isRotated() {
		return !vectorTransformations.isEmpty();
	}

	// Generic Location Transformations

	public boolean hasLocationTransformer() {
		return locationTransformer != null;
	}

	public void setLocationTransformer(LocationTransformer locationTransformer) {
		this.locationTransformer = locationTransformer;
	}

	public LocationTransformer getLocationTransformer() {
		return locationTransformer;
	}

	// Filter Transformations

	public void addFilterTransformation(Function<Vector, Boolean> filter) {
		filterTransformations.add(filter);
	}

	public boolean hasFilters() {
		return !filterTransformations.isEmpty();
	}

	/**
	 * Checks if location should be filtered out
	 * 
	 * @param location
	 * @return True if the location has to be filtered out
	 */
	public boolean isFiltered(Vector vec) {
		for (Function<Vector, Boolean> filter : filterTransformations) {
			if (filter.apply(vec)) {
				return true;
			}
		}
		return false;
	}

}
