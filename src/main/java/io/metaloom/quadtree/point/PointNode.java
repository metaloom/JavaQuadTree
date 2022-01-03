package io.metaloom.quadtree.point;

import java.util.Map;
import java.util.Vector;

import io.metaloom.quadtree.Cell;
import io.metaloom.quadtree.Node;

public interface PointNode<T> extends Node<T> {

	/**
	 * Returns all elements for this node
	 * 
	 * @return
	 */
	Vector<PointNodeElement<T>> getElements();

	/**
	 * Returns all elements within the cell that matches the given coordinates
	 * 
	 * @param coordinates
	 * @return
	 */
	Vector<PointNodeElement<T>> getElements(Point coordinates);

	@Override
	Map<Cell, ? extends PointNode<T>> getSubNodes();

	/**
	 * Insert the element into this node. If needed a subdivision will be performed
	 * 
	 * @param element
	 * @return true when the element could be inserted
	 */
	boolean insert(PointNodeElement<T> element);
}
