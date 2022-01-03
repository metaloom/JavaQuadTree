package io.metaloom.quadtree;

import java.util.Iterator;
import java.util.Vector;
import java.util.stream.Collectors;

import io.metaloom.quadtree.point.PointNodeElement;

public abstract class AbstractQuadTreeTest {

	public String concat(Vector<PointNodeElement<String>> vectorData) {
		String ids = "[";
		Iterator<String> it = vectorData.stream().map(e -> e.getElement()).collect(Collectors.toList()).iterator();
		while (it.hasNext()) {
			ids += it.next();
			if (it.hasNext()) {
				ids += ",";
			}
		}
		ids += "]";
		return ids;
	}
}
