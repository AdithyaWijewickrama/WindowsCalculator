// Java program to demonstrate BoxLayout
// class along Y-Axis
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.JTextField;

// construct a class Demo_1
public class Demo_1 {

	// Main Method
	public static void main(String[] args)
	{

		// Function to set up the window frame.
		JFrame.setDefaultLookAndFeelDecorated(true);

		// Creating Object of "JFrame" class
		JFrame frame = new JFrame("BoxLayout Example Y_AXIS");

		// Declaration of objects of JTextField class.
		          JTextField jbtn1, jbtn2, jbtn3, jbtn4, jbtn5;

		// Function to set the default close operation of JFrame the.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the panel to add buttons
		JPanel panel = new JPanel();

		// Creating Object of "boxlayout" in Y_Axis from top to down
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

		// to set the box layout
		panel.setLayout(boxlayout);

		// Set border for the panel
		panel.setBorder(new EmptyBorder(new Insets(100, 150, 100, 150)));

		// Initialization of object "jb1" of JTextField class.
		jbtn1 = new JTextField("Button 1");

		// Initialization of object "jb2" of JTextField class.
		jbtn2 = new JTextField("Button 2");

		// Initialization of object "jb3" of JTextField class.
		jbtn3 = new JTextField("Button 3");

		// Initialization of object "jb4" of JTextField class.
		jbtn4 = new JTextField("Button 4");

		// Initialization of object "jb5" of JTextField class.
		jbtn5 = new JTextField("Button 5");

		// Adding JTextField "jb1" on JFrame
		panel.add(jbtn1);

		// Adding JTextField "jb2" on JFrame
		panel.add(jbtn2);

		// Adding JTextField "jb3" on JFrame
		panel.add(jbtn3);

		// Adding JTextField "jb4" on JFrame
		panel.add(jbtn4);

		// Adding JTextField "jb5" on JFrame
		panel.add(jbtn5);

		// Function to set the panel of JFrame.
		frame.add(panel);

		// Function to use the pack of JFrame.
		frame.pack();

		// Function to set visible status of JFrame.
		frame.setVisible(true);
	}
}
