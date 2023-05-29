package strategy.product.tool.bucket;

import strategy.material.bar.IronBar;

public class IronBucket extends Bucket {

    public IronBucket() {
    }

    public IronBucket(IronBar material) {
    }

    @Override
    public String toString() {
        return "Iron bucket";
    }
}
