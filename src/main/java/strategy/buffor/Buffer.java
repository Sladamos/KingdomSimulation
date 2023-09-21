package strategy.buffor;

public interface Buffer<T> {
	void addItem(T item);
	T getItem();
}
