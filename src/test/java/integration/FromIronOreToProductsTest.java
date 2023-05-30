package integration;

import org.junit.jupiter.api.Test;
import strategy.building.producer.smelter.IronBarSmelter;
import strategy.building.producer.miner.basic.IronMiner;
import strategy.building.producer.artisan.bucket.IronBucketArtisan;
import strategy.building.producer.smith.meele.IronSwordBlacksmith;
import strategy.product.tool.bucket.IronBucket;
import strategy.product.weapon.meele.sword.IronSword;

import static org.assertj.core.api.Assertions.assertThat;

public class FromIronOreToProductsTest {

    @Test
    public void Should_ProduceBucketAndSword_When_IronBarsInStorage() {
        IronMiner miner = new IronMiner(0);
        IronBarSmelter smelter = new IronBarSmelter(miner::getMaterial, 0);
        IronBucketArtisan artisan = new IronBucketArtisan(smelter::getBar,0);
        IronSwordBlacksmith blacksmith = new IronSwordBlacksmith(smelter::getBar, 0);
        Thread thread = new Thread(artisan);
        thread.start();
        thread = new Thread(blacksmith);
        thread.start();
        thread = new Thread(smelter);
        thread.start();
        thread = new Thread(miner);
        thread.start();
        assertThat(artisan.getTool()).isInstanceOf(IronBucket.class);
        assertThat(blacksmith.getWeapon()).isInstanceOf(IronSword.class);
    }
}
