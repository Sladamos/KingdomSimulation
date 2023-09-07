package strategy.item.organism.human;

public class Adult extends Human {

    public Adult(int initialHunger, int hungerLimit) {
        super(initialHunger, hungerLimit);
    }

    @Override
    public String toString() {
        return "Adult";
    }
}
