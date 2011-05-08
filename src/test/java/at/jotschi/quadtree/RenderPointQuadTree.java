package at.jotschi.quadtree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Vector;

import at.jotschi.quadtree.AbstractNode.Cell;
import at.jotschi.quadtree.gui.QuadTreePanel;
import at.jotschi.quadtree.point.PointNode;
import at.jotschi.quadtree.point.PointQuadTree;

@SuppressWarnings("serial")
public class RenderPointQuadTree extends QuadTreePanel {

	protected PointQuadTree<String> tree;

	protected Vector<AbstractNodeElement<String>> selectedElements = new Vector<AbstractNodeElement<String>>();

	public static void main(String[] args) {
		new RenderPointQuadTree();
	}

	protected RenderPointQuadTree() {
		tree = createQuadTree();
		setupGui();
	}

	/**
	 * Create the QuadTree and add some random points
	 * 
	 * @return
	 */
	protected PointQuadTree<String> createQuadTree() {
		PointQuadTree<String> tree = new PointQuadTree<String>(new Point(100,
				100), new Dimension(400, 400), 8, 4);

		for (int i = 0; i < 200; i++) {
			int x = (int) (Math.random() * 1000 * 0.4) + 100;
			int y = (int) (Math.random() * 1000 * 0.4) + 100;
			System.out.println(x + " " + y);
			tree.insert(x, y, "test");
		}

		return tree;
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawString(
				"Left click to add new points. Right click to highlight all elements of a selected cell/node.",
				100, 80);
		PointNode<String> rootNode = tree.getRootNode();
		drawCells(rootNode, g);
	}

	/**
	 * Draws the tree content
	 * 
	 * @param node
	 * @param g
	 */
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

		// Draw selected elements
		drawSelectedElements(g);
	}

	public void drawSelectedElements(Graphics g) {
		g.setColor(Color.RED);
		for (AbstractNodeElement<String> element : selectedElements) {
			g.drawOval((int) element.getX()-2, (int) element.getY()-2, 4, 4);
		}
		g.setColor(Color.BLACK);
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
			int x = (int) element.getX();
			int y = (int) element.getY();
			g.drawLine(x, y, x, y);
		}
	}

	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == 27) {
			System.exit(10);
		} else if (e.getKeyCode() == 67) {
			log.debug("Invoking clearing of tree.");
			tree.clear();
			repaint();
		}

	}

	public void mouseClicked(MouseEvent e) {

		Point p = e.getPoint();
		// Somehow my mousepoints do not match the coordinates at the window
		// (maybe this is a xorg releated bug)
		p.x -= 8;
		p.y -= 30;

		if (e.getButton() == 1) {
			try {
				tree.insert(p, "MouseClick");
			} catch (IndexOutOfBoundsException e1) {
				log.info("Out of bounds - omitting.");
			}
		} else if (e.getButton() == 3) {
			selectedElements = (Vector<AbstractNodeElement<String>>) tree
					.getElements(p);
		}
		repaint();
	}
}
