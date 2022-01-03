package io.metaloom.quadtree.impl;

import io.metaloom.quadtree.Size;

public class SizeImpl implements Size {

	private final long width;
	private final long height;

	public SizeImpl(long width, long height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public long height() {
		return height;
	}

	@Override
	public long width() {
		return width;
	}

	@Override
	public String toString() {
		return "[" + width + ":" + height + "]";
	}
}
