package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

public class SetTest extends CollectionTest {
	Set<Integer> set;

	@BeforeEach
	void setUp() throws Exception {
		super.setUp();
		set = (Set<Integer>) collection;
	}
	
	@Override
	@Test
	void testAdd() {
		assertTrue(set.add(Integer.MAX_VALUE));
		assertFalse(set.add(Integer.MAX_VALUE));
	}

	@Override
	@Test
	void testIterator() {
		Integer[] testNumbers = { 10, 100, -5, 134, 280, 120, 15, 16, 17, 177, 300, 9, 23, 7, 53, 113 };
		Set<Integer> testSet = new HashSet<>(4, 0.75f);
		for (Integer num : testNumbers) {
			testSet.add(num);
		}
		
		Integer[] array = new Integer[testNumbers.length];
		int index = 0;
		Iterator<Integer> iterator = testSet.iterator();
		while (iterator.hasNext()) {
			array[index++] = iterator.next();
		}

		assertEquals(testSet.size(), index);
		Arrays.sort(testNumbers);
		Arrays.sort(array);
		assertArrayEquals(testNumbers, array);
		assertThrows(NoSuchElementException.class, iterator::next);
	}

}
