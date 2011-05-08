package at.jotschi.quadtree;

import java.awt.Dimension;
import java.awt.Point;

public abstract class AbstractNode {

	/**
	 * Default value for amount of elements
	 */
	protected final int MAX_ELEMENTS = 4;

	/**
	 * Default value for max depth
	 */
	protected final int MAX_DEPTH = 4;

	public static enum Cell {
		TOP_LEFT, BOTTOM_RIGHT, BOTTOM_LEFT, TOP_RIGHT
	}

	protected Dimension bounds;
	protected Point startCoordinates;
	protected int maxDepth;
	protected int maxElements;
	protected int depth;

	public AbstractNode(Point startCoordinates, Dimension bounds, int depth) {
		this.startCoordinates = startCoordinates;
		this.bounds = bounds;
		this.depth = depth;
		this.maxDepth = MAX_DEPTH;
		this.maxElements = MAX_ELEMENTS;
	}

	public AbstractNode(Point startCoordinates, Dimension bounds, int depth,
			int maxDepth, int maxChildren) {
		this.startCoordinates = startCoordinates;
		this.bounds = bounds;
		this.maxDepth = maxDepth;
		this.maxElements = maxChildren;
		this.depth = depth;
	}

	/**
	 * Returns the bounds for this Node
	 * 
	 * @return
	 */
	public Dimension getBounds() {
		return this.bounds;
	}

	/**
	 * Returns the startCoordinates for this Node
	 * 
	 * @return
	 */
	public Point getStartCoordinates() {
		return this.startCoordinates;
	}

	/**
	 * Returns the max elements
	 * 
	 * @return
	 */
	public int getMaxElements() {
		return this.maxElements;
	}

	/**
	 * Returns the max depth
	 * 
	 * @return
	 */
	public int getMaxDepth() {
		return this.maxDepth;
	}
	
	public abstract void subdivide(); 

}
