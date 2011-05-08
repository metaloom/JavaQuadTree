package at.jotschi.quadtree.spacial;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import at.jotschi.quadtree.AbstractNode;

public class SpatialNode<T> extends AbstractNode {

	protected Map<Cell, SpatialNode<T>> nodes = new HashMap<Cell, SpatialNode<T>>();

	protected SpatialNodeElement<T> element;

	protected static Logger log = Logger.getLogger(SpatialNode.class);

	public SpatialNode(Point startCoordinates, Dimension bounds, int depth) {
		super(startCoordinates, bounds, depth);
	}

	public SpatialNode(Point startCoordinates, Dimension bounds, int depth,
			int maxDepth, int maxChildren) {
		super(startCoordinates, bounds, depth, maxDepth, maxChildren);

	}

	/**
	 * Insert the element into this node. If needed a subdivision will be
	 * performed
	 * 
	 * @param element
	 */
	public boolean insert(SpatialNodeElement<T> element) {

		boolean wMatch = element.getWidth() == this.getBounds().width;
		boolean hMatch = element.getHeight() == this.getBounds().height;
		boolean fits = wMatch && hMatch;

		boolean wSmaller = element.getWidth() < this.getBounds().width;
		boolean hSmaller = element.getHeight() < this.getBounds().height;
		boolean smaller = wSmaller && hSmaller;

		log.debug("Inserting element " + element.getHeight() + " - "
				+ element.getWidth());

		// Check if this node already contains a element
		if (this.element != null) {
			return false;
		}

		// Check if the element fits into this node
		if (fits && this.nodes.size() == 0) {
			element.x = this.startCoordinates.x;
			element.y = this.startCoordinates.y;
			this.element = element;
			log.debug("Inserting element at coordinates [" + element.x + "-"
					+ element.y + "]");
			return true;
		} else if (!(this.depth >= MAX_DEPTH) && nodes.size() == 0 && smaller) {
			// We need to subdivide the node if the element is smaller than then
			// the dimensions of this node.
			this.subdivide();
			// After subdivision we try to insert the element into one of the subnodes
			for (SpatialNode<T> current : nodes.values()) {
				if (current.insert(element)) {
					return true;
				}
			}

		} else {
			// Recall insert for the element. This will try to add the element
			// into
			// one of the subnodes.
			for (SpatialNode<T> current : nodes.values()) {
				if (current.insert(element)) {
					return true;
				}
			}

		}

		return false;
	}

	@Override
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

		SpatialNode<T> cellNode = null;

		// top left
		cellNode = new SpatialNode<T>(new Point(bx, by), newBounds, depth);
		this.nodes.put(Cell.TOP_LEFT, cellNode);

		// top right
		cellNode = new SpatialNode<T>(new Point(newXStartCoordinate, by),
				newBounds, depth);
		this.nodes.put(Cell.TOP_RIGHT, cellNode);

		// bottom left
		cellNode = new SpatialNode<T>(new Point(bx, newYStartCoordinate),
				newBounds, depth);
		this.nodes.put(Cell.BOTTOM_LEFT, cellNode);

		// bottom right
		cellNode = new SpatialNode<T>(new Point(newXStartCoordinate,
				newYStartCoordinate), newBounds, depth);
		this.nodes.put(Cell.BOTTOM_RIGHT, cellNode);
	}

	/**
	 * Clears this node and all subnodes
	 */
	public void clear() {
		for (SpatialNode<T> node : nodes.values()) {
			node.clear();
		}
	}

	public SpatialNodeElement<T> getElement() {
		return this.element;
	}

	/**
	 * Returns the subnodes of this node
	 * 
	 * @return
	 */
	public Map<Cell, SpatialNode<T>> getSubNodes() {
		return this.nodes;
	}

}
