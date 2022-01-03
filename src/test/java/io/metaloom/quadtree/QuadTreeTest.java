package io.metaloom.quadtree;

import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.Test;

import io.metaloom.quadtree.point.Point;
import io.metaloom.quadtree.point.impl.PointNodeElement;
import io.metaloom.quadtree.point.impl.PointQuadTreeImpl;
import io.metaloom.quadtree.spatial.impl.SpatialQuadTreeImpl;

/**
 * Unit test for simple tree setup.
 */
public class QuadTreeTest {

	@Test
	public void testBasics() {
		PointQuadTreeImpl<String> tree = new PointQuadTreeImpl<>(Point.of(0, 0), Size.of(600, 600));
		tree.insert(1, 3, "1");
		tree.insert(11, 32, "2");
		tree.insert(11, 52, "3");

		tree.insert(454, 555, "4");
		tree.insert(353, 555, "5");
		tree.insert(552, 555, "6");
		tree.insert(551, 555, "7");

		Vector<PointNodeElement<String>> elements = tree.getElements(Point.of(500, 550));

		for (NodeElement<String> element : elements) {
			System.out.println("[" + element.x() + "-" + element.y() + "] " + element.getElement());
		}
	}

	@Test
	public void quadTreeBoundsTest() {
		SpatialQuadTreeImpl<String> tree = new SpatialQuadTreeImpl<>(Point.of(0, 0), Size.of(600, 600));
		tree.insert(Point.of(11, 3), Size.of(10, 10), "1");
		tree.insert(Point.of(22, 3), Size.of(30, 10), "2");
		tree.insert(Point.of(33, 3), Size.of(40, 10), "3");

	}

	@Test
	public void testEmptyTree() {
		SpatialQuadTreeImpl<String> tree = new SpatialQuadTreeImpl<>(Point.of(0, 0), Size.of(600, 600));
		assertEquals(600, tree.getSize().height());
		assertEquals(600, tree.getSize().width());
	}

}
