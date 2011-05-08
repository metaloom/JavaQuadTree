package at.jotschi.quadtree.impl;

import java.awt.Dimension;
import java.awt.Point;

import at.jotschi.quadtree.AbstractQuadTree;

/**
 * Creates a new QuadTree that can hold the given type of elements. Each element
 * can also have a certain dimension.
 * 
 * @author jotschi
 * 
 * @param <T>
 */
public class SpacialQuadTree<T> extends AbstractQuadTree<T> {

	protected SpacialNode<T> rootNode;

	/**
	 * Create a new QuadTree with the give start coordinates and size
	 * 
	 * @param startCorrdinates
	 * @param size
	 */
	public SpacialQuadTree(Point startCoordinates, Dimension size) {
		super(startCoordinates, size);
		this.rootNode = new SpacialNode<T>(startCoordinates, size, 0);
	}

	/**
	 * Insert the element with the given size in the next free cell
	 * 
	 * @param element
	 * @param elementSize
	 */
	public void insert(T element, Dimension elementSize) {

		// TODO check element size
		this.rootNode.insert(new SpacialNodeElement<T>(element, elementSize));

	}

	/**
	 * Add a new element to the QuadTree that has a specific dimension/size
	 * 
	 * @param point
	 * @param size
	 * @param element
	 */
	public void insert(Point point, Dimension elementSize, T element) {

		// Check if the element coordinates are within bounds of the quadtree
		if (point.x > startCoordinates.x + size.width
				|| point.x < startCoordinates.x) {
			throw new IndexOutOfBoundsException(
					"The x coordinate must be within bounds of ["
							+ startCoordinates.x + "] to [" + size.width
							+ "] / [" + point.x + "]");
		}
		if (point.y > startCoordinates.y + size.height
				|| point.y < startCoordinates.y) {
			throw new IndexOutOfBoundsException(
					"The y coordinate must be within bounds of ["
							+ startCoordinates.y + "] to [" + size.height
							+ "] / [" + point.y + "]");
		}

		// Check if the right bottom corner is within bounds
		if (point.x + elementSize.width > startCoordinates.x + size.width
				|| point.x < startCoordinates.x) {
			throw new IndexOutOfBoundsException(
					"The x coordinate must be within bounds of ["
							+ startCoordinates.x + "] to [" + size.width
							+ "] / [" + point.x + elementSize.width + "]");
		}

		if (point.y + elementSize.height > startCoordinates.y + size.height
				|| point.y < startCoordinates.y) {
			throw new IndexOutOfBoundsException(
					"The y coordinate must be within bounds of ["
							+ startCoordinates.y + "] to [" + size.height
							+ "] / [" + point.x + elementSize.height + "]");
		}

		this.rootNode.insert(new SpacialNodeElement<T>(point, elementSize,
				element));

	}

	/**
	 * Returns the rootNode of this tree
	 * 
	 * @return
	 */
	public SpacialNode<T> getRootNode() {
		return this.rootNode;
	}

	@Override
	public void clear() {
		this.rootNode.clear();
	}

}
