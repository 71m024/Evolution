package ch.dorf10.view;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


public class Window extends JFrame{

	private static final long serialVersionUID = 1L;
	private Container cp;
	
	public Window(String name, KeyListener keyListener) {
		super(name);
		this.addKeyListener(keyListener);
		cp = getContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void setView(View view) {
		cp.removeAll();
		cp.add(view);
		setBounds(new Rectangle(view.getBounds()));
	}
}
