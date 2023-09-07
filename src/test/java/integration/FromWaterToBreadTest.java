package integration;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import strategy.producer.building.bakery.bread.WheatBreadBakery;
import strategy.producer.building.farm.WheatFarm;
import strategy.producer.building.mill.WheatMill;
import strategy.producer.building.miner.basic.SaltMiner;
import strategy.producer.building.well.basic.WaterWell;
import strategy.item.mineral.Salt;
import strategy.item.fluid.Water;
import strategy.item.food.baking.bread.WheatBread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FromWaterToBreadTest {

    @Test
    public void Should_ReturnBread_When_BreadCreated() {
        WaterWell well = Mockito.mock(WaterWell.class);
        Mockito.when(well.getItem()).thenReturn(new Water());
        SaltMiner saltMiner = Mockito.mock(SaltMiner.class);
        Mockito.when(saltMiner.getMineral()).thenReturn(new Salt());
        WheatFarm farm = new WheatFarm(well::getItem, 0);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        WheatMill mill = new WheatMill(farm::getPlant, 0);
        WheatBreadBakery bakery = new WheatBreadBakery(mill::getFlour, saltMiner::getMineral, 0);
        executorService.execute(farm);
        executorService.execute(mill);
        executorService.execute(bakery);
        assertThat(bakery.getBaking()).isInstanceOf(WheatBread.class);
    }
}
