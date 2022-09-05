import java.awt.Color;
import java.awt.Font;
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

	private final static Color BackgroundColor = Color.black;
	private final static int TimerDelay = 5;
	GameState gameState = GameState.Initialising;
	Ball ball;
	Paddle paddle1, paddle2;
	private final static int ballMovementSpeed = 2;
	private final static int pointsToWin = 3;
	int player1Score = 0, player2Score = 0;
	Player gameWinner;
	private final static int xPadding = 100;
	private final static int yPadding = 100;
	private final static int fontSize = 50;
	private final static String FontFamily = "Serif";

	public PongPanel() {
		setBackground(BackgroundColor);
		Timer timer = new Timer(TimerDelay, this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
	}

	public void addScore(Player player) {
		if (Player.One == player) {
			player1Score++;
		}
		if (Player.Two == player) {
			player2Score++;
		}
	}

	public void checkWin() {
		if (player1Score >= pointsToWin) {
			gameWinner = Player.One;
			gameState = GameState.GameOver;
		} else if (player2Score >= pointsToWin) {
			gameWinner = Player.Two;
			gameState = GameState.GameOver;
		}
	}

	public void createObjects() {
		ball = new Ball(getWidth(), getHeight());
		paddle1 = new Paddle(Player.One, getWidth(), getHeight());
		paddle2 = new Paddle(Player.Two, getWidth(), getHeight());
	}

	private void update() {
		switch (gameState) {
		case Initialising: {
			createObjects();
			gameState = GameState.Playing;
			ball.setxVelocity(ballMovementSpeed);
			ball.setyVelocity(ballMovementSpeed);
			break;
		}
		case Playing: {
			moveObject(paddle1);
			moveObject(paddle2);
			moveObject(ball); // Move ball
			checkWallBounce(); // Check for wall bounce
			checkPaddleBounce(); // Check for paddle bounce
			checkWin(); // Check if player has won
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
		g2d.setPaint(Color.white);
		g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
		g2d.dispose();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintDottedLine(g);
		if (gameState != GameState.Initialising) {
			paintSprite(g, ball);
			paintSprite(g, paddle1);
			paintSprite(g, paddle2);
			paintScores(g);
		}
		if (gameState == GameState.GameOver) {
			paintWin(g);
		}

	}

	private void paintScores(Graphics g) {
		Font scoreFont = new Font(FontFamily, Font.BOLD, fontSize);
		String leftScore = Integer.toString(player1Score);
		String rightScore = Integer.toString(player2Score);
		g.setFont(scoreFont);
		g.drawString(leftScore, xPadding, yPadding);
		g.drawString(rightScore, getWidth() - xPadding, yPadding);
	}

	private void paintWin(Graphics g) {

		int xWinnerPadding = 200;
		int	yWinnerPadding = 200;		
		String winText = "Winner!";
		
		if (gameWinner != null) {
			
			Font WinFont = new Font(FontFamily, Font.BOLD, fontSize);
			g.setFont(WinFont);
			
			int textPosition = getWidth() / 2;
			
			if (gameWinner == Player.One) {
				textPosition -= xWinnerPadding;
			}
			else if (gameWinner == Player.Two) {
				textPosition += xWinnerPadding;
			}
			
			g.drawString(winText, textPosition, yWinnerPadding);
		}		
	}

	private void checkPaddleBounce() {
		if (ball.getxVelocity() < 0 && ball.getRectangle().intersects(paddle1.getRectangle())) {
			ball.setxVelocity(ballMovementSpeed);
		}
		if (ball.getxVelocity() > 0 && ball.getRectangle().intersects(paddle2.getRectangle())) {
			ball.setxVelocity(-ballMovementSpeed);
		}
	}

	public void moveObject(Sprite obj) {
		obj.setxPosition(obj.getxPosition() + obj.getxVelocity(), getWidth());
		obj.setyPosition(obj.getyPosition() + obj.getyVelocity(), getHeight());
	}

	private void checkWallBounce() {
		if (ball.getxPosition() <= 0) {
			// Hit left side of screen
			ball.setxVelocity(-ball.getxVelocity());
			addScore(Player.Two);
			resetBall();
		} else if (ball.getxPosition() >= getWidth() - ball.getWidth()) {
			// Hit right side of screen
			ball.setxVelocity(-ball.getxVelocity());
			addScore(Player.One);
			resetBall();
		}
		if (ball.getyPosition() <= 0 || ball.getyPosition() >= getHeight() - ball.getHeight()) {
			// Hit top or bottom of screen
			ball.setyVelocity(-ball.getyVelocity());

		}
	}

	private void resetBall() {
		ball.resetToInitialPosition();
	}	

	@Override
	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_UP) {
			paddle2.setyVelocity(-5);
		} else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
			paddle2.setyVelocity(5);
		}

		if (event.getKeyCode() == KeyEvent.VK_W) {
			paddle1.setyVelocity(-5);
		} else if (event.getKeyCode() == KeyEvent.VK_S) {
			paddle1.setyVelocity(5);
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN) {
			paddle2.setyVelocity(0);
		}

		else if (event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_S) {
			paddle1.setyVelocity(0);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		repaint();
	}

}