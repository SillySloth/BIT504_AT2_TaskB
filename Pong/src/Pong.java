import javax.swing.JFrame;

public class Pong extends JFrame {

	private final static String WINDOW_TITLE = "Pong";
	private final static int WINDOW_WIDTH = 1200;
	private final static int WINDOW_HEIGHT = 800;

	public Pong() {
		setTitle(WINDOW_TITLE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT); // (width, height)
		setResizable(true);
		add(new PongPanel());
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Pong();
			}
		});
	}

}
