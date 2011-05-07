package at.jotschi.quadtree;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Vector;

/**
 * Creates a new QuadTree that can hold the given type of elements
 * 
 * @author jotschi
 * 
 * @param <T>
 */
public class QuadTree<T> {

	protected Node<T> rootNode;
	protected Dimension size;
	protected Point startCoordinates;

	/**
	 * Create a new QuadTree with the give start coordinates and size
	 * 
	 * @param startCorrdinates
	 * @param size
	 */
	public QuadTree(Point startCoordinates, Dimension size) {

		this.size = size;
		this.startCoordinates = startCoordinates;
		this.rootNode = new Node<T>(startCoordinates, size, 0);
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
		
		//Check if the right bottom corner is within bounds 
		if (point.x+size.width > startCoordinates.x + size.width
				|| point.x < startCoordinates.x) {
			throw new IndexOutOfBoundsException(
					"The x coordinate must be within bounds of ["
							+ startCoordinates.x + "] to [" + size.width + "]");
		}
		if (point.y+size.width > startCoordinates.y + size.height
				|| point.y < startCoordinates.y) {
			throw new IndexOutOfBoundsException(
					"The y coordinate must be within bounds of ["
							+ startCoordinates.y + "] to [" + size.height + "]");
		}
			
		
		this.rootNode.insert(new NodeElement<T>(point, element));
	
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

		this.rootNode.insert(new NodeElement<T>(point, element));
	}

	/**
	 * Clear the QuadTree
	 */
	public void clear() {
		this.rootNode.clear();
	}

	/**
	 * Returns the rootNode of this tree
	 * 
	 * @return
	 */
	public Node<T> getRootNode() {
		return this.rootNode;
	}

	/**
	 * Returns all elements wihtin the cell that matches the given coordinates
	 * 
	 * @param coordinates
	 * @return
	 */
	public Vector<? extends NodeElement<T>> getElements(Point coordinates) {
		return this.rootNode.getElements(coordinates);
	}
}
