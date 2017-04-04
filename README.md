# JavaQuadTree

Just a simple java implementation of a quadtree

## Usage

```
PointQuadTree<String> tree = new PointQuadTree<String>(new Point(0, 0), new Dimension(600, 600));
	tree.insert(1, 3, "1");
	tree.insert(11, 32, "2");
	tree.insert(11, 52, "3");
	tree.insert(454, 555, "4");
	tree.insert(353, 555, "5");
	tree.insert(552, 555, "6");
	tree.insert(551, 555, "7");
```
