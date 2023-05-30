package integration;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import strategy.building.producer.bakery.bread.WheatBreadBakery;
import strategy.building.producer.farm.WheatFarm;
import strategy.building.producer.mill.WheatMill;
import strategy.building.producer.miner.basic.SaltMiner;
import strategy.building.producer.well.basic.WaterWell;
import strategy.material.mineral.Salt;
import strategy.product.fluid.Water;
import strategy.product.food.baking.bread.WheatBread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FromWaterToBreadTest {

    @Test
    public void Should_ReturnBread_When_BreadCreated() {
        WaterWell well = Mockito.mock(WaterWell.class);
        Mockito.when(well.getItem()).thenReturn(new Water());
        SaltMiner saltMiner = Mockito.mock(SaltMiner.class);
        Mockito.when(saltMiner.getMaterial()).thenReturn(new Salt());
        WheatFarm farm = new WheatFarm(well::getItem, 0);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        WheatMill mill = new WheatMill(farm::getPlant, 0);
        WheatBreadBakery bakery = new WheatBreadBakery(mill::getFlour, saltMiner::getMaterial, 0);
        executorService.execute(farm);
        executorService.execute(mill);
        executorService.execute(bakery);
        assertThat(bakery.getBaking()).isInstanceOf(WheatBread.class);
    }
}
