package strategy.kingdom.building.producer.tool.bucket;

import strategy.kingdom.building.producer.tool.Artisan;
import strategy.kingdom.material.bar.Bar;
import strategy.kingdom.product.tool.bucket.Bucket;

public abstract class BucketArtisan<T extends Bucket, U extends Bar> implements Artisan {

    //@Getter(onMethod_={@Synchronized})
    @Override
    public void run() {

    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public void dealDamage(int damage) {

    }

    @Override
    public int getDurability() {
        return 0;
    }
}
