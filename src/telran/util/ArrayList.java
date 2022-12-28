package telran.util;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {

	private static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size;

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public boolean add(T element) {
		if (size == array.length) {
			reallocate();
		}
		array[size++] = element;
		return true;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	@Override
	public boolean remove(Object pattern) {
		boolean res = false;
		int index = indexOf(pattern);
		if (index != -1) {
			remove(index);
			res = true;
		}
		return res;
	}

	@Override
	public boolean removeIf(Predicate<? super T> predicate) {
		Objects.requireNonNull(predicate);
		int[] indexesToRemove = new int[size];
		int index = 0;
		for (int i = 0; i < size; i++) {
			if (predicate.test(array[i])) {
				indexesToRemove[index++] = i;
			}
		}
		for (int i = index - 1; i >= 0; i--) {
			remove(indexesToRemove[i]);
		}
		return index != 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(T pattern) {
		return indexOf(pattern) != -1;
	}

	@Override
	public T[] toArray(T[] ar) {
		if (ar.length < size) {
			ar = Arrays.copyOf(ar, size);	
		}
		System.arraycopy(array, 0, ar, 0, size);
		fillWithNulls(ar);
		return ar;
	}

	private void fillWithNulls(T[] ar) {
		for (int i = size; i < ar.length; i++) {
			ar[i] = null;
		}	
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, true);
		if (size == array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = element;
		size++;
	}

	@Override
	public T remove(int index) {
		T res = get(index);
		System.arraycopy(array, index + 1, array, index, size - index - 1);
		size--;
		return res;
	}

	@Override
	public int indexOf(Object pattern) {
		int index = 0;
		while (index < size && !checkEquals(index, pattern)) {
			index++;
		}

		return index == size ? -1 : index;
	}

	private boolean checkEquals(int index, Object pattern) {
		return pattern == null ? array[index] == null : pattern.equals(array[index]);
	}

	@Override
	public int lastIndexOf(Object pattern) {
		int index = size - 1;
		while (index >= 0 && !checkEquals(index, pattern)) {
			index--;
		}
		return index;
	}

	@Override
	public T get(int index) {
		checkIndex(index, false);
		return array[index];
	}

	private void checkIndex(int index, boolean add) {
		int rightBound = add ? size : size - 1;
		if (index < 0 || index > rightBound) {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public T set(int index, T element) {
		T res = get(index);
		array[index] = element;
		return res;
	}

}
