package telran.shapes;

public class Canvas extends Shape {
	private String direction = "row";
	private int margin = 5;
	private Shape[] shapes;

	public Canvas(int width, int height, Shape[] shapes) {
		super(width, height);
		this.shapes = shapes;
	}

	@Override
	public String[] presentation(int offset) {
		String[] res = null;
		if ("row".equals(direction)) {
			res = presentationHorizontal(offset);
		} else if ("column".equals(direction)) {
			res = presentationVertical(offset);
		}
		return res;
	}

	private String[] presentationVertical(int offset) {
		String[] res = new String[height];
		shapes[0].setWidth(width);
		String[] shapeLines = shapes[0].presentation(offset);
		System.arraycopy(shapeLines, 0, res, 0, shapeLines.length);
		int position = shapeLines.length;
		for (int i = 1; i < shapes.length; i++) {
			fillEmptyLines(res, position, position + margin, width + offset);
			position += margin;
			shapes[i].setWidth(width);
			shapeLines = shapes[i].presentation(offset);
			System.arraycopy(shapeLines, 0, res, position, shapeLines.length);
			position += shapeLines.length;
		}
		fillEmptyLines(res, position, height, width + offset);
		return res;
	}

	private void fillEmptyLines(String[] res, int start, int end, int widthToFill) {
		for (int i = start; i < end; i++) {
			res[i] = getOffset(widthToFill);
		}
	}

	private String[] presentationHorizontal(int offset) {
		shapes[0].setHeight(height);
		String[] res = shapes[0].presentation(offset);
		for (int i = 1; i < shapes.length; i++) {
			shapes[i].setHeight(height);
			String[] shapeLines = shapes[i].presentation(margin);
			for (int j = 0; j < height; j++) {
				res[j] += shapeLines[j];
			}
		}
		return res;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}
}
