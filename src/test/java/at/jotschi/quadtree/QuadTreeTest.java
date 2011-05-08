package at.jotschi.quadtree;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Vector;

import org.junit.Test;

import at.jotschi.quadtree.point.PointQuadTree;
import at.jotschi.quadtree.spacial.SpatialQuadTree;

/**
 * Unit test for simple tree setup.
 */
public class QuadTreeTest {

	@Test
	public void testApp() {
		PointQuadTree<String> tree = new PointQuadTree<String>(new Point(0, 0),
				new Dimension(600, 600));
		tree.insert(1, 3, "1");
		tree.insert(11, 32, "2");
		tree.insert(11, 52, "3");

		tree.insert(454, 555, "4");
		tree.insert(353, 555, "5");
		tree.insert(552, 555, "6");
		tree.insert(551, 555, "7");

		Vector<AbstractNodeElement<String>> elements = (Vector<AbstractNodeElement<String>>) tree
				.getElements(new Point(500, 550));

		for (AbstractNodeElement<String> element : elements) {
			System.out.println("[" + element.getX() + "-" + element.getY()
					+ "] " + element.getElement());
		}
	}

	@Test
	public void quadTreeBoundsTest() {
		SpatialQuadTree<String> tree = new SpatialQuadTree<String>(
				new Point(0, 0), new Dimension(600, 600));
		tree.insert(new Point(11, 3), new Dimension(10, 10), "1");
		tree.insert(new Point(22, 3), new Dimension(30, 10), "2");
		tree.insert(new Point(33, 3), new Dimension(40, 10), "3");

	}

}
