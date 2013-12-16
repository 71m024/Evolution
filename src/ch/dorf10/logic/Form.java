package ch.dorf10.logic;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import ch.dorf10.view.View;

public class Form extends Element {
	
	private List<Point2D.Double> points;

	public Form() {
		super();
		points = new ArrayList<Point2D.Double>();
	}
	
	public Form(Form form) {
		this();
		angle = form.angle;
		color = form.color;
		borderColor = form.borderColor;
		points = new ArrayList<Point2D.Double>();
		for (Point2D.Double point : form.points) {
			points.add((Point2D.Double)point.clone());
		}
	}
	
	public void addPoint(Point2D.Double p) {
		points.add(p);
	}
	
	public void removePoint(Point2D.Double p) {
		points.remove(p);
	}
	
	public Polygon getPolygon() {
    	Polygon polygon = new Polygon();
    	for (Point2D.Double point : points) {
    		polygon.addPoint((int)(point.getX()), (int)(point.getY()));
    	}
    	return polygon;
    }
	
	@Override
	public void rotate(double angle, Point2D.Double center) {
		super.rotate(angle, center);
		double radians = Math.toRadians(angle);
		for (Point2D.Double point : points) {
    		double newX = center.getX() + (point.getX() - center.getX()) * Math.cos(radians) - (point.getY() - center.getY()) * Math.sin(radians);
        	double newY = center.getY() + (point.getX() - center.getX()) * Math.sin(radians) + (point.getY() - center.getY()) * Math.cos(radians);
        	point.setLocation(new Point2D.Double(newX, newY));
    	}
	}
	
	@Override
	public void paint(Graphics g, View view) {
		
		updatePosition();
		
		Form viewForm = new Form(this);
		
		//int xShift = (int) (view.getSize().getWidth() / 2 - view.getCenter().getX());
		//int yShift = (int) (view.getSize().getHeight() / 2 - view.getCenter().getY());
		//viewForm.move(xShift, yShift);
		//viewForm.rotate(-view.getAngle(), view.getCenter());
		
		Polygon p = viewForm.getPolygon();
		
		g.setColor(color);
		g.fillPolygon(p);
		g.setColor(borderColor);
		g.drawPolygon(p);
	}
	
	@Override
	public void setPosition(Point2D.Double toPos) {
		Point2D.Double fromPos = getCenterOfGravity();
		double moveX = toPos.getX() - fromPos.getX();
		double moveY = toPos.getY() - fromPos.getY();
		move(moveX, moveY);
	}
	
	@Override
    public double getArea() {
        int j;  
        double area = 0;  
        for (int i = 0; i < points.size();i++) {  
	        j = (i + 1) % points.size();  
	        area += points.get(i).getX() * points.get(j).getY();  
	        area -= points.get(i).getY() * points.get(j).getX();  
        }  
        return Math.abs(0.5 * area);
    }
    
	@Override
    public void move(double x, double y) {
		for (Point2D.Double point : points) {
			point.x += x;
			point.y +=y;
		}
	}
    
	@Override
    public void moveInDirection(double amount, double angle) {
    	angle += this.angle;
    	angle %= 360;
    	angle += angle < 0 ? 360 : 0;
    	double moveX = 0;
    	double moveY = 0;
    	if (0 <= angle && angle < 90) {
    		moveX = -Math.sin(Math.toRadians(angle)) * amount;
    		moveY = Math.sin(Math.toRadians(90 - angle)) * amount;
    	} else if (90 <= angle && angle < 180) {
    		moveX = -Math.sin(Math.toRadians(180 - angle)) * amount;
    		moveY = -Math.sin(Math.toRadians(angle - 90)) * amount;
    	} else if (180 <= angle && angle < 270) {
    		moveX = Math.sin(Math.toRadians(angle - 180)) * amount;
    		moveY = -Math.sin(Math.toRadians(270 - angle)) * amount;
    	} else if (270 <= angle && angle < 360) {
    		moveX = Math.sin(Math.toRadians(360 - angle)) * amount;
    		moveY = Math.sin(Math.toRadians(angle - 270)) * amount;
    	}
    	move(moveX, moveY);
    }
    
    @Override
	public Point2D.Double getCenterOfGravity() {
		double cx = 0, cy = 0;
		double A = getArea();
		int i, j;

		double factor = 0;
		for (i = 0; i < points.size(); i++) {
			j = (i + 1) % points.size();
			factor = (points.get(i).x * points.get(j).y - points.get(j).x * points.get(i).y);
			cx += (points.get(i).x + points.get(j).x) * factor;
			cy += (points.get(i).y + points.get(j).y) * factor;
		}
		factor = 1.0 / (6.0 * A);
		factor = Double.isInfinite(factor) || Double.isNaN(factor) ? 0 : factor;
		
		cx *= factor;
		cy *= factor;
		return new Point2D.Double(cx, cy);
	}
}
