package pck1;

import javax.swing.JFrame;

/**
 * Klasa tworzy ramke okienka pod nazwa "Cars"
 * @author £ukasz Czarny
 */
public class WindowFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Konstruktor ustawia parametry ramki okienka
	 */
	public WindowFrame()
	{
		setTitle("CARS");
		WindowPanel panel = new WindowPanel();	
		add(panel);
        setSize(800,600);         
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);      	
	}
}
