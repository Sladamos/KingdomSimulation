package integration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import strategy.producer.building.jewellery.basic.necklace.RubyNecklaceJeweller;
import strategy.producer.building.jewellery.basic.ring.SapphireRingJeweller;
import strategy.producer.building.miner.basic.RubyMiner;
import strategy.producer.building.miner.basic.SapphireMiner;
import strategy.material.mineral.gem.Ruby;
import strategy.material.mineral.gem.Sapphire;
import strategy.product.jewellery.necklace.RubyNecklace;

public class FromGemsToJewelleryTest {

	@Test
	public void Should_ReturnRubyNecklace_When_GetRubyNecklaceCalled() {
		RubyMiner miner = Mockito.mock(RubyMiner.class);
		Mockito.when(miner.getMineral()).thenReturn(new Ruby());
		RubyNecklaceJeweller jeweller = new RubyNecklaceJeweller(miner::getMineral, 0);
		Thread thread = new Thread(jeweller);
		thread.start();
		Assertions.assertThat(jeweller.getJewellery()).isInstanceOf(RubyNecklace.class);
	}

	@Test
	public void Should_ReturnSapphireRing_When_GetSapphireRingCalled() {
		SapphireMiner miner = Mockito.mock(SapphireMiner.class);
		Mockito.when(miner.getMineral()).thenReturn(new Sapphire());
		SapphireRingJeweller jeweller = new SapphireRingJeweller(miner::getMineral, 0);
		Thread thread = new Thread(jeweller);
		thread.start();
		Assertions.assertThat(jeweller.getJewellery()).isInstanceOf(strategy.product.jewellery.ring.SapphireRing.class);
	}
}
