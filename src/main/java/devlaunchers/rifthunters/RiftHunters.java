package devlaunchers.rifthunters;

import org.bukkit.World;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public final class RiftHunters extends JavaPlugin {

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
