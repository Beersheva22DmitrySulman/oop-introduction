package telran.shapes;

public class Canvas extends Shape {
	private String direction;
	private int margin = 5;
	private Shape[] shapes;

	public Canvas(int width, int height, Shape[] shapes) {
		super(width, height);
		this.shapes = shapes;
		setDirection("row");
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
		String[] lines = new String[height];
		int position = 0;
		String[] shapeLines = shapes[0].presentation(offset);
		System.arraycopy(shapeLines, 0, lines, 0, shapeLines.length);
		position += shapeLines.length;
		for (int i = 1; i < shapes.length; i++) {
			fillEmptyLines(lines, position, position + margin, width + offset);
			position += margin;
			shapeLines = shapes[i].presentation(offset);
			System.arraycopy(shapeLines, 0, lines, position, shapeLines.length);
			position += shapeLines.length;
		}
		fillEmptyLines(lines, position, height, width + offset);
		return lines;
	}

	private void fillEmptyLines(String[] lines, int start, int end, int widthToFill) {
		for (int i = start; i < end; i++) {
			lines[i] = getOffset(widthToFill);
		}
	}

	private String[] presentationHorizontal(int offset) {
		String[] res = shapes[0].presentation(offset);
		for (int i = 1; i < shapes.length; i++) {
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
		int newHeight = 0;
		for (Shape shape : shapes) {
			if ("row".equals(direction)) {
				shape.setHeight(height);
			} else if ("column".equals(direction)) {
				shape.setWidth(width);
			}
			if (shape instanceof Canvas) {
				((Canvas) shape).setDirection(direction);
			}
			newHeight += shape.getHeight();
		}
		if ("column".equals(direction)) {
			newHeight += margin * (shapes.length - 1);
			height = Math.max(height, newHeight);
		}
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}
}
