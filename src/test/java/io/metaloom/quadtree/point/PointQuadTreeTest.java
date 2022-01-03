package io.metaloom.quadtree.point;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.metaloom.quadtree.AbstractQuadTreeTest;
import io.metaloom.quadtree.Size;
import io.metaloom.quadtree.point.impl.PointQuadTreeImpl;

/**
 * Unit test for simple tree setup.
 */
public class PointQuadTreeTest extends AbstractQuadTreeTest {

	@Test
	public void testBasics() {
		PointQuadTree<String> tree = new PointQuadTreeImpl<>(Point.of(0, 0), Size.of(600, 600));
		tree.insert(1, 3, "1");
		tree.insert(11, 32, "2");

		tree.insert(454, 555, "4");
		tree.insert(353, 555, "5");
		tree.insert(552, 555, "6");
		tree.insert(551, 555, "7");

		Point target = Point.of(500, 550);
		assertEquals("[4,5,6,7]", concat(tree.getElements(target)));

		Point target2 = Point.of(0, 0);
		assertEquals("[1,2]", concat(tree.getElements(target2)));

		tree.insert(11, 52, "3");
		assertEquals("[1,2,3]", concat(tree.getElements(target2)));

	}
}
