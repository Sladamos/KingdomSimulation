package military;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WarriorFightTest {

    private Warrior attacker;
    private Warrior defender;

    @BeforeEach
    public void initializeUnits() {
        attacker = new Warrior(12, 5);
        defender = new Warrior(3, 7);
    }

    @Test
    public void Should_DecreaseEnemyHealth_When_Attack() {

    }

    @Test
    public void Should_KillEnemy_When_StrongAttack() {

    }

    @Test
    public void Should_NotDealDamageToEnemy_When_LowAttack() {

    }

    @Test
    public void Should_ThrowException_When_AttacksWhenDead() {

    }

    @Test
    public void Should_ThrowException_When_HitWhenDead() {

    }
}
