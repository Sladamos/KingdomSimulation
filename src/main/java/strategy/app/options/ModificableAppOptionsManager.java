package strategy.app.options;

import strategy.option.kingdom.KingdomDisabledOption;
import strategy.option.kingdom.KingdomLaunchedOption;

public interface ModificableAppOptionsManager extends AppOptionsManager {
	void setKingdomLaunchedOption(KingdomLaunchedOption kingdomLaunchedOption);
	void setKingdomDisabledOption(KingdomDisabledOption kingdomDisabledOption);
}
