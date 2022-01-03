package io.metaloom.quadtree.point.impl;

import java.util.Vector;

import io.metaloom.quadtree.AbstractQuadTree;
import io.metaloom.quadtree.Size;
import io.metaloom.quadtree.point.Point;
import io.metaloom.quadtree.point.PointQuadTree;

/**
 * Creates a new QuadTree that can hold the given type of elements
 * 
 * @param <T>
 */
public class PointQuadTreeImpl<T> extends AbstractQuadTree<T> implements PointQuadTree<T> {

	protected PointNode<T> rootNode;

	/**
	 * Create a new QuadTree with the give start coordinates and size
	 * 
	 * @param startCorrdinates
	 * @param size
	 */
	public PointQuadTreeImpl(Point startCoordinates, Size size) {
		super(startCoordinates, size);
		this.rootNode = new PointNode<T>(startCoordinates, size, 0);
	}

	public PointQuadTreeImpl(Point startCoordinates, Size size, int maxDepth, int maxChildren) {
		super(startCoordinates, size);
		this.rootNode = new PointNode<T>(startCoordinates, size, 0, maxDepth, maxChildren);
	}

	@Override
	public void insert(int x, int y, T element) {
		insert(Point.of(x, y), element);
	}

	@Override
	public void insert(Point point, Size size, T element) {

		// Check if the element coordinates are within bounds of the quadtree
		if (point.x() > startCoordinates.x() + size.width() || point.x() < startCoordinates.x()) {
			throw new IndexOutOfBoundsException("The x coordinate must be within bounds of [" + startCoordinates.x() + "] to [" + size.width() + "]");
		}
		if (point.y() > startCoordinates.y() + size.height() || point.y() < startCoordinates.y()) {
			throw new IndexOutOfBoundsException(
				"The y coordinate must be within bounds of [" + startCoordinates.y() + "] to [" + size.height() + "]");
		}

		// Check if the right bottom corner is within bounds
		if (point.x() + size.width() > startCoordinates.x() + size.width() || point.x() < startCoordinates.x()) {
			throw new IndexOutOfBoundsException("The x coordinate must be within bounds of [" + startCoordinates.x() + "] to [" + size.width() + "]");
		}
		if (point.y() + size.width() > startCoordinates.y() + size.height() || point.y() < startCoordinates.y()) {
			throw new IndexOutOfBoundsException(
				"The y coordinate must be within bounds of [" + startCoordinates.y() + "] to [" + size.height() + "]");
		}

		rootNode.insert(new PointNodeElement<T>(point, element));

	}

	@Override
	public void insert(T element, Size elementSize) {
		throw new RuntimeException("Not supported for point quad trees");
	}

	@Override
	public void insert(Point point, T element) {

		// Check if the element coordinates are within bounds of the quadtree
		if (point.x() > startCoordinates.x() + size.width() || point.x() < startCoordinates.x()) {
			throw new IndexOutOfBoundsException("The x coordinate must be within bounds of [" + startCoordinates.x() + "] to [" + size.width() + "]");
		}
		if (point.y() > startCoordinates.y() + size.height() || point.y() < startCoordinates.y()) {
			throw new IndexOutOfBoundsException(
				"The y coordinate must be within bounds of [" + startCoordinates.y() + "] to [" + size.height() + "]");
		}

		rootNode.insert(new PointNodeElement<T>(point, element));
	}

	@Override
	public PointNode<T> getRootNode() {
		return rootNode;
	}

	/**
	 * Returns all elements within the cell that matches the given coordinates
	 * 
	 * @param coordinates
	 * @return
	 */
	public Vector<PointNodeElement<T>> getElements(Point coordinates) {
		return rootNode.getElements(coordinates);
	}

	@Override
	public void clear() {
		rootNode.clear();
	}
}
