package strategy.option.creator;

import strategy.app.options.AppOptionsManagerImpl;
import strategy.app.options.ModificableAppOptionsManager;
import strategy.option.battle.BattleLaunchedOption;
import strategy.option.battle.BattleLaunchedOptionImpl;
import strategy.option.battle.BattleStoppedOption;
import strategy.option.battle.BattleStoppedOptionImpl;
import strategy.option.kingdom.KingdomLaunchedOption;
import strategy.option.kingdom.KingdomLaunchedOptionImpl;
import strategy.option.kingdom.KingdomStoppedOption;
import strategy.option.kingdom.KingdomStoppedOptionImpl;
import strategy.provider.battle.BattleIdProvider;
import strategy.provider.kingdom.KingdomIdProvider;

public class OptionsManagerCreatorImpl implements OptionsManagerCreator {

	@Override
	public ModificableAppOptionsManager createDefaultAppOptionsManager(KingdomIdProvider kingdomIdProvider, BattleIdProvider battleIdProvider) {
		ModificableAppOptionsManager optionsManager = new AppOptionsManagerImpl();
		KingdomLaunchedOption kingdomLaunchedOption = new KingdomLaunchedOptionImpl(kingdomIdProvider);
		KingdomStoppedOption kingdomStoppedOption = new KingdomStoppedOptionImpl(kingdomIdProvider);
		BattleLaunchedOption battleLaunchedOption = new BattleLaunchedOptionImpl(kingdomIdProvider);
		BattleStoppedOption battleStoppedOption = new BattleStoppedOptionImpl(battleIdProvider);
		optionsManager.setKingdomLaunchedOption(kingdomLaunchedOption);
		optionsManager.setKingdomStoppedOption(kingdomStoppedOption);
		optionsManager.setBattleLaunchedOption(battleLaunchedOption);
		optionsManager.setBattleStoppedOption(battleStoppedOption);
		return optionsManager;
	}
}
