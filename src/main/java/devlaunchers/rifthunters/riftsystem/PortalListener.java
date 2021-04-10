package devlaunchers.rifthunters.riftsystem;

import devlaunchers.rifthunters.RiftHuntersPlugin;

import java.util.ArrayList;
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

public class PortalListener implements Listener {

	private static boolean playerInPortalBlock = false;

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		Location playerLocation = player.getLocation();
		World world = player.getWorld();
		Block playerBlock = world.getBlockAt(playerLocation);
		if (playerBlock.getType() == Material.END_GATEWAY && !playerInPortalBlock) {
			playerInPortalBlock = true;

			// Visually notify the player that they are standing in a portal block
			Location effectLocation = playerLocation.clone();
			effectLocation.setY(effectLocation.getY() + 1);
			// world.playEffect(effectLocation, Effect.ENDER_SIGNAL, 0);
			world.playEffect(effectLocation, Effect.PORTAL_TRAVEL, 0);

			// Delay for two seconds: if player is still in portal, then teleport
			Bukkit.getScheduler().runTaskLater(RiftHuntersPlugin.getInstance(), () -> {
				teleportIfInPortal(player, playerBlock);
			}, 40);
			
		} else {
			playerInPortalBlock = false;
		}
	}

	/**
	 * Helper Function for onPlayerMove
	 */
	private static void teleportIfInPortal(Player player, Block playerBlock) {
		Block newPlayerBlock = player.getLocation().getBlock();
		if (newPlayerBlock.getType() == Material.END_GATEWAY) {
			// Player is STILL standing in portal
			Portal.doPortalTeleport(player, playerBlock);
		}
	}

}
