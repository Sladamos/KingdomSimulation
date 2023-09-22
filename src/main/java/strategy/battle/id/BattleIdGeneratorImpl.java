package strategy.battle.id;

public class BattleIdGeneratorImpl implements BattleIdGenerator {

    static {
        globalId = 0;
    }

    private static int globalId;

    @Override
    public Integer generateId() {
        globalId++;
        return globalId;
    }
}
