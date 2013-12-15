package ch.dorf10.logic;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

import ch.dorf10.view.Followable;
import ch.dorf10.view.View;
import ch.dorf10.view.Window;

public class Game implements KeyListener{
	
	private View view;
	private Creature timo;

	public Game() {
		timo = new Creature();
		Creature tuxes = new Creature();
		Creature ramibu = new Creature();
		Creature thunder = new Creature();
		timo.setPosition(new Point2D.Double (200, 200));
		tuxes.setPosition(new Point2D.Double(50, 50));
		ramibu.setPosition(new Point2D.Double(300, 50));
		thunder.setPosition(new Point2D.Double(200, 50));
		Map map = new Map();
		map.addCreature(timo);
		map.addCreature(tuxes);
		map.addCreature(thunder);
		map.addCreature(ramibu);
		view = new View(new Dimension(800, 600), (Followable) timo, map);
		new Window("GameWindow", this).setView(view);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case 37:
			timo.rotate(-5);
			break;
		case 38:
			timo.moveInDirection(-5, 0f);
			break;
		case 39:
			timo.rotate(5);
			break;
		case 40:
			timo.moveInDirection(5, 0f);
		}
		view.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
