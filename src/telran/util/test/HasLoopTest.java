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
	void hasLoopTest1() {
		assertFalse(linkedList.hasLoop());
		linkedList.setNext(5, 2);
		assertTrue(linkedList.hasLoop());
	}

	@Test
	void hasLoopTest2() {
		assertFalse(linkedList.hasLoop());
		linkedList.setNext(5, 1);
		assertTrue(linkedList.hasLoop());
	}
	
	@Test
	void hasLoopTest3() {
		assertFalse(linkedList.hasLoop());
		linkedList.setNext(6, 0);
		assertTrue(linkedList.hasLoop());
	}
	
	@Test
	void hasLoopTest4() {
		assertFalse(linkedList.hasLoop());
		linkedList.setNext(6, 5);
		assertTrue(linkedList.hasLoop());
	}
}
