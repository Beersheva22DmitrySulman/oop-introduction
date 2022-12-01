package telran.shapes;

public class Rectangle {
	public static final String SYMBOL = "*";
	
	private static String symbol = SYMBOL;
	
	private int width;
	private int height;
	
	public Rectangle (int width, int height) {
		this.width = width;
		this.height = height;
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

	private String getLine(int offset) {
		return getOffset(offset) + symbol.repeat(width);
	}

	private String getOffset(int offset) {
		return " ".repeat(offset);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	public static String getSymbol() {
		return symbol;
	}

	public static void setSymbol(String symbol) {
		Rectangle.symbol = symbol;
	}
}
