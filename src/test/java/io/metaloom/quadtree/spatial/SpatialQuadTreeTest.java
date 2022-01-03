package io.metaloom.quadtree.spatial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import io.metaloom.quadtree.AbstractQuadTreeTest;
import io.metaloom.quadtree.Cell;
import io.metaloom.quadtree.Size;
import io.metaloom.quadtree.point.Point;
import io.metaloom.quadtree.spatial.impl.SpatialQuadTreeImpl;

/**
 * Unit test for simple tree setup.
 */
public class SpatialQuadTreeTest extends AbstractQuadTreeTest {

	@Test
	public void testBasics() {
		SpatialQuadTree<String> tree = new SpatialQuadTreeImpl<>(Point.of(0, 0), Size.of(600, 600));
		assertTrue(tree.insert(Point.of(11, 3), Size.of(150, 150), "1"));
		assertTrue(tree.insert(Point.of(22, 3), Size.of(150, 150), "2"));
		assertTrue(tree.insert(Point.of(33, 3), Size.of(150, 150), "3"));

		SpatialNode<String> root = tree.getRootNode();
		assertNotNull(root);
		assertEquals("There should be always 4 sub nodes in a quad tree.", 4, root.getSubNodes().size());
		SpatialNode<String> element = find("2", root.getSubNodes());
		assertNotNull(element);
		assertEquals(2, element.getDepth());
	}

	@Test
	public void testBasics2() {
		SpatialQuadTree<String> tree = new SpatialQuadTreeImpl<>(Point.of(0, 0), Size.of(600, 600), 10, 200);
		assertTrue(tree.insert(Size.of(150, 150), "1"));
		assertTrue(tree.insert(Size.of(150, 150), "2"));
		assertTrue(tree.insert(Size.of(150, 150), "3"));

		SpatialNode<String> root = tree.getRootNode();
		assertNotNull(root);
		assertEquals("There should be always 4 sub nodes in a quad tree.", 4, root.getSubNodes().size());
		assertNotNull(find("2", root.getSubNodes()));
	}

	private SpatialNode<String> find(String id, Map<Cell, ? extends SpatialNode<String>> subNodes) {
		for (Entry<Cell, ? extends SpatialNode<String>> entry : subNodes.entrySet()) {
			SpatialNode<String> v = entry.getValue();
			SpatialNodeElement<String> data = v.getElement();
			if (data != null) {
				if (id.equalsIgnoreCase(data.getElement())) {
					return v;
				}
			} else {
				return find(id, v.getSubNodes());
			}
		}
		return null;
	}

	@Test
	public void testEmptyTree() {
		SpatialQuadTree<String> tree = new SpatialQuadTreeImpl<>(Point.of(0, 0), Size.of(600, 600));
		assertEquals(600, tree.getSize().height());
		assertEquals(600, tree.getSize().width());
		assertNotNull(tree.getRootNode());
	}

}
