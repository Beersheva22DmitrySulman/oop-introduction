package telran.shapes;

import java.util.ArrayList;
import java.util.List;

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
		List<String> lines = new ArrayList<>();
		lines.addAll(getLinesVertical(shapes[0], offset));
		for (int i = 1; i < shapes.length; i++) {
			fillEmptyLines(lines, margin, width + offset);
			lines.addAll(getLinesVertical(shapes[i], offset));
		}
		fillEmptyLines(lines, height - lines.size(), width + offset);
		return lines.toArray(new String[0]);
	}

	private List<String> getLinesVertical(Shape shape, int offset) {
		if (shape instanceof Canvas) {
			((Canvas) shape).setDirection("column");
		}
		shape.setWidth(width);
		return List.of(shape.presentation(offset));
	}

	private void fillEmptyLines(List<String> lines, int length, int widthToFill) {
		for (int i = 0; i < length; i++) {
			lines.add(getOffset(widthToFill));
		}
	}

	private String[] presentationHorizontal(int offset) {
		String[] res = getLinesHorizontal(shapes[0], offset);
		for (int i = 1; i < shapes.length; i++) {
			String[] shapeLines = getLinesHorizontal(shapes[i], margin);
			for (int j = 0; j < height; j++) {
				res[j] += shapeLines[j];
			}
		}
		return res;
	}

	private String[] getLinesHorizontal(Shape shape, int offset) {
		if (shape instanceof Canvas) {
			((Canvas) shape).setDirection("row");
		}
		shape.setHeight(height);
		return shape.presentation(offset);
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
