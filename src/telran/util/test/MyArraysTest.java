package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.EvenOddComparator;
import telran.util.MyArrays;

class MyArraysTest {
	Integer[] numbers = { 13, 2, -8, 47, 100, 10, -7, 7 };
	String[] strings = { "ab", "abm", "abmb", "abmbc" };

	@Test
	@Disabled
	void sortTest() {
		String[] strings = { "abcd", "lmn", "zz" };
		String[] expected = { "zz", "lmn", "abcd" };
		MyArrays.sort(strings, Comparator.comparingInt(String::length));
		assertArrayEquals(expected, strings);
	}

	@Test
	@Disabled
	void evenOddTest() {
		Integer[] expected = { -8, 2, 10, 100, 47, 13, 7 };
		MyArrays.sort(numbers, new EvenOddComparator());
		assertArrayEquals(expected, numbers);

		Integer[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		Integer[] numsExpected = { 2, 4, 6, 8, 10, 9, 7, 5, 3, 1 };
		MyArrays.sort(nums, new EvenOddComparator());
		assertArrayEquals(numsExpected, nums);
	}

	@Test
	@Disabled
	void binarySearchTest() {
		Integer[] nums1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		assertEquals(4, MyArrays.binarySearch(nums1, 5, Integer::compare));

		Integer[] nums2 = { 1, 2, 3, 4, 6, 7, 8, 9, 10 };
		assertEquals(-5, MyArrays.binarySearch(nums2, 5, Integer::compare));

		assertEquals(-10, MyArrays.binarySearch(nums2, 100, Integer::compare));

		assertEquals(-1, MyArrays.binarySearch(nums2, 0, Integer::compare));
	}

	@Test
	@Disabled
	void filterTest() {
		int divider = 2;
		String subStr = "m";
		Predicate<Integer> predEven = new DividerPredicate(divider);
		Predicate<String> predSubstr = new SubstrPredicate(subStr);

		Integer[] expectedNumbers = { 2, -8, 100, 10 };
		String[] expectedStr = { "abm", "abmb", "abmbc" };

		assertArrayEquals(expectedNumbers, MyArrays.filter(numbers, predEven));
		assertArrayEquals(expectedStr, MyArrays.filter(strings, predSubstr));
	}

	@Test
	void removeIfTest() {
		Integer[] expectedNumbers = { 13, 47, -7, 7 };
		String[] expectedStr = { "ab" };

		assertArrayEquals(expectedNumbers, MyArrays.removeIf(numbers, x -> x % 2 == 0));
		assertArrayEquals(expectedStr, MyArrays.removeIf(strings, x -> x.contains("m")));
	}

	@Test
	void removeRepeatedTest() {
		Integer[] nums1 = { 0, 1, 1, 2, 2, 1, 1, 3, 3, -10, 15, 1, 22, 23, 15, 7, 8, 0 };
		Integer[] expectedNums1 = { 0, 1, 2, 3, -10, 15, 22, 23, 7, 8 };

		Integer[] nums2 = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		Integer[] expectedNums2 = { 1 };

		Integer[] nums3 = { 1, 2, 3, 4, 5, 6 };
		Integer[] expectedNums3 = { 1, 2, 3, 4, 5, 6 };

		assertArrayEquals(expectedNums1, MyArrays.removeRepeated(nums1));
		assertArrayEquals(expectedNums2, MyArrays.removeRepeated(nums2));
		assertArrayEquals(expectedNums3, MyArrays.removeRepeated(nums3));
	}

	@Test
	void containsTest() {
		assertTrue(MyArrays.contains(numbers, 47));
		assertFalse(MyArrays.contains(numbers, 48));

		assertTrue(MyArrays.contains(strings, "ab"));
		assertFalse(MyArrays.contains(strings, "abc"));
	}

}
