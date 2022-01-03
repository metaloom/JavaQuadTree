package io.metaloom.quadtree;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuadTreePanel extends JPanel implements KeyListener, MouseListener {

	private static final long serialVersionUID = 8763986244017440078L;

	protected static Logger log = LoggerFactory.getLogger(QuadTreePanel.class);

	/**
	 * Create a new panel
	 */
	protected QuadTreePanel() {

	}

	protected void setupGui(int width, int height) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.addKeyListener(this);
		f.addMouseListener(this);
		f.add(this);
		f.setLocationRelativeTo(null);
		f.setSize(width, height);
		f.setVisible(true);
	}

	public void keyTyped(KeyEvent e) {
		// NOOP
	}

	public void keyPressed(KeyEvent e) {
		// NOOP
	}

	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == 27) {
			System.exit(10);
		}

	}

	public void mousePressed(MouseEvent e) {
		// NOOP
	}

	public void mouseReleased(MouseEvent e) {
		// NOOP
	}

	public void mouseEntered(MouseEvent e) {
		// NOOP
	}

	public void mouseExited(MouseEvent e) {
		// NOOP
	}

	public void mouseClicked(MouseEvent e) {
		// NOOP
	}
}
