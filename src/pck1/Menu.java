package pck1;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Klasa implementuje ekran Menu gry z odpowiednimi
 * polami tekstowymi i przyciskami.
 * @author £ukasz Czarny
 */
public class Menu extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor przyjmuje referencje do obszaru tekstowego
	 * oraz przyciskow opcji gry i przekazuje je do metody
	 * ustawiajacej parametry.
	 * @param start
	 * @param options
	 * @param exit
	 * @param score_table
	 */
	public Menu(JButton start, JButton options, JButton exit,JTextArea score_table)
	{		
		set_Menu(start,options,exit,score_table);
	}
	
	/**
	 * Metoda przyjmuje referencje do okien tekstowych
	 * oraz przyciskow Menu gry i ustawia odpowiednie
	 * parametry kazdego z nich.
	 * @param start
	 * @param options
	 * @param exit
	 * @param score_table
	 */
	public void set_Menu(JButton start, JButton options, JButton exit,JTextArea score_table)
	{
		start.setLocation(325,170);
		start.setVisible(true);
		start.setText("P L A Y");
		start.setPreferredSize(new Dimension(150, 50));
		start.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent e)
			{
				WindowPanel.game_state = 2;
				start.setVisible(false);
				options.setVisible(false);
				exit.setVisible(false);
				score_table.setVisible(true);
				repaint();
			}	
		});
		
		options.setLocation(325,274);
		options.setVisible(true);
		options.setText("O P T I O N S");
		options.setPreferredSize(new Dimension(150, 50));
		options.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent e)
			{
			    start.setVisible(false);
				options.setVisible(false);
				exit.setVisible(false);
				WindowPanel.game_state = 4;
				repaint();
			}
	
		});
		
		exit.setLocation(325,380);
		exit.setVisible(true);
		exit.setText("E X I T  G A M E");
		exit.setPreferredSize(new Dimension(150,50));
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent f)
			{
				System.exit(0);
			}
		});		
	}
}
