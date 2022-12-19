package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import telran.util.IntSortComparator;
import telran.util.MyArrays;

class MyArraysTest {

	@Test
	void sortTest() {
		String[] strings = {"abcd", "lmn", "zz"};
		String[] expected = {"zz", "lmn", "abcd"};
		MyArrays.sort(strings, Comparator.comparingInt(String::length));
		assertArrayEquals(expected, strings);
		
		Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		Integer[] numsExpected = {2, 4, 6, 8, 10, 9, 7, 5, 3, 1};
		
		MyArrays.sort(nums, new IntSortComparator());
		assertArrayEquals(numsExpected, nums);
	}
	
	@Test
	void binarySearchTest() {		
		Integer[] nums1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		assertEquals(4, MyArrays.binarySearch(nums1, 5, Integer::compare));
		
		Integer[] nums2 = {1, 2, 3, 4, 6, 7, 8, 9, 10};
		assertEquals(-5, MyArrays.binarySearch(nums2, 5, Integer::compare));
		
		assertEquals(-10, MyArrays.binarySearch(nums2, 100, Integer::compare));
		
		assertEquals(-1, MyArrays.binarySearch(nums2, 0, Integer::compare));
	}

}
