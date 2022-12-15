package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.shapes.*;

class ShapeTest {
	
	Canvas canvas = new Canvas(10, 20,
			new Shape[] { new Rectangle(10, 3), new Square(10), new SquareLeftTriangle(10) });
	Shape[] shapes = { new Rectangle(10, 3), new Square(10), new SquareLeftTriangle(10), new SquareRightTriangle(10),
			canvas, new Square(10) };

	@Test
	@Disabled
	void rectangleTest() {
		Rectangle rectangle = new Rectangle(50, 10);
		assertEquals(50, rectangle.getWidth());
		assertEquals(10, rectangle.getHeight());
		displayStrings(rectangle.presentation(15));
		Rectangle.setSymbol("#");
		displayStrings(rectangle.presentation(15));
	}

	@Test
	@Disabled
	void squareTest() {
		Square square = new Square(10);
		assertEquals(10, square.getHeight());
		assertEquals(10, square.getWidth());
		Rectangle.setSymbol("#");
		displayStrings(square.presentation(10));
	}

	@Test
	@Disabled
	void leftTriangleTest() {
		SquareLeftTriangle squareLeftTriangle = new SquareLeftTriangle(10);
		assertEquals(10, squareLeftTriangle.getHeight());
		assertEquals(10, squareLeftTriangle.getWidth());
		displayStrings(squareLeftTriangle.presentation(10));
	}

	@Test
	@Disabled
	void leftRightTest() {
		SquareRightTriangle squareRightTriangle = new SquareRightTriangle(10);
		assertEquals(10, squareRightTriangle.getHeight());
		assertEquals(10, squareRightTriangle.getWidth());
		displayStrings(squareRightTriangle.presentation(10));
	}

	@Test
	@Disabled
	void canvasTest() {
		Rectangle rectangle = new Rectangle(50, 8);
		Square square = new Square(12);
		SquareLeftTriangle squareLeftTriangle = new SquareLeftTriangle(23);
		SquareRightTriangle squareRightTriangle = new SquareRightTriangle(70);

		Shape[] shapes = { rectangle, square, squareLeftTriangle, squareRightTriangle };

		Canvas canvas = new Canvas(10, 50, shapes);
		canvas.setDirection("column");
		canvas.setMargin(1);
		displayStrings(canvas.presentation(5));
		
		canvas.setHeight(10);
		canvas.setDirection("row");
		canvas.setMargin(5);
		displayStrings(canvas.presentation(0));
	}
	
	@Test
	void canvasInRowTest() {
		Canvas canvas = new Canvas(10, 4, shapes);
		canvas.setMargin(3);
		displayStrings(canvas.presentation(2));

	}

	@Test
	void canvasInColumnTest() {
		Canvas canvas = new Canvas(10, 4, shapes);
		canvas.setDirection("column");
		this.canvas.setDirection("column");
		canvas.setMargin(1);
		displayStrings(canvas.presentation(2));

	}

	private void displayStrings(String[] lines) {
		for (String line : lines) {
			System.out.println(line);
		}
	}

}
