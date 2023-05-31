package bulding.bar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy.producer.exceptions.ProducerDestroyedException;
import strategy.producer.exceptions.IncorrectDamageException;
import strategy.producer.exceptions.IncorrectStorageException;
import strategy.producer.building.smelter.IronBarSmelter;
import strategy.producer.building.miner.basic.IronMiner;
import strategy.material.bar.IronBar;
import strategy.material.mineral.ore.IronOre;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.*;

public class IronBarSmelterTest {

    private IronBarSmelter barSmelter;

    private Supplier<IronOre> oresSupplier;

    @BeforeEach
    public void initialIronBarSmelter() {
        IronMiner miner = new IronMiner(0);
        oresSupplier = miner::getMineral;
        barSmelter = new IronBarSmelter(oresSupplier, 5);
        Thread thread = new Thread(miner);
        thread.start();
    }

    @Test
    public void Should_PutBarToStorage_When_SmeltBar() {
        int initBars = barSmelter.getNumberOfBarsInStorage();
        barSmelter.store(new IronBar());
        assertThat(barSmelter.getNumberOfBarsInStorage()).isGreaterThan(initBars);

    }

    @Test
    public void Should_RemoveBarFromStorage_When_TakenBar() {
        int initBars = barSmelter.getNumberOfBarsInStorage();
        barSmelter.getBar();
        assertThat(barSmelter.getNumberOfBarsInStorage()).isLessThan(initBars);
    }

    @Test
    public void Should_NotifyAndWait_When_SmeltBar() {
        barSmelter = new IronBarSmelter(oresSupplier, 0);
        Thread thread = new Thread(barSmelter);
        thread.start();
        barSmelter.getBar();
    }

    @Test
    public void Should_DecreaseDurability_When_Damaged() {
        int durability = barSmelter.getDurability();
        barSmelter.dealDamage(durability - 1);
        assertThat(barSmelter.getDurability()).isLessThan(durability);
    }

    @Test
    public void Should_ThrowException_When_NegativeDamage() {
        assertThatThrownBy(() -> barSmelter.dealDamage(-5))
                .isInstanceOf(IncorrectDamageException.class).hasMessageContaining("Damage must be a non negative number");
    }

    @Test
    public void Should_ThrowException_When_NegativeInitialStorageValue() {
        assertThatThrownBy(() -> new IronBarSmelter(oresSupplier, -1))
                .isInstanceOf(IncorrectStorageException.class).hasMessageContaining("Storage size must be a non negative number");
    }

    @Test
    public void Should_BeDestroyed_When_DurabilityIsZero() {
        assertThat(barSmelter.isDestroyed()).isEqualTo(false);
        int durability = barSmelter.getDurability();
        barSmelter.dealDamage(durability+1000);
        assertThat(barSmelter.getDurability()).isEqualTo(0);
        assertThat(barSmelter.isDestroyed()).isEqualTo(true);
    }

    @Test
    public void Should_ThrowException_When_DamageDestroyedIronBarSmelter() {
        int durability = barSmelter.getDurability();
        barSmelter.dealDamage(durability+1000);
        assertThatThrownBy(() -> barSmelter.dealDamage(1)).isInstanceOf(ProducerDestroyedException.class).hasMessageContaining("It's not possible to attack destroyed building");
        assertThat(barSmelter.getDurability()).isEqualTo(0);
        assertThat(barSmelter.isDestroyed()).isEqualTo(true);
    }


    @Test
    public void Should_ThrowException_When_WaitForBarWhenSmeltDestroyed() {
        barSmelter = new IronBarSmelter(oresSupplier, 0);
        Thread thread = new Thread(barSmelter);
        thread.start();
        int durability = barSmelter.getDurability();
        barSmelter.dealDamage(durability+1);
        assertThatThrownBy(() -> barSmelter.getBar()).isInstanceOf(ProducerDestroyedException.class);
    }
}
