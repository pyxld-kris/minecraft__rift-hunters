package devlaunchers.structuresystem.shapes.implementation;

import org.bukkit.Material;

import devlaunchers.structuresystem.shapes.FilterTransformer;
import devlaunchers.structuresystem.shapes.SimpleMaterialTransformer;

public final class Transformers {

	public static FilterTransformer filterUpperPart(final int height) {
		return (vec) -> vec.getBlockY() > height;
	}

	public static FilterTransformer filterLowerPart(final int height) {
		return (vec) -> vec.getBlockY() < height;
	}

	public static FilterTransformer filterCoordinate(final int x, final int y, final int z) {
		return (vec) -> vec.getBlockX() == x && vec.getBlockY() == y && vec.getBlockZ() == z;
	}

	public static SimpleMaterialTransformer randomMaterialSelector(final Material... materials) {
		return (vec) -> materials[(int) Math.round(Math.random() * (materials.length - 1))];
	}

}
