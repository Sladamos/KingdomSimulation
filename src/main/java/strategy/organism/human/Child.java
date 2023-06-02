package strategy.organism.human;

public class Child extends Human {

	public Child(int initialHunger, int hungerLimit) {
		super(initialHunger, hungerLimit);
	}

	@Override
	public String toString() {
		return "Child";
	}
}
