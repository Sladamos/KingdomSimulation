package strategy.buffor;

public interface Buffor<T> {
	void addItem(T item);
	T getItem();
}
