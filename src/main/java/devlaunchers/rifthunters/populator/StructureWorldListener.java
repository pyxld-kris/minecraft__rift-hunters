package devlaunchers.rifthunters.populator;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldUnloadEvent;

import devlaunchers.rifthunters.RiftHuntersPlugin;
import devlaunchers.rifthunters.riftsystem.PortalPopulator;

public class StructureWorldListener implements Listener {

	private static List<StructurePopulator> populators = new ArrayList<StructurePopulator>();

	private static List<String> populatedWorlds = new ArrayList<>();

	@EventHandler
	public void onWorldInit(WorldInitEvent e) {
		World world = e.getWorld();
		if (!populatedWorlds.contains(world.getName())) {
			RiftHuntersPlugin.getInstance().getLogger()
					.info("onWorldInit: WORLD '" + world.getName() + "' HAS INITIALIZED");
			// spawn is generated before the next line is called
			world.getPopulators().addAll(populators);
			populatedWorlds.add(world.getName());
		}
	}

	@EventHandler
	public void onWorldUnload(WorldUnloadEvent e) {
		populatedWorlds.remove(e.getWorld().getName());
	}
	
	public static void registerPopulator(StructurePopulator populator) {
		populators.add(populator);
	}

}
