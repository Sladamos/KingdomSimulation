package strategy.storage;

import strategy.buffer.SwitchableBuffer;
import strategy.item.Item;
import strategy.message.JSONMessage;
import strategy.message.sender.MessagesSender;

public interface OneItemStorage<T extends Item> extends SwitchableBuffer<T>, MessagesSender<JSONMessage> {
}
