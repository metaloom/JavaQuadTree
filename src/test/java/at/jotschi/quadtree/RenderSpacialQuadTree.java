package at.jotschi.quadtree;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Map;
import java.util.Vector;

import at.jotschi.quadtree.AbstractNode.Cell;
import at.jotschi.quadtree.gui.QuadTreePanel;
import at.jotschi.quadtree.impl.PointNode;
import at.jotschi.quadtree.impl.SpacialNode;
import at.jotschi.quadtree.impl.SpacialQuadTree;

@SuppressWarnings("serial")
public class RenderSpacialQuadTree extends QuadTreePanel {

	protected SpacialQuadTree<String> tree;

	public static void main(String[] args) {
		new RenderSpacialQuadTree();
	}

	/**
	 * Create a new jframe and display it
	 */
	public RenderSpacialQuadTree() {
		tree = createSpacialQuadTree();
		setupGui();
	}

	/**
	 * Create the QuadTree and add some random points
	 * 
	 * @return
	 */
	protected SpacialQuadTree<String> createSpacialQuadTree() {

		Point startCoordinates = new Point(100, 100);
		Dimension size = new Dimension(512, 512);

		SpacialQuadTree<String> tree = new SpacialQuadTree<String>(
				startCoordinates, size);

		for (int i = 0; i < 200; i++) {
			// int x = (int) (Math.random() * 1000 * 0.3) + startCoordinates.x;
			// int y = (int) (Math.random() * 1000 * 0.3) + startCoordinates.y;
			// Point point = new Point(x, y);
			Dimension dimension = new Dimension(128, 128);
			tree.insert("test", dimension);
		}

		return tree;
	}

	protected void paintComponent(Graphics g) {
		SpacialNode<String> rootNode = tree.getRootNode();
		drawCells(rootNode, g);
	}
	
	protected void drawCells(PointNode<String> node, Graphics g) {
		Dimension bounds = node.getBounds();
		Point startCoordinates = node.getStartCoordinates();
		// Draw node bounds
		g.drawRect(startCoordinates.x, startCoordinates.y, bounds.width,
				bounds.height);

		// Draw subnodes
		Map<Cell, PointNode<String>> subNodes = node.getSubNodes();
		for (PointNode<String> subNode : subNodes.values()) {
			drawCells(subNode, g);
		}

		// Draw points of this node
		drawElements(node, g);

	}

	/**
	 * Draw all elements of the node
	 * 
	 * @param g
	 */

	public void drawElements(PointNode node, Graphics g) {

		Vector<AbstractNodeElement<String>> elements = (Vector<AbstractNodeElement<String>>) node
				.getElements();
		for (AbstractNodeElement<String> element : elements) {
			g.drawRect((int) element.getX(), (int) element.getY(),
					node.getBounds().width, node.getBounds().height);
		}
	}

}
