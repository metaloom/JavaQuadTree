package io.metaloom.quadtree.point.impl;


import io.metaloom.quadtree.AbstractNodeElement;
import io.metaloom.quadtree.point.Point;

public class PointNodeElement<T> extends AbstractNodeElement<T> {

	public PointNodeElement(Point coordinates, T element) {
		super(coordinates, element);
	}

}
