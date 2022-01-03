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
	protected final static long MAX_DEPTH = 4;

	protected Size bounds;
	protected Point startCoordinates;
	protected long maxDepth;
	protected int maxElements;
	protected long depth;

	public AbstractNode(Point startCoordinates, Size bounds, long depth) {
		this(startCoordinates, bounds, depth, MAX_ELEMENTS, MAX_DEPTH);
	}

	public AbstractNode(Point startCoordinates, Size bounds, long depth, int maxElements, long maxDepth) {
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
	public long getDepth() {
		return depth;
	}

	@Override
	public long getMaxDepth() {
		return maxDepth;
	}

}
