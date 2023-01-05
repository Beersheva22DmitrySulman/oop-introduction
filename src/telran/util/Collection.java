package telran.util;

import java.util.Iterator;
import java.util.function.*;

public interface Collection<T> extends Iterable<T> {
	boolean add(T element);

	boolean remove(Object pattern);

	boolean isEmpty();

	int size();

	boolean contains(T pattern);
	
	T[] toArray(T[] ar);
	
	default boolean removeIf(Predicate<? super T> predicate) {
		Iterator<T> iterator = iterator();
		int oldSize = size();
		while(iterator.hasNext()) {
			T obj = iterator.next();
			if (predicate.test(obj)) {
				iterator.remove();
			}
		}
		return size() != oldSize;
	}
}
