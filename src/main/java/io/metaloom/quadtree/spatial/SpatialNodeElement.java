package io.metaloom.quadtree.spatial;

import io.metaloom.quadtree.NodeElement;

public interface SpatialNodeElement<T> extends NodeElement<T> {

	int getWidth();

	int getHeight();

	void setY(int y);

	void setX(int x);

}
