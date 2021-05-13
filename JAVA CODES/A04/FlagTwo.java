import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * This class creates a Jamaican Flag 
 * @author Md Ishfaq Alam
 */
public class FlagTwo extends JPanel {
    
    //Constants which represent dimensions of Flags and its components inside
    private static final int WIDTH_OF_FLAG = 900;
    private static final int HEIGHT_OF_FLAG = WIDTH_OF_FLAG * 2 / 3;
    private static final int[] ACROSS_FOR_TOP_TRIANGLE = {70, 450, 830};
    private static final int[] DOWN_FOR_TOP_TRIANGLE = {0, 250, 0};
    private static final int[] ACROSS_FOR_BOTTOM_TRIANGLE = {70, 450, 830};
    private static final int[] DOWN_FOR_BOTTOM_TRIANGLE = {600, 350, 600};
    private static final int[] ACROSS_FOR_LEFT_HAND_TRIANGLE = {0, 395, 0};
    private static final int[] DOWN_FOR_LEFT_HAND_TRIANGLE = {35, 300, 565};
    private static final int[] ACROSS_FOR_RIGHT_HAND_TRIANGLE = 
        {900, 525, 900};
    private static final int[] DOWN_FOR_RIGHT_HAND_TRIANGLE = {35, 300, 565};
    
    //Constant which represents GOLDEN colour
    public static final Color GOLD = new Color(255, 204, 51);

    /**
     *  Constructor which sets the basic dimension and name for the flag.
     */
    public FlagTwo() {
        setPreferredSize(new Dimension(WIDTH_OF_FLAG, HEIGHT_OF_FLAG));
        setName("Jamaican");
    }

    /**
     * This method paints the overall components present in the flag
     * @param g - is a variable which points to a Graphics Object
     */
    @Override
    public void paintComponent(Graphics g) {

        //SET BACKGROUND COLOR
        g.setColor(GOLD);
        g.fillRect(0, 0, WIDTH_OF_FLAG, HEIGHT_OF_FLAG);

        //Set top green triangle
        g.setColor(Color.GREEN.darker());
        g.fillPolygon(ACROSS_FOR_TOP_TRIANGLE, 
            DOWN_FOR_TOP_TRIANGLE, 3);

        //set bottom green triangle
        g.setColor(Color.GREEN.darker());
        g.fillPolygon(ACROSS_FOR_BOTTOM_TRIANGLE, 
            DOWN_FOR_BOTTOM_TRIANGLE, 3);

        //set left hand triangle (Black)
        g.setColor(Color.BLACK);
        g.fillPolygon(ACROSS_FOR_LEFT_HAND_TRIANGLE, 
            DOWN_FOR_LEFT_HAND_TRIANGLE, 3);

        //set right hand triangle (Black) 
        g.setColor(Color.BLACK);
        g.fillPolygon(ACROSS_FOR_RIGHT_HAND_TRIANGLE, 
            DOWN_FOR_RIGHT_HAND_TRIANGLE, 3);
    }

    /**
     * Create and show the FlagFrame with this flag in it.
     */
    public static void main(String[] args) {
        FlagFrame f = new FlagFrame(new FlagTwo());
        f.setVisible(true);
    }
}
