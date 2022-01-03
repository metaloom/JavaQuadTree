package io.metaloom.quadtree;

import io.metaloom.quadtree.point.Point;

public abstract class AbstractQuadTree<T> implements QuadTree<T> {

	protected Size size;
	protected Point startCoordinates;

	public AbstractQuadTree(Point startCoordinates, Size size) {
		this.size = size;
		this.startCoordinates = startCoordinates;
	}

	@Override
	public Size getSize() {
		return size;
	}

	@Override
	public Point getStartCoordinates() {
		return startCoordinates;
	}

}
