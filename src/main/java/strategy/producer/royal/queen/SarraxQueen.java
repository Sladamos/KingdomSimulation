package strategy.producer.royal.queen;

import strategy.organism.human.Adult;
import strategy.organism.human.Child;
import strategy.producer.TwoToOneProducer;
import strategy.item.elixir.GrowthElixir;

import java.util.function.Supplier;

public class SarraxQueen extends TwoToOneProducer<Child, GrowthElixir, Adult> implements Queen {

	private static final int QUEEN_TRANSFORMATING_SPEED = 1;

	private static final int QUEEN_DURABILITY = 1000;

	public SarraxQueen(Supplier<Child> firstProducer, Supplier<GrowthElixir> secondProducer) {
		super(firstProducer, secondProducer, 0, QUEEN_TRANSFORMATING_SPEED,
				QUEEN_DURABILITY);
	}

	public synchronized Adult getAdult() {
		return getItem();
	}

	@Override
	protected Adult createNewItem(Child material, GrowthElixir secondMaterial) {
		return new Adult(10, 100);
	}

	@Override
	protected Adult produceNewItem() {
		return new Adult(10, 100);
	}

	@Override
	protected int getProducingTime() {
		return 3000;
	}
}
