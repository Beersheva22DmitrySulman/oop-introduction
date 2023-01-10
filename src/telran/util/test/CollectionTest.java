package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

public abstract class CollectionTest {
	protected Integer[] numbers = { 10, 100, -5, 134, 280, 120, 15 };
	protected Integer ar[] = new Integer[numbers.length + 100];
	protected Collection<Integer> collection;
	protected Integer[] empty = {};

	@BeforeEach
	void setUp() throws Exception {
		for (Integer num : numbers) {
			collection.add(num);
		}
	}

	abstract void testAdd();

	abstract void testIterator();

	@Test
	void testRemove() {
		Integer[] expected = { 10, 100, -5, 280, 120, 15 };
		assertTrue(collection.remove((Integer) 134));
		Arrays.sort(expected);
		Integer[] actual = collection.toArray(empty);
		Arrays.sort(actual);
		assertArrayEquals(expected, actual);
		assertFalse(collection.remove((Integer) 134));
	}

	@Test
	void testRemoveIf() {
		Integer[] expected = { -5, 15 };
		assertTrue(collection.removeIf(n -> n % 2 == 0));
		Integer[] actual = collection.toArray(empty);
		Arrays.sort(actual);
		assertArrayEquals(expected, actual);
		assertFalse(collection.removeIf(n -> n % 2 == 0));
		assertTrue(collection.removeIf(n -> true));
		assertTrue(collection.isEmpty());

	}

	@Test
	void testIsEmpty() {
		assertFalse(collection.isEmpty());
		collection.removeIf(n -> true);
		assertTrue(collection.isEmpty());
	}

	@Test
	void testSize() {
		assertEquals(numbers.length, collection.size());
	}

	@Test
	void testContains() {
		assertTrue(collection.contains(numbers[0]));
		assertFalse(collection.contains(Integer.MIN_VALUE));
	}

	@Test
	void testToArray() {

		Arrays.fill(ar, 10);
		assertTrue(ar == collection.toArray(ar));
		Arrays.sort(ar, 0, collection.size());
		Arrays.sort(numbers);
		for (int i = 0; i < numbers.length; i++) {
			assertEquals(ar[i], numbers[i]);
		}
		for (int i = numbers.length; i < ar.length; i++) {
			assertNull(ar[i]);
		}
	}

	@Test
	void removeIteratorTest() {
		Iterator<Integer> iterator = collection.iterator();
		assertThrows(IllegalStateException.class, iterator::remove);
		Integer num = iterator.next();
		assertTrue(collection.contains(num));
		iterator.remove();
		assertFalse(collection.contains(num));
		assertEquals(numbers.length - 1, collection.size());
		assertThrows(IllegalStateException.class, iterator::remove);

		iterator = collection.iterator();
		while (iterator.hasNext()) {
			num = iterator.next();
		}
		assertTrue(collection.contains(num));
		iterator.remove();
		assertFalse(collection.contains(num));

		iterator = collection.iterator();
		iterator.next();
		iterator.remove();
		int sizeExpected = collection.size();
		int index = 0;
		while (iterator.hasNext()) {
			num = iterator.next();
			index++;
		}
		assertEquals(sizeExpected, index);
	}
}