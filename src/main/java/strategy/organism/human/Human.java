package strategy.organism.human;

import lombok.Getter;
import lombok.Synchronized;
import strategy.organism.Organism;
import strategy.organism.mechanisms.starve.Starvable;
import strategy.organism.mechanisms.starve.exceptions.IncorrectFoodException;
import strategy.organism.mechanisms.starve.exceptions.IncorrectHungerException;
import strategy.organism.mechanisms.starve.exceptions.StarveActionException;

public abstract class Human implements Organism, Starvable {

    private final static int HUNGER_LIMIT = 100;

    private final static int INITIAL_HUNGER = 50;

    @Getter(onMethod_={@Synchronized})
    private int hunger;

    private final int hungerLimit;

    private boolean isAlive;

    public Human(int initialHunger, int hungerLimit) {
        this.hungerLimit = hungerLimit;
        this.hunger = initialHunger;
        validateHunger();
        this.isAlive = true;
    }

    public Human() {
        this(INITIAL_HUNGER, HUNGER_LIMIT);
    }

    private void validateHunger() {
        if(hungerLimit <= 0) {
            throw new IncorrectHungerException("Initial hunger limit not higher than 0");
        }

        if(hunger < 0) {
            throw new IncorrectHungerException("Initial hunger less than 0");
        }

        if(hunger >= hungerLimit) {
            throw new IncorrectHungerException("Human dead at creation");
        }
    }

    @Override
    public synchronized void eat(int foodValue) {
        if(isDead()) {
            throw new StarveActionException("Can't eat when is dead.");
        }

        if(foodValue < 0) {
            throw new IncorrectFoodException("Hunger can't increase by eating");
        }

        hunger -= foodValue;
        hunger = Math.max(hunger, 0);
    }

    @Override
    public synchronized void starve(int hungerValue) {
        if(isDead()) {
            throw new StarveActionException("Can't starve when is dead.");
        }

        if(hungerValue < 0) {
            throw new IncorrectHungerException("Hunger can't decrease by starving");
        }

        hunger += hungerValue;
        hunger = Math.min(hunger, hungerLimit);
        checkIsStillAlive();
    }

    private synchronized void checkIsStillAlive() {
        if(hunger == hungerLimit) {
            isAlive = false;
        }
    }

    @Override
    public synchronized boolean isDead() {
        return !isAlive;
    }
}
