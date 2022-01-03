package io.metaloom.quadtree;

import io.metaloom.quadtree.point.Point;

/**
 * Container class that holds the object within the quadtree
 */
public abstract class AbstractNodeElement<T> implements NodeElement<T> {

	private T element;

	private Point coordinates;

	/**
	 * Create a new NodeElement that holds the element at the given coordinates.
	 * 
	 * @param coordinates
	 * @param element
	 */
	public AbstractNodeElement(Point coordinates, T element) {
		this.coordinates = coordinates;
		this.element = element;
	}

	public AbstractNodeElement(T element) {
		this.element = element;
		this.coordinates = Point.of(0, 0);
	}

	@Override
	public T getElement() {
		return element;
	}

	@Override
	public long x() {
		return coordinates.x();
	}

	@Override
	public long y() {
		return coordinates.y();
	}

	@Override
	public void setX(long x) {
		coordinates.setX(x);
	}

	@Override
	public void setY(long y) {
		coordinates.setY(y);
	}
}
