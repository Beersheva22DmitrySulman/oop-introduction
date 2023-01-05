package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.LinkedList;

class HasLoopTest extends ListTest {
	LinkedList<Integer> linkedList;
	
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new LinkedList<>();
		super.setUp();
		linkedList = (LinkedList<Integer>) collection; 
	}
	
	@Test
	void hasLoopTest() {
		assertFalse(linkedList.hasLoop());
		linkedList.setNext(3, 2);
		assertTrue(linkedList.hasLoop());
	}

}
