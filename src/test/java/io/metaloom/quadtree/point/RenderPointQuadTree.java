package io.metaloom.quadtree.point;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Vector;

import io.metaloom.quadtree.Cell;
import io.metaloom.quadtree.NodeElement;
import io.metaloom.quadtree.QuadTreePanel;
import io.metaloom.quadtree.Size;
import io.metaloom.quadtree.point.Point;
import io.metaloom.quadtree.point.PointNode;
import io.metaloom.quadtree.point.PointNodeElement;
import io.metaloom.quadtree.point.PointQuadTree;
import io.metaloom.quadtree.point.impl.PointQuadTreeImpl;

public class RenderPointQuadTree extends QuadTreePanel {

	private static final long serialVersionUID = -2360219811620173423L;

	protected PointQuadTree<String> tree;

	protected Vector<PointNodeElement<String>> selectedElements = new Vector<>();

	public static void main(String[] args) {
		new RenderPointQuadTree();
	}

	protected RenderPointQuadTree() {
		tree = createQuadTree();
		setupGui(670, 550);
	}

	/**
	 * Create the QuadTree and add some random points
	 * 
	 * @return
	 */
	protected PointQuadTreeImpl<String> createQuadTree() {
		PointQuadTreeImpl<String> tree = new PointQuadTreeImpl<>(Point.of(100, 100), Size.of(400, 400), 8, 4);

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
		g.drawString("Left click to add new points. Right click to highlight all elements of a selected cell/node.", 100, 80);
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

		Size bounds = node.getBounds();
		Point startCoordinates = node.getStartCoordinates();
		// Draw node bounds
		g.drawRect((int) startCoordinates.x(), (int) startCoordinates.y(), (int) bounds.width(), (int) bounds.height());

		// Draw subnodes
		Map<Cell, ? extends PointNode<String>> subNodes = node.getSubNodes();
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
		for (NodeElement<String> element : selectedElements) {
			g.drawOval((int) element.x() - 2, (int) element.y() - 2, 4, 4);
		}
		g.setColor(Color.BLACK);
	}

	/**
	 * Draw all elements of the node
	 * 
	 * @param g
	 */
	public <T> void drawElements(PointNode<String> node, Graphics g) {
		Vector<PointNodeElement<String>> elements = node.getElements();
		for (PointNodeElement<String> element : elements) {
			int x = (int) element.x();
			int y = (int) element.y();
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

		java.awt.Point p = e.getPoint();
		// Somehow my mousepoints do not match the coordinates at the window (maybe this is a xorg releated bug)
		p.x -= 0;
		p.y -= 20;

		if (e.getButton() == 1) {
			try {
				tree.insert(Point.of(p), "MouseClick");
			} catch (IndexOutOfBoundsException e1) {
				log.info("Out of bounds - omitting.");
			}
		} else if (e.getButton() == 3) {
			selectedElements = tree.getElements(Point.of(p));
		}
		repaint();
	}
}
