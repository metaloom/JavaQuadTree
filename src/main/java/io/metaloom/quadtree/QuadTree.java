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
	 * @param size
	 * @param element
	 */
	void insert(Point point, Size elementSize, T element);

	/**
	 * Insert the element with the given size in the next free cell
	 * 
	 * @param element
	 * @param elementSize
	 */
	void insert(T element, Size elementSize);

}
