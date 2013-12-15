package ch.dorf10.logic;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import ch.dorf10.view.View;


public class Map {
	
	private Set<Creature> creatures;
	
	public Map() {
		creatures = new HashSet<Creature>();
	}
	
	public void addCreature(Creature c) {
		creatures.add(c);
	}
	
	public void paint(Graphics g, View view) {
		for (Creature c : creatures) {
			c.paint(g, view);
		}
	}
}
