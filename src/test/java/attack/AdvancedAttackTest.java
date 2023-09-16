package attack;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import strategy.action.AdvancedAttack;
import strategy.error.CriticalAppError;
import strategy.military.mechanism.fight.Fightable;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class AdvancedAttackTest {

	@Test
	public void throwCriticalAppError_when_passedNegativeDamage() {
		Fightable fightable = Mockito.mock(Fightable.class);
		ThrowingCallable callable = () -> new AdvancedAttack(fightable, -5);
		assertThatThrownBy(callable).isInstanceOf(CriticalAppError.class);
	}

	@Test
	public void doesNotThrowAnyException_when_attackerIsNotPassed() {
		ThrowingCallable callable = () -> new AdvancedAttack(null, 5);
		assertThatCode(callable).doesNotThrowAnyException();
	}

	@Test
	public void doesNotThrowAnyException_when_damageIsEqualToZero() {
		Fightable fightable = Mockito.mock(Fightable.class);
		ThrowingCallable callable = () -> new AdvancedAttack(fightable, 0);
		assertThatCode(callable).doesNotThrowAnyException();
	}

	@Test
	public void doesNotThrowAnyException_when_damageIsPositive() {
		Fightable fightable = Mockito.mock(Fightable.class);
		ThrowingCallable callable = () -> new AdvancedAttack(fightable, 4);
		assertThatCode(callable).doesNotThrowAnyException();
	}

	@Test
	public void returnOptional_when_getFightableIsCalled() {
		Fightable fightable = Mockito.mock(Fightable.class);
		AdvancedAttack attack = new AdvancedAttack(fightable, 4);
		assertThat(attack.getAttacker()).isInstanceOf(Optional.class);
	}
}
