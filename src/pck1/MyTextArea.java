package pck1;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JTextArea;

public class MyTextArea extends JTextArea {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;

    public MyTextArea(int a, int b) {
        super(a,b);
        try{
            img = ImageIO.read(new File("Stats_bcg.png"));
        } catch(IOException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(img,0,0,null);
        super.paintComponent(g);
    }
}
