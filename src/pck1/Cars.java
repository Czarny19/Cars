package pck1;

import java.awt.EventQueue;

/**
 * Klasa odpowiada za uruchomienie gry "Cars"
 * W grze zadaniem gracza jest wyminiecie jak najwiekszej
 * ilosci aut przeciwnika bez uderzania w nie.
 * Sterowanie w grze odbywa si� za pomoc� strza�ek:
 * gora oraz dol.
 * @author �ukasz Czarny
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
