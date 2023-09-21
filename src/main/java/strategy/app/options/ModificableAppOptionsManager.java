package strategy.app.options;

import strategy.option.kingdom.KingdomStoppedOption;
import strategy.option.kingdom.KingdomLaunchedOption;

public interface ModificableAppOptionsManager extends AppOptionsManager {
	void setKingdomLaunchedOption(KingdomLaunchedOption kingdomLaunchedOption);
	void setKingdomStoppedOption(KingdomStoppedOption kingdomStoppedOption);
}
