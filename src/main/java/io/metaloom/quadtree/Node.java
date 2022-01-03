package io.metaloom.quadtree;

import java.util.Map;

import io.metaloom.quadtree.point.Point;

public interface Node<T> {

	/**
	 * Returns the startCoordinates for this {@link Node}
	 * 
	 * @return
	 */
	Point getStartCoordinates();

	/**
	 * Returns the max elements
	 * 
	 * @return
	 */
	int getMaxElements();

	/**
	 * Returns the depth of this node
	 * 
	 * @return
	 */
	int getDepth();

	/**
	 * Returns the max depth
	 * 
	 * @return
	 */
	int getMaxDepth();

	void clear();

	void subdivide();

	Map<Cell, ? extends Node<T>> getSubNodes();

	/**
	 * Returns the bounds for this Node
	 * 
	 * @return
	 */
	Size getBounds();

}
