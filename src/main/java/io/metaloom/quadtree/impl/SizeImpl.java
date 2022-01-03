package io.metaloom.quadtree.impl;

import io.metaloom.quadtree.Size;

public class SizeImpl implements Size {

	private final int width;
	private final int height;

	public SizeImpl(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public int height() {
		return height;
	}

	@Override
	public int width() {
		return width;
	}

}
