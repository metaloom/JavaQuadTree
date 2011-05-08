package at.jotschi.quadtree;

import java.awt.Point;

/**
 * Container class that holds the object within the quadtree
 * 
 * @author jotschi
 * 
 */
@SuppressWarnings("serial")
public abstract class AbstractNodeElement<T> extends Point {

	private T element;

	/**
	 * Create a new NodeElement that holds the element at the given coordinates.
	 * 
	 * @param x
	 * @param y
	 * @param element
	 */
	public AbstractNodeElement(Point coordinates, T element) {
		super(coordinates);
		this.element = element;
	}

	public AbstractNodeElement(T element) {
		this.element = element;
	}

	/**
	 * Returns the element that is contained within this NodeElement
	 * 
	 * @return
	 */
	public T getElement() {
		return element;
	}

}
