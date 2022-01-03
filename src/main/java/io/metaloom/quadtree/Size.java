package io.metaloom.quadtree;

import io.metaloom.quadtree.impl.SizeImpl;
import io.metaloom.quadtree.point.Point;

/**
 * Dimensional representation for quad trees and their elements.
 */
public interface Size {

	long height();

	long width();

	/**
	 * Create a new size which uses the coordinates of the point as dimensional references.
	 * 
	 * @param point
	 * @return
	 */
	static Size of(Point point) {
		return new SizeImpl(point.x(), point.y());
	}

	/**
	 * Create a new size.
	 * 
	 * @param width
	 * @param height
	 * @return
	 */
	static Size of(long width, long height) {
		return new SizeImpl(width, height);
	}
}
