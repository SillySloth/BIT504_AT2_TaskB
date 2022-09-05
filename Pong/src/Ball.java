import java.awt.Color;

public class Ball extends Sprite{

	private final static Color ballColor = Color.white;
	private final static int ballHeight = 25;
	private final static int ballWidth = 25;
	
	public Ball(int panelWidth, int panelHeight) {
		setWidth(ballWidth);
        setHeight(ballHeight);
        setColor(ballColor);
        setInitialPosition(panelWidth / 2 - (getWidth() / 2), panelHeight / 2 - (getHeight() / 2));
        resetToInitialPosition();
	}	
}
