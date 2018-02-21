package pck1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.*;

import javax.imageio.ImageIO;

/**
 * Klasa implementuje ekran gry oraz wszystkie pozostale
 * wystepujace w grze ekrany czyli: Koniec gry, opcje oraz
 * Menu.
 * Implementuje tak¿e animacje ruchu, detekcjê kolizji,
 * oraz sterowanie w grze.
 * @author £ukasz Czarny
 *
 */
public class WindowPanel extends JPanel implements KeyListener, ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Timer timer;
	
	private BufferedImage Background;
	private BufferedImage Player_Car;
	private BufferedImage Enemy_Car;
	private BufferedImage Game_Over_Screen;
	
	JButton START = new JButton();
	JButton EXIT = new JButton();
	JButton OPTIONS = new JButton();
	JButton NAME_ACCEPT = new JButton();
	JButton EXIT_OPTIONS = new JButton();
	
	JLabel Game_Over_Score = new JLabel();
	JLabel High_Score = new JLabel();
	
	MyTextArea Score_Table  =  new MyTextArea(3, 20);
	
	JTextField Name_Change = new JTextField(30);
	
	Font f = new Font("Serif", Font.BOLD, 18);
		
	Random rand = new Random();

	Graphics2D g2d;
	
	int[] position;
	int score = 0;
	int enemy_car1,enemy_car2;	
	int player_car_position = 160;
	int player_car_pos_lr = 0;
	int enemy_car_position = 800;
	int speed = 1;
	int speed_max = 1;
	int speed_diff = 1;
	static int game_state = 1;
	boolean guard = false;	
	static String player_name;
	
	/**
	 * Konstruktor wczytuje pod odpowiednie zmienne BufferedImage
	 * obrazy wykorzystywane w grze i ustawia parametry okna tekstowego
	 * z informacjami o grze.
	 * Ustawia takze pozycje aut w grze oraz ustawia domyslna nazwe gracza.
	 */
	public WindowPanel()
	{
		File imageFILE1 = new File("Background.png");
		File imageFILE2 = new File("Car.png");
		File imageFILE3 = new File("Enemy_Car.png");
		File imageFILE4 = new File("end.png");

		position = new int[3];
		
		position[0] = 160;
		position[1] = 263;
		position[2] = 376;
		
		player_name = "Player1";
		
		enemy_car1 = rand.nextInt(3);
		enemy_car2 = rand.nextInt(3);
		while(enemy_car2 == enemy_car1)
		{
			enemy_car2 = rand.nextInt(3);
		}
			
		try{
			Background=ImageIO.read(imageFILE1);
			Player_Car=ImageIO.read(imageFILE2);
			Enemy_Car=ImageIO.read(imageFILE3);
			Game_Over_Screen=ImageIO.read(imageFILE4);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		Score_Table.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
		Score_Table.setEditable(false);  
		Score_Table.setFont(new Font("Arial", 0, 18));
		Score_Table.setForeground(Color.BLACK);
		Score_Table.setBackground(new Color(1,1,1, (float) 0.01));
		
		High_Score.setFont(new Font("Arial", 0, 30));
		High_Score.setForeground(Color.WHITE);
	}
	
	/**
	 * Funkcja rysujaca okno gry w zaleznosci od stanu
	 * w jakim znajduje sie gra
	 * game_state = 1	Menu gry
	 * game_state = 2	Ekran gry
	 * game_state = 3	Ekran koñca gry
	 * game_state = 4	Ekran opcji gry
	 * @param g
	 */
	@SuppressWarnings("unused")
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g2d = (Graphics2D)g;
		
		setPreferredSize(new Dimension(800,600));
		
		addKeyListener(this);
		
		if(game_state == 1)
		{						
			score = 0;
			guard = false;
			
			g2d.drawImage(Background, 0,0,this);
			
			Score_Table.setVisible(false);
			High_Score.setVisible(false);
			
			Menu menu = new Menu(START,OPTIONS,EXIT,Score_Table);
			
			add(START);
			add(EXIT);
			add(OPTIONS);
		}
		if(game_state == 2)
		{	
			Score_Table.setText("         Player name = " + player_name + "\n"  +"         Score = " + score + "\n" 
					+ "         Difficulty level = " + speed_max);  
							
			timer = new Timer(5, this);
			timer.start();
			setFocusable(true);
			requestFocusInWindow();
				
			g2d.drawImage(Background, 0,0,this);
			g2d.drawImage(Player_Car,player_car_pos_lr,player_car_position,this);				
			g2d.drawImage(Enemy_Car,enemy_car_position,position[enemy_car1],this);
			g2d.drawImage(Enemy_Car,enemy_car_position,position[enemy_car2],this);
				
			add(Score_Table);		
		}
		if(game_state == 3)
		{
			Score_Table.setVisible(false);
			
			g2d.drawImage(Game_Over_Screen,0,0,this);	
			
			GameOver GO = new GameOver(score, Game_Over_Score);
			
			add(Game_Over_Score);
			
			player_car_pos_lr = 0;
		}
		if(game_state == 4)
		{
			g2d.drawImage(Background,0,0,this);		
			
			Options options = new Options(Name_Change, High_Score, NAME_ACCEPT, EXIT_OPTIONS, START, OPTIONS, EXIT);
		
			add(Name_Change);
			add(High_Score);
			add(NAME_ACCEPT);
			add(EXIT_OPTIONS);
			
			EXIT.setLocation(325,480);
			EXIT.setVisible(true);
		}
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if(game_state == 3)
		{
			game_state = 1;
			Game_Over_Score.setVisible(false);
			repaint();
		}
		
		if (key.getKeyCode()==40)
		{	
			if(guard == false)
			{
				if (player_car_position == 263)
				{
					player_car_position = 376;
				}
				else if(player_car_position == 160)
				{
					player_car_position = 263;
				}
				guard = true;
			}
		}
		else if (key.getKeyCode()==38)
		{
			if(guard == false)
			{
				if(player_car_position == 263)
				{
					player_car_position = 160;
				}
				else if (player_car_position == 376)
				{
					player_car_position = 263;
				}
				guard = true;
			}
		}
		else if (key.getKeyCode() == 39)
		{
			if(guard == false)
			{	
				if(player_car_pos_lr < 600)
				{
					player_car_pos_lr+=40;
					guard = true;
				}
			}			
		}
		else if (key.getKeyCode()==37)
		{
			if(guard == false)
			{	
				if(player_car_pos_lr > 0)
				{
					player_car_pos_lr-=40;
					guard = true;
				}
			}	
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		guard = false;
		g2d.drawImage(Player_Car, 0,player_car_position,this);
		repaint();		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == timer)
		{		
			timer.stop();
			if(speed == 4700 && speed_max < 4)
			{
				speed_diff += 2;
				speed_max++;
				speed = 1;	
				Score_Table.setText("         Player name = " + player_name + "\n"  +"         Score = " + score + "\n" 
						+ "         Difficulty level = " + speed_max);
			}
			
			speed ++;
			enemy_car_position = enemy_car_position - speed_diff;
			
			if(enemy_car_position < -150)
			{
				score += 100;
				Score_Table.setText("         Player name = " + player_name + "\n"  +"         Score = " + score + "\n" 
						+ "         Difficulty level = " + speed_max); 
				
				enemy_car_position = 800;
				enemy_car1 = rand.nextInt(3);
				enemy_car2 = rand.nextInt(3);
				while(enemy_car2 == enemy_car1)
				{
					enemy_car2 = rand.nextInt(3);
				}
			}
			
			if(((enemy_car_position < (player_car_pos_lr+150) && enemy_car_position > player_car_pos_lr) ||
					(player_car_pos_lr < enemy_car_position+150) && player_car_pos_lr > enemy_car_position) && 
					(player_car_position == position[enemy_car1] || player_car_position == position[enemy_car2]))
			{
				game_state = 3;
				enemy_car_position = 800;
				speed = 1;
				speed_max = 1;
				speed_diff = 1;
			}
			repaint();
		}
	}
}
