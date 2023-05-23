package human;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class HumanTest {

    private int hungerLimit;

    @BeforeAll
    public void setHungerLimit() {
        hungerLimit = 100;
    }

    @Test
    public void eat() {
        int initialHunger = 50;
        int eatValue = 10;
        Human human = new Human(initialHunger, hungerLimit);
        human.eat(eatValue);
        assertThat(human.getHunger()).isEqualTo(40);
    }

    @Test
    public void starve() {
        int initialHunger = 50;
        int starveValue = 10;
        Human human = new Human(initialHunger, hungerLimit);
        human.starve(starveValue);
        assertThat(human.getHunger()).isEqualTo(60);
    }

    @Test
    public void overeat() {
        int initialHunger = 50;
        int eatValue = 70;
        Human human = new Human(initialHunger, hungerLimit);
        human.eat(eatValue);
        assertThat(human.getHunger()).isEqualTo(0);
    }

    @Test
    public void overstarve() {
        int initialHunger = 50;
        int starveValue = 70;
        Human human = new Human(initialHunger, hungerLimit);
        human.starve(starveValue);
        assertThat(human.getHunger()).isEqualTo(hungerLimit);
    }

    @Test
    public void overeatOnCreate() {
        int initialHunger = -10;
        assertThatThrownBy(() -> new Human(initialHunger, hungerLimit))
                .isInstanceOf(IncorrectHungerException.class).hasMessageContaining("Initial hunger less than 0");
    }

    @Test
    public void deadOnCreate() {
        int initialHunger = hungerLimit;
        assertThatThrownBy(() -> new Human(initialHunger, hungerLimit))
                .isInstanceOf(IncorrectHungerException.class).hasMessageContaining("Human dead at creation");
    }

    @Test
    public void starvedOnCreate() {
        int initialHunger = 120;
        assertThatThrownBy(() -> new Human(initialHunger, hungerLimit))
                .isInstanceOf(IncorrectHungerException.class).hasMessageContaining("Human starved at creation");
    }

    @Test
    public void starveByEating() {
        int initialHunger = 50;
        int eatValue = -10;
        Human human = new Human(initialHunger, hungerLimit);

        assertThatThrownBy(human.eat(eatValue)).isInstanceOf(IncorrectEatException.class)
                .hasMessageContaining("Hunger can't increase by eating");
    }

    @Test
    public void eatByStarving() {
        int initialHunger = 50;
        int starveValue = -10;
        Human human = new Human(initialHunger, hungerLimit);

        assertThatThrownBy(human.starve(starveValue)).isInstanceOf(IncorrectStarveException.class)
                .hasMessageContaining("Hunger can't decrease by starving");
    }

    @Test
    public void isNotHumanDeadOnCreate() {
        int initialHunger = 50;
        Human human = new Human(initialHunger, hungerLimit);
        assertThat(human.isDead()).isEqualTo(false);
    }

    @Test
    public void isNotHumanDeadWhenHungerNotReachedLimit() {
        int initialHunger = 50;
        int hungerValue = 5;
        Human human = new Human(initialHunger, hungerLimit);
        human.starve(hungerValue);
        assertThat(human.isDead()).isEqualTo(false);
    }

    @Test
    public void isNotHumanDeadWhenEat() {
        int initialHunger = 50;
        int eatValue = 5;
        Human human = new Human(initialHunger, hungerLimit);
        human.eat(eatValue);
        assertThat(human.isDead()).isEqualTo(false);
    }

    @Test
    public void isHumanDeadWhenHungerReachedLimit() {
        int initialHunger = 50;
        int hungerValue = 50;
        Human human = new Human(initialHunger, hungerLimit);
        human.starve(hungerValue);
        assertThat(human.isDead()).isEqualTo(true);
    }

    @Test
    public void eatWhileDead() {
        int initialHunger = 50;
        int hungerValue = 50;
        Human human = new Human(initialHunger, hungerLimit);
        human.starve(hungerValue);
        int eatValue = 20;
        assertThatThrownBy(human.eat(eatValue)).isInstanceOf(IncorrectActionException.class)
                .hasMessageContaining("Can't eat when is dead.");
    }

    @Test
    public void starveWhileDead() {
        int initialHunger = 50;
        int hungerValue = 50;
        Human human = new Human(initialHunger, hungerLimit);
        human.starve(hungerValue);
        int starveValue = 20;
        assertThatThrownBy(human.starve(starveValue)).isInstanceOf(IncorrectActionException.class)
                .hasMessageContaining("Can't starve when is dead.");
    }
}
