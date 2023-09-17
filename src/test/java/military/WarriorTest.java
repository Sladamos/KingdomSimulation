package military;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import strategy.action.Attack;
import strategy.military.infantry.warrior.Warrior;
import strategy.military.infantry.warrior.WarriorConfig;
import strategy.military.mechanism.fight.exceptions.FightActionException;

import static org.assertj.core.api.Assertions.*;

public class WarriorTest {

	@Test
	public void doesNotThrowAnyException_when_initializedWithoutDefense() {
		WarriorConfig config = Mockito.mock(WarriorConfig.class);
		Mockito.when(config.getDamage()).thenReturn(5);
		Mockito.when(config.getDefense()).thenReturn(0);
		Mockito.when(config.getHealth()).thenReturn(15);
		assertThatCode(() -> new Warrior(config)).doesNotThrowAnyException();
	}

	@Test
	public void doesNotThrowAnyException_when_initializedWithoutDamage() {
		WarriorConfig config = Mockito.mock(WarriorConfig.class);
		Mockito.when(config.getDamage()).thenReturn(0);
		Mockito.when(config.getDefense()).thenReturn(5);
		Mockito.when(config.getHealth()).thenReturn(15);
		assertThatCode(() -> new Warrior(config)).doesNotThrowAnyException();
	}

	@Test
	public void isDead_when_initializedWithoutHp() {
		WarriorConfig config = Mockito.mock(WarriorConfig.class);
		Mockito.when(config.getDamage()).thenReturn(10);
		Mockito.when(config.getDefense()).thenReturn(5);
		Mockito.when(config.getHealth()).thenReturn(0);
		Warrior warrior = new Warrior(config);
		assertThat(warrior.isDead()).isEqualTo(true);
	}

	@Test
	public void throwFightActionException_when_getAttackedWhenIsDead() {
		WarriorConfig config = Mockito.mock(WarriorConfig.class);
		Mockito.when(config.getDamage()).thenReturn(10);
		Mockito.when(config.getDefense()).thenReturn(5);
		Mockito.when(config.getHealth()).thenReturn(0);
		Warrior warrior = new Warrior(config);
		Attack attack = Mockito.mock(Attack.class);
		assertThatThrownBy(() -> warrior.getHit(attack)).isInstanceOf(FightActionException.class);
	}

	@Test
	public void becomeDead_when_hitForFullHp() {
		WarriorConfig config = Mockito.mock(WarriorConfig.class);
		Mockito.when(config.getDamage()).thenReturn(10);
		Mockito.when(config.getDefense()).thenReturn(0);
		Mockito.when(config.getHealth()).thenReturn(50);
		Warrior warrior = new Warrior(config);
		Attack attack = Mockito.mock(Attack.class);
		Mockito.when(attack.getAttackDamage()).thenReturn(50);
		warrior.getHit(attack);
		assertThat(warrior.isDead()).isEqualTo(true);
	}

	@Test
	public void becomeDead_when_hitForMoreThanFullHp() {
		WarriorConfig config = Mockito.mock(WarriorConfig.class);
		Mockito.when(config.getDamage()).thenReturn(10);
		Mockito.when(config.getDefense()).thenReturn(0);
		Mockito.when(config.getHealth()).thenReturn(50);
		Warrior warrior = new Warrior(config);
		Attack attack = Mockito.mock(Attack.class);
		Mockito.when(attack.getAttackDamage()).thenReturn(1550);
		warrior.getHit(attack);
		assertThat(warrior.isDead()).isEqualTo(true);
	}

	@Test
	public void createAttackWithProperDamage_when_createAttackIsExecuted() {
		WarriorConfig config = Mockito.mock(WarriorConfig.class);
		Mockito.when(config.getDamage()).thenReturn(10);
		Mockito.when(config.getDefense()).thenReturn(0);
		Mockito.when(config.getHealth()).thenReturn(50);
		Warrior warrior = new Warrior(config);
		Attack attack = warrior.createAttack();
		assertThat(attack.getAttackDamage()).isEqualTo(10);
	}
}
