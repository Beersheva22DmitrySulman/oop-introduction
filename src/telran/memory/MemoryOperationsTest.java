package telran.memory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class MemoryOperationsTest {
	byte[] ar;
	int maxMemory;
	
	@Test
	@Disabled
	void maxMemoryTest() {
		int maxMemory = MemoryOperations.getMaxAvailableMemory();
		ar = new byte[maxMemory];
		ar = null;
		boolean flException = false;
		try {
			ar = new byte[maxMemory + 1];
		} catch (Throwable e) {
			flException = true;
		}
		System.out.println(maxMemory);
		assertTrue(flException);
	}

	@Test
	void standartMemoryMethods() {
		Runtime runtime = Runtime.getRuntime();
		System.out.printf("Maximal memory JVM may require from OS: %d, "
				+ "current total JVM memory: %d, "
				+ "current free JVM memory: %d\n", 
				runtime.maxMemory(), runtime.totalMemory(), runtime.freeMemory());
	}
}
