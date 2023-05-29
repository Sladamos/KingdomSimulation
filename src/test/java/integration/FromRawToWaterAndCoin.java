package integration;

import org.junit.jupiter.api.Test;
import strategy.building.producer.artisan.bucket.IronBucketArtisan;
import strategy.building.producer.artisan.bucket.WoodenBucketArtisan;
import strategy.building.producer.lumberjack.MahoganyLumberjack;
import strategy.building.producer.miner.advanced.SarraxMiner;
import strategy.building.producer.miner.basic.IronMiner;
import strategy.building.producer.smelter.IronBarSmelter;
import strategy.building.producer.well.basic.GoldenCoinWell;
import strategy.building.producer.well.basic.WaterWell;
import strategy.product.coin.GoldenCoin;
import strategy.product.fluid.Water;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;

public class FromRawToWaterAndCoin {

	@Test
	public void Should_ProduceWaterAndCoin_When_BucketsInStorage() {
		IronMiner miner = new IronMiner(50);
		IronBarSmelter smelter = new IronBarSmelter(miner::getOre,0);
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
