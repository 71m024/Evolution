package ch.dorf10.view;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import javax.swing.JComponent;

import ch.dorf10.logic.Map;


public class View extends JComponent {

	/**
	 * the serialVersionUID
	 */
	private static final long serialVersionUID = 2533867472740695022L;
	
	private Followable follow;
	private Map map;
	private boolean running;
	public static final int REPAINT_BREAK_MS = 50;
	
	public View(Followable follow, Map map) {
		super();
		this.follow = follow;
		this.map = map;
		run();
	}
	
	public double getAngle() {
		return follow.getAngle();
	}
	
	public Point2D.Double getCenter() {
		return follow.getCenterOfGravity();
	}
	
	public void stop() {
		running = false;
	}

	@Override
	public void paintComponent(Graphics g) {
		map.paint(g, this);
	}

	public void run() {
		running = true;
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (running) {
					View.this.repaint();
					try {
						Thread.sleep(View.REPAINT_BREAK_MS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}).start();
	}
}
