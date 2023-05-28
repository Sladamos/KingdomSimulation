package bulding.tool;

import org.junit.jupiter.api.Test;
import strategy.kingdom.building.exceptions.BuildingDestroyedException;
import strategy.kingdom.building.producer.bar.IronBarSmelter;
import strategy.kingdom.building.producer.mine.basic.IronMiner;
import strategy.kingdom.building.producer.tool.bucket.IronBucketArtisan;
import strategy.kingdom.product.tool.bucket.IronBucket;

import static org.assertj.core.api.Assertions.*;

public class IronBucketArtisanTest {

    @Test
    public void Should_ProduceBucket_When_IronBarInStorage() {
        IronMiner miner = new IronMiner(0);
        IronBarSmelter smelter = new IronBarSmelter(miner::getOre, 0);
        IronBucketArtisan artisan = new IronBucketArtisan(smelter::getBar,0);
        Thread thread = new Thread(artisan);
        thread.start();
        thread = new Thread(smelter);
        thread.start();
        thread = new Thread(miner);
        thread.start();
        assertThat(artisan.getTool()).isInstanceOf(IronBucket.class);
    }
}
