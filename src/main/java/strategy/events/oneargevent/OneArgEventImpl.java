package strategy.events.oneargevent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

public class OneArgEventImpl<T> implements OneArgEvent<T> {

	private final ArrayList<Consumer<T>> listeners;

	private final ArrayList<Consumer<T>> listenersToRemove;

	public OneArgEventImpl() {
		listeners = new ArrayList<>();
		listenersToRemove = new ArrayList<>();
	}

	@Override
	public void addListener(Consumer<T> listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(Consumer<T> listener) {
		try {
			listeners.remove(listener);
		} catch (Exception err) {
			listenersToRemove.add(listener);
		}
	}

	@Override
	public void invoke(T arg) {
		for (var listener : listeners) {
			listener.accept(arg);
		}
		clearListeners();
	}

	private void clearListeners() {
		Iterator<Consumer<T>> iterator = listenersToRemove.iterator();
		while (iterator.hasNext()) {
			Consumer<T> listener = iterator.next();
			listeners.remove(listener);
			iterator.remove();
		}
	}
}
