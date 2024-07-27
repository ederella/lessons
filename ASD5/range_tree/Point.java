package range_tree;

public class Point {

	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public boolean isBetween(Point p1, Point p2) {
		return isBetweenX(p1, p2) && isBetweenY(p1, p2);
	}
	
	public boolean isBetweenX(Point p1, Point p2) {
		return x>=p1.x && x<=p2.x ;
	}
	
	public boolean isBetweenY(Point p1, Point p2) {
		return y>=p1.y && y<=p2.y;
	}
}
