package io.metaloom.quadtree;

import io.metaloom.quadtree.point.Point;

public interface QuadTree<T> {

	/**
	 * Returns the size of the tree
	 * 
	 * @return
	 */
	Size getSize();

	/**
	 * Returns the startCoordinates
	 * 
	 * @return
	 */
	Point getStartCoordinates();

	/**
	 * Clear the QuadTree
	 */
	void clear();

	/**
	 * Return the root node of this quad tree
	 * 
	 * @return
	 */
	Node<T> getRootNode();

	/**
	 * Add a new element to the QuadTree that has a specific dimension/size
	 * 
	 * @param point
	 * @param elementSize
	 * @param element
	 * @return true when the element could be inserted
	 */
	boolean insert(Point point, Size elementSize, T element);


}
