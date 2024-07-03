import java.awt.Color;


import javax.swing.JButton;
import javax.swing.JFrame;
 
public class MyFrame extends JFrame {
	private MyPanel panel;
    
	
    public MyPanel getPanel() {
		return panel;
	}

    
    JButton newGameButton;
    JButton exitButton;
    JButton result;
	public MyFrame() {
        super("- - - t e t r i s - - -");
        panel = new MyPanel();
        add(panel); 
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setBackground(Color.BLACK);
        //loadbutton.setFocusable(false);

        setVisible(true);
        addKeyListener(panel);

        
        
    }
	public static void main(String[] args) throws InterruptedException {
		new MyFrame().getPanel().run();
       
	}
 }