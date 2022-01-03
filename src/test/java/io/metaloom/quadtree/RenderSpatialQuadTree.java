package io.metaloom.quadtree;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import io.metaloom.quadtree.gui.QuadTreePanel;
import io.metaloom.quadtree.point.Point;
import io.metaloom.quadtree.spatial.SpatialNode;
import io.metaloom.quadtree.spatial.SpatialNodeElement;
import io.metaloom.quadtree.spatial.impl.SpatialQuadTreeImpl;

public class RenderSpatialQuadTree extends QuadTreePanel {

	private static final long serialVersionUID = -7363155496742737522L;

	protected SpatialQuadTreeImpl<Image> tree;

	int untilDepth = 0;

	public static void main(String[] args) {
		new RenderSpatialQuadTree();
	}

	/**
	 * Create a new jframe and display it
	 */
	public RenderSpatialQuadTree() {
		try {
			tree = createSpatialQuadTree();
		} catch (IOException e) {
			log.error("Could not load image.", e);
		}
		setupGui();
	}

	/**
	 * Create the QuadTree and add some random areas
	 * 
	 * @return
	 * @throws IOException
	 */
	protected SpatialQuadTreeImpl<Image> createSpatialQuadTree() throws IOException {
		Point startCoordinates = Point.of(100, 100);
		Size size = Size.of(512, 512);

		SpatialQuadTreeImpl<Image> tree = new SpatialQuadTreeImpl<>(startCoordinates, size);

		int elementSize = 32;
		for (int i = 0; i < 56; i++) {
			Size dimension = Size.of(elementSize, elementSize);
			tree.insert(getRandomImage(elementSize), dimension);
		}
		elementSize = 64;
		for (int i = 0; i < 16; i++) {
			Size dimension = Size.of(elementSize, elementSize);
			tree.insert(getRandomImage(elementSize), dimension);
		}
		elementSize = 128;
		for (int i = 0; i < 3; i++) {
			Size dimension = Size.of(elementSize, elementSize);
			tree.insert(getRandomImage(elementSize), dimension);
		}
		elementSize = 256;
		for (int i = 0; i < 1; i++) {
			Size dimension = Size.of(elementSize, elementSize);
			tree.insert(getRandomImage(elementSize), dimension);
		}

		return tree;
	}

	/**
	 * Load a random image for the given size
	 * 
	 * @param size
	 * @return
	 * @throws IOException
	 */
	public Image getRandomImage(int size) throws IOException {
		String[] types = { "stone", "dirt", "pattern", "grass" };
		int i = (int) (Math.random() * 4);
		String fileName = types[i] + "_" + size + ".jpg";
		log.debug("Loading image: " + fileName);
		Image image = ImageIO.read(ClassLoader.getSystemResourceAsStream(fileName));

		return image;
	}

	protected void paintComponent(Graphics g) {
		g.drawString("Hit space key to draw elements of the next level of the quadtree.", 100, 80);
		SpatialNode<Image> rootNode = tree.getRootNode();
		drawCells(rootNode, g);
	}

	protected void drawCells(SpatialNode<Image> node, Graphics g) {
		Size bounds = node.getBounds();
		Point startCoordinates = node.getStartCoordinates();
		// Draw node bounds
		g.drawRect(startCoordinates.x(), startCoordinates.y(), bounds.width(), bounds.height());

		// Draw subnodes
		Map<Cell, ? extends SpatialNode<Image>> subNodes = node.getSubNodes();
		for (SpatialNode<Image> subNode : subNodes.values()) {
			drawCells(subNode, g);
		}

		// Draw areas of this node
		if (node.getDepth() == untilDepth) {
			drawElements(node, g);
		}

	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 27) {
			System.exit(10);
		} else if (e.getKeyCode() == 32) {
			untilDepth++;
			repaint();
		}
	}

	/**
	 * Draw all elements of the node
	 * 
	 * @param g
	 */
	public void drawElements(SpatialNode<Image> node, Graphics g) {
		SpatialNodeElement<Image> element = node.getElement();
		if (element != null) {
			g.drawImage(element.getElement(), element.x(), element.y(), null);
		}

	}

}
