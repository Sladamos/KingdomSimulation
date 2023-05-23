package human;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import strategy.kingdom.organism.human.Human;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;

public class HumanTest {

    private static int hungerLimit;

    @BeforeAll
    public static void setHungerLimit() {
        hungerLimit = 100;
    }

    @Test
    public void Should_DecreseHunger_When_Eat() {
        int initialHunger = 50;
        int eatValue = 10;
        Human human = mock(Human.class, withSettings().useConstructor(initialHunger, hungerLimit));
        human.eat(eatValue);
        assertThat(human.getHunger()).isEqualTo(40);
    }

    @Test
    public void Should_IncreaseHunger_When_Starve() {
        int initialHunger = 50;
        int starveValue = 10;
        Human human = mock(Human.class, withSettings().useConstructor(initialHunger, hungerLimit));
        human.starve(starveValue);
        assertThat(human.getHunger()).isEqualTo(60);
    }

    @Test
    public void Should_GetNoHunger_When_EatTooMuch() {
        int initialHunger = 50;
        int eatValue = 70;
        Human human = mock(Human.class, withSettings().useConstructor(initialHunger, hungerLimit));
        human.eat(eatValue);
        assertThat(human.getHunger()).isEqualTo(0);
    }

    @Test
    public void Should_ReachHungerLimit_When_Overstarve() {
        int initialHunger = 50;
        int starveValue = 70;
        Human human = mock(Human.class, withSettings().useConstructor(initialHunger, hungerLimit));
        human.starve(starveValue);
        assertThat(human.getHunger()).isEqualTo(hungerLimit);
    }

    @Test
    public void Should_ThrowException_When_OvereatOnCreate() {
        int initialHunger = -10;
        assertThatThrownBy(() -> mock(Human.class, withSettings().useConstructor(initialHunger, hungerLimit)))
                .isInstanceOf(IncorrectHungerException.class).hasMessageContaining("Initial hunger less than 0");
    }

    @Test
    public void Should_ThrowException_When_DeadOnCreate() {
        int initialHunger = hungerLimit;
        assertThatThrownBy(() -> mock(Human.class, withSettings().useConstructor(initialHunger, hungerLimit)))
                .isInstanceOf(IncorrectHungerException.class).hasMessageContaining("Human dead at creation");
    }

    @Test
    public void Should_ThrowException_When_StarvedOnCreate() {
        int initialHunger = 120;
        assertThatThrownBy(() -> mock(Human.class, withSettings().useConstructor(initialHunger, hungerLimit)))
                .isInstanceOf(IncorrectHungerException.class).hasMessageContaining("Human starved at creation");
    }

    @Test
    public void Should_ThrowException_When_StarveByEating() {
        int initialHunger = 50;
        int eatValue = -10;
        Human human = mock(Human.class, withSettings().useConstructor(initialHunger, hungerLimit));

        assertThatThrownBy(human.eat(eatValue)).isInstanceOf(IncorrectEatException.class)
                .hasMessageContaining("Hunger can't increase by eating");
    }

    @Test
    public void Should_ThrowException_When_EatByStarving() {
        int initialHunger = 50;
        int starveValue = -10;
        Human human = mock(Human.class, withSettings().useConstructor(initialHunger, hungerLimit));

        assertThatThrownBy(human.starve(starveValue)).isInstanceOf(IncorrectStarveException.class)
                .hasMessageContaining("Hunger can't decrease by starving");
    }

    @Test
    public void Should_ReturnFalse_When_IsNotHumanDeadOnCreate() {
        int initialHunger = 50;
        Human human = mock(Human.class, withSettings().useConstructor(initialHunger, hungerLimit));
        assertThat(human.isDead()).isEqualTo(false);
    }

    @Test
    public void Should_ReturnFalse_When_IsNotHumanDeadWhenHungerNotReachedLimit() {
        int initialHunger = 50;
        int hungerValue = 5;
        Human human = mock(Human.class, withSettings().useConstructor(initialHunger, hungerLimit));
        human.starve(hungerValue);
        assertThat(human.isDead()).isEqualTo(false);
    }

    @Test
    public void Should_ReturnFalse_When_IsNotHumanDeadWhenEat() {
        int initialHunger = 50;
        int eatValue = 5;
        Human human = mock(Human.class, withSettings().useConstructor(initialHunger, hungerLimit));
        human.eat(eatValue);
        assertThat(human.isDead()).isEqualTo(false);
    }

    @Test
    public void Should_ReturnTrue_When_IsHumanDeadWhenHungerReachedLimit() {
        int initialHunger = 50;
        int hungerValue = 50;
        Human human = mock(Human.class, withSettings().useConstructor(initialHunger, hungerLimit));
        human.starve(hungerValue);
        assertThat(human.isDead()).isEqualTo(true);
    }

    @Test
    public void Should_ThrowException_When_EatWhileDead() {
        int initialHunger = 50;
        int hungerValue = 50;
        Human human = mock(Human.class, withSettings().useConstructor(initialHunger, hungerLimit));
        human.starve(hungerValue);
        int eatValue = 20;
        assertThatThrownBy(human.eat(eatValue)).isInstanceOf(IncorrectActionException.class)
                .hasMessageContaining("Can't eat when is dead.");
    }

    @Test
    public void Should_ThrowException_When_StarveWhileDead() {
        int initialHunger = 50;
        int hungerValue = 50;
        Human human = mock(Human.class, withSettings().useConstructor(initialHunger, hungerLimit));
        human.starve(hungerValue);
        int starveValue = 20;
        assertThatThrownBy(human.starve(starveValue)).isInstanceOf(IncorrectActionException.class)
                .hasMessageContaining("Can't starve when is dead.");
    }
}
