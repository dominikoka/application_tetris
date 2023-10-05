import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BallKeyListener implements KeyListener {

  private MyFrame panel;

  public BallKeyListener(MyFrame panel)
  {
    this.panel = panel;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    System.out.println("aaa");
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
  }

  @Override
  public void keyPressed(KeyEvent e) {
    System.out.println("bbb");
    //if(e.)
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
  }

  @Override
  public void keyReleased(KeyEvent e) {
    System.out.println("ccc");
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
  }
  
}
