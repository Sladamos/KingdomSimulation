package military;

import strategy.item.military.infantry.warrior.Warrior;
import strategy.item.military.infantry.InfantryUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy.mechanism.fight.exceptions.FightActionException;
import strategy.mechanism.fight.exceptions.IncorrectAttackException;
import strategy.mechanism.fight.exceptions.IncorrectDefenseException;

import static org.assertj.core.api.Assertions.*;

public class InfantryUnitFightTest {

    private InfantryUnit attacker;
    private InfantryUnit defender;

    @BeforeEach
    public void initializeUnits() {
        attacker = new Warrior(12, 5);
        defender = new Warrior(3, 7);
    }

    @Test
    public void Should_DecreaseEnemyHealth_When_NormalAttack() {
        int hitPoints = defender.getHitPoints();
        attacker.attack(defender);
        assertThat(defender.getHitPoints()).isLessThan(hitPoints);
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
        assertThatThrownBy(() -> attacker.attack(attacker))
                .isInstanceOf(FightActionException.class).hasMessageContaining("Can't attack itself");
    }

    @Test
    public void Should_ThrowException_When_AttacksIfDead() {
        attacker.getHit(defender.getHitPoints() * 2);
        assertThatThrownBy(() -> attacker.attack(defender))
                .isInstanceOf(FightActionException.class).hasMessageContaining("Can't attack when attacker is dead");
    }

    @Test
    public void Should_ThrowException_When_HitWhenDead() {
        defender.getHit(defender.getHitPoints() * 2);
        assertThatThrownBy(() -> defender.getHit(1))
                .isInstanceOf(FightActionException.class).hasMessageContaining("Can't receive damage when is dead");
    }

    @Test
    public void Should_ThrowException_When_NegativeDefense() {
        assertThatThrownBy(() -> new Warrior(5, -1))
                .isInstanceOf(IncorrectDefenseException.class).hasMessageContaining("Defense must be a not negative number");
    }

    @Test
    public void Should_ThrowException_When_NegativeAttack() {
        assertThatThrownBy(() -> new Warrior(-5, 1))
                .isInstanceOf(IncorrectAttackException.class).hasMessageContaining("Attack must be a not negative number");
    }
}
