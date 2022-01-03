package io.metaloom.quadtree.spatial.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.metaloom.quadtree.AbstractNode;
import io.metaloom.quadtree.Cell;
import io.metaloom.quadtree.Size;
import io.metaloom.quadtree.point.Point;
import io.metaloom.quadtree.spatial.SpatialNode;
import io.metaloom.quadtree.spatial.SpatialNodeElement;

public class SpatialNodeImpl<T> extends AbstractNode<T> implements SpatialNode<T> {

	public static Logger log = LoggerFactory.getLogger(SpatialNodeImpl.class);

	protected Map<Cell, SpatialNode<T>> nodes = new HashMap<>();

	protected SpatialNodeElement<T> element;

	public SpatialNodeImpl(Point startCoordinates, Size bounds, int depth) {
		super(startCoordinates, bounds, depth);
	}

	public SpatialNodeImpl(Point startCoordinates, Size bounds, int depth, int maxDepth, int maxChildren) {
		super(startCoordinates, bounds, depth, maxDepth, maxChildren);
	}

	@Override
	public boolean insert(SpatialNodeElement<T> insertElement) {

		// Check if this node already contains a element
		if (element != null) {
			return false;
		}

		boolean wMatch = insertElement.getWidth() == this.getBounds().width();
		boolean hMatch = insertElement.getHeight() == this.getBounds().height();
		boolean fits = wMatch && hMatch;

		boolean wSmaller = insertElement.getWidth() < this.getBounds().width();
		boolean hSmaller = insertElement.getHeight() < this.getBounds().height();
		boolean smaller = wSmaller && hSmaller;

		log.debug("Inserting element " + insertElement.getHeight() + " - " + insertElement.getWidth());

		// Check if the element fits into this node
		if (fits && nodes.size() == 0) {
			insertElement.setX(startCoordinates.x());
			insertElement.setY(startCoordinates.y());
			this.element = insertElement;
			if (log.isDebugEnabled()) {
				log.debug("Inserting element at coordinates [" + insertElement.x() + "-" + insertElement.y() + "]");
			}
			return true;
		} else if (!(this.depth >= MAX_DEPTH) && nodes.size() == 0 && smaller) {
			// We need to subdivide the node if the element is smaller than then the dimensions of this node.
			subdivide();
			// After subdivision we try to insert the element into one of the subnodes
			for (SpatialNode<T> current : nodes.values()) {
				if (current.insert(insertElement)) {
					return true;
				}
			}

		} else {
			// Recall insert for the element. This will try to add the element into one of the subnodes.
			for (SpatialNode<T> current : nodes.values()) {
				if (current.insert(insertElement)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Subdivide the current node and add subnodes
	 */
	@Override
	public void subdivide() {
		if (log.isDebugEnabled()) {
			log.debug("Subdividing node at depth " + depth);
		}
		int depth = this.depth + 1;

		int bx = startCoordinates.x();
		int by = startCoordinates.y();

		// Create the bounds for the new cell
		Size newBounds = Size.of(bounds.width() / 2, bounds.height() / 2);

		// Add new bounds to current start coordinates to calculate the new
		// start coordinates
		int newXStartCoordinate = bx + newBounds.width();
		int newYStartCoordinate = by + newBounds.height();

		SpatialNodeImpl<T> cellNode = null;

		// top left
		cellNode = new SpatialNodeImpl<T>(Point.of(bx, by), newBounds, depth);
		nodes.put(Cell.TOP_LEFT, cellNode);

		// top right
		cellNode = new SpatialNodeImpl<T>(Point.of(newXStartCoordinate, by), newBounds, depth);
		nodes.put(Cell.TOP_RIGHT, cellNode);

		// bottom left
		cellNode = new SpatialNodeImpl<T>(Point.of(bx, newYStartCoordinate), newBounds, depth);
		nodes.put(Cell.BOTTOM_LEFT, cellNode);

		// bottom right
		cellNode = new SpatialNodeImpl<T>(Point.of(newXStartCoordinate, newYStartCoordinate), newBounds, depth);
		nodes.put(Cell.BOTTOM_RIGHT, cellNode);
	}

	@Override
	public void clear() {
		for (SpatialNode<T> node : nodes.values()) {
			node.clear();
		}
	}

	@Override
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
