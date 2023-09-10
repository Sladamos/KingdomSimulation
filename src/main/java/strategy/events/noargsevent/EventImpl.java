package strategy.events.noargsevent;

import java.util.ArrayList;
import java.util.Iterator;

public class EventImpl implements Event {

	private final ArrayList<Runnable> listeners;

	private final ArrayList<Runnable> listenersToRemove;

	public EventImpl() {
		listeners = new ArrayList<>();
		listenersToRemove = new ArrayList<>();
	}

	@Override
	public synchronized void addListener(Runnable listener) {
		listeners.add(listener);
	}

	@Override
	public synchronized void removeListener(Runnable listener) {
		try {
			listeners.remove(listener);
		} catch (Exception err) {
			listenersToRemove.add(listener);
		}
	}

	@Override
	public void invoke() {
		for (var listener : listeners) {
			listener.run();
		}
		clearListeners();
	}

	private void clearListeners() {
		Iterator<Runnable> iterator = listenersToRemove.iterator();
		while (iterator.hasNext()) {
			Runnable listener = iterator.next();
			listeners.remove(listener);
			iterator.remove();
		}
	}
}
