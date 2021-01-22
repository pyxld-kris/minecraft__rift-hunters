package devlaunchers.rifthunters;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;



public class PortalListener implements Listener {

    private static boolean populatorIsInitialized = false;

    @EventHandler
    public void onWorldInit(WorldInitEvent e) {
        if (!populatorIsInitialized) {
            System.out.println("onWorldInit: WORLD HAS INITIALIZED");
            World world = e.getWorld();
            // spawn is generated before the next line is called
            world.getPopulators().add(new PortalBlockPopulator());
            populatorIsInitialized = true;
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        System.out.println("PLAYER_JOINED");
        Bukkit.getServer().broadcastMessage("player joined");
    }

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
            effectLocation.setY(effectLocation.getY()+1);
            //world.playEffect(effectLocation, Effect.ENDER_SIGNAL, 0);
            world.playEffect(effectLocation,Effect.PORTAL_TRAVEL,0);

            // Delay for one second: if player is still in portal, then teleport
            Bukkit.getScheduler().runTaskLater(RiftHunters.getInstance(), new Runnable() {
                @Override
                public void run() {
                    Block newPlayerBlock = world.getBlockAt(player.getLocation());
                    if (newPlayerBlock.getType() == Material.END_GATEWAY) {
                        // Player is STILL standing in portal
                        Portal.doPortalTeleport(player, playerBlock);
                    }
                }

            }, 40);
        }
        else {
            playerInPortalBlock = false;
        }
    }


}
