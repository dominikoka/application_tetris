import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class MyFrame extends JFrame {
	private MyPanel panel;
    
	
    public MyPanel getPanel() {
		return panel;
	}

	public MyFrame() {
        super("- - - t e t r i s - - -");
        panel = new MyPanel();
        add(panel); 
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        addKeyListener(panel);
        
    }
	public static void main(String[] args) throws InterruptedException {
		new MyFrame().getPanel().proccesor();
	}
    
//     @Override
//     public void keyTyped(KeyEvent e) {
        
        
//     }

//     @Override
//     public void keyPressed(KeyEvent e) {
//         // TODO Auto-generated method stub
//         System.out.println(e.getKeyChar());
//         Integer c = e.getKeyCode();
//         switch(c){
//             case 87:
//             System.out.println("wcisnales w");
            
//             paint(getGraphics());
//             break;
//         }
        
//     }

//     @Override
//     public void keyReleased(KeyEvent e) {
//         // TODO Auto-generated method stub
        
//     }
 }