package telran.shapes;

public class SquareTriangle extends Square {
	private boolean isLeftDiagonal;

	protected SquareTriangle(int size, boolean isLeftDiagonal) {
		super(size);
		this.isLeftDiagonal = isLeftDiagonal;
	}

	@Override
	public String[] presentation(int offset) {
		int size = getWidth();
		String[] lines = new String[size];
		int verticalLine = (isLeftDiagonal ? 0 : size - 1) + offset;
		for (int i = 0; i < size - 1; i++) {
			char[] line = clearLine(size, offset);
			int diagonalLine = (isLeftDiagonal ? i : size - 1 - i) + offset;
			line[verticalLine] = getSymbol().charAt(0);
			line[diagonalLine] = getSymbol().charAt(0);
			lines[i] = new String(line);
		}
		lines[size - 1] = new String(line(size, offset));
		return lines;
	}
	
	private char[] clearLine(int size, int offset) {
		char[] res = new char[size + offset];
		for (int i = 0; i < size + offset; i++) {
			res[i] = ' ';
		}
		return res;
	}
	
	private char[] line(int size, int offset) {
		char[] res = new char[size + offset];
		for (int i = 0; i < offset; i++) {
			res[i] = ' ';
		}
		for (int i = offset; i < size + offset; i++) {
			res[i] = getSymbol().charAt(0);
		}
		return res;
	}
}
