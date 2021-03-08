package devlaunchers.rifthunters;

import devlaunchers.rifthunters.riftsystem.PortalListener;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Sheep;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.entity.Cow;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public final class RiftHuntersPlugin extends JavaPlugin {

    // START CONFIG
    private static String[] WORLDS = new String[]{
            "daily-world"
    };
    // END CONFIG

    private static JavaPlugin instance;

    public static JavaPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        // Plugin startup logic
        System.out.println("PLUGIN_INIT - hehe");
        getServer().broadcastMessage("plugin work haha yay");
        getServer().getPluginManager().registerEvents(new PortalListener(), this);

    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
