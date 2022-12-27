package telran.util;

import java.util.function.*;

public interface Collection<T> {
	boolean add(T element);

	boolean remove(Object pattern);

	boolean removeIf(Predicate<? super T> predicate);

	boolean isEmpty();

	int size();

	boolean contains(T pattern);
	
	T[] toArray(T[] ar);
}
