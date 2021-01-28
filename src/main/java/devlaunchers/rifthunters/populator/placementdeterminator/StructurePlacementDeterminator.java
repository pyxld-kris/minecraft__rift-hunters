package devlaunchers.rifthunters.populator.placementdeterminator;

import org.bukkit.Location;
import org.bukkit.World;

public interface StructurePlacementDeterminator {

    public boolean determinePlacement(World world, Location location);

}
