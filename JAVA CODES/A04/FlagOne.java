import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * This class creates a Bangladesh Flag
 * @author Md Ishfaq Alam
 */
public class FlagOne extends JPanel {

    //constants which represent dimensions for the flag and the circle
    private static final int WIDTH_OF_FLAG = 900;
    private static final int HEIGHT_OF_FLAG = WIDTH_OF_FLAG * 2 / 3;
    private static final int ACROSS = 350;
    private static final int DOWN = 210;

    /**
     * Constructor which sets the basic dimension and name for the flag.
     */
    public FlagOne() {
        setPreferredSize(new Dimension(WIDTH_OF_FLAG, HEIGHT_OF_FLAG));
        setName("Bangladesh");
    }

    /**
     * This method paints the overall components present in the flag
     *
     * @param g - is a variable which points to a Graphics Object
     */
    @Override
    public void paintComponent(Graphics g) {
        //SET GREEN COLOR BACKGROUND
        g.setColor(Color.green.darker());
        g.fillRect(0, 0, WIDTH_OF_FLAG, HEIGHT_OF_FLAG);

        //SET THE RED CIRCLE
        g.setColor(Color.RED);
        g.fillOval(ACROSS, DOWN, 180, 180);
    }

    /**
     * Create and show the FlagFrame with this flag in it.
     */
    public static void main(String[] args) {
        FlagFrame f = new FlagFrame(new FlagOne());
        f.setVisible(true);

    }
}
