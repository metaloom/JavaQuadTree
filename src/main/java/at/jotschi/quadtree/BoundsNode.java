package at.jotschi.quadtree;

import java.awt.Dimension;
import java.awt.Point;

public class BoundsNode<T> extends Node<T> {

	public BoundsNode(Point startCoordinates, Dimension bounds, int depth) {
		super(startCoordinates, bounds, depth);
	}

	public BoundsNode(Point startCoordinates, Dimension bounds, int depth,
			int maxDepth, int maxChildren) {
		super(startCoordinates, bounds, depth, maxDepth, maxChildren);

	}

}
