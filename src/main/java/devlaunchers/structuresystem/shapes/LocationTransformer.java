package devlaunchers.structuresystem.shapes;

import org.bukkit.Location;

/**
 * Enables one to transform Shapes according to the terrain or similar World Constraints.
 */
public interface LocationTransformer {

	public Location transformLocation(LocationTransformationRequest locationTransformationRequest);

}
