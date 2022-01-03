package io.metaloom.quadtree;

import io.metaloom.quadtree.impl.SizeImpl;
import io.metaloom.quadtree.point.Point;

public interface Size {

	int height();

	int width();

	static Size of(Point point) {
		return new SizeImpl(point.x(), point.y());
	}

	static Size of(int width, int height) {
		return new SizeImpl(width, height);
	}
}
