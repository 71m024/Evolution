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
	protected Point2D.Double speed;
	protected Point2D.Double acceleration;
	protected Point2D.Double maxSpeed;
	protected Point2D.Double minSpeed;
	private double lastTimeUpdated = System.currentTimeMillis();
	
	public static final double ANGLE_DEFAULT = 0;
	public static final Color COLOR_DEFAULT = Color.WHITE;
	public static final Color BORDER_COLOR_DEFAULT = Color.BLACK;
	public static final Point2D.Double SPEED_DEFAULT = new Point2D.Double(0,0);
	public static final Point2D.Double ACCELERATION_DEFAULT = new Point2D.Double(0,0);
	public static final Point2D.Double SPPED_MIN_DEFAULT = new Point2D.Double(0,-100);
	public static final Point2D.Double SPEED_MAX_DEFAULT = new Point2D.Double(0,100);
	
	public Element() {
		this.angle = ANGLE_DEFAULT;
		this.color = COLOR_DEFAULT;
		this.borderColor = BORDER_COLOR_DEFAULT;
		this.speed = SPEED_DEFAULT;
		this.acceleration = ACCELERATION_DEFAULT;
		this.minSpeed = SPPED_MIN_DEFAULT;
		this.maxSpeed = SPEED_MAX_DEFAULT;
	}
	
	public Element(Color borderColor) {
		this();
		this.borderColor = borderColor;
	}
	
	public Element(Color borderColor, Color color) {
		this(borderColor);
		this.color = color;
	}
	
	private double checkSpeed(double speed, double min, double max) {
		if (speed < min) {
			return min;
		} else if (speed > max) {
			return max;
		}
		return speed;
	}
	
	public void updatePosition() {
		double timeNow = System.currentTimeMillis();
		double timeDelta = timeNow - lastTimeUpdated;
		lastTimeUpdated = timeNow;
		
		//new speed
		speed.x = checkSpeed(speed.x + (acceleration.x * timeDelta / 1000), minSpeed.x, maxSpeed.x);
		speed.y = checkSpeed(speed.y + (acceleration.y * timeDelta / 1000), minSpeed.y, maxSpeed.y);
		
		//new position
		double shiftX = (speed.x * timeDelta / 1000);
		double shiftY = (speed.y * timeDelta / 1000);
		
		moveInDirection(shiftX, 90);
		moveInDirection(shiftY, 0);
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
	
	public Point2D.Double getSpeed() {
		return speed;
	}

	public void setSpeed(Point2D.Double speed) {
		this.speed = speed;
	}

	public Point2D.Double getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Point2D.Double acceleration) {
		this.acceleration = acceleration;
	}

	public Point2D.Double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(Point2D.Double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public Point2D.Double getMinSpeed() {
		return minSpeed;
	}

	public void setMinSpeed(Point2D.Double minSpeed) {
		this.minSpeed = minSpeed;
	}
	
	
	
	@Override
	public abstract Point2D.Double getCenterOfGravity();

	public abstract void paint(Graphics g, View view);

	public abstract void setPosition(Double toPos);

	public abstract double getArea();

	public abstract void move(double x, double y);

	public abstract void moveInDirection(double amount, double angle);
}
