package strategy.building.producer.jewellery.advanced;

import strategy.building.producer.Producer;
import strategy.building.producer.jewellery.basic.necklace.RubyNecklaceJeweller;
import strategy.building.producer.jewellery.basic.ring.SapphireRingJeweller;
import strategy.material.mineral.gem.Sapphire;
import strategy.material.mineral.gem.Ruby;
import strategy.product.jewellery.necklace.RubyNecklace;
import strategy.product.jewellery.ring.SapphireRing;

import java.util.function.Supplier;

public class SarraxJeweller implements Producer {

	private final SapphireRingJeweller sapphireRingJeweller;

	private final RubyNecklaceJeweller rubyNecklaceJeweller;

	public SarraxJeweller(Supplier<Ruby> rubySupplier, Supplier<Sapphire> sapphireSupplier) {
		this.sapphireRingJeweller = new SapphireRingJeweller(sapphireSupplier, 3);
		this.rubyNecklaceJeweller = new RubyNecklaceJeweller(rubySupplier, 2);
	}

	@Override
	public void run() {
		Thread thread = new Thread(sapphireRingJeweller);
		thread.start();
		rubyNecklaceJeweller.run();
	}

	@Override
	public synchronized boolean isDestroyed() {
		return rubyNecklaceJeweller.isDestroyed() && sapphireRingJeweller.isDestroyed();
	}

	// @throws - BuildingDestroyedException if two jewellers are destroyed
	@Override
	public synchronized void dealDamage(int damage) {
		if(sapphireRingJeweller.isDestroyed() || rubyNecklaceJeweller.isDestroyed()) {
			dealDamageIfOneJewellerIsDestroyed(damage);
		} else {
			dealDamageForBothJewellers(damage);
		}
	}

	@Override
	public synchronized int getDurability() {
		return rubyNecklaceJeweller.getDurability() + sapphireRingJeweller.getDurability();
	}

	private void dealDamageIfOneJewellerIsDestroyed(int damage) {
		if(rubyNecklaceJeweller.isDestroyed()) {
			sapphireRingJeweller.dealDamage(damage);
		} else if (sapphireRingJeweller.isDestroyed()) {
			rubyNecklaceJeweller.dealDamage(damage);
		}
	}

	private void dealDamageForBothJewellers(int damage) {

		int damageForRubyNecklaceJeweller = rubyNecklaceJeweller.getDurability() < damage / 2 ? rubyNecklaceJeweller.getDurability() : damage / 2;
		int damageForSapphireRingJeweller = sapphireRingJeweller.getDurability() < damage / 2 ? sapphireRingJeweller.getDurability() : damage / 2;

		rubyNecklaceJeweller.dealDamage(damageForRubyNecklaceJeweller);
		sapphireRingJeweller.dealDamage(damageForSapphireRingJeweller);
	}

	public synchronized RubyNecklace getRubyNecklace() {
		return rubyNecklaceJeweller.getJewellery();
	}

	public synchronized SapphireRing getSapphireRing() {
		return sapphireRingJeweller.getJewellery();
	}
}
