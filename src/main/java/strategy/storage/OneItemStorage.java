package strategy.storage;

import strategy.buffor.Buffor;
import strategy.buffor.SwitchableBuffor;
import strategy.item.Item;
import strategy.message.JSONMessage;
import strategy.message.sender.MessagesSender;

public interface OneItemStorage<T extends Item> extends Buffor<T>,
		MessagesSender<JSONMessage>, SwitchableBuffor {
}
