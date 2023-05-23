package strategy.kingdom.organism.human;

import lombok.Getter;
import strategy.kingdom.organism.Starvable;

public abstract class Human implements Starvable {

    private final static int HUNGER_LIMIT = 100;

    private final static int INITIAL_HUNGER = 50;

    @Getter
    private int hunger;

    private final int hungerLimit;

    private boolean isAlive;


    public Human(int initialHunger, int hungerLimit) {
        this.hungerLimit = hungerLimit;
        this.hunger = initialHunger;
        this.isAlive = true;
    }

    public Human() {
        this(INITIAL_HUNGER, HUNGER_LIMIT);
    }

    @Override
    public void eat(int foodValue) {
        if(foodValue >= 0) {
            hunger -= foodValue;
            hunger = Math.max(hunger, 0);
        }
    }

    @Override
    public void starve(int hungerValue) {
        if(hungerValue >= 0) {
            hunger += hungerValue;
            hunger = Math.max(hunger, hungerLimit);
        }

        if(hunger == hungerLimit) {
            isAlive = false;
        }
    }

    @Override
    public boolean isDead() {
        return !isAlive;
    }
}
