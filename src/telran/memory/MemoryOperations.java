package telran.memory;

public class MemoryOperations {
	public static int getMaxAvailableMemory() {
		byte[] ar;
		int l = 0;
		int r = Integer.MAX_VALUE;
		int m;
		while (l != r - 1) {
			ar = null;
			m = l + (r - l) / 2;
			try {				
				ar = new byte[m];
				l = m;
			} catch (Throwable e) {
				r = m;
			}
		}
		return l;
	}

}
