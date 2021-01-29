package devlaunchers.rifthunters.riftsystem;

import devlaunchers.rifthunters.RiftHuntersPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Portal {
    //////////////////////
    // <Static Methods> //
    //////////////////////
    public static void doPortalTeleport(Player player, Block portalBlock) {
        World world = player.getWorld();
        Location portalLocation = portalBlock.getLocation();
        Portal portal = new Portal((int) portalLocation.getX(), 0, (int) portalLocation.getZ());
        Vector destination = portal.getDestination();

        // Get safe Y position to teleport to (this is where portal will be)
        int destinationPortalY = 1 + (int) world.getHighestBlockAt((int) destination.getX(), (int) destination.getZ()).getLocation().getY();
        destination.setY(destinationPortalY);

        Location playerTeleportLocation = new Location(world, (int) destination.getX() + 1, (int) destination.getY() + 3, (int) destination.getZ());

        player.teleport(playerTeleportLocation);

        // Place a portal block at the destination portal location
        // (initial seed algortthm only generates base portals, not destinations)
        Bukkit.getScheduler().runTaskLater(RiftHuntersPlugin.getInstance(), () -> {
            PortalGenerator.spawnPortal(world, (int) destination.getX(), (int) destination.getZ());
        }, 10);

    }
    ///////////////////////
    // </Static Methods> //
    ///////////////////////


    private Vector location;

    public Portal(int _x, int _y, int _z) {
        location = new Vector(_x, _y, _z);
    }

    public Vector getLocation() {
        return location;
    }

    public Vector getDestination() {
        return calculateDestination(location);
    }

    public String toString() {
        return location.toString();
    }


    final private int TWENTY_FOUR_BIT_MAX = 0b111111111111111111111111;
    final private int PIVOT_KEY = TWENTY_FOUR_BIT_MAX / 2;

    private Vector calculateDestination(Vector location) {
        return new Vector(
                calculateComponentDestination((int) location.getX()),
                location.getY(),
                calculateComponentDestination((int) location.getZ())
        );
    }

    private int calculateComponentDestination(int component) {
        if (component > 0) {
            // Encrypt
            int shiftLeft = (component << 12) & TWENTY_FOUR_BIT_MAX;
            int shiftRight = (component >> 12) & TWENTY_FOUR_BIT_MAX;
            return Math.abs((shiftLeft | shiftRight) ^ PIVOT_KEY) * -1;
        } else {
            // Decrypt
            component *= -1;
            int shiftLeft = ((component ^ PIVOT_KEY) << 12) & TWENTY_FOUR_BIT_MAX;
            int shiftRight = ((component ^ PIVOT_KEY) >> 12) & TWENTY_FOUR_BIT_MAX;
            return Math.abs(shiftLeft | shiftRight);
        }
    }

}
