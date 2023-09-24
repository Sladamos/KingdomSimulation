package strategy.option.creator;

import strategy.app.options.ModificableAppOptionsManager;
import strategy.provider.battle.BattleIdProvider;
import strategy.provider.kingdom.KingdomIdProvider;

public interface OptionsManagerCreator {
	ModificableAppOptionsManager createDefaultAppOptionsManager(KingdomIdProvider kingdomIdProvider, BattleIdProvider battleIdProvider);
}
