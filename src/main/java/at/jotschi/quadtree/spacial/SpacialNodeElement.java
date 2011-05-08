package at.jotschi.quadtree.impl;

import java.awt.Dimension;
import java.awt.Point;

import at.jotschi.quadtree.AbstractNodeElement;


@SuppressWarnings("serial")
public class SpacialNodeElement<T> extends AbstractNodeElement<T> {

	protected Dimension elementSize;

	public SpacialNodeElement(T element, Dimension elementSize) {
		super(element);
		this.elementSize = elementSize;
	}
	
	public SpacialNodeElement(Point coordinates, Dimension elementSize, T element) {
		super(coordinates, element);
		this.elementSize = elementSize;
	}

	public int getWidth() {
		return this.elementSize.width;
	}

	public int getHeight() {
		return this.elementSize.height;
	}

}
