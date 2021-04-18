package devlaunchers.rifthunters.riftsystem;

import devlaunchers.rifthunters.RiftHuntersPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Portal {

	private Vector location;

	public Portal(int _x, int _y, int _z) {
		location = new Vector(_x, _y, _z);
	}

	public static Portal getPortal(Location location) {
		return new Portal(location.getBlockX(), 0, location.getBlockZ());
	}

	public Vector getLocation() {
		return location;
	}

	public Vector getDestination() {
		return calculateDestination(location);
	}

	public void doPortalTeleport(Player player) {
		Vector destination = getDestination();

		World world = player.getWorld();

		int chunkX = destination.getBlockX() / 16;
		int chunkZ = destination.getBlockZ() / 16;

		Chunk destinationChunk = world.getChunkAt(chunkX, chunkZ);

		if (destinationChunk.load(true)) {
			Block portalBlock = world.getHighestBlockAt(destination.getBlockX(), destination.getBlockZ());

			Location teleportLocation = destination.toLocation(world).add(1, portalBlock.getY() + 3, 0);

			// If Portal doesn't exist, generate it before Teleporting the Player
			if (portalBlock.getType() != Material.DIAMOND_BLOCK) {
				for (int x = -1; x < 2; x++) {
					for (int z = -1; z < 2; z++) {
						world.getChunkAt(chunkX + x, chunkZ + z).load(true);
					}
				}

				Bukkit.getScheduler().runTaskLater(RiftHuntersPlugin.getInstance(), () -> {
					generateDestinationPortal(world);
				}, 1);
				Bukkit.getScheduler().runTaskLater(RiftHuntersPlugin.getInstance(), () -> {
					player.teleport(teleportLocation);
				}, 4);
			} else {
				player.teleport(teleportLocation);
			}
		}
	}

	public String toString() {
		return location.toString();
	}

	final private int TWENTY_FOUR_BIT_MAX = 0b111111111111111111111111;
	final private int PIVOT_KEY = TWENTY_FOUR_BIT_MAX / 2;

	private Vector calculateDestination(Vector location) {
		return new Vector(calculateComponentDestination((int) location.getX()), location.getY(),
				calculateComponentDestination((int) location.getZ()));
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

	private void generateDestinationPortal(World world) {
		Vector destination = getDestination();
		PortalGenerator.spawnPortal(world, (int) destination.getX(), (int) destination.getZ());
	}

}
