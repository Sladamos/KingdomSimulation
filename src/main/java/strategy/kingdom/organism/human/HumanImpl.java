package strategy.kingdom.organism.human;

public class HumanImpl extends Human {

    private final static int HUNGER_LIMIT = 100;

    private final static int INITIAL_HUNGER = 50;

    public HumanImpl(int initialHunger, int hungerLimit) {
        super(initialHunger, hungerLimit);
    }

    public HumanImpl() {
        super(INITIAL_HUNGER, HUNGER_LIMIT);
    }
}
