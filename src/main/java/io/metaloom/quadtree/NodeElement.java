package io.metaloom.quadtree;

import io.metaloom.quadtree.point.Point;

public interface NodeElement<T> extends Point {

	/**
	 * Returns the element that is contained within this NodeElement
	 * 
	 * @return
	 */
	T getElement();

}
