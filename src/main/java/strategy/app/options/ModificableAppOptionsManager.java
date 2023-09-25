package strategy.app.options;

import strategy.app.AppExitable;
import strategy.option.battle.BattleLaunchedOption;
import strategy.option.battle.BattleStoppedOption;
import strategy.option.kingdom.KingdomLaunchedOption;
import strategy.option.kingdom.KingdomStoppedOption;

public interface ModificableAppOptionsManager extends AppOptionsManager, AppExitable {
	void setKingdomLaunchedOption(KingdomLaunchedOption kingdomLaunchedOption);
	void setKingdomStoppedOption(KingdomStoppedOption kingdomStoppedOption);
	void setBattleLaunchedOption(BattleLaunchedOption battleLaunchedOption);
	void setBattleStoppedOption(BattleStoppedOption battleStoppedOption);
}
