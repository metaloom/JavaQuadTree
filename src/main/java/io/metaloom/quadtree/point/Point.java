package io.metaloom.quadtree.point;

import io.metaloom.quadtree.point.impl.PointImpl;

/**
 * Representation of a two dimensional point.
 */
public interface Point {

	int x();

	int y();

	void setX(int x);

	void setY(int y);

	static Point of(int x, int y) {
		return new PointImpl(x, y);
	}

	static Point of(java.awt.Point p) {
		return new PointImpl(p.x, p.y);
	}

}
