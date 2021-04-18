package devlaunchers.structuresystem.shapes;

import org.bukkit.block.Block;
import org.bukkit.util.Vector;

/**
 * Allows overriding Material and BlockData of placed Blocks from Shapes. 
 * transformBlock Method is supposed to interact directly with the Block.
 */
public interface BlockTransformer {

	public void transformBlock(Vector relativeLocation, Block blockToTransform);
	
}
