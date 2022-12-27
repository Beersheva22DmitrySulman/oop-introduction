package telran.util;

public interface List<T> extends Collection<T> {
	void add(int index, T element);

	T remove(int index);
	
	int indexOf(Object pattern);

	int lastIndexOf(Object pattern);
	
	T get(int index);
	
	T set(int index, T element);
}
