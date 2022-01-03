package io.metaloom.quadtree.point.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.metaloom.quadtree.AbstractNode;
import io.metaloom.quadtree.AbstractNodeElement;
import io.metaloom.quadtree.Cell;
import io.metaloom.quadtree.Size;
import io.metaloom.quadtree.point.Point;
import io.metaloom.quadtree.point.PointNode;
import io.metaloom.quadtree.point.PointNodeElement;

/**
 * This is a Node that represents each 'cell' within the quadtree. The Node will contains elements {@link AbstractNodeElement} that itself will contain the
 * final data within the tree.
 * 
 * @param <T>
 */
public class PointNodeImpl<T> extends AbstractNode<T> implements PointNode<T> {

	public static final Logger log = LoggerFactory.getLogger(PointNodeImpl.class);

	protected Map<Cell, PointNode<T>> nodes = new HashMap<>();

	/**
	 * Holds all elements for this node
	 */
	protected Vector<PointNodeElement<T>> elements = new Vector<>();

	public PointNodeImpl(Point startCoordinates, Size bounds, long depth) {
		super(startCoordinates, bounds, depth);
		if (log.isDebugEnabled()) {
			log.debug("Creating new Node at depth " + depth);
		}
	}

	/**
	 * 
	 * @param startCoordinates
	 * @param bounds
	 * @param depth
	 * @param maxDepth
	 * @param maxChildren
	 */
	public PointNodeImpl(Point startCoordinates, Size bounds, long depth, long maxDepth, int maxChildren) {
		super(startCoordinates, bounds, depth, maxChildren, maxDepth);
		if (log.isDebugEnabled()) {
			log.debug("Creating new Node at depth " + depth);
		}
	}

	/**
	 * Returns the subnodes of this node
	 * 
	 * @return
	 */
	public Map<Cell, PointNode<T>> getSubNodes() {
		return nodes;
	}

	/**
	 * Returns the cell which matches the coordinates.
	 * 
	 * @param coordinates
	 * @return
	 */
	protected Cell findIndex(Point coordinates) {
		// Compute the sector for the coordinates
		boolean left = (coordinates.x() > (startCoordinates.x() + bounds.width() / 2)) ? false : true;
		boolean top = (coordinates.y() > (startCoordinates.y() + bounds.height() / 2)) ? false : true;

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
		if (log.isDebugEnabled()) {
			log.debug("Coordinate [" + coordinates.x() + "-" + coordinates.y() + "] is within " + index.toString() + " at depth " + depth);
		}
		return index;
	}

	@Override
	public Vector<PointNodeElement<T>> getElements() {
		return this.elements;
	}

	@Override
	public Vector<PointNodeElement<T>> getElements(Point coordinates) {
		// Check if this node has already been subdivided. Therefore this node should contain no elements
		if (nodes.size() > 0) {
			Cell index = findIndex(coordinates);
			PointNode<T> node = nodes.get(index);
			return node.getElements(coordinates);
		} else {
			return this.elements;
		}
	}

	@Override
	public boolean insert(PointNodeElement<T> element) {
		if (log.isDebugEnabled()) {
			log.debug("Inserting element into Node at depth " + depth);
		}
		// If this Node has already been subdivided just add the elements to the appropriate cell
		if (nodes.size() != 0) {
			Cell index = findIndex(element);
			if (log.isDebugEnabled()) {
				log.debug("Inserting into existing cell: " + index);
			}
			nodes.get(index).insert(element);
			return true;
		}

		// Add the element to this node
		elements.add(element);

		// Only subdivide the node if it contain more than MAX_CHILDREN and is not the deepest node
		if (!(depth >= maxDepth) && elements.size() > maxElements) {
			subdivide();

			// Recall insert for each element. This will move all elements of
			// this node into the new nodes at the appropriate cell
			for (PointNodeElement<T> current : elements) {
				insert(current);
			}
			// Remove all elements from this node since they were moved into subnodes
			elements.clear();

		}
		return true;
	}

	/**
	 * Subdivide the current node and add subnodes
	 */
	public void subdivide() {
		if (log.isDebugEnabled()) {
			log.debug("Subdividing node at depth " + depth);
		}
		long depth = this.depth + 1;

		long bx = this.startCoordinates.x();
		long by = this.startCoordinates.y();

		// Create the bounds for the new cell
		Size newBounds = Size.of(bounds.width() / 2, bounds.height() / 2);

		// Add new bounds to current start coordinates to calculate the new
		// start coordinates
		long newXStartCoordinate = bx + newBounds.width();
		long newYStartCoordinate = by + newBounds.height();

		// top left
		PointNode<T> tlCellNode = new PointNodeImpl<T>(Point.of(bx, by), newBounds, depth, this.maxDepth, this.maxElements);
		this.nodes.put(Cell.TOP_LEFT, tlCellNode);

		// top right
		PointNode<T> crCellNode = new PointNodeImpl<T>(Point.of(newXStartCoordinate, by), newBounds, depth, this.maxDepth, this.maxElements);
		this.nodes.put(Cell.TOP_RIGHT, crCellNode);

		// bottom left
		PointNode<T> blCellNode = new PointNodeImpl<T>(Point.of(bx, newYStartCoordinate), newBounds, depth, this.maxDepth, this.maxElements);
		this.nodes.put(Cell.BOTTOM_LEFT, blCellNode);

		// bottom right
		PointNode<T> brCellNode = new PointNodeImpl<T>(Point.of(newXStartCoordinate, newYStartCoordinate), newBounds, depth, this.maxDepth,
			this.maxElements);
		this.nodes.put(Cell.BOTTOM_RIGHT, brCellNode);
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
