package integration;

import org.junit.jupiter.api.Test;
import strategy.kingdom.building.producer.bar.IronBarSmelter;
import strategy.kingdom.building.producer.mine.basic.IronMiner;
import strategy.kingdom.building.producer.tool.bucket.IronBucketArtisan;
import strategy.kingdom.building.producer.weapon.meele.IronSwordBlacksmith;
import strategy.kingdom.product.tool.bucket.IronBucket;
import strategy.kingdom.product.weapon.meele.sword.IronSword;

import static org.assertj.core.api.Assertions.assertThat;

public class FromIronOreToProductsTest {
    @Test
    public void Should_ProduceBucketAndSword_When_IronBarsInStorage() {
        IronMiner miner = new IronMiner(0);
        IronBarSmelter smelter = new IronBarSmelter(miner::getOre, 2);
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
