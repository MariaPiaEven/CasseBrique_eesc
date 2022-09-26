package com.mpeven.cassebrique;

import javax.swing.*;
import java.awt.*;

public class CasseBrique extends Canvas {

    public CasseBrique() throws InterruptedException {
        JFrame fenetre = new JFrame("Casse brique");
        //On récupère le panneau de la fenetre principale
        JPanel panneau = (JPanel) fenetre.getContentPane();
        //On définie la hauteur / largeur de l'écran
        panneau.setPreferredSize(new Dimension(500, 500));
        setBounds(0, 0, 500, 500);
        //On ajoute cette classe (qui hérite de Canvas) comme composant du panneau principal
        panneau.add(this);

        fenetre.pack();
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.requestFocus();

        //On indique que le raffraichissement de l'ecran doit être fait manuellement.
        createBufferStrategy(2);
        setIgnoreRepaint(true);
        this.setFocusable(false);

        demarrer();
    }

    public void demarrer() throws InterruptedException {

        long indexFrame = 0;
        int xBalle = 250;
        int yBalle = 250;
        int vitesseVerticalBalle = -2;
        int vitesseHorizontalBalle = 3;
        int diametreBalle = 50;
        int sBalle = diametreBalle * 30 / 100;
        int decalageReflet = diametreBalle * 20 / 100;


        while (true) {
            indexFrame++;

            Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();

            //-------------------------------
            //Reset dessin
            dessin.setColor(Color.BLACK);
            dessin.fillRect(0, 0, 500, 500);

            //-------------------------------
            //Dessin Balle
            xBalle += vitesseHorizontalBalle;
            yBalle += vitesseVerticalBalle;
            dessin.setColor(Color.MAGENTA);
            dessin.fillOval(xBalle, yBalle, diametreBalle, diametreBalle);

            //Dessin Petite Balle
            dessin.setColor(Color.white);
            dessin.fillOval(
                    xBalle + decalageReflet,
                    yBalle + decalageReflet,
                    sBalle,
                    sBalle);

            //Mouvement balle
            if (xBalle < 0 || xBalle > 500 - diametreBalle) {
                vitesseHorizontalBalle *= -1;
                //vitesseHorizontalBalle = vitesseHorizontalBalle * -1
            }

            if (yBalle < 0 || yBalle > 500 - diametreBalle) {
                vitesseVerticalBalle *= -1;

            }

            //-------------------------------
            dessin.dispose();
            getBufferStrategy().show();
            Thread.sleep(1000 / 60);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new CasseBrique();
    }
}
