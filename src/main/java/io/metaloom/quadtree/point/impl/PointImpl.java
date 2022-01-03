package io.metaloom.quadtree.point.impl;

import io.metaloom.quadtree.point.Point;

public class PointImpl implements Point {

	public long x;
	public long y;

	public PointImpl(long x, long y) {
		this.x = x;
		this.y = y;
	}

	public PointImpl(Point point) {
		this.x = point.x();
		this.y = point.y();
	}

	@Override
	public long x() {
		return x;
	}

	@Override
	public long y() {
		return y;
	}

	@Override
	public void setX(long x) {
		this.x = x;
	}

	@Override
	public void setY(long y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "[" + x + ":" + y + "]";
	}
}
