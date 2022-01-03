package io.metaloom.quadtree.point;

import io.metaloom.quadtree.QuadTree;
import io.metaloom.quadtree.Size;
import io.metaloom.quadtree.point.impl.PointNode;

public interface PointQuadTree<T> extends QuadTree<T> {

	/**
	 * Add a new element to the QuadTree that has a specific dimension/size
	 * 
	 * @param point
	 * @param size
	 * @param element
	 */
	void insert(Point point, Size size, T element);

	/**
	 * Add a new element to the QuadTree
	 * 
	 * @param x
	 * @param y
	 * @param element
	 */
	void insert(int x, int y, T element);

	/**
	 * Add a new element to the QuadTree
	 * 
	 * @param point
	 * @param element
	 */
	void insert(Point point, T element);

	/**
	 * Returns the rootNode of this tree
	 * 
	 * @return
	 */
	PointNode<T> getRootNode();
}
