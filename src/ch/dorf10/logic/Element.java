package ch.dorf10.logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import ch.dorf10.view.Followable;
import ch.dorf10.view.View;

public abstract class Element implements Followable {
	
	protected Color color;
	protected Color borderColor;
	protected double angle;
	
	public static final Color COLOR_DEFAULT = Color.black;
	
	public Element() {
		this.borderColor = COLOR_DEFAULT;
	}
	
	public Element(Color borderColor) {
		this.borderColor = borderColor;
	}
	
	public Element(Color borderColor, Color color) {
		this(borderColor);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	
	public void rotate(double angle, Point2D.Double center) {
		double newAngle = this.angle + angle;
    	newAngle %= 360;
    	newAngle += newAngle < 0 ? 360 : 0;
    	this.angle = newAngle;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	@Override
	public abstract Point2D.Double getCenterOfGravity();

	public abstract void paint(Graphics g, View view);

	public abstract void setPosition(Double toPos);

	public abstract double getArea();

	public abstract void move(double x, double y);

	public abstract void moveInDirection(double amount, double angle);
}
