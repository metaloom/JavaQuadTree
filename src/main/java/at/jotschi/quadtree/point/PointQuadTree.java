package at.jotschi.quadtree.point;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Vector;

import at.jotschi.quadtree.AbstractNodeElement;
import at.jotschi.quadtree.AbstractQuadTree;

/**
 * Creates a new QuadTree that can hold the given type of elements
 * 
 * @author jotschi
 * 
 * @param <T>
 */
public class PointQuadTree<T> extends AbstractQuadTree<T> {

	protected PointNode<T> rootNode;

	/**
	 * Create a new QuadTree with the give start coordinates and size
	 * 
	 * @param startCorrdinates
	 * @param size
	 */
	public PointQuadTree(Point startCoordinates, Dimension size) {
		super(startCoordinates, size);
		this.rootNode = new PointNode<T>(startCoordinates, size, 0);
	}
	
	public PointQuadTree(Point startCoordinates, Dimension size, int maxDepth, int maxChildren) {
		super(startCoordinates, size);
		this.rootNode = new PointNode<T>(startCoordinates, size, 0,maxDepth,maxChildren);
	}

	/**
	 * Add a new element to the QuadTree
	 * 
	 * @param x
	 * @param y
	 * @param element
	 */
	public void insert(int x, int y, T element) {
		insert(new Point(x, y), element);
	}

	/**
	 * Add a new element to the QuadTree that has a specific dimension/size
	 * 
	 * @param point
	 * @param size
	 * @param element
	 */
	public void insert(Point point, Dimension size, T element) {

		// Check if the element coordinates are within bounds of the quadtree
		if (point.x > startCoordinates.x + size.width
				|| point.x < startCoordinates.x) {
			throw new IndexOutOfBoundsException(
					"The x coordinate must be within bounds of ["
							+ startCoordinates.x + "] to [" + size.width + "]");
		}
		if (point.y > startCoordinates.y + size.height
				|| point.y < startCoordinates.y) {
			throw new IndexOutOfBoundsException(
					"The y coordinate must be within bounds of ["
							+ startCoordinates.y + "] to [" + size.height + "]");
		}

		// Check if the right bottom corner is within bounds
		if (point.x + size.width > startCoordinates.x + size.width
				|| point.x < startCoordinates.x) {
			throw new IndexOutOfBoundsException(
					"The x coordinate must be within bounds of ["
							+ startCoordinates.x + "] to [" + size.width + "]");
		}
		if (point.y + size.width > startCoordinates.y + size.height
				|| point.y < startCoordinates.y) {
			throw new IndexOutOfBoundsException(
					"The y coordinate must be within bounds of ["
							+ startCoordinates.y + "] to [" + size.height + "]");
		}

		this.rootNode.insert(new PointNodeElement<T>(point, element));

	}

	/**
	 * Add a new element to the QuadTree
	 * 
	 * @param point
	 * @param element
	 */
	public void insert(Point point, T element) {

		// Check if the element coordinates are within bounds of the quadtree
		if (point.x > startCoordinates.x + size.width
				|| point.x < startCoordinates.x) {
			throw new IndexOutOfBoundsException(
					"The x coordinate must be within bounds of ["
							+ startCoordinates.x + "] to [" + size.width + "]");
		}
		if (point.y > startCoordinates.y + size.height
				|| point.y < startCoordinates.y) {
			throw new IndexOutOfBoundsException(
					"The y coordinate must be within bounds of ["
							+ startCoordinates.y + "] to [" + size.height + "]");
		}

		this.rootNode.insert(new PointNodeElement<T>(point, element));
	}

	/**
	 * Returns the rootNode of this tree
	 * 
	 * @return
	 */
	public PointNode<T> getRootNode() {
		return this.rootNode;
	}

	/**
	 * Returns all elements wihtin the cell that matches the given coordinates
	 * 
	 * @param coordinates
	 * @return
	 */
	public Vector<? extends AbstractNodeElement<T>> getElements(Point coordinates) {
		return this.rootNode.getElements(coordinates);
	}

	@Override
	public void clear() {
		this.rootNode.clear();
	}
}
