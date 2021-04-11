package devlaunchers.structuresystem.shapes;

import org.bukkit.Location;

public class LocationTransformationRequest {

	public Location originalLocation;

	public int relativeX, relativeY, relativeZ;

	public LocationTransformationRequest(Location originalLocation, int relativeX, int relativeY, int relativeZ) {
		this.originalLocation = originalLocation;
		this.relativeX = relativeX;
		this.relativeY = relativeY;
		this.relativeZ = relativeZ;
	}
}