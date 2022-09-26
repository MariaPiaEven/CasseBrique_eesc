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
        Balle balle = new Balle(250, 250, 4, -6, 30, Color.magenta);

        while (true) {
            indexFrame++;

            Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();

            //-------------------------------
            //Reset dessin
            dessin.setColor(Color.BLACK);
            dessin.fillRect(0, 0, 500, 500);

            //-------------------------------
            //Dessin com.mpeven.cassebrique.Balle
            balle.setX(balle.getX() + balle.getVitesseHorizontal());
            balle.setY(balle.getY() + balle.getVitesseVertical());
            dessin.setColor(balle.getCouleur());
            dessin.fillOval(balle.getX(), balle.getY(), balle.getDiametre(), balle.getDiametre());

            //Dessin Petite com.mpeven.cassebrique.Balle
            dessin.setColor(Color.white);
            dessin.fillOval(
                    balle.getX() + balle.getDecalageReflet(),
                    balle.getY() + balle.getDecalageReflet(),
                    balle.getDiametreReflet(),
                    balle.getDiametreReflet());

            //Mouvement balle
            if (balle.getX() < 0 || balle.getX() > 500 - balle.getDiametre()) {
                balle.setVitesseHorizontal(balle.getVitesseHorizontal() * -1);
                //vitesseHorizontalBalle = vitesseHorizontalBalle * -1
            }

            if (balle.getY() < 0 || balle.getY() > 500 - balle.getDiametre()) {
                balle.setVitesseVertical(balle.getVitesseVertical() * -1);

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
