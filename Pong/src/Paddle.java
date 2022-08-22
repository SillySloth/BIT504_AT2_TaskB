import java.awt.Color;

public class Paddle extends Sprite {

	private final static int paddleWidth = 10;
	private final static int paddleHeight = 100;	
	private final static Color paddleColor = Color.white;
	private final static int paddleEdgeGap = 40;
	
	public Paddle(Player player, int panelWidth, int panelHeight) {
		setWidth(paddleWidth);
		setHeight(paddleHeight);
		setColor(paddleColor);
		int xPos;
		
		if(player == Player.One) {
            xPos = paddleEdgeGap;
        } else {
            xPos = panelWidth - paddleEdgeGap - getWidth();
        }
        setInitialPosition(xPos, panelHeight / 2 - (getHeight() / 2));
        resetToInitialPosition();
	}
	
}
