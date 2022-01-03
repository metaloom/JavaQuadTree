package io.metaloom.quadtree.spatial;

import io.metaloom.quadtree.NodeElement;

public interface SpatialNodeElement<T> extends NodeElement<T> {

	/**
	 * Return the width that the element consumes within the {@link SpatialQuadTree}.
	 * 
	 * @return
	 */
	long getWidth();

	/**
	 * Return the height of the element that it consumes within the {@link SpatialQuadTree}.
	 * 
	 * @return
	 */
	long getHeight();

	void setY(long y);

	void setX(long x);

}
