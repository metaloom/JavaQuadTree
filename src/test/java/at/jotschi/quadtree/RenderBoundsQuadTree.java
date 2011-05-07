package at.jotschi.quadtree;

import java.awt.Dimension;
import java.awt.Point;

@SuppressWarnings("serial")
public class RenderBoundsQuadTree extends QuadTreePanel {

	public static void main(String[] args) {
		new RenderBoundsQuadTree();
	}

	/**
	 * Create a new jframe and display it
	 */
	protected RenderBoundsQuadTree() {
		tree = createQuadTree();
		setupGui();
	}
	
	/**
	 * Create the QuadTree and add some random points
	 * 
	 * @return
	 */
	private QuadTree<String> createQuadTree() {
		BoundsQuadTree<String> tree = new BoundsQuadTree<String>(new Point(100,
				100), new Dimension(400, 400));

		for (int i = 0; i < 200; i++) {
			int x = (int) (Math.random() * 1000 * 0.4) + 100;
			int y = (int) (Math.random() * 1000 * 0.4) + 100;
			Point point = new Point(x, y);
			Dimension dimension = new Dimension(50, 50);
			tree.insert(point, dimension, "test");
		}

		return tree;
	}

}
