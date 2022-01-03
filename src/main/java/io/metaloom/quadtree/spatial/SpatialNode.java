package io.metaloom.quadtree.spatial;

import java.util.Map;

import io.metaloom.quadtree.Cell;
import io.metaloom.quadtree.Node;

public interface SpatialNode<T> extends Node<T> {

	/**
	 * Return the node element which contains the actual data object.
	 * 
	 * @return
	 */
	SpatialNodeElement<T> getElement();

	/**
	 * Insert the element into this node. If needed a subdivision will be performed
	 * 
	 * @param element
	 * @return Returns false when the element could not be inserted
	 * 
	 */
	boolean insert(SpatialNodeElement<T> element);

	/**
	 * Return the subnodes of this parent node.
	 */
	Map<Cell, ? extends SpatialNode<T>> getSubNodes();
}
