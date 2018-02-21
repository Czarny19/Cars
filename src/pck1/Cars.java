package pck1;

import java.awt.EventQueue;

/**
 * Klasa odpowiada za uruchomienie gry "Cars"
 * W grze zadaniem gracza jest wyminiecie jak najwiekszej
 * ilosci aut przeciwnika bez uderzania w nie.
 * Sterowanie w grze odbywa siê za pomoc¹ strza³ek:
 * gora oraz dol.
 * @author £ukasz Czarny
 *
 */
public class Cars {

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WindowFrame();
            }
        });
	}
}
