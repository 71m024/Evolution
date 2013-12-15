package ch.dorf10.view;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import javax.swing.JComponent;

import ch.dorf10.logic.Map;


public class View extends JComponent{

	private static final long serialVersionUID = 1L;
	private Followable follow;
	private Map map;
	
	public View(Dimension dim, Followable follow, Map map) {
		super();
		this.setSize(dim);
		this.follow = follow;
		this.map = map;
	}
	
	public double getAngle() {
		return follow.getAngle();
	}
	
	public Point2D.Double getCenter() {
		return follow.getCenterOfGravity();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		map.paint(g, this);
	}
}
