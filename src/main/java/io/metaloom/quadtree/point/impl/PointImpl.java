package io.metaloom.quadtree.point.impl;

import io.metaloom.quadtree.point.Point;

public class PointImpl implements Point {

	public int x;
	public int y;

	public PointImpl(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public PointImpl(Point point) {
		this.x = point.x();
		this.y = point.y();
	}

	@Override
	public int x() {
		return x;
	}

	@Override
	public int y() {
		return y;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

}
