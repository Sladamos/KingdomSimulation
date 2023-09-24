package strategy.app.options;

import strategy.option.NamedOption;
import strategy.option.battle.BattleLaunchedOption;
import strategy.option.battle.BattleStoppedOption;
import strategy.option.kingdom.KingdomLaunchedOption;
import strategy.option.kingdom.KingdomStoppedOption;

public interface ModificableAppOptionsManager extends AppOptionsManager {
	void setKingdomLaunchedOption(KingdomLaunchedOption kingdomLaunchedOption);
	void setKingdomStoppedOption(KingdomStoppedOption kingdomStoppedOption);
	void setBattleLaunchedOption(BattleLaunchedOption battleLaunchedOption);
	void setBattleStoppedOption(BattleStoppedOption battleStoppedOption);
	void setAppDisabledOption(NamedOption appDisabledOption);
}
