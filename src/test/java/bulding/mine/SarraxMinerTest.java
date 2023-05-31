package bulding.mine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy.producer.exceptions.ProducerDestroyedException;
import strategy.producer.exceptions.IncorrectDamageException;
import strategy.producer.building.miner.advanced.SarraxMiner;

import static org.assertj.core.api.Assertions.*;

public class SarraxMinerTest {

    private SarraxMiner miner;

    @BeforeEach
    public void initializeMiner() {
        miner = new SarraxMiner();
    }

    @Test
    public void Should_NotifyAndWait_When_MinedOre() {
        miner = new SarraxMiner();
        Thread thread = new Thread(miner);
        thread.start();
        for(int i = 0; i < 12; i++) {
            miner.getRuby();
            miner.getIronOre();
        }
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
    public void Should_BeDestroyed_When_DurabilityIsZero() {
        miner = new SarraxMiner();
        assertThat(miner.isDestroyed()).isEqualTo(false);
        int durability = miner.getDurability();
        miner.dealDamage(durability+1000);
        assertThat(miner.getDurability()).isEqualTo(0);
        assertThat(miner.isDestroyed()).isEqualTo(true);
    }

    @Test
    public void Should_ThrowException_When_DamageDestroyedMiner() {
        miner = new SarraxMiner();
        int durability = miner.getDurability();
        miner.dealDamage(durability+1000);
        assertThatThrownBy(() -> miner.dealDamage(1)).isInstanceOf(ProducerDestroyedException.class).hasMessageContaining("It's not possible to attack destroyed building");
    }

    @Test
    public void Should_DamageIronMiner_When_RubyMinerDestroyed() {
        miner = new SarraxMiner();
        int durability = miner.getDurability();
        miner.dealDamage(durability / 2);
        durability = miner.getDurability();
        miner.dealDamage(durability / 2);
    }

    @Test
    public void Should_ThrowException_When_WaitForOreWhenMineDestroyed() {
        miner = new SarraxMiner();
        Thread thread = new Thread(miner);
        thread.start();
        int durability = miner.getDurability();
        miner.dealDamage(durability);
        miner.dealDamage(durability);
        assertThatThrownBy(() -> miner.getRuby()).isInstanceOf(ProducerDestroyedException.class);
        assertThatThrownBy(() -> miner.getIronOre()).isInstanceOf(ProducerDestroyedException.class);
    }
}
