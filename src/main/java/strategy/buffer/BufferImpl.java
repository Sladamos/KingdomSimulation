package strategy.buffer;

import java.util.ArrayDeque;
import java.util.Deque;

public class BufferImpl<T> implements SwitchableBuffer<T> {

	private final Deque<T> buffor;

	private boolean isWorking;

	public BufferImpl() {
		buffor = new ArrayDeque<>();
		isWorking = false;
	}

	@Override
	public synchronized void addItem(T item) {
		if (isWorking) {
			buffor.push(item);
			notifyAll();
		}
	}

	@Override
	public synchronized T getItem() {
		waitForItemInStorage();
		if (!isWorking) {
			throw new BufferTerminatedException();
		}
		return buffor.pop();
	}

	@Override
	public synchronized void enableAcceptingItems() {
		isWorking = true;
	}

	@Override
	public synchronized void disableAcceptingItems() {
		isWorking = false;
		notifyAll();
	}

	private void waitForItemInStorage() {
		while(buffor.isEmpty() && isWorking) {
			try {
				wait();
			}
			catch (InterruptedException ignored) {
			}
		}
	}
}
