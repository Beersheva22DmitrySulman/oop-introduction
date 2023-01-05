package telran.util.test;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

class ArrayListTest extends ListTest {
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new ArrayList<>(2);
		super.setUp();
	}

	@Test
	@Override
	void removeIteratorTest() {
		Iterator<Integer> iterator = collection.iterator();
	}
}