package devlaunchers.rifthunters.riftsystem;

import devlaunchers.rifthunters.populator.StructurePopulator;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.Random;

public class PortalPopulator extends StructurePopulator {

    public PortalPopulator() {
        super(new PortalGenerator(), new PortalPlacementDeterminator());
    }

}
