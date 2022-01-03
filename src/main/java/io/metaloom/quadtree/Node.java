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
	long getDepth();

	/**
	 * Returns the max depth
	 * 
	 * @return
	 */
	long getMaxDepth();

	/**
	 * Recursively clear all elements
	 */
	void clear();

	/**
	 * Divide this node so that additional sub nodes can be stored.
	 */
	void subdivide();

	/**
	 * Return the sub node data of this node.
	 * 
	 * @return
	 */
	Map<Cell, ? extends Node<T>> getSubNodes();

	/**
	 * Returns the bounds for this Node
	 * 
	 * @return
	 */
	Size getBounds();

}
