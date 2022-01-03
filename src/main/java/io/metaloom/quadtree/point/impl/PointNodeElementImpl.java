package io.metaloom.quadtree.point.impl;


import io.metaloom.quadtree.AbstractNodeElement;
import io.metaloom.quadtree.point.Point;
import io.metaloom.quadtree.point.PointNodeElement;

public class PointNodeElementImpl<T> extends AbstractNodeElement<T> implements PointNodeElement<T> {

	public PointNodeElementImpl(Point coordinates, T element) {
		super(coordinates, element);
	}

}
