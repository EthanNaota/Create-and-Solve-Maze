package maze;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Maze extends JFrame {

	public Maze() {
		this.setVisible(true);
		
		this.add(new MazeGridPanel(150,150));
		this.setSize(1000, 1000);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
			new Maze();
	}
}
