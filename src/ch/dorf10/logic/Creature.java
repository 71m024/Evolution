package ch.dorf10.logic;
import java.awt.geom.Point2D;


public class Creature extends Form{
	
	public Creature() {
		addPoint(new Point2D.Double(0, 0));
		addPoint(new Point2D.Double(40, 0));
		addPoint(new Point2D.Double(40, 60));
		addPoint(new Point2D.Double(0, 60));
	}
	
	public void rotate(double angle) {
		super.rotate(angle, getCenterOfGravity());
	}
}
