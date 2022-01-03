package io.metaloom.quadtree.spatial.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.metaloom.quadtree.AbstractQuadTree;
import io.metaloom.quadtree.Size;
import io.metaloom.quadtree.point.Point;
import io.metaloom.quadtree.spatial.SpatialNode;
import io.metaloom.quadtree.spatial.SpatialQuadTree;

public class SpatialQuadTreeImpl<T> extends AbstractQuadTree<T> implements SpatialQuadTree<T> {

	protected static Logger log = LoggerFactory.getLogger(SpatialQuadTreeImpl.class);

	protected SpatialNode<T> rootNode;

	/**
	 * Create a new QuadTree with the give start coordinates and size
	 * 
	 * @param startCorrdinates
	 * @param size
	 */
	public SpatialQuadTreeImpl(Point startCoordinates, Size size) {
		super(startCoordinates, size);
		this.rootNode = new SpatialNodeImpl<T>(startCoordinates, size, 0);
	}

	public SpatialQuadTreeImpl(Point startCoordinates, Size size, long maxDepth, int maxChildren) {
		super(startCoordinates, size);
		this.rootNode = new SpatialNodeImpl<T>(startCoordinates, size, 0L, maxDepth, maxChildren);
	}
	

	@Override
	public boolean insert(Size elementSize, T element) {
		return rootNode.insert(new SpatialNodeElementImpl<T>(element, elementSize));
	}

	@Override
	public boolean insert(Point point, Size elementSize, T element) {

		// Check if the element coordinates are within bounds of the quadtree
		if (point.x() > startCoordinates.x() + size.width() || point.x() < startCoordinates.x()) {
			throw new IndexOutOfBoundsException(
				"The x coordinate must be within bounds of [" + startCoordinates.x() + "] to [" + size.width() + "] / [" + point.x() + "]");
		}
		if (point.y() > startCoordinates.y() + size.height() || point.y() < startCoordinates.y()) {
			throw new IndexOutOfBoundsException(
				"The y coordinate must be within bounds of [" + startCoordinates.y() + "] to [" + size.height() + "] / [" + point.y() + "]");
		}

		// Check if the right bottom corner is within bounds
		if (point.x() + elementSize.width() > startCoordinates.x() + size.width() || point.x() < startCoordinates.x()) {
			throw new IndexOutOfBoundsException("The x coordinate must be within bounds of [" + startCoordinates.x() + "] to [" + size.width()
				+ "] / [" + point.x() + elementSize.width() + "]");
		}

		if (point.y() + elementSize.height() > startCoordinates.y() + size.height() || point.y() < startCoordinates.y()) {
			throw new IndexOutOfBoundsException("The y coordinate must be within bounds of [" + startCoordinates.y() + "] to [" + size.height()
				+ "] / [" + point.x() + elementSize.height() + "]");
		}

		return rootNode.insert(new SpatialNodeElementImpl<T>(point, elementSize, element));

	}

	@Override
	public SpatialNode<T> getRootNode() {
		return rootNode;
	}

	@Override
	public void clear() {
		rootNode.clear();
	}

}
