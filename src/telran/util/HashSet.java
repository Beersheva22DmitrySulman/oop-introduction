package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSet<T> extends AbstractCollection<T> implements Set<T> {
	private List<T>[] hashTable;
	private static final int DEFAULT_TABLE_SIZE = 16;
	private static final float DEFAULT_FACTOR = 0.75f;
	private float factor;

	@SuppressWarnings("unchecked")
	public HashSet(int tableSize, float factor) {
		if (tableSize < 1) {
			throw new IllegalArgumentException("Wrong initital size of Hash Table");
		}
		if (factor < 0.3 || factor > 1) {
			throw new IllegalArgumentException("Wrong factor");
		}
		hashTable = new List[tableSize];
		this.factor = factor;
	}

	public HashSet() {
		this(DEFAULT_TABLE_SIZE, DEFAULT_FACTOR);
	}

	@Override
	public boolean add(T element) {
		if (size >= hashTable.length * factor) {
			tableRecreation();
		}
		int index = getHashIndex(element);
		boolean res = false;
		List<T> list = hashTable[index];
		if (list == null) {
			list = new LinkedList<>();
			hashTable[index] = list;
		}
		if (!list.contains(element)) {
			list.add(element);
			res = true;
			size++;
		}

		return res;
	}

	private void tableRecreation() {
		HashSet<T> hashSet = new HashSet<>(hashTable.length * 2, factor);
		for (List<T> list : hashTable) {
			if (list != null) {
				for (T obj : list) {
					hashSet.add(obj);
				}
			}
		}
		hashTable = hashSet.hashTable;
	}

	private int getHashIndex(Object element) {
		return Math.abs(element.hashCode()) % hashTable.length;
	}

	@Override
	public boolean remove(Object pattern) {
		int index = getHashIndex(pattern);
		boolean res = false;
		if (hashTable[index] != null) {
			res = hashTable[index].remove(pattern);
			if (res) {
				if (hashTable[index].isEmpty()) {
					hashTable[index] = null;
				}
				size--;
			}
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		int index = getHashIndex(pattern);
		boolean res = false;
		if (hashTable[index] != null) {
			res = hashTable[index].contains(pattern);
		}
		return res;
	}

	@Override
	public Iterator<T> iterator() {
		return new HashSetIterator();
	}

	private class HashSetIterator implements Iterator<T> {
		boolean flNext = false;
		int current = 0;
		int currentBucketIndex = -1;
		Iterator<T> listIterator = getNextBucketIterator();

		@Override
		public boolean hasNext() {
			return current < size;
		}

		private Iterator<T> getNextBucketIterator() {
			do {
				currentBucketIndex++;
			} while (hashTable[currentBucketIndex] == null);
			
			return hashTable[currentBucketIndex].iterator();
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			if (!listIterator.hasNext()) {
				listIterator = getNextBucketIterator();
			}
			current++;
			flNext = true;

			return listIterator.next();
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			listIterator.remove();
			if (hashTable[currentBucketIndex].isEmpty()) {
				hashTable[currentBucketIndex] = null;
			}
			current--;
			size--;
			flNext = false;
		}
	}
}
