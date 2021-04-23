package devlaunchers.rifthunters.riftsystem;

import devlaunchers.rifthunters.RiftHuntersPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.scheduler.BukkitTask;

public class PortalListener implements Listener {

	private static HashMap<String, BukkitTask> queuedTeleports = new HashMap<>();

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		final Player player = e.getPlayer();
		Location playerLocation = player.getLocation();
		Block playerBlock = playerLocation.getBlock();

		if (!queuedTeleports.containsKey(player.getName())) {
			if (playerBlock.getType() == Material.END_GATEWAY) {

				World world = player.getWorld();

				// world.playEffect(effectLocation, Effect.ENDER_SIGNAL, 0);

				// Portal Travel Effect expects no additional Data and therefore can be null
				player.playEffect(player.getEyeLocation(), Effect.PORTAL_TRAVEL, null);

				// Delay for two seconds: if player is still in portal, then teleport
				Bukkit.getScheduler().runTaskLater(RiftHuntersPlugin.getInstance(), () -> {
					teleportIfInPortal(player);
				}, 40);

			}
		} else {
			if (playerBlock.getType() != Material.END_GATEWAY) {
				queuedTeleports.get(player.getName()).cancel();
				queuedTeleports.remove(player.getName());
			}
		}
	}

	/**
	 * Helper Function for onPlayerMove
	 */
	private static void teleportIfInPortal(Player player) {
		Block newPlayerBlock = player.getLocation().getBlock();
		if (newPlayerBlock.getType() == Material.END_GATEWAY) {
			// Player is STILL standing in portal
			Portal.getPortal(player.getLocation()).doPortalTeleport(player);
		}
		queuedTeleports.remove(player.getName());
	}

}
