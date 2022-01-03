package io.metaloom.quadtree;

import java.util.Map;
import io.metaloom.quadtree.point.Point;

public abstract class AbstractNode<T> implements Node<T> {

	/**
	 * Default value for amount of elements
	 */
	protected final static int MAX_ELEMENTS = 4;

	/**
	 * Default value for max depth
	 */
	protected final static int MAX_DEPTH = 4;

	protected Size bounds;
	protected Point startCoordinates;
	protected int maxDepth;
	protected int maxElements;
	protected int depth;

	public AbstractNode(Point startCoordinates, Size bounds, int depth) {
		this(startCoordinates, bounds, depth, MAX_ELEMENTS, MAX_DEPTH);
	}

	public AbstractNode(Point startCoordinates, Size bounds, int depth,
		int maxDepth, int maxElements) {
		this.startCoordinates = startCoordinates;
		this.bounds = bounds;
		this.maxDepth = maxDepth;
		this.maxElements = maxElements;
		this.depth = depth;
	}

	@Override
	public Size getBounds() {
		return bounds;
	}

	@Override
	public Point getStartCoordinates() {
		return startCoordinates;
	}

	@Override
	public int getMaxElements() {
		return maxElements;
	}

	@Override
	public int getDepth() {
		return depth;
	}

	@Override
	public int getMaxDepth() {
		return maxDepth;
	}

	@Override
	public abstract void subdivide();

	@Override
	public abstract void clear();

	@Override
	public abstract Map<Cell, ? extends Node<T>> getSubNodes();

}
