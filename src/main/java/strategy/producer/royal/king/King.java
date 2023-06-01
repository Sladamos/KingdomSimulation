package strategy.producer.royal.king;

import strategy.material.mineral.gem.Gem;
import strategy.producer.Producer;
import strategy.producer.TwoToTwoProducer;
import strategy.producer.building.jewellery.basic.Jeweller;
import strategy.product.present.Present;

public abstract class King <T extends Jeweller<?, ?>, S extends Jeweller<?, ?>, U extends Present, V extends Present>
	implements Producer {

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

	@Override
	public void run() {

	}
}
