package pck1;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Klasa implementuje ekran opcji gry z odpowiednimi
 * polami tekstowymi i przyciskami oraz dostêpem do
 * plikow z danymi najlepszego gracza
 * @author £ukasz Czarny
 */
public class Options extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Font f = new Font("Serif", Font.BOLD, 18);
	
	int num;

	/**
	 * Konstruktor przyjmuje referencje do okien tekstowych
	 * oraz przyciskow opcji gry i przekazuje je do metody
	 * ustawiajacej parametry oraz uruchamia metodê
	 * odczytujaca najwyzszy wynik z plikow.
	 * @param name_change
	 * @param High_Score
	 * @param NAME_ACCEPT
	 * @param EXIT_OPTIONS
	 * @param START
	 * @param OPTIONS
	 * @param EXIT
	 */
	public Options(JTextField name_change,JLabel High_Score, JButton NAME_ACCEPT,
			JButton EXIT_OPTIONS, JButton START, JButton OPTIONS, JButton EXIT){
		
		File_operation(High_Score);
		set_Menu(name_change,High_Score,EXIT_OPTIONS,START,OPTIONS,EXIT,NAME_ACCEPT);		
	}
	
	/**
	 * Metoda przyjmuje referencje do okien tekstowych
	 * oraz przyciskow Menu gry i ustawia odpowiednie
	 * parametry ka¿dego z nich.
	 * @param name_change
	 * @param High_Score
	 * @param EXIT_OPTIONS
	 * @param START
	 * @param OPTIONS
	 * @param EXIT
	 * @param NAME_ACCEPT
	 */
	public void set_Menu(JTextField name_change,JLabel High_Score,
			JButton EXIT_OPTIONS,JButton START, JButton OPTIONS, JButton EXIT,JButton NAME_ACCEPT)
	{
		name_change.setBounds(100,285,300,30);
		name_change.setEditable(true);
		name_change.setVisible(true);
		name_change.setFont(f);
	
		High_Score.setBounds(100, 170, 700, 50);
		High_Score.setVisible(true);
		
		NAME_ACCEPT.setLocation(450,275);
		NAME_ACCEPT.setVisible(true);
		NAME_ACCEPT.setText("Accept Player Name");
		NAME_ACCEPT.setPreferredSize(new Dimension(150,50));
		NAME_ACCEPT.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent l)
			{
				WindowPanel.player_name = name_change.getText();
			}
		});	
		
		EXIT_OPTIONS.setLocation(325,380);
		EXIT_OPTIONS.setVisible(true);
		EXIT_OPTIONS.setText("E X I T  O P T I O N S");
		EXIT_OPTIONS.setPreferredSize(new Dimension(150,50));
		EXIT_OPTIONS.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent l)
			{
				WindowPanel.game_state = 1;
				START.setVisible(true);
				OPTIONS.setVisible(true);
				EXIT.setVisible(true);
				name_change.setVisible(false);
				NAME_ACCEPT.setVisible(false);
				EXIT_OPTIONS.setVisible(false);
			}
		});		
	}
	
	/**
	 * Metoda odczytuje z plikow najwyzszy zdobyty
	 * wynik gracza oraz nazwe gracza.
	 * @param High_Score
	 */
	public void File_operation(JLabel High_Score)
	{
		String H_Player_Name = "No HighScore!!!!!";
		String H_Player_Score = "X";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("H_Player.txt"));
			H_Player_Name = br.readLine();
		    if(H_Player_Name == null)
		    {
		    	H_Player_Name = "No HighScore!!!!!";
		    	H_Player_Score = "";
		    }
		    br.close();
		} catch (IOException ex) {
			ex.setStackTrace(null);
	    }
	    
		if(H_Player_Score != "")
		{
			try {
				BufferedReader nb = new BufferedReader(new FileReader("H_Score.txt"));
				H_Player_Score = nb.readLine();
				num = Integer.parseInt(H_Player_Score.trim());
				nb.close();
			} catch (IOException ex) {
				ex.setStackTrace(null);
			}
		}
		if(H_Player_Score == "")
			High_Score.setText("HIGHSCORE        " + H_Player_Name);
		else
			High_Score.setText("HIGHSCORE        " + H_Player_Name + "            " + num);
	}
}
