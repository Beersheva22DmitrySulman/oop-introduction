package telran.util;

public interface List<T> extends Collection<T> {
	void add(int index, T element);

	T remove(int index);
	
	int indexOf(Object pattern);

	int lastIndexOf(Object pattern);
	
	T get(int index);
	
	T set(int index, T element);
	
	default void checkIndex(int index, boolean add) {
		int rightBound = add ? size() : size() - 1;
		if (index < 0 || index > rightBound) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	default boolean contains(T pattern) {
		return indexOf(pattern) != -1;
	}
	
	default boolean isEmpty() {
		return size() == 0;
	}
	
	default boolean remove(Object pattern) {
		int index = indexOf(pattern);
		if (index != -1) {
			remove(index);
		}
		return index != -1;
	}
}
