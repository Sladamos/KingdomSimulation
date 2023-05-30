package integration;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import strategy.building.producer.farm.WheatFarm;
import strategy.building.producer.mill.WheatMill;
import strategy.building.producer.well.basic.WaterWell;
import strategy.product.flour.WheatFlour;
import strategy.product.fluid.Water;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FromWaterToBreadTest {

    @Test
    public void Should_ReturnBread_When_BreadCreated() {
        WaterWell well = Mockito.mock(WaterWell.class);
        Mockito.when(well.getItem()).thenReturn(new Water());
        WheatFarm farm = new WheatFarm(well::getItem, 0);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        WheatMill mill = new WheatMill(farm::getPlant, 0);
        WheatBreadBakery bakery = new WheatBreadBakery(mill::getFlour, 0);
        executorService.execute(farm);
        executorService.execute(mill);
        executorService.execute(bakery);
        assertThat(bakery.getBread()).isInstanceOf(WheatBread.class);
    }
}
