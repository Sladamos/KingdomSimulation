package bulding.wood;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy.producer.exceptions.ProducerDestroyedException;
import strategy.producer.exceptions.IncorrectDamageException;
import strategy.producer.exceptions.IncorrectStorageException;
import strategy.producer.building.lumberjack.MahoganyLumberjack;
import strategy.item.wood.Mahogany;

import static org.assertj.core.api.Assertions.*;

public class MahoganyLumberjackTest {

    private MahoganyLumberjack lumberjack;

    @BeforeEach
    public void initializeMiner() {
        lumberjack = new MahoganyLumberjack(5);
    }

    @Test
    public void Should_PutWoodToStorage_When_MineWood() {
        int initWoods = lumberjack.getNumberOfWoodsInStorage();
        lumberjack.store(new Mahogany());
        assertThat(lumberjack.getNumberOfWoodsInStorage()).isGreaterThan(initWoods);

    }

    @Test
    public void Should_RemoveWoodFromStorage_When_TakenWood() {
        int initWoods = lumberjack.getNumberOfWoodsInStorage();
        lumberjack.getWood();
        assertThat(lumberjack.getNumberOfWoodsInStorage()).isLessThan(initWoods);
    }

    @Test
    public void Should_NotifyAndWait_When_MinedWood() {
        lumberjack = new MahoganyLumberjack(0);
        Thread thread = new Thread(lumberjack);
        thread.start();
        lumberjack.getWood();
    }

    @Test
    public void Should_DecreaseDurability_When_Damaged() {
        int durability = lumberjack.getDurability();
        lumberjack.dealDamage(durability - 1);
        assertThat(lumberjack.getDurability()).isLessThan(durability);
    }

    @Test
    public void Should_ThrowException_When_NegativeDamage() {
        assertThatThrownBy(() -> lumberjack.dealDamage(-5))
                .isInstanceOf(IncorrectDamageException.class).hasMessageContaining("Damage must be a non negative number");
    }

    @Test
    public void Should_ThrowException_When_NegativeInitialStorageValue() {
        assertThatThrownBy(() -> new MahoganyLumberjack(-1))
                .isInstanceOf(IncorrectStorageException.class).hasMessageContaining("Storage size must be a non negative number");
    }

    @Test
    public void Should_BeDestroyed_When_DurabilityIsZero() {
        assertThat(lumberjack.isDestroyed()).isEqualTo(false);
        int durability = lumberjack.getDurability();
        lumberjack.dealDamage(durability+1000);
        assertThat(lumberjack.getDurability()).isEqualTo(0);
        assertThat(lumberjack.isDestroyed()).isEqualTo(true);
    }


    @Test
    public void Should_ThrowException_When_WaitForWoodWhenMineDestroyed() {
        lumberjack = new MahoganyLumberjack(0);
        Thread thread = new Thread(lumberjack);
        thread.start();
        int durability = lumberjack.getDurability();
        lumberjack.dealDamage(durability+1);
        assertThatThrownBy(() -> lumberjack.getWood()).isInstanceOf(ProducerDestroyedException.class);
    }
}
