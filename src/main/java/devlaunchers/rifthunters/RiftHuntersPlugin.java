package devlaunchers.rifthunters;

import org.bukkit.plugin.java.JavaPlugin;

import devlaunchers.rifthunters.riftsystem.PortalPopulator;

public final class RiftHuntersPlugin extends JavaPlugin {

    private static JavaPlugin instance;

    private static PortalPopulator portalPopulator;
    
    public static JavaPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        // Plugin startup logic
        this.getLogger().info("Plugin initialising...");
        
        portalPopulator = new PortalPopulator(this);

        this.getLogger().info("Plugin initialised!");
        
    }

}
