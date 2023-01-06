package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.*;

public interface Collection<T> extends Iterable<T> {
	boolean add(T element);

	boolean remove(Object pattern);

	boolean isEmpty();

	int size();

	boolean contains(T pattern);
	
	default T[] toArray(T[] ar) {
		if (ar.length < size()) {
			ar = Arrays.copyOf(ar, size());
		}
		int index = 0;
		for (T t : this) {
			ar[index++] = t;
		}
		Arrays.fill(ar, size(), ar.length, null);
		return ar;
	}
	
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
