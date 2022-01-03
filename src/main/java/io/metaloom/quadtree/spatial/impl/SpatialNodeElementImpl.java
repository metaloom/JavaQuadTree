package io.metaloom.quadtree.spatial.impl;

import io.metaloom.quadtree.AbstractNodeElement;
import io.metaloom.quadtree.Size;
import io.metaloom.quadtree.point.Point;
import io.metaloom.quadtree.spatial.SpatialNodeElement;

public class SpatialNodeElementImpl<T> extends AbstractNodeElement<T> implements SpatialNodeElement<T> {

	protected Size elementSize;

	public SpatialNodeElementImpl(T element, Size elementSize) {
		super(element);
		this.elementSize = elementSize;
	}

	public SpatialNodeElementImpl(Point coordinates, Size elementSize, T element) {
		super(coordinates, element);
		this.elementSize = elementSize;
	}

	@Override
	public long getWidth() {
		return elementSize.width();
	}

	@Override
	public long getHeight() {
		return elementSize.height();
	}

	@Override
	public String toString() {
		return "[" + elementSize + "] @ [" + x() + ":" + y() + "] => " + getElement();
	}
}
