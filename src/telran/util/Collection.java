package telran.util;

import java.util.function.*;

public interface Collection<T> extends Iterable<T> {
	boolean add(T element);

	boolean remove(Object pattern);

	boolean isEmpty();

	int size();

	boolean contains(T pattern);
	
	T[] toArray(T[] ar);
	
	boolean removeIf(Predicate<? super T> predicate);
}
