package at.jotschi.quadtree.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import at.jotschi.quadtree.Node;
import at.jotschi.quadtree.NodeElement;
import at.jotschi.quadtree.QuadTree;
import at.jotschi.quadtree.Node.Cell;

@SuppressWarnings("serial")
public class QuadTreePanel extends JPanel implements KeyListener, MouseListener {

	protected QuadTree<String> tree;
	
	protected Vector<NodeElement<String>> selectedElements = new Vector<NodeElement<String>>();

	protected static Logger log = Logger.getLogger(QuadTreePanel.class);

	/**
	 * Create a new jframe and display it
	 */
	protected QuadTreePanel() {
		tree = createQuadTree();
		setupGui();
	}

	protected void setupGui() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.addKeyListener(this);
		f.addMouseListener(this);
		f.add(this);
		f.setLocationRelativeTo(null);
		f.setSize(600, 600);
		f.setVisible(true);
	}

	/**
	 * Create the QuadTree and add some random points
	 * 
	 * @return
	 */
	private QuadTree<String> createQuadTree() {
		QuadTree<String> tree = new QuadTree<String>(new Point(100, 100),
				new Dimension(400, 400));

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
		Node<String> rootNode = tree.getRootNode();
		drawCells(rootNode, g);
	}

	/**
	 * Draws the tree content
	 * 
	 * @param node
	 * @param g
	 */
	protected void drawCells(Node<String> node, Graphics g) {

		Dimension bounds = node.getBounds();
		Point startCoordinates = node.getStartCoordinates();
		// Draw node bounds
		g.drawRect(startCoordinates.x, startCoordinates.y, bounds.width,
				bounds.height);

		// Draw subnodes
		Map<Cell, Node<String>> subNodes = node.getSubNodes();
		for (Node<String> subNode : subNodes.values()) {
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
			g.drawOval((int) element.getX(), (int) element.getY(), 4, 4);
		}
		g.setColor(Color.BLACK);
	}
	
	/**
	 * Draw all elements of the node
	 * 
	 * @param g
	 */
	public void drawElements(Node node, Graphics g) {

		Vector<NodeElement<String>> elements = (Vector<NodeElement<String>>) node
				.getElements();
		for (NodeElement<String> element : elements) {
			g.drawOval((int) element.getX(), (int) element.getY(), 4, 4);
		}
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == 27) {
			System.exit(10);
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
			selectedElements = (Vector<NodeElement<String>>) tree
					.getElements(p);
		}
		repaint();
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
