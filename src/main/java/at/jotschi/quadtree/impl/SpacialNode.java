package at.jotschi.quadtree.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import at.jotschi.quadtree.AbstractNodeElement;

public class SpacialNode<T> extends PointNode<T> {

	protected Map<Cell, SpacialNode<T>> nodes = new HashMap<Cell, SpacialNode<T>>();

	protected AbstractNodeElement<T> element;

	public SpacialNode(Point startCoordinates, Dimension bounds, int depth) {
		super(startCoordinates, bounds, depth);
	}

	public SpacialNode(Point startCoordinates, Dimension bounds, int depth,
			int maxDepth, int maxChildren) {
		super(startCoordinates, bounds, depth, maxDepth, maxChildren);

	}

	/**
	 * Insert the element into this node. If needed a subdivision will be
	 * performed
	 * 
	 * @param element
	 */
	public void insert(SpacialNodeElement<T> element) {
		log.debug("Inserting element into Node at depth " + depth);

		boolean wMatch = element.getWidth() == this.getBounds().width;
		boolean hMatch = element.getHeight() == this.getBounds().height;

		// Check if the element fits into this node
		if (wMatch && hMatch && this.element == null) {
			element.x = this.startCoordinates.x;
			element.y = this.startCoordinates.y;
			this.element = element;
			return;
		}

		// We need to subdivide the node if the element is smaller than then the
		// dimensions of this node.
		if (!(this.depth >= MAX_DEPTH)) {
			this.subdivide();
		}

		// Recall insert for the element. This will try to add the element into
		// one of the subnodes.
		for (SpacialNode<T> current : nodes.values()) {
			current.insert(element);
		}

	}

}
