package strategy.buffer;

public interface Buffer<T> {
	void addItem(T item);
	T getItem();
}
