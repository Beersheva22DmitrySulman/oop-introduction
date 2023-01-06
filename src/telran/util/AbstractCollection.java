package telran.util;

import java.util.Iterator;
import java.util.function.Predicate;

public abstract class AbstractCollection<T> implements Collection<T> {
	protected int size;
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	protected boolean isEqual(T currentObj, Object pattern) {
		return currentObj == null ? pattern == null : currentObj.equals(pattern);
	}
}
