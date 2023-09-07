package integration;

import org.junit.jupiter.api.Test;
import strategy.producer.building.artisan.bucket.IronBucketArtisan;
import strategy.producer.building.artisan.bucket.WoodenBucketArtisan;
import strategy.producer.building.lumberjack.MahoganyLumberjack;
import strategy.producer.building.miner.basic.IronMiner;
import strategy.producer.building.smelter.IronBarSmelter;
import strategy.producer.building.well.basic.GoldenCoinWell;
import strategy.producer.building.well.basic.WaterWell;
import strategy.item.coin.GoldenCoin;
import strategy.item.fluid.Water;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;

public class FromRawToWaterAndCoinTest {

	@Test
	public void Should_ProduceWaterAndCoin_When_BucketsInStorage() {
		IronMiner miner = new IronMiner(50);
		IronBarSmelter smelter = new IronBarSmelter(miner::getMineral,0);
		MahoganyLumberjack lumberjack = new MahoganyLumberjack(0);
		WoodenBucketArtisan woodenBucketArtisan = new WoodenBucketArtisan(lumberjack::getWood, 0);
		IronBucketArtisan ironBucketArtisan = new IronBucketArtisan(smelter::getBar, 0);
		WaterWell waterWell = new WaterWell(ironBucketArtisan::getTool, 0);
		GoldenCoinWell coinWell = new GoldenCoinWell(woodenBucketArtisan::getTool, 0);
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		executorService.execute(coinWell);
		executorService.execute(waterWell);
		executorService.execute(ironBucketArtisan);
		executorService.execute(woodenBucketArtisan);
		executorService.execute(lumberjack);
		executorService.execute(smelter);
		executorService.execute(miner);
		assertThat(waterWell.getItem()).isInstanceOf(Water.class);
		assertThat(coinWell.getItem()).isInstanceOf(GoldenCoin.class);
	}
}
