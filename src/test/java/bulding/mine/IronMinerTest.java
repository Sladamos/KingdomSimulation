package bulding.mine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy.producer.exceptions.ProducerDestroyedException;
import strategy.producer.exceptions.IncorrectDamageException;
import strategy.producer.exceptions.IncorrectStorageException;
import strategy.producer.building.miner.basic.IronMiner;
import strategy.item.mineral.ore.IronOre;

import static org.assertj.core.api.Assertions.*;

public class IronMinerTest {

    private IronMiner miner;

    @BeforeEach
    public void initializeMiner() {
        miner = new IronMiner(5);
    }

    @Test
    public void Should_PutOreToStorage_When_MineOre() {
        int initOres = miner.getNumberOfMineralsInStorage();
        miner.store(new IronOre());
        assertThat(miner.getNumberOfMineralsInStorage()).isGreaterThan(initOres);

    }

    @Test
    public void Should_RemoveOreFromStorage_When_TakenOre() {
        int initOres = miner.getNumberOfMineralsInStorage();
        miner.getMineral();
        assertThat(miner.getNumberOfMineralsInStorage()).isLessThan(initOres);
    }

    @Test
    public void Should_NotifyAndWait_When_MinedOre() {
        miner = new IronMiner(0);
        Thread thread = new Thread(miner);
        thread.start();
        miner.getMineral();
    }

    @Test
    public void Should_DecreaseDurability_When_Damaged() {
        int durability = miner.getDurability();
        miner.dealDamage(durability - 1);
        assertThat(miner.getDurability()).isLessThan(durability);
    }

    @Test
    public void Should_ThrowException_When_NegativeDamage() {
        assertThatThrownBy(() -> miner.dealDamage(-5))
                .isInstanceOf(IncorrectDamageException.class).hasMessageContaining("Damage must be a non negative number");
    }

    @Test
    public void Should_ThrowException_When_NegativeInitialStorageValue() {
        assertThatThrownBy(() -> new IronMiner(-1))
                .isInstanceOf(IncorrectStorageException.class).hasMessageContaining("Storage size must be a non negative number");
    }

    @Test
    public void Should_BeDestroyed_When_DurabilityIsZero() {
        miner = new IronMiner(1);
        assertThat(miner.isDestroyed()).isEqualTo(false);
        int durability = miner.getDurability();
        miner.dealDamage(durability+1000);
        assertThat(miner.getDurability()).isEqualTo(0);
        assertThat(miner.isDestroyed()).isEqualTo(true);
    }

    @Test
    public void Should_ThrowException_When_DamageDestroyedMiner() {
        int durability = miner.getDurability();
        miner.dealDamage(durability+1000);
        assertThatThrownBy(() -> miner.dealDamage(1)).isInstanceOf(ProducerDestroyedException.class).hasMessageContaining("It's not possible to attack destroyed building");
        assertThat(miner.getDurability()).isEqualTo(0);
        assertThat(miner.isDestroyed()).isEqualTo(true);
    }


    @Test
    public void Should_ThrowException_When_WaitForOreWhenMineDestroyed() {
        miner = new IronMiner(0);
        Thread thread = new Thread(miner);
        thread.start();
        int durability = miner.getDurability();
        miner.dealDamage(durability+1);
        assertThatThrownBy(() -> miner.getMineral()).isInstanceOf(ProducerDestroyedException.class);
    }
}
