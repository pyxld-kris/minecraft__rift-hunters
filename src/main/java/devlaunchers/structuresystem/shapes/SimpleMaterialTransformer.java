package devlaunchers.structuresystem.shapes;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public interface SimpleMaterialTransformer extends BlockTransformer {

	@Override
	default void transformBlock(Vector relativeLocation, Block blockToTransform) {
		blockToTransform.setType(materialTransformer(relativeLocation));
	}

	public Material materialTransformer(Vector relativeLocation);

}
