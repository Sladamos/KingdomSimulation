package strategy.battle;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BattleConfigImpl implements BattleConfig {

    private final Integer battleId;

    private final String firstKingdomId;

    private final String secondKingdomId;
}
