package telran.shapes;

public abstract class Shape {
	public static final String SYMBOL = "*";
	
	protected static String symbol = SYMBOL;
	
	protected int width;
	protected int height;
	
	public Shape (int width, int height) {
		this.width = width;
		this.height = height;
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
		Shape.symbol = symbol;
	}
	
	protected String getOffset(int offset) {
		return " ".repeat(offset);
	}
	
	abstract public String[] presentation(int offset);
}
