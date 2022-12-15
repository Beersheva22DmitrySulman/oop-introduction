package telran.memory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MemoryOperationsTest {
	byte[] ar;
	int maxMemory;
	
	@Test
	void maxMemoryTest() {
		int maxMemory = MemoryOperations.getMaxAvailableMemory();
		ar = new byte[maxMemory];
	}

}
