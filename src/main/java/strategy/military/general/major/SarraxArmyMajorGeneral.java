package strategy.military.general.major;

import strategy.error.BasicAppError;
import strategy.location.castle.CastleStorageManager;
import strategy.location.settlement.SettlementStorageManager;
import strategy.military.MilitaryUnit;
import strategy.military.army.ArmyType;
import strategy.military.general.ArmyGeneral;
import strategy.military.general.ArmyGeneralImpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SarraxArmyMajorGeneral implements ArmyMajorGeneral {

    private final Map<ArmyType, ArmyGeneral> armiesGenerals;

    private final ExecutorService executor;

    public SarraxArmyMajorGeneral(SettlementStorageManager settlementStorageManager, CastleStorageManager castleStorageManager, MajorGeneralConfig majorGeneralConfig) {
        executor = Executors.newCachedThreadPool();
        armiesGenerals = new HashMap<>();
        ArmyGeneral warriorsGeneral = new ArmyGeneralImpl(castleStorageManager.getHappinessStorage(),
                settlementStorageManager.getWarriorsStorage(), majorGeneralConfig.getWarriorsGeneralConfig());
        armiesGenerals.put(ArmyType.WARRIOR, warriorsGeneral);
    }

    @Override
    public void addUnits(ArmyType armyType, Collection<MilitaryUnit> militaryUnits) {
        if(!armiesGenerals.containsKey(armyType)) {
            throw new BasicAppError("Can't recruit these types of units because general hasn't been recruited.");
        }
        armiesGenerals.get(armyType).addMilitaryUnits(militaryUnits);
    }

    @Override
    public void run() {
        for(var kv : armiesGenerals.entrySet()) {
            executor.submit(() -> kv.getValue().runHappinessConusmer());
            executor.submit(() -> kv.getValue().runUnitsConusmer());
        }
    }

    @Override
    public void terminate() {
        for(var kv : armiesGenerals.entrySet()) {
            kv.getValue().terminate();
        }
    }

}
