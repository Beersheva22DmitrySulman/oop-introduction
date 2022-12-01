package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.shapes.Rectangle;

class ShapeTest {

	@Test
	void rectangleTest() {
		Rectangle rectangle = new Rectangle(50, 10);
		assertEquals(50, rectangle.getWidth());
		assertEquals(10, rectangle.getHeight());
		displayStrings(rectangle.presentation(15));
		Rectangle.setSymbol("#");
		displayStrings(rectangle.presentation(15));
	}
	
	private void displayStrings(String[] lines) {
		for (String line : lines) {
			System.out.println(line);
		}
	}

}
