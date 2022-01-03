package io.metaloom.quadtree.spatial;

import io.metaloom.quadtree.QuadTree;
import io.metaloom.quadtree.Size;

/**
 * A QuadTree that can hold the given type of elements. Each element can also have a certain dimension.
 * 
 * @param <T>
 */
public interface SpatialQuadTree<T> extends QuadTree<T> {

	/**
	 * Return the root node of the tree.
	 */
	SpatialNode<T> getRootNode();

	/**
	 * Insert the element with the given size in the next free cell
	 * 
	 * @param elementSize
	 * @param element
	 * @return true when the element could be inserted
	 */
	boolean insert(Size elementSize, T element);
}
