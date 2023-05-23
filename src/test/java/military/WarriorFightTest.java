package military;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy.kingdom.organism.human.HumanImpl;
import strategy.kingdom.organism.mechanisms.starve.exceptions.IncorrectHungerException;

import static org.assertj.core.api.Assertions.*;

public class WarriorFightTest {

    private Warrior attacker;
    private Warrior defender;

    @BeforeEach
    public void initializeUnits() {
        attacker = new Warrior(12, 5);
        defender = new Warrior(3, 7);
    }

    @Test
    public void Should_DecreaseEnemyHealth_When_NormalAttack() {
        int hitPoints = defender.getHitPoints();
        attacker.attack(defender);
        assertThat(defender.getHitPoints()).isLowerThan(hitPoints);
    }

    @Test
    public void Should_KillEnemy_When_CriticalAttack() {
        attacker = new Warrior(10000, 3);
        attacker.attack(defender);
        assertThat(defender.getHitPoints()).isEqualTo(0);
        assertThat(defender.isDead()).isEqualTo(true);
    }

    @Test
    public void Should_NotDealDamageToEnemy_When_LowAttack() {
        defender = new Warrior(5, 10000);
        int hitPoints = defender.getHitPoints();
        attacker.attack(defender);
        assertThat(defender.getHitPoints()).isEqualTo(hitPoints);
    }

    @Test
    public void Should_ThrowException_When_AttacksItself() {
        assertThatThrownBy(attacker.attack(attacker))
                .isInstanceOf(FightActionException.class).hasMessageContaining("Can't attack itself");
    }

    @Test
    public void Should_ThrowException_When_AttacksIfDead() {
        assertThatThrownBy(attacker.attack(defender))
                .isInstanceOf(FightActionException.class).hasMessageContaining("Can't attack when is dead");
    }

    @Test
    public void Should_ThrowException_When_HitWhenDead() {
        assertThatThrownBy(attacker.attack(defender))
                .isInstanceOf(FightActionException.class).hasMessageContaining("Can't attack dead defender");
    }
}
