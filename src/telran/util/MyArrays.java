package telran.util;

import java.util.Comparator;

public class MyArrays {
	public static <T> void sort(T[] objects, Comparator<T> comparator) {
		int nextIterationLimit = objects.length - 1;
		do {
			nextIterationLimit = moveGreaterRight(objects, nextIterationLimit, comparator);
		} while (nextIterationLimit != -1);
	}

	private static <T> int moveGreaterRight(T[] objects, int iterationLimit, Comparator<T> comparator) {
		int lastSwapIndex = -1;
		for (int j = 0; j < iterationLimit; j++) {
			if (comparator.compare(objects[j + 1], objects[j]) < 0) {
				lastSwapIndex = j;
				swapElements(objects, j, j + 1);
			}
		}
		return lastSwapIndex;
	}
	
	private static <T> void swapElements(T[] objects, int index1, int index2) {
		T swap = objects[index1];
		objects[index1] = objects[index2];
		objects[index2] = swap;
	}
	
	public static <T> int binarySearch(T[] objects, T key, Comparator<T> comparator) {
		int left = 0;
		int right = objects.length - 1;
		int middle = left + (right - left) / 2;
		while(left <= right && comparator.compare(key, objects[middle]) != 0) {
			if (comparator.compare(key, objects[middle]) > 0) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
			middle = left + (right - left) / 2;
		}
		return middle < objects.length && comparator.compare(key, objects[middle]) == 0 ? middle : -1 - left;
	}
}
