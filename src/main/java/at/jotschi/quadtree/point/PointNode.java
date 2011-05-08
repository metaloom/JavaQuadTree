package at.jotschi.quadtree.point;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import at.jotschi.quadtree.AbstractNode;
import at.jotschi.quadtree.AbstractNodeElement;

/**
 * Node that represents each 'cell' within the quadtree. The Node will contains
 * elements {@link AbstractNodeElement} that itself will contain the final data
 * within the tree.
 * 
 * @author jotschi
 * 
 * @param <T>
 */
public class PointNode<T> extends AbstractNode {

	protected static Logger log = Logger.getLogger(PointNode.class);

	protected Map<Cell, PointNode<T>> nodes = new HashMap<Cell, PointNode<T>>();

	/**
	 * Holds all elements for this node
	 */
	protected Vector<PointNodeElement<T>> elements = new Vector<PointNodeElement<T>>();

	public PointNode(Point startCoordinates, Dimension bounds, int depth) {
		super(startCoordinates, bounds, depth);
		log.debug("Creating new Node at depth " + depth);
	}

	/**
	 * 
	 * @param startCoordinates
	 * @param bounds
	 * @param depth
	 * @param maxDepth
	 * @param maxChildren
	 */
	public PointNode(Point startCoordinates, Dimension bounds, int depth,
			int maxDepth, int maxChildren) {
		super(startCoordinates, bounds, depth, maxDepth, maxChildren);
		log.debug("Creating new Node at depth " + depth);
	}

	/**
	 * Returns the subnodes of this node
	 * 
	 * @return
	 */
	public Map<Cell, PointNode<T>> getSubNodes() {
		return this.nodes;
	}

	/**
	 * Returns the cell of this element
	 * 
	 * @param element
	 * @return
	 */
	protected Cell findIndex(Point coordinates) {
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
	public Vector<PointNodeElement<T>> getElements() {
		return this.elements;
	}

	/**
	 * Returns all elements within the cell that matches the given coordinates
	 * 
	 * @param coordinates
	 * @return
	 */
	public Vector<PointNodeElement<T>> getElements(Point coordinates) {

		// Check if this node has already been subdivided. Therefor this node
		// should contain no elements
		if (nodes.size() > 0) {
			Cell index = findIndex(coordinates);
			PointNode<T> node = this.nodes.get(index);
			return node.getElements(coordinates);
		} else {
			return this.elements;
		}
	}

	/**
	 * Insert the element into this node. If needed a subdivision will be
	 * performed
	 * 
	 * @param element
	 */
	public void insert(PointNodeElement<T> element) {
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
		if (!(this.depth >= maxDepth) && this.elements.size() > maxElements) {
			this.subdivide();

			// Recall insert for each element. This will move all elements of
			// this node into the new nodes at the appropriate cell
			for (PointNodeElement<T> current : elements) {
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

		PointNode<T> cellNode = null;

		// top left
		cellNode = new PointNode<T>(new Point(bx, by), newBounds, depth,
				this.maxDepth, this.maxElements);
		this.nodes.put(Cell.TOP_LEFT, cellNode);

		// top right
		cellNode = new PointNode<T>(new Point(newXStartCoordinate, by),
				newBounds, depth, this.maxDepth, this.maxElements);
		this.nodes.put(Cell.TOP_RIGHT, cellNode);

		// bottom left
		cellNode = new PointNode<T>(new Point(bx, newYStartCoordinate),
				newBounds, depth, this.maxDepth, this.maxElements);
		this.nodes.put(Cell.BOTTOM_LEFT, cellNode);

		// bottom right
		cellNode = new PointNode<T>(new Point(newXStartCoordinate,
				newYStartCoordinate), newBounds, depth, this.maxDepth,
				this.maxElements);
		this.nodes.put(Cell.BOTTOM_RIGHT, cellNode);
	}

	/**
	 * Clears this node and all subnodes
	 */
	public void clear() {
		for (PointNode<T> node : nodes.values()) {
			node.clear();
		}
		elements.clear();
		nodes.clear();
	}
}
