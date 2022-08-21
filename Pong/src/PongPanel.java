import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class PongPanel extends JPanel implements ActionListener, KeyListener {

	private final static Color BackgroundColor = Color.BLACK;
	private final static int TimerDelay = 5;
	
	public PongPanel() {
		setBackground(BackgroundColor);
		Timer timer = new Timer(TimerDelay, this);
        timer.start();
	}
	
	 private void update() {
         
	 }

	@Override
	public void keyTyped(KeyEvent e) {		

	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	 @Override
	 public void paintComponent(Graphics g) {
	     super.paintComponent(g);
	     g.setColor(Color.WHITE);
	     g.fillRect(20, 20, 30, 200);
	 }
	
	

}