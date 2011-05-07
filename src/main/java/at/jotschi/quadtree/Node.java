package at.jotschi.quadtree;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

/**
 * Node that represents each 'cell' within the quadtree. The Node will contains
 * elements {@link NodeElement} that itself will contain the final data within
 * the tree.
 * 
 * @author jotschi
 * 
 * @param <T>
 */
public class Node<T> {

	protected static Logger log = Logger.getLogger(Node.class);

	public static enum Cell {
		TOP_LEFT, BOTTOM_RIGHT, BOTTOM_LEFT, TOP_RIGHT
	}

	protected Dimension bounds;
	protected Point startCoordinates;
	protected int maxDepth;
	protected int maxElements;
	protected int depth;

	/**
	 * Default value for amount of elements
	 */
	protected final int MAX_ELEMENTS = 4;

	/**
	 * Default value for max depth
	 */
	protected final int MAX_DEPTH = 4;

	protected Map<Cell, Node<T>> nodes = new HashMap<Cell, Node<T>>();

	/**
	 * Holds all elements for this node
	 */
	private Vector<NodeElement<T>> elements = new Vector<NodeElement<T>>();

	public Node(Point startCoordinates, Dimension bounds, int depth) {
		log.debug("Creating new Node at depth " + depth);
		this.startCoordinates = startCoordinates;
		this.bounds = bounds;
		this.depth = depth;
		this.maxDepth = MAX_DEPTH;
		this.maxElements = MAX_ELEMENTS;

	}

	/**
	 * 
	 * @param startCoordinates
	 * @param bounds
	 * @param depth
	 * @param maxDepth
	 * @param maxChildren
	 */
	public Node(Point startCoordinates, Dimension bounds, int depth,
			int maxDepth, int maxChildren) {
		log.debug("Creating new Node at depth " + depth);
		this.startCoordinates = startCoordinates;
		this.bounds = bounds;
		this.maxDepth = maxDepth;
		this.maxElements = maxChildren;
		this.depth = depth;

	}

	/**
	 * Returns the subnodes of this node
	 * 
	 * @return
	 */
	public Map<Cell, Node<T>> getSubNodes() {
		return this.nodes;
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

	/**
	 * Returns the cell of this element
	 * 
	 * @param element
	 * @return
	 */
	private Cell findIndex(Point coordinates) {
		// Compute the sector for the coordinates
		boolean left = (coordinates.x > (startCoordinates.x + bounds.width / 2)) ? false
				: true;
		boolean top = (coordinates.y > (startCoordinates.y + bounds.height / 2)) ? false
				: true;

		// top left
		Cell index = Cell.TOP_LEFT;
		if (left) {
			// left side
			if (!top) {
				// bottom left
				index = Cell.BOTTOM_LEFT;
			}
		} else {
			// right side
			if (top) {
				// top right
				index = Cell.TOP_RIGHT;
			} else {
				// bottom right
				index = Cell.BOTTOM_RIGHT;

			}
		}
		log.debug("Coordinate [" + coordinates.x + "-" + coordinates.y
				+ "] is within " + index.toString() + " at depth " + depth);
		return index;
	}

	/**
	 * Returns all elements for this node
	 * 
	 * @return
	 */
	public Vector<? extends NodeElement<T>> getElements() {
		return this.elements;
	}

	/**
	 * Returns all elements wihtin the cell that matches the given coordinates
	 * 
	 * @param coordinates
	 * @return
	 */
	public Vector<? extends NodeElement<T>> getElements(Point coordinates) {

		// Check if this node has already been subdivided. Therefor this node
		// should contain no elements
		if (nodes.size() > 0) {
			Cell index = findIndex(coordinates);
			Node<T> node = this.nodes.get(index);
			return node.getElements(coordinates);
		} else {
			return this.elements;
		}
	}

	/**
	 * Insert the element into this node. If needed a subdivison will be
	 * performed
	 * 
	 * @param element
	 */
	public void insert(NodeElement<T> element) {
		log.debug("Inserting element into Node at depth " + depth);
		// If this Node has already been subdivided just add the elements to the
		// appropriate cell
		if (this.nodes.size() != 0) {
			Cell index = findIndex(element);
			log.debug("Inserting into existing cell: " + index);
			this.nodes.get(index).insert(element);
			return;
		}

		// Add the element to this node
		this.elements.add(element);

		// Only subdivide the node if it contain more than MAX_CHILDREN and is
		// not the deepest node
		if (!(this.depth >= MAX_DEPTH) && this.elements.size() > MAX_ELEMENTS) {
			this.subdivide();

			// Recall insert for each element. This will move all elements of
			// this node into the new nodes at the appropriate cell
			for (NodeElement<T> current : elements) {
				this.insert(current);
			}
			// Remove all elements from this node since they were moved into
			// subnodes
			this.elements.clear();

		}

	}

	/**
	 * Subdivide the current node and add subnodes
	 */
	public void subdivide() {
		log.debug("Subdividing node at depth " + depth);
		int depth = this.depth + 1;

		int bx = this.startCoordinates.x;
		int by = this.startCoordinates.y;

		// Create the bounds for the new cell
		Dimension newBounds = new Dimension(this.bounds.width / 2,
				this.bounds.height / 2);

		// Add new bounds to current start coordinates to calculate the new
		// start coordinates
		int newXStartCoordinate = bx + newBounds.width;
		int newYStartCoordinate = by + newBounds.height;

		Node<T> cellNode = null;

		// top left
		cellNode = new Node<T>(new Point(bx, by), newBounds, depth);
		this.nodes.put(Cell.TOP_LEFT, cellNode);

		// top right
		cellNode = new Node<T>(new Point(newXStartCoordinate, by), newBounds,
				depth);
		this.nodes.put(Cell.TOP_RIGHT, cellNode);

		// bottom left
		cellNode = new Node<T>(new Point(bx, newYStartCoordinate), newBounds,
				depth);
		this.nodes.put(Cell.BOTTOM_LEFT, cellNode);

		// bottom right
		cellNode = new Node<T>(new Point(newXStartCoordinate,
				newYStartCoordinate), newBounds, depth);
		this.nodes.put(Cell.BOTTOM_RIGHT, cellNode);
	}

	/**
	 * Clears this node and all subnodes
	 */
	public void clear() {
		for (Node<T> node : nodes.values()) {
			node.clear();
		}
		elements.clear();
	}
}
