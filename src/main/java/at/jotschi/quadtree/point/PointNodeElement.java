package at.jotschi.quadtree.point;

import java.awt.Point;

import at.jotschi.quadtree.AbstractNodeElement;

@SuppressWarnings("serial")
public class PointNodeElement<T> extends AbstractNodeElement<T> {

	public PointNodeElement(Point coordinates, T element) {
		super(coordinates, element);
	}

}
