package strategy.military.army;

import strategy.military.MilitaryUnit;
import strategy.military.mechanism.fight.Fightable;
import strategy.message.JSONMessage;
import strategy.message.sender.MessagesSender;

import java.util.function.Consumer;

public interface Army extends Consumer<MilitaryUnit>, MessagesSender<JSONMessage>, Fightable {
	void onMoraleChanged(int morale);
}
