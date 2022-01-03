package io.metaloom.quadtree.point;

import io.metaloom.quadtree.point.impl.PointImpl;

/**
 * Representation of a two dimensional point.
 */
public interface Point {

	long x();

	long y();

	void setX(long x);

	void setY(long y);

	/**
	 * Create a new point with the given coordinates.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	static Point of(long x, long y) {
		return new PointImpl(x, y);
	}

	/**
	 * Create a new point based on a {@link java.awt.Point} object data.
	 * 
	 * @param p
	 * @return
	 */
	static Point of(java.awt.Point p) {
		return new PointImpl(p.x, p.y);
	}

}
