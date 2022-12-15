package telran.shapes;

import java.util.ArrayList;
import java.util.Arrays;
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
		lines.addAll(List.of(shapes[0].presentation(offset)));
		for (int i = 1; i < shapes.length; i++) {
			fillEmptyLines(lines, margin, width + offset);
			if (shapes[i] instanceof Canvas) {
				((Canvas) shapes[i]).setDirection("column");
			}
			shapes[i].setWidth(width);
			lines.addAll(List.of(shapes[i].presentation(offset)));
		}
		fillEmptyLines(lines, height - lines.size(), width + offset);
		
		return lines.toArray(new String[0]);
	}

	private void fillEmptyLines(List<String> lines, int length, int widthToFill) {
		for (int i = 0; i < length; i++) {
			lines.add(getOffset(widthToFill));
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
