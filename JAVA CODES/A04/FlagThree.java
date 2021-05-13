import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * A class which represents Israeli Flag using Graphics of JPanel
 *
 * @author Md Ishfaq Alam
 */
public class FlagThree extends JPanel{
    //Class Constants which represent dimensions for -
    //Flag and the horizontal rectangle stripes in Top and Bottom
    private static final int WIDTH_OF_FLAG = 900;
    private static final int HEIGHT_OF_FLAG = 560;
    private static final int ACROSS_FOR_HORIZONTAL_RECTANGLE_TOP = 0;
    private static final int DOWN_FOR_HORIZONTAL_RECTANGLE_TOP = 60;
    private static final int ACROSS_FOR_HORIZONTAL_RECTANGLE_BOTTOM = 0;
    private static final int DOWN_FOR_HORIZONTAL_RECTANGLE_BOTTOM = 410;
    private static final int HEIGHT_FOR_HORIZONTAL_RECT = 80;
    private static final int WIDTH_FOR_HORIZONTAL_RECT = 900;
    
    //Class Arrays for holding Across and Down values for - 
    //Top and Bottom Horizontal Rectangle and the Star in the middle
    private static final int[] ACROSS_FOR_NORMAL_TRIANGLE = 
        {450, 320, 580};
    private static final int[] ACROSS_FOR_WHITE_NORMAL_TRIANGLE = 
        {450, 345, 555};
    private static final int[] DOWN_FOR_NORMAL_TRIANGLE = 
        {150, 345, 345};
    private static final int[] DOWN_FOR_WHITE_NORMAL_TRIANGLE = 
        {170, 330, 330};
    private static final int[] ACROSS_FOR_INVERTED_TRIANGLE = 
        {450, 320, 580};
    private static final int[] ACROSS_FOR_WHITE_INVERTED_TRIANGLE = 
        {450, 345, 555};
    private static final int[] DOWN_FOR_INVERTED_TRIANGLE = 
        {395, 200, 200};
    private static final int[] DOWN_FOR_WHITE_INVERTED_TRIANGLE = 
        {375, 215, 215};
    private static final int[] ACROSS_FOR_SIX_SIDED_POLYGON = 
        {416, 483, 528, 475, 425, 370};
    private static final int[] DOWN_FOR_SIX_SIDED_POLYGON = 
        {200, 200, 270, 345, 345, 270};
    private static final int[] ACROSS_FOR_SIX_SIDED_POLYGON_WHITE = 
        {420, 478, 515, 477, 422, 384};
    private static final int[] DOWN_FOR_SIX_SIDED_POLYGON_WHITE = 
        {215, 215, 270, 330, 330, 270};
    
    /**
     * Constructor which sets the basic dimension and name for the flag.
     */
    public FlagThree(){
        setPreferredSize(new Dimension(WIDTH_OF_FLAG, HEIGHT_OF_FLAG));
        setName("Israel");
    }

    /**
     * This method paints the overall components present in the flag
     * @param g - is a variable which points to a Graphics Object
     */
    @Override
    public void paintComponent(Graphics g){

        //set Background color
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH_OF_FLAG, HEIGHT_OF_FLAG);

        //set horizontal top blue Strip
        g.setColor(Color.BLUE);
        g.fillRect(
            ACROSS_FOR_HORIZONTAL_RECTANGLE_TOP, 
            DOWN_FOR_HORIZONTAL_RECTANGLE_TOP, 
            WIDTH_FOR_HORIZONTAL_RECT, 
            HEIGHT_FOR_HORIZONTAL_RECT);

        //Set horizontal bottom blue strip
        g.setColor(Color.BLUE);
        g.fillRect(
            ACROSS_FOR_HORIZONTAL_RECTANGLE_BOTTOM, 
            DOWN_FOR_HORIZONTAL_RECTANGLE_BOTTOM, 
            WIDTH_FOR_HORIZONTAL_RECT, 
            HEIGHT_FOR_HORIZONTAL_RECT);

        // Draw normal triangle (blue)
        g.setColor(Color.BLUE);
        g.fillPolygon(
            ACROSS_FOR_NORMAL_TRIANGLE, 
            DOWN_FOR_NORMAL_TRIANGLE, 3);

        //draw normal triangle (white)
        g.setColor(Color.WHITE);
        g.fillPolygon(
            ACROSS_FOR_WHITE_NORMAL_TRIANGLE, 
            DOWN_FOR_WHITE_NORMAL_TRIANGLE, 3);

        // draw inverted triangle (blue)
        g.setColor(Color.BLUE);
        g.fillPolygon(
            ACROSS_FOR_INVERTED_TRIANGLE, 
            DOWN_FOR_INVERTED_TRIANGLE, 3);

        //draw inverted triangle (white)
        g.setColor(Color.WHITE);
        g.fillPolygon(
            ACROSS_FOR_WHITE_INVERTED_TRIANGLE, 
            DOWN_FOR_WHITE_INVERTED_TRIANGLE, 3);

        //draw six sided polygon (blue)
        g.setColor(Color.BLUE);
        g.fillPolygon(
            ACROSS_FOR_SIX_SIDED_POLYGON, 
            DOWN_FOR_SIX_SIDED_POLYGON, 6);

        //draw six sided polygon (white)
        g.setColor(Color.WHITE);
        g.fillPolygon(
            ACROSS_FOR_SIX_SIDED_POLYGON_WHITE, 
            DOWN_FOR_SIX_SIDED_POLYGON_WHITE, 6);
    }

    /**
     * Create and show the FlagFrame with this flag in it.
     * @param args - runs the program
     */
    public static void main(String[] args) {
        FlagFrame f = new FlagFrame(new FlagThree());
        f.setVisible(true);
    }
}
