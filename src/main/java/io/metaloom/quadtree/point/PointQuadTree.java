package io.metaloom.quadtree.point;

import java.util.Vector;

import io.metaloom.quadtree.QuadTree;
import io.metaloom.quadtree.Size;

public interface PointQuadTree<T> extends QuadTree<T> {

	/**
	 * Add a new element to the QuadTree that has a specific dimension/size
	 * 
	 * @param point
	 * @param size
	 * @param element
	 */
	boolean insert(Point point, Size size, T element);

	/**
	 * Add a new element to the QuadTree
	 * 
	 * @param x
	 * @param y
	 * @param element
	 * @return true when the element could be inserted
	 */
	boolean insert(int x, int y, T element);

	/**
	 * Add a new element to the QuadTree
	 * 
	 * @param point
	 * @param element
	 * @return true when the element could be inserted
	 */
	boolean insert(Point point, T element);

	/**
	 * Returns the rootNode of this tree
	 * 
	 * @return
	 */
	PointNode<T> getRootNode();

	/**
	 * Returns all elements within the cell that matches the given coordinates
	 * 
	 * @param coordinates
	 * @return
	 */
	Vector<PointNodeElement<T>> getElements(Point coordinates);
}
