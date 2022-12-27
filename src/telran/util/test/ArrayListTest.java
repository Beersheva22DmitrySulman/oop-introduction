package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.util.ArrayList;
import telran.util.List;

class ArrayListTest {

	@Test
	void test() {
		List<String> list = new ArrayList<>();
		list.add("0");
		list.add("1");
		list.add(2, "2");
		assertEquals(3, list.size());
		assertEquals("0", list.get(0));
		assertEquals("1", list.get(1));
		assertEquals("2", list.get(2));
		for (int i = 3; i < 20; i++) {
			list.add(String.valueOf(i));
		}
		assertEquals(20, list.size());

		assertEquals(3, list.indexOf("3"));
		assertEquals(3, list.lastIndexOf("3"));

		list.add(20, "20");
		assertEquals(21, list.size());

		list.remove(0);
		assertEquals(20, list.size());
		assertEquals("1", list.get(0));
		assertEquals("20", list.get(19));
		list.remove(19);
		assertEquals(19, list.size());
		assertEquals("19", list.get(18));

		assertTrue(list.remove("19"));
		assertEquals(18, list.size());
		assertEquals("18", list.get(17));

		assertFalse(list.remove("100"));

		assertTrue(list.remove("10"));
		assertEquals(17, list.size());
		assertEquals("9", list.get(8));
		assertEquals("11", list.get(9));
		assertEquals("18", list.get(16));

		list.add("20");
		list.add("21");
		assertTrue(list.removeIf(s -> s.contains("1") || s.contains("8")));
		assertEquals(8, list.size());
		assertEquals("2", list.get(0));
		assertEquals("9", list.get(6));
		assertEquals("20", list.get(7));

		assertTrue(list.removeIf(s -> s.contains("2") && s.contains("0")));
		assertEquals(7, list.size());
		assertEquals("9", list.get(6));

		assertTrue(list.removeIf(s -> s.contains("2")));
		assertEquals(6, list.size());
		assertEquals("3", list.get(0));
		assertEquals("9", list.get(5));

		list.add(3, null);
		assertEquals(7, list.size());
		assertEquals(3, list.indexOf(null));
		assertEquals("5", list.get(2));
		assertEquals("6", list.get(4));

		list.add(0, "1000");
		list.add(1, "1000");
		assertEquals(9, list.size());
		assertEquals("1000", list.get(0));
		assertEquals("1000", list.get(1));
		assertEquals("3", list.get(2));
		assertEquals("9", list.get(8));
		assertEquals(0, list.indexOf("1000"));
		assertEquals(1, list.lastIndexOf("1000"));

		String[] expectedArray = {"1000", "1000", "3", "4", "5", null, "6", "7", "9"};
		String[] expectedArrayWithNulls = {"1000", "1000", "3", "4", "5", null, "6", "7", "9", null, null};
		
		assertArrayEquals(expectedArray, list.toArray(new String[0]));
		assertArrayEquals(expectedArrayWithNulls, list.toArray(new String[11]));
	}

}
