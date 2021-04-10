package devlaunchers.rifthunters.populator;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class StructureGeneratorConfig {

	private File generatorConfigFile;

	private FileConfiguration config;

	private List<String> allowedWorlds;

	public StructureGeneratorConfig(String structureName, JavaPlugin plugin) {
		File dataFolder = plugin.getDataFolder();
		if (!dataFolder.exists()) {
			dataFolder.mkdirs();
		}

		generatorConfigFile = new File(dataFolder, structureName + ".yml");

		if (!generatorConfigFile.exists()) {
			plugin.saveResource(structureName + ".yml", false);
		}

		config = YamlConfiguration.loadConfiguration(generatorConfigFile);

		allowedWorlds = config.getStringList("allowedWorlds");

	}

	public boolean shouldWorldBePopulated(String worldName) {
		for (String worldRegex : allowedWorlds) {
			if (Pattern.matches(worldRegex, worldName)) {
				return true;
			}
		}
		return false;
	}

	public FileConfiguration getStructureConfig() {
		return config;
	}

}
