package telran.shapes;

public class Rectangle extends Shape {

	
	public Rectangle(int width, int height) {
		super(width, height);
	}
	
	public String[] presentation(int offset) {
		String[] lines = new String[height];
		String line = getLine(offset);
		lines[0] = line;
		int lastLine = height - 1;
		lines[lastLine] = line;
		for (int i = 1; i < lastLine; i++) {
			lines[i] = getMiddleLine(offset);
		}
		return lines;
	}

	private String getMiddleLine(int offset) {
		return getOffset(offset) + symbol + getOffset(width - 2) + symbol;
	}

	protected String getLine(int offset) {
		return getOffset(offset) + symbol.repeat(width);
	}
}
