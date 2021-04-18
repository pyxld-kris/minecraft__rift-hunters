package devlaunchers.structuresystem.shapes;

import org.bukkit.util.Vector;

public interface FilterTransformer {

	/**
	 * Filters out non-desired Locations for a specific Shape
	 * @param vector The Location that should be checked against the Filter
	 * @return Location will be filtered out, if true is returned.
	 */
	public boolean filter(Vector vector);
	
}
