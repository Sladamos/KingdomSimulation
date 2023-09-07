package human;

import strategy.item.organism.human.Human;
import strategy.item.organism.human.Adult;
import strategy.mechanism.starve.exceptions.IncorrectFoodException;
import strategy.mechanism.starve.exceptions.IncorrectHungerException;
import strategy.mechanism.starve.exceptions.StarveActionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class HumanStarveTest {

    private static int hungerLimit;

    @BeforeAll
    public static void setHungerLimit() {
        hungerLimit = 100;
    }

    @Test
    public void Should_DecreseHunger_When_Eat() {
        int initialHunger = 50;
        int eatValue = 10;
        Human human = new Adult(initialHunger, hungerLimit);
        human.eat(eatValue);
        assertThat(human.getHunger()).isEqualTo(40);
    }

    @Test
    public void Should_IncreaseHunger_When_Starve() {
        int initialHunger = 50;
        int starveValue = 10;
        Human human = new Adult(initialHunger, hungerLimit);
        human.starve(starveValue);
        assertThat(human.getHunger()).isEqualTo(60);
    }

    @Test
    public void Should_GetNoHunger_When_EatTooMuch() {
        int initialHunger = 50;
        int eatValue = 70;
        Human human = new Adult(initialHunger, hungerLimit);
        human.eat(eatValue);
        assertThat(human.getHunger()).isEqualTo(0);
    }

    @Test
    public void Should_ReachHungerLimit_When_Overstarve() {
        int initialHunger = 50;
        int starveValue = 70;
        Human human = new Adult(initialHunger, hungerLimit);
        human.starve(starveValue);
        assertThat(human.getHunger()).isEqualTo(hungerLimit);
    }

    @Test
    public void Should_ThrowException_When_OvereatOnCreate() {
        int initialHunger = -10;
        assertThatThrownBy(() -> new Adult(initialHunger, hungerLimit))
                .isInstanceOf(IncorrectHungerException.class).hasMessageContaining("Initial hunger less than 0");
    }

    @Test
    public void Should_ThrowException_When_DeadOnCreate() {
        int initialHunger = hungerLimit;
        assertThatThrownBy(() -> new Adult(initialHunger, hungerLimit))
                .isInstanceOf(IncorrectHungerException.class).hasMessageContaining("Human dead at creation");
    }

    @Test
    public void Should_ThrowException_When_StarvedOnCreate() {
        int initialHunger = 120;
        assertThatThrownBy(() -> new Adult(initialHunger, hungerLimit))
                .isInstanceOf(IncorrectHungerException.class).hasMessageContaining("Human dead at creation");
    }

    @Test
    public void Should_ThrowException_When_StarveByEating() {
        int initialHunger = 50;
        int eatValue = -10;
        Human human = new Adult(initialHunger, hungerLimit);

        assertThatThrownBy(() -> human.eat(eatValue)).isInstanceOf(IncorrectFoodException.class)
                .hasMessageContaining("Hunger can't increase by eating");
    }

    @Test
    public void Should_ThrowException_When_EatByStarving() {
        int initialHunger = 50;
        int starveValue = -10;
        Human human = new Adult(initialHunger, hungerLimit);

        assertThatThrownBy(() -> human.starve(starveValue)).isInstanceOf(IncorrectHungerException.class)
                .hasMessageContaining("Hunger can't decrease by starving");
    }

    @Test
    public void Should_ReturnFalse_When_IsNotHumanDeadOnCreate() {
        int initialHunger = 50;
        Human human = new Adult(initialHunger, hungerLimit);
        assertThat(human.isDead()).isEqualTo(false);
    }

    @Test
    public void Should_ReturnFalse_When_IsNotHumanDeadWhenHungerNotReachedLimit() {
        int initialHunger = 50;
        int hungerValue = 5;
        Human human = new Adult(initialHunger, hungerLimit);
        human.starve(hungerValue);
        assertThat(human.isDead()).isEqualTo(false);
    }

    @Test
    public void Should_ReturnFalse_When_IsNotHumanDeadWhenEat() {
        int initialHunger = 50;
        int eatValue = 5;
        Human human = new Adult(initialHunger, hungerLimit);
        human.eat(eatValue);
        assertThat(human.isDead()).isEqualTo(false);
    }

    @Test
    public void Should_ReturnTrue_When_IsHumanDeadWhenHungerReachedLimit() {
        int initialHunger = 50;
        int hungerValue = 50;
        Human human = new Adult(initialHunger, hungerLimit);
        human.starve(hungerValue);
        assertThat(human.isDead()).isEqualTo(true);
    }

    @Test
    public void Should_ThrowException_When_EatWhileDead() {
        int initialHunger = 50;
        int hungerValue = 50;
        Human human = new Adult(initialHunger, hungerLimit);
        human.starve(hungerValue);
        int eatValue = 20;
        assertThatThrownBy(() -> human.eat(eatValue)).isInstanceOf(StarveActionException.class)
                .hasMessageContaining("Can't eat when is dead.");
    }

    @Test
    public void Should_ThrowException_When_StarveWhileDead() {
        int initialHunger = 50;
        int hungerValue = 50;
        Human human = new Adult(initialHunger, hungerLimit);
        human.starve(hungerValue);
        int starveValue = 20;
        assertThatThrownBy(() -> human.starve(starveValue)).isInstanceOf(StarveActionException.class)
                .hasMessageContaining("Can't starve when is dead.");
    }
}
