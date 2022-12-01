package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.shapes.Rectangle;

class ShapeTest {

	@Test
	void rectangleTest() {
		Rectangle rectangle = new Rectangle(50, 10);
		drawLines(rectangle.presentation(15));
	}
	
	private void drawLines(String[] lines) {
		for (String line : lines) {
			System.out.println(line);
		}
	}

}
