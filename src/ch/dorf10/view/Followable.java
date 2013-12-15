package ch.dorf10.view;

import java.awt.geom.Point2D;

public interface Followable {
	public Point2D.Double getCenterOfGravity();
	public double getAngle();
}
