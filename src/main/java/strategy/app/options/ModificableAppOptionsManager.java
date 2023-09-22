package strategy.app.options;

import strategy.option.Option;
import strategy.option.battle.BattleLaunchedOption;
import strategy.option.battle.BattleStoppedOption;
import strategy.option.kingdom.KingdomStoppedOption;
import strategy.option.kingdom.KingdomLaunchedOption;

public interface ModificableAppOptionsManager extends AppOptionsManager {
	void setKingdomLaunchedOption(KingdomLaunchedOption kingdomLaunchedOption);
	void setKingdomStoppedOption(KingdomStoppedOption kingdomStoppedOption);
	void setBattleLaunchedOption(BattleLaunchedOption battleLaunchedOption);
	void setBattleStoppedOption(BattleStoppedOption battleStoppedOption);
	void setAppDisabledOption(Option appDisabledOption);
}
