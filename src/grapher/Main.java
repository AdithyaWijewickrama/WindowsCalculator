package grapher;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

import window.GraphingPanel;
import parser.ExpressionParser;
import parser.TokenString;

public class Main {
	
	public static void main(String[] args) {
		GraphingPanel window = new GraphingPanel();
		
		JFrame frame = new JFrame("Function Grapher");
            frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
		frame.getContentPane().add(window);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(window).start();
	}
}
