package devlaunchers.rifthunters.populator;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.List;

public class BlockConverter {

    private int radius;
    private Material fromMaterial;
    private Material toMaterial;

    private List<Block> blockList;

    public BlockConverter(List<Block> _blockList) {
        blockList = _blockList;

        blockList.removeIf((Block block) -> {
            return block.getType().isAir();
        });
    }
}
