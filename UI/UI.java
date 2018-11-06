package UI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;

public class UI extends JPanel{
	public final JFrame frame = new JFrame();
    private JMenuBar menubar;
    private static double zoom = 1.0;
    static String file;
    public UI() {
        initUI();
    }
 
    private void initUI() {
    	menubar = new JMenuBar();
    	frame.setJMenuBar(menubar);
    	
    	ImageIcon newIcon = new ImageIcon("D:\\load.gif");
        ImageIcon openIcon = new ImageIcon("D:\\load.gif");
        ImageIcon exitIcon = new ImageIcon("D:\\load.gif");
        
        JMenu menuFile = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New", newIcon);
        JMenuItem openItem = new JMenuItem("Open", openIcon);
        JMenuItem exitItem = new JMenuItem("Exit", exitIcon);
        newItem.setMnemonic(KeyEvent.VK_N);
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
        		ActionEvent.CTRL_MASK));
        exitItem.setMnemonic(KeyEvent.VK_E);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
        		ActionEvent.CTRL_MASK));
        
        exitItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               System.exit(0);
            }
            });
        menuFile.add(newItem);
        menuFile.add(openItem);
        menuFile.add(exitItem);
        frame.pack();
        menubar.add(menuFile);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.validate();
        frame.setVisible(true);
        openItem.setMnemonic(KeyEvent.VK_O);
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
        		ActionEvent.CTRL_MASK));
        
        openItem.addActionListener(new ActionListener(){
        	//@Override
        	public void actionPerformed(ActionEvent e){
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                	int returnValue = fileChooser.showOpenDialog(frame);
        			if(returnValue == JFileChooser.APPROVE_OPTION){
        			file = fileChooser.getSelectedFile().toString().replace("\\", "\\\\");
        			frame.remove(frame);
        			Surface panel = new Surface(file,(int)(zoom*15));
        			JOptionPane.showMessageDialog(frame, "Successful file parsing");
        			panel.setPreferredSize(new Dimension(6000, 3000));
//        			JButton b = new JButton();
//        			panel.add(b);
        	        JScrollPane pane = new JScrollPane(panel);
//        			frame.add(getCheckBoxPanel(), "South");
        	        frame.add(pane);
        			frame.setSize(500, 500);
        			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);        			
        	        frame.setLocationRelativeTo(null);
        		}
        			else {
            			JOptionPane.showMessageDialog(frame, "Fail file parsing");
            			
        			}
        	}
        });
//        System.out.println(file);
//        Surface panel = new Surface(file,(int)(zoom*20));
//        JButton b = new JButton();
//		panel.add(b);
//        
//        JScrollPane pane = new JScrollPane(panel);
//		JOptionPane.showMessageDialog(frame, panel.sizeFile1());
//		
//		frame.add(getCheckBoxPanel(), "South");
//		//frame.add(pane);
//		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        frame.setLocationRelativeTo(null);
    }

//	private JPanel getCheckBoxPanel(){
//    	JButton zoomIn = new JButton("Zoom In");
//    	zoomIn.addActionListener(new ActionListener(){
//    		@Override
//    		public void actionPerformed(ActionEvent e){
//    			zoomAndRepaint(0.1);
//    		}
//    	});
//    	JPanel panel = new JPanel();
//    	panel.add(zoomIn);
//    	return panel;
//    }
//    protected void zoomAndRepaint(double d){
//    	zoom +=d;
//    	frame.repaint();
//    }
}
