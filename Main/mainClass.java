package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import UI.UI;

public class mainClass {
	JFrame frame;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
	         @Override
	         public void run() {
	             UI ex = new UI();
	             ex.frame.setVisible(true);
	         }
	     });
	}

}
