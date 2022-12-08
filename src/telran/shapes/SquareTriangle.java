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
		lines[size - 1] = getLine(offset);
		int verticalLineIndex = isLeftDiagonal ? 0 : size - 1;
		for (int i = 0; i < size - 1; i++) {
			lines[i] = getOffset(offset) + String.valueOf(getMiddleLine(i, verticalLineIndex));
		}
		return lines;
	}

	private char[] getMiddleLine(int index, int verticalLineIndex) {
		int size = getWidth();
		char[] line = getClearLine();
		int diagonalLineIndex = isLeftDiagonal ? index : size - 1 - index;
		line[verticalLineIndex] = getSymbol().charAt(0);
		line[diagonalLineIndex] = getSymbol().charAt(0);
		return line;
	}
	
	private char[] getClearLine() {
		int size = getWidth();
		char[] res = new char[size];
		for (int i = 0; i < size; i++) {
			res[i] = ' ';
		}
		return res;
	}

	public boolean isLeftDiagonal() {
		return isLeftDiagonal;
	}
}
