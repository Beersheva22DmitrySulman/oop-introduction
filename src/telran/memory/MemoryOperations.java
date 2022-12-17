package telran.memory;

public class MemoryOperations {
	public static int getMaxAvailableMemory() {
		byte[] ar;
		int left = 0;
		int right = Integer.MAX_VALUE;
		int middle;
		while (left != right - 1) {
			ar = null;
			middle = left + (right - left) / 2;
			try {				
				ar = new byte[middle];
				left = middle;
			} catch (Throwable e) {
				right = middle;
			}
		}
		return left;
	}

}
