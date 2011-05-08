package at.jotschi.quadtree;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

import at.jotschi.quadtree.gui.QuadTreePanel;

@SuppressWarnings("serial")
public class RenderBoundsQuadTree extends QuadTreePanel {

	protected BoundsQuadTree<String> tree;
	
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
	private BoundsQuadTree<String> createQuadTree() {
		Point startCoordinates = new Point(100, 100);
		BoundsQuadTree<String> tree = new BoundsQuadTree<String>(
				startCoordinates, new Dimension(400, 400));

		for (int i = 0; i < 200; i++) {
			int x = (int) (Math.random() * 1000 * 0.3) + startCoordinates.x;
			int y = (int) (Math.random() * 1000 * 0.3) + startCoordinates.y;
			Point point = new Point(x, y);
			Dimension dimension = new Dimension(50, 50);
			tree.insert(point, dimension, "test");
		}

		return tree;
	}

	@Override
	protected void paintComponent(Graphics g) {
		BoundsNode<String> rootNode = tree.getRootNode();
		drawCells(rootNode, g);
	}

	
	/**
	 * Draw all elements of the node
	 * 
	 * @param g
	 */
	@Override
	public void drawElements(Node node, Graphics g) {

		Vector<NodeElement<String>> elements = (Vector<NodeElement<String>>) node
				.getElements();
		for (NodeElement<String> element : elements) {
			g.drawRect((int) element.getX(), (int) element.getY(),
					node.getBounds().width, node.getBounds().height);
		}
	}

}
