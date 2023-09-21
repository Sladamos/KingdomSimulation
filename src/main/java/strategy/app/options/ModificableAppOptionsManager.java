package strategy.app.options;

import strategy.option.kingdom.KingdomLaunchedOption;

public interface ModificableAppOptionsManager extends AppOptionsManager {
	void setKingdomLaunchedOption(KingdomLaunchedOption kingdomLaunchedOption);
}
