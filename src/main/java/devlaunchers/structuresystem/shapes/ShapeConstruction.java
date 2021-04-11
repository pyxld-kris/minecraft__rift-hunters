package devlaunchers.structuresystem.shapes;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class ShapeConstruction {

	private Location startLocation;
	private LocationTransformer locationTransformer;
	private int xOffset = 0, yOffset = 0, zOffset = 0;

	public ShapeConstruction(Location startLocation, LocationTransformer transformer) {
		this.startLocation = startLocation;
		this.locationTransformer = transformer;
	}

	private ShapeConstruction(Location startLocation, LocationTransformer transformer, int xOffset, int yOffset,
			int zOffset) {
		this.startLocation = startLocation;
		this.locationTransformer = transformer;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.zOffset = zOffset;
	}

	public ShapeConstruction(Location startLocation) {
		this(startLocation, null);
	}

	public Block getBlockAt(int relativeX, int relativeY, int relativeZ) {
		return transformLocation(relativeX, relativeY, relativeZ).getBlock();
	}

	public void setBlockType(int relativeX, int relativeY, int relativeZ, Material material) {
		getBlockAt(relativeX, relativeY, relativeZ).setType(material);
	}

	public Material getBlockType(int relativeX, int relativeY, int relativeZ) {
		return getBlockAt(relativeX, relativeY, relativeZ).getType();
	}

	public Location transformLocation(int relativeX, int relativeY, int relativeZ) {
		Location originalLocation = startLocation.clone().add(xOffset + relativeX, yOffset + relativeY,
				zOffset + relativeZ);

		if (locationTransformer != null) {
			return locationTransformer.transformLocation(new LocationTransformationRequest(originalLocation,
					xOffset + relativeX, yOffset + relativeY, zOffset + relativeZ));
		}
		return originalLocation;
	}

	public ShapeConstruction subregion(int relativeX, int relativeY, int relativeZ) {
		return new ShapeConstruction(startLocation.clone(), locationTransformer, relativeX, relativeY, relativeZ);
	}

}