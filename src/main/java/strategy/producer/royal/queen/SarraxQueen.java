package strategy.producer.royal.queen;

import strategy.item.elixir.GrowthElixir;
import strategy.item.organism.human.Adult;
import strategy.item.organism.human.Child;
import strategy.producer.ProducerConfig;
import strategy.producer.TwoToOneProducer;
import strategy.storage.OneItemStorage;

public class SarraxQueen extends TwoToOneProducer<Child, GrowthElixir, Adult> implements Queen {

	public SarraxQueen(OneItemStorage<Child> firstSourceStorage, OneItemStorage<GrowthElixir> secondSourceStorage, OneItemStorage<Adult> destinationStorage, ProducerConfig producerConfig) {
		super(firstSourceStorage, secondSourceStorage, destinationStorage, producerConfig);
	}

	@Override
	protected Adult createNewItem(Child firstMaterial, GrowthElixir secondMaterial) {
		return new Adult(0, 0);
	}
}
