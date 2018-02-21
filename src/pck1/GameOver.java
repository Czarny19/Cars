package pck1;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Klasa implementuje ekran konca gry z odpowiednimi
 * etykietami oraz zapisuje do plikow nowy najlepszy wynik.
 * @author £ukasz Czarny
 */
public class GameOver extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	String H_Player_Score = null;
	int num;
	boolean new_highscore = false;
	
	/**
	 * Konstruktor przyjmuje referencje do etykiety 
	 * i przekazuje je do metody ustawiaj¹cej parametry 
	 * oraz pole z wartoscia wyniku gracza
	 * i zapisuje go do plikow jezeli najwyzszy wynik
	 * zostal pobity.
	 * @param score
	 * @param Game_Over_Score_Recived
	 */
	public GameOver(int score, JLabel Game_Over_Score_Recived)
	{	
		set_GameOverScreen(score,Game_Over_Score_Recived);
		File_operation(score);
	}
	
	/**
	 * Metoda przyjmuje wartosc zdobytego wyniku przez gracza
	 * i zapisuje do plikow wynik gracza 
	 * oraz nazwe gracza jezeli pobil najwyzszy wynik.
	 * @param score
	 */
	public void File_operation(int score)
	{
		try
		{
			BufferedReader R1 = new BufferedReader(new FileReader("H_Score.txt"));
			H_Player_Score = R1.readLine();
			R1.close();
			PrintWriter W1 = new PrintWriter("H_Score.txt");
			if (H_Player_Score == null)
			{
				W1.print(score);
				new_highscore = true;				
				W1.close();
			}
			else
			{	
				num = Integer.parseInt(H_Player_Score.trim());
				if(score > num)
				{
					W1.print(score);	
					new_highscore = true;
				}
				else
				{
					W1.print(num);
				}
				W1.close();
			}
			} catch (IOException ex) {
		}
		if(new_highscore)
		{
			try{
				PrintWriter W2 = new PrintWriter("H_Player.txt", "UTF-8");
				W2.print(WindowPanel.player_name);
			    W2.close();
			    new_highscore = false;
			} catch (IOException ex) {
		    }
		}		
	}
	
	/**
	 * Metoda przyjmuje referencje do etykiety
	 * i ustawia odpowiednie dla niej parametry
	 * oraz wartoœæ wyniku gracza do wyœwietlenia
	 * po przegranej.
	 * @param score
	 * @param Game_Over_Score_Recived
	 */
	public void set_GameOverScreen(int score, JLabel Game_Over_Score_Recived)
	{
		Game_Over_Score_Recived.setText("" + score);
		Game_Over_Score_Recived.setLocation(500,450);
		Game_Over_Score_Recived.setVisible(true);
		Game_Over_Score_Recived.setFont(new Font("Arial", 0, 30));
		Game_Over_Score_Recived.setForeground(Color.WHITE);		
	}
}
