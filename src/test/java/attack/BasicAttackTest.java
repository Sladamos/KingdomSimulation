package attack;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import strategy.action.Attack;
import strategy.action.BasicAttack;
import strategy.error.CriticalAppError;
import strategy.military.mechanism.fight.Fightable;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class BasicAttackTest {

	@Test
	public void doesNotThrowAnyException_when_combinationIsEmpty() {
		Fightable fightable = Mockito.mock(Fightable.class);
		ThrowingCallable callable = () -> new BasicAttack(fightable, new LinkedList<>());
		assertThatCode(callable).doesNotThrowAnyException();
	}

	@Test
	public void throwCriticalAppError_when_combinationIsNull() {
		Fightable fightable = Mockito.mock(Fightable.class);
		ThrowingCallable callable = () -> new BasicAttack(fightable, null);
		assertThatThrownBy(callable).isInstanceOf(CriticalAppError.class);
	}

	@Test
	public void doesNotThrowAnyException_when_attackerIsNotPassed() {
		Attack attack = Mockito.mock(BasicAttack.class);
		Collection<Attack> attacks = new LinkedList<>();
		attacks.add(attack);
		ThrowingCallable callable = () -> new BasicAttack(null, attacks);
		assertThatCode(callable).doesNotThrowAnyException();
	}

	@Test
	public void returnOptional_when_getFightableIsCalled() {
		Attack mockAttack = Mockito.mock(BasicAttack.class);
		Collection<Attack> attacks = new LinkedList<>();
		attacks.add(mockAttack);
		Fightable fightable = Mockito.mock(Fightable.class);
		BasicAttack attack = new BasicAttack(fightable, attacks);
		assertThat(attack.getAttacker()).isInstanceOf(Optional.class);
	}

	@Test
	public void returnProperAttackDamage_when_getAttackDamageIsCalled() {
		Attack mockAttack = Mockito.mock(BasicAttack.class);
		Mockito.when(mockAttack.getAttackDamage()).thenReturn(5);
		Collection<Attack> attacks = new LinkedList<>();
		attacks.add(mockAttack);
		attacks.add(mockAttack);
		Fightable fightable = Mockito.mock(Fightable.class);
		BasicAttack attack = new BasicAttack(fightable, attacks);
		assertThat(attack.getAttackDamage()).isEqualTo(10);
	}
}
