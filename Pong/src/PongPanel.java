import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;

public class PongPanel extends JPanel implements ActionListener, KeyListener {

	private final static Color BackgroundColor = Color.BLACK;
	private final static int TimerDelay = 5;	
	GameState gameState = GameState.Initialising;
	Ball ball;
	Paddle paddle1, paddle2;

	public PongPanel() {
		setBackground(BackgroundColor);
		Timer timer = new Timer(TimerDelay, this);
		timer.start();
	}

	public void createObjects() {
		ball = new Ball(getWidth(), getHeight());
		paddle1 = new Paddle(Player.One, getWidth(),getHeight());
		paddle2 = new Paddle(Player.Two, getWidth(),getHeight());
	}

	private void update() {
		switch(gameState) {
        case Initialising: {
            createObjects();
           gameState = GameState.Playing;
            break;
        }
        case Playing: {
            break;
       }
       case GameOver: {
           break;
       }
   }
	}
	
	private void paintSprite(Graphics g, Sprite sprite) {
	      g.setColor(sprite.getColour());
	      g.fillRect(sprite.getxPosition(), sprite.getyPosition(), sprite.getWidth(), sprite.getHeight());
	}

	private void paintDottedLine(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0);
		g2d.setStroke(dashed);
		g2d.setPaint(Color.WHITE);
		g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
		g2d.dispose();
	}

	 @Override
	 public void paintComponent(Graphics g) {
         super.paintComponent(g);
         paintDottedLine(g);
         if(gameState != GameState.Initialising) {
             paintSprite(g, ball);
             paintSprite(g, paddle1);
             paintSprite(g, paddle2);
         }
         
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
		update();
		repaint();
	}

	
}