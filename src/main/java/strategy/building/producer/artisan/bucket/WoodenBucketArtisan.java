package strategy.building.producer.artisan.bucket;

import strategy.material.wood.Mahogany;
import strategy.product.tool.bucket.WoodenBucket;

import java.util.function.Supplier;

public class WoodenBucketArtisan extends BucketArtisan<Mahogany, WoodenBucket> {

	private static final int WOODEN_BUCKET_ARTISAN_DURABILITY = 100;

	private static final int WOODEN_BUCKET_ARTISAN_CRAFTING_SPEED = 4;
	public WoodenBucketArtisan(Supplier<Mahogany> materialProducer, int defaultStorageSize) {
		super(materialProducer, defaultStorageSize, WOODEN_BUCKET_ARTISAN_CRAFTING_SPEED,
				WOODEN_BUCKET_ARTISAN_DURABILITY);
	}

	@Override
	protected WoodenBucket createNewTool(Mahogany material) {
		return new WoodenBucket();
	}

	@Override
	protected WoodenBucket createNewTool() {
		return new WoodenBucket();
	}
}
