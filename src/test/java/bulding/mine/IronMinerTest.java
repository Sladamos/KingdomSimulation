package bulding.mine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy.kingdom.building.mine.IronMiner;
import strategy.kingdom.building.mine.Miner;
import strategy.kingdom.material.ore.IronOre;
import strategy.kingdom.organism.mechanisms.fight.exceptions.IncorrectAttackException;
import strategy.kingdom.organism.mechanisms.starve.exceptions.IncorrectHungerException;

import static org.assertj.core.api.Assertions.*;

public class IronMinerTest {

    private Miner<IronOre> miner;

    @BeforeEach
    public void initializeMiner() {
        miner = new IronMiner(5);
    }

    @Test
    public void Should_PutOreToStorage_When_MineOre() {
        int initOres = miner.getNumberOfOresInStorage();
        miner.putOreToStorage(new IronOre());
        assertThat(miner.getNumberOfOresInStorage()).isGreaterThan(initOres);

    }

    @Test
    public void Should_RemoveOreFromStorage_When_TakenOre() {
        int initOres = miner.getNumberOfOresInStorage();
        miner.getOre();
        assertThat(miner.getNumberOfOresInStorage()).isLessThan(initOres);
    }

    @Test
    public void Should_NotifyAndWait_When_MinedOre() {
        miner = new IronMiner(0);
        Thread thread = new Thread(miner);
        thread.start();
        miner.getOre();
    }

    @Test
    public void Should_DecreaseDurability_When_Damaged() {
        int durability = miner.getDurability();
        miner.dealDamage(durability - 1);
        assertThat(miner.getDurability()).isLessThan(durability);
    }

    @Test
    public void Should_ThrowException_When_NegativeDamage() {
        assertThat(() -> miner.dealDamage(-5))
                .isInstanceOf(IncorrectDamageException.class).hasMessageContaining("It's not possible to repair building by dealing damage");
    }

    @Test
    public void Should_ThrowException_When_NegativeInitialValue() {
        assertThat(() -> new Miner(-1))
                .isInstanceOf(IncorrectStorageSize.class).hasMessageContaining("Storage size must be a non negative number");
    }

    @Test
    public void Should_BeDestroyed_When_DurabilityIsZero() {
        miner = new IronMiner(1);
        assertThat(miner.isDestroyed()).isEqualTo(false);
        int durability = miner.getDurability();
        miner.dealDamage(durability+1);
        assertThat(miner.getDurability).isEqualTo(0);
        assertThat(miner.isDestroyed()).isEqualTo(true);
    }


    @Test
    public void Should_ReturnNullOre_When_MineDestroyed() {

    }
}
