package strategy.kingdom.product.tool.bucket;

import strategy.kingdom.material.bar.Bar;
import strategy.kingdom.material.bar.IronBar;

public class IronBucket extends Bucket {

    public IronBucket() {
    }

    public IronBucket(IronBar material) {
        super();
    }

    @Override
    public String toString() {
        return "Iron bucket";
    }
}