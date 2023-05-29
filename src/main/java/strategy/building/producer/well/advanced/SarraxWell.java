package strategy.building.producer.well.advanced;

import strategy.building.producer.Producer;
import strategy.building.producer.well.basic.GoldenCoinWell;
import strategy.building.producer.well.basic.WaterWell;
import strategy.product.coin.GoldenCoin;
import strategy.product.fluid.Water;
import strategy.product.tool.bucket.IronBucket;
import strategy.product.tool.bucket.WoodenBucket;

import java.util.function.Supplier;

public class SarraxWell implements Producer {

	private final WaterWell waterWell;

	private final GoldenCoinWell goldenCoinWell;

	public SarraxWell(Supplier<WoodenBucket> woodenBucketSupplier, Supplier<IronBucket> ironBucketSupplier) {
		this.waterWell = new WaterWell(ironBucketSupplier, 15);
		this.goldenCoinWell = new GoldenCoinWell(woodenBucketSupplier, 10);
	}

	@Override
	public void run() {
		Thread thread = new Thread(waterWell);
		thread.start();
		goldenCoinWell.run();
	}

	@Override
	public synchronized boolean isDestroyed() {
		return waterWell.isDestroyed() && goldenCoinWell.isDestroyed();
	}

	// @throws - BuildingDestroyedException if two mines are destroyed
	@Override
	public synchronized void dealDamage(int damage) {
		if(goldenCoinWell.isDestroyed() || waterWell.isDestroyed()) {
			dealDamageIfOneWellIsDestroyed(damage);
		} else {
			dealDamageForBothWells(damage);
		}
	}

	@Override
	public synchronized int getDurability() {
		return waterWell.getDurability() + goldenCoinWell.getDurability();
	}

	private void dealDamageIfOneWellIsDestroyed(int damage) {
		if(waterWell.isDestroyed()) {
			goldenCoinWell.dealDamage(damage);
		} else if (goldenCoinWell.isDestroyed()) {
			waterWell.dealDamage(damage);
		}
	}

	private void dealDamageForBothWells(int damage) {

		int damageForWaterWell = waterWell.getDurability() < damage / 2 ? waterWell.getDurability() : damage / 2;
		int damageForGoldenCoinWell = goldenCoinWell.getDurability() < damage / 2 ? goldenCoinWell.getDurability() : damage / 2;

		waterWell.dealDamage(damageForWaterWell);
		goldenCoinWell.dealDamage(damageForGoldenCoinWell);
	}

	public synchronized Water getWater() {
		return waterWell.getItem();
	}

	public synchronized GoldenCoin getGoldenCoin() {
		return goldenCoinWell.getItem();
	}
}
