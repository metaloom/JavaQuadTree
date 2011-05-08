package at.jotschi.quadtree;

import java.awt.Dimension;
import java.awt.Point;

public abstract class AbstractQuadTree<T> {

	protected Dimension size;
	protected Point startCoordinates;

	public AbstractQuadTree(Point startCoordinates, Dimension size) {
		this.size = size;
		this.startCoordinates = startCoordinates;
	}

	/**
	 * Returns the size
	 * 
	 * @return
	 */
	public Dimension getSize() {
		return this.size;
	}

	/**
	 * Returns the startCoordinates
	 * 
	 * @return
	 */
	public Point getStartCoordinates() {
		return this.startCoordinates;
	}

	/**
	 * Clear the QuadTree
	 */
	public abstract void clear();

	/**
	 * Return the root node of this quad tree
	 * 
	 * @return
	 */
	public abstract AbstractNode<T> getRootNode();

}
