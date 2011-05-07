package at.jotschi.quadtree;

import java.awt.Dimension;
import java.awt.Point;

@SuppressWarnings("serial")
public class BoundsNodeElement<T> extends NodeElement<T> {

	protected Dimension bounds;

	public BoundsNodeElement(Point coordinates, Dimension bounds, T element) {
		super(coordinates, element);
		this.bounds = bounds;
	}

	public int getWidth() {
		return this.bounds.width;
	}

	public int getHeight() {
		return this.bounds.height;
	}

}
